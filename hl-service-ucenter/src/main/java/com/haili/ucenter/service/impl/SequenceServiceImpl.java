package com.haili.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.system.CodeRule;
import com.haili.framework.domain.system.Sequence;
import com.haili.ucenter.config.AbstractSerialNumberGenerator;
import com.haili.ucenter.mapper.CodeRuleMapper;
import com.haili.ucenter.mapper.SequenceMapper;
import com.haili.ucenter.service.ISequenceService;
import org.apache.commons.jexl2.JexlContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;

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
        String bizName = (String) map.get("bizName");
        String id = (String) map.get("id");
        Integer length = (Integer) map.get("length");
        Long resetValue = Long.parseLong((String) map.get("resetValue"));
        AbstractSerialNumberGenerator serialNumberGenerator = getSerialNumberGenerator(bizName, map);
        CodeRule codeRule = codeRuleMapper.selectById(id);
        String rule = codeRule.getRule();
        String resetType = codeRule.getResetType();
        LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
        if (rule.contains("[SEQ]")) {
            QueryWrapper<Sequence> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", bizName);
            Sequence sequence = this.baseMapper.selectOne(queryWrapper);
            if (sequence == null) {
                sequence = new Sequence();
                sequence.setName(bizName);
                sequence.setValue(resetValue);
                sequence.setResetTime(now);
                this.baseMapper.insert(sequence);
                return serialNumberGenerator.nextSerialNumber(rule, length, now, resetValue);
            }
            LocalDateTime resetTime = sequence.getResetTime();
            Boolean needReset = isNeedReset(resetType, now, resetTime);
            if (needReset) {
                sequence.setValue(resetValue);
                sequence.setResetTime(now);
            } else {
                sequence.setValue(sequence.getValue() + 1);
            }
            this.baseMapper.updateById(sequence);
            return serialNumberGenerator.nextSerialNumber(rule, length, now, sequence.getValue());
        } else {
            return serialNumberGenerator.nextSerialNumber(rule, length, now);
        }
    }

    private Boolean isNeedReset(String resetType, LocalDateTime resetTime, LocalDateTime now) {
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
                dateTime1 = resetTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);
                dateTime2 = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);
                if (!dateTime1.equals(dateTime2)) {
                    needReset = true;
                }
                break;
            case "MONTH":
                dateTime1 = resetTime.toLocalDate().atStartOfDay();
                dateTime2 = now.toLocalDate().atStartOfDay();
                Period period = Period.between(dateTime1.toLocalDate(), dateTime2.toLocalDate());
                if (period.toTotalMonths() != 0) {
                    needReset = true;
                }
                break;
        }
        return needReset;
    }

    public AbstractSerialNumberGenerator getSerialNumberGenerator(String bizName, Map<String, Object> map) {
        System.out.println("===============根据业务名生成新的序列生成器===============");
        return new AbstractSerialNumberGenerator(bizName, this) {
            @Override
            public void buildJc(JexlContext jc) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    System.out.println("~~~~~~~~~~~~Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    jc.set(entry.getKey(), entry.getValue());
                }
            }
        };
    }
}
