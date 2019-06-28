package com.example.my.shop.web.api.service.impl;

import com.example.my.shop.web.api.dao.TbUserMapper;
import com.example.my.shop.web.api.service.TbUserService;
import com.example.my.shop.web.api.web.dto.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public Boolean register(TbUser tbUser) {
        String md5Password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
        tbUser.setPassword(md5Password);
        return tbUserMapper.insert(tbUser);
    }
}
