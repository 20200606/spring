package com.zhangjiang.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @className BaseInfo
 * @Author zhangjiang
 * @Description:
 * @Date 2022/10/13 13:54:31
 */
@TableName("base_info")
@Data
public class BaseInfo {
    @TableField("id")
    private Long id;

    @TableField("id_card")
    private String id_card;

    @TableField("name")
    private String name;

    @TableField("age")
    private String age;

    @TableField("city")
    private String city;

    @TableField("version")
    private String version;

    @TableField("create_by")
    private String createBy;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("update_by")
    private String updateBy;

    @TableField("UPDATE_TIME")
    private Date updateTime;
}
