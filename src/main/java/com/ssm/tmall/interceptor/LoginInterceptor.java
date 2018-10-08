package com.ssm.tmall.interceptor;

import com.ssm.tmall.pojo.User;
import com.ssm.tmall.service.OrderItemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * 登录状态拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private OrderItemService orderItemService;

    public LoginInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // 得到上下文(项目)路径---/tmall
        String contextPath = session.getServletContext().getContextPath();
        String[] noAutoPath = new String[]{
                "home",
                "checkLogin",
                "register",
                "search",
                "login",
                "loginAjax",
                "product",
                "category"
        };
        // 去掉tomcat路径，直接从项目路径开始
        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
        if(uri.startsWith("/fore")) {
            String method = StringUtils.substringAfterLast(uri, "/fore");
            if(!Arrays.asList(noAutoPath).contains(method)) {
                User user = (User) session.getAttribute("user");
                if(null == user) {
                    response.sendRedirect(contextPath + "/loginPage");
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
