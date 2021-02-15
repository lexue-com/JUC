/**
 * 同步和非同步方法是否可以同时调用？  当然可以
 * @author mashibing
 */

package com.mashibing.juc.c_003_Synchronized;

public class SyncAndNonSyncDemo1 {

	public synchronized void m1() {
		System.out.println(Thread.currentThread().getName() + " m1 start...");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m1 end");
	}

	public void m2() {
		System.out.println(Thread.currentThread().getName() + " m2 start...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " m2 end");
	}

	public static void main(String[] args) {
		SyncAndNonSyncDemo1 t = new SyncAndNonSyncDemo1();

		/*new Thread(()->t.m1(), "t1").start();
		new Thread(()->t.m2(), "t2").start();*/

		new Thread(t::m1, "t1").start();
		new Thread(t::m2, "t2").start();

		/*
		//1.8之前的写法
		new Thread(new Runnable() {

			@Override
			public void run() {
				t.m1();
			}

		});
		*/

	}

}
