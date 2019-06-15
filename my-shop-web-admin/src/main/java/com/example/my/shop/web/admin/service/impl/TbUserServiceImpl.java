package com.example.my.shop.web.admin.service.impl;


import com.example.my.shop.commons.dto.BaseResult;
import com.example.my.shop.commons.dto.PageInfo;
import com.example.my.shop.commons.utils.RegexpUtils;
import com.example.my.shop.commons.validator.BeanValidator;
import com.example.my.shop.domain.TbUser;
import com.example.my.shop.web.admin.dao.TbUserMapper;
import com.example.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TbUserServiceImpl implements TbUserService {

    private static final Logger logger = LoggerFactory.getLogger(TbUserServiceImpl.class);

    @Autowired
    private TbUserMapper tbUserMapper;

    public TbUser login(String email, String password) {
        logger.debug("调用 getByEmail(), email:{} password:{}", email, password);

        TbUser tbUser = tbUserMapper.getByEmail(email);
        if (tbUser != null) {
            String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes());
            if (md5Pwd.equals(tbUser.getPassword())) {
                return tbUser;
            }
        }
        return null;
    }

    @Override
    public BaseResult save(TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        //验证不通过
        if(validator != null){
            return  BaseResult.fail(validator);
        }
        //验证通过
        else {
            tbUser.setUpdated(new Date());
            //新增用户
            if (tbUser.getId() == null) {
                //密码加密
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUserMapper.insert(tbUser);
            }
            //编辑用户
            else {
                tbUserMapper.updateByPrimaryKey(tbUser);
            }
            return BaseResult.success("Successful saving of user information");
        }
    }

    @Override
    public TbUser selectByPrimaryKey(Long id) {
        return tbUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteMulti(String[] ids) {
        return tbUserMapper.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbUser> page(int start,int length,int draw,TbUser tbUser,String likeSearch) {
        Map<String,Object> map = new HashMap<>();
        map.put("start",start);
        map.put("length",length);
        map.put("tbUser",tbUser);
        map.put("likeSearch",likeSearch);

        int count = tbUserMapper.count(map);
        PageInfo<TbUser> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);

        pageInfo.setData(tbUserMapper.page(map));
        return pageInfo;
    }

    @Override
    public BaseResult deleteByPrimaryKey(Long id) {
        BaseResult baseResult = BaseResult.fail();
        int result = tbUserMapper.deleteByPrimaryKey(id);
        if(result>0){
            baseResult = BaseResult.success();
        }
        return baseResult;
    }

    /**
     * @Method: checkTbUser
     * @Description: 用户的有效验证
     * @Param: [tbUser]
     * @return: void
     * @Author: Mr.Vincent
     * @Date: 2019/5/2
     */
    public BaseResult checkTbUser(TbUser tbUser) {
        BaseResult baseResult = BaseResult.success();

        //非空验证
        //验证Email非空
        if (StringUtils.isBlank(tbUser.getEmail())) {
            baseResult = BaseResult.fail("Email cannot be empty , please re-enter the Email");
        }
        //Email格式不正确
        else if(!RegexpUtils.checkEmail(tbUser.getEmail())){
            baseResult = BaseResult.fail("Email format is incorrect , please enter the correct Email format");
        }
        //验证Password非空
        else if (StringUtils.isBlank(tbUser.getPassword())) {
            baseResult = BaseResult.fail("Password cannot be empty , please re-enter the Password");
        }
        //验证Username非空
        else if (StringUtils.isBlank(tbUser.getUsername())) {
            baseResult = BaseResult.fail("Username cannot be empty , please re-enter the Username");
        }
        //验证Phone非空
        else if (StringUtils.isBlank(tbUser.getPhone())) {
            baseResult = BaseResult.fail("Phone cannot be empty , please re-enter the Phone");
        }
        //Phone格式不正确
        else if(!RegexpUtils.checkPhone(tbUser.getPhone())){
            baseResult = BaseResult.fail("Phone format is incorrect , please enter the correct Phone format");
        }

        return baseResult;
    }
}
