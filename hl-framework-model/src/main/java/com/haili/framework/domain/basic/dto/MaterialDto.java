package com.haili.framework.domain.basic.dto;

import com.haili.framework.domain.basic.Material;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class MaterialDto extends Material {
    String typeName;
}
