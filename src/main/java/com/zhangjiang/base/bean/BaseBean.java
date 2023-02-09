package com.zhangjiang.base.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @className BaseBean
 * @Author zhangjiang
 * @Description:
 * @Date 2022/11/20 12:44:37
 */
@Component
public class BaseBean {

    @Bean
    public static void firstStep(){
        System.out.println("第一步" + true);
    }

    @Bean
    public static void secondStep(){
        System.out.println("第二步" + true);
    }

    @Bean
    public static void thirdStep(){
        System.out.println("第三步" + true);
    }
}
