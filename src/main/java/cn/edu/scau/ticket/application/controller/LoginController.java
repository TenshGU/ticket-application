package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/25
 */
@RestController
public class LoginController {
    @GetMapping("/toLogin")
    public ResultStatus toLogin() {
        return ResultStatus.SUCCESS;
    }
}
