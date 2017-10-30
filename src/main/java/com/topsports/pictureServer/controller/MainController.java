package com.topsports.pictureServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huang.cj on 2017/10/30.
 */
@Controller
public class MainController {

    @RequestMapping("login")
    public String login(){
        return "login";
    }
}
