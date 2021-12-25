package com.design.combining.observer;

public class QuackCounter implements Quackable {
	Quackable duck;
	static int numberOfQuacks;
  
	public QuackCounter(Quackable duck) {
		this.duck = duck;
	}
  
	public void quack() {
		duck.quack();
		numberOfQuacks++;
	}
 
	public static int getQuacks() {
		return numberOfQuacks;
	}
 
	public void registerObserver(Observer observer) {
		duck.registerObserver(observer);
	} // 데코레이터도 QuackObservable 의 method 구현은 필요.. 이때는 그냥 duck꺼 부르면 됨.
 
	public void notifyObservers() {
		duck.notifyObservers();
	}
   
	public String toString() {
		return duck.toString();
	}
}
