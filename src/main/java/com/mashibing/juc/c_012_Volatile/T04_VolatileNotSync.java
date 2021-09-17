/**
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 * 运行下面的程序，结果会出现<100000，因为count++是多条指令，volatile只是保证了线程之间的可见性并不保证原子性
 * @author mashibing
 */
package com.mashibing.juc.c_012_Volatile;

import java.util.ArrayList;
import java.util.List;

public class T04_VolatileNotSync {
	volatile int count = 0;
	void m() {
		for(int i=0; i<10000; i++) count++;  //count++并不是一条指令
	}

	public static void main(String[] args) {
		T04_VolatileNotSync t = new T04_VolatileNotSync();

		List<Thread> threads = new ArrayList<Thread>();

		for(int i=0; i<10; i++) {
			threads.add(new Thread(t::m, "thread-"+i));
		}

		threads.forEach((o)->o.start());

		threads.forEach((o)->{
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(t.count);


	}

}


