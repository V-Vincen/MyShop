package com.example.my.shop.web.admin.service;

import com.example.my.shop.commons.persistence.BaseService;
import com.example.my.shop.domain.TbContentCategory;

import java.util.List;

public interface TbContentCategoryService extends BaseService<TbContentCategory> {

    List<TbContentCategory> selectAll();

    List<TbContentCategory> selectByParentId(Long parentId);

}
