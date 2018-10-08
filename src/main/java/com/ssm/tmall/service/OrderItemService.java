package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    // CRUD
    OrderItem get(Integer id);

    List<OrderItem> getList();

    /**
     * 获取指定用户的订单项
     *
     * @param uid 用户 id
     * @return 未提交的订单项目列表
     */
    List<OrderItem> getListByUser(Integer uid);

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

    /**
     * 获取指定产品的销售数量
     *
     * @param pid 产品 id
     * @return
     */
    int getSaleCount(Integer pid);

    /**
     * 用户点击加入购物车按钮后，新增一条订单项目记录(未下单，该记录则没有对应的 oid)
     *
     * @param uid 用户 id
     * @param pid 产品 id
     * @param num 产品购买数量
     * @return 新增的订单项目的 id
     */
    int add(Integer uid, Integer pid, Integer num);

    /**
     * 获取用户购物车物品数量
     *
     * @param uid 用户 id
     * @return 用户购物车的物品总数量
     */
    int getCartCount(Integer uid);

    /**
     * 修改指定订单项目的物品数量
     *
     * @param pid 产品 id
     * @param num 修改后的产品数量
     * @param uid 用户 id
     */
    void change(Integer pid, Integer num, Integer uid);
}
