package com.design.state.gumballstate;

public class GumballMachine {

  State soldOutState;
  State noQuarterState;
  State hasQuarterState;
  State soldState;

  State state = soldOutState; // null 이라 함.
  int count = 0;

  public GumballMachine(int numberGumballs) {
    soldOutState = new SoldOutState(this);
    noQuarterState = new NoQuarterState(this);
    hasQuarterState = new HasQuarterState(this);
    soldState = new SoldState(this);

    this.count = numberGumballs;
    if (numberGumballs > 0) {
      state = noQuarterState;
    }
  }

  public void insertQuarter() {
    state.insertQuarter();
  }

  public void ejectQuarter() {
    state.ejectQuarter();
  }

  public void turnCrank() {
    state.turnCrank(); // 손잡이 돌리고
    state.dispense(); // 알맹이 출력 -> 이부분은 사실 SoldState 빼고는 필요없는데, 게속 사용됨.. 어떻게 작업?
    // turnCrank 의 return을 두고 return에 따라서 dispense를 호출해주는 형태로 변경 가능.. p455
  }

  void releaseBall() {
    System.out.println("A gumball comes rolling out the slot...");
    if (count != 0) {
      count = count - 1;
    }
  }

  int getCount() {
    return count;
  }

  void refill(int count) {
    this.count = count;
    state = noQuarterState;
  }

  public State getState() {
    return state;
  }

  void setState(State state) {
    this.state = state;
  }

  public State getSoldOutState() {
    return soldOutState;
  }

  public State getNoQuarterState() {
    return noQuarterState;
  }

  public State getHasQuarterState() {
    return hasQuarterState;
  }

  public State getSoldState() {
    return soldState;
  }

  public String toString() {
    StringBuffer result = new StringBuffer();
    result.append("\nMighty Gumball, Inc.");
    result.append("\nJava-enabled Standing Gumball Model #2004");
    result.append("\nInventory: " + count + " gumball");
    if (count != 1) {
      result.append("s");
    }
    result.append("\n");
    result.append("Machine is " + state + "\n");
    return result.toString();
  }
}
