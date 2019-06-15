package com.example.my.shop.commons.utils;

 /**
  * @ProjectName:
  * @Package:        com.example.my.shop.commons.utils
  * @ClassName:      RegexpUtils
  * @Description:    正则表达式工具类
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/5/2 20:00
  * @Version:        1.0.0
  */
public class RegexpUtils {
    /**
     * 验证手机号
     */
    public static final String PHONE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 验证邮箱地址
     */
    public static final String EMAIL = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}";

    /**
     * 验证手机号
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        return phone.matches(PHONE);
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        return email.matches(EMAIL);
    }
}