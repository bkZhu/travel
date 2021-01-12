package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Order;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.OrderService;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.OrderServiceImpl;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {
    private OrderService service = new OrderServiceImpl();
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Order order = new Order();
        try {
            BeanUtils.populate(order,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(order);
        service.regist(order);
        System.out.println("订单创建完成");
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = service.findAll();
        request.setAttribute("orders",orders);

        request.getRequestDispatcher("/m_index.jsp").forward(request,response);
    }
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service.active(request.getParameter("order_id"));
        response.sendRedirect(request.getContextPath()+"/OrderFindAll");
    }
}
