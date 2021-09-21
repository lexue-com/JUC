package com.mashibing.juc.c_018_01_Unsafe;

//import sun.misc.*;

import sun.misc.Unsafe;

public class HelloUnsafe {
    static class M {
        private M() {}

        int i =0;
    }

    public static void main(String[] args) throws InstantiationException {
        //Unsafe是直接操作内存的一个类，目前oracle已经将其关闭
        Unsafe unsafe = Unsafe.getUnsafe();
        M m = (M)unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
    }
}


