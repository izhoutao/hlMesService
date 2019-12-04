package com.haili.framework.domain.basic.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhou Tao
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PrinterDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 打印机名称
     */
    private String name;

    /**
     * 打印机路径
     */
    private String path;

    /**
     * 是否为默认打印机
     */
    private Boolean isDefault;



}
