package com.topsports.pictureServer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huang.cj on 2017/10/30.
 */
@Controller
@RequestMapping(value = "uploadPicture")
public class UploadFilesController {
    private static final Logger logger = LoggerFactory.getLogger(UploadFilesController.class);
    @RequestMapping(value="/uploadPicture")
    public String uploadPictures(Model model){
        return "uploadPicture";
    }
}
