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
public class Permission implements Serializable {

    private static final long serialVersionUID = 8237616249163998537L;
    private String id;
    private String roleId;
    private String menuId;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
