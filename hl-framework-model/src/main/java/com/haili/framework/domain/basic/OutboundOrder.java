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
 * @since 2019-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_outbound_order")
public class OutboundOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String number;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private String levelId;

    private String statusId;

    private String typeId;

    private String customerId;

    private String warehouseId;

    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;
}
