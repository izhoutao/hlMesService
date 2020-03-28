package com.haili.framework.domain.basic;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * 
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_journaling_rewind_item")
public class JournalingRewindItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String productNumber;

    private String steelGrade;

    /**
     * 热轧产地
     */
    private String hotRollOrigin;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    private Integer inputThickness;

    private Integer inputWeight;

    private Integer processVelocity;

    private Integer welderCurrent;

    private Integer welderVelocity;

    private LocalDateTime beginTime;

    private LocalDateTime endTime;

    private Integer outputLength;

    private Integer outputWeight;

    private String lossReason;

    private String shiftId;

    private LocalDate date;

}
