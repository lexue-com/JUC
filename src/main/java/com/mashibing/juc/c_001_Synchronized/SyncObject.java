/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */

package com.mashibing.juc.c_001_Synchronized;

public class SyncObject {
	//可以锁任何对象，不一定要锁count
	private int count = 10;
	/**方式一：通过创建对象加锁**/
	private Object o = new Object();
	public void a() {
		synchronized(o) { //任何线程要执行下面的代码，必须先拿到o的锁
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}
	/**方式二：每次创建对象过于繁琐，可以直接锁定当前对象synchronized（this）**/
	public void b() {
		synchronized (this) { //任何线程要执行下面的代码，必须先拿到this的锁
			count--;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}
	}

	/**方式三：锁定当前对象synchronized（this）等同于synchronized方法**/
	public synchronized void c() { //等同于在方法的代码执行时要synchronized(this)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}







}

