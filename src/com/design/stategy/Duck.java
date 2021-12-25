package com.design.stategy;

public abstract class Duck {
    FlyBehavior flyBehavior;
    QuakBehavior quakBehavior;

    public Duck(){ }

    public abstract void display();

    public void performFly(){
        flyBehavior.fly();
    }

    public void performQuack(){
        quakBehavior.quack();
    }

    public void swim(){
        System.out.println("오리는 물에 뜹니다. 가짜 오리도 뜨죠.");
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuakBehavior(QuakBehavior quakBehavior) {
        this.quakBehavior = quakBehavior;
    }
}
