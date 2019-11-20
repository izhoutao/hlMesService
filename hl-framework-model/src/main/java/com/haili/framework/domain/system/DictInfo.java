package com.haili.framework.domain.system;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by admin on 2018/2/6.
 */
@Data
@ToString
public class DictInfo implements Serializable {

    private static final long serialVersionUID = -1834887303545632132L;
    private String id;

    private String code;

    private String name;

    private Integer sequenceNumber;

    private String typeId;
}
