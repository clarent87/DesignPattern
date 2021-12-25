package com.design.factory.pizzas;

public class PizzaStore {

  SimplePizzaFactory factory; // 역시.. 객체를 직접 new로 초기화해서 쓰지 않고, 받아서 쓴다.
                              // ( new로 쓰면 구상객체를 이용한 방식.. 이겠지.. )
                              // 그리고 이건 composition(구성)이용한 방식

  public PizzaStore(SimplePizzaFactory factory) {
    this.factory = factory;
  }

  public Pizza orderPizza(String type) {
    Pizza pizza;

    pizza = factory.createPizza(type);// [*] 심플팩토리로 이부분이 치환된것

    // simplefactory의 이슈
    // PizzaStore를 구현하는 따라 아래 내용이 달라질수 있다.
    // 즉, 예제 조건은 아래 prepare, bake, cut, box 순서는 지켜져야함. ( 즉, 피자 만드는 과정 )
    pizza.prepare();
    pizza.bake();
    pizza.cut();
    pizza.box();

    return pizza;
  }
}
