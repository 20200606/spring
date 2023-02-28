package com.zhangjiang.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjiang.base.entity.BaseInfo;
import com.zhangjiang.base.lock.DistributeLock;
import com.zhangjiang.base.lock.LockResultDTO;
import com.zhangjiang.base.mapper.BaseInfoMapper;
import com.zhangjiang.base.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CyclicBarrier;

/**
 * @className OrderInfoServiceImpl
 * @Author zhangjiang
 * @Description:
 * @Date 2022/10/13 13:51:59
 */
@Service
public class BaseInfoServiceImpl extends ServiceImpl<BaseInfoMapper, BaseInfo> implements BaseInfoService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    DistributeLock distributeLock;

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

    @Override
    public String testRedisLock() {
        LockResultDTO lockResultDTO = distributeLock.consumerByLock(() -> {
            String lockKey = "zhangjiang:" + new Date();
            return lockKey;
        }, () -> {
            BaseInfo baseInfo = baseMapper.selectById(26);
            return "sucess";
        });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (LockResultDTO.SUCCESS.equals(lockResultDTO.getCode())) {
            return "success";
        } else if (LockResultDTO.PROCESS.equals(lockResultDTO.getCode())) {
            return "process";
        }else {
            return "fail";
        }
    }

    @Override
    public Map getFromRedis() {
        Map<String, Object> map = new HashMap<>();
        String value = stringRedisTemplate.opsForValue().get("zhangjiang");
        map.put("01", value);
        return map;
    }

    @Override
    public List<BaseInfo> selectById() throws InterruptedException{
        List<BaseInfo> baseInfoList = new ArrayList<>();

        // CountDownLatch
        /*CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            try {
                // 执行业务逻辑
                for (int i1 = 0; i1 < 200; i1++) {
                    String id = String.valueOf(new Random().nextInt(1000));
                    BaseInfo baseInfo = baseMapper.selectById(id);
                    baseInfoList.add(baseInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
        }
        countDownLatch.await();
        System.out.println("--------------数据查询完毕----------------");
        System.out.println("baseInfoList：：：" + baseInfoList.get(0));
        System.out.println("baseInfoList长度：：：" + baseInfoList.size());*/


        // CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("--------------数据查询完毕----------------");
                System.out.println("baseInfoList：：：" + baseInfoList.get(0));
                System.out.println("baseInfoList长度：：：" + baseInfoList.size());
            }
        });
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(300);
                    String id = String.valueOf(new Random().nextInt(5));
                    BaseInfo baseInfo = baseMapper.selectById(id);
                    baseInfoList.add(baseInfo);
                    cyclicBarrier.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(300);
                    String id = String.valueOf(new Random().nextInt(20));
                    BaseInfo baseInfo = baseMapper.selectById(id);
                    baseInfoList.add(baseInfo);
                    cyclicBarrier.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }

        // Semaphore
        /*Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < 10; i++) {
            semaphore.acquire();
            System.out.println("semaphore获取线程，信号量减1，semaphore.availablePermits()::::" + semaphore.availablePermits());
            Thread.sleep(300);
            String id = String.valueOf(new Random().nextInt(10));
            BaseInfo baseInfo = baseMapper.selectById(id);
            baseInfoList.add(baseInfo);
            semaphore.release();
            System.out.println("semaphore释放线程，信号量加1，semaphore.availablePermits()::::" + semaphore.availablePermits());
        }*/
        return baseInfoList;
    }

    @Override
    public Map<String, Integer> testMapApi() {
        List<BaseInfo> baseInfoList = Arrays.asList(
                new BaseInfo("张三", "23"),
                new BaseInfo("张三", "24"),
                new BaseInfo("张三", "25"),
                new BaseInfo("李四", "26"),
                new BaseInfo("李四", "27"),
                new BaseInfo("李四", "28"),
                new BaseInfo("王五","34"),
                new BaseInfo("王五","56"),
                new BaseInfo("王五","78"));
        // 普通的Map累加
        Map<String, Integer> stringMap = new HashMap<>();
        baseInfoList.forEach(baseInfo -> {
            if (stringMap.containsKey(baseInfo.getName())) {
                stringMap.put(baseInfo.getName(), stringMap.get(baseInfo.getName()) + Integer.valueOf(baseInfo.getAge()));
            }else {
                stringMap.put(baseInfo.getName(), Integer.valueOf(baseInfo.getAge()));
            }
        });
        // map的累加，先判断Key是否存在，不在则加入Key
        Map<String, Integer> stringIntegerHashMap = new HashMap<>();
        baseInfoList.forEach(baseInfo ->
                stringIntegerHashMap.merge(
                        baseInfo.getName(),
                        Integer.valueOf(baseInfo.getAge()),
                        (oldvalue,newValue)-> Integer.valueOf(oldvalue) + Integer.valueOf(newValue)));
        Map<Object, Object> map = new HashMap<>();
        map.put(1, 1);
        return stringIntegerHashMap;
    }

    public static void main(String[] args) {
        int i = 5 << 1;
        System.out.println("i = " + i);
        Integer e = 1;
        e.toString();
    }
}
