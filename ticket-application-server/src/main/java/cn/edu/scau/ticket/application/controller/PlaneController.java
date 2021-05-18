package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/17
 */
@RestController
public class PlaneController {
    @Autowired
    private PlaneService planeService;

    @GetMapping("/planes")
    public ResultEntity getAllPlanes() {
        return planeService.getAllPlanes();
    }
}
