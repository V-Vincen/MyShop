package com.example.my.shop.web.admin.web.conttroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 /**
  * @ProjectName:
  * @Package:        com.example.my.shop.spring.webmvc.controller
  * @ClassName:      MainController
  * @Description:
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/4/22 13:23
  * @Version:        1.0.0
  */
@Controller
public class MainController {

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = "main",method = RequestMethod.GET)
    public String main(){
        return "main";
    };
}
