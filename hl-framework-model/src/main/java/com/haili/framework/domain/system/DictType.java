package com.haili.framework.domain.system;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by admin on 2018/2/6.
 */
@Data
@ToString
public class DictType implements Serializable {

    private static final long serialVersionUID = 1421748832103435259L;
    private String id;
    private String code;
    private String name;
    private String state;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
