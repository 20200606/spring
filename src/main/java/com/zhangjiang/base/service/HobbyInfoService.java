package com.zhangjiang.base.service;

import com.zhangjiang.base.entity.HobbyInfo;

import java.util.List;

/**
 * @className HobbyInfoService
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/9 15:16:19
 */
public interface HobbyInfoService {

    /**
     * 批量插入数据
     * @return
     */
    int insertHobbyRecords();

    /**
     * 联合查询
     * @return
     */
    List<HobbyInfo> selectUnion();

    /**
     * 查询所有
     * @return
     */
    List<HobbyInfo> queryAllHobby();

    /**
     * 转换Json
     * @return
     */
    String transferJson();
}
