package com.haili.framework.domain.ucenter;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by admin on 2018/3/19.
 */
@Data
@ToString
@NoArgsConstructor
public class Menu implements Serializable {

    private static final long serialVersionUID = -780689995317302247L;

    private String id;

    /**
     * 名称
     */
    private String name;

    private String code;

    /**
     * 父菜单ID
     */
    private String pid;

    /**
     * 类型：0：目录，1：菜单，:2：按钮
     */
    private String type;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    private Boolean hidden;

    private Boolean alwaysShow;

    //角色信息
    @TableField(exist=false)
    private List<Role> roleList;
}
