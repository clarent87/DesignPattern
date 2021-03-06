# 디자인 패턴

디자인 패턴 목차
1. 패턴 소개 - 스트래티지 패턴
2. 옵저버
3. 데코레이터
4. 팩토리
5. 싱글턴
6. 커맨드
7. 어댑터, 퍼사드
8. 템플릿 메소드
9. 이터레이터, 컴포지트
10. 스테이트
11. 프록시
12. 컴파운드
13. 실전 디자인
14. 기타 패턴

> 패턴도 분류가 있었는데.. 분류에 따른 label 붙여야 할듯.

## 기초 용어

구상 class (concrete class) :  abstract class의 반대 의미 즉 abstract/interface가 아닌 클래스
고수준 : superclass를 의미하거나, 특정 행동의 좀더 추상화된 행동 
         (ex: 계산method는 고수준, 더하기,빼기는 저수준 method, 계산 method는 저수준 method를 써야하니까 의존관계)
client : 그냥 해당 알고리즘을 쓰는 객체 혹은 메소드.
구성 : 객체를 변수로 받아서 이용하는것
interface : 책에서는 interface 를 java interface 및 abstract class 모두를 지칭하는데 씀, 주로

## 디자인 패턴 요약
### 1.스트래티지 패턴
- 구성이용, 구성객체는 특정 interface를 구현
- client는 구성객체를 이루는 interface로 작업
### 2. 옵저버
- 주제와 옵저버는 모두 느슨한 결합, 즉 각각 구상class가 아닌 interface에 의존
- 주제, 옵저버는 모두 interface
- 주제는 옵저버 등록, 제거, notify method를 가짐 (보통) -> 옵저버에서 주제의 등록,제거 호출해서 자신을 등록
- 옵저버는 update 메소드를 가짐 (보통) -> 주제에서 update메소드를 호출
### 3. 데코레이터 
- 다른 패턴에서도 유사한 기술이 쓰임
- 상속과 구성을 이용함
```java
 // 1. 일단 데코레이팅할 class의 superclass를 이용해서 추상 데코레이터 생성
 public abstract class CondimentDecorator extends Beverage {}
 // 2. 추상 데코레이터를 이용한 구상 데코레이터 생성
 public class Mocha extends CondimentDecorator {
  Beverage beverage; // 3. 이때 데코레이팅할 class의 interface를 구성변수로 가지고 있는다
  ///...
 }
```
### 4. 팩토리
- 심플 팩토리
  - 그냥 if문 반복해서 객체를 "생성만" 해주는 객체.
  - 클라이언트는 팩토리 객체를 이용해서 객체를 받기만 한다.
  - 이건 패턴이라 볼수 없다.
- 팩토리 메소드
  - **팩토리 메소드 패턴은 특화된 템플릿 메소드 패턴이다**
  - abstract 메소드로 객체 생성 부분을 subclass에서 제공하도록 책임전가
  - 팩토리 메소드 패턴의 class는 **구상메소드 제공**(객체 생성+기타 작업)
  - 심플 팩토리는 그냥 객체만 생성하는건데, 사실 객체 생성하고 여러 세팅작업을 해야 사용할수 있는 객체가 되므로.
    이런 일련의 작업을 고정하기 위해 사용 ( 피자를 만들때 피자만들고 토핑을 뿌리는 순서에 맞추어 뿌려야 하는것 처럼)
  - 중요 포인트는, 의존성 역전!
    - 이건 고수준 , 저수준 구성요소 모두 추상적인것에 의존하게 해야한다는것 
- 추상 팩토리
  - 말그대로 추상임. 따라서 구상 팩토리 객체를 다양하게 만들수 있다.
  - 각각 item 군에 대한 생성 method 제공
  - 추상 팩토리 vs 팩토리 메소드는 사용 용도가 다른거 같다. 
 
### 5. 싱글턴
- 기본 싱글턴 : 이건 동시성 이슈
- DCL 이용 또는 static이용 : 동시성 이슈 해결

### 6. 커맨드 패턴
- client : 인보커 객체 이용하는 프로그램
- 인보커 : 커맨드 객체를 저장(변수에 등록) 하고 method로 command의 method를 쓰는 녀석. 이때 변수는 커맨드 interface로 작업
- 리시버 : 각종 class
- 커맨드 : 리시버에 해당하는 각각의 커맨드 class, 리시버당 여러개 만들수 있다. command interface를 구현해서 만든다. (보통 인터페이스에 execute, undo method가 있다.)

