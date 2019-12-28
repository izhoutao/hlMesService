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
 * @since 2019-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_journaling_rewind")
public class JournalingRewind implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String reportDate;

    private Double totalLossWeight;

    private Double totalInputWeight;

    private String shiftId;

    private Integer producedCoilNumber;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;

    private Double totalOutputWeight;

    private Double outputRate;

    private Double capacityUtilization;

    private Integer expectedAttendance;

    private Integer actualAttendance;

    private String shiftLeader;

    private String supervisor;

    private String mattersRecord;

    private String shiftHandover;


}
