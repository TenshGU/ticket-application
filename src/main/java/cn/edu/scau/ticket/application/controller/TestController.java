package cn.edu.scau.ticket.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/3/31
 */
@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @GetMapping("/toLogin")
    public String login() {
        return "login";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @GetMapping("/toError")
    public String error() {
        return "error";
    }
}
