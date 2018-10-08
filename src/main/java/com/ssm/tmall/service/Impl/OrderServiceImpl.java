package com.ssm.tmall.service.Impl;

import com.ssm.tmall.dao.OrderDao;
import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.pojo.OrderExample;
import com.ssm.tmall.pojo.OrderItem;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.OrderItemService;
import com.ssm.tmall.service.OrderService;
import com.ssm.tmall.service.UserService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
     * 根据订单项目列表，新增订单(需要进行事务处理，因为涉及到两张表的操作，数据需要同步)
     *
     * @param order      新订单
     * @param orderItems 订单项目列表
     * @return 订单总金额
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, noRollbackForClassName = "Exception")
    public float add(Order order, List<OrderItem> orderItems) {
        // 订单总价格
        float totalMoney = 0;
        // 生成订单号码，订单创建时间 + 四位随机数
        Date date = new Date();
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date) +
                RandomUtils.nextInt(10000);

        order.setCreateDate(date);
        order.setOrderCode(orderCode);
        order.setStatus(OrderService.waitPay);

        // 此处需要事务控制(可以人为制造异常进行测试-----？？？？？)
        orderDao.insert(order);
        Integer oid = order.getId();
        for(OrderItem orderItem: orderItems) {
            orderItem.setOid(oid);
            orderItemService.update(orderItem);
            totalMoney += orderItem.getNumber() * orderItem.getProduct().getPromotePrice();
        }

        return totalMoney;
    }

    /**
     * 获取指定状态的订单
     *
     * @param uid    用户 id
     * @param status 需要获取的订单状态（ all 表示获取全部订单）
     * @return
     */
    @Override
    public List<Order> getList(Integer uid, String status) {
        OrderExample example = new OrderExample();
        List<Order> orders = null;

        switch (status) {
            case "all":
                example.createCriteria().andStatusNotEqualTo(OrderService.delete).andUidEqualTo(uid);
                break;
            case OrderService.waitPay:
                example.createCriteria().andStatusNotEqualTo(OrderService.waitPay).andUidEqualTo(uid);
                break;
            case OrderService.waitDelivery:
                example.createCriteria().andStatusNotEqualTo(OrderService.waitDelivery).andUidEqualTo(uid);
                break;
            case OrderService.waitConfirm:
                example.createCriteria().andStatusNotEqualTo(OrderService.waitConfirm).andUidEqualTo(uid);
                break;
            case OrderService.waitReview:
                example.createCriteria().andStatusNotEqualTo(OrderService.waitReview).andUidEqualTo(uid);
                break;
            default:
                return null;
        }

        example.setOrderByClause("id desc");

        orders = orderDao.selectByExample(example);
        this.setUser(orders);
        orderItemService.fill(orders);

        return orders;
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
