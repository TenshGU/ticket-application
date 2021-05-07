package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.User;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResultStatus register(User user, @RequestPart("headImg") MultipartFile headImg) {
        userService.saveUser(user,headImg);
        System.out.println(user);
        return ResultStatus.SUCCESS;
    }

    @GetMapping("/myself/{username}")
    public ResultStatus myself(@PathVariable("username") Integer username) {
        return ResultStatus.SUCCESS;
    }
}