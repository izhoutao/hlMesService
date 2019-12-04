package com.haili.framework.domain.basic;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 供应商信息表
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    private String id;

    /**
     * 客户编码
     */
    private String code;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 客户联系人
     */
    private String linkMan;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 客户地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 传真
     */
    private String fax;

    /**
     * 状态：0禁止，1启用
     */
    private Integer state;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 省州
     */
    private String province;

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

    /**
     * 删除标志：0未删除，1已删除
     */
    @TableLogic
    private Integer deleted;


}
