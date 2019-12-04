package com.haili.framework.domain.basic;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2019-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_printer")
public class Printer implements Serializable {

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
     * 字符编码
     */
    private String characterCode;

    /**
     * 访问用户名
     */
    private String username;

    /**
     * 访问密码
     */
    private String password;

    /**
     * 打印文件路径
     */
    private String filePath;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
