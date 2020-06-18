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
public class UserRole implements Serializable {
    private static final long serialVersionUID = 663583570906646676L;
    private String id;
    private String userId;
    private String roleId;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
