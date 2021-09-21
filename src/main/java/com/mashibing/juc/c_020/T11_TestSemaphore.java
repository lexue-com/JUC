package com.mashibing.juc.c_020;

import java.util.concurrent.Semaphore;

/**
 * 信号灯：最多允许指定个数的线程同时运行，
 * 使用场景：限流
 */
public class T11_TestSemaphore {
    public static void main(String[] args) {
        //Semaphore s = new Semaphore(2);
        Semaphore s = new Semaphore(2, true);   // permits:允许的灯个数
        //允许一个线程同时执行
        //Semaphore s = new Semaphore(1);

        new Thread(()->{
            try {
                s.acquire();  //必须获取到信号灯许可才能继续往下执行，获取到，信号灯个数就会-1，如果是0取不到灯就会阻塞

                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");

                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
