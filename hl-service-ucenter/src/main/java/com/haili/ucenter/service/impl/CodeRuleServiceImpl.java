package com.haili.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.system.CodeRule;
import com.haili.framework.domain.system.XSequenceSequence;
import com.haili.framework.utils.AbstractSerialNumberGenerator;
import com.haili.ucenter.config.SerialNumberGeneratorConfig;
import com.haili.ucenter.mapper.CodeRuleMapper;
import com.haili.ucenter.mapper.XSequenceSequenceMapper;
import com.haili.ucenter.service.ICodeRuleService;
import com.zaxxer.hikari.HikariDataSource;
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
 * @since 2019-12-07
 */
@Service
@Transactional
public class CodeRuleServiceImpl extends ServiceImpl<CodeRuleMapper, CodeRule> implements ICodeRuleService {
//    private static final Logger logger = LoggerFactory.getLogger(CodeRuleServiceImpl.class);
//    //静态变量存储最大值
//    private static final AtomicLong atomicNum = new AtomicLong();
//    //初始化分组编号
//    private final long INIT_GROUP_NUM = 0;
//
//    /**
//     * @throws Exception void
//     * @Author javaloveiphone
//     * @Description :初始化设置分组编号最大值
//     */
//    @PostConstruct
//    public void initMaxNum() {
//        try {
//
//
//            QueryWrapper<CodeRule> queryWrapper = new QueryWrapper<>();
//            queryWrapper.select("id", "min_number", "max_number").eq("id", 20);
//            CodeRule codeRule = this.baseMapper.selectOne(queryWrapper);
//            String maxNumber = codeRule.getMaxNumber();
//            long maxGroupNum = NumberUtil.parseLong(maxNumber);
//            if (maxGroupNum < INIT_GROUP_NUM) {
//                maxGroupNum = INIT_GROUP_NUM;
//            }
//            if (logger.isDebugEnabled()) {
//                logger.debug("初始化分组编号最大值为：" + maxGroupNum);
//            }
//            atomicNum.set(maxGroupNum);
//        } catch (Exception e) {
//            logger.error("初始化获取分组编号最大值异常", e);
//        }
//    }
//
//    /**
//     * @return int
//     * 注：此方法并没有使用synchronized进行同步，因为共享的编号自增操作是原子操作，线程安全的
//     * @Author javaloveiphone
//     * @Description :获取最新分组编号
//     */
//    public String getNewAutoNum() {
//        //线程安全的原子操作，所以此方法无需同步
//        Long newNum = atomicNum.incrementAndGet();
//        //数字长度为5位，长度不够数字前面补0
//        String newStrNum = String.format("%05d", newNum);
//        return newStrNum;
//    }

    @Autowired
    HikariDataSource dataSource;
    @Autowired
    SerialNumberGeneratorConfig serialNumberGeneratorConfig;
    @Autowired
    XSequenceSequenceMapper xSequenceSequenceMapper;

    public String nextSerialNumber(Map<String, Object> map) {
        String bizName = (String) map.get("bizName");
        String id = (String) map.get("id");
        Integer length = (Integer) map.get("length");
        Long resetValue = Long.parseLong((String) map.get("resetValue"));
        AbstractSerialNumberGenerator serialNumberGenerator = serialNumberGeneratorConfig.getSerialNumberGenerator(bizName, map);
        CodeRule codeRule = this.baseMapper.selectById(id);
        String rule = codeRule.getRule();
        String resetType = codeRule.getResetType();
        if (rule.contains("[SEQ]")) {
            QueryWrapper<XSequenceSequence> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", bizName);
            XSequenceSequence xSequenceSequence = xSequenceSequenceMapper.selectOne(queryWrapper);
            LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
            if (xSequenceSequence == null) {
                return serialNumberGenerator.nextSerialNumber(rule, length, now);
            }
            LocalDateTime gmtCreate = xSequenceSequence.getGmtCreate();
            Boolean needReset = isNeedReset(resetType, now, gmtCreate);
            if (needReset) {
                xSequenceSequence.setGmtCreate(now);
                xSequenceSequence.setGmtModified(now);
                xSequenceSequence.setValue(resetValue);
                xSequenceSequenceMapper.updateById(xSequenceSequence);
                return serialNumberGenerator.nextSerialNumber(rule, length, now, resetValue);
            }
            return serialNumberGenerator.nextSerialNumber(rule, length, now);
        } else {
            return serialNumberGenerator.nextSerialNumber(rule, length, null);
        }
    }

    private Boolean isNeedReset(String resetType, LocalDateTime now, LocalDateTime gmtCreate) {
        Boolean needReset = false;
        LocalDateTime dateTime1, dateTime2;
        switch (resetType) {
            case "DAILY":
                dateTime1 = gmtCreate.toLocalDate().atStartOfDay();
                dateTime2 = now.toLocalDate().atStartOfDay();
                if (!dateTime1.equals(dateTime2)) {
                    needReset = true;
                }
                break;
            case "WEEK":
                dateTime1 = gmtCreate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);
                dateTime2 = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).with(LocalTime.MIN);
                if (!dateTime1.equals(dateTime2)) {
                    needReset = true;
                }
                break;
            case "MONTH":
                dateTime1 = gmtCreate.toLocalDate().atStartOfDay();
                dateTime2 = now.toLocalDate().atStartOfDay();
                Period period = Period.between(dateTime1.toLocalDate(), dateTime2.toLocalDate());
                if (period.toTotalMonths() != 0) {
                    needReset = true;
                }
                break;
        }
        return needReset;
    }

}
