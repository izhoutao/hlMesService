package com.haili.framework.domain.basic;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("tb_ipqc")
public class Ipqc implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 检验日期
     */
    private LocalDate inspectDate;

    /**
     * 报工日期
     */
    @NotNull(message = "报工日期不能为空")
//    @Past(message = "报工日期必须是一个过去的日期")
    private LocalDate date;

    /**
     * 热轧产地
     */
    private String hotRollOrigin;

    /**
     * 制程
     */
    private String operation;

    /**
     * 下制程
     */
    private String nextOperation;

    /**
     * 班别
     */
    private String shiftId;

    /**
     * 钢种
     */
    private String steelGrade;

    /**
     * 表面品级
     */
    private String surfaceFinish;

    /**
     * 用途
     */
    private String uses;

    /**
     * 客户
     */
    private String customerId;

    /**
     * 钢卷编号
     */
    @NotBlank(message = "钢卷编号不能为空")
    private String productNumber;

    /**
     * 原料编号
     */
    private String materialNumber;

    /**
     * 等级
     */
    private String grade;

    /**
     * 有害缺陷率（%）
     */
    private Float harmfulDefectPercent;

    /**
     * 等级评分
     */
    private String gradeScore;

    /**
     * 退火TV
     */
    private Float annealTv;

    /**
     * 退火硬度
     */
    private Float annealHardness;

    /**
     * 轧/平道次
     */
    private String rollingPass;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    /**
     * 建议使用表面
     */
    private String recommendedSurface;

    /**
     * 开卷方式
     */
    private String unwindMethod;

    /**
     * 交接事项
     */
    private String handoverMatters;

    /**
     * 备注
     */
    private String note;

    /**
     * 品检员
     */
    private String inspector;

    /**
     * 品检员姓名
     */
    private String inspectorName;

    /**
     * 品检员结论
     */
    private String inspectorResult;

    /**
     * 复判员
     */
    private String checker;

    /**
     * 复判员姓名
     */
    private String checkerName;

    /**
     * 复判员结论
     */
    private String checkerResult;

    /**
     * 测量值
     */
    private String measurement;

    /**
     * 状态：0，未提交；1：品检员已提交；2：复判员已提交。
     */
    private Integer status;
}
