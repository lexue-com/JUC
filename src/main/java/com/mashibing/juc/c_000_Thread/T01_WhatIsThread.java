package com.mashibing.juc.c_000_Thread;

import java.util.concurrent.TimeUnit;

/**
 * 什么是线程
 */
public class T01_WhatIsThread {
    private static class T1 extends Thread {
        @Override
        public void run() {
           for(int i=0; i<10; i++) {
               try {
                   TimeUnit.MICROSECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("T1");
           }
        }
    }

    public static void main(String[] args) {
        //想运行run方法有两种方式:
        // 方式一：new T1().run(),一条执行路径   main -> run -> main 结果先输出T1再输出main；
        // 方式二：new T1().start(),start()是Thread的实现方法，有两条执行路径，即main与run同时运行，结果T1与main交替输出；
        //new T1().run();
        new T1().start();
        for(int i=0; i<10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }

    }
}
