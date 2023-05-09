## 10-1 인터페이스란
* 구현 코드가 없는 인터페이스
    - 인터페이스(interFace)  
        : 모든 메서드가 추상 메서드와 상수로만 이루어진 클래스
        + 형식적인 선언만 있고, 구현은 없음 -> 인스턴스 생성 불가능
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
    ![image](https://user-images.githubusercontent.com/104348646/196173213-a08c49bb-32d5-4631-b464-2682133a9c3f.png)  
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

* 인터페이스 구현과 형 변환
    - 인터페이스를 구현한 클래스는 인터페이스 형으로 선언한 변수로 형 변환 가능
        + cf) 상속 관계에서의 묵시적 형 변환
        + ex) CompleteCalc 클래스: Calculator형 & Calc형
        ```
        Calc calc = new CompleteCalc(); // Calc형으로 선언한 변수에 대입 가능
        ```  
        ![image](https://user-images.githubusercontent.com/104348646/196173366-de8bcc1b-c099-499e-8019-7636e6280b93.png)  
        + UserInfoWeb은 IUserInfoDao에 정의된 메소드 명세만 보고 Dao를 사용할 수 있고, Dao 클래스들은 IUserInfoDao에 정의된 메소드를 구현할 책임이 있다
    - 구현 코드가 없기 때문에, 여러 인터페이스 구현 가능
        + <-> 클래스 상속
    - 형 변환 시 사용할 수 있는 메서드는 인터페이스에 선언된 메서드만 사용할 수 있음
        + ex) Calc형으로 선언한 변수에서 사용할 수 있는 메서드는 Calc 인터페이스에 선언한 메서드뿐  
        ![image](https://user-images.githubusercontent.com/104348646/196173405-a49fba5a-b5dd-4c3f-a7b2-ffb817d01885.png)  

## 10-2 인터페이스와 다형성
* 인터페이스의 역할
    - "Client Program"에 어떤 메서드를 제공하는지 미리 알려주는 명세(specification) -> interface의 정의만을 보고 사용 가능
    - "Client Code"와 서비스를 제공하는 "객체" 사이의 약속
    - 한 객체가 어떤 interface 타입이다 = 그 interface가 제공하는 메서드를 구현했다

* 인터페이스와 다형성
    - 인터페이스를 사용하면 다형성을 구현해 확장성 있는 프로그램 개발 가능
    - Client Program에 기능 추가/다른 기능 사용 가능
    - 고객 상담 전화 배분 프로그램  
        ![image](https://user-images.githubusercontent.com/104348646/196173790-04c116c2-6a96-438b-965b-6a4559cfb7b2.png)  
        + Scheduler 인터페이스: 상담원에게 전화 업무를 배분하는 기능 구현  
        ![image](https://user-images.githubusercontent.com/104348646/196173456-99518a05-4f36-44d0-b4d4-537faddfc278.png)
        ```
        # Scheduler 인터페이스 정의하기

        package scheduler;

        public interface Scheduler { // 추상 메서드 선언
            public void getNextCall();
            public void sendCallToAgent();
        }
        ```
        ```
        # RoundRobin 클래스 구현

        package scheduler;

        public class RoundRobin implements Scheduler{
            // 상담원 한 명씩 돌아가며 동일하게 상담 업무 배분
            @Override
            public void getNextCall(){
                System.out.println("상담 전화를 순서대로 대기열에서 가져옵니다");
            }
            
            @Override
            public void sendCallToAgent(){
                System.out.println("다음 순서 상담원에게 배분합니다");
            }
        }
        ```
        ```
        # LeastJob 클래스 구현

        package scheduler;

        // 현재 상담 업무가 없거나 대기가 가장 적은 상담원에게 배분
        public class LeastJob implements Scheduler{
            @Override
            public void getNextCall(){
                System.out.println("상담 순서대로 대기열에서 가져옵니다");
            }
            
            @Override
            public void sendCallToAgent(){
                System.out.println("현재 상담 업무가 없거나 대기가 가장 적은 상담원에게 할당합니다");
            }
        }
        ```
        ```
        # PriorityAllocation 클래스 구현

        package scheduler;

        // 고객 등급이 높은 고객의 전화부터 대기열에서 가져와 업무 skill 값이 높은 상담원에게 우선 배분
        public class PriorityAllocation implements Scheduler{
            @Override
            public void getNextCall(){
                System.out.println("고객 등급이 높은 고객의 전화를 먼저 가져옵니다");
            }

            @Override
            public void sendCallToAgent(){
                System.out.println("업무 skill 값이 높은 상담원에게 우선적으로 배분합니다");
            }
        }
        ```
        ```
        # (Test) 입력 문자에 따라 배분 정책 수행하기

        package scheduler;

        import java.io.IOException;

        public class SchedulerTest{
            public static void main(String[] args) throws IOException {
                // IOException: 문자를 입력받는 System.in.read() 사용을 위한 오류 처리
                System.out.println("전화 할당 방식을 선택하게요");
                System.out.println("R: 한명씩 차례로 할당");
                System.out.println("L: 쉬고 있거나 대기가 가장 적은 상담원에게 할당");
                System.out.println("P: 우선순위가 높은 고객 먼저 할당");

                int ch = System.in.read(); // 할당 방식을 입력받아 ch 변수에 대입
                Scheduler scheduler = null;

                if(ch == 'R' || ch == 'r'){ // 'R'을 입력 받으면 RoundRobin 클래스 생성
                    scheduler = new RoundRobin();
                }
                else if(ch == 'L' || ch == 'l'){ // 'L'을 입력 받으면 LeastJob 클래스 생성
                    scheduler = new LeastJob();
                }
                else if(ch == 'P' || ch == 'p'){ // 'P'을 입력 받으면 PriorityAllocation 클래스 생성
                    scheduler = new PriorityAllocation();
                }
                else{
                    System.out.println("지원되지 않는 기능입니다");
                    return;
                }

                // 어떤 정책인가와 상관 없이 인터페이스에 선언한 메서드 호출
                scheduler.getNextCall();
                scheduler.sendCallToAgent();
            }
        }
        ```  
        ![image](https://user-images.githubusercontent.com/104348646/196173843-dcdc90e2-43a7-4f85-9b23-2cf6a8e3ca3c.png)  
        
* 클라이언트가 클래스를 사용하는 방법
    - 새로운 배분 정책을 추가하는 경우, 다른 정책들과 마찬가리로 Scheduler 인터페이스를 구현하는 새 클래스로 생성
    ```
    // 어떤 클래스를 구현하든지, 인터페이스를 구현한 클래스를 사용하는 방식은 동일함(아래 코드)
    scheduler.getNextCall();
    scheduler.sendCallToAgent();
    ```

## 10-3 인터페이스 요소 살펴보기
* 인터페이스의 요소
    - 상수
    - 추상 메서드
    - 디폴트 메서드
    - 정적 메서드
    - private 메서드

* 인터페이스 상수
    - 인터페이스에 선언한 모든 변수는 컴파일 후에 상수로 변환됨
    ```
    public interface Calc{
        double PI = 4.12;
        int ERROR = -999999999;
        ... // 모두 상수로 변환
    }
    ```

* 디폴트 메서드와 정적 메서드
    - 추상 메서드
        + 모든 메서드는 추상 메서드로 구현됨
    - JAVA 8부터 제공
        + 디폴트 메서드
        + 정적 메서드

* 디폴트 메서드  
    : 기본 구현을 가지는 메서드
    - 인터페이스에서 구현하지만, 이후 인터페이스를 구현한 클래스가 생성되면 그 클래스에서 사용할 기본 기능
    - default 예약어 사용
        ```
        # Calc 인터페이스에 디폴트 메서드 구현하기

        package interfaceex;

        public interface Calc{
            ...
            default void description(){
                System.out.println("정수 계산기를 구현합니다");
            }
        }
        ```
        ```
        # 디폴트 메서드 호출하기

        package interfaceex;

        public class CalculatorTest{
            public static void main(String[] args){
                int num1 = 10;
                int num2 = 5;
                
                CompleteCalc calc = new CompleteCalc(); // CompleteCalc 클래스 생성
                System.out.println(calc.add(num1, num2)); // @@ 15
                System.out.println(calc.substract(num1, num2)); // @@ 5
                System.out.println(calc.times(num1, num2)); // @@ 50
                System.out.println(calc.divide(num1, num2)); // @@ 2
                calc.showInfo(); // @@ Calc 인터페이스를 구현하였습니다
                calc.description(); // calc 인스턴트: 디폴트 메서드 호출 @@ 정수 계산기를 구현합니다
            }
        }
        ```
        + 디폴트 메서드는 인터페이스에 이미 구현됨 -> 추상 클래스 Calculator에서 코드 구현 불필요
    - 구현 클래스에서 재정의하기
        + Calculator 클래스(Calc 인터페이스를 구현), CompleteCalc 클래스(Calculator 클래스의 하위클래스)에서도 재정의 가능
        ```
        public class CompleteCalc extends Calculator{
            ...
            @Override
            public void description(){
                // 디폴트 메서드 description()을 CompleteCalc 클래스에서 원하는 기능으로 재정의
                super.description();
            }
        }
        ```

* 정적 메서드
    - 인스턴스 생성과 상관 없이 인터페이스 타입으로 사용할 수 있는 메서드
    - static 예약어 사용
    - 인터페이스 이름으로 직접 참조해서 사용
        ```
        # 정적 메서드 구현하기

        package interfaceex;

        public interface Calc{
            ...
            static int total(int[] arr){
                // 정적 메서드 total: Calc 인터페이스에 매개변수로 전달된 배열의 모든 요소 값을 더함
                int total = 0;
                
                for(int i: arr){
                    total += i;
                }
                return total;
            }
        }
        ```
        ```
        # 정적 메서드 호출하기

        package interfaceex;

        public class CalculatorTest{
            public static void main(String[] args){
                int num1 = 10;
                int num2 = 5;

                CompleteCalc calc = new CompleteCalc(); // CompleteCalc 클래스 생성
                System.out.println(calc.add(num1, num2)); // @@ 15
                System.out.println(calc.substract(num1, num2)); // @@ 5
                System.out.println(calc.times(num1, num2)); // @@ 50
                System.out.println(calc.divide(num1, num2)); // @@ 2
                calc.showInfo(); // @@ Calc 인터페이스를 구현하였습니다
                
                calc.description(); // 디폴트 메서드 호출 @@ 정수 계산기를 구현합니다
                
                int[] arr = {1, 2, 3, 4, 5};
                System.out.println(Calc.total(arr)); // 정적 메서드 사용하기 @@ 15
            }
        }
        ```

* private 메서드
    - JAVA 9부터 제공
    - 인터페이스를 구현한 클래스에서 사용하거나 재정의 할 수 없음
    - 인터페이스 내부에서만 기능을 제공하기 위해 구현하는 메서드
    - 코드를 모두 구현해야 함 -> private 예약어 사용 X / static 예약어 사용 O
        ```
        # Calc 인터페이스에 private 메서드 구현하기

        package interfaceex;

        public interface Calc{
            ...
            default void description(){
                System.out.println("정수 계산기를 구현합니다");
                myMethod(); // 디폴트 메서드에서 private 메서드 호출
            }

            static int total(int[] arr){
                int total = 0;

                for(int i: arr){
                    total += i;
                }
                myStaticMethod(); // 정적 메서드에서 private static 메서드 호출
                return total;
            }

            private void myMethod(){
                System.out.println("private 메서드입니다"); // private 메서드
            }

            private static void myStaticMethod(){
                System.out.println("private 메서드 입니다"); // private static 메서드
            }
        }
        ```

## 10-4 인터페이스 활용하기
* 한 클래스가 여러 인터페이스를 구현하는 경우  
    ![image](https://user-images.githubusercontent.com/104348646/196173975-456b007c-285b-44af-9f60-0c6dacd279a7.png)  
    ```
    # 추상 메서드 Buy() 선언

    package interfaceex;

    public interface Buy{
        void buy();
    }

    # 추상 메서드 Sell() 선언

    package interfaceex;

    public interface Sell{
        void sell();
    }
    ```
    ```
    # Customer 클래스가 Buy(), Sell() 인터페이스 구현
    // 인터페이스는 구현 코드/멤버 변수를 갖지 않음 -> 여러 개 동시 구현 가능
    // Cuatomer 클래스: Buy형 & Sell형

    package interfaceex;

    public class Customer implements Buy, Sell{
        @Override
        public void sell(){
            System.out.println("판매하기");
        }
        
        @Override
        public void buy(){
            System.out.println("구매하기");
        }
    }
    ```
    ```
    # 테스트 프로그램

    package interfaceex;

    public class CustomerTest{
        public static void main(String[] args){
            Customer customer = new Customer();
            
            // customer(Customer 클래스형)을 buyer(Buy 인터페이스형)에 대입해 형 변환
            // buyer은 Buy 인터페이스의 메서드만 호출 가능
            Buy buyer = customer;
            buyer.buy();

            // customer(Customer 클래스형)을 seller(Sell 인터페이스형)에 대입해 형 변환
            // seller은 Sell 인터페이스의 메서드만 호출 가능
            Sell seller = customer;
            seller.sell();

            if(seller instanceof Customer){ // instanceof: 안전한 다운 캐스팅 형 변환 가능(상속과 같음)
                Customer customer2 = (Customer)seller; // seller를 하위 클래스형인 Customer로 다시 형 변환
                customer2.buy();
                customer2.sell();
            }
        }
    }
    ```

* 두 인터페이스의 디폴트 메서드가 중복되는 경우
    - Customer 클래스가 Buy, Sell 두 인터페이스를 구현하고, 이 안에 똑같은 pay() 정적 메서드가 있는 경우
        ```
        # 추상 메서드 Buy()

        package interfaceex;

        public interface Buy{
            void buy();
            
            default void order(){
                System.out.println("구매 주문");
            }
        }

        # 추상 메서드 Sell()

        package interfaceex;

        public interface Sell{
            void Sell();

            default void order(){
                System.out.println("판매 주문");
            }
        }
        ```
        + Customer 클래스 error: 디폴트 메서드 중복 -> 두 인터페이스를 구현하는 Customer 클래스에서 재정의 필요
        ```
        # 디폴트 메서드 order()를 Customer 클래스에서 재정의

        package interfaceex;

        public class Customer implements Buy, Sell{
            ...
            // 디폴트 메서드 order()를 Customer 클래스에서 재정의함
            @Override
            public void order(){
                System.out.println("고객 판매 주문");
            }
        }
        ```
        ```
        #  Customer에서 재정의된 order()가 호출됨
        
        package interfaceex;

        public class CustomerTest{
            public static void main(String[] args){
                Customer customer = new Customer();
                
                Buy buyer = customer;
                buyer.buy(); // @@ 구매하기
                buyer.order(); // 재정의된 order() 메서드 호출 @@ 고객 판매 주문
                
                Sell seller = Customer;
                seller.sell(); // @@ 판매하기
                seller.order(); // 재정의된 order() 메서드 호출 @@ 고객 판매 주문
                
                if(seller instanceof Customer){
                    Customer customer2 = (Customer)seller;
                    customer2.buy(); // @@ 구매하기
                    customer2.sell(); // @@ 판매하기
                }
                customer.order(); // 재정의된 order() 메서드 호출 @@ 고객 판매 주문
            }
        }
        ```

* 인터페이스 상속하기
    - 형 상속(type inheritance)  
        : 구현 코드를 통해 기능을 상속하지 않음
    - 인터페이스 간에도 상속 가능
    - 여러개를 동시에 상속 받기 가능
    - 인터페이스를 정의할 때, 기능상 계층 구조가 필요한 경우에 사용  
    ![image](https://user-images.githubusercontent.com/104348646/196174646-a1be26cd-deb9-4ffe-83fb-ca3bd9909ca2.png)  
        ```
        # 인터페이스 X에 추상 메서드 x()를 선언

        package interfaceex;

        public interface X{
            void x();
        }

        # 인터페이스 X에 추상 메서드 x()를 선언

        package interfaceex;

        public interface Y{
            void y();
        }

        # 인터페이스 MyInterface가 X, Y 인터페이스를 상속받기

        package interfaceex;

        public interface MyInterface extends X, Y{
            void myMethod();
        }
        ```
        ```
        # 클래스 myClass에서 추상 메서드 x(), y(), myMethod() 구현
                
        package interfaceex;

        public class MyClass implements MyInterface{
            @Override
            public void x(){
                System.out.println("x()");
            }

            @Override
            public void y(){
                System.out.println("y()");
            }
            
            @Override
            public void myMethod(){
                System.out.println("myMethod()");
            }
        }
        ```
        ```
        # (Test) MyClass 클래스를 실행하기

        package interfaceex;

        public class MyClassTest{
            public static void main(String[] args){
                MyClass mClass = new MyClass();
                X xClass = mClass; // 상위 인터페이스 X형으로 대입하면, X에 선언한 메서드만 호출 가능
                xClass.x(); // @@ x()

                Y yClass = mClass; // 상위 인터페이스 Y형으로 대입하면, Y에 선언한 메서드만 호출 가능
                yClass.y(); // @@ y()

                // 구현한 인터페이스형 변수에 대입하면, 인터페이스가 상속한 모든 메서드 호출 가능
                MyInterface iClass = mClass;
                iClass.myMethod(); // @@ myMethod()
                iClass.x(); // @@ x()
                iClass.y(); // @@ y()
            }
        }
        ```

* 인터페이스 구현과 클래스 상속 함께 쓰기  
    ![image](https://user-images.githubusercontent.com/104348646/196174186-ccad47e2-d905-4d5e-b875-a7d528d97da1.png)  
    - Queue 인터페이스 구현
    - shelf 클래스를 상속받는 BookShelf 클래스: 책을 넣은 순서대로 꺼냄
        ```
        # Shelf 클래스 만들기

        package bookshelf;
        import java.util.ArrayList;

        public class Shelf{
            protected ArrayList<String> shelf; // 자료를 순서대로 저장할 ArrayList 선언

            public Shelf(){ // 디폴트 생성자로 Shelf 클래스를 생성하면, ArrayList도 생성됨
                shelf = new ArrayList<String>();
            }

            public ArrayList<String> getShelf(){ // 배열 shelf를 반환
                return shelf;
            }

            public int getCount(){ // 배열 shelf에 저장된 요소 개수를 반환
                return self.size();
            }
        }
        ```
        ```
        # Queue 인터페이스 정의하기

        package bookshelf;

        public interface Queue{
            void enQueue(String title); // 배열의 맨 마지막에 추가
            String deQueue(); // 배열의 맨 처음 항목 반환
            int getSize(); // 현재 Queue에 있는 요소 개수 반환
        }
        ```
        ```
        # BookShelf 클래스 구현하기

        package bookshelf;

        public class BookShelf extends Shelf implements Queue{
            @Override
            public void enQueue(String title){ // 배열의 맨 마지막에 추가
                shelf.add(title);
            }

            @Override
            public void deQueue(String title){ // 배열의 맨 처음 항목 반환
                return shelf.remove(0);
            }

            @Override
            public void getSize(){
                return getCount(); // 현재 Queue에 있는 요소 개수 반환
            }
        }
        ```
        ```
        # BookShelf 테스트하기

        package bookshelf;

        public class BookShelfTest{
            public static void main(String[] args){
                Queue shelfQueue = new BookShelf();
                
                // 순서대로 요소 추가
                shelfQueue.enQueue("태백산맥 1");
                shelfQueue.enQueue("태백산맥 2");
                shelfQueue.enQueue("태백산맥 3");
                
                // 입력 순서대로 요소를 꺼내서 출력
                System.out.println(shelfQueue.deQueue());
                System.out.println(shelfQueue.deQueue());
                System.out.println(shelfQueue.deQueue());
            }
        }
        ```  
        ![image](https://user-images.githubusercontent.com/104348646/196174292-5366ed6b-67ef-405b-8e4d-50ed799d6932.png)  

* 실무에서 인터페이스를 사용하는 경우
    - 인터페이스를 이용한 다향성의 구현
        : 여러 클래스가 같은 메서드를 다르게 구현하는 경우, 인터페이스에 메서드를 선언한 후에 인터페이스를 구현한 각 클래스에서 같은 메서드에 대해 다양한 기능을 구현
    - 사용자 정보를 DB에 입력/업데이트/삭제하는 기능을 UserInfoDao 인터페이스에서 정의
