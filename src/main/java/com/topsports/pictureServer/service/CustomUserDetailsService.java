package com.topsports.pictureServer.service;

import com.topsports.pictureServer.dao.UserInfoMapper;
import com.topsports.pictureServer.model.UserExt;
import com.topsports.pictureServer.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by huang.cj on 2017/11/22.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    public UserInfoMapper userInfoMapper;

    public UserDetails loadUserByUsername(String userCode)
            throws UsernameNotFoundException {
        UserDetails user = null;
        if(userCode.equals("admin")){
            user = new UserExt("admin", "admin", true, true, true, true,getAuthorities(),"管理员");
            return user;
        }

        Map<String,Object> params=new HashMap<String,Object>();
        params.put("userCode", userCode);
        UserInfo userInfo= userInfoMapper.selectByParams(params);
        user = new UserExt(userInfo.getUserCode(), userInfo.getPassword(), true, true, true, true,getAuthorities(),userInfo.getUserName());
        return user;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        // 根据自定义逻辑来返回用户权限，如果用户权限返回空或者和拦截路径对应权限不同，验证不通过
            List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
            GrantedAuthority au = new SimpleGrantedAuthority("ROLE_USER");
            list.add(au);
            return list;
    }
}
