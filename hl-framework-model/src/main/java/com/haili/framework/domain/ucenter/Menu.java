package com.haili.framework.domain.ucenter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2018/3/19.
 */
@Data
@ToString
@TableName("tb_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = -780689995317302247L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("p_id")
    private String pId;
    @TableField("menu_code")
    private String menuCode;
    @TableField("menu_name")
    private String menuName;
    private String path;
    @TableField("is_menu")
    private String isMenu;
    private Integer level;
    private Integer sort;
    private String status;
    private String icon;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

//    //角色信息
//    private List<Role> roles;
}
