package com.example.my.shop.web.admin.web.conttroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadController {

    private static final String UPLOAD_PATH = "/static/upload/";

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropzFile, MultipartFile editorFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();

        MultipartFile myFile = dropzFile == null ? editorFile : dropzFile;

        // 获取上传的原始文件名
        String fileName = myFile.getOriginalFilename();

        //文件存放路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

        //截取文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());

        //文件存放路径
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        //将文件写入目标目录
        file = new File(filePath, UUID.randomUUID() + fileSuffix);
        try {
            //把图片写入文件夹中
            myFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //scheme:服务端提供的协议 http/https
        //serverName:服务器名称 localhost/ip/domain
        //serverPort:服务器端口号
        String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        //dropzone插件图片上传
        if (dropzFile != null) {
            // 返回 JSON 数据，这里只带入了文件名
            result.put("fileName", serverPath + UPLOAD_PATH + file.getName());
        }
        //wangEditor插件图片上传
        else {
            result.put("errno", 0);
            result.put("data", new String[]{serverPath + UPLOAD_PATH + file.getName()});
        }
        return result;
    }

}
