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
@TableName("tb_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 8237616249163998537L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("role_id")
    private String roleId;
    @TableField("menu_id")
    private String menuId;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
