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
    public User getByName(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);

        return userDao.selectByExample(example).get(0);
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

    /**
     * 判断用户名是否已经被注册
     *
     * @param name 注册提交的的新用户名
     * @return true 表示用户名已被注册，false 表示用户名未被注册
     */
    @Override
    public boolean isExist(String name) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(name);

        return !(userDao.selectByExample(example).isEmpty());
    }

    /**
     * 检查用户名密码是否匹配
     *
     * @param user     用户名
     * @param password 密码
     * @return 用户名密码匹配则为 true 否则为 false
     */
    @Override
    public boolean check(String user, String password) {
        UserExample example = new UserExample();
        example.createCriteria().andNameEqualTo(user).andPasswordEqualTo(password);
        if(userDao.selectByExample(example).isEmpty()){
            return false;
        }

        return true;
    }
}
