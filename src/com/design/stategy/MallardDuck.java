package com.design.stategy;

public class MallardDuck extends Duck {
    public MallardDuck() {
        quakBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("저는 물오리 입니다. ");
    }
}
