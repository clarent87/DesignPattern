package com.design.observer;

public class WeatherStation {

  public static void main(String[] args) {
    WeatherData weatherData = new WeatherData(); // Subject의 구현체 (1:N)

    // observer에 subject를 전달
    // obsever에서는 subject에 this를 등록
    CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData); 
      
    // Subject의 변경을 진행
    // Subject에서는 변경사항을 Observer에 전달
    weatherData.setMeasurements(80, 65, 30.4f);
    
    // [*] 여튼 Subject와 Observer는 서로의 객체를 가지고 있네, 단 Subject는 Observer 객체를 여러개 가지고, Observer는 Subject 한개만 저장하고.. 일단
    // [*] 서로 객체를 가지고 있는것은 그냥 여기서의 구현
  }

}
