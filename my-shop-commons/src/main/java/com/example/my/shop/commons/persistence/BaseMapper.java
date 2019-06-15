package com.example.my.shop.commons.persistence;

public interface BaseMapper<T> {

    /**
     * @Method:        deleteByPrimaryKey
     * @Description:    根据id删除数据
     * @Param:         [id]
     * @return:        int
     * @Author:        Mr.Vincent
     * @Date:          2019/6/6
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Method:        insert
     * @Description:    新增
     * @Param:         [entity]
     * @return:        int
     * @Author:        Mr.Vincent
     * @Date:          2019/6/6
     */
    int insert(T entity);

    /**
     * @Method:        selectByPrimaryKey
     * @Description:    根据id查询数据
     * @Param:         [id]
     * @return:        T
     * @Author:        Mr.Vincent
     * @Date:          2019/6/6
     */
    T selectByPrimaryKey(Long id);

    /**
     * @Method:        updateByPrimaryKey
     * @Description:    修改
     * @Param:         [entity]
     * @return:        int
     * @Author:        Mr.Vincent
     * @Date:          2019/6/6
     */
    int updateByPrimaryKey(T entity);

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
