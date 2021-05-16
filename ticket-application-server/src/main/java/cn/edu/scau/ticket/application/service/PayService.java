package cn.edu.scau.ticket.application.service;

import cn.edu.scau.ticket.application.beans.Flight;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PayService {
    void sendPayment(Flight flight, String username, HttpServletResponse response);

    ResultEntity validAndSaveOrder(HttpServletRequest request);
}
