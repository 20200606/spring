package com.zhangjiang.base.util.enumutil;

import com.zhangjiang.base.common.EnumEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className EnumUtils
 * @Author zhangjiang
 * @Description:
 * @Date 2022/11/5 21:30:17
 */
public class EnumUtils {

    private static Logger log = LoggerFactory.getLogger(EnumUtils.class);

    /**
     * 枚举转换 Map
     * @param enumT
     * @param methodsName
     * @param <T>
     * @return
     */
    public static <T> Map<String,String> enumToMap(Class<T> enumT,String...methodsName){
        Map<String, String> enumMap = new HashMap<String, String>();
        // 判断是否是枚举
        if (!enumT.isEnum()) {
            return enumMap;
        }
        // 获取枚举中的实例
        T enumConstants[]  = enumT.getEnumConstants();
        // 获取转换中的方法
        int count = methodsName.length;
        String keyMethod = "";
        String valueMethod = "";
        if (count >= 1 && !"".equals(methodsName[0])) {
            keyMethod = methodsName[0];
        }
        if (count == 2 && !"".equals(methodsName[1])) {
            valueMethod = methodsName[1];
        }
        for (int i = 0; i < enumConstants.length; i++) {
            T enumConstant = enumConstants[i];
            try {
                /**获取key值*/
                Object resultkey = getMethodValue(keyMethod, enumConstant);
                if ("".equals(resultkey)) {
                    continue;
                }
                /**获取value值*/
                Object resultValue = getMethodValue(valueMethod, enumConstant);
                /**如果描述不存在获取整条enum*/
                if ("".equals(resultValue)) {
                    resultValue = enumConstant;
                }
                enumMap.put(resultkey+"", resultValue+"");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("枚举类转成Map异常",e);
            }
        }
        return enumMap;
    }

    /**
     * 枚举转换 List
     * @param enumT
     * @param methodNames
     * @param <T>
     * @return
     */
    public static <T> List<EnumEntity> getEnumToList(Class<T> enumT, String... methodNames) {
        List<EnumEntity> enumList = new ArrayList<>(); //最终返回的list  enumEntity是对枚举类的封装实体
        if (!enumT.isEnum()) {
            return enumList;
        }
        T enums[] = enumT.getEnumConstants();  //得么枚举类里所有的枚举类
        if (enums.length == 0) {  //如果没有枚举键和值   结束
            return enumList;
        }
        int count = methodNames.length;
        String keyMethod = "";  //默认的取 key的方法
        String valueMethod = "";  //默认的取 value 的方法
        if (count >= 1 && !methodNames[0].equals("")) { //如果方法的长度是大于等于1的,并且不为空
            keyMethod = methodNames[0];
        }
        if (count == 2 && !methodNames[1].equals("")) { //如果方法的长度是等于2的,并且不为空
            valueMethod = methodNames[1];
        }
        try {
            for (int i = 0; i < enums.length; i++) {
                EnumEntity enumEntity = new EnumEntity();
                T object = enums[i];     //得到枚举类里每条值
                Object resultKey = getMethodValue(keyMethod, object); //获取key值
                if (resultKey.equals("")) {
                    continue;
                }
                Object resultValue = getMethodValue(valueMethod, object); //获取value值
                if (resultValue.equals("")) {
                    resultValue = object;
                }
                //MessageUtils.getMessage为读取国际化的.
                enumEntity.setKey(resultKey.toString());  //把key存到实体类
                enumEntity.setValue(resultValue.toString()); //把value存到实体类
                enumList.add(enumEntity);   //存到list
            }
        } catch (Exception e) {
            e.getStackTrace();
            log.error("枚举类转成List异常", e);
        }
        return enumList;
    }


    private static <T> Object getMethodValue(String methodName, T obj, Object... args) {
        Object resut = "";
        try {
            /********************************* start *****************************************/
            /**获取方法数组，这里只要公有的方法*/
            Method[] methods = obj.getClass().getMethods();
            if (methods.length <= 0) {
                return resut;
            }
            Method method = null;
            for (int i = 0, len = methods.length; i < len; i++) {
                /**忽略大小写取方法*/
                if (methods[i].getName().equalsIgnoreCase(methodName)) {
                    /**如果存在，则取出正确的方法名称*/
                    methodName = methods[i].getName();
                    method = methods[i];
                    break;
                }
            }
            /*************************** end ***********************************************/
            if (method == null) {
                return resut;
            }
            /**方法执行*/
            resut = method.invoke(obj, args);
            if (resut == null) {
                resut = "";
            }
            /**返回结果*/
            return resut;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resut;
    }

}
