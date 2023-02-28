package com.zhangjiang.base;

import com.zhangjiang.base.Enum.ContractEnum;
import com.zhangjiang.base.common.EnumEntity;
import com.zhangjiang.base.util.enumutil.EnumUtils;
import com.zhangjiang.base.util.numberutil.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class StudyApplicationTests {

    @Test
    public void contextLoads() {
        Integer i = 58888;
        String s = NumberUtils.toChineseNumber(i);
        String str = "5888888888";
        String s1 = NumberUtils.insertCharacter(str);
        System.out.println("s = " + s);
        System.out.println("s1 = " + s1);
        // 测试枚举转换List
        Class<ContractEnum> contractEnumClass = ContractEnum.class;
        List<EnumEntity> enumEntityList = EnumUtils.getEnumToList(contractEnumClass, "getName");
        System.out.println("enumEntityList = " + enumEntityList.get(0).getKey());
        // 测试枚举转换 Map
        Class<ContractEnum> contractEnumClass1 = ContractEnum.class;
        Map<String, String> map = EnumUtils.enumToMap(contractEnumClass1, "getType");
        System.out.println("map = " + map.get("CSHZContractToJXS"));
    }

    @Test
    public void test(){
        System.out.println("00 = " + (0 % 1000));
        System.out.println("01 = " + (1 % 1000));
        System.out.println("02 = " + (2 % 1000));
        System.out.println("03 = " + (3 % 1000));
    }

}
