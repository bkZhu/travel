package cn.itcast.travel.dao;

import cn.itcast.travel.dao.impl.OrderDaoImpl;
import org.junit.Test;

public class OrderTest {
    @Test
    public void OrderDaoTest(){
        OrderDaoImpl o = new OrderDaoImpl();
        System.out.println(o.findAll());
        System.out.println(o.findByName("张三"));
        System.out.println(o.findNOrder());
        System.out.println(o.findYOrder());
    }

}
