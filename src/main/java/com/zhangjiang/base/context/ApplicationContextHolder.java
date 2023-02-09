package com.zhangjiang.base.context;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @className ApplicationContextHolder
 * @Author zhangjiang
 * @Description:
 * @Date 2022/11/20 12:04:20
 */
@Component
@Scope("singleton")
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {
    private static volatile ApplicationContext applicationContext = null;
    private static Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);

    public ApplicationContextHolder(){

    }

    public static ApplicationContext getContext(){
        assertContextInjected();
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBeanByClassName(String className){
        assertContextInjected();
        Class cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            logger.error("类" + className + "不存在");
            throw new RuntimeException("类" + className + "不存在", e);
        }
        return (T) applicationContext.getBean(cls);
    }

    public static void clearContextHoler(){
        logger.debug("清除SpringContextHolder中的ApplicationContext" + applicationContext);
        applicationContext = null;
    }

    @Override
    public void destroy() throws Exception {
        clearContextHoler();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("注入ApplicationContext到SpringContextHolder:{}", applicationContext);
        if (applicationContext != null) {
            logger.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:" + applicationContext);
        }

        applicationContext = applicationContext;
    }

    private static void assertContextInjected() {
        Validate.validState(applicationContext != null,"applicationContext属性未注入，请在applicationContext.xml中定义contextHoler",new Object[0]);
    }
}
