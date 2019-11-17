package com.haili.framework.domain.basic;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_defect")
public class Defect implements Serializable {

    private static final long serialVersionUID = -2578229855036888913L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String code;

    private String name;

    private String description;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("group_id")
    private String groupId;


}
