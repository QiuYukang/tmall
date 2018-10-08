package com.ssm.tmall.interceptor;

import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 前台界面的其他拦截器
 * 实现的功能有:
 * 添加购物车物品数量属性
 */
public class OtherInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private OrderItemService orderItemService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // 用户购物车物品总数
        int cartTotalItemNumber = 0;

        if (null != user) {
            cartTotalItemNumber = orderItemService.getCartCount(user.getId());
        }

        session.setAttribute("cartTotalItemNumber", cartTotalItemNumber);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
