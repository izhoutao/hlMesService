package com.haili.framework.domain.ucenter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
@TableName("tb_company_user")
public class CompanyUser implements Serializable {
    private static final long serialVersionUID = -916357110051689786L;
    @TableId(value = "id", type = IdType.UUID )
    private String id;
    @TableField("company_id")
    private String companyId;
    @TableField("user_id")
    private String userId;
}
