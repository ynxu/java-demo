package com.test.thread;

import org.junit.Test;
import test.thread.VolatileExample;

public class VolatileTest {

    @Test
    public void test01() throws InterruptedException {
        VolatileExample example = new VolatileExample();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                    example.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }).start();
        }
        Thread.sleep(2000);
        System.out.println(example.counter);
    }
}
