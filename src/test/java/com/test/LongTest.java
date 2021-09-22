package com.test;

import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.atomic.LongAdder;

public class LongTest {

    @Test
    public void test03() {
        System.out.println(UUID.randomUUID().toString());
    }
    @Test
    public void test02() {
        int cidrMask = 24;
        long bits = 0;
        bits = 0xffffffff ^ (1 << 32 - cidrMask) - 1;
        String mask = String.format("%d.%d.%d.%d", (bits & 0x0000000000ff000000L) >> 24, (bits & 0x0000000000ff0000) >> 16, (bits & 0x0000000000ff00) >> 8, bits & 0xff);
        System.out.println(mask);
//        for (int i = 1; i < 32; i++) {
//            int ip = 0xFFFFFFFF << (32 - i);
//            String binaryStr = Integer.toBinaryString(ip);
//            StringBuffer buffer = new StringBuffer();
//            for (int j = 0; j < 4; j++) {
//                int beginIndex = j * 8;
//                buffer.append(Integer.parseInt(binaryStr.substring(beginIndex, beginIndex + 8), 2)).append(".");
//            }
//            System.out.println("net mask " + i + " ,submask:" + buffer.substring(0, buffer.length() - 1));
//        }
    }

    @Test
    public void test() {
        LongAdder a = new LongAdder();
        for (int i = 0; i < 10; i++) {
            add(a);
        }
        System.out.println("a " + a.longValue());
    }

    void add(LongAdder a) {
        a.add(1);
    }


}
