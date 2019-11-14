package com.haili.framework.domain.ucenter.ext;

import com.haili.framework.domain.ucenter.Menu;
import com.haili.framework.domain.ucenter.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@ToString
@NoArgsConstructor
public class RoleExt extends Role {
    List<Menu> menuList;
}
