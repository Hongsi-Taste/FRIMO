# FRIMO

### 1. 예상 작업
```
1) 현재까지 작업 한 내용에 대한 깊이 있는 이해 (view, adapter 등)
    
2) UI 작업
  - Little Me Diary
  
  - Everytime FRIMO
  
  - Friendly Community
    Edit profile button 클릭 시 profile을 조명하며 메뉴 나오기
    Recycleview에서 user 클릭 시 위치를 보고 싶냐는 메뉴 나오기
    Contents, Friend Candidate button 클릭 시 event
    
  - Trend Report
    괜찮은 open source 찾아보기
    - skydoves/DisneyCompose
    - skydoves/Pokedex
    
3) Apache license 사본 받고 적용

4) Powermenu 관련 code를 Little me Diary로 옮기기 (확인 필요)
```

### 2. Refactoring 예상 작업
```
Tablayout와 Viewpager2를 이용한 fragment 변환 작업 (참고할 Reference가 많아지기 위해)

Java로 작성된 Activity를 Kotlin으로 전환 (요즘 대기업에서 backend로 Kotlin과 Spring을 합친 코프링 사용 중이라고 함)
```
----------

### 3. 어려웠던 점
```
처음으로 opensource 라이브러리를(powermenu) 사용했는데 guide를 보고 진행했음에도 menu를 생성하는 작업이 잘 안되었음
-> code clone해서 작업 및 엄재웅 선배님께 context에 대해 질문 및 학습 진행

아래 2개의 방법을 통해 powermenu를 click해 Mode(fragment)를 전환하는 작업이 잘 안되었음
  - getSupportFragmentManager().beginTransaction().replace().commit()
  - 새로운 adapter 생성 후 fragment 교체 이후 다시 기존 adapter로 변경
-> '안드로이드 개발 Q&A 및 팁'방에 질문해 '어피치'님과 'HN'님의 도움을 얻고 View에 대해 학습 진행

현재 ballon을 보고 작업 중인데 Kotlin 언어에 대해 무지하다 보니 코드 이해 및 활용이 어려움
-> 구굴링
-> https://kotlinlang.org/docs/home.html

```

### 4. 배운 내용
```
1) Naming convention
    https://github.com/leapfrogtechnology/android-guidelines/blob/master/ResourcesGuidelines.md
    참고하여 파일 및 변수 이름 설정하자      

2) Context
    Context는 안드로이드 SDK 자체에 의존적이면서도 추상화가 굉장히 복잡하게 되어있어서 한마디로 설명하기 어렵다.

    일반적으로 구분이 가능한 대표적인 Context로는 Application Context와 Activity에서 제공하는 Context 이렇게 두 가지가 있는데
      - Application Context 같은 경우는 안드로이드 processor의 전반적인 시스템 resource에 대한 management를
      - Acitivty의 Context의 경우에는 주로 안드로이드의 UI를 구성하기 위한 컴포넌트들에 대한 management를 (제공, 관리 등등) 한다.

3) View and ViewGroup / Adapter
    추후 작성

4) ReadMe.md
    Readme 작성 후 '< > Edit file' 옆에 있는 'Preview'를 통해 미리 볼 수 있다
    
5) Error
    1. White space is required before the encoding pseudo attribute in the XML declaration.
      '<?xml version="1.0"encoding="UTF-8"?>'로 작성해서 발생했던 문제
      가운데 띄어쓰기하여 '<?xml version="1.0" encoding="UTF-8"?>' 으로 바꿔주고 해결
      
    2. cannot find symbol  
      컴파일 에러. 컴파일러가 식별자가 어디에 선언되어 있는지 찾지 못했을 때 발생. 아래 항목이 대표적이다.
      [1] 식별자
        철자가 잘못된 경우: 예를 들어 StringBuilder라는 이름 대신에 StringBiulder라고 작성한 경우.
        대소문자 구분을 잘못한 경우: 예를 들어 StringBuilder 대신에 stringBuilder라고 작성한 경우.
        밑줄(_)을 사용한 경우: 예를 들어, my_string 대신에 mystring라고 작성한 경우.

      [2] 변수를 참조하는 식별자
        변수를 선언하지 않은 경우
        변수를 사용하는 시점에, 변수의 사용 범위를 벗어난 경우 (Overflow)

      [3] Method
        슈퍼클래스 혹은 인터페이스에 선언되지 않은 method를 호출하려고 할 경우
        
      [4] Class
        class를 import 하지 않은 경우
        "star"를 사용하여 import 했을 때, 해당 package 안에 class가 선언되지 않은 경우
        class의 객체를 생성할 때 new 키워드를 사용하지 않은 경우

      
6) Kotlin
    1. Java <-> Kotlin
      Java -> Kotlin : Code > Convert java file to kotlin file
      Kotlin -> Java : Tools > Kotlin > Show Kotlin Bytecode -> Decompile (정확하지는 않은 듯한 느낌을 받음)

  
```
