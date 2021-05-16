package cn.edu.scau.ticket.application.service.impl;

import cn.edu.scau.ticket.application.beans.Order;
import cn.edu.scau.ticket.application.beans.result.ResultEntity;
import cn.edu.scau.ticket.application.beans.result.ResultStatus;
import cn.edu.scau.ticket.application.mapper.OrderMapper;
import cn.edu.scau.ticket.application.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/16
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResultEntity getOrdersByUsername(String username) {
        List<Order> orders = orderMapper.getOrdersByUsername(username);
        return ResultEntity.getResultEntity(ResultStatus.SUCCESS)
                .addInfo("total",orders.size()).addInfo("orders",orders);
    }
}
