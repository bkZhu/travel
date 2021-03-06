package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Order;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.service.OrderService;
import cn.itcast.travel.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/FindOrderByPageServlet_U")
public class FindOrderByPageServlet_U extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService service = new OrderServiceImpl();
        request.setCharacterEncoding("utf-8");
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        if(currentPage==null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "10";
        }
        Map<String,String[]> condition = request.getParameterMap();
        if(condition.containsKey("username")==false||condition.get("username")[0].length()<1){
            request.getRequestDispatcher("/u_index.jsp").forward(request,response);
            return;
        }
        PageBean<Order> pb = service.findOrderByPage(currentPage,rows,condition);
        request.setAttribute("pb",pb);
        request.setAttribute("condition",condition);//存放查询条件

        request.getRequestDispatcher("/u_index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
