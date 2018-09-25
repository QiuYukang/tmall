package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    // CRUD
    OrderItem get(Integer id);

    List<OrderItem> getList();

    void add(OrderItem orderItem);

    void delete(Integer id);

    void update(OrderItem orderItem);

    /**
     * 给 Order 订单写入订单所包含的订单项目
     *
     * @param order 订单
     */
    void fill(Order order);

    void fill(List<Order> orders);
}
