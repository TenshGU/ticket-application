package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.service.PayService;
import cn.edu.scau.ticket.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/15
 */
@RestController
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/alipay/notify")
    public String alipayNotify() {
        return "success";
    }

    /**
     * 用户支付后回调，不能确保一定能成功
     * @return
     */
    @GetMapping("/alipay/return")
    public ResultEntity alipayReturn(HttpServletRequest request) {
        return payService.validAndSaveOrder(request);
    }
}
