package com.design.combining.observer;

public class MallardDuck implements Quackable {
	Observable observable;
 
	public MallardDuck() {
		observable = new Observable(this);
	}
 
	public void quack() {
		System.out.println("Quack");
		notifyObservers();
	}
 
	public void registerObserver(Observer observer) {
		observable.registerObserver(observer);
	}  // observable에 기능 위임
 
	public void notifyObservers() {
		observable.notifyObservers();
	} // observable에 기능 위임
 
	public String toString() {
		return "Mallard Duck";
	}
}
