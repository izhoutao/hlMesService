package com.haili.framework.domain.ucenter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
@TableName("tb_company")
public class Company implements Serializable {
    private static final long serialVersionUID = -916357110051689786L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    private String name;
    private String logo;
    private String intro;
    private String email;
    private String mobile;
    private String linkname;
    private String identitypic;
    private String worktype;
    private String businesspic;
    private String status;


}
