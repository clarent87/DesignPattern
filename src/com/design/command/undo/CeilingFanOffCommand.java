package com.design.command.undo;

public class CeilingFanOffCommand implements Command {
	CeilingFan ceilingFan;
	int prevSpeed;
  
	public CeilingFanOffCommand(CeilingFan ceilingFan) {
		this.ceilingFan = ceilingFan;
	}
 
	public void execute() {
		prevSpeed = ceilingFan.getSpeed();
		ceilingFan.off();
	}
 
	public void undo() { // off 커맨드 누르고, undo눌렀을때의 동작
		if (prevSpeed == CeilingFan.HIGH) {
			ceilingFan.high();
		} else if (prevSpeed == CeilingFan.MEDIUM) {
			ceilingFan.medium();
		} else if (prevSpeed == CeilingFan.LOW) {
			ceilingFan.low();
		} else if (prevSpeed == CeilingFan.OFF) {
			ceilingFan.off();
		}
	}
}
