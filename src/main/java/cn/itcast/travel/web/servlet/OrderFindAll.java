package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Order;
import cn.itcast.travel.service.OrderService;
import cn.itcast.travel.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/OrderFindAll")
public class OrderFindAll extends HttpServlet {
    private OrderService service = new OrderServiceImpl();
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = service.findAll();
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/m_index.jsp").forward(req,resp);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
