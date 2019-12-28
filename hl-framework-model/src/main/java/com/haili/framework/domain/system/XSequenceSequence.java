package com.haili.framework.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("x_sequence_sequence")
public class XSequenceSequence implements Serializable {

    private static final long serialVersionUID = 8499706582542647298L;
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    private Long value;

    private String name;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
