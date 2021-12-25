package com.design.factory.pizzafm;

public abstract class PizzaStore {

	// [*] 이게 팩토리 메소드
	abstract Pizza createPizza(String item); // [*] 원래 구성으로 구현한것을 abstract method로 추가하였네..
																					//  [*] 여튼 이 PizzaStore class도 보면 따로 new를 하는 곳은 없음 (즉, 구현을 바탕으로 코딩하지 않음 )
																					//  [*] 제품 구상 class가 필요한 부분을 한곳으로 몰았음. 이건 subclass에서 구현

	// 어떤 type의 피자를 만드는지 super class에서는 알수 없다는게 핵심.
	public Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);
		System.out.println("--- Making a " + pizza.getName() + " ---");
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
