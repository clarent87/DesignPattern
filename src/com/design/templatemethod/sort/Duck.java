package com.design.templatemethod.sort;

public class Duck implements Comparable { // 역시 인터페이스가 제네릭이라서 T를 명시해서 써야하는게 원래 원칙
	String name;
	int weight;
  
	public Duck(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}
 
	public String toString() {
		return name + " weighs " + weight;
	}
 
 
  
	public int compareTo(Object object) { // 여기서 T obbject인데.. 이건 옛날 코드 예제라 이런가봄..
 
		Duck otherDuck = (Duck)object;
  
		if (this.weight < otherDuck.weight) {
			return -1;
		} else if (this.weight == otherDuck.weight) {
			return 0;
		} else { // this.weight > otherDuck.weight
			return 1;
		}
	}
}
