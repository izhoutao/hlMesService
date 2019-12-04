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
@TableName("tb_inbound_order_detail")
public class InboundOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

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

    private String materialId;
    @TableField(exist=false)
    private String materialCode;
    @TableField(exist=false)
    private String materialName;

    private String inboundOrderId;

    private String checkResult;

    private Integer quantity;

    private Integer receivedQuantity;

    @TableField(fill = FieldFill.INSERT)
    private String createPerson;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatePerson;
}
