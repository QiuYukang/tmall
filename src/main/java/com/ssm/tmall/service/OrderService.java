package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.pojo.OrderItem;

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

    /**
     * 根据订单项目列表，新增订单
     *
     * @param order 新订单
     *
     * @param orderItems 订单项目列表
     * @return 订单总金额
     */
    float add(Order order, List<OrderItem> orderItems);

    /**
     * 获取指定状态的订单
     * @param uid 用户 id
     * @param status 需要获取的订单状态（ all 表示获取全部订单）
     * @return
     */
    List<Order> getList(Integer uid, String status);
}
