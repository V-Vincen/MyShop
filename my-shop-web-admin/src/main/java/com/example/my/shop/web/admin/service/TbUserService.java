package com.example.my.shop.web.admin.service;

import com.example.my.shop.commons.dto.BaseResult;
import com.example.my.shop.commons.dto.PageInfo;
import com.example.my.shop.domain.TbUser;

public interface TbUserService {

    /**
     * @Method:        login
     * @Description:    登录
     * @Param:         [email]
     * @return:        com.example.my.shop.domain.User
     * @Author:        Mr.Vincent
     * @Date:          2019/4/30
     */
    public TbUser login(String email, String password);

    /**
     * @Method:        save
     * @Description:    保存用户
     * @Param:         [tbUser]
     * @return:        void
     * @Author:        Mr.Vincent
     * @Date:          2019/5/2
     */
    public BaseResult save(TbUser tbUser);

    /**
     * @Method:        selectByPrimaryKey
     * @Description:    根据id查询用户信息
     * @Param:         [id]
     * @return:        com.example.my.shop.domain.TbUser
     * @Author:        Mr.Vincent
     * @Date:          2019/5/2
     */
    public TbUser selectByPrimaryKey(Long id);

    /**
     * @Method:        deleteMulti
     * @Description:    批量删除
     * @Param:         [ids]
     * @return:        void
     * @Author:        Mr.Vincent
     * @Date:          2019/5/4
     */
    public int deleteMulti(String[] ids);

    /**
     * @Method:        page
     * @Description:    分页
     * @Param:         [map]
     * @return:        java.util.List<com.example.my.shop.domain.TbUser>
     * @Author:        Mr.Vincent
     * @Date:          2019/5/6
     */
    public PageInfo<TbUser> page(int start,int length,int draw,TbUser tbUser,String likeSearch);

    /**
     * @Method:        deleteByPrimaryKey
     * @Description:    根据id删除
     * @Param:         [id]
     * @return:        int
     * @Author:        Mr.Vincent
     * @Date:          2019/5/7
     */
    BaseResult deleteByPrimaryKey(Long id);
}
