package com.test;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.util.List;

public class TestDemo {
    @Test
    public void testReg() {
        String str = "202107060";
        String reg = "^\\d{4}\\d{2}\\d{2}";
        String date = ReUtil.get(reg, str,0);
        System.out.println(date);
        System.out.println(StrUtil.sub(str, 8,str.length()));
    }
    @Test
    public void test() {
        NumberUtil.parseInt("50.00");
    }

    public void test02(){
    }
}
