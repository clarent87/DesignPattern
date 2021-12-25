package com.design.stategy;

public class Squeak implements QuakBehavior{

    @Override
    public void quack() {
        System.out.println("삑");
    }
}
