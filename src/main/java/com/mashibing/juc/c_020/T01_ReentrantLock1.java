/**
 * reentrantlock用于替代synchronized
 * 本例中起一个线调用m1，在m1中调用m2，即同一个线程m1、m2都要申请同一把锁，synchronized可重入m1、m2可同时执行
 * 这里是复习synchronized可重入的特性
 * @author mashibing
 */
package com.mashibing.juc.c_020;

import java.util.concurrent.TimeUnit;

public class T01_ReentrantLock1 {
	synchronized void m1() {
		for(int i=0; i<10; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);
			if(i == 2) m2();
		}

	}

	synchronized void m2() {
		System.out.println("m2 ...");
	}

	public static void main(String[] args) {
		T01_ReentrantLock1 rl = new T01_ReentrantLock1();
		new Thread(rl::m1).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
