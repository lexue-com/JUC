/**
 * 分析一下这个程序的输出
 * @author mashibing
 */

package com.mashibing.juc.c_002_Synchronized;

public class SyncDemo1 implements Runnable {

	private /*volatile*/ int count = 100;
	//synchronized既保证原子性，又可以保证可见性，所以加锁了就不用给count加volatile了
	public /*synchronized*/ void run() {
		//不加锁的话count输出会乱
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

	public static void main(String[] args) {
		SyncDemo1 t = new SyncDemo1();
		for(int i=0; i<100; i++) {
			new Thread(t, "THREAD" + i).start();
		}
	}

}
