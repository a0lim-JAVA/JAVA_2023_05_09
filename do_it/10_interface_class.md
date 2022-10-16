## 10-1 인터페이스란
* 구현 코드가 없는 인터페이스
    - 인터페이스(interFace)  
        : 모든 메서드가 추상 메서드와 상수로만 이루어진 클래스
        + 형식적인 선언만 있고, 구현은 없음 -> 잉ㄴ스턴스 생성 불가능
        + 클래스/프로그램이 제공하는 기능을 명시적으로 선언하는 역할
    - 인터페이스 만들기  
        ![image](https://user-images.githubusercontent.com/104348646/196037184-6a970025-ddc5-4e69-becf-1dc2d709b335.png)
        ```
        # Calc 인터페이스 만들기

        package interfaceex;

        public interface Calc {
            // 인터페이스에서 선언한 변수: 컴파일 과정에서 상수로 변환됨(public static fianl 예약어 필요 없음)
            double PI = 3.14;
            int ERROR = -999999999;
            
            // 인터페이스에서 선언한 메서드: 컴파일 과정에서 추상 메서드로 변환됨
            int add(int num1, int num2);
            int subtract(int num1, int num2);
            int times(int num1, int num2);
            int divide(int num1, int num2);
        }
        ```

* 클래스에서 인터페이스 구현하기(implements)  
    = 선언한 인터페이스를 클래스가 사용
    ```
    # Calc 인터페이스를 Calculator 클래스에서 구현
    package interfaceex;

    public class Calculator implements Calc{
        // error: 추상 메서드 구현 or Calculator 클래스를 추상 클래스로 생성 <- 추상 메서드 4개 포함됨

    }
    ```
    - Calc 인터페이스에 선언한 메서드를 구현해서 추상 클래스로 만든 후, 이를 상속하는 클래스 생성
    ```
    # 인터페이스 구현하기

    public abstract class Calculator implements Calc{ // 추상 클래스
        @Override
        public int add(int num1, int num2){
            return num1 + num2;
        }
        
        @Override
        public int substract(int num1, int num2){
            return num1 - num2;
        }
    }
    ```

* 클래스 완성하고 실행하기
    ```
    # 계산기 클래스 만들기

    package interfaccex;

    public class CompleteCalc extends Calculator{
        @Override
        public int times(int num1, int num2){
            return num1 * num2;
        }

        @Override
        public int divide(int num1, int num2){
            if(num2 != 0)
                return num1 - num2;
            else
                return Calc.ERROR;
        }
        
        // CompleteCalc에서 추가로 구현한 메서드
        public void showInfo(){
            System.out.println("Calc 인터페이스를 구현하였습니다");
        }
    }
    ```
    ```
    # (Test) CompleteCalc 클래스 실행하기

    package interfaceex;

    public class CalculatorTest{
        public static void main(String[] args){
            int num1 = 10;
            int num2 = 5;
            
            CompleteCalc calc = new CompleteCalc();
            System.out.println(calc.add(num1, num2)); // @@ 15
            System.out.println(calc.substract(num1, num2)); // @@ 5
            System.out.println(calc.times(num1, num2)); // @@ 50
            System.out.println(calc.divide(num1, num2)); // @@ 2
            calc.showInfo(); // 구체적인 클래스(CompleteCalc)만 인스턴스 생성 가능 @@ Calc 인터페이스를 구현하였습니다
        }
    }
    ```
