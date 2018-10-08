package com.ssm.tmall.comparator;

import com.ssm.tmall.pojo.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 价格比较器
 * 贵的在前面
 */
public class ProductPRiceComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return (int) (p2.getPromotePrice() - p1.getPromotePrice());
    }
}
