package com.zhangjiang.base;

import com.zhangjiang.base.Enum.ContractEnum;
import com.zhangjiang.base.common.EnumEntity;
import com.zhangjiang.base.util.enumutil.EnumUtils;
import com.zhangjiang.base.util.numberutil.NumberUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * @className BaseTest
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/9 21:35:20
 */
public class BaseTest {

    @Autowired
    private NumberUtils numberUtils;

    @Test
    public void test01(){
        String s2 = numberUtils.toChineseNumber(567);
        System.out.println("s2 = " + s2);

        String ss[] = new String[]{"", "十", "百", "千", "万", "十", "百", "千", "亿"};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("五");
        stringBuffer.append("七");
        stringBuffer.append("六");
        stringBuffer.insert(2,ss[1]);
        stringBuffer.insert(1,ss[2]);
        System.out.println("stringBuffer = " + stringBuffer);

        String str = "34566777777600";
        String format = new DecimalFormat("#,###.00").format(new BigDecimal(str));
        System.out.println("format = " + format);
    }

    @Test
    public void test02(){
        Class<ContractEnum> contractEnumClass = ContractEnum.class;
        List<EnumEntity> getName = EnumUtils.getEnumToList(contractEnumClass, "getName");
        System.out.println("getName = " + getName.get(0));
        Map<String, String> map = EnumUtils.enumToMap(contractEnumClass, "getType");
        System.out.println("map = " + map);
    }
}
