package com.ssm.tmall.service;

import com.ssm.tmall.pojo.User;

import java.util.List;

public interface UserService {
    User getByid(Integer id);

    User getByName(String name);

    List<User> getList();

    void delete(Integer id);

    void add(User user);

    void update(User user);

    /**
     * 判断用户名是否已经被注册
     *
     * @param name 注册提交的的新用户名
     * @return
     */
    boolean isExist(String name);

    /**
     * 检查用户名密码是否匹配
     *
     * @param user 用户名
     * @param password 密码
     */
    boolean check(String user, String password);
}
