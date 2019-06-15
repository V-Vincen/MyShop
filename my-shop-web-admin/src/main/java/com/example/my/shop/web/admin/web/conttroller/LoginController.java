package com.example.my.shop.web.admin.web.conttroller;

import com.example.my.shop.commons.constant.ConstantUtils;
import com.example.my.shop.domain.TbUser;
import com.example.my.shop.web.admin.service.TbUserService;
import com.example.my.shop.web.admin.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.my.shop.commons.constant.ConstantUtils.COOKIE_NAME_USER_INFO;

@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest req) {
        //获取游览器中的COOKIE用户
        String userInfo = CookieUtils.getCookieValue(req, COOKIE_NAME_USER_INFO);
        if(!StringUtils.isBlank(userInfo)){
            String[] userInfoArray = userInfo.split(":");
            String email = userInfoArray[0];
            String password = userInfoArray[1];
            req.setAttribute("email",email);
            req.setAttribute("password",password);
            req.setAttribute("isRemember",true);
        }
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpServletRequest req, HttpServletResponse resp, Model model) {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean isRemember = req.getParameter("isRemember") == null ? false : true;

        //用户选择不记住
        if(!isRemember){
            CookieUtils.deleteCookie(req,resp,COOKIE_NAME_USER_INFO);
        }
        TbUser tbUser = tbUserService.login(email, password);
        //登录失败
        if (tbUser == null) {
            model.addAttribute("message", "用户密码错误");
            return "login";

        } else {//登陆成功
            if (isRemember) {
                //用户信息存储一周
                CookieUtils.setCookie(req, resp, COOKIE_NAME_USER_INFO, String.format("%s:%s", email, password), 7 * 24 * 60 * 60);
            }
            //将登陆信息放入回话
            req.getSession().setAttribute(ConstantUtils.SESSION_USER, tbUser);
            return "redirect:/main";
        }
    }

    /**
     * 退出登录
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return "login";
    }
}
