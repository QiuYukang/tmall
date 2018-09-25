package com.ssm.tmall.service.Impl;

import com.ssm.tmall.dao.OrderItemDao;
import com.ssm.tmall.pojo.*;
import com.ssm.tmall.service.OrderItemService;
import com.ssm.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private ProductService productService;

    @Override
    public OrderItem get(Integer id) {
        OrderItem orderItem = orderItemDao.selectByPrimaryKey(id);
        this.setProduct(orderItem);

        return orderItem;
    }

    @Override
    public List<OrderItem> getList() {
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id desc");
        List<OrderItem> orderItems = orderItemDao.selectByExample(example);
        this.setProduct(orderItems);

        return orderItems;
    }

    @Override
    public void add(OrderItem orderItem) {
        orderItemDao.insert(orderItem);
    }

    @Override
    public void delete(Integer id) {
        orderItemDao.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemDao.updateByPrimaryKeySelective(orderItem);
    }

    /**
     * 给 Order 订单写入所包含的订单项目
     *
     * @param order 订单
     */
    @Override
    public void fill(Order order) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOidEqualTo(order.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> items = orderItemDao.selectByExample(example);
        order.setItems(items);

        this.setProduct(items);

        // 更新订单的金额和订单货物数量
        float totalMoney = 0;
        int totalNumber = 0;
        for(OrderItem item: items){
            totalNumber += item.getNumber();
            totalMoney += item.getProduct().getPromotePrice()*item.getNumber();
        }

        order.setTotalNumber(totalNumber);
        order.setToatlMoney(totalMoney);
    }

    @Override
    public void fill(List<Order> orders) {
        for(Order order: orders){
            this.fill(order);
        }
    }

    /**
     * 给订单项目添加对应的产品信息
     * @param orderItem
     */
    private void setProduct(OrderItem orderItem) {
        orderItem.setProduct(productService.getById(orderItem.getPid()));
    }

    private void setProduct(List<OrderItem> orderItems) {
        for(OrderItem orderItem: orderItems) {
            this.setProduct(orderItem);
        }
    }
}
