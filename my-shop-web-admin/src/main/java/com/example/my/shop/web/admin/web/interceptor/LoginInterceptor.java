package com.example.my.shop.web.admin.web.interceptor;

import com.example.my.shop.domain.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my.shop.commons.constant.ConstantUtils.SESSION_USER;

/**
  * @ProjectName:
  * @Package:        com.example.my.shop.spring.webmvc.commons.interceptor
  * @ClassName:      LoginInterceptor
  * @Description:    登录拦截器
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/4/22 13:39
  * @Version:        1.0.0
  */
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        TbUser tbUser = (TbUser) httpServletRequest.getSession().getAttribute(SESSION_USER);

        // 判断用户是否登录
        if (tbUser == null) {
            // 用户未登录，重定向到登录页
            httpServletResponse.sendRedirect("/login");
            return false;
        }

        // 放行
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        /*//如果请假来自登陆页面
        System.out.println(modelAndView.getViewName());
        if(modelAndView.getViewName().endsWith("login")){
            // 则直接重定向到首页不再显示登录页
            httpServletResponse.sendRedirect("/main");
        }*/
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
