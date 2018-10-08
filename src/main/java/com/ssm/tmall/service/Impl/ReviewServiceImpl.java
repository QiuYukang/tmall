package com.ssm.tmall.service.Impl;

import com.ssm.tmall.dao.ReviewDao;
import com.ssm.tmall.pojo.Review;
import com.ssm.tmall.pojo.ReviewExample;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.ReviewService;
import com.ssm.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private UserService userService;

    @Override
    public void add(Review review) {
        reviewDao.insert(review);
    }

    @Override
    public void delete(Integer id) {
        reviewDao.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review review) {
        reviewDao.updateByPrimaryKeySelective(review);
    }

    @Override
    public Review getById(Integer id) {
        return reviewDao.selectByPrimaryKey(id);
    }

    /**
     * 获取指定产品的所有评论
     *
     * @param pid 产品 id
     * @return 产品评论列表
     */
    @Override
    public List<Review> getList(Integer pid) {
        ReviewExample example = new ReviewExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andPidEqualTo(pid);

        // 给查询到的数据库信息添加User信息
        List<Review> reviews = reviewDao.selectByExample(example);
        this.setUser(reviews);

        return reviews;
    }

    /**
     * 获取评论个数
     *
     * @param pid 产品 id
     * @return 指定产品的评论数量
     */
    @Override
    public int getCount(Integer pid) {
        return this.getList(pid).size();
    }

    /**
     * 给评价设置用户信息
     *
     * @param review 评价
     */
    private void setUser(Review review) {
        User user = userService.getByid(review.getUid());
        review.setUser(user);
    }

    private void setUser(List<Review> reviews) {
        for(Review review: reviews) {
            this.setUser(review);
        }
    }
}
