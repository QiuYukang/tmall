package com.ssm.tmall.dao;

import com.ssm.tmall.pojo.PropertyValue;
import com.ssm.tmall.pojo.PropertyValueExample;
import java.util.List;

public interface PropertyValueDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PropertyValue record);

    int insertSelective(PropertyValue record);

    List<PropertyValue> selectByExample(PropertyValueExample example);

    PropertyValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PropertyValue record);

    int updateByPrimaryKey(PropertyValue record);
}