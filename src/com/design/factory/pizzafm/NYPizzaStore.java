package com.design.factory.pizzafm;

public class NYPizzaStore extends PizzaStore {

	// [*] 원래 factory class를 만들고 객체를 변수로 넘기던것을,
	// [*] 구상 대신 상속으로 구현한것이네..
	Pizza createPizza(String item) {
		if (item.equals("cheese")) {
			return new NYStyleCheesePizza();
		} else if (item.equals("veggie")) {
			return new NYStyleVeggiePizza();
		} else if (item.equals("clam")) {
			return new NYStyleClamPizza();
		} else if (item.equals("pepperoni")) {
			return new NYStylePepperoniPizza();
		} else return null;
	}
}
