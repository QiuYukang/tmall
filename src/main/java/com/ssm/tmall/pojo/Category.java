package com.ssm.tmall.pojo;

import java.util.List;

/**
 * 产品类别
 */
public class Category {
    private Integer id; // 类别 id
    private String name;    // 类别名称，如平板电脑，沙发等

    public Category() {
    }

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // 非数据库字段
    // 用于显示首页下面的每个分类的五个产品
    private List<Product> products;

    // 用于显示首页右侧的分类推荐版块，每行八个，有多行
    private List<List<Product>> productsByRow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsByRow() {
        return productsByRow;
    }

    public void setProductsByRow(List<List<Product>> productsByRow) {
        this.productsByRow = productsByRow;
    }

    @Override
    public String toString() {
        return "PropertyDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
