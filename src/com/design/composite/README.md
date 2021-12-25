# 컴포지트

이터레이터를 먼저 보고 와야한다.  
컴포지트 패턴과 이터레이터 패턴이 연관이 있는건 아닌데,,  
그냥 책 예제상 이렇게 흘러감 ( 예제의 문제가 두가지 패턴을 써야 해결됨.)

## 컴포지트 패턴 정의

컴포지트 패턴을 이용하면 객체들을 트리 구조로 구성하여 부분과 전체를 나타내는 계층구조로 만들 수 있습니다.  
이 패턴을 이용하면 클라이언트에서 개별 객체와 다른 객체들로 구성된 복합 객체(composite)를 똑같은 방법으로 다룰 수 있습니다. 

> compose 영어단어가 구성.

p396- class 다이어그램
component interface(abstract, interface)를 상속한 composite, leaf 구상 class가 있다.
즉, component, composite, leaf로 class 다이어그램이 구성됨

## menu 예제 

컴포지트 패턴을 이용해서 menu와 menuitem을 구성한 모습. 예제를 보면 안다.  

(중요) p405   
- 컴포지트 패턴을 사용한 class에서는 계층구조 관리(ArrayList) 및 Menu 관련작업을 진행. 
  (MenuComponent interface method군이 그러함) 즉 한 class에서 두가지 역할을 하는데?  
  - 컴포지트 패턴은 단일 역할 원칙을 깨는대신 투명성을 확보하기 위한 패턴
  - 투명성 :  클라이언트에서 보면 어떤 원소가 composite(복합객체) 인지 leaf인지 클라이언트에게는 투명하게 보임 ( 즉 같은 방식으로 처리가능)
  - 즉 Component interface에 "자식 관리 기능","잎으로써의 기능" 이 모두 있어서 client는 복합객체와 잎을 똑같이 처리할수 있게 된것.
  - 안정성이 떨어지기는 함 ( 자세한 내용은 p405읽기)
    
## menuiterator  예제

앞선예제는 복합객체(composite)에 대한 반복자를 제공하지 않음. 즉 복합객체에서 저장한 객체들을 일일이 받을수는 있지만(getChild)...  
따라서 본 예제에서는 interface에 createIterator method가 추가됨   
CompositeIterator class 및 null iterator가 특징적이니까 주의해서 봐야한다.  

CompositeIterator 에서 stack에 composite를 만날때 마다 해당 하는 iterator를 저장.  
이후 stack에서 iterator를 뽑아서 operation.

Waitress의 printVegetarianMenu이 사용예인데,, iterator에서 menu가 나오면 그냥 exception내서 처리 해버리는게.. 특이하네.. 이거 나쁘지 않나?
- p413에 그 이유가 나옴. 결론은 투명성을 중시해서 예제는 그렇게 만들엇다 (즉, menu나 menuitem이나 같은 코드로 처리하려고..)

- nulliterator
  - menuitem을 위한것 hasNext를 무조건 false를 반환
  - 코드산 menuitme의 iterator을 만드는 상황은 없긴 한데, interface에 createIterator가 abstract라 만든듯.
  
## 정리 - p414

- 컴포지트 패턴은 GUI에서 씀.
- 컴포지트 패턴을 쓰려면 어쩔수 없이 leaf의 모든 기능, composite의 모든 기능이 component interface에 추가되야함  
  그래야, client는 투명성을 유지할수 있음. 즉 객체 type에 따라 method를 구분해서 호출하는 등 객체 type을 알필요가 없다는게 투명성.
- 컴포지트 패턴이 경우에 따라 복잡해 질수도 있음
  - 자식에 부모 ref를 달아두는 경우.
  - 자식의 순서에 맞춰 저장해야 하는경우, 이때는 자식 추가를 순서에 맞게 해야해서 복잡한 관리 방법 필요
  - composite가 너무 복잡하거나 다도는데 너무 많은 자원이 필요한경우.. 
    원하는 작업을 자식에게 하고 값을 cache하는 형태로 해나가야 함..
    
> 컴포지트 패턴 책 예는 단순한 tree형태고,, 아마 복잡해지거나 커지면 자료구조를 적절히 활용해야 하나봄.
