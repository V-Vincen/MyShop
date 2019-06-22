package com.example.my.shop.web.api.service.impl;

import com.example.my.shop.domain.TbContent;
import com.example.my.shop.domain.TbContentCategory;
import com.example.my.shop.web.api.dao.TbContentMapper;
import com.example.my.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public List<TbContent> selectByCategoryId(Long categoryId) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(categoryId);
        TbContent tbContent = new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);
        return tbContentMapper.selectByCategoryId(tbContent);
    }
}
