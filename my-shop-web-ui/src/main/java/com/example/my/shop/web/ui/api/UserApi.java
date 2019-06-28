package com.example.my.shop.web.ui.api;

import com.example.my.shop.commons.dto.BaseResult;
import com.example.my.shop.commons.utils.HttpClientUtils;
import com.example.my.shop.commons.utils.JacksonUtils;
import com.example.my.shop.web.ui.dto.TbUser;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class UserApi {

    public static BaseResult register(TbUser tbUser) throws Exception {
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username",tbUser.getEmail()));
        params.add(new BasicNameValuePair("password",tbUser.getPassword()));

        String json = HttpClientUtils.doPost(String.format("%s/register",API.API_USERS), params.toArray(new BasicNameValuePair[params.size()]));
        BaseResult baseResult = JacksonUtils.json2pojo(json, BaseResult.class);
        return baseResult;
    }
}
