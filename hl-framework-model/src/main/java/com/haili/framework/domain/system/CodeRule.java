package com.haili.framework.domain.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_code_rule")
public class CodeRule implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 编码名称
     */
    private String name;

    /**
     * 编码规则
     */
    private String rule;

    /**
     * 描述
     */
    private String description;

    /**
     * 重置类型
     */
    private String resetType;

    /**
     * 起始码
     */
    private String minNumber;

    /**
     * 截止码
     */
    private String maxNumber;

    /**
     * 星期首日
     */
    private String firstDayOfWeek;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
