/**
 * 对比上面一个小程序，分析一下这个程序的输出
 * 每个线程输出结果一致
 * @author mashibing
 */

package com.mashibing.juc.c_002_Synchronized;

public class SyncDemo2 implements Runnable {

	private int count = 100;

	public synchronized void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

	public static void main(String[] args) {

		for(int i=0; i<100; i++) {
			SyncDemo2 t = new SyncDemo2();
			new Thread(t, "THREAD" + i).start();
		}
	}

}
