package com.zhangjiang.base;

import com.zhangjiang.base.entity.BaseInfo;
import com.zhangjiang.base.mapper.BaseInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @className Test
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/23 16:04:17
 */
public class Test {

    @Autowired
    BaseInfoMapper baseInfoMapper;

    @org.junit.jupiter.api.Test
    public void test(){
        // 序列化
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setId(01L);
        baseInfo.setId_card("421181");
        baseInfo.setName("zhangjiang");
        baseInfo.setCity("北京");
        baseInfo.setAge("99");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("F:\\baseInfo.txt"));
            objectOutputStream.writeObject(baseInfo);
            objectOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    public void test02(){
        //反序列化
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("F:\\baseInfo.txt"));
            BaseInfo baseInfo = (BaseInfo) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("baseInfo = " + baseInfo);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    public void test3() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        List<BaseInfo> baseInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    // 执行业务逻辑
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    String id = String.valueOf(new Random().nextInt(1000));
                    BaseInfo baseInfo = baseInfoMapper.selectById(id);
                    baseInfoList.add(baseInfo);
                    System.out.println(Thread.currentThread().getName() + " 数据开始");
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        for (BaseInfo baseInfo : baseInfoList) {
            System.out.println("baseInfo = " + baseInfo);
        }
    }
}
