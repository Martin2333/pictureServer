package com.topsports.pictureServer.dao;

import com.topsports.pictureServer.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by huang.cj on 2017/8/31.
 */

@Repository
public interface UserInfoMapper {

    /**
     * 通过参数获取用户信息
     * @param params
     * @return
     */
    UserInfo selectByParams(@Param("params") Map<String, Object> params);
}
