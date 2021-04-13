package demo.test.num;

import org.junit.Test;

public class NumTest {

    @Test
    public void test01(){
        int state = 0b0000;
        state |= 0b1000;
        System.out.println(Integer.toBinaryString(state));
    }

}
