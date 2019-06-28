package com.example.my.shop.web.ui.controller;

import com.example.my.shop.web.ui.api.ContentsApi;
import com.example.my.shop.web.ui.dto.TbConent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

@Controller
public class IndexController {

    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(Model model) throws IOException {
        List<TbConent> tbConents = ContentsApi.ppt();
        model.addAttribute("ppt", tbConents);
        return "index";
    }

}
