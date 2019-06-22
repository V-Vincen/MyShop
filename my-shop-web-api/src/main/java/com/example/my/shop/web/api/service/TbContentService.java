package com.example.my.shop.web.api.service;

import com.example.my.shop.domain.TbContent;

import java.util.List;

public interface TbContentService {

    /**
     * @Method: selectByCategoryId
     * @Description: 根据类别id查询内容列表
     * @Param: [tbContent]
     * @return: java.util.List<com.example.my.shop.domain.TbContent>
     * @Author: Mr.Vincent
     * @Date: 2019/6/16
     */
    List<TbContent> selectByCategoryId(Long categoryId);
}

