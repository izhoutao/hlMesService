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
public class Workflow implements Serializable {

    private static final long serialVersionUID = 7135554402367092463L;
    private String id;

//    @TableField("code")
//    private String code;

    private String name;

    private String jsonText;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
