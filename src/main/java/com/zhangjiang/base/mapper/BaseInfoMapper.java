package com.zhangjiang.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangjiang.base.entity.BaseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @className OrderInfoMapper
 * @Author zhangjiang
 * @Description:
 * @Date 2022/10/13 13:53:29
 */
@Mapper
public interface BaseInfoMapper extends BaseMapper<BaseInfo> {

    /**
     * 查询所有
     */
    List<BaseInfo> selectAll();
}
