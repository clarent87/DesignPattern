package com.design.command.simpleremote;

// 커맨드 패턴의 클라이언트
public class RemoteControlTest {

  public static void main(String[] args) {
    SimpleRemoteControl remote = new SimpleRemoteControl(); // 인보커 객체
    Light light = new Light(); // 리시버 객체
    GarageDoor garageDoor = new GarageDoor(); // 리시버 객체
    LightOnCommand lightOn = new LightOnCommand(light); // 커맨드 객체
    GarageDoorOpenCommand garageOpen = new GarageDoorOpenCommand(garageDoor); // 커맨드 객체

    remote.setCommand(lightOn); // 커맨드 객체 주입
    remote.buttonWasPressed();  // execute.
    remote.setCommand(garageOpen);
    remote.buttonWasPressed();
  }
}
