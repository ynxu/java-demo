package com.test;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TreeMapSort {
    public static void main(String[] args) {
        TreeSet<A> set = new TreeSet<>(Comparator.comparing(A::getDepth, Comparator.naturalOrder()));
        set.add(new A(2, "2A"));
        set.add(new A(3, "3A"));
        set.add(new A(1, "1A"));
        set.add(new A(2, "2b"));
        set.stream().map(i -> {
            System.out.println(i.toString());
            return i;
        }).collect(Collectors.toList());
    }



    public static class A{
        private int depth;
        private String value;

        public A(int depth, String value) {
            this.depth = depth;
            this.value = value;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "A{" +
                    "depth=" + depth +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
