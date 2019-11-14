package com.haili.framework.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by admin on 2018/2/6.
 */
@Data
@ToString
@TableName("tb_dict_info")
public class DictInfo implements Serializable {

    private static final long serialVersionUID = -1834887303545632132L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    @TableField("sequence_number")
    private Integer sequenceNumber;

    @TableField("type_id")
    private String typeId;

}
