package com.design.singleton.threadsafe;

public class Singleton {
	private static Singleton uniqueInstance;
 
	// other useful instance variables here
 
	private Singleton() {}

	/**
	 * 좋긴한데, uniqueInstance 생성된 뒤부터는 synchronized는 오버 헤드임
	 */
	public static synchronized Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
 
	// other useful methods here
}
