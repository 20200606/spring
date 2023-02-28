package com.zhangjiang.base.entity;

import com.zhangjiang.base.util.excel.ExcelImport;
import lombok.Data;

/**
 * @className User
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/20 10:07:54
 */
@Data
public class User {

    private int rowNum;

    @ExcelImport("姓名")
    private String userName;

    @ExcelImport("年龄")
    private Integer age;

    @ExcelImport(value = "性别",kv = "1-男；2-女")
    private Integer sex;

    @ExcelImport("电话")
    private String telNumber;

    @ExcelImport("城市")
    private String city;

    @ExcelImport("头像")
    private String avatar;
    /** 错误提示*/
    private String rowTips;
    /** 原始数据*/
    private String rowData;

}
