package com.design.observer;

import java.util.ArrayList;

public class WeatherData implements Subject {

  private ArrayList<Observer> observers; // 제네릭이라 타입 명시해주는 것이 좋다. 안쓰면 default는 object..
  // [*] 책은 제네릭을 사용하지 않았는데 일단 나는 ArrayList 쓸때마다 type-casting하기 싫어서 그냥 제네릭을 썻다.
  // [*] type 위치에는 interface를 넣어도 되나봄..
  private float temperature;
  private float humidity;
  private float pressure;


  public WeatherData() {
    this.observers = new ArrayList<>(); // 여기서는 this 생략이 가능하겠지만, paramter 이름도 observers 였을땐 꼭 this 써야 하는듯.
  }

  @Override
  public void registerObserver(Observer o) {
    observers.add(o);
  }

  @Override
  public void removeObserver(Observer o) {
    int i = observers.indexOf(o);
    if (i >= 0) {
      observers.remove(i);
    }
  }

  @Override
  public void notifyObserver() {
    // observer들에게 data의 update를 알림
    for (int i = 0; i < observers.size(); i++) {
      Observer observer = observers.get(i);
      observer.update(temperature, humidity, pressure);
    }
  }

  public void measurementsChanged() {
    notifyObserver();
  }

  // 아래 함수는 패턴과는 관계없음
  // 단순히 Subject의 data를 외부에서 update해주는 함수
  public void setMeasurements(float temperature, float humidity, float pressure) {
    this.temperature = temperature;
    this.humidity = humidity;
    this.pressure = pressure;
    measurementsChanged();
  }
}
