package com.example.my.shop.web.admin.dao;

import com.example.my.shop.commons.persistence.BaseMapper;
import com.example.my.shop.domain.TbContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TbContentMapper extends BaseMapper<TbContent> {

    List<TbContent> selectAll(Map<String,Object> map);

    int deleteByCategoryId(@Param("categoryId") Long categoryId);
}