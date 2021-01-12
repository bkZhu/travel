package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Order;

import java.util.List;
import java.util.Map;


public interface OrderDao {
    public void save(Order order);
    List<Order> findByName(String username);
    Order findByOrderId(int order_id);

    void updateStatus(int order_id);

    List<Order> findAll();
    List<Order> findNOrder();
    List<Order> findYOrder();

    int findTotalCount(Map<String, String[]> condition);

    List<Order> findByPage(int start, int rows, Map<String, String[]> condition);

    void updateOrder(Order order);
}
