2. 옵저버 패턴

jdk에서 가장 많이 쓰이는 패턴

keyword)
일대다 관계
느슨한 패턴 -> 구상 class에 맞춰 상호작용하는게 아니라 각각의 인터페이스에 맞춰 상호작용해야함,
              옵저버나 subject의 경우를 보면 서로 구상객체는 모름. 인터페이스만 알지.. 이게 느슨한 패턴임임
81p의 내용 중요!
- 구체적 구현에 맞춘 이슈
- 캡슐화 해야 하는 부분 이슈.

옵저버 패턴 정의)
    한 객체(subject)의 상태가 바뀌면 그 객체에 의존하는 다른 객체들한테 연락이 가고 자동으로 내용이 갱신되는
    방식으로 일대다 (one to many) 의존성을 정의 한다. (subject:one - observer:many)

1차 정리)
Subject객체 만 상태를 가지고 있고, Observer들은 Subject객체에 등록이 되는 형태.
옵저버는 데이터가 변경되었을 때 주제에서 갱신해 주기를 기다리는 입장이라서 의존성이 있다고함.
이런 방법이 여러 객체에서 동일한 데이터를 제어하도록 하는 것에 비해 더 깔끔한 객체지향 디자인임 ( 즉 Observer에서 똑같은 상태(data)를 가지는 것보다 낫다고 하는거 같음 )

느슨한 결함 : Loose coupling ) p91
옵저버 패턴에서는 주제와 옵저버가 느슨하게 결합되어 있는 디자인 이라고함.
1. Subject 객체는 언제든지 옵저버 추가가능
2. 새로훈 형식의 옵저버를 주제에  추가가능 하고 이때 subject의 수정필요없음 ( subject객체는 Observer의 concrete class가 아닌 interface를 다루기 때문 )
3. 주제와 옵저버는 서로 독립적인 재사용 가능
4. 주제나 옵저버가 바뀌더라도 서로한테 영향없음

따라서 객체 사이의 의존성이 매우 적다 ( 느슨한 결합 )

디자인 원칙!)
    서로 상호작용을 하는 객체 사이에서는 가능하면 느슨하게 결합하는 디자인을 사용해야 한다.

2차 정리)
  예제 옵저버 방식은 push방식, getter는 pull 방식
  java의 내장 Observer를 이용하면 push, pull방식 모두 쓸수 있다.
  Subject에서 observer 등록은 interface를 기반으로 등록하는 거임
  ( 따라서 내장 observerable을 이용하면 register같은 함수 구현안해도 되는것,, 이건 코드를 보면 안다. )

java 내장 옵저버 패턴 )
  java.util.Observable => 이건 Subject와 동일
  java.util.Observer => 이건 그냥 앞선 옵저버와 같다.
  * 근데. 이거 deprecated되었다.


weatherobserable directrory : java.util로 다시 만든것

의견) java 내장 옵저버 패턴은 deprecated되었는데, 한계가 있어서,, 이거 대신 java.util.concurrent.Flow를 쓰라는거 같음
그리고 해당 글을 보니까 reactive랑 옵저버 패턴이랑 연관이 되는거 같기도 함.
책에서도 문제점에 대해서 기술해 놨네.

정리) 옵저버 패턴은
주제 객체에서 list로 옵저버(interface)들을 관리하는 것.
주제의 상태 변경시 옵저버.update를 호출해서 정보를 전달한다.
> 주제에 옵저버를 등록하는 행위가 선행되어야 한다