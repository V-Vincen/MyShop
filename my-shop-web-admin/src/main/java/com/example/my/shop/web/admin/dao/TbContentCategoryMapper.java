package com.example.my.shop.web.admin.dao;
import com.example.my.shop.commons.persistence.BaseMapper;
import com.example.my.shop.domain.TbContentCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbContentCategoryMapper extends BaseMapper<TbContentCategory> {

    List<TbContentCategory> selectAll();

    List<TbContentCategory> selectByParentId(@Param("parentId")Long parentId);

}