package com.ssm.tmall.dao;

import com.ssm.tmall.pojo.ProductImage;
import com.ssm.tmall.pojo.ProductImageExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductImage record);

    int insertSelective(ProductImage record);

    List<ProductImage> selectByExample(ProductImageExample example);

    ProductImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductImage record);

    int updateByPrimaryKey(ProductImage record);
}