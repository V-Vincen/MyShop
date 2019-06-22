package com.example.my.shop.web.api.web.controller.v1;


import com.example.my.shop.commons.dto.BaseResult;
import com.example.my.shop.web.api.service.TbUserService;
import com.example.my.shop.web.api.web.dto.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = "register")
    public BaseResult register(TbUser tbUser) {
        BaseResult baseResult = BaseResult.fail("注册失败！！！");
        Boolean aBoolean = tbUserService.register(tbUser);
        if (aBoolean) {
            baseResult = BaseResult.success("注册成功!!!");
        }
        return baseResult;
    }
}
