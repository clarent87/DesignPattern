package com.design.factory.pizzas;

// 컥.. 이런 간단한 팩토리는 디자인 패턴이라고 할수 없다네..
// 그냥 관용구 라고 함
public class SimplePizzaFactory {

  public Pizza createPizza(String type) {
    Pizza pizza = null;

    if (type.equals("cheese")) {
      pizza = new CheesePizza();
    } else if (type.equals("pepperoni")) {
      pizza = new PepperoniPizza();
    } else if (type.equals("clam")) {
      pizza = new ClamPizza();
    } else if (type.equals("veggie")) {
      pizza = new VeggiePizza();
    }
    return pizza;
  }
}
