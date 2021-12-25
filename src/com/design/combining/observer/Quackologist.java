package com.design.combining.observer;

public class Quackologist implements Observer {
 
	public void update(QuackObservable duck) {
		System.out.println("Quackologist: " + duck + " just quacked."); // ? 이거 string 출력되나? 실제 객체에 대한 메소드 호출가능함?
	}
 
	public String toString() {
		return "Quackologist";
	}
}
