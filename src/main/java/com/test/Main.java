package com.test;

import com.test.hutool.BioDemo;
import com.test.hutool.HutoolTest;

public class Main {
    public static void main(String[] args) {
        System.out.println(args.length);
        for (String arg : args) {
            System.out.println(arg);
        }
        HutoolTest.test();
        BioDemo.test(args);
    }
}
