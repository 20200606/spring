package com.zhangjiang.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @className HobbyInfo
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/9 15:09:12
 */
@TableName("hobby_info")
@Data
public class HobbyInfo {

    @TableField("id")
    private Long id;

    @TableField("id_card")
    private String idCard;

    @TableField("hobby")
    private String hobby;

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
