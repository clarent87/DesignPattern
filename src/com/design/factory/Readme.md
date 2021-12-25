# 팩토리 패턴

## 들어가기전

느슨한 결합, 불필요한 의존성 제거가 본장의 목표  

> 디자인 원칙 :  특정 구현을 바탕으로 프로그래밍 해서는 안된다. p148

위 말의 의미가 이해하기 어렵긴한데.. 대충 적어보면 아래와 같다. 
- new 를 이용해서 직접 객체를 만드는 것이 구현을 바탕으로 한 프로그래밍.  
- 인터페이스 변수로 작업하는것이 특정 구현을 이용하지 않는 방법.  
- 하지만 new를 쓰지 않을수는 없다. 중요한것은 변화에 대응하는 것.. 

인터페이스에 맞추서 코딩을 하면 여러 변화에 대응가능 (다형성 때문)

목표 : 애플리케이션에서 구상 클래스의 인스턴스 만드는 부분을 전부 찾아내서 애플리케이션의 
나머지 부분으로 부터 분리/캡슐화 한다. - p149

> 디자인 원칙: 바뀔수 있는 부분을 찾아내서 바뀌지 않는 부분하고 분리시켜야 한다.  

팩토리 패턴은 행동, 책임, 역할을 분할하는 개념과 일맥상통하는듯 (즉, 객체 생성만 따로 팩토리 클래스에서 진행)

## 팩토리 패턴

간단한 팩토리는 정적 메소드로 구현하기도 함 ( static factory).  
이렇게 하면 팩토리 class의 객체를 생성하고, method를 호출하는 번거로운 작업을 줄일수 있음(그냥 static method만 호출하면 되니까) 
단, 이경우 static method는 subclass에서 변경이 안되니까, subclass를 이용한 객체 생성 행동 변경을 할수 없는 단점이 있다.  

> 중요.. SimplePizzaFactory와 같은 간단한 팩토리는 팩토리 패턴이 아니라네.. 그냥 관용구 같은거라고 함...   
> 즉 난 그동안 잘못알고 있었다.. 

팩토리 패턴은 두가지가 있다. - p155

## pizzafm

위 관용구의 문제를 해결한 코드 p156. 문제는 관용구(객체생성)만으로는 피자 생성 순서를 지키게 하지 못함. 
이부분은 pizzas의 pizzastore코드에 적어둠.  
즉 팩토리 패턴의 목표는 단순히 객체를 생성하는것 뿐만아니라. 객체의 생성을 위한 method 호출순서까지 제어하는게  
목표인가봄. ( 즉, operation도 제공 )

> 즉 팩토리 메소드 패턴은 팩토리 메소드+ 이와 결합된 operation메소드를 제공하는 형태의 프레임워크
  
여기서는 팩토리class가 아닌 팩토리 method가 같은 일을 담당.
즉 super class에서 abstract 팩토리 method를 제공하면, subclass에서 원하는 대로 method를 구현해서 쓰는형태.  
( 원래 관용구에서는 팩토리 객체를 넘겨서 썼다. composition방식.)

> 중요한게.. prepare, bake, cut은 pizza객체에 있는거네.. orderPizza 메소드는 피자객체의 함수를 호출하는게 전부..  
> 느낌상 store에서 prepare, bake, cut메소드를 제공해야 하는거 아닌가 싶었는데. (역할 개념으로 본다면..)
  
- 팩토리메소드 패턴 (p169)
  - 생산자 클래스(abstract) => 프레임워크라도로 표현할수 있는듯, 제품 클래스를 다루는 operation도 제공하니까
  - 제품 클래스(abstract) => 이것의 존재도 팩토리 패턴에서 중요 포인트
    

## 정리 

모든 팩토리 패턴은 객체 생성을 캡슐화함.  
- 심플 팩토리 패턴
- 팩토리 메소드 (with 상속)  
  
p170 - 제품 클래스와 생산자 클래스의 class uml로 계층구조를 비교 (중요)  
보면 추상 생산자 클래스에서 추상 제품 클래스를 다루고 있다. ( ex: ordermethod에서 pizza를 다룸, cheesepizza같은 구상 class가 아니라..)

> p172에 공식적인 정의가 있다.(중요)  
> 요약하면 클래스(제품)의 인스턴스를 만드는 일을 subclass가 하게하는 것 (인터페이스를 구현해서.. )

어찌되었던 최종적으로는 팩토리를 통해 Pizza객체를 얻는것이 목표 (operation도 끝난.. ex: bake,cut..등)  
  
- p173중요.
  - Creator 클래스는 ConcreteProduct와 느슨한 결합.
  
## 의견

p150에서는 pizzastore의 orderpizza에 pizza객체까지 생성했었다.  
일반적으로 pizza의 종류가 늘어나면 pizzastore의 class의 orderpizza를 계속해서 수정하거나,  
pizzastore class를 복사해서 다른 store를 만들고 orderpizza의 객체생성 부분만 계속 수정할텐데.. 
  
