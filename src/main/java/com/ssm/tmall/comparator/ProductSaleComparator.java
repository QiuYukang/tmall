package com.ssm.tmall.comparator;

import com.ssm.tmall.pojo.Product;

import java.util.Comparator;

/**
 * 销量比较器
 * 销量高的在前面
 */
public class ProductSaleComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getSaleCount() - p1.getSaleCount();
    }
}
