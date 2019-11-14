package com.haili.framework.domain.ucenter.ext;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UserSearchParam implements Serializable {
    String username;
    String name;
    String phone;
}
