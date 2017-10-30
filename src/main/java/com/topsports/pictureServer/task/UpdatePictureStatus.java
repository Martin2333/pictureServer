package com.topsports.pictureServer.task;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.topsports.pictureServer.model.Picture;
import com.topsports.pictureServer.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by huang.cj on 2017/8/16.
 */
@Configuration
@PropertySource("classpath:config.properties")
public class UpdatePictureStatus {
    @Value("${endpoint}") String endpoint;
    @Value("${accessKeyId}") String accessKeyId;
    @Value("${accessKeySecret}") String accessKeySecret;
    @Value("${bucketName}") String bucketName;
    @Autowired
    private PictureService pictureService;
    int maxKeys = 1000;

    /**
     * 定时任务：根据OSS中最近更新的图片更新服务器图片信息
     */
    @Transactional
    public void doUpdatePictureStatus() {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        List<Picture> uploadingPictures =getUploadingPictures();
        List<String > brandCodeList = pictureService.getAllBrandCode();

        System.out.println(refFormatNowTime()+"待更新数据数："+uploadingPictures.size());
        for (int i=0; i<brandCodeList.size() ;i++) {
            ObjectListing objectListing;
            String brandCode = brandCodeList.get(i);
            String lastPicturePath;
            String picturePath = "";
            String keyPrefix ="product/"+brandCode+"/";
            String nextMaker = pictureService.getLastPictureByBrand(brandCode);

            System.out.println(refFormatNowTime()+i+":"+brandCode);
            do {
                objectListing = ossClient.listObjects(new ListObjectsRequest(bucketName).withPrefix(keyPrefix).withMarker(nextMaker).withMaxKeys(maxKeys));
                List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
                for (OSSObjectSummary s : sums) {
                    if (picturePath == "")
                        picturePath = "'"+"/"+s.getKey()+"'";
                    else
                        picturePath = picturePath+","+"'"+"/"+s.getKey()+"'";
                }
                nextMaker = objectListing.getNextMarker();
                if (picturePath != "")
                    pictureService.updatePicturesEnable(picturePath);
                if (sums.size() == 0)
                    lastPicturePath = null;
                else
                    lastPicturePath = sums.get(sums.size()-1).getKey();
            } while (objectListing.isTruncated());
            if (lastPicturePath != null) {
                pictureService.updateLastPictureIdByBrand(brandCode, lastPicturePath);
            }
        }
        System.out.println(refFormatNowTime()+"更新图片状态成功");
        // 关闭client
        ossClient.shutdown();
    }

    /**
     * 定时任务，每天将服务器数据库更新日期小于当前系统日期的带上传图片状态改为未上传
     */
    @Transactional
    public void doUpdateUnablePictureStatus() {
        System.out.println(refFormatNowTime()+"将过期图片状态置为无效");
        pictureService.updateUnablePictureStatus();
        System.out.println(refFormatNowTime()+"将过期图片状态置为无效、成功");
    }

    public List<Picture> getUploadingPictures() {
        List<Picture> uploadingPictures;
        uploadingPictures = pictureService.getUploadingPictures();
        return uploadingPictures;
    }

    public String refFormatNowTime(){
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("HH:mm:ss");
        String retStrFormatNowTime = sdFormatter.format(nowTime);
        return "时间:"+retStrFormatNowTime+"  ";
    }
}
