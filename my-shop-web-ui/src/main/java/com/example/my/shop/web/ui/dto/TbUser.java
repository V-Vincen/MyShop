package com.example.my.shop.web.ui.dto;

import com.example.my.shop.commons.persistence.BaseEntity;
import lombok.Data;

@Data
public class TbUser extends BaseEntity {

    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String verification;


}