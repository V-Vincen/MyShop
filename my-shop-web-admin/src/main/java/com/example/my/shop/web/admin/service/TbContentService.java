package com.example.my.shop.web.admin.service;

import com.example.my.shop.commons.persistence.BaseService;
import com.example.my.shop.domain.TbContent;

import java.util.Map;

public interface TbContentService extends BaseService<TbContent> {

    Map<String, Object> selectAll(Map<String, Object> condition, int page, int row);

}
