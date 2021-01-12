package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.OrderDao;
import cn.itcast.travel.domain.Order;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void save(Order order) {
        //1.定义sql
        String sql = "insert into tab_order(username,content,email,price,status) values(?,?,?,?,?)";
        //2.执行sql

        template.update(sql,order.getUsername(),
                order.getContent(),
                order.getEmail(),
                order.getPrice(),
                order.getStatus()
        );
    }

    @Override
    public List<Order> findByName(String username) {
        //1.定义sql
        String sql = "select * from tab_order where username = ?";
        //2.执行sql
        return template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),username);
    }

    @Override
    public Order findByOrderId(int order_id) {
        String sql = "select * from tab_order where order_id = ?";
        //2.执行sql
        return template.queryForObject(sql,new BeanPropertyRowMapper<Order>(Order.class),order_id);
    }

    @Override
    public void updateStatus(int order_id) {
        String sql = " update tab_order set status = 'Y' where order_id=?";
        template.update(sql,order_id);
    }

    @Override
    public List<Order> findAll() {
        String sql = "select * from tab_Order ";
        return template.query(sql,new BeanPropertyRowMapper<Order>(Order.class));
    }

    @Override
    public List<Order> findNOrder() {
        //1.定义sql
        String sql = "select * from tab_order where status = 'Y'";
        //2.执行sql
        return template.query(sql,new BeanPropertyRowMapper<Order>(Order.class));
    }

    @Override
    public List<Order> findYOrder() {
        //1.定义sql
        String sql = "select * from tab_order where status = 'N'";
        //2.执行sql
        return template.query(sql,new BeanPropertyRowMapper<Order>(Order.class));
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //初始化sql语句，如果有条件就拼接上去
        String sql = "select count(*) from tab_order where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        //参数集合
        List<Object> params = new ArrayList<Object>();
        for(String key:condition.keySet()) {
            String value = condition.get(key)[0];
            if("currentPage".equals(key)||"rows".equals(key))
                continue;
            if(value!=null&&!"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%"+value+"%");
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        sql = sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Order> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from tab_order where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        //参数集合
        List<Object> params = new ArrayList<Object>();
        for(String key:condition.keySet()) {
            String value = condition.get(key)[0];
            if("currentPage".equals(key)||"rows".equals(key))
                continue;
            if(value!=null&&!"".equals(value)) {
                sb.append(" and " + key + " like ? ");
                params.add("%"+value+"%");
            }
        }
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),params.toArray());
    }

    @Override
    public void updateOrder(Order order) {
        String sql = "update tab_order set email = ? where order_id = ?";
        template.update(sql,order.getEmail(),order.getOrder_id());
    }
}
