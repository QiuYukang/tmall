package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Review;

import java.util.List;

public interface ReviewService {
    void add(Review review);

    void delete(Integer id);

    void update(Review review);

    Review getById(Integer id);

    /**
     * 获取指定产品的所有评论
     * @param pid 产品 id
     * @return 产品评论列表
     */
    List<Review> getList(Integer pid);

    /**
     * 获取评论个数
     *
     * @param pid 产品 id
     * @return 指定产品的评论数量
     */
    int getCount(Integer pid);
}
