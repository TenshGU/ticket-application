package cn.edu.scau.ticket.application.mapper;

import cn.edu.scau.ticket.application.beans.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insertOrder(Order order);
    List<Order> getOrdersByUsername(String username);
}
