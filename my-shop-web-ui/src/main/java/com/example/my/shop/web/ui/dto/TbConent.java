package com.example.my.shop.web.ui.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TbConent implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;
    private String content;
}
