# Compound 패턴 ( 컴파운드 패턴) - 패턴들로 이루어진 패턴

- 대충 패턴 몇개 조합했다고 그게 컴파운드 패턴인것은 아님 (아래 duck을 가지고 만들어가는 예제..)
- 컴파운드 패턴은 여러가지 문제를 해결할수 있는 일반적인 해결책 이어야 함.
- MVC가 진정한 컴파운드 패턴.

## ducks 예제

기본적인 컴파운드 소개 예제.

- 기존 duck에서와는 다르게 Quackable 인터페이스를 MallardDuck에서 구현하는 형태로 진행
    - 원랜 구성을 이용했었다. 
  
## adapter 예제

앞선 duck에서 Goose를 추가하기 위한 예제 ( Goose는 Quackable 인터페이스를 구현한게 아님).  
따라서 Goose를 duck위치에 써주기 위해서는 어댑터가 필요.

## decorator

duck class 수정없이 오리가 꽥꽥 거린 횟수를 세주고 싶을때.  
즉, class 변경 없이 새로운 기능을 추가해야 할때.

## factory

시뮬레이터에서 데코레이팅해서 duck을 만드는 코드를 캡슐화함.  
단순히 추상 팩토리를 이용.

> 추상 팩토리를 데코레이터와 같이 쓰니까 좋네..
> 1. 추상 팩토리 구현에서는 그냥 객체를 생성할 수도 있고.
> 2. 데코레이터로 감싼 객체를 내보낼수도 있다. 
> 즉, 1,2에 해당하는 추상 팩토리 구현을 만들어 낼수 있음 

## composite 

- composite는 composite(복합객체), leaf를 구성하는 패턴이었음. client는 node와 leaf를 동일하게 취급 가능 

예제는 오리를 개별로 다루는것 뿐만아니라 오리 컬렉션, 또는 그 부분 컬렉션으로 다루는 예제  
그래서 컴포지트 씀.

> 근데 이 예제는 component에 composite와 leaf의 모든 기능을 넣지 않았네? 일부러 그런듯.
> 원래 composite에서는 MenuComponent로 menu(composite)까지 다룸, 여기서는 flock는 구상 객체의 add method를 직접 이용함
  
p553(중요) : 여기 예제는 투명성 보다는 안정성을 강조 했다. 따라서 Flock와 Duck의 동작이 다름 (add 함수)  
일부러 이런거고, 원래 composite 챕터 예에서는 composite랑 leaf이랑 interface가 완전 같아서, client에서는 객체의 종류를 고민할 필요가 없었음. 
여기서는 고민 필요.(duck가지고 add method를 호출하면 안되니까.)
  
## observer

여기서는 duck class 들이 관찰 대상(subject)가 된다. 즉, duck class들에게 옵저버가 등록되어야함

- QuackObservable 
  - subject이고, 여기에 옵저버들이 등록되야 하니까
  - 등록, 삭제, 옵저버에게 알림 method가 있어야 한다.
-  Observable class
  - 위 인터페이스 구현
  - 이부분이 핵심이네.. 

> 기존 옵저버 예제에서는 subject를 구상class인 WeatherData에서 구현했음
> 하지만 이번예제에서는 subject의 구상 class가 여러개라서 그런지 Observable class에 등록 및 연락 method를 캡슐화 함.
> 즉, 원래 대로 라면 MallardDuck 에서  QuackObservable의 모든 메소드를 직접구현...
  
## 정리

- 여기 까지 예제가 컴파운드 패턴인가? NO?
  - 그냥 여러 패턴을 섞어서 쓴것뿐..
  - 컴파운드 패턴이라고 하려면, 일반적인 문제를 해결할수 있어야 함.
  
- 오리 예제는 패턴을 쫌 과하게 썻다.
  - 실전에서는 상황에 맞는것만 써야함.
  - 또는 올바른 "디자인 원칙"만 적용해도 문제가 해결되기도 함.
