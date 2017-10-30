package com.topsports.pictureServer.service;

import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.topsports.pictureServer.dao.PictureMapper;
import com.topsports.pictureServer.dao.UrlMapper;
import com.topsports.pictureServer.model.Picture;
import com.topsports.pictureServer.util.GetUuid;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.aliyun.oss.OSSClient;

import static com.topsports.pictureServer.service.StsService.getCredentials;
import static sun.rmi.transport.TransportConstants.Magic;

/**
 * Created by huang.cj on 2017/8/4.
 */
@Service
public class PictureService {
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private UrlMapper urlMapper;
    /**
     * 根据ID获取数据
     */
    public Picture selectByParams(Map<String,Object> params) {
        Picture picture = pictureMapper.selectByParams(params);
        return picture;
    }

    /**
     *
     */
    public String getUrlBySourceSystem(String sourceSystem) {
        String url = urlMapper.getUrlBySourceSystem(sourceSystem);

        return url;
    }

    /**
     * 根据参数删除图片（逻辑删除）
     */
    public int deleteByParams(Map<String,Object> params) {
        int i = pictureMapper.deleteByParams(params);
        return i;
    }

    /**
     * 获取服务器数据库中尚未验证的图片信息
     * @return
     */
    public List<Picture> getUploadingPictures() {
        List<Picture> list= pictureMapper.getUploadingPictures();
        return list;
    }

    /**
     * 获取所有品牌
     * @return
     */
    public List<String> getAllBrandCode() {
        List<String> list = pictureMapper.getAllBrandCode();
        return list;
    }


    /**
     * 上传图片，存入本地路径，获取货号，对图片重新命名
     */
    /*
    @Transactional
    public String uploadDataProImgs(@RequestParam MultipartFile imgs_upload,Map<String,Object> params) {

        String fileNm = imgs_upload.getOriginalFilename();
        String suffix = fileNm.substring(fileNm.lastIndexOf("."));
        String format = suffix.substring(1,suffix.length());
        String brandCode = params.get("brandCode").toString();
        String categoryName = params.get("categoryName").toString();
        String pictureCode = params.get("pictureCode").toString();

        if(!suffix.equals(".jpg")&&!suffix.equals(".png")&&!suffix.equals(".jpeg")){
            return "请选择jpg或png图片";
        }
        String pre = fileNm.substring(0, fileNm.lastIndexOf(".")).trim();
        if(pre.length()==0){
            return "请选择命名格式正确的图片(货号_n.jpg/png或货号.jpg/png)";
        }

        String pictureId = *//*productCode.concat("-").concat(lastSerialNumber);*//*createPictureId(categoryName);
        //生成图片新名称
        String fileTrueName = pictureId.concat(suffix);
        //生成图片应该存放路径
        String picturePath = getPicturePath(params);
        //上传图片到百度云OSS
        saveImg(imgs_upload,fileTrueName,picturePath);
        //在服务器表中插入对应图片信息
        Picture newPicture = new Picture(pictureId,pictureCode,categoryName,brandCode,picturePath+fileTrueName,url,format);
        pictureMapper.uploadDataProImgs(newPicture);
        //记录最新流水号到表last_picture_id中
        //pictureMapper.updateLastPictureId(newPictureId*//*,imgs_upload.size*//*);
        return url+"/"+picturePath+fileTrueName;
    }*/
    /**
     * 上传图片：获取临时身份访问阿里OSS，生成图片存放路径，生成图片新名称。
     */
    @Transactional
    public Map<String,Object> uploadDataProImgs(Map<String,Object> params) {
        Map<String,Object> results = new HashedMap();
        String brandCode = params.get("brandCode").toString();
        String categoryName = params.get("categoryName").toString();
        String pictureCode = params.get("pictureCode").toString();
        String format = params.get("format").toString();
        String sourceSystem = params.get("sourceSystem").toString();

        String picturePath = generatePicturePath(params);
        //String newPictureName = generatePictureName(categoryName);
        String newPictureName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+GetUuid.getId();
        String fileTrueName = newPictureName+"."+format;
        String url = urlMapper.getUrlBySourceSystem(sourceSystem);

        //将图片信息存入服务器数据库
        Picture newPicture = new Picture(newPictureName,pictureCode,categoryName,brandCode,picturePath+fileTrueName,format);
        newPicture.setSourceSystem(sourceSystem);
        pictureMapper.uploadDataProImgs(newPicture);


        results.put("url",url);
        results.put("pictureName",newPictureName);
        results.put("picturePath",picturePath);

        return results;
    }

    public Map<String,Object> getCredential() {
        Map<String,Object> results = new HashedMap();
        //获取OSS临时访问凭证
        AssumeRoleResponse.Credentials credentials = getCredentials();

        results.put("credentials",credentials);
        return results;
    }

    /**
     * 生成图片路径
     * @param params
     * @return
     */
    private String generatePicturePath(Map<String,Object> params) {

        String brandCode = params.get("brandCode").toString();
        String categoryName = params.get("categoryName").toString();

        String picturePath = "/"+categoryName+"/"+brandCode+"/";
        return picturePath;
    }

    /**
     * 生成图片流水号
     * @param categoryName
     * @return
     */
    public String generatePictureName(String categoryName) {
        int serialNumberLength = 10;
        int serialNumber = pictureMapper.getLastId()+1;
        String productId = categoryName.substring(0,1).toUpperCase();
        String lastSerialNumber = serialNumber+"";
        while(serialNumberLength-lastSerialNumber.length()>0) {
            lastSerialNumber = "0".concat(lastSerialNumber);
        }
        productId = productId.concat("-").concat(lastSerialNumber);
        return productId;
    }

    /**
     * 更新图片状态
     * @param picturePath
     */
    public void updatePicturesEnable(String picturePath) {
        pictureMapper.updatePicturesEnable(picturePath);
    }

    /**
     * 根据品牌获取对应品牌最近更新状态图片信息
     * @param brandCode
     * @return
     */
    public String getLastPictureByBrand(String brandCode) {
        String picturePath = pictureMapper.getLastPictureByBrand(brandCode);
        return picturePath;
    }

    /**
     * 更新品牌对应最新更新状态图片信息
     * @param brandCode
     * @param lastBrandPicturePath
     */
    public void updateLastPictureIdByBrand(String brandCode,String lastBrandPicturePath) {
        pictureMapper.updateLastPictureByBrand(brandCode,lastBrandPicturePath);
    }

    /**
     * 更新服务器图片信息中更新日期在系统当前日期之前的数据，将该图片状态置为上传失败
     */
    public void updateUnablePictureStatus() {
        pictureMapper.updateUnablePictureStatus();
    }

}
