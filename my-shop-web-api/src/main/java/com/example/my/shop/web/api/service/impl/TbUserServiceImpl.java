package com.example.my.shop.web.api.service.impl;

import com.example.my.shop.web.api.dao.TbUserMapper;
import com.example.my.shop.web.api.service.TbUserService;
import com.example.my.shop.web.api.web.dto.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public Boolean register(TbUser tbUser) {
        return tbUserMapper.insert(tbUser);
    }
}
