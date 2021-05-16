package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/16
 */
@RestController
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/allAirports")
    public ResultEntity getAllAirports() {
        return airportService.getAllAirports();
    }
}
