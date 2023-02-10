## 13-1 내부 클래스
* 내부 클래스
  - (inner class)(= 중첩 클래스)  
    : 클래스 내부에 선언한 클래스  
    + 외부 클래스와 밀접한 관련이 있음
    + 그 밖의 다른 클래스와 협력할 일이 없는 경우에 사용
    ```
    class Out{ // 외부 클래스
      class In{ // 내부 클래스
        ...
      }
    }
    ```
  - 내부 클래스의 유형  
     : 선언하는 위치, 예약어에 따라 나뉨  
    + 인스턴스 내부 클래스
    + 정적(static) 내부 클래스
    + 지역(local) 내부 클래스
    + 익명(anonymous) 내부 클래스  
      : 클래스 이름 없이 선언하고 바로 생성하여 사용  

  - 변수 유형과 내부 클래스 유형 비교
  ```
  // 변수

  class ABC{
      int n1; // 인스턴스 변수
      static iint n2; // 정적 변수

      public void abc(){
          int i; // 지역 변수
      }
  }
  ```
  ```
  // 내부 클래스

  class ABC{// 외부 클래스
      class In {  // 인스턴스 내부 클래스
          static class Sln{ // 정적 내부 클래스
          }
      }
      public void abc(){
          class Local{ // 지역 내부 클래스
          }
      }
  }
  ```

* 인스턴스 내부 클래스(instance inner class)  
  : 인스턴스 변수를 선언할 때와 같은 위치에 선언하며, 외부 클래스 내부에서만 생성하여 사용하는 객체를 선언할 때 사용  
  - 다른 외부 클래스에서 사용할 일이 없는 경우 정의함
  - 외부 클래스 생성 후 생성됨
    + cf) <-> 정적 내부 클래스
    ```
    package innerClass;

    public class OutClass { // 외부 클래스
        private int num = 10; // 외부 클래스 - private 변수
        private static int sNum = 20; // 외부 클래스 - 정적 변수

        private InClass inClass; // 내부 클래스 자료형 변수를 먼저 선언

        // 외부 클래스 디폴트 생성자: 외부 클래스가 생성된 후에 내부 클래스 생성 가능
        public Outclass(){
            inClass = new inClass();
        }

        class InClass { // 인스턴스 내부 클래스
            int inNum = 200; // 내부 클래스 - 인스턴스 변수
            //static int sInNum = 200; // 인스턴스 내부 클래스에 정적 변수 선언 불가능

            void inTest(){
                System.out.println("OutClass num = " + num);
                System.out.println("OutClass num = " + sNum);
            }

            // 인스턴스 내부 클래스에 정적 메서드 선언 불가능
            //static void sTest(){
            //}

        }
        public void usingClass(){
            inClass.inTest();
        }
    }

    public class InnerTest{
        public static void main(String[] args){
            OutClass outclass = new inClass();
            outClass.usingClass(); // 내부 클래스 기능 호출
        }
    }
    ```

* 정적 내부 클래스(static inner Class)
  - 외부 클래스 생성과 무관하게 사용 + 정적 변수 사용
  ```
  package innerclass;

  class OutClass{
      private int num = 10;
      private static int sNum = 20;

      static class InStaticClass{
          int inNum = 100;
          static int sInNum = 200;

          // 정적 내부 클래스의 일반 메서드
          void inTest(){
              // num += 10; // 외부 클래스의 인스턴스 변수 사용 불가
              System.out.println("InStaticClass inNum = " + inNum); // 내부 - 인스턴스 변수 @@ 100
              System.out.println("InStaticClass sInNum = " + sInNum); // 내부 - 정적 변수 @@ 200
              System.out.println("InStaticClass sNum = " + sNum); // 외부 - 정적 변수 @@ 20
          }

          // 정적 내부 클래스의 정적 메서드
          static void sTest(){
              // num += 10;
              // inNum += 10; // 외부+내부 클래스의 인스턴스 변수 사용 불가
              System.out.println("OutClass sNum = " + sNum);
              System.out.println("InStaticClass sInNum = " + sInNum);
          }
      }
  }

  public class InnerTest{
      public static void main(String[] args){
          ...
          OutClass.InStaticClass sInClass = new OutClass.InStaticClass(); // 외부 클래스를 생성하지 않고 바로 정적 내부 클래스 생성 가능
          sInClass.inTest(); // 외부 - 정적 변수 @@ 20
          OutClass.InStaticClass.sTest(); // 내부 - 정적 변수 @@ 200
      }
  }
  ```
  - 다른 클래스에서 정적 내부 클래스 사용하기
    ```
    // 외부 클래스를 생성하지 않고 내부 클래스 자료형으로 바로 선언 + 생성 가능
    OutClass.InStaticClass sInClass = new OutClass.InStaticClass();
    
    // 정적 내부 클래스에 선언한 메서드/변수는 private이 아닌 경우에, 다른 클래스에서도 바로 사용 가능
    OutClass.InStaticClass.sTest();
    ```

