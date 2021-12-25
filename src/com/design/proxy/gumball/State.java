package com.design.proxy.gumball;

import java.io.*;

// 직렬화 가능하게 Serializable interface 확장.
public interface State extends Serializable {
	public void insertQuarter();
	public void ejectQuarter();
	public void turnCrank();
	public void dispense();
}
