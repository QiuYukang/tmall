package com.ssm.tmall.service.Impl;

import com.ssm.tmall.dao.UserDao;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.pojo.UserExample;
import com.ssm.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getByid(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getList() {
        UserExample example = new UserExample();
        // 按照 id 排列
        example.setOrderByClause("id desc");

        return userDao.selectByExample(example);
    }

    @Override
    public void delete(Integer id) {
        userDao.deleteByPrimaryKey(id);
    }

    @Override
    public void add(User user) {
        userDao.insert(user);
    }

    @Override
    public void update(User user) {
        // 如果有字段是空，则保持原始字段不更新即可，只更新非空字段；updateByPrimaryKey是更新所有字段
        userDao.updateByPrimaryKeySelective(user);
    }
}
