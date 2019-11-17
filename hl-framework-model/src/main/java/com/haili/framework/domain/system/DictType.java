package com.haili.framework.domain.system;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by admin on 2018/2/6.
 */
@Data
@ToString
@TableName("tb_dict_type")
public class DictType implements Serializable {

    private static final long serialVersionUID = 1421748832103435259L;

    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    @TableField("state")
    private String state;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
