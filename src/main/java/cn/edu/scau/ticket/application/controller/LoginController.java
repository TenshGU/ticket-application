package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PreAuthorize("hasRole('commonUser')")
    @GetMapping("/flightBook")
    public ResultStatus flightBook() {
        return ResultStatus.SUCCESS;
    }

    @GetMapping("/myself/{username}")
    public ResultStatus myself(@PathVariable("username") Integer username) {
        return ResultStatus.SUCCESS;
    }
}