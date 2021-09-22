package com.mashibing.juc.c_020;

import java.util.concurrent.Exchanger;

/**
 * 交换两个线程的值，只能是两个线程之间
 * 场景举例：游戏中两个人交换装备
 */
public class T12_TestExchanger {

    static Exchanger<String> exchanger = new Exchanger<>();  //Exchanger是个容器，承载阻塞后需要交换的值

    public static void main(String[] args) {
        new Thread(()->{
            String s = "T1";
            try {
                s = exchanger.exchange(s);   //阻塞等待另外一个线程的值过来，另外一个线程也exchange阻塞了，两个线程进行交换值
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t1").start();


        new Thread(()->{
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);

        }, "t2").start();


    }
}
