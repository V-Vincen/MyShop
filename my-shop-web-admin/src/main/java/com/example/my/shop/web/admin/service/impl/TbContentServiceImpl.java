package com.example.my.shop.web.admin.service.impl;

import com.example.my.shop.commons.dto.BaseResult;
import com.example.my.shop.commons.validator.BeanValidator;
import com.example.my.shop.domain.TbContent;
import com.example.my.shop.web.admin.dao.TbContentMapper;
import com.example.my.shop.web.admin.service.TbContentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbContentServiceImpl implements TbContentService {

    @Resource
    private TbContentMapper tbContentMapper;

    @Override
    public BaseResult deleteByPrimaryKey(Long id) {
        BaseResult baseResult = BaseResult.fail();
        int result = tbContentMapper.deleteByPrimaryKey(id);
        if(result>0){
            baseResult = BaseResult.success();
        }
        return baseResult;
    }

    @Override
    public TbContent selectByPrimaryKey(Long id) {
        return tbContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteMulti(String[] ids) {
        return tbContentMapper.deleteMulti(ids);
    }

    @Override
    public Map<String, Object> selectAll(Map<String, Object> condition, int page, int row) {
        Map<String, Object> map = new HashMap<>();

        PageHelper.startPage(page, row);
        List<TbContent> tbContents = tbContentMapper.selectAll(condition);
        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);

        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());

        return map;
    }

    @Override
    @Transactional
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        //验证通过
        else {
            tbContent.setUpdated(new Date());
            //新增用户
            if (tbContent.getId() == null) {
                //密码加密
                tbContent.setCreated(new Date());
                tbContentMapper.insert(tbContent);
            }
            //编辑用户
            else {
                tbContentMapper.updateByPrimaryKey(tbContent);
            }
            return BaseResult.success("Successful saving of user information");
        }
    }

}
