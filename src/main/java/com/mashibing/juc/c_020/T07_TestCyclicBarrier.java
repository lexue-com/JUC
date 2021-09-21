package com.mashibing.juc.c_020;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *循环栅栏 ：就像比赛时要等运动员都上场后才开始
 */
public class T07_TestCyclicBarrier {
    public static void main(String[] args) {
        //写法一
        //CyclicBarrier barrier = new CyclicBarrier(20);
        //写法二
        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("满人，发车"));
        //写法三
        /*CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人，发车");
            }
        });*/

        for(int i=0; i<100; i++) {

                new Thread(()->{
                    try {
                        barrier.await(); //阻塞到这里等够20个线程
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }).start();
            
        }
    }
}
