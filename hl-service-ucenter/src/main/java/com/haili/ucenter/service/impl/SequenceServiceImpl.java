package com.haili.ucenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.system.CodeRule;
import com.haili.framework.domain.system.Sequence;
import com.haili.framework.domain.system.response.SequenceCode;
import com.haili.framework.exception.ExceptionCast;
import com.haili.framework.model.response.CommonCode;
import com.haili.ucenter.mapper.CodeRuleMapper;
import com.haili.ucenter.mapper.SequenceMapper;
import com.haili.ucenter.service.ISequenceService;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-28
 */
@Service
@Transactional
public class SequenceServiceImpl extends ServiceImpl<SequenceMapper, Sequence> implements ISequenceService {

    @Autowired
    CodeRuleMapper codeRuleMapper;

    public String nextSerialNumber(Map<String, Object> map) {
        String codeRuleName = (String) map.get("codeRuleName");
        LambdaQueryWrapper<CodeRule> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(CodeRule::getName, codeRuleName);
        CodeRule codeRule = codeRuleMapper.selectOne(wrapper);
        if (codeRule == null) {
            ExceptionCast.cast(SequenceCode.SEQUENCE_CANNOT_DETERMINE_CODE_RULE);
        }
        String rule = codeRule.getRule();
        String resetType = codeRule.getResetType();
        String minNumber = codeRule.getMinNumber();
        String maxNumber = codeRule.getMaxNumber();
        Integer seqLength = minNumber.length();
        Long minValue = 1L;
        Long maxValue = 9999L;
        DayOfWeek firstDayOfWeek = DayOfWeek.MONDAY;
        try {
            minValue = Long.parseLong(minNumber);
            maxValue = Long.parseLong(maxNumber);
            firstDayOfWeek = DayOfWeek.valueOf(codeRule.getFirstDayOfWeek().toUpperCase());
        } catch (NumberFormatException e) {
            ExceptionCast.cast(SequenceCode.SEQUENCE_CANNOT_RESOLVE_MIN_OR_MAX);
        } catch (IllegalArgumentException e) {
            ExceptionCast.cast(SequenceCode.SEQUENCE_CANNOT_RESOLVE_FIRST_DAY_OF_WEEK);
        }
        LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));

        if (rule.contains("[SEQ]")) {
            String bizName = (String) map.get("bizName");
            if (bizName == null) {
                ExceptionCast.cast(SequenceCode.SEQUENCE_CANNOT_DETERMINE_BIZ_NAME);
            }
            QueryWrapper<Sequence> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", bizName);
            Sequence sequence = this.baseMapper.selectOne(queryWrapper);
            if (sequence == null) {
                sequence = new Sequence();
                sequence.setName(bizName);
                sequence.setValue(minValue);
                sequence.setResetTime(now);
                this.baseMapper.insert(sequence);
                return nextSerialNumber(rule, now, minValue, seqLength, map);
            }
            LocalDateTime resetTime = sequence.getResetTime();
            Boolean needReset = isNeedReset(resetType, now, resetTime, firstDayOfWeek);
            if (needReset) {
                sequence.setValue(minValue);
                sequence.setResetTime(now);
            } else {
                sequence.setValue(sequence.getValue() + 1);
                if (sequence.getValue() > maxValue) {
                    ExceptionCast.cast(SequenceCode.SEQUENCE_MAXIMUM_EXCEEDED);
                }
            }
            this.baseMapper.updateById(sequence);
            return nextSerialNumber(rule, now, sequence.getValue(), seqLength, map);
        } else {
            return nextSerialNumber(rule, now, null, null, map);
        }
    }

    private Boolean isNeedReset(String resetType, LocalDateTime resetTime, LocalDateTime now, DayOfWeek firstDayOfWeek) {
        Boolean needReset = false;
        LocalDateTime dateTime1, dateTime2;
        switch (resetType) {
            case "DAILY":
                dateTime1 = resetTime.toLocalDate().atStartOfDay();
                dateTime2 = now.toLocalDate().atStartOfDay();
                if (!dateTime1.equals(dateTime2)) {
                    needReset = true;
                }
                break;
            case "WEEK":
                if (firstDayOfWeek == null) {
                    ExceptionCast.cast(SequenceCode.SEQUENCE_FIRST_DAY_OF_WEEK_NOT_DETERMINED);
                }
                dateTime1 = resetTime.with(TemporalAdjusters.previousOrSame(firstDayOfWeek)).with(LocalTime.MIN);
                dateTime2 = now.with(TemporalAdjusters.previousOrSame(firstDayOfWeek)).with(LocalTime.MIN);
                if (!dateTime1.equals(dateTime2)) {
                    needReset = true;
                }
                break;
            case "MONTH":
                dateTime1 = resetTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
                dateTime2 = now.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
                if (!dateTime1.equals(dateTime2)) {
                    needReset = true;
                }
                break;
        }
        return needReset;
    }

    public String nextSerialNumber(String rule, LocalDateTime localDateTime, Long seq, Integer seqLength, Map<String, Object> map) {
//        String rule = "IQC|[yyyy]|[MM]|[dd]|[SEQ]|{bizName}";
        String[] ruleArr = rule.split("\\|");
        List<String> snList = new LinkedList<>();
        Arrays.asList(ruleArr).forEach(item -> {
            if (item.startsWith("[") && item.endsWith("]")) {
                String str = item.substring(1, item.length() - 1);
                if ("SEQ".equals(str)) {
                    String seqStr = String.format("%0" + seqLength + "d", seq);
                    snList.add(seqStr);
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(str);
                    String formattedDateTime = formatter.format(localDateTime);
                    snList.add(formattedDateTime);
                }
            } else if (item.startsWith("{") && item.endsWith("}")) {
                String str = item.substring(1, item.length() - 1);
                JexlContext jc = new MapContext(map);

                Expression e = new JexlEngine().createExpression(str);
                Object result = e.evaluate(jc);
                snList.add(result.toString());
            } else {
                snList.add(item);
            }
        });
        String serialNumber = CollUtil.join(snList, "");
        return serialNumber;
    }

    public List<String> getSerialNumberList(String num, Map<String, Object> map) {
        if (!NumberUtil.isInteger(num)) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Long listLength = Long.parseLong(num);
        String codeRuleName = (String) map.get("codeRuleName");
        LambdaQueryWrapper<CodeRule> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(CodeRule::getName, codeRuleName);
        CodeRule codeRule = codeRuleMapper.selectOne(wrapper);
        if (codeRule == null) {
            ExceptionCast.cast(SequenceCode.SEQUENCE_CANNOT_DETERMINE_CODE_RULE);
        }
        String rule = codeRule.getRule();
        String resetType = codeRule.getResetType();
        String minNumber = codeRule.getMinNumber();
        String maxNumber = codeRule.getMaxNumber();
        Integer seqLength = minNumber.length();
        Long minValue = 1L;
        Long maxValue = 9999L;
        DayOfWeek firstDayOfWeek = DayOfWeek.MONDAY;
        try {
            minValue = Long.parseLong(minNumber);
            maxValue = Long.parseLong(maxNumber);
            firstDayOfWeek = DayOfWeek.valueOf(codeRule.getFirstDayOfWeek().toUpperCase());
        } catch (NumberFormatException e) {
            ExceptionCast.cast(SequenceCode.SEQUENCE_CANNOT_RESOLVE_MIN_OR_MAX);
        } catch (IllegalArgumentException e) {
            ExceptionCast.cast(SequenceCode.SEQUENCE_CANNOT_RESOLVE_FIRST_DAY_OF_WEEK);
        }
        LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));

        if (rule.contains("[SEQ]")) {
            String bizName = (String) map.get("bizName");
            if (bizName == null) {
                ExceptionCast.cast(SequenceCode.SEQUENCE_CANNOT_DETERMINE_BIZ_NAME);
            }
            QueryWrapper<Sequence> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", bizName);
            Sequence sequence = this.baseMapper.selectOne(queryWrapper);
            if (sequence == null) {
                sequence = new Sequence();
                sequence.setName(bizName);
                sequence.setValue(minValue + listLength);
                if (sequence.getValue() > maxValue) {
                    ExceptionCast.cast(SequenceCode.SEQUENCE_MAXIMUM_EXCEEDED);
                }
                sequence.setResetTime(now);
                this.baseMapper.insert(sequence);
                return nextSerialNumberList(rule, listLength, now, minValue, seqLength, map);
            }
            LocalDateTime resetTime = sequence.getResetTime();
            Boolean needReset = isNeedReset(resetType, now, resetTime, firstDayOfWeek);
            if (needReset) {
                sequence.setValue(minValue + listLength);
                sequence.setResetTime(now);
            } else {
                sequence.setValue(sequence.getValue() + listLength);
            }
            if (sequence.getValue() > maxValue) {
                ExceptionCast.cast(SequenceCode.SEQUENCE_MAXIMUM_EXCEEDED);
            }
            this.baseMapper.updateById(sequence);
            return nextSerialNumberList(rule, listLength, now, sequence.getValue() - listLength, seqLength, map);
        } else {
            return nextSerialNumberList(rule, listLength, now, null, null, map);
        }
    }

    public List<String> nextSerialNumberList(String rule, Long listLength, LocalDateTime localDateTime, Long seq, Integer seqLength, Map<String, Object> map) {
        //        String rule = "IQC|[yyyy]|[MM]|[dd]|[SEQ]|{bizName}";
        String[] ruleArr = rule.split("\\|");
        List<List<String>> snListList = new LinkedList<>();
        for (long i = 0; i < listLength; i++) {
            snListList.add(new LinkedList<>());
        }
        Arrays.asList(ruleArr).forEach(item -> {
            if (item.startsWith("[") && item.endsWith("]")) {
                String str = item.substring(1, item.length() - 1);
                if ("SEQ".equals(str)) {
                    Long i = 0L;
                    for (List<String> snList : snListList) {
                        String seqStr = String.format("%0" + seqLength + "d", seq + i++);
                        snList.add(seqStr);
                    }
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(str);
                    String formattedDateTime = formatter.format(localDateTime);
                    snListList.forEach(snList -> snList.add(formattedDateTime));
                }
            } else if (item.startsWith("{") && item.endsWith("}")) {
                String str = item.substring(1, item.length() - 1);
                JexlContext jc = new MapContext(map);
                Expression e = new JexlEngine().createExpression(str);
                Object result = e.evaluate(jc);
                snListList.forEach(snList -> snList.add(result.toString()));
            } else {
                snListList.forEach(snList -> snList.add(item));
            }
        });
        List<String> snStrList = snListList.stream()
                .map(snList -> CollUtil.join(snList, "")).collect(Collectors.toList());
        return snStrList;
    }
}
