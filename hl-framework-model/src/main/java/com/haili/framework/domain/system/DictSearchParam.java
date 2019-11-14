package com.haili.framework.domain.system;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DictSearchParam {
    private String code;
    private String name;
    private String typeId;
}
