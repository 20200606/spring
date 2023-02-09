package com.zhangjiang.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjiang.base.entity.HobbyInfo;
import com.zhangjiang.base.mapper.HobbyInfoMapper;
import com.zhangjiang.base.service.HobbyInfoService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @className HobbyInfoServiceImpl
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/9 15:17:29
 */
@Service
public class HobbyInfoServiceImpl extends ServiceImpl<HobbyInfoMapper,HobbyInfo> implements HobbyInfoService {

    @Override
    public int insertHobbyRecords() {
        int count = 0;
        String[] strings = new String[4];
        strings[0] = "足球";
        strings[1] = "篮球";
        strings[2] = "羽毛球";
        strings[3] = "乒乓球";
        for (int i = 0; i < 100000; i++) {
            HobbyInfo hobbyInfo = new HobbyInfo();
            hobbyInfo.setId(Long.valueOf(i + 1));
            hobbyInfo.setIdCard("421181" + i);
            hobbyInfo.setVersion("1");
            hobbyInfo.setHobby(strings[i % 4]);
            hobbyInfo.setCreateBy("system");
            hobbyInfo.setCreateTime(new Date());
            hobbyInfo.setUpdateBy("system");
            hobbyInfo.setUpdateTime(new Date());
            baseMapper.insert(hobbyInfo);
            count++;
        }
        if (count > 100000) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public List<HobbyInfo> selectUnion() {
        return baseMapper.selectUnion();
    }

    @Override
    public List<HobbyInfo> queryAllHobby() {
        return baseMapper.selectAll();
    }
}
