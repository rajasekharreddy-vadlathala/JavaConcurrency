package com.raja.concurrency;

import com.raja.concurrency.model.LongWrapper;

public class TestRaceCondition {

	public static void main(String[] args) throws InterruptedException {
		//Race condition means reading/writing to same variable at the same time by 2 threads
		//in this example it will not give correct value bcz one thread incrementing value and other thread assigning value
		
		//(copy value to) i = (read value of)i+1;
		LongWrapper wrapper = new LongWrapper(0L);
		Runnable task = ()->{
			for(int i=0;i<1000;i++){
				wrapper.incrementValue();
			}
		};
		
		Thread[] t1 = new Thread[1000];
		for(int i=0;i<t1.length;i++){
			t1[i] = new Thread(task);
			t1[i].start();
		}
		for(int j=0;j<t1.length;j++){
			t1[j].join();
			
		}
		System.out.println("Race condition value:"+wrapper.getValue());//Race condition value:998816
		//Race condition value:996711
		
	//the value should come as 1000000 but it is showing different values since 2 threads accessing same variable and assigning value
	//to avoid this use synchronization for LongWrapper incrementValue method
	
	//after implementing synchronized block for increment value method - 
	//Race condition value:1000000
	//Object lock key will used each time to enter into incrementValue method until one thread execution complete the second thread
	//can not enter into the block with out lock key.	
		
		
	}

}
