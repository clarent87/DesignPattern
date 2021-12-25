# 프록시 패턴 (객체 접근 제어)

state패턴 예제에서 기능이 확장된 예제.

## gumballmonitor

뽑기기계의 몇가지 상태를 알아볼수 있는 monitor class가 추가됨 ( 알맹이 갯수, 현재 상태, 위치 )
  
근데 이 예제는 같은 jvm에서 .. GumballMachine class 및 GumballMonitor가 있을떄 활용 가능함  
만약 GumballMonitor가 원격(다른 jvm)에 있는 GumballMachine에 대한 상태를 보고 싶을땐 활용하지 못함.  
이걸 프록시로 해결하는게 아래 예제..

> 원격 GumballMachine 예제는 그냥 proxy의 예를 위해서 만든것으로 보임, 즉 이런 상황에만 proxy 패턴을 쓰는건 아니다. 

## gumball (원격 프록시 예제)

GumballMonitor class는 그대로 유지하고 GumballMachine에 대한 proxy 객체를 대신 받아서 사용한다.   
이때 네트웤관련 처리는 proxy객체에서 진행 ( 원격에 있는 GumballMachine에 메시지를 요청하고 받는거.. 등등. )  
  
자바에서 제공하는 원격 호출을 위한 **RMI**를 이용한다함. 
- 예제는 java program을 여러개(jvm여러개) 띄워서 IPC하는 예고.
- 실제로 RMI이용하면 네트웤 통신도 가능할거 같긴함. 
여기서 부터는 RMI의 사용법을 우선 소개함.  

> 예제에서는 다른 JVM의 heap에 들어있는 객체를 호출하나봄.  
> RMI가.. java 기본인가본데.. headfirst 자바에 나온다함..

p475에  RMI 개념이 잘 나와 있음(중요!!)  
그 다음부터는 실제 RMI 만드는 내용이 나옴.. 이부분은 책을 참조 하도록 한다.
- 약간 c에서 배웠던 IPC같음. 

- marker interface : 아무 메소드도 없는 interface, p480
    - interface를 extends해서 새 interface를 만듬.
    
- transient 키워드 : 해당 class의 객체를 직렬화 할때 변수에 transient가 있으면 그건 직렬화하지 않음. 즉 포함안하는듯.
    - 이경우 역직렬화해서 객체 사용하는 측은 해당 변수 사용 불가.
    
쫌 난해하긴 한데, 그래도 대략 정리 해보면

1. 원격인터페이스 준비 : GumballMachineRemote
2. 서버 서비스 구현 : GaumballMachine
3. 서비스 등록 : GaumballMachineTestDrive
4. 클라이언트 수정 : GumballMonitorTestDrive
    - 클라이언트는 GumballMachineRemote를 받음. (stub)
    - 이때 GumballMonitor는 GumballMachine대신 GumballMachineRemote를 사용하게 변경 (proxy)
    
> 이 예제에서는 proxy가 stub인데, 직접 구현하진 않음. RMI에의해 생성, 전달됨

## 프록시 패턴 정의 

"어떤 객체에 대한 접근을 제어하기 위한 용도로 대리인이나 대변인에 해당하는 객체를 제공하는 패턴."
원격 프록시(Remote Proxy)는 일반적인 프록시 패턴을 구현하는 방법 가운데 하나임.

> 근데 그냥 스트래티지랑.. 비슷한거 아닌가?? interface를 이용한 작업... 
> 프록시는 패턴 + @라고 보이네.. 프록시의 중요포인트는  "접근을 제어하기 위한 용도"

중요한 포인트는 "접근을 제어하기 위한 용도"  
프록시 패턴은 수많은 변종이 있음  
해당 변종들은 대게 "접근을 제어하는 방법"면에서 차이가 있따.   
  
프록시에서 접근을 제어하는 방법은 아래와 같다. 
- 원격 프록시를 써서 원격 개체에 대한 접근을 제어할 수 있다. 
- 가상 프록시를 써서 생성하기 힘든 자원에 대한 접근을 제어할 수 있다. 
- 보호 프록시를 써서 접근 권한이 필요한 자원에 대한 접근을 제어 할 수 있다. 

## virtualproxy

- 가상 프록시 
    - 생성하는데 많은 비용이 드는 객체를 대신하는 역할
    - 진짜 객체가 필요하게 되기 전까지 객체의 생성을 미루게 해주는 기능 제공
    - 객체 생성전, 또는 객체 생성 도중에 객체를 대신하기도 함.
    - 객체 생성이 완료되고 나면 그냥 RealSubject에 요청을 직접 전달. 
    
ex: GUI에서 네트웤을 통해 이미지를 받아오는 경우, 네트웤이 느려지면 로딩 이미지를 보여줘야 하는데, 이럴때 사용

원리는 간단함, 진짜 ICON객체를 생성하는 ICON Proxy를 만들고 실제 ICON객체가 필요한곳에 넣는다.  
이떄 ICON객체나 ICON Proxy나 같은 ICON interface를 구현한것.  
icon proxy에서는 paintIcon이 최초 호출되면, 일단 default 메시지를 띄우고  
쓰레드에서 실제 ICON객체를 만든뒤, repaint api를 호출함..
이러면 다시 paintIcon이 호출되므로... 이미 ICON객체가 생성된 뒤니까. ICON객체의 paintIcon을 호출함

### Q&A - p509

- 가상 프록시예제는 데코레이터 아닌가?
    - 아님. 물론 형태는 동일한데 용도를 가지고 구분해야함
    - 데코레이터는 class에 새로운 행동을 추가하는거고
    - 프록시는 어떤 class에 대한 접근 제어.
    
- 팩토리 메소드 패턴과 같이 흔하게 쓰임
    - 팩토리가 실제 객체를 만들어주는거니까, 여기서 실제 객체 대신 proxy를 만들어서 client에 전달
    
- 보호 프록시의 경우 어댑터와 유사한 면이 있다. 

p510 : 프록시와 데코레이터의 차이가 쫌 나와있다. 
- 이를 테면 데코레이터는 데코레이팅할 객체를 생성하지는 않는데, 프록시는 생성하기도 함.

## java proxy

보호 프록시 예제, java.lang.reflect 패키지 이용. 
java에서 제공하는 dynamic proxy를 이용한 방법이다. 중요!
  
proxy class는 자바에서 직접 만들어 주기 때문에, 앞선예제(ICON) 처럼 proxy class를 만들지 않아도됨.  
따라서 Proxy class의 InvocationHandler에 대신 필요한 코드를 넣으면 된다.   

- 예제 설명
  - PersonBean객체의 특정 값은 자신만 변경할수 있어야 하고, 일부 특정 값은 자신이 아닌 다른객체만 변경할 수 있어야 한다.
  - 이걸 접근 제어를 통해 해결함.
  - 여기서는 proxy를 두가지를 생성 (자기 자신용, 다른 사람용)
  
> 오.. proxy 사용하는거 좋아 보인다. 

- dynamic proxy
  - 코드가 없고 runtime에 class가 생성됨.
  - method 호출시 invoke 가 대신 호출되고 method명 param, this객체(proxy)가 invoke에 넘어가는듯.
  - proxy는 interface로만 만들수 있나봄. 즉 newProxyInstance param으로 넘어가는 java interface에 대한 구현을 만들어 주나봄.


 