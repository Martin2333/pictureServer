package com.topsports.pictureServer.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by huang.cj on 2017/8/31.
 */

@Repository
public interface UrlMapper {

    /**
     * 通过sourceSystem获取url
     * @param sourceSystem
     * @return
     */
    String getUrlBySourceSystem(@Param("sourceSystem")String sourceSystem);
}
