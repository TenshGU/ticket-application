package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.ResultStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/3/31
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
