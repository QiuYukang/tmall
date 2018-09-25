package com.ssm.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "admin_user_list", method = RequestMethod.GET)
    public String get(@RequestParam(required = false, defaultValue = "1") Integer start,
                      Model model) {
        PageHelper.startPage(start, 5);
        List<User> users = userService.getList();
        PageInfo<User> page = new PageInfo<>(users, 4);

        model.addAttribute("users", users);
        model.addAttribute("page", page);
        model.addAttribute("url", "admin_user_list");

        return "admin/listUser";
    }
}
