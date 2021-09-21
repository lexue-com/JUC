/**
 * ReentrantLock默认是非公平锁，即先获取到锁的线程执行完再执行下一个线程
 * ReentrantLock还可以指定为公平锁，new ReentrantLock(true)参数为true表示为公平锁
 * 所谓公平锁也不是说两个线程交替执行，但等待队列中的线程先进先出
 * @author
 */
package com.mashibing.juc.c_020;

import java.util.concurrent.locks.ReentrantLock;

public class T05_ReentrantLock5 extends Thread {

    private static ReentrantLock lock=new ReentrantLock(true); //参数为true表示为公平锁，请对比输出结果
    public void run() {
        for(int i=0; i<100; i++) {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally{
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        T05_ReentrantLock5 rl=new T05_ReentrantLock5();
        Thread th1=new Thread(rl);
        Thread th2=new Thread(rl);
        th1.start();
        th2.start();
    }
}
