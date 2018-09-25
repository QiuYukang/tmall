package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Order;

import java.util.List;

public interface OrderService {
    // 接口中的成员变量默认是 static final的
    // 订单状态定义
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    // CRUD
    Order get(Integer id);

    List<Order> getList();

    void delete(Integer id);

    void update(Order order);

    void add(Order order);
}
