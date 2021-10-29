package com.test.thread;

import org.junit.Test;

public class FinalTest {

    @Test
    public void test() {
        Son son = new Son();
        Base father = son;
        father.test();
    }

    public class Base {
        private void test() {
            System.out.println("base");
        }
    }

    public class Son extends Base {
        public void test() {
            System.out.println("son");
        }
    }

}
