package com.haili.framework.domain.ucenter.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created by admin on 2018/3/20.
 */
@Data
@ToString
@NoArgsConstructor
public class MenuDTO {
    String code;
    List<String> roles;
}
