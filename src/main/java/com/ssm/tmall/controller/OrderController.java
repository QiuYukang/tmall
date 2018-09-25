package com.ssm.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.tmall.pojo.Order;
import com.ssm.tmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/admin_order_list", method = RequestMethod.GET)
    public String get(@RequestParam(required = false, defaultValue = "1") Integer start,
                      Model model) {
        PageHelper.startPage(start, 5);
        List<Order> orders = orderService.getList();
        PageInfo<Order> page = new PageInfo<>(orders, 4);

        model.addAttribute("page", page);
        model.addAttribute("orders", orders);
        model.addAttribute("url", "admin_list_order");

        return "admin/listOrder";
    }

    @RequestMapping(value = "admin_order_delivery/{id}")
    public String delivery(@PathVariable("id") Integer id){

        return "";
    }
}
