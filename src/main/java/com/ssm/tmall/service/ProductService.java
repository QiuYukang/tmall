package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);
    void delete(Integer id);
    void update(Product product);
    Product getById(Integer id);

    /**
     * 获取指定分类下的所有产品
     *
     * @param cId 类别 id
     * @return 产品列表
     */
    List<Product> getListBycId(Integer cId);
}
