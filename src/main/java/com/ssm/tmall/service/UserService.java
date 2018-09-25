package com.ssm.tmall.service;

import com.ssm.tmall.pojo.User;

import java.util.List;

public interface UserService {
    User getByid(Integer id);

    List<User> getList();

    void delete(Integer id);

    void add(User user);

    void update(User user);
}