팩토리 패턴을 이용하면 비슷한 구성이지만 변경되는 부분을 따로 빼서 팩토리메소드.. 또는 심플팩토리로  
만들어서 중복을 제거한 형태.. 라고 보여짐.  
  
하지만 생각해도면 PizzaTestDrive에서 팩토리 패턴을 이용하면, pizza 종류가 추가되었을때. 수정해야할 부분이 매우 적어짐.
( 단순히 new.. store 부분만 수정하면됨, subclass는 당연히 만들어 주고..)
  
> 아.. 위내용은 p175에 더 잘 나와 있다. 이거 꼭 참조 
  
p174 중요! - 팩토리의 장점
p176 - 의존성에 대해

> 객체 생성 코드를 모아서 관리하면 좋다. 

## 의존성 뒤집기 원칙

디자인 원칙 : 추상화된 것에 의존하도록 만들어라. 구상 클래스에 의존하도록 만들지 않아도 된다. 

> 고수준의 구성요소가 저수준의 구성요소에 의존하지 않게 해야 한다. 

고수준의 구성요소가, 저수준의 구성요소에 의존하면 안된다는 것이 내포된 원칙  
고수준의 구성요소 : 저수준의 구성요소에 의해 정의된 "행동"이 있는 요소
(ex: 고수준의 구성요소 => pizzastore, 저수준의 구성요소 : pizza)

보통 고수준의 구성요소가 저수준의 구성요소에 의존(변수로 사용)하는게 객체지향 설계시 일반적인 방법인가봄.  
의존성 뒤집기에서는 저수준의 구성요소(ex:nystyleclampizza)가 고수준의 추상 class(pizza)에 의존하게 함 (p178참조)

p178 : 고수준, 저수준 구성요소 모두 pizza 추상 class에 의존하게됨 (의존성 뒤집기.)

> 개념이 참 애매하긴 하다. p180 보면 조금 나을지도..

p179 : 베이스 클래스의 method를 override한다는 것은 애초부터 base class가 제대로 추상화 된게 아닐수 있다. 

> pizza 를 상속하는 것도 의존이라고 보는듯. 

- 의존성 뒤집기 정리 (최종)
  - pizzaStore에서 각종 피자(구상클래스)들을 직접 만든다. => 코드가 구상클래스에 종속됨 ( 고수준이 저수준에 의존함)
  - 이때 pizzaStore는 abstract createPizza함수를 준비하고 return으로 pizza(추상요소)만 받도록한다. 
    그러면 pizzaStore는 pizza라는 추상요소에만 의존
  - 나머지 저수준 요소였던 각종 피자들은 pizza라는 추상class를 implement해서 만들어지므로, pizza라는 추상요소에만 의존.  
  
- 고수준 구성요소는 저수준의 구성요소들을 기반으로 완성된다. 즉 A 라는 Class가 B,C,D라는 변수를 가지고 있다면  
  A는 고수준 요소 B,C,D는 저수준 요소

## 의존성 뒤집기 원칙을 지킬수 있는 가이드 라인

p181

- 어떤 변수에도 구상 클래스에 대한 레퍼런스를 저장하지 않아야 한다. 
- 구상 클래스에서 유도된 클래스를 만들지 말아야 한다. 
- 베이스 클래스에 이미 구현되어 있던 메소드를 오버라이드하지 않아야 한다. 

## pizzaaf (추상팩토리)

추상팩토리에 대한예제.  심플 팩토리랑은 약간 차이가 있다.  
추상 팩토리는 제품"군"(ex: 도우, 소스, 치즈) 을 만들어 내기 위한 interface를 제공 (이부분은 예제를 확인하는게 이해에 좋다.)

> 심플팩토리랑 목적이 다르기때문에 형태에 차이가 약간 있고, 사실 뭐 거의 느낌은 비슷하다..

- 추상팩토리 패턴 정의
  - 추상 팩토리 패턴에서는 인터페이스(abstract,interface)를 이용하여 서로 연관된, 또는 의존하는 객체를 구상 클래스를 
    지정하지 않고도 생성할 수 있다. 

추상팩토리 패턴에서 메소드는 팩토리 메소드 이다 .( 보면 안다. )  
근데 이것이 추상 팩토리 패턴에서 팩토리 메소드 "패턴" 을 쓴다는 것은 아님 (두 패턴은 차이가 있음 p196)

> 추상팩토리는 사용시 구성으로 진행하고, 팩토리 메소드는 상속.. 임. 

### 추상 팩토리 vs 심플 팩토리

- 추상 팩토리는 말그래도 추상이다. 즉 구현을 여러개 만들어서 쓸수 있음
- 추상 팩토리는 심플 팩토리랑은 다르게 각각의 item에 대한 생성 method존재
- 심플 팩토리는 단일 아이템에 대한 생성 method

## 종합의견

내용은 아래 3개 였다.
1. 심플 팩토리
2. 팩토리 메소드
3. 추상 팩토리
  
근데 중요한 것은 어떤 상황에서 쓸지 약간은 모호 하다 (예제가 쫌 복잡해서 그런듯.)

p195 - uml이상한듯. 