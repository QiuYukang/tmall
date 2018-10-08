package com.ssm.tmall.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class TmallExceptionHandler {

    // 空指针异常(无法找到对应的信息)
    @ExceptionHandler(value = {NullPointerException.class})
    public ModelAndView nullPointer(Exception e) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errorMsg", "请求的信息不存在！");

        return mv;
    }
}
