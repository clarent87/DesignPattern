package com.design.adapter.ducks;

public class TurkeyAdapter implements Duck { // Duck 위치에 넣을꺼니까., Duck을 구현

  Turkey turkey; // 구성으로는 turkey를 사용

  public TurkeyAdapter(Turkey turkey) {
    this.turkey = turkey;
  }

  public void quack() {
    turkey.gobble();
  }

  public void fly() {
    for (int i = 0; i < 5; i++) {
      turkey.fly();
    }
  }
}
