package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.service.OrderService;
import cn.edu.scau.ticket.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/16
 */
@RestController
public class OrderController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResultEntity orders(HttpServletRequest request) {
        String username = userService.getUsernameFromToken(request);
        return orderService.getOrdersByUsername(username);
    }
}
