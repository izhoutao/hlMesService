package com.haili.ucenter.config;

import cn.hutool.core.collection.CollUtil;
import com.haili.ucenter.service.impl.SequenceServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 产生流水号工具类
 *
 * @version V1.0
 * @date: 2019-12-26 下午5:21:37
 */
@Data
@AllArgsConstructor
public abstract class AbstractSerialNumberGenerator {
    String bizName;
    SequenceServiceImpl sequenceService;

    public String nextSerialNumber(String rule, int length, LocalDateTime localDateTime) {
        return nextSerialNumber(rule, length, localDateTime, null);
    }

    public String nextSerialNumber(String rule, int length, LocalDateTime localDateTime, Long seq) {
//        String rule = "IQC|[yyyy]|[MM]|[dd]|[SEQ]";
        String[] ruleArr = rule.split("\\|");
        List<String> snList = new LinkedList<>();
        Arrays.asList(ruleArr).forEach(item -> {
            if (item.startsWith("[") && item.endsWith("]")) {
                String str = item.substring(1, item.length() - 1);
                if ("SEQ".equals(str)) {
                    String seqStr = String.format("%0" + length + "d", seq);
                    snList.add(seqStr);
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(str);
                    String formattedDateTime = formatter.format(localDateTime);
                    snList.add(formattedDateTime);
                }
            } else if (item.startsWith("{") && item.endsWith("}")) {
                String str = item.substring(1, item.length() - 1);
                JexlContext jc = new MapContext();
                this.buildJc(jc);
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

    public abstract void buildJc(JexlContext jc);

}