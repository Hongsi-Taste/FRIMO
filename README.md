# FRIMO

### 1. 예상 작업
    현재까지 작업 한 내용에 대한 깊이 있는 이해
    
    Fragment에 들어있는 내용 제거 (fragment가 잘 전환된다는 것을 확인함)
    
    UI

### 2. 배운 내용
#### 1) Naming convention
    https://github.com/leapfrogtechnology/android-guidelines/blob/master/ResourcesGuidelines.md
    참고하여 파일 및 변수 이름 설정하자      

#### 2) Context
```
Context는 안드로이드 SDK 자체에 의존적이면서도 추상화가 굉장히 복잡하게 되어있어서 한마디로 설명하기 어렵다.

일반적으로 구분이 가능한 대표적인 Context로는 Application Context와 Activity에서 제공하는 Context 이렇게 두 가지가 있는데
  - Application Context 같은 경우는 안드로이드 processor의 전반적인 시스템 resource에 대한 management를
  - Acitivty의 Context의 경우에는 주로 안드로이드의 UI를 구성하기 위한 컴포넌트들에 대한 management를 (제공, 관리 등등) 한다.
```
    
#### 3) View
    추후 작성

### 3. Refactoring 예상 작업
    Tablayout와 Viewpager2를 이용한 fragment 변환 작업 (참고할 Reference가 많아지기 위해)

### 4. 어려웠던 점
```
처음으로 opensource 라이브러리를(powermenu) 사용했는데 guide를 보고 진행했음에도 menu를 생성하는 작업이 잘 안되었음
-> code clone해서 작업 및 엄재웅 선배님께 context에 대해 질문 및 학습 진행

4개의 fragment slide 작업 이후 powermenu를 click해서 3개의 fragment를 전환하는 작업
  - getSupportFragmentManager().beginTransaction().replace().commit()
  - 새로운 adapter 생성 후 fragment 교체 이후 다시 기존 adapter로 변경
2가지 사항 진행해도 해결 못함 -> '안드로이드 개발 Q&A 및 팁'방에 질문해 '어피치'님과 'HN'님의 도움을 얻고 View에 대해 학습 진행
```
