package com.topsports.pictureServer.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by huang.cj on 2017/11/22.
 */
public class UserExt extends User {
    /**
     * 中文名
     */
    private String userCnName;

    public UserExt(String username, String password, boolean enabled,
                   boolean accountNonExpired, boolean credentialsNonExpired,
                   boolean accountNonLocked,
                   Collection<? extends GrantedAuthority> authorities,
                   String userCnName) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        this.userCnName=userCnName;
    }
}
