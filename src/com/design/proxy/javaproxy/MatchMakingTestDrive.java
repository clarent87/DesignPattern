package com.design.proxy.javaproxy;

import java.lang.reflect.Proxy;
import java.util.Hashtable;

public class MatchMakingTestDrive {

  Hashtable datingDB = new Hashtable();

  public MatchMakingTestDrive() {
    initializeDatabase();
  }

  public static void main(String[] args) {
    MatchMakingTestDrive test = new MatchMakingTestDrive();
    test.drive();
  }

  public void drive() {
    PersonBean joe = getPersonFromDatabase("Joe Javabean");
    PersonBean ownerProxy = getOwnerProxy(joe);
    System.out.println("Name is " + ownerProxy.getName());
    ownerProxy.setInterests("bowling, Go");
    System.out.println("Interests set from owner proxy");
    try {
      ownerProxy.setHotOrNotRating(10);
    } catch (Exception e) {
      System.out.println("Can't set rating from owner proxy");
    }
    System.out.println("Rating is " + ownerProxy.getHotOrNotRating());

    PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
    System.out.println("Name is " + nonOwnerProxy.getName());
    try {
      nonOwnerProxy.setInterests("bowling, Go");
    } catch (Exception e) {
      System.out.println("Can't set interests from non owner proxy");
    }
    nonOwnerProxy.setHotOrNotRating(3);
    System.out.println("Rating set from non owner proxy");
    System.out.println("Rating is " + nonOwnerProxy.getHotOrNotRating());
  }

  PersonBean getOwnerProxy(PersonBean person) {

    return (PersonBean) Proxy.newProxyInstance(
        person.getClass().getClassLoader(),
        person.getClass().getInterfaces(),
        new OwnerInvocationHandler(person));
  }

  PersonBean getNonOwnerProxy(PersonBean person) {

    return (PersonBean) Proxy.newProxyInstance(
        person.getClass().getClassLoader(),
        person.getClass().getInterfaces(), // 이거 newProxyInstance쓸때 중요 class는 안됨, 반드시 interface 배열만 됨
        new NonOwnerInvocationHandler(person));
  }

  PersonBean getPersonFromDatabase(String name) {
    return (PersonBean) datingDB.get(name);
  }

  void initializeDatabase() {
    PersonBean joe = new PersonBeanImpl();
    joe.setName("Joe Javabean");
    joe.setInterests("cars, computers, music");
    joe.setHotOrNotRating(7);
    datingDB.put(joe.getName(), joe);

    PersonBean kelly = new PersonBeanImpl();
    kelly.setName("Kelly Klosure");
    kelly.setInterests("ebay, movies, music");
    kelly.setHotOrNotRating(6);
    datingDB.put(kelly.getName(), kelly);
  }
}
