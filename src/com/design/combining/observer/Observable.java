package com.design.combining.observer;

import java.util.ArrayList;
import java.util.Iterator;

public class Observable implements QuackObservable {

  ArrayList observers = new ArrayList(); // 옵저버들 등록
  QuackObservable duck; // 이건 별의미가 있는것은 아니고 그냥 noti로 옵저버들에게 어떤 duck이 알리는것인지 전달하기 위한것
												// 즉 type에는 신경쓰지 않아도 됨 Quackable이었어도 문제 없을듯.

  public Observable(QuackObservable duck) {
    this.duck = duck;
  }

  public void registerObserver(Observer observer) {
    observers.add(observer);
  }

  public void notifyObservers() {
    Iterator iterator = observers.iterator();
    while (iterator.hasNext()) {
      Observer observer = (Observer) iterator.next();
      observer.update(duck); // 옵저버들에게 QuackObservable duck을 전달하네..
    }
  }

  public Iterator getObservers() {
    return observers.iterator();
  }
}
