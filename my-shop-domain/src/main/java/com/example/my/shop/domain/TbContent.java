package com.example.my.shop.domain;

import com.example.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class TbContent extends BaseEntity {

    private Long id;

    @NotNull(message = "父级类目不能为空")
    private Long categoryId;

    @Length(min = 1, max = 20, message = "标题长度介于 1 - 20 个字符之间")
    private String title;

    @Length(min = 1, max = 20, message = "子标题长度介于 1 - 20 个字符之间")
    private String subTitle;


    @Length(min = 1, max = 50, message = "标题描述长度介于 1 - 50 个字符之间")
    private String titleDesc;

    //链接
    private String url;

    //图片绝对路径
    private String pic;

    //图片2
    private String pic2;

    @Length(min = 1, message = "内容不可为空")
    private String content;

    //@NotNull(message = "父级类目不能为空")
    private TbContentCategory tbContentCategory;


}