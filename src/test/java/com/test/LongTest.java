package com.test;

import org.junit.Test;

import java.util.concurrent.atomic.LongAdder;

public class LongTest {

    @Test
    public void test(){
        LongAdder a = new LongAdder();
        for (int i = 0; i < 10; i++) {
            add(a);
        }
        System.out.println("a " +a.longValue());
    }
    void add (LongAdder a){
        a.add(1);
    }
}
