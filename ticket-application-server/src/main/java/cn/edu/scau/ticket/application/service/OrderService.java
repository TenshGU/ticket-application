package cn.edu.scau.ticket.application.service;

import cn.edu.scau.ticket.application.beans.result.ResultEntity;

public interface OrderService {
    ResultEntity getOrdersByUsername(String username);
}
