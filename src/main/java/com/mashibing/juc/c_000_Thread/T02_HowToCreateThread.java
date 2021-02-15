package com.mashibing.juc.c_000_Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建及启动线程的方式
 */
public class T02_HowToCreateThread {
    /**创建线程方式一：继承Thread**/
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }
    }
    /**创建线程方式二：实现Runnable接口**/
    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRun!");
        }
    }
//启动线程的三种方式 1：Thread 2: Runnable 3:Executors.newCachedThrad
    public static void main(String[] args) {
        /**线程启动方式一：继承Thread类的启动**/
        new MyThread().start();
        /**线程启动方式二：实现Runnable接口的启动**/
        new Thread(new MyRun()).start();
        new Thread(()->{
            System.out.println("Lambda表达式写法!");
        }).start();
        /**线程启动方式三：通过线程池启动**/
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }


    }

}


