package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/16
 */
@Controller
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/allAirport")
    public ResultEntity getAllAirport() {
        return airportService.getAllAirports();
    }
}
