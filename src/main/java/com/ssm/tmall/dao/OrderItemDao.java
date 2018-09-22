package com.ssm.tmall.dao;

import com.ssm.tmall.pojo.OrderItem;
import com.ssm.tmall.pojo.OrderItemExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExample(OrderItemExample example);

    OrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}