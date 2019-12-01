package com.haili.auth.service;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

@Data
@ToString
public class UserJwt extends User {

    private String id;
    private String staffId;
    private String name;
    private String avatar;
    private String department;
    private List<String> roles;
    private List<String> menus;

    public UserJwt(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

}
