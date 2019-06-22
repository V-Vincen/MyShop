package com.example.my.shop.web.api.web.dto;

import com.example.my.shop.commons.persistence.BaseEntity;
import com.example.my.shop.commons.utils.RegexpUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class TbUser extends BaseEntity {

    private String username;

    @JsonIgnore
    private String password;

    private String phone;

    private String email;


}