package com.ssm.tmall.comparator;

import com.ssm.tmall.pojo.Product;

import java.util.Comparator;

/**
 * 产品综合排序比较器
 * 把 销量 x 评价 高的放在前面
 */
public class ProductAllComparator implements Comparator<Product> {


    @Override
    public int compare(Product p1, Product p2) {
        // sort后输出为从小到大，所以要从大到小排序需要反过来
        return p2.getReviewCount()*p2.getSaleCount() - p1.getReviewCount()*p1.getSaleCount();
    }
}
