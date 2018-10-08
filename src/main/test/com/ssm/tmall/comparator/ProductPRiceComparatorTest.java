package com.ssm.tmall.comparator;

import com.ssm.tmall.pojo.Product;
import org.junit.Test;

import java.util.Arrays;

public class ProductPRiceComparatorTest {
    @Test
    public void test1() {
        Product p1 = new Product();
        p1.setPromotePrice((float) 30.0);
        Product p2 = new Product();
        p2.setPromotePrice((float) 40.0);

        Product[] products = new Product[]{p1, p2};

        System.out.println(products);

        Arrays.sort(products, new ProductPRiceComparator());


        System.out.println(products);
    }
}