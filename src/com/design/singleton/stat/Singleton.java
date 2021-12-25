package com.design.singleton.stat;

public class Singleton {
	private static Singleton uniqueInstance = new Singleton(); // 아싸리 classloading때 jvm이 인스턴스 생성하게 함
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		return uniqueInstance;
	}
}
