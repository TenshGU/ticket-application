package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.User;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.service.UserService;
import cn.edu.scau.ticket.application.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/25
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

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

    /**
     * 用户查看个人信息接口
     * 注意：这个方法仅在用户登录后，token通过验证后才可以访问，所以直接从token中获取账户名即可
     * @return 含有个人信息的JSON
     */
    @GetMapping("/myself")
    public ResultEntity myself(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader(jwtUtil.getHeader());
        String username = jwtUtil.getUsernameFromToken(token);
        return userService.getUserInfo(username);
    }

    /**
     * 管理员查看所有用户接口
     * @param username 查看的用户账号
     * @return 含有个人信息的JSON
     */
    @PreAuthorize("hasRole('ticketManager')")
    @GetMapping("/myself/{username}")
    public ResultEntity myself(@PathVariable("username") String username) {
        return userService.getUserInfo(username);
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     */
    @GetMapping("/verifycode")
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        userService.generateVerifyCode(request,response);
    }

    /**
     * 是否存在用户名
     * @param username 用户名
     * @return 结果值
     */
    @GetMapping("/existUsername")
    public boolean isExistUsername(String username) {
        return userService.isExistUsername(username);
    }
}