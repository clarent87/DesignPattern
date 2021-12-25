package com.design.stategy;

public class MuteQuack implements QuakBehavior{

    @Override
    public void quack() {
        System.out.println("조용");
    }
}
