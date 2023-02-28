package com.zhangjiang.base.interfaceTest;

/**
 * @className InterFacefather
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/25 11:07:30
 */
public interface Interfacefather {

    /**
     * 公共的静态常量
     */
    int MAX_VALUE = 1234344;

    /**
     * 公共的抽象方法
     * @param string
     * @return
     */
    public abstract String Test(String string);

    /**
     * 公共的静态方法
     * @return
     */
    public static String Test02(){
        return new String("返回数据");
    }

    /**
     * 公共的默认方法
     * @return
     */
    default String test03(){
        return new String("返回数据");
    }

}
