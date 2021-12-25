package com.design.adapter.ducks;

public class DuckTestDrive {

  public static void main(String[] args) {
    MallardDuck duck = new MallardDuck();

    WildTurkey turkey = new WildTurkey();
    Duck turkeyAdapter = new TurkeyAdapter(turkey); // 어댑터로 만든 duck

    System.out.println("The Turkey says...");
    turkey.gobble();
    turkey.fly();

    System.out.println("\nThe Duck says...");
    testDuck(duck);

    System.out.println("\nThe TurkeyAdapter says...");
    testDuck(turkeyAdapter); // 오리 대신 칠면조를 이용한 어댑터를 Duck을 test하는 method에 넘김.
  }

  // 이게 client일거고 interface에 맞추어 작성이되어 있다. -p281
  static void testDuck(Duck duck) {
    duck.quack();
    duck.fly();
  }
}
