package com.zhangjiang.base.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @className NumberUtils
 * @Author zhangjiang
 * @Description:
 * @Date 2022/10/25 10:58:16
 */
@Component
public class NumberUtils {

    /**
     * 数字转换中文
     * @param d
     * @return
     */
    public static String toChineseNumber(Integer d){
        String[] str = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
        String ss[] = new String[]{"", "十", "百", "千", "万", "十", "百", "千", "亿"};
        String s = String.valueOf(d);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            String index = String.valueOf(s.charAt(i));
            stringBuffer = stringBuffer.append(str[Integer.parseInt(index)]);
        }
        s = String.valueOf(stringBuffer);
        int i = 0;
        for (int j = s.length(); j > 0; j--) {
            stringBuffer = stringBuffer.insert(j, ss[i++]);
        }
        return stringBuffer.toString();
    }

    /**
     * 三位一逗转换
     * @param str
     * @return
     */
    public static String insertCharacter(String str){
        return new DecimalFormat("#,###.00").format(new BigDecimal(str));
    }
}
