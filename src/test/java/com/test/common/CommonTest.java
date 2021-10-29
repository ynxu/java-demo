package com.test.common;

import cn.hutool.core.util.NumberUtil;
import org.junit.Test;

import java.util.stream.Stream;

public class CommonTest {

    @Test
    public void a() {
        Stream.of("a","1","b","c","2").filter(NumberUtil::isNumber).forEach(System.out::println);
    }
}
