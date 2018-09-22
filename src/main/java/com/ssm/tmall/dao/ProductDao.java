package com.ssm.tmall.dao;

import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.pojo.ProductExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);
}