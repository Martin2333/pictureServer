package com.topsports.pictureServer.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by huang.cj on 2017/10/30.
 */
@Controller
public class MainController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("")
    public String defaul(){
        return "index";
    }
    @RequestMapping("index")
    public String index(){
        return "index";
    }
}