### 7. 어댑터, 퍼사드
- 어댑터
  - 데코레이터와 비슷, 상속과 구성을 이용함
  - 차이는 어댑터는 상속과 구성의 interface가 다름
  - 어댑터는 타겟 인터페이스를 implement하고 어댑티를 구성으로 가지고 있음
    이를 통해 타겟 인터페이스 위치에 어댑티를 꼿을수 있다.
- 퍼사드
  - 별거 없다. 
  - 그냥 여러 class 묶어서 하나의 단순화된 interface를 제공하는 하나의 class를 만든것 (이때 여러 class를 구성으로 가지고 있음

### 8. 템플릿 메소드
- 팩토리랑 비슷
- 알고리즘을 제공하는데 일부분은 변경가능하게 만들기 위함
- abstract class에 구상메소드로 알고리즘을 제공하는 부분이 템플릿 메소드이며, abstract method로 제공하는 부분을 subclass에서 구현하는 형태
- 실전에서는 조금 다르게 쓰기도 한다. (구성을 상속대신 이용)

### 9. 이터레이터, 컴포지트
- 이터레이터
  - 컬렉션의 iterator 그거 맞음. hasnext, next method를 가짐
  - 이 인터페이스를 구현해두면, 컬렉션의 요소를 순회할수 있음 ( client 측에서 컬렉션 type에 관계없이 순회하기 편함. )
- 컴포지트
  - 메뉴판과 메뉴item예제를 생각하면 좋음. 또는 GUI 프로그램의 화면 요소들..
  - composite와 leaf를 tree형태로 다루는 구조.
  - 이때는 composite의 객체관리기능+operation과 leaf의 operation 모두를 conponent interface의 method로 뽑아야함.
      - 그래야 client에서 composite인지 leaf인지 객체를 구분할 필요없이 쓸수있음
      - 이경우 단일책임은 위배되지만 투명성을 확보하는데 좋음
      - conponent 구현시 필요없는 method는 null객체를 반환하던지.. null또는 false를 리턴 또는 예외를 던지면된다.
  - client나 복합객체(composite)에서 사용할 composite,leaf의 인터페이스가 동일하다는게 강점(투명성)


## 13장.

- 패턴 카탈로그
  - 패턴의 용도와 패턴이 만들어지게 된 배경, 적용될 수 있는 범위, 해결책의 디자인 및 해결책을 적용한 결과 등의 내용이 나옴
  - 예시
    - GOF의 디자인 패턴 : 가장 훌률한 패턴 카탈로그
    - 엔터프라이즈 소프트웨어, 컨커런트 시스템, 비지니스 시스템 등의 영역에 맞는 패턴 카탈로그가 출시되고 있음. 
  
패턴 카탈로그를 볼땐 아래의 용어가 있음
- 문제, 컨텍스트, 원인, 해결책.. 등.
    
### p642

- 애플리케이션 패턴 : MVC
- Domain Specific Pattern : J2EE에 존재

## 기타 패턴

GOF의 패턴이긴 한데, 쫌 덜쓰이는것들.

- 브릿지 패턴 : 이건 어렵다.. 나중에 봐야 할듯
- 빌더 패턴 : 해봤던것
- Chain of Responsibility 패턴 : 윈도우 핸들러
- 플라이웨이트 패턴 : 객체는 한개만 만들고, 객체들의 값(변수들)..을 따로 배열로 저장하고, 객체에 update하며 사용하나봄.
- 인터프리터 패턴 : 간단하게는 확인해보면 좋을거 같음.
- 미디에이터 패턴 : 개념은 간단.. 객채들 간의 복잡한 통신을 미디에이터가 중간에서 해결해주는것, 객체는 미디에이터랑만 통신하면됨 
- 메멘토 패턴 : 상태를 따로 객체로 빼는것.
- 프로토타입 패턴 : 자바 clone같은걸로 객체를 복사해서 만드는거네.. ( cpp로 치면 복사 생성자?) , 객체 생성이 복잡할때 쓴다데.. 생성보다 복제가 빠를때.
- 비지터 패턴 : 캡슐화가 별로 중요하지 않을때 사용. (단점, 캡슐화가 깨진다함..즉 은닉이 깨진다는것?)

> 캡슐화란 은닉 + 관련있는 변수,메소드 묶는것,.