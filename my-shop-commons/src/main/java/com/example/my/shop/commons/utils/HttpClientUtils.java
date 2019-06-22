package com.example.my.shop.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * @ProjectName:
 * @Package: com.example.my.shop.commons.utils
 * @ClassName: HttpClientUtils
 * @Description: HttpClientUtils工具类
 * @Author: Mr.Vincent
 * @CreateDate: 2019/6/21 13:44
 * @Version: 1.0.0
 */
public class HttpClientUtils {

    public static final String GET = "get";
    public static final String POST = "post";

    public static final String REQUEST_HEARDER_CONNECTION = "keep-alive";
    public static final String REQUEST_HEARDER_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36";


    /**
     * @Method: doGet
     * @Description: get请求
     * @Param: [url]
     * @return: java.lang.String
     * @Author: Mr.Vincent
     * @Date: 2019/6/22
     */
    public static String doGet(String url) {
        return HttpClientUtils.createRequset(url, GET, null);
    }

    /**
     * @Method: doGet
     * @Description: get请求, 带Cookie
     * @Param: [url, Cookie]
     * @return: java.lang.String
     * @Author: Mr.Vincent
     * @Date: 2019/6/22
     */
    public static String doGet(String url, String Cookie) {
        return HttpClientUtils.createRequset(url, GET, Cookie);
    }

    /**
     * @Method: doPost
     * @Description: post请求
     * @Param: [url, params]
     * @return: java.lang.String
     * @Author: Mr.Vincent
     * @Date: 2019/6/22
     */
    public static String doPost(String url, BasicNameValuePair... params) {
        return HttpClientUtils.createRequset(url, POST, null, params);
    }

    /**
     * @Method: doPost
     * @Description: post请求, 带Cookie
     * @Param: [url, Cookie, params]
     * @return: java.lang.String
     * @Author: Mr.Vincent
     * @Date: 2019/6/22
     */
    public static String doPost(String url, String Cookie, BasicNameValuePair... params) {
        return HttpClientUtils.createRequset(url, POST, Cookie, params);
    }


    private static String createRequset(String url, String requsetMethod, String Cookie, BasicNameValuePair... params) {
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //请求方式
        HttpGet httpGet = null;
        HttpPost httpPost = null;

        //响应
        CloseableHttpResponse httpResponse = null;

        //响应结果
        String result = null;
        try {


            //判断如果是get请求
            if (GET.equals(requsetMethod)) {
                httpGet = new HttpGet(url);
                httpGet.setHeader("Connection", REQUEST_HEARDER_CONNECTION);
                httpGet.setHeader("User_Agent", REQUEST_HEARDER_USER_AGENT);
                httpGet.setHeader("Cookie", Cookie);

                //httpClient执行get请求
                httpResponse = httpClient.execute(httpGet);
            }

            //判断如果是post请求
            else if (POST.equals(requsetMethod)) {
                httpPost = new HttpPost(url);
                httpPost.setHeader("Connection", REQUEST_HEARDER_CONNECTION);
                httpPost.setHeader("User_Agent", REQUEST_HEARDER_USER_AGENT);
                httpPost.setHeader("Cookie", Cookie);

                if (params != null && params.length > 0) {
                    httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params), "UTF-8"));
                }

                //httpClient执行post请求
                httpResponse = httpClient.execute(httpPost);
            }

            //获取响应内容（json）
            HttpEntity httpEntity = httpResponse.getEntity();

            //将响应结果转换成String类型
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }

                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

}
