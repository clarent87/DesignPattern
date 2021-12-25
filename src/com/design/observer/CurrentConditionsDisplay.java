package com.design.observer;

public class CurrentConditionsDisplay implements Observer, DisplayElement {

  private float temperature;
  private float humidity;
  private Subject weatherData; // 옵저버에서 subject의 객체를 가지고 있는다. ( 물론 옵저버는 subject에 등록되는것 )

  public CurrentConditionsDisplay(Subject weatherData) {
    this.weatherData = weatherData;
    this.weatherData.registerObserver(this);
  }

  @Override
  public void display() {
    System.out.println("Current conditions: " + temperature + "나머지는 생략");

  }

  @Override
  public void update(float temp, float humidity, float pressure) {
    this.temperature = temp;
    this.humidity = humidity;
    display();
  }
}
