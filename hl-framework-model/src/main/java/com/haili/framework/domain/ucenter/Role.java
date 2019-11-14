package com.haili.framework.domain.ucenter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2018/3/19.
 */
@Data
@ToString
@NoArgsConstructor
@TableName("tb_role")
public class Role implements Serializable {

    private static final long serialVersionUID = -3922073841666238922L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("role_name")
    private String roleName;
    @TableField("role_code")
    private String roleCode;
    private String description;
    private String status;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

//    //菜单路由信息
//    private List<Menu> menus;
}
