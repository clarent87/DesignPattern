package com.design.command.undo;

public class RemoteLoader {
 
	public static void main(String[] args) {
		RemoteControlWithUndo remoteControl = new RemoteControlWithUndo(); // 커맨드 패턴의 핵심. 인포커 코드는 만들어 두면 변하지 않음
 
		Light livingRoomLight = new Light("Living Room");
 
		LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
		LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
 
		remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
 
		remoteControl.onButtonWasPushed(0); // 0번 버튼 클릭. 
		remoteControl.offButtonWasPushed(0);
		System.out.println(remoteControl);
		remoteControl.undoButtonWasPushed();
		remoteControl.offButtonWasPushed(0);
		remoteControl.onButtonWasPushed(0);
		System.out.println(remoteControl);
		remoteControl.undoButtonWasPushed();

		CeilingFan ceilingFan = new CeilingFan("Living Room");
   
		CeilingFanMediumCommand ceilingFanMedium = 
				new CeilingFanMediumCommand(ceilingFan);
		CeilingFanHighCommand ceilingFanHigh = 
				new CeilingFanHighCommand(ceilingFan);
		CeilingFanOffCommand ceilingFanOff = 
				new CeilingFanOffCommand(ceilingFan);
  
		remoteControl.setCommand(0, ceilingFanMedium, ceilingFanOff);
		remoteControl.setCommand(1, ceilingFanHigh, ceilingFanOff);
   
		remoteControl.onButtonWasPushed(0);
		remoteControl.offButtonWasPushed(0);
		System.out.println(remoteControl);
		remoteControl.undoButtonWasPushed();
  
		remoteControl.onButtonWasPushed(1);
		System.out.println(remoteControl);
		remoteControl.undoButtonWasPushed();
	}
}
