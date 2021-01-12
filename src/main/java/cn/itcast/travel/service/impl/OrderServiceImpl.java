package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.OrderDao;
import cn.itcast.travel.dao.impl.OrderDaoImpl;
import cn.itcast.travel.domain.Order;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.service.OrderService;
import cn.itcast.travel.util.MailUtils;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    @Override
    public void regist(Order order) {
        order.setStatus("N");
        orderDao.save(order);
    }

    @Override
    public void active(String order_id) {
        orderDao.updateStatus(Integer.parseInt(order_id));
        Order order = orderDao.findByOrderId(Integer.parseInt(order_id));
        String content = "尊敬的"+order.getUsername()
                    +",您的订单已受理,公司将在近期为您安排行程。"
                    +"您的订单编号为:"+order.getOrder_id()
                    +",您的订单内容为:"+order.getContent()
                    +",您的订单价格为:"+order.getPrice()
                    +"。祝您旅途愉快！";
        content += "<a href='http://localhost:9090/travel'>" +
                "点击前往旅游网官网查看订单</a>";
        MailUtils.sendMail(order.getEmail(),content,"订单受理成功");

        System.out.println("已发送受理邮件");
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public PageBean<Order> findOrderByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        PageBean<Order> pb = new PageBean<Order>();
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        int totalCount = orderDao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        int start = (currentPage -1 )* rows;
        List<Order> list = orderDao.findByPage(start,rows,condition);
        pb.setList(list);
        int totPage = (int) Math.ceil((totalCount/(rows*1.0)));
        pb.setTotalPage(totPage);
        return pb;
    }

    @Override
    public Order findByOrderId(String order_id) {
        return orderDao.findByOrderId(Integer.parseInt(order_id));
    }

    @Override
    public void updateOrder(Order order) {
        orderDao.updateOrder(order);
    }
}
