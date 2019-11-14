package com.haili.framework.domain.basic;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by admin on 2018/2/6.
 */
@Data
@ToString
@TableName("tb_operation")
public class Operation implements Serializable {


    private static final long serialVersionUID = 1470364719759463798L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    @TableField("description")
    private String description;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

}
