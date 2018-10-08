package com.ssm.tmall.comparator;

import com.ssm.tmall.pojo.Product;

import java.util.Comparator;

/**
 * 新品比较器
 * 根据产品上架日期，上架时间越晚的的在前面
 */
public class ProductDateComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getCreateDate().compareTo(p1.getCreateDate());
    }
}
