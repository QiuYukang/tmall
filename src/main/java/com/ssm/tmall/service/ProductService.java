package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Category;
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

    /**
     * 给产品设置第一张图片
     *
     * @param product 产品对象
     */
    void setFirstProductIamge(Product product);

    /**
     * 给分类对象填充相应的产品内容
     *
     * @param category 分类对象
     */
    void fill(Category category);

    /**
     * 为多个分类对象填充产品集合
     * @param categories
     */
    void fill(List<Category> categories);

    /**
     * 为多个分类填充推荐产品集合，按照 8 个为 1 行，拆成多行，以便后续页面进行显示
     * @param categories
     */
    void fillByRow(List<Category> categories);

    /**
     * 给产品填充销量和评价总数信息
     *
     * @param product
     */
    void setSaleAndReviewNumber(Product product);

    void setSaleAndReviewNumber(List<Product> products);

    List<Product> search(String keyword);
}
