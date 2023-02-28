package com.zhangjiang.base.controller;

import com.zhangjiang.base.util.fastdfs.FastDfsClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className FastDfsController
 * @Author zhangjiang
 * @Description: FastDfs文件服务器操作
 * @Date 2023/2/20 14:37:24
 */
@RestController
@RequestMapping("/fastdfs")
public class FastDfsController {

    @Autowired
    private FastDfsClientUtils fastDfsClientUtils;


    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile file) throws IOException {
        return fastDfsClientUtils.uploadFileForFastDfs(file);
    }

    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response) throws IOException{
        String url = "http://192.168.6.228:8888/group1/M00/00/00/wKgG5GPzPEeAbaGhAAz6gN4aXb0466.jpg";
        // 分组名
        String groupName = "group1";
        // 相对路径
        String path = "M00/00/00/wKgG5GPzPEeAbaGhAAz6gN4aXb0466.jpg";
        fastDfsClientUtils.downloadFile(groupName, path,response);
    }
}
