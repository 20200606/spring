package com.zhangjiang.base.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhangjiang.base.entity.User;
import com.zhangjiang.base.util.excel.ExcelUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @className ExcelController
 * @Author zhangjiang
 * @Description: Excel转换测试
 * @Date 2023/2/19 15:56:57
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @PostMapping("/import")
    public void importFile(@RequestPart("file") MultipartFile multipartFile) throws Exception{
        JSONArray jsonArray = ExcelUtils.readMultipartFile(multipartFile);
        System.out.println("jsonArray = " + jsonArray);
    }

    @PostMapping("/importForClass")
    public List<User> importForClass(@RequestPart("file") MultipartFile multipartFile) throws Exception{
        List<User> users = ExcelUtils.readMultipartFile(multipartFile, User.class);
        for (User user : users) {
            System.out.println("user = " + user);
        }
        return users;
    }

    @PostMapping("/importManySheet")
    public Map importManySheet(@RequestPart("file") MultipartFile multipartFile) throws Exception{
        Map<String, JSONArray> stringJSONArrayMap = ExcelUtils.readFileManySheet(multipartFile);
        stringJSONArrayMap.forEach((key,value)->{
            System.out.println("sheet名称： " + key);
            System.out.println("-----------------------------");
            System.out.println("sheet数据：" + value);
            System.out.println("-----------------------------");
        });
        return stringJSONArrayMap;
    }

}
