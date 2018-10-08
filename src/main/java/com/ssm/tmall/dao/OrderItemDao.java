package com.ssm.tmall.dao;

import com.ssm.tmall.pojo.OrderItem;
import com.ssm.tmall.pojo.OrderItemExample;
import java.util.List;

public interface OrderItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExample(OrderItemExample example);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    /**
     * 获取指定产品的销售数量
     *
     * @param pid 产品 id
     * @return
     */
    Integer getSaleCount(Integer pid);
}