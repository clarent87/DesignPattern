package com.design.state.gumballstate;

// 앞선 예의 method들을 전부 State에 추가
public interface State {
 
	public void insertQuarter();
	public void ejectQuarter();
	public void turnCrank();
	public void dispense();
}
