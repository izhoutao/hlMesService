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
 * @since 2019-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_line_station_printer")
public class LineStationPrinter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String lineStationId;

    private String printerId;

    private Boolean isDefault;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


//    /**
//     * 打印机名称
//     */
//    @TableField(exist = false)
//    private String printerName;
//
//    /**
//     * 打印机路径
//     */
//    @TableField(exist = false)
//    private String printerPath;

}
