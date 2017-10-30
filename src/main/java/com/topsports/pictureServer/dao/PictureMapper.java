package com.topsports.pictureServer.dao;

import com.topsports.pictureServer.model.Picture;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by huang.cj on 2017/8/4.
 */
@Repository
public interface PictureMapper {
    /**
     * 通过参数获取图片
     * @param params
     * @return
     */
    Picture selectByParams(@Param("params") Map<String, Object> params);

    /**
     * 获取所有品牌
     * @return
     */
    List<String> getAllBrandCode();

    /**
     * 通过参数删除图片（逻辑删除）
     * @param params
     */
    int deleteByParams(@Param("params") Map<String, Object> params);

    /**
     * 获取状态为上传中的图片信息
     * @return
     */
    List<Picture> getUploadingPictures();

    /**
     * 更新图片状态
     * @param enable
     * @param pictureId
     */
    void updatePictureEnable(@Param("enable")int enable,@Param("pictureId")String pictureId);

    /**
     * 获取最新流水id
     */
    Integer getLastId();

    /**
     * 向服务器记录图片信息
     */
    void uploadDataProImgs(Picture picture);

    /**
     * 更新最新图片流水id
     */
    //void updateLastPictureId(@Param("new_id")String new_id);

    void updatePicturesEnable(@Param("picturePath")String picturePath);

    /**
     * 获取最近更新状态的图片的图片路径
     * @param brandCode
     * @return
     */
    String getLastPictureByBrand(@Param("brandCode")String brandCode);

    /**
     * 根据品牌更新对应品牌最近更新状态的图片的图片路径
     * @param brandCode
     * @param lastBrandPicturePath
     */
    void updateLastPictureByBrand(@Param("brandCode")String brandCode,@Param("lastBrandPicturePath")String lastBrandPicturePath);

    /**
     * 更新服务器图片信息中更新日期在系统当前日期之前的数据，将该图片状态置为上传失败
     */
    void updateUnablePictureStatus();

    int deleteById(String pictureId);
}
