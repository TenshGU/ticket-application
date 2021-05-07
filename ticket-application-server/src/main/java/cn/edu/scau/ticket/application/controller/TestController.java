package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.result.ResultStatus;
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

    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
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
