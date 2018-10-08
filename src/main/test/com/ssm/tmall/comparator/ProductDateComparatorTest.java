package com.ssm.tmall.comparator;

import com.ssm.tmall.pojo.Product;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class ProductDateComparatorTest {
    @Test
    public void test1() throws InterruptedException {
        Product p1 = new Product();
        p1.setCreateDate(new Date());
        p1.setName("p1");

        Thread.sleep(2000);

        Product p2 = new Product();
        p2.setName("p2");
        p2.setCreateDate(new Date());

        Product[] products = new Product[]{p1, p2};

        System.out.println(products);

        Arrays.sort(products, new ProductDateComparator());

        System.out.println(products);
    }
}