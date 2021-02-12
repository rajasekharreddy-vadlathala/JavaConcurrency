package com.raja.concurrency;

public class ProducerConsumerPatternTest {
 
	private static int count = 0;
	private static int[] buffer;
	
	private static Object key = new Object();
	
	private static class Producer{
		void produce(){
			synchronized(key){
				if(isFull(buffer)){
					try {
						key.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				key.notify();
				buffer[count++]=1;
			}
		}
	}
	
	private static class Consumer{
		void consume(){
			synchronized(key){
				if(isEmpty()){
					try {
						key.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				key.notify();
				buffer[--count]=0;
				
			}
		}
	}	
	
	static boolean isFull(int[] buffer){
		return buffer.length==count;
	}
	
	static boolean isEmpty(){
		return count==0;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		buffer = new int[10];
		count = 0;
		Producer p = new Producer();
		Consumer c = new Consumer();
		
		Runnable producerTask = ()->{
			for(int i=0;i<50;i++){
				p.produce();
			}
		};
		Runnable consumerTask = ()->{
			for(int i=0;i<50;i++){
				c.consume();
			}
		};
		
		Thread t1 = new Thread(producerTask);
		Thread t2 = new Thread(consumerTask);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("buffer elements count: "+count);
				
		
	}

}
