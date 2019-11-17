package com.haili.framework.domain.ucenter;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

//    //角色信息
//    private List<Role> roles;
}
