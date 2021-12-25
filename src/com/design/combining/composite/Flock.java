package com.design.combining.composite;

import java.util.ArrayList;
import java.util.Iterator;

// Flock : 무리(떼)
public class Flock implements Quackable {

  ArrayList quackers = new ArrayList();

  public void add(Quackable quacker) {
    quackers.add(quacker);
  } // 따로 Quackale.add를 쓰지 않고 구상객체의 add를 직접 쓸껀가봄.

  public void quack() {
    Iterator iterator = quackers.iterator();
    while (iterator.hasNext()) {
      Quackable quacker = (Quackable) iterator.next();
      quacker.quack();
    }
  }

  public String toString() {
    return "Flock of Quackers";
  }
}
