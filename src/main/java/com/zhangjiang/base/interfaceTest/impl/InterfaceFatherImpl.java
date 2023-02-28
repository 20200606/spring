package com.zhangjiang.base.interfaceTest.impl;

import com.zhangjiang.base.interfaceTest.Interfacefather;
import org.springframework.stereotype.Service;

/**
 * @className InterfaceFatherImpl
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/25 11:14:26
 */
@Service
public class InterfaceFatherImpl implements Interfacefather {

    private int MAX_VALUE = 123456;

    @Override
    public String Test(String string) {
        return null;
    }

    @Override
    public String test03() {
        int maxValue = Interfacefather.MAX_VALUE;
        return null;
    }
}
