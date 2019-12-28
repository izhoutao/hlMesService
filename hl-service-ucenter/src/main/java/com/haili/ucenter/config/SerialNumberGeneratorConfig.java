package com.haili.ucenter.config;

import com.haili.framework.utils.AbstractSerialNumberGenerator;
import com.xuanner.seq.DbSeqBuilder;
import com.xuanner.seq.sequence.Sequence;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.jexl2.JexlContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class SerialNumberGeneratorConfig {
    @Autowired
    HikariDataSource dataSource;

    //    @Bean(name = "orderNumberGenerator")
    //    public AbstractSerialNumberGenerator serialNumberGenerator() {
    //        Sequence sequence = DbSeqBuilder.create().dataSource(dataSource).step(1).stepStart(0).bizName("orderNumber").build();
    //        return new AbstractSerialNumberGenerator(sequence) {
    //            @Override
    //            public void buildJc(JexlContext jc) {
    //            }
    //        };
    //    }

    @Bean(name = "sequenceMap")
    public ConcurrentHashMap<String, Sequence> sequenceMap() {
        return new ConcurrentHashMap<>();
    }

    public Sequence getSequence(String bizName) {
        Sequence sequence = sequenceMap().get(bizName);
        if (sequence == null) {
            System.out.println("===============map里的sequence为空，此处创建之=============");
            sequence = DbSeqBuilder
                    .create()
                    .dataSource(dataSource)
                    .step(1)
                    .stepStart(0)
                    .bizName(bizName)
                    .build();
            sequenceMap().put(bizName, sequence);
        }
        return sequence;
    }

    public AbstractSerialNumberGenerator getSerialNumberGenerator(String bizName, Map<String, Object> map) {
        System.out.println("===============根据业务名生成新的序列生成器===============");
        Sequence sequence = getSequence(bizName);
        return new AbstractSerialNumberGenerator(sequence) {
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