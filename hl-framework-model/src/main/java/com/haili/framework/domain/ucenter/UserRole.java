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
@TableName("tb_user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 663583570906646676L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @TableField("user_id")
    private String userId;
    @TableField("role_id")
    private String roleId;
    private String creator;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
