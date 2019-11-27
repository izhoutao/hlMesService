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
public class Role implements Serializable {

    private static final long serialVersionUID = -3922073841666238922L;

    private String id;
    private String name;
    private String code;
    private String description;
    private String status;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //菜单路由信息
    @TableField(exist=false)
    private List<Menu> menuList;
}
