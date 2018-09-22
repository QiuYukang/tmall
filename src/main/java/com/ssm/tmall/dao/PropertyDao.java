package com.ssm.tmall.dao;

import com.ssm.tmall.pojo.Property;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 属性类别 DAO
 */
@Repository
public interface PropertyDao {
    /**
     * 获取指定 id 的属性
     *
     * @param id 属性 id
     * @return 属性对象
     */
    Property get(Integer id);

    /**
     * 通过类别 id 获取所有属性
     *
     * @param cId 类别 id
     * @return 指定类别下的所有属性对象列表
     */
    List<Property> getAll(Integer cId);

    /**
     * 添加一个新的属性
     *
     * @param property 新的属性对象(cid字段不能为空)
     */
    void add(Property property);

    /**
     * 删除指定 id 的属性
     *
     * @param id 属性 id
     */
    void delete(Integer id);

    /**
     * 更新指定 id 的属性
     *
     * @param property 新的属性对象(id不变)
     */
    void update(Property property);
}