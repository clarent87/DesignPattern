package com.design.templatemethod.barista;

// 템플릿 메소드 패턴
public abstract class CaffeineBeverage {

	// 이게 템플릿 메소드
	final void prepareRecipe() {
		boilWater();
		brew();
		pourInCup();
		addCondiments();
	}
 
	abstract void brew();
  
	abstract void addCondiments();
 
	void boilWater() {
		System.out.println("Boiling water");
	}
  
	void pourInCup() {
		System.out.println("Pouring into cup");
	}
}
