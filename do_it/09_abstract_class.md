## 09-1 추상 클래스
* 추상 클래스(abstract class)  
    : 상속을 위한 클래스(추상 메서드를 포함)
    - 구현코드 없이 메서드의 선언만 있음
        ```
        # 일반 클래스 1
        int add(int x, int y){
            return x + y; // {} 내부: 함수의 구현부(implementation)
        }
        # 일반 클래스 2
        int add(int x, int y){}

        # 추상 클래스
        abstract in add(int x, int y); // abstract 예약어 사용 / 함수의 구현부가 없음 -> {} 대신 ; 사용
        ```

* 추상 클래스 구현하기
      - 메서드 선언의 의미  
        : 메서드가 해야 할 일을 명시
        + 함수의 선언부(declatration): 반환 값, 함수 이름, 매개변수 -> 함수의 역할, 구현 방법을 정의
    - 추상 메서드  
        : 하위 클래스가 구현해야 할 메서드
        + 각 하위 클래스마다 다르게 구현되어야 하는 기능에 해당
    - 구현된 메서드
        + 하위 클래스가 공통으로 사용될 수 있는 기능 구현
        + 경우에 따라서는 하위 클래스가 재정의(overriding) 가능  

    ![image](https://user-images.githubusercontent.com/104348646/196071509-f35dc240-b823-4aa8-9db4-81af6814a89b.png)  
    - 추상 클래스 구현하기
        ```
        # 1

        package abstractex;

        public class Computer {
            public void display(); // error: 몸체(body) 부분을 작성 or 추상 메서드로 변경해야 함
            public void typing(); // same error
            public void turnOn(){
                System.out.println("전원을 켭니다");
            }
            public void turnOff(){
                System.out.println("전원을 끕니다");
            }
        }

        # 2: 추상 메서드로 변경
        public class Computer{
            public abstract void display();
            // error: 추상 메서드가 속한 클래스를 추상 클래스로 선언하지 않음 -> abstract 예약어 제거 or computer 클래스를 추상 클래스로 만들어야 함
            public abstract void typing(); // same error
            ...
        }

        # 3: Computer 클래스를 추상 클래스로 변경
        public abstract class Computer{ // abstract  예약어 추가
            public abstract void display();
            public abstract void typing();
        }
        ```
        + turnOn(), turnOff() 구현코드: 공통
        + display(), typing(): 하위 클래스에 따라 구현 변화 가능
        + => Computer()에서는 구현하지 않음 -> 메서드 구현에 대한 책임을 상속받는 클래스(DeskTop, NoteBook)에 위임함
    ```
    # 추상 클래스 상속받기

    package abstractex;

    public class DeskTop extends Computer{
    // error: 상속받은 클래스 DeckTop은 추상 메서드를 포함 -> 추상 메서드를 모두 구현 or DeskTop을 추상 클래스로 설정 필요

    }

    # 2

    package abstractex;

    public class DeskTop extends Computer{
        @Override
        public void display(){
            
        }
        @Override
        public void typing(){

        }
    }
    ```
    ```
    # 추상 메서드 구현하기

    package abstractex;

    public class DeskTop extends Computer{
        @Override
        public void display(){
            System.out.println("DeskTop display()"); // 추상 메서드의 몸체 코드 작성
        }
        
        @Override
        public void typing(){
            System.out.println("DeskTop typing()"); // 추상 메서드의 몸체 코드 작성
        }
    }
    ```
    ```
    # NoteBook 클래스 구현하기

    package abstractex;

    public abstract class NoteBook extends Computer{
        @Override
        public void display(){
            System.out.println("NoteBook display()"); // display만 구현 -> 추상 메서드 가짐 -> 추상 클래스가 됨
        }
    }
    ```
    ```
    # MyNoteBook 클래스 구현하기

    package abtractex;

    public class MyNoteBook extends NoteBook{
        @Override
        public void typing(){
            System.out.println("MyNoteBook typing()"); // 모든 추상 메서드가 구현됨 -> abstract 예약어 사용 불필요
        }
    }
    ```
    - 모든 추상 메서드를 구현한 클래스에 abstract 예약어를 사용하는 경우
        ```
        package abtractex;

        public abstract class AbstractTV{ // 공통 기능만 구현 -> 상속만(사용 x) -> new 예약어로 인스턴스 생성 불기
            public void turnOn(){
                System.out.println("전원을 켭니다");
            }
            public void turnOff(){
                System.out.println("전원을 끕니다");
            }
        }
        ```

* 추상 클래스를 만드는 이유
    - 추상 클래스는 인스턴스로 생성할 수 없다
        + 추상 클래스는 모든 메서드가 구현되지 않음(구현 코드 없음) -> 인스턴스 생성 불가능
    ```
    # 추상 클래스 테스트하기

    package abstractex;

    public class ComputerTest{
        public static void main(String[] args){
            Computer c1 = new Computer(); // error: 추상 클래스는 인스턴스 생성이 불가능
            Computer c2 = new DeskTop();
            Computer c3 = new NoteBook(); // same error
            Computer c4 = new MyNoteBook();
        }
    }
    ```
    - 추상 클래스에서 구현하는 메서드
        + 추상 클래스  
            : 상속을 위한 클래스
        + 구현된 메서드  
            : 하위 클래스에서 공통으로 사용할 수현 코드(하위 클래스에서 재정의 가능)
        + 추상 메서드  
            : 하위 클래스가 어떤 클래스인지에 따라 구현 코드가 달라짐

## 09-2 템플릿 메서드
* 추상 클래스와 템플릿 메서드
    - 템플릿(template) 메서드  
        : 추상 메서드나 구현된 메서드를 활용하여, 전체 기능의 흐름(시나리오)를 정의하는 메서드  
        ![image](https://user-images.githubusercontent.com/104348646/196071583-6a54f721-2d84-4318-9b11-abc617f53d58.png)
        ```
        # Car 클래스 생성

        package template;

        public abstract class Car{
            // 추상 메서드: drive(), stop() <- 차종에 따라 다른 방식으로 움직일 수 있음
            public abstract void drive();
            public abstract void stop();

            // 구현된 메서드: startCar(), turnOff(), run()
            public void startCar(){
                System.out.println("시동을 켭니다");
            }

            public void turnOff(){
                System.out.println("시동을 끕니다");
            }

            final public void run(){ // 템플릿 메서드
                startCar();
                drive();
                stop();
                turnOff();
            }
        }
        ```
        ```
        # AICar 클래스 생성

        package template;

        public class AICar extends Car{ // AICar: Car을 상속받음
            // 추상 메서드: drive(), car()
            @Override
            public void drive(){
                System.out.println("자율 주행합니다");
                System.out.println("자동차가 알아서 방향을 전환합니다");
            }

            @Override
            public void stop(){
                System.out.println("스스로 멈춥니다");
            }
        }
        ```
        ```
        # ManualCar 클래스 생성

        package template;

        public class ManualCar extends Car{ // ManualCar: Car을 상속받음
            // 추상 메서드: drive(), car()
            @Override
            public void drive(){
                System.out.println("사람이 운전합니다");
                System.out.println("사람이 핸들을 조작합니다");
            }

            @Override
            public void stop(){
                System.out.println("브레이크로 정지합니다");
            }
        }
        ```
        ```
        # Test

        package template;

        public class CarTest{
            public static void main(String[] args){
                System.out.println("=== 자율 주행하는 자동차 ===");
                Car myCar = new AICar();
                myCar.run();

                System.out.println("=== 사람이 운전하는 자동차 ===");
                Car hisCar = new ManualCar();
                hisCar.run();
            }
        }
        ```  
        ![image](https://user-images.githubusercontent.com/104348646/196071627-873a6f37-d359-48c0-9eb6-9c501206a296.png)    

* 템플릿 메서드의 역할
    - 메서드 실행 순서와 시나리오 정의
        + ex) run(): 차가 달리는 방법을 구현함
        + 추상 메서드를 호출하는 경우, 차종에 따라 구현 내용 변동 가능하지만 startCar(), drive(), stop(), turnOff()를 순서대로 실행하는 시나리오는 불변
        + 하위 클래스에서 템플릿 메서드 재정의는 불가능
        + => final로 선언 -> 하위 클래스에서 재정의 불가능

## 09-3 템플릿 메서드 응용하기
```
# 예제 시나리오

Player가 있고, 이 Player가 게임을 합니다.
게임에서 Player가 가지는 레벨에 따라 할 수 있는 세 가지 기능이 있습니다. run(),jump(), turn()
모든 레벨에서 Player가 사용할 수 있는 필살기인 go(int count) 메서드를 제공합니다.
go() 메서드는 한 번 run하고, 매개변수로 전달된 count만큼 jump하고, 한 번 turn합니다.
그 레벨에서 불가능한 기능을 요청하면 할 수 없다는 메세지를 출력합니다.
```

* 클래스 기능과 관계
    - 의사 코드(pseudo code) 작성
    ```
    if(level == beginner)
    else if(level == advanced)
    else if(level == super) // 비효율적: 기능이 추가될 때마다 각각 코딩 필요
    ```

* 클래스 설계하기
    - Player, PlayerLevel 클래스: HAS-A 관계 -> Player 클래스에서 PlayerLevel을 멤버 변수로 가짐
    - PlayerLevel 클래스: 추상 클래스 -> 공통 구현 + 추상 메서드(레벨 별 기능)
    - 하위 클래스: BeginnerLevel, AdvancedLevel, SuperLevel  
    ![image](https://user-images.githubusercontent.com/104348646/196034992-6a781899-6e6e-425e-af84-c2167f440d0c.png)    

    - Player 클래스
        ```
        # Player 클래스 구현하기

        package gamelevel;

        public class Player{
            // 멤버 변수 PlayerLevel 선언
            private PlayerLevel level;
            
            public Player(){ // 디폴트 생성자
                level = new BeginnerLevel(); // 처음 생성되면, BeginnerLevel로 시작
                level.showLevelMessage(); // 레벨 메세지 출력
            }
            
            public PlayerLevel getLevel(){
                return level;
            }
            // 레벨 변경 베서드
            public void upgradeLevel(PlayerLevel level){ // 매개변수 level: 모든 레벨로 변환 가능한 PlayerLevel 자료형
                this.level = level; // 현재 자신의 level을 매개변수로, 받은 level로 변경
                level.showLevelMessage(); // 레벨 메세지 출력
            }
            
            public void play(int count){
                level.go(count); // PlayerLevel의 템플릿 메서드 go() 호출 <- run, jump, turn, showLevelMessage가 순서대로 호출됨
            }
        }
        ```
    - PlayerLevel 클래스
        ```
        # PlayerLevel 추상 클래스 구현하기

        package gamelevel;

        public abstract class PlayerLevel{
            // 추상 메서드: run(), jump(), turn(), showLevelMessage() <- 레벨 별로 다르게 구현 필요
            public abstract void run();
            public abstract void jump();
            public abstract void turn();
            public abstract void showLevelMessage();

            final public void go(int count){ // final: 재정의 방지
                run();
                for(int i = 0; i < count; i++){
                    jump();
                }
                turn();
            }
        }
        ```
    - 초보자 레벨 클래스 구현하기
        ```
        package gamelevel;

        public class BeginnerLevel extends PlayerLevel{
            @Override
            public void run(){
                System.out.println("천천히 달립니다");
            }

            @Override
            public void jump(){
                System.out.println("Jump할 줄 모르지롱");
            }

            @Override
            public void turn(){
                System.out.println("Turn할 줄 모르지롱");
            }
            
            @Override
            public void showLevelMessage(){
                System.out.println("***** 초보자 레벨입니다 *****");
            }
        }
        ```
    - 중급자 레벨 클래스 구현하기
        ```
        package gamelevel;

        public class AdvancedLevel extends PlayerLevel{
            @Override
            public void run(){
                System.out.println("빨리 달립니다");
            }

            @Override
            public void jump(){
                System.out.println("높이 Jump합니다");
            }

            @Override
            public void turn(){
                System.out.println("Turn할 줄 모르지롱");
            }

            @Override
            public void showLevelMessage(){
                System.out.println("***** 중급자 레벨입니다 *****");
            }
        }
        ```
    - 고급자 레벨 클래스 구현하기
        ```
        package gamelevel;

        public class SuperLevel extends PlayerLevel{
            @Override
            public void run(){
                System.out.println("엄청 빨리 달립니다");
            }

            @Override
            public void jump(){
                System.out.println("아주 높이 Jump합니다");
            }

            @Override
            public void turn(){
                System.out.println("한 바퀴 돕니다");
            }

            @Override
            public void showLevelMessage(){
                System.out.println("***** 고급자 레벨입니다 *****");
            }
        }
        ```
    - 테스트 프로그램 작성해서 실행하기
        ```
        package gamelevel;
        public class MainBoard {
            public static void main(String[] args) {
                Player player = new Player(); // 처음 생성: BeginnerLevel
                player.play(1); // 입력된 값(1)만큼 jump
                
                AdvancedLevel aLevel = new AdvancedLevel(); // aLevel: 중급자 레벨 클래스
                player.upgradeLevel(aLevel); // aLevel 참조변수를 upgradeLevel() 메서드에 대입 -> 레벨 업그레이드
                player.play(2);
                
                SuperLevel sLevel = new SuperLevel();
                player.upgradeLevel(sLevel);
                player.play(3);
            }
        }
        ```  
        ![image](https://user-images.githubusercontent.com/104348646/196071671-a1ad179a-483e-4b2f-ad22-e97844034397.png)

* 추상 클래스와 다형성
    - 상위 클래스인 추상 클래스는 하위에 구현된 여러 클래스를 하나의 자료형(상위 클래스 자료형)으로 선언/대입 가능

## 09-4 final 예약어
* final 예약어의 사용
    |사용 위치|설명|
    |----|-----------|
    |변수| final 변수는 상수를 의미하고, 단 한번만 값을 할당할 수 있음|
    |메서드| final 메서드는 하위 클래스에서 재정의할 수 없음|
    |클래스| final 클래스는 상속할 수 없음|

* 상수를 의미하는 final 변수
    ```
    package fianlex;

    public class Constant{
        int num = 10;
        final int NUM = 100; // 상수 선언
        
        public static void main(String[] args){
            Constant cons = new Constant();
            cons.num = 50;
            cons.NUM = 200; // error: 상수에 값을 대입함
            
            System.out.println(cons.num);
            System.out.println(cons.NUM);
        }
    }
    ```
    - 여러 자바 파일에서 공유하는 상수 값 정의하기
        + public 예약어로 선언함 -> 외부에서 사용 가능
        + static으로 선언함 -> 인스턴스를 생성하는 것과 관계없이 클래스 이름으로 참조 가능
        ```
        # 여러 파일에서 공유하는 상수

        package finalex;

        public class Define{
            public static final int MIN = 1;
            public static final int Max = 99999;
            public static final int ENG = 1001;
            public static final int MATH = 2001;
            public static final doule PI = 3.14;
            public static final String GOOD_MORNING = "Good Morning!";
        }
        ```
        ```
        # 상수 사용하기

        package finalex;

        public class UsingDefine{
            public static void main(String[] args){
                System.out.println(Define.GOOD_MORNING);
                System.out.println("최솟값은 " + Define.MIN + "입니다");
                System.out.println("최댓값은 " + Define.MAX + "입니다");
                System.out.println("수학 과목 코드 값은 " + Define.MATH + "입니다");
                System.out.println("영어 과목 코드 값은 " + Define.ENG + "입니다");
            }

        }
        ```
        
* 상속할 수 없는 final 클래스
    - 보안과 관련되어 있거나 기반 클래스가 변하면 안 되는 경우에 사용
    - ex) JAVA의 String, Integer 클래스

* 프로그램을 잘 구현하는 또 다른 방법
    - 테스트 주도 개발(TDD: Test Driven Development)  
        : 테스트 코드(최종 실행 파일)부터 개발하는 개발 방법론
        ```
        package test;

        public class MainBoard{
            public static void Main(String[] args){
                Player player = new Player(); // error -> 코드를 수정하면서 프로그램을 구현
                player.play(1);

                AdvancedLevel aLevel = new AdvancedLevel();
                player.upgradeLevel(aLevel); // same error
                player.play(2);

                SuperLevel sLevel = new SuperLevel();
                player.upgradeLevel(sLevel); // same error
                player.play(3);
            }
        }
        ```
