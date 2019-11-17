package com.haili.framework.domain.basic;

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
@TableName("tb_workflow")
public class Workflow implements Serializable {

    private static final long serialVersionUID = 7135554402367092463L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

//    @TableField("code")
//    private String code;

    @TableField("name")
    private String name;

    @TableField("json_text")
    private String jsonText;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
