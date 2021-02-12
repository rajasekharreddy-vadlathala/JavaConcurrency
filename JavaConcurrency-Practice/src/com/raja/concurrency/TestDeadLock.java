package com.raja.concurrency;

import com.raja.concurrency.model.A;

public class TestDeadLock {
	
	//the program not terminate since b() method doesn't own key2 yet and c() method does not own key1 to call a() method.
	//this will create deadlock situation among 3 threads
	
	/*com.raja.concurrency.TestDeadLock at localhost:50999	
	Thread [main] (Running)	
	Thread [Thread-1] (Running)	
	Thread [Thread-0] (Running)	*/


	public static void main(String[] args) throws InterruptedException {
		A a = new A();
		
		Runnable r1 = ()->a.a();
		Runnable r2 = ()->a.b();

		Thread t1 = new Thread(r1);
		t1.start();
		Thread t2 = new Thread(r2);
		t2.start();
		
		t1.join();
		t2.join();

		
	}

}
