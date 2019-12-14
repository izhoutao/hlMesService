package com.haili.ucenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haili.framework.domain.system.CodeRule;
import com.haili.ucenter.mapper.CodeRuleMapper;
import com.haili.ucenter.service.ICodeRuleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-07
 */
@Service
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

}
