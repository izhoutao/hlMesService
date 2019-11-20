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
@TableName("tb_vendor")
public class Vendor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商ID
     */
    private String id;

    /**
     * 供应商编码
     */
    private String code;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 供应商联系人
     */
    private String linkMan;

    /**
     * 联系电话
     */
    private String phoneNumber;

    /**
     * 供应商地址
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
    private String state;

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
