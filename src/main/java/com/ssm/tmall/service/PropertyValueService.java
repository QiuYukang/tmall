package com.ssm.tmall.service;

import com.ssm.tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    void add(PropertyValue propertyValue);

    void delete(Integer id);

    void update(PropertyValue propertyValue);

    /**
     * 获取指定产品的指定属性
     *
     * @param pid 产品 id
     * @param ptid 产品属性 id
     * @return
     */
    PropertyValue get(Integer pid, Integer ptid);

    /**
     * 获取指定产品的所有属性值
     * @param pid 产品 id
     * @return
     */
    List<PropertyValue> getList(Integer pid);
}
