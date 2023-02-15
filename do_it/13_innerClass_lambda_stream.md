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
  
![image](https://user-images.githubusercontent.com/104348646/218961839-48f9ed25-54d0-4a19-ab09-4c76bcc8901c.png)  

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
```
## 함수형 인터페이스 선언하기

package lambda;

public interface MyNumber {
    int getMax(int num1, int num2); // 추상 메서드 선언
}

## 람다식 구현과 호출

public class TestMyNumber{
    public static void main(String[] args){
        MyNumber max = (x, y) -> (x >= y) ? x : y; // max: 인터페이스형 변수
        System.out.println(max.getMax(10, 20)); // 인터페이스형 변수로 메서드 호출
    }
}

### cf) 람다식을 적용하지 않은 이전 코드

(x, y) -> {
    if(x >= y) return x;
    else return y;
        }
```

  - 함수형 프로그래밍은 순수 함수를 구현하고 호출 -> 외부 자료에 부수적인 영향(side effect)를 주지 않음
    + 순수함수(pure function): 매개변수만을 사용해서 만드는 함수
    + 함수를 기반으로, 자료를 입력받아 구현하는 방식
    + 외부 자료에 영향을 미치지 않음 -> 병렬 처리, 안정되고 확장성 있는 프로그램 개발 가능
    + 함수 기능이 자료에 독립적일 수 있도록 보장함 -> 다양한 자료에 같은 기능 수행 가능

* 함수형 인터페이스  
  - 메서드 이름이 없음 -> 메서드 선언 및 구현 어려움 -> 함수형 인터페이스 사용 및 메서드 선언  
    ```
    package lambda;

    public interface MyNumber {
        int getMax(int num1, int num2);
        // int add(int num1, int num2); // 람다식은 하나의 메서드를 구현함 -> 인터페이스에서 두 개 이상의 메서드는 가질 수 없음
    }
    ```
  - @FunctionalInterface 애노테이션  
    : 함수형 인터페이스임을 명시함 -> 메서드가 두 개 이상 선언되면 error를 표시함
    + 필수는 아님. 오류 방지용
  
* 객체 지향 프로그래밍 방식과 람다식 비교
```
## 인터페이스 구현

package lambda;

public interface StringConcat {
    public void makeString(String s1, String s2);
}

## 1. 클래스에서 인터페이스 구현하는 방법
### 추상 메서드 구현

package lambda;

public class StringConcatImpl implements StringConcat{
    @Override
    public void makeString(String s1, String s2){
        System.out.println(s1 + ", " + s2);
    }
}
### 메서드 테스트

package lambda;

public class TestStringConcat{
    public static void main(String[] args){
        String s1 = "Hello";
        String s2 = "World";
        String StringConcatImpl concat1 = new StringConcatImpl(); // 인스턴스 생성
        concat1.makeString(s1, s2); // @@ "Hello, World"
    }
}

## 2. 람다식으로 인터페이스 구현하는 방법
### 메서드를 하나만 포함하는 함수형 인터페이스만 사용 가능

package lambda;

public class TestStringConcat{
    public static void main(String[] args){
        String s1 = "Hello";
        String s2 = "World";
        StringConcat concat2 = (s, v) -> System.out.println(s + ", " + v);
        concat2.makeString(s1, s2);
    }
}
```

* 익명 객체를 생성하는 람다식
  - 플로우: 람다식으로 메서드 구현 및 호출 -> 컴퓨터 내부에서 익명 클래스 생성 -> 익명 객체 생성
    + 익명 내부 클래스는 클래스 이름 없이 인터페이스 자료형 변수에 바로 메서드 구현부를 생성 및 대입 가능
    ```
    String StringConcatImpl concat3 = new StringConcatImpl(){
        @Override
        public void makeString(String s1, String s2){
            System.out.println(s1 + ", " + s2);
        }
    };
    ```
  - 람다식에서 사용하는 지역 변수
    ```
    ## 외부 메서드의 지역 변수인 i를 수정하는 경우
    
    public class TestStringConcat{
        public static void main(String[] args){
            ...
            int i = 100;

            StringConcat concat2 = (s, v) -> {
                // i = 200; // error
                System.out.println(i);
                System.out.println(s + ", " + v);
            };
        }
    }
    ```
    + 지역변수는 메서드 호출이 끝나면 메모리에서 사라짐 -> 익명 내부 클래스 내에서는 지역 변수가 상수로 변함(람다식도 동일)

* 함수를 변수처럼 사용하는 람다식
  |변수를 사용하는 경우|예시|
  |--------------------|----|
  |특정 자료형으로 변수 선언 후 값 대입해서 사용|int a = 10;|
  |매개변수로 전달|int add(int x, int y);|
  |메서드의 반환 값으로 반환|return num;|
    
  - 인터페이스형 변수에 람다식 대입하기
    ```
    PrintString lambdaStr  = s -> System.out.println(s);
    lambdaStr.showString("hello lambda_1");
    ```
  - 매개변수로 전달하는 람다식
    ```
    package lambda;

    public interface PrintString {
        void showString(String str);
    }

    public class TestLambda{
        public static void main(String[] args){
            // 람다식: 인터페이스형 변수에 대입 + 람다식 구현부 호출
            PrintString lambdaStr = s -> System.out.println(s);
            lambdaStr.showString("hello lambda_1");
            showMyString(lambdaStr); // 메서드의 매개변수로 람다식을 대입한 변수 전달 @@ "hello lambda_1"
        }
        public static void showMyString(PrintString p){
            p.showString("hello lambda_2"); // @@ "hello lambda_2"
        }
    }
    ```
  - 반환 값으로 쓰이는 람다식
    ```
    package lambda;

    public interface PrintString {
        void showString(String str);
    }

    public class TestLambda{
        public static void main(String[] args){
            ...
            PrintString reStr = returnString(); // 변수로 반환받기
            reStr.showString("hello"); // 메서드 호출
        }
        public static void showMyString(PrintString p){
            p.showString("hello lambda_2"); // @@ "hello lambda_2"
        }
        public static PrintString returnString(){
            return s -> System.out.println(s + "world"); // 변수처럼 사용 가능 @@ "hello world"
        }
    }
    ```

## 13-3 스트림
* 스트림(stream)  
  : 여러 자료 처리(정렬, 필터 등)에 대한 기능을 구현해 놓은 클래스
  ```
  int[] arr = {1, 2, 3, 4, 5};
  Arrays.stream(arr).forEach(n -> System.out.println(n));
  
  // Arrays.stream(arr): 스트림 생성 부분
  // forEach(n -> System.out.println(n)): 요소를 하나씩 꺼내서 출력하는 기능
  ```

* 스트림 연산
  - 종류
    + 중간 연산: 자료를 거르거나 변경해서 또 다른 자료를 내부적으로 생성
    + 최종 연산: 생성된 내부 자료를 소모해 가면서 연산을 수행
  - 중간 연산
    : 조건에 맞는 결과를 추출해 내는 중간 역할
    + filter(): 조건을 넣고 그 조건에 맞는 참인 경우만 추출
      ```
      ## 문자열의 길이가 5 이상인 경우만 출력
      
      sList.stream().filter(s -> s.length() >= 5).forEach(s -> System.out.println(s));
      
      // 스트림 생성: sList.stream()
      // 중간 연산: filter(s -> s.length() >= 5)
      // 최종 연산: forEach(s -> System.out.println(s))
      ```
    + map()  
      : 요소들을 순회해서 다른 형식으로 변환 가능
      ```
      ## 고객 이름만 가져와서 출력
      
      customerList.stream().map(c -> c.getName()).forEach(s -> System.out.println(s));
      
      // 스트림 생성: customerList.stream()
      // 중간 연산: map(c -> c.getName())
      // 최종 연산: forEach(s -> System.out.println(s))
      ```
  - 최종 연산  
    : 결과를 만드는 데 사용
    + forEach()
    + count()
    + sum()
    + reduce()

* 스트림 생성하고 사용하기
  - 정수 배열에 스트림 생성하고 사용하기
    ```
    package stream;

    import java.util.Arrays;

    public class IntArrayTest {
        public static void main(String[] args){
            int[] arr = {1, 2, 3, 4, 5};

            int sumVal = Arrays.stream(arr).sum();
            int count = (int)Arrays.stream(arr).count();

            System.out.println(sumVal); // @@ 15
            System.out.println(count); // @@ 5
        }
    }
    ```
  - Collection에서 스트림 생성하고 사용하기
    + 생성  
      : Stream<E> stream() -> 스트림 클래스를 반환하는 메서드
     ```
    List<String> sList = new ArrayList<String>();
    sList.add("Tomas");
    sList.add("Edward");
    sList.add("Jack");

    // 자료형 명시

    Stream<String> stream = sList.stream();
    ```
  + 사용
    ```
    // forEach(): 각 요소를 하나씩 출력
    
    stream.forEach(s -> System.out.println(s));
  
    // sorted(): 정렬
  
    Stream<String> stream2 = sList.stream();
    stream2.sorted().forEach(s -> System.out.println(s)); // 최종 연산 출력: forEach()
    ```

* 스트림의 특징
  - 자료의 대상과 관계없이 동일한 연산을 수행한다
    + 여러 자료 구조에 대해 요소 값 출력, 조건에 맞는 자료 추출 등의 작업을 일관성 있게 처리할 수 있는 메서드를 제공
  - 한 번 생성하고 사용한 스트림은 재사용할 수 없다
    + 어떤 자료에 대한 스트림을 생성하고 이 스트림에 메서드를 호출하여 연산을수행한 경우, 해당 스트림을 다시 다른 연산에 사용할 수 없음
  - 스트림의 연산은 기존 자료를 변경하지 않는다
    + 스트림 연산을 위해 사용하는 메모리 공간이 별도로 존재함
  - 스트림의 연산은 중간 연산과 최종 연산이 있다
    + 스트림에 중간 연산은 여러 개 적용 가능, 최종 연산은 맨 마지막에 한 번 적용
    + 지연 연산(lazy evaluation): 중간 연산은 호출되었지만 최종 연산이 호출되지 않은 경우, 결과를 가져올 수 없음

* reduce() 연산  
  : 내부적으로 스트림의 요소를 하나씩 소모하면서 프로그래머가 직접 지정한 기능을 수행함
  - reduce() 메서드의 정의
    ```
    T reduce(T identify, BinaryOperator<T> accumulator)
  
    // T identify: 초깃값
    // BinaryOperator<T> accumulator: 수행해야 하는 기능
    /// BinaryOperator 인터페이스: 두 매개변수로 람다식을 구현(= 수행해야 할 기능)
    /// apply() 메서드 구현 필수 -> apply(): 두 개의 매개변수와 한 개의 반환값을 가짐(세 개 모두 같은 자료형임)
    ```
  - 예시
    ```
    Arrays.stream(arr).reduce(0, (a, b) -> a + b);
  
    // 0: 초깃값
    // (a, b) -> a + b: 각 요소가 수행해야 할 기능
    // (a, b): 전달되는 요소
    ```
  ```
  ## reduce() 사용하기
  
  package stream;

  import java.lang.reflect.Array;
  import java.util.function.BinaryOperator;

  public class CompareString implements BinaryOperator<String> {
      @Override
      // reduce() 메서드가 호출될 때 불리는 메서드: 두 문자열 길이를 비교(바이트 수가 더 긴 문자열을 반환)
      public String apply(String s1, String s2){
          if(s1.getBytes().length >= s2.getBytes().length) return s1;
          else return s2;
      }
  }

  public class ReduceTest{
      public static void main(String[] args){
          String[] greetings = {"안녕하세요", "hello", "Good Morning", "반갑습니다"};
          // 람다식을 직접 구현하는 방법
          System.out.println(Array.stream(greetings).reduce("", (s1, s2) -> {
              if(s1.getBytes().length <= s2.getBytes().length)
                  return s1;
              else return s2;
          })); // @@ "안녕하세요"

          // BinaryOperator를 구현한 클래스를 사용하는 방법
          String str = Array.stream(greeting).reduce(new CompareString()).get();
          System.out.println(str); // @@ "안녕하세요"
      }
  }
  ```

* 스트림을 활용하여 여행객의 여행 비용 계산하기
  ```
  ## 고객 클래스 정의

  package stream;

  public class TravelCustomer {
      private String name;
      private int age;
      private int price;

      public TravelCustomer(String name, int age, int price){
          this.name = name;
          this.age = age;
          this.price = price;
      }

      public String getname(){
          return name;
      }

      public int getAge(){
          return age;
      }

      public int getPrice(){
          return price;
      }

      public String toString(){
          return name + ", " + age + ", " +  price;
      }
  }                                                             
  ```
  ```
  package stream;

  import java.util.ArrayList;
  import java.util.List;

  public class TravelTest {
      public static void main(String[] args){
          // 고객 생성
          TravelCustomer customerLee = new TravelCustomer("Lee", 40, 100);
          TravelCustomer customerKim = new TravelCustomer("Kim", 20, 100);
          TravelCustomer customerHong = new TravelCustomer("Hong", 13, 50);

          // ArrayList에 고객 추가
          List<TravelCustomer> customerList = new ArrayList<>();
          customerList.add(customerLee);
          customerList.add(customerKim);
          customerList.add(customerHong);

          // 고객 명단 출력
          customerList.stream().map(c -> c.getname()).forEach(s -> System.out.println(s));
          // @@ "Lee"
          // @@ "Kim"
          // @@ "Hong"

          // 여행의 총 비용 계산
          int total = customerList.stream().mapToInt(c -> c.getPrice()).sum(); // mapToInt: 값을 정수로 변환
          System.out.println("총 여행 비용: " + total + "원"); // @@ 총 여행 비용: 250원

          // 고객 중 20세 이상인 사람의 이름을 정렬해서 출력
          customerList.stream().filter(c -> c.getAge() >= 20)
                  .map(c -> c.getname()).sorted().forEach(s -> System.out.println(s));
          // @@ "Lee"
          // @@ "Kim"
      }
  }                                                          
  ```
