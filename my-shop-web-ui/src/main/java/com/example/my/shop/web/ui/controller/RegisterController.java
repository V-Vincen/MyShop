package com.example.my.shop.web.ui.controller;


import com.example.my.shop.commons.dto.BaseResult;
import com.example.my.shop.web.ui.api.UserApi;
import com.example.my.shop.web.ui.dto.TbUser;
import com.google.code.kaptcha.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    @RequestMapping(value = "registerJsp",method = RequestMethod.GET)
    public String registerJsp() {
        return "register";
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String register(TbUser tbUser, Model model, HttpServletRequest req) throws Exception {
        String verification  = (String) req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if(!StringUtils.equals(verification,tbUser.getVerification())){
            model.addAttribute("baseResult",BaseResult.fail("验证码输入错误，请重新输入"));
            return "register";
        }

        BaseResult result = UserApi.register(tbUser);
        model.addAttribute("baseResult",result);
        if(result.getStatus() == 200){
            return "index";
        }
        return "register";
    }
}
