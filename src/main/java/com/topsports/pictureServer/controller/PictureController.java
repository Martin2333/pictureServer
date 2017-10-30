package com.topsports.pictureServer.controller;

import com.topsports.pictureServer.model.Picture;
import com.topsports.pictureServer.service.PictureService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huang.cj on 2017/8/3.
 */
@RestController
@RequestMapping(value = "/picture/*")
public class PictureController extends BaseController{
    @Autowired
    private PictureService pictureService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;


    @RequestMapping(value = "getPicturePath",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String selectPicture() {
        Map<String, Object> params = builderParams(request);
        Map<String, String> result = new HashedMap();
        int errorCode = 0;
        Picture picture;
        String url;

        System.out.println(params.size());
        if(params.get("brandCode") == null || params.get("pictureCode")== null || params.get("sourceSystem")== null) {
            errorCode = 1001;
        }

        if (errorCode == 0) {
            picture = pictureService.selectByParams(params);
            if (picture == null) {
                errorCode = 1002;
            }
            else {
                url = pictureService.getUrlBySourceSystem(picture.getSourceSystem());
                if (url == null) {
                    errorCode = 1004;
                }
                else {
                    result.put("picturePath", url + picture.getPicturePath());
                }
            }
        }
        return retContent(errorCode, result);
    }

    @RequestMapping(value = "deletePicture",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String deletePicture() {
        Map<String, Object> params = builderParams(request);
        Map<String, String> result = new HashedMap();
        int errorCode = 0;
        int i;

        if (params.get("pictureId") ==null){
            if(params.get("brandCode") == null || params.get("pictureCode")== null || params.get("sourceSystem")== null) {
                errorCode = 1001;
            }
        }

        if (errorCode == 0) {
            i = pictureService.deleteByParams(params);
            if (i == 0) {
                errorCode = 1003;
            }
        }
        return retContent(errorCode, result);
    }

    @RequestMapping(value = "uploadPicture",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String uploadPicture() {
        Map<String, Object> params = builderParams(request);
        Map<String,Object> result = new HashedMap();
        int errorCode = 0;

        if( params.get("categoryName")== null || params.get("brandCode") == null || params.get("pictureCode")== null ||
                params.get("sourceSystem")== null || params.get("format") == null) {
            errorCode = 1001;
        }

        if (errorCode == 0) {
            result = pictureService.uploadDataProImgs(params);
        }
        return retContent(errorCode, result);
    }

    @RequestMapping(value = "getCredentials",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getCredential() {
        Map<String,Object> result = pictureService.getCredential();
        JSONObject jsonObject = JSONObject.fromObject(result);
        System.out.println(jsonObject);

        return retContent(0, result);
    }
}
