package com.zhangjiang.base.controller;

import com.zhangjiang.base.Enum.ResultCodeEnum;
import com.zhangjiang.base.common.Result;
import com.zhangjiang.base.entity.BaseInfo;
import com.zhangjiang.base.entity.HobbyInfo;
import com.zhangjiang.base.service.BaseInfoService;
import com.zhangjiang.base.service.HobbyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className BaseInfoController
 * @Author zhangjiang
 * @Description: 基本信息测试
 * @Date 2022/10/13 13:37:49
 */
@RestController
@RequestMapping("/base")
public class BaseInfoController {

    @Autowired
    private BaseInfoService baseInfoService;
    @Autowired
    private HobbyInfoService hobbyInfoService;
    @Autowired
    ApplicationContext applicationContext;

    // 通过xml的查询
    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public List<BaseInfo> queryAll(){
        long start = System.currentTimeMillis();
        List<BaseInfo> baseInfoList = baseInfoService.selectAll();
        long end = System.currentTimeMillis();
        System.out.println("查询数据时间：" + (end - start) + "ms");
        return baseInfoList;
    }

    @PostMapping("/queryById")
    public BaseInfo query01(@RequestBody Map map){
        String id = map.get("id").toString();
        return baseInfoService.selectForId(id);
    }

    @PostMapping("/insertRecords")
    public Result insertRecords(){
        int rows = baseInfoService.insertRecords();
        if (rows > 0) {
            return Result.ok(ResultCodeEnum.SUCCESS);
        }else{
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    @GetMapping("testJUCUtils")
    public List<BaseInfo> testJUCUtils() throws InterruptedException{
        long start = System.currentTimeMillis();
        List<BaseInfo> baseInfoList = baseInfoService.selectById();
        long end = System.currentTimeMillis();
        System.out.println("查询时间" + (end - start) + "ms，查询数据" + baseInfoList.size() + "条");
        return baseInfoList;
    }

    @PostMapping("/insertHobbyRecords")
    public Result insertHobbyRecords() {
        int rows = hobbyInfoService.insertHobbyRecords();
        if (rows > 0) {
            return Result.ok(ResultCodeEnum.SUCCESS);
        }else{
            return Result.fail(ResultCodeEnum.FAIL);
        }
    }

    @RequestMapping(value = "/queryAllHobby", method = RequestMethod.GET)
    public List<HobbyInfo> queryAllHobby(){
        long start = System.currentTimeMillis();
        List<HobbyInfo> hobbyInfoList = hobbyInfoService.queryAllHobby();
        long end = System.currentTimeMillis();
        System.out.println("查询数据时间：" + (end - start) + "ms");
        return hobbyInfoList;
    }

    @GetMapping("/selectUnion")
    public Map selectUnion(){
        Map<Object, Object> map = new HashMap<>();
        long start = System.currentTimeMillis();
        List<HobbyInfo> hobbyInfoList = hobbyInfoService.selectUnion();
        long end = System.currentTimeMillis();
        System.out.println("查询时间：" + (end-start) + "ms");
        map.put("data", hobbyInfoList);
        return map;
    }

    @PostMapping("/redisLock")
    public String testRedisLock(){
        return baseInfoService.testRedisLock();
    }

    @GetMapping("/getFromRedis")
    public Map getFromRedis(){
        return baseInfoService.getFromRedis();
    }

    @PostMapping("/transferJson")
    public String transferJson(){
        return hobbyInfoService.transferJson();
    }

    @PostMapping("/testMapApi")
    public Map<String, Integer> testMapApi(){

        return baseInfoService.testMapApi();
    }
}
