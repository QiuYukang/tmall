package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Property;

import java.util.List;

/**
 * 属性服务接口
 */
public interface PropertyService {
    Property get(Integer id);

    /**
     * 获取指定产品类别的所有属性
     *
     * @param cid 产品类别 id
     * @return 属性列表
     */
    List<Property> getListByCid(Integer cid);

    /**
     * 增加一个新的属性
     * @param property 新的属性对象 (只要包含名字 name 和所属类别 cId 即可)
     */
    void add(Property property);

    /**
     * 删除指定 id 的属性
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改属性名字
     *
     * @param property 新的属性信息
     */
    void update(Property property);
}
