package com.haili.framework.domain.ucenter;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by admin on 2018/3/19.
 */
@Data
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -8644930299662562058L;

    private String id;
    private String staffId;
    private String username;
    private String password;
    private String name;
    private String sex;
    private String avatar;
    private LocalDate birthday;
    private LocalDate hiredate;
    private String phone;
    private String email;
    private String state;
    private String description;
    private String department;
    private String position;
    private String line;
    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}