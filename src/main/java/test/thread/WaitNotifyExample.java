package test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WaitNotifyExample {

    public synchronized void before(){
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after(){
        try {
            wait();
            System.out.println("after");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        WaitNotifyExample example = new WaitNotifyExample();
        exec.execute(example::after);
        exec.execute(example::before);
    }



}
