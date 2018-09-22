package com.ssm.tmall.dao;

import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.pojo.OrderExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}