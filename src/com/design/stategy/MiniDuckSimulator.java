package com.design.stategy;

public class MiniDuckSimulator {
    public static void main(String[] args){
        Duck mallard = new MallardDuck(); // 중요 포인트! super class를 이용
        mallard.performQuack(); // 캡슐화된 행동/알고리즘
        mallard.performFly(); // 캡슐화된 행동/알고리즘

        // 행동을 수정할 수 있다.
        Duck model = new ModelDuck();
        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered()); // 행동을 교환하는 개념이 스트래티지에서 중요!!
        model.performFly();
    }
}
