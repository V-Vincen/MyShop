package com.example.my.shop.domain;

import com.example.my.shop.commons.persistence.BaseEntity;
import com.example.my.shop.commons.utils.RegexpUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
public class TbUser extends BaseEntity {

    @Length(min = 6, max = 20, message = "Username 长度必须介于 6 和 20 之间")
    private String username;

    @JsonIgnore
    @Length(min = 6, max = 20, message = "Password 长度必须介于 6 和 20 之间")
    private String password;

    @Pattern(regexp = RegexpUtils.PHONE, message = "Phone 格式不正确")
    private String phone;

    @Pattern(regexp = RegexpUtils.EMAIL, message = "Email 格式不正确")
    private String email;


}