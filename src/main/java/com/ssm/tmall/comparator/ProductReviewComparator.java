package com.ssm.tmall.comparator;

import com.ssm.tmall.pojo.Product;

import java.util.Comparator;

/**
 * 人气比较器
 * 评价数量多的在前面
 */
public class ProductReviewComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p2.getReviewCount() - p1.getReviewCount();
    }
}

