package com.design.singleton.dcl;

//
// Danger!  This implementation of Singleton not
// guaranteed to work prior to Java 5
//

public class Singleton {
	private volatile static Singleton uniqueInstance; // 값변경 사항이 항상 memory에 반영되도록 보장
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			synchronized (Singleton.class) {
				if (uniqueInstance == null) { // 블록 안에서도 다시한번 null check
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
}
