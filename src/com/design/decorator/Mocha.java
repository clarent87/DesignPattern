package com.design.decorator;

public class Mocha extends CondimentDecorator {

  Beverage beverage;

  public Mocha(Beverage beverage) {
    this.beverage = beverage;
  }

  @Override
  public double cost() {
    return .20 + beverage.cost(); // 0.20이아니라.. .20이라고 써도 되네.
  }

  @Override
  public String getDescription() {
    return beverage.getDescription() + ", 모카";
    // [*] beverage.description 라고 하면 "Unknown Beverage"가 나옴..
    // [*] 왜냐면 beverage의 객체가 데코레이팅 된 mocha 객체일때는 description 값은 unknown..
    // [*] 한번도 데코레이터 객체의 description값을 변경한 적은 없음
  }
}
