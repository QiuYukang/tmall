package com.ssm.tmall.service.Impl;

import com.ssm.tmall.dao.OrderDao;
import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.pojo.OrderExample;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.OrderItemService;
import com.ssm.tmall.service.OrderService;
import com.ssm.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    private UserService userService;

    @Override
    public Order get(Integer id) {
        Order order = orderDao.selectByPrimaryKey(id);
        this.setUser(order);
        orderItemService.fill(order);

        return order;
    }

    @Override
    public List<Order> getList() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> orders =  orderDao.selectByExample(example);
        this.setUser(orders);
        orderItemService.fill(orders);

        return orders;
    }

    @Override
    public void delete(Integer id) {
        orderDao.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order order) {
        orderDao.updateByPrimaryKeySelective(order);
    }

    @Override
    public void add(Order order) {
        orderDao.insert(order);
    }

    /**
     * 给订单设置用户信息
     *
     * @param order
     */
    private void setUser(Order order) {
        User user = userService.getByid(order.getUid());
        order.setUser(user);
    }

    private void setUser(List<Order> orders){
        for(Order order: orders) {
            this.setUser(order);
        }
    }
}
