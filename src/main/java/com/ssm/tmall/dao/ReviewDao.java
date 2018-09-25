package com.ssm.tmall.dao;

import com.ssm.tmall.pojo.Review;
import com.ssm.tmall.pojo.ReviewExample;
import java.util.List;

public interface ReviewDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Review record);

    int insertSelective(Review record);

    List<Review> selectByExample(ReviewExample example);

    Review selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Review record);

    int updateByPrimaryKey(Review record);
}