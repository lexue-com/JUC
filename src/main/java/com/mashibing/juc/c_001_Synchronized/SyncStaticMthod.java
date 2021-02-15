/**
 * synchronized关键字
 * 静态加锁
 * @author mashibing
 */

package com.mashibing.juc.c_001_Synchronized;

public class SyncStaticMthod {

	private static int count = 10;
	/**方式一：静态方法加锁**/
	public synchronized static void a() { //这里等同于synchronized(SyncStaticMthod.class)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	/**方式二：静态方法加锁等同于锁定当前class**/
	public static void b() {
		synchronized(SyncStaticMthod.class) { //考虑一下这里写synchronized(this)是否可以？不可以
			count --;
		}
	}

}
