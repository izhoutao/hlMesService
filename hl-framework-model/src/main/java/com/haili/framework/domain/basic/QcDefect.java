package com.haili.framework.domain.basic;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_qc_defect")
public class QcDefect implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String ipqcId;

    private String defectId;

    /**
     * 上面
     */
    private String up;

    /**
     * 下面
     */
    private String down;

    /**
     * 宽度位置
     */
    @TableField("widthPosition")
    private String widthPosition;

    /**
     * 起始位置
     */
    @TableField("startPosition")
    private String startPosition;

    /**
     * 结束位置
     */
    @TableField("endPosition")
    private String endPosition;

    /**
     * 缺陷长度
     */
    @TableField("defectLength")
    private String defectLength;

    /**
     * 程度
     */
    private String degree;

    /**
     * 类别|波高
     */
    @TableField("waveHeightCategory")
    private String waveHeightCategory;

    /**
     * 周期|mm
     */
    private String period;

    /**
     * 频率
     */
    private String frequency;

    /**
     * 直径|mm
     */
    private String diameter;

    /**
     * 距边|mm
     */
    private String margin;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;


}
