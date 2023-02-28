package com.zhangjiang.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjiang.base.entity.BaseInfo;

import java.util.List;
import java.util.Map;

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

    /**
     * 测试redis加锁
     * @return
     */
    String testRedisLock();

    /**
     * redis中获取值
     * @return
     */
    Map getFromRedis();

    /**
     * 测试JUC辅助工具类：
     * @return
     */
    List<BaseInfo> selectById() throws InterruptedException;

    /**
     * 测试Map中的Api：累加merge方法
     * @return
     */
    Map<String, Integer> testMapApi();
}
