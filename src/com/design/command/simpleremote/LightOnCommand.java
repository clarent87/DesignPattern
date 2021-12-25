package com.design.command.simpleremote;

public class LightOnCommand implements Command {
	Light light; // 활용해야 할 class => 이게 리시버
  
	public LightOnCommand(Light light) {
		this.light = light;
	}
 
	public void execute() {
		light.on();
	} // execute에서는 활용할 class의 동작을 진행.
}
