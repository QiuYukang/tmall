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
        order.setOrderItems(items);

        this.setProduct(items);

        // 更新订单的金额和订单货物数量
        float totalMoney = 0;
        int totalNumber = 0;
        for (OrderItem item : items) {
            totalNumber += item.getNumber();
            totalMoney += item.getProduct().getPromotePrice() * item.getNumber();
        }

        order.setTotalNumber(totalNumber);
        order.setTotal(totalMoney);
    }

    @Override
    public void fill(List<Order> orders) {
        for (Order order : orders) {
            this.fill(order);
        }
    }

    /**
     * 获取指定产品的销售数量
     *
     * @param pid 产品 id
     * @return
     */
    @Override
    public int getSaleCount(Integer pid) {

        return orderItemDao.getSaleCount(pid);
    }

    /**
     * 用户点击加入购物车按钮后，新增一条订单项目记录(未下单，该记录则没有对应的 oid)
     *
     * @param uid 用户 id
     * @param pid 产品 id
     * @param num 产品购买数量
     * @return 新增的订单项目的 id
     */
    @Override
    public int add(Integer uid, Integer pid, Integer num) {
        List<OrderItem> orderItems = this.getListByUser(uid);
        // 新增的产品是否已经存在(曾经加入购物车但是没有提交)
        boolean exitFlag = false;
        // 订单项目 id
        int oiid = 0;

        for (OrderItem orderItem : orderItems) {
            if (orderItem.getProduct().getId().equals(pid)) {
                exitFlag = true;
                orderItem.setNumber(orderItem.getNumber() + num);
                this.update(orderItem);
                oiid = orderItem.getId();
                break;
            }
        }

        // 如果订单项目不存在，则直接新增订单项目
        if (!exitFlag) {
            OrderItem orderItem = new OrderItem();
            orderItem.setNumber(num);
            orderItem.setPid(pid);
            orderItem.setUid(uid);

            orderItemDao.insert(orderItem);
            oiid = orderItem.getId();
        }

        return oiid;
    }

    /**
     * 获取用户购物车物品数量
     *
     * @param uid 用户 id
     * @return 用户购物车的物品总数量
     */
    @Override
    public int getCartCount(Integer uid) {
        List<OrderItem> orderItems = this.getListByUser(uid);
        int num = 0;

        for (OrderItem orderItem : orderItems) {
            num += orderItem.getNumber();
        }

        return num;
    }

    /**
     * 修改指定订单项目的物品数量
     *
     * @param pid 产品 id
     * @param num 修改后的产品数量
     * @param uid 用户 id
     */
    @Override
    public void change(Integer pid, Integer num, Integer uid) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andUidEqualTo(uid).andPidEqualTo(pid);

        List<OrderItem> orderItems = orderItemDao.selectByExample(example);
        if (orderItems.size() != 0) {
            orderItems.get(0).setNumber(num);
            orderItemDao.updateByPrimaryKeySelective(orderItems.get(0));
        }
    }

    /**
     * 给订单项目添加对应的产品信息
     *
     * @param orderItem
     */
    private void setProduct(OrderItem orderItem) {
        orderItem.setProduct(productService.getById(orderItem.getPid()));
    }

    private void setProduct(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            this.setProduct(orderItem);
        }
    }


    /**
     * 获取指定用户的订单项
     *
     * @param uid 用户 id
     * @return 未提交的订单项目列表
     */
    @Override
    public List<OrderItem> getListByUser(Integer uid) {
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andUidEqualTo(uid).andOidIsNull();

        List<OrderItem> orderItems = orderItemDao.selectByExample(example);
        this.setProduct(orderItems);

        return orderItems;
    }
}
