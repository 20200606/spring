package com.zhangjiang.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjiang.base.entity.BaseInfo;

import java.util.List;

/**
 * @className OrderInfoService
 * @Author zhangjiang
 * @Description:
 * @Date 2022/10/13 13:51:36
 */
public interface BaseInfoService extends IService<BaseInfo> {
    /**
     * 查询所有
     */
    List<BaseInfo> selectAll();

    /**
     * 主键查询
     * @param id
     * @return
     */
    BaseInfo selectForId(String id);

    /**
     * 批量查询数据
     * @return
     */
    int insertRecords();
}
