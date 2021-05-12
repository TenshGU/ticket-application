package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.User;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/25
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResultEntity register(@Validated User user, BindingResult bindingResult, @RequestPart(value = "headImg",required = false) MultipartFile headImg) {
        ResultEntity resultEntity = null;
        if (bindingResult.hasErrors()) {
            resultEntity = userService.validUserError(bindingResult);
        } else {
            resultEntity = userService.saveUser(user,headImg);
        }
        return resultEntity;
    }

    @GetMapping("/myself/{username}")
    public ResultEntity myself(@PathVariable("username") Integer username) {
        return ResultEntity.getResultEntity(ResultStatus.SUCCESS);
    }

    @GetMapping("/verifycode")
    public void generateCode(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Contro","no-cache");
        response.setDateHeader("Expire",0);
        this.userService.generateVerifyCode(req,response);
    }
}