package com.example.my.shop.commons.persistence;

import com.example.my.shop.commons.dto.BaseResult;

public interface BaseService<T> {

    /**
     * @Method:        deleteByPrimaryKey
     * @Description:    根据id删除数据
     * @Param:         [id]
     * @return:        int
     * @Author:        Mr.Vincent
     * @Date:          2019/6/6
     */
    BaseResult deleteByPrimaryKey(Long id);

    /**
     * @Method:        insert
     * @Description:    添加或修改
     * @Param:         [entity]
     * @return:        int
     * @Author:        Mr.Vincent
     * @Date:          2019/6/6
     */
    BaseResult save(T entity);

    /**
     * @Method:        selectByPrimaryKey
     * @Description:     根据id查询数据
     * @Param:         [id]
     * @return:        T
     * @Author:        Mr.Vincent
     * @Date:          2019/6/6
     */
    T selectByPrimaryKey(Long id);


    /**
     * @Method:        deleteMulti
     * @Description:    批量删除
     * @Param:         [ids]
     * @return:        int
     * @Author:        Mr.Vincent
     * @Date:          2019/6/6
     */
    int deleteMulti(String[] ids);

}
