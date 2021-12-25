# 이터레이터와 컴포지션 (잘 관리된 컬렉션)

이터레이터는 그 내가 아는 이터레이터 개념과 유사하긴 하다. 단 foreach에 쓰려는 목적이 아니라.
어떤 컬렉션들을 같은 방식으로 다루기 위한 방법 -> 쓰니까 애매한데 그냥 코드 확인하는게 좋을듯

## dinermerger 예제

문제 인식 - p350
- PancakeHouseMenu, DinerMenu는 배열을 쓰냐, ArrayList를 쓰냐 차이
- Waitress에서는 각각 메뉴를 출력하려면 원래는 각각 for문을 따로 두고 배열,ArrayList에 대해 돌려야함.
  
이럴때 이터레이터 패턴을 씀. - p363
Iterator는 java interface  
  
- 적용방식
    - Iterator interface를 구현하여 각각 배열, ArrayList를 위한 Iterator 구상 class를 생성
    - 해당 class는 hasNext, Next만 배열, ArrayList에 대해 구현
    - 기존 PancakeHouseMenu, DinerMenu에서는 getMenuItems 필요없이
    - 구상 Iterator class를 생성해서 반환해 주면됨 ( createIterator )

- 이슈 해결
  - PancakeHouseMenu, DinerMenu에 method( createIterator )한개 추가해서 해결됨.
    - 즉, 궅이 PancakeHouseMenu의 ArrayList를  DinerMenu의 배열 type으로 전환하지 않아도 되었음.
  - 따라서 Waitress코드의 중복이 사라짐.
  
하지만 아직 Waitress는 PancakeHouseMenu, DinerMenu 구상 class에 묶여있음 (printMenu에서 배열[] 및 ArrayList에 묶인 부분은 해소됨) - p368
  
p370 
- java.util.Iterator를 이용해도 좋다. 만약 remove method를 쓰고 싶지 않다면, 일단 구현은 하고 UnsupportedOperationException을 던지게 한다.   
Iterator api문서에 보면 제대로 만든 클라이언트라면 remove 메소드 호출면 이 예외를 확인해야 한다는 내용이 있다.
- 멀티 쓰레드 환경에서는 remove가 어떻게 동작할지 정의되어 있지 않다. 따라서 조심해야한다.   

> 이터레이터 패턴은 그냥, iterator interface를 구현한 객체를 제공하는 패턴. 이라고 보면될듯.
>
## dinermergeri 예제

위 프로젝트의 Iterator를 java.util꺼로 전환한것.  
그리고 Menu interface를 구현하도록 DinerMenu및 PancakeHouseMenu를 수정  
따라서 Waitree class는 구성에서 특정 구현에 의존하지 않게됨 ( 변수만 Menu interface를 받게 변경된게 전부긴함.)

> 특정 구현이 아닌 인터페이스에 맞춰 프로그래밍 하는 원칙 => Waitress와 구상 class간 의존성이 줄어듬

## 이터레이터 패턴 정의

정의는 됬고 중요 포인트 정리 p374,376
  
- 집합체(컬렉션)의 각 항목 접근 기능을 반복자(이터레이터)에 책임을 전가함
- aggregate : 반복자 생성 method가 담긴 인터페이스, 컬렉션이 구현해야함 ( ex : Menu interface )
- 이터레이터는 컬렉션의 위치를 관리함 (position변수)  
- 컬렉션의 처음으로 되돌아가는 기능은 이터레이터에는 없음, 이게 필요하면 이터레이터 다시 생성해서 사용
- internal iterator, external iterator 두가지 종류가 있음
  - 책 내용은 external iterator : 클라이언트에서 next 함수를 호출해서 객체를 받음
  - interanl iterator : 클라이언트는 반복자에게 job을 넘김. 그럼 반복자가 알아서 돌면서 작업을 진행.
                        (약간 함수 포인터?, job객체를 넘겨서 iterator에서 처리하는 형태인듯. )
  
## 디자인 원칙 : 단일 역할 원칙

이건  "객체지향~" 책에 나온 내용과 같다.  
하나의 class는 하나의 책임을 다뤄야한다.  

컬렉션 같은 경우 이터레이터 개념을 같이 구현하기 보다, 책임을 나눠서 이터레이터 class를 따로 두는게 좋음  
만약 컬렉션에 이터레이터 개념이 같이 있으면, 이터레이터 변경시 컬렉션을 수정해야 함.  
즉 컬렉션의 책임은 : 컬렉션 관리 이기 때문에 그외의 책임은 다른데로 넘기는게 좋다.   
  
즉 "클래스를 바꾸는 이유는 한 가지 뿐이어야 한다." 

- 응집도
  - 응집도가 높다 : 일련의 서로 연관된 기능들이 묶여있다.
  - 응집도가 낮다 : 서로 상관없는 기능들이 묶여있다.
  
## dinermergercafe 예제

별거 없다 앞서 이터레이터로 수정한 버전에서 cafe를 추가하는게 얼마나 쉬운지 보여주는 예제

- 자바 컬렉션 프레임 워크
  - ArrayList, Stack 등을 모아둔것
  - 모두 Collection 인터페이스를 구현 ( 여기에 iterator 받는 api 가 있다. Menu의  createIterator 와 같은것)

- 자바5
  - for/in이라는 문법 나옴
  
p388 - AlternatingDinerMenuItorator 나옴.. 그냥 만들어본 예제 같음.

## 아직까지 문제

Waitress 코드에서 PrintMenu 를 세번이나 호출해야함. 특히 새로는 메뉴가 추가되면, PrintMenu를 또 호출해줘야함.  
이건 OCP원칙에 위배됨

## transition 예제

위 문제를 해결한 방법, 별건없다.  DinerMenu, PancakeHouseMenu, cafeMenu를 따로 Waitress에 변수로 가지는것이 아닌
Menu ArrayList로 관리하는것.  
이렇게 하면 추후 어떤 Menu가 들어와도 ArrayList에 포함되기만 하면된다.

## 그래도 이슈가 있다.

메뉴 안에 sub메뉴를 만들고 싶을때? 이때는 Sub메뉴까지 돌수 있어야 한다. 그리고 Sub메뉴만 따로 돌수도 있어야하는데..  
지금 구조로는 많은 부분을 수정해야 함. (현재는 Menu에 MenuItem만 들어갈수 있지, Menu가 들어가지 못함)

p393 - 메뉴가 tree형태 여야함..

**컴포지트 패턴이 필요함**

