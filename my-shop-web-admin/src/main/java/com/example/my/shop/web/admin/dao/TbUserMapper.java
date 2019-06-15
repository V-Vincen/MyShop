package com.example.my.shop.web.admin.dao;

import com.example.my.shop.domain.TbUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName:
 * @Package: com.example.my.shop.web.admin.dao
 * @ClassName: TbUserMapper
 * @Description:
 * @Author: Mr.Vincent
 * @CreateDate: 2019/4/28 9:52
 * @Version: 1.0.0
 */

public interface TbUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbUser record);

    TbUser selectByPrimaryKey(Long id);

    int updateByPrimaryKey(TbUser record);

    TbUser getByEmail(@Param("email") String email);

    int deleteMulti(String[] ids);

    List<TbUser> page(Map<String,Object> map);

    int count(Map<String,Object> map);

}








