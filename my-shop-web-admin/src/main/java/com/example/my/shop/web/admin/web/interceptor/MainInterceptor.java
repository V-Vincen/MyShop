package com.example.my.shop.web.admin.web.interceptor;

import com.example.my.shop.domain.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my.shop.commons.constant.ConstantUtils.SESSION_USER;

public class MainInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //如果请假来自登陆页面
        if (modelAndView !=null && modelAndView.getViewName() != null && modelAndView.getViewName().endsWith("login")) {
            TbUser tbUser = (TbUser) httpServletRequest.getSession().getAttribute(SESSION_USER);

            // 判断用户是否登录
            if (tbUser != null) {
                // 则直接重定向到首页不再显示登录页
                httpServletResponse.sendRedirect("/main");
            }
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
