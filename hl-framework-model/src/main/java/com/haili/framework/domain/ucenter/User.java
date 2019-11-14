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
@TableName("tb_user")
public class User implements Serializable {

    private static final long serialVersionUID = -8644930299662562058L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("staff_id")
    private String staffId;
    private String username;
    private String password;
    private String name;
    private String sex;
    private String avatar;
    private Date birthday;
    private Date hiredate;
    private String phone;
    private String email;
    private String state;
    private String description;
    private String department;
    private String position;

    private String line;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;

}