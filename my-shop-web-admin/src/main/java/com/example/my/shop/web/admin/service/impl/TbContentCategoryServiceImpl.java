package com.example.my.shop.web.admin.service.impl;

import com.example.my.shop.commons.dto.BaseResult;
import com.example.my.shop.commons.validator.BeanValidator;
import com.example.my.shop.domain.TbContentCategory;
import com.example.my.shop.web.admin.dao.TbContentCategoryMapper;
import com.example.my.shop.web.admin.dao.TbContentMapper;
import com.example.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Resource
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> selectAll() {
        return tbContentCategoryMapper.selectAll();
    }

    @Override
    public List<TbContentCategory> selectByParentId(Long parentId) {
        return tbContentCategoryMapper.selectByParentId(parentId);
    }

    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult deleteByPrimaryKey(Long id) {
        List<TbContentCategory> sourceList = tbContentCategoryMapper.selectByParentId(id);
        //删除目录下的子目录（递归删除）
        sorList(sourceList);
        //根据id删除目录
        tbContentCategoryMapper.deleteByPrimaryKey(id);
        // 根据id = categoryId 删除tb_content的内容
        tbContentMapper.deleteByCategoryId(id);
        return BaseResult.success();
    }

    //递归根据id删数据
    @Transactional(rollbackFor = Exception.class)
    public void sorList(List<TbContentCategory> sourceList) {
        for (TbContentCategory contentCategory : sourceList) {
            if (contentCategory.getIsParent()) {
                List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByParentId(contentCategory.getId());
                sorList(tbContentCategories);
            }
            //System.out.println("!!!!!" + contentCategory.getId());
            //根据id删除目录
            tbContentCategoryMapper.deleteByPrimaryKey(contentCategory.getId());
            // 根据id = categoryId 删除tb_content的内容
            tbContentMapper.deleteByCategoryId(contentCategory.getId());
        }

    }

    @Override
    @Transactional
    public BaseResult save(TbContentCategory entity) {
        String validator = BeanValidator.validator(TbContentCategory.class);
        //验证，当validator不为null时，验证失败，输出validator信息
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        //验证，当validator为null时，验证成功
        else {
            entity.setUpdated(new Date());
            //当id为null时，新增内容类别
            if (entity.getId() == null) {
                //如果isParent=true 或者为null 时，说明自身是父级目录
                if (entity.getParentId() == 0) {
                    //当parentId为0时，说明添加的内容类别是根目录，约定isParent是父级目录
                    entity.setIsParent(true);
                } else {
                    //当parentId不为0时，说明添加的内容类别不是根目录，约定isParent是不父级目录
                    //entity.setIsParent(false);

                    //判断当前目录的父级目录，是否为父节点
                    TbContentCategory currentCategoryParent = tbContentCategoryMapper.selectByPrimaryKey(entity.getParentId());
                    //当不是父节点时
                    if (currentCategoryParent != null) {
                        currentCategoryParent.setIsParent(true);
                        tbContentCategoryMapper.updateByPrimaryKey(currentCategoryParent);
                    }
                    entity.setIsParent(false);
                }
                entity.setCreated(new Date());
                tbContentCategoryMapper.insert(entity);
            }
            //当id不为null时，修改内容类别
            else {
                tbContentCategoryMapper.updateByPrimaryKey(entity);
            }
            return BaseResult.success("Successful saving of user information");
        }
    }

    @Override
    public TbContentCategory selectByPrimaryKey(Long id) {
        return tbContentCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteMulti(String[] ids) {
        return 0;
    }

}
