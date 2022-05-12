package com.mashibing.juc.c_36_ProducterAndConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @auther LXY
 * @create 2022/5/10 15:13
 * @Description TODO
 */
public class producerAndConsumerByLock {

    private static  int count = 0;
    private int maxNum = 3;
    ReentrantLock lock = new ReentrantLock();
    Condition producerCondition = lock.newCondition();
    Condition consumerCondition = lock.newCondition();

    public static void main(String[] args) {
        producerAndConsumerByLock test = new producerAndConsumerByLock();
        new Thread(test.new Producer()).start();
        new Thread(test.new Producer()).start();
        new Thread(test.new Consumer()).start();
        new Thread(test.new Consumer()).start();
    }

    class Producer implements Runnable {
        @Override public void run() {
            for (int i = 0; i < 10; i++) {
                try { Thread.sleep(500); }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //获取锁
                 lock.lock();
                try {
                    while (count >= maxNum) {
                        producerCondition.await();
                        System.out.println("生产能力达到上限，进入等待状态");
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    //唤醒消费者
                    consumerCondition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放锁
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.lock();
            try {
                while (count <= 0) {
                consumerCondition.await();
                }
                count--;
                System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                //唤醒生产者
                producerCondition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}




