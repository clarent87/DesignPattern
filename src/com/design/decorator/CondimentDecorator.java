package com.design.decorator;

public abstract class CondimentDecorator extends Beverage {

  // Condiment : 첨가물
  // [*] getDescription은 Beverage에도 있는것인데,, 이렇게 abstract로 다시 만들수 있나봄
  // [*] cpp도 동일할거 같네..
  public abstract String getDescription();

  // [*] 데코레이터 패턴의 중요 포인트!
  // [*] target class의 super class를 가지고 바로 concrete decorator를 만드는게 아니라
  // [*] abstract decorator class를 먼저 만드는것!
  // [*] 이를 통해 target super class의 method를 제어 할수도 있음 ( 위 getDescription 처럼 )

}
