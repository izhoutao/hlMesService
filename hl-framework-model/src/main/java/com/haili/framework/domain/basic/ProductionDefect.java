package com.haili.framework.domain.basic;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author Zhou Tao
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("v_production_defect")
public class ProductionDefect implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String ipqcId;

    /**
     * 缺陷码
     */
    private String defectCode;

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

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String createPerson;

    private String updatePerson;

    /**
     * 检验日期
     */
    private LocalDate inspectDate;

    /**
     * 报工日期
     */
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
    private Integer annealTv;

    /**
     * 退火硬度
     */
    private Integer annealHardness;

    /**
     * 轧/平道次
     */
    private String rollingPass;

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
     * 复判员
     */
    private String checker;

    /**
     * 品检员姓名
     */
    private String inspectorName;

    /**
     * 复判员姓名
     */
    private String checkerName;

    /**
     * 测量值
     */
    private String measurement;

    /**
     * 品检员结论
     */
    private String inspectorResult;

    /**
     * 复判员结论
     */
    private String checkerResult;

    /**
     * 状态：0，未提交；1：品检员已提交；2：复判员已提交。
     */
    private Integer status;

    /**
     * 缺陷信息
     */
    private String defectList;

    /**
     * 生产要求
     */
    private String requirements;

    /**
     * 目标宽度
     */
    private String targetWidth;
    /**
     * 宽度容差
     */
    private String toleranceWidth;
    /**
     * 目标厚度
     */
    private String targetThickness;
    /**
     * 厚度容差
     */
    private String toleranceThickness;

    /**
     * 测量统计数据
     */
    private String statistics;


}
