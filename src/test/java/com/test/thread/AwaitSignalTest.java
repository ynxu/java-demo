package com.test.thread;

import org.junit.Test;
import test.thread.AwaitSignalExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AwaitSignalTest {

    @Test
    public void test01() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AwaitSignalExample example = new AwaitSignalExample();
        executorService.execute(example::after);
        executorService.execute(example::before);
    }
}
