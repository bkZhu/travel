package cn.itcast.travel.service;

import cn.itcast.travel.domain.Order;
import cn.itcast.travel.domain.PageBean;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void regist(Order order);

    void active(String order_id);
    List<Order> findAll();

    PageBean<Order> findOrderByPage(String currentPage, String rows, Map<String, String[]> condition);

    Order findByOrderId(String order_id);

    void updateOrder(Order order);
}
