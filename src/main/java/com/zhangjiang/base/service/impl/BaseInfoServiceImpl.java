package com.zhangjiang.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjiang.base.entity.BaseInfo;
import com.zhangjiang.base.mapper.BaseInfoMapper;
import com.zhangjiang.base.service.BaseInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @className OrderInfoServiceImpl
 * @Author zhangjiang
 * @Description:
 * @Date 2022/10/13 13:51:59
 */
@Service
public class BaseInfoServiceImpl extends ServiceImpl<BaseInfoMapper, BaseInfo> implements BaseInfoService {

    @Override
    public List<BaseInfo> selectAll() {
        List<BaseInfo> orderInfoList = baseMapper.selectAll();
        return orderInfoList;
    }

    @Override
    public BaseInfo selectForId(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public int insertRecords() {
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            BaseInfo baseInfo = new BaseInfo();
            baseInfo.setId(Long.valueOf(i + 1));
            baseInfo.setAge(String.valueOf(i));
            baseInfo.setCity("武汉" + i);
            baseInfo.setId_card("421181" + i);
            baseInfo.setName("张三" + i);
            baseInfo.setVersion("1");
            baseInfo.setCreateBy("system");
            baseInfo.setCreateTime(new Date());
            baseInfo.setUpdateBy("system");
            baseInfo.setUpdateTime(new Date());
            baseMapper.insert(baseInfo);
            count++;
        }
        if (count > 1000) {
            return 0;
        } else {
            return 1;
        }
    }
}