* 지역 내부 클래스  
: 지역 변수처럼 메서드 내부에 클래스를 정의하여 사용하는 것
  ```
  ## Runneable 인터페이스를 구현하는 클래스를 지역 내부 클래스로 만듦

  package innerClass;

  class Outer {
      int outNum = 100;
      static int sNum = 200;

      Runnable getRunnable(int i){
          int num = 100; // 지역변수

          class MyRunnable implements Runnable{ // 지역 내부 클래스
              int localNum = 10; // 지역 내부 클래스의 인스턴스 변수

              @Override
              public void run(){
                  // num = 200; // 지역 변수는 상수로 바뀜 -> 값을 변경할 수 없음
                  // i = 100; // 매개변수도 상수로 바뀜 -> ㄱ밧을 변경할 수 없음

                  System.out.println(i); // @@ 10
                  System.out.println(num); // @@ 100
                  System.out.println(localNum); // @@ 10
                  System.out.println(outNum); // 외부 클래스 인스턴스 변수 @@ 100
                  System.out.println(Outer.sNum); // 외부 클래스 정적 변수 @@ 200
              }
          }

          return new MyRunnable();

      }
  }

  public class LocalInnerTest{
      public static void main(String[] args){
          Outer out = new Outer();
          Runnable runner = out.getRunnable(10); // 메서드 호출
          runner.run();
      }
  }
  ```

  - 지역 내부 클래스에서 지역 변수의 유효성  
    : 지역 내부 클래스에서 사용하는 메서드의 지역 변수는 모두 상수로 바뀜  
    ```
        Outer out = new Outer();
        Runnable runner = out.getRunnable(10); // getRunnable() 메서드 호출이 끝남 -> 메모리에서 삭제됨
        runner.run(); // run()이 실행 -> getRunnable() 메서드의 지역 변수를 사용 => 메모리에서 삭제한 이후에도 지역 변수를 상수로 참조 가능
    ```

* 익명 내부 클래스  
  : 클래스 이름을 사용하지 않는 클래스
  ```
  package innerClass;

  class Outer2 {
      Runnable getRunnable(int i){
          int num = 100;

          // 익명 내부 클래스 Runnable
          return new Runnable(){ // 인터페이스 생성
              @Override
              public void run(){
                  // num = 200;
                  // i = 10;
                  System.out.println(i); // @@ 10
                  System.out.println(num); // @@ 100
              }
          }; // 클래스 종료
      }

      // 인터페이스나 추상 클래스형 변수를 선언하고 클래스를 생성해 대입하는 방법
      Runnable runner = new Runnable(){ // 익명 내부 클래스를 변수에 대입
          @Override
          public void run(){
              System.out.println("Runnable이 구현된 익명 클래스 변수");
          }
      }; // 클래스 종료
  }

  public class AnonymousInnerTest{
      public static void main(String[] args){
          Outer2 out = new Outer2();
          Runnable runnable = out.getRunnable(10);
          runnable.run();
          out.runner.run();
      }
  }
  ```
  
  - 익명 내부 클래스의 활용
    + 안드로이드 프로그래밍에서 위젯의 이벤트를 처리하는 핸들러 구현
      ```
      ## 버튼을 눌렀을 때 'hello ' 메시지를 띄우는 코드 예시
      
      button1.setOnClickListener(new View.OnClickListener(){ // new View.OnClickListener(): 이벤트 핸들러
        public boolean onClick(View V){ // onClick: 구현 메서드
          Toast.makeText(getBaseContext(), "hello ", Toast.LENGTH_LONG).show();
          return true;
        }
      });
      ```

(그림)

## 13-2 람다식

* 함수형 프로그래밍과 람다식  
  - 함수형 프로그래밍(Functional Programming, FP)  
    : 함수의 구현과 호출만으로 프로그램을 만들 수 있는 프로그래밍 방식
    + 자바 8부터 지원함
  - 람다식(Lambda expression)  
    : 자바에서 제공하는 함수형 프로그래밍 방식

* 람다식 구현하기
  - 함수 이름이 없는 익명 함수를 만듦
    + (매개변수) -> {실행문;}
    ```
    # 기존 함수
    
    int add(int x, int y){
      return x + y;
    }
    
    # 람다식
    
    (int x, int y) -> {return x + y;}
    ```
  - ```->``` 기호를 사용해서 구현함

* 람다식 문법 살펴보기
  - 매개변수 자료형과 괄호 생략하기
    ```
    str -> {System.out.println(str);} // 매개변수가 1개인 경우, 괄호 생략 가능
    ```
  - 중괄호 생략하기
    + return 문: 중괄호 생략 불가능
    ```
    str -> System.out.println(str);
    ```
  - return 생략하기
    + 중괄호 안의 구현 부분이 return문 하나인 경우, 중괄호 + return을 모두 생략
    ```
    (x, y) -> x + y
    str -> str.length()
    ```

* 람다식 사용하기










