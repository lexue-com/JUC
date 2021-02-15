/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁，对业务读方法不加锁，这样行不行？
 * 答案：容易产生脏读问题（dirtyRead），根源就是因为同步写方法和非同步读方法可以同时运行；
 */

package com.mashibing.juc.c_003_Synchronized;

import java.util.concurrent.TimeUnit;

public class SyncAndNonSyncDemo2 {
	String name;
	double balance;

	public synchronized void set(String name, double balance) {
		this.name = name;

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.balance = balance;
	}

	public /*synchronized*/ double getBalance(String name) {
		return this.balance;
	}


	public static void main(String[] args) {
		SyncAndNonSyncDemo2 a = new SyncAndNonSyncDemo2();
		new Thread(()->a.set("zhangsan", 100.0)).start();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//还未初始化balance就先读了
		System.out.println(a.getBalance("zhangsan"));

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(a.getBalance("zhangsan"));
	}
}
