package com.haili.framework.domain.system;

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
 * @since 2019-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Sequence implements Serializable {

    private static final long serialVersionUID = -6287959171101969570L;

    private String id;

    private Long value;

    private String name;

    private LocalDateTime resetTime;

}
