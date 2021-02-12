package com.raja.concurrency.model;

public class A {
	Object key1 = new Object();
	Object key2 = new Object();
	
	public void a(){
		synchronized(key1){
			System.out.println("["+Thread.currentThread().getName()+"] running in a()");
			b();
		}
	}
	public void b(){
		synchronized(key2){
			System.out.println("["+Thread.currentThread().getName()+"] running in b()");
			c();
		}
	}
	public void c(){
		System.out.println("["+Thread.currentThread().getName()+"] running in c()");
		synchronized(key1){
		}
	}
}
