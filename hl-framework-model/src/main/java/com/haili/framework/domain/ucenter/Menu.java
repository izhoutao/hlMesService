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
public class Menu implements Serializable {

    private static final long serialVersionUID = -780689995317302247L;
    private String id;
    private String pId;
    private String menuCode;
    private String menuName;
    private String path;
    private String isMenu;
    private Integer level;
    private Integer sort;
    private String status;
    private String icon;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

//    //角色信息
//    private List<Role> roles;
}
