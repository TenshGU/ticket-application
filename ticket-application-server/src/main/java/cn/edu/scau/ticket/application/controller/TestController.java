package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.User;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.utils.FastDFSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/3/31
 */
@Controller
public class TestController {
    @Autowired
    private FastDFSUtil fastDFSUtil;

    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/testUpload")
    @ResponseBody
    public ResultEntity register(@RequestPart(value = "headImg",required = false) MultipartFile headImg) throws Exception {
        String image = fastDFSUtil.uploadFile(headImg,true);
        System.out.println(image);
        return ResultEntity.getResultEntity(ResultStatus.SUCCESS);
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @GetMapping("/toError")
    public String error() {
        return "error";
    }
}
