package com.design.state.gumball;

public class GumballMachineTestDrive {

	public static void main(String[] args) {
		GumballMachine gumballMachine = new GumballMachine(5); // 알맹이는 5개로 초기화

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter(); // 동전 삽입
		gumballMachine.turnCrank(); // 손잡이 돌림

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.ejectQuarter(); // 동전 반환
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.ejectQuarter();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);
	}
}
