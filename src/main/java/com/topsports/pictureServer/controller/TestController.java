package com.topsports.pictureServer.controller;

import com.topsports.pictureServer.dao.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huang.cj on 2017/8/3.
 */
@Controller
public class TestController extends BaseController{
    @Autowired
    private HttpServletRequest request;
    /*@Autowired
    private HttpServletResponse response;*/

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ResponseBody
    public String getString(){
        Map<String, Object> params = builderParams(request);

        return "Martin";
    }

}
