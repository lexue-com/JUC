package com.mashibing.juc.c_020;

import java.util.concurrent.Semaphore;

/**
 * 信号灯：最多允许指定个数的线程同时运行
 * 使用场景：限流，如8条车道，只有2个收费站窗口，即最多有2个线程同时出口
 */
public class T11_TestSemaphore {
    public static void main(String[] args) {
        //Semaphore s = new Semaphore(2);
        Semaphore s = new Semaphore(2, true);   // permits:允许同时执行的线程个数，好比收费窗口
        //允许一个线程同时执行
        //Semaphore s = new Semaphore(1);

        new Thread(()->{
            try {
                s.acquire();  //必须获取到信号灯许可才能继续往下执行，获取之前一直将线程阻塞，获取到，信号灯个数就会-1，如果是0取不到灯就会阻塞

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
