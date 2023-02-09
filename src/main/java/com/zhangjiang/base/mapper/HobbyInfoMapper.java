package com.zhangjiang.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjiang.base.entity.HobbyInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className HobbyInfoMapper
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/9 15:13:49
 */
@Mapper
public interface HobbyInfoMapper extends BaseMapper<HobbyInfo> {

    /**
     * 查询所有
     */
    List<HobbyInfo> selectAll();

    /**
     * 联合查询
     * @return
     */
    List<HobbyInfo> selectUnion();
}
