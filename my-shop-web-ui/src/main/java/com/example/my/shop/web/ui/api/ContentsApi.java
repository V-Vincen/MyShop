package com.example.my.shop.web.ui.api;

import com.example.my.shop.commons.utils.HttpClientUtils;
import com.example.my.shop.commons.utils.JacksonUtils;
import com.example.my.shop.web.ui.dto.TbConent;

import java.util.List;

public class ContentsApi {

    public static List<TbConent> ppt() {
        String result = HttpClientUtils.doGet(API.API_CONTENTS + "89");
        List<TbConent> tbConents = null;
        try {

            tbConents = JacksonUtils.json2listByTree(result, "data", TbConent.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbConents;
    }

}
