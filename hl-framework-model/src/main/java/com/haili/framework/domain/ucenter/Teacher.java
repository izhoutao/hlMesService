package com.haili.framework.domain.ucenter;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by admin on 2018/2/10.
 */
@Data
@ToString
public class Teacher implements Serializable {
    private static final long serialVersionUID = -916357110051689786L;
    private String id;
    private String name;
    private String pic;
    private String intro;
    private String resume;
    private String userId;

}
