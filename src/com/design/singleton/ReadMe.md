# 싱글톤

## 고전적인 싱글턴 패턴

고전적인 패턴이고, 문제가 많은 패턴(멀티 쓰레딩때 제대로 동작 안함)

```java
public class Singleton {

  private static Singleton uniqueInstance;

  private Singleton() {
  }

  public static getInstance() {
    if (uniqueInstance == null) { // [*] 멀티 쓰레드에서 각각 여기를 지나가면
      uniqueInstance == new Singleton(); // [*] 각각 다른 객체를 받게 된다. 
    }
    return uniqueInstance;
  }
}

```

## 고전적인 싱글턴 패턴 문제 수정 1

-[예제](./threadsafe/Singleton.java)

메소드를 동기화한 예제이고, 메소드 동기화시 성능은 100배 나빠진다고함 - p219

## 고전적인 싱글턴 패턴 문제 수정 2

-[예제](./stat/Singleton.java)

static 변수에서 new로 직접 객체를 생성해서, 아싸리 classloading때 jvm이 인스턴스 생성하게 함  
이경우는 jvm이 classloading 및 static 처리를 동기화 문제 없이 해주는것을 보장.

## 고전적인 싱글턴 패턴 문제 수정 3 : DCL (double checking locking)

-[예제](./dcl/Singleton.java)

## 중요

- p222 : 중요한 포인트들이있음
  - 클래스 로더가 여러개 인경우, 로더마다 싱글톤 객체가 생성될 수있다. 이경우 classloader를 명시해줘야 한다함.
  - 그냥 class를 만들때 모든 변수, method를 static으로 만들어서 싱글톤 대신 써도 되긴 한데, 복잡한 초기화가 필요없는 경우만 가능
    ( 정적 초기화 방식이나 순서 때문에 bug발생시 뭔가 찾기 어렵나봄, 특히 여러  class가 얽혀있는경우는 더욱..)
  - 그냥 전역변수 쓰는것보다 나은 이유 : 이건 길어서 생략. 책을 참조하길 바람

## 정리

고전적인 싱글턴 패턴 문제 수정 2 나 DCL가 가장 좋은 방법인가봄.

-p227 각 방식의 장단점.

## 기타 

스프링 강좌에서는 아래 코드가 관례상 많이 쓰는 싱글톤이라고 함. .

```java
public class SingletonService{
  private static final SingletonService instance = new SingletonService();
    
  public static SingletonService() {
    return instance;
  }
  private SingletonService() {   
  }
}
```

