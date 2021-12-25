package com.design.combining.ducks;

// 기존 스트래티지랑 다르게 상속으로 Duck 구현
public class MallardDuck implements Quackable {
	public void quack() {
		System.out.println("Quack");
	}
}
