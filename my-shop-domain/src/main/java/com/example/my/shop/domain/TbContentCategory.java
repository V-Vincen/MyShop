package com.example.my.shop.domain;

import com.example.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class TbContentCategory extends BaseEntity {

    private Long id;

    @Length(min = 1, max = 20, message = "分类名称必须介于1-20位之间")
    private Long parentId;

    //分类名称
    private String name;

    //状态。可选值:1(正常),2(删除)
    private Integer status;

    //排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;

    //该类目是否为父类目，1为true，0为false
    private Boolean isParent;

    private TbContentCategory parent;
}