package com.example.my.shop.commons.dto;

import java.io.Serializable;

 /**
  * @ProjectName:
  * @Package:        com.example.my.shop.commons.dto
  * @ClassName:      BaseResult
  * @Description:    自定义返回结果
  * @Author:         Mr.Vincent
  * @CreateDate:     2019/5/2 17:10
  * @Version:        1.0.0
  */
public class BaseResult implements Serializable {
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_FAIL = 500;

    private int status;
    private String message;

    public static BaseResult success(){
        return BaseResult.createResult(STATUS_SUCCESS,"Success");
    }

    public static BaseResult success(String message){
        return BaseResult.createResult(STATUS_SUCCESS,message);
    }

    public static BaseResult fail(){
        return BaseResult.createResult(STATUS_FAIL,"Fail");
    }

     public static BaseResult fail(String message){
         return BaseResult.createResult(STATUS_FAIL,message);
     }

     public static BaseResult fail(int status,String message){
         return BaseResult.createResult(status,message);
     }

     public int getStatus() {
         return status;
     }

     public void setStatus(int status) {
         this.status = status;
     }

     public String getMessage() {
         return message;
     }

     public void setMessage(String message) {
         this.message = message;
     }

     private static BaseResult createResult(int status,String message){
         BaseResult baseResult = new BaseResult();
         baseResult.setStatus(status);
         baseResult.setMessage(message);
         return baseResult;
     }
 }
