package cn.edu.scau.ticket.application.controller;

import cn.edu.scau.ticket.application.beans.Flight;
import cn.edu.scau.ticket.application.beans.qo.FlightCriteria;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.service.FlightService;
import cn.edu.scau.ticket.application.service.PayService;
import cn.edu.scau.ticket.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/7
 */
@RestController
public class FlightController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private UserService userService;

    @Autowired
    private PayService payService;

    /**
     * 机票订购，需要接收航班信息
     * 此处会提取用户登录的token
     * @param flight 即将订购的航班
     * @param response
     * @return
     */
    @PreAuthorize("hasRole('commonUser')")
    @PutMapping("/flightBook")
    public void flightBook(Flight flight, HttpServletRequest request, HttpServletResponse response) {
        String username = userService.getUsernameFromToken(request);
        payService.sendPayment(flight, username, response);
    }

    @GetMapping("/flight")
    public ResultEntity searchFlight(FlightCriteria flightCriteria) {
        return flightService.searchFlightByCondition(flightCriteria);
    }

    @PreAuthorize("hasRole('ticketManager')")
    @PutMapping("/flight")
    public ResultEntity addFlight(Flight flight) {
        return flightService.addFlight(flight);
    }

    @PreAuthorize("hasRole('ticketManager')")
    @PostMapping("/flight")
    public ResultEntity updateFlight(Flight flight) {
        return flightService.updateFlight(flight);
    }

    @PreAuthorize("hasRole('ticketManager')")
    @DeleteMapping("/flight/{flightId}")
    public ResultEntity deleteFlight(@PathVariable("flightId") String flightId) {
        return flightService.deleteFlightById(flightId);
    }
}