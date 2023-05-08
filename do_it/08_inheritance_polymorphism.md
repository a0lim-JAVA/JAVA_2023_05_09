## 08-1 상속이란
* 상속(inheritance)
    - 클래스를 정의할 때, 이미 구현된 클래스를 상속받아서 속성이나 기능이 확장되는 클래스를 구현함
    - 상속하는 클래스: 상위 클래스, 부모 클래스(parent class), base class, super class
    - 상속받는 클래스: 하위 클래스, 자식 클래스(child class), derived class, subclass
    - 상위 클래스는 보다 일반적인 의미를 가짐
    - 하위 클래스는 보다 구체적인 의미를 가짐  
    ![image](https://user-images.githubusercontent.com/104348646/195585871-41be91b6-e75e-47d0-bc98-c84ed3373032.png)  
    - 클래스 상속 문법
        + extends 예약어 사용
        + extends 뒤에는 하나의 class만 사용 가능 <- 자바는 single inheritance만 지원
        ```
        class B extends A{
        } // B 클래스가 A 클래스를 상속받는다
        ```

* 상속을 사용하여 고객 관리 프로그램 구현하기
    ```
    # 기본 구현
    package inheritance;

    public class Customer {
        // 멤버 변수
        private int customerID;
        private String customerName;
        private String customerGrade;
        int bonusPoint;
        double bonusRatio;

        public Customer(){ // 디폴트 생성자
            customerGrade = "SILVER"; // 기본 등급: SILVER
            bonusRatio = 0.01; // 보너스 포인트 기본 적립 비율
        }

        public int calcPrice(int price){ // 보너스 포인트 적립, 지불 가격 계산 메서드
            bonusPoint += price * bonusRatio; // 보너스 포인트 계산
            return price;
        }

        public String showCustomerInfo(){ // 고객 정보를 반환하는 메서드
            return customerName + "님의 등급은 " + customerGrade + "이며, 보너스 포인트는 " + bonusPoint + "입니다.";
        }
    }
    ```
    - 멤버 변수  
        ![image](https://user-images.githubusercontent.com/104348646/195585929-aa507367-0b18-4a76-a828-ecbaa10be226.png)  
    - 메서드  
        ![image](https://user-images.githubusercontent.com/104348646/195585943-69daa35c-8604-41ef-970f-eefdfefa3bb3.png)  
    - 추가 요구 사항  
        ![image](https://user-images.githubusercontent.com/104348646/195585957-0558dbc3-8c3b-457d-820b-a6a0abab0927.png)  
        + 방법 1. Customer 클래스에 VIP 고객을 함께 포함해서 구현 -> Customer 클래스의 코드 복잡 + 일반 고객에겐 해당되지 않는 VIP 내용이 같이 생성되는 낭비
        + 방법 2. VIPCustomer 클래스 생성
            ```
            # VIPCustomer 클래스 구현: Customer 클래스와 중복 존재

            public class VIPCustomer{
                // Customer 클래스와 겹치는 멤버 변수
                private int customerID;
                private String customerName;
                private String customerGrade;
                int bonusPoint;
                double bonusRatio;
                // VIP 고객 관련 기능을 구현할 때만 필요한 멤버 변수
                private int agentID;
                double saleRatio;

                public VIPCustomer(){ // 디폴트 생성자
                    customerGrade = "VIP";
                    bonusRatio = 0.05;
                    saleRatio = 0.1;
                }

                public int calcPrice(int price){
                    bonusPoint += price * bonusRatio;
                    return price = (int)(price * saleRatio); // 할인율 적용 / (int) 정수로 변환
                }

                public int getAgentID(){ // VIP 고객에게만 필요한 메서드
                    return agentID;
                }

                public String showCustomerInfo(){ // 고객 정보를 반환하는 메서드
                    return customerName + "님의 등급은 " + customerGrade + "이며, 보너스 포인트는 " + bonusPoint + "입니다.";
                }
            }
            ```
            ```
            # VIPCustomer 클래스 구현하기: 상속 사용

            package inheritance;

            public class VIPCustomer extends Customer{ // VIPCustomer 클래스는 Customer 클래스를 상속받음
                // VIP 고객 관련 기능을 구현할 때만 필요한 멤버 변수만 추가로 구현
                private int agentID;
                double saleRatio;

                public VIPCustomer(){
                    customerGrade = "VIP"; // error: 상위 클래스에서 customerGrade는 private 변수
                    bonusRatio = 0.05;
                    saleRatio = 0.1;
                }
                
                // 할인율, 세일 미적용
                
                public int getAgentID(){
                    return agentID;
                }
            }
            ```
    + 상위 클래스 변수를 사용하기 위한 protected 예약어
        + protected  
            : 외부 클래스에서 사용할 수 없지만, 하위 클래스에서는 사용할 수 있도록 지정하는 예약어  
            ![image](https://user-images.githubusercontent.com/104348646/195586425-51a613dc-8ec5-441a-957d-52bd94f09721.png)
        ```
        package inheritance;

        public class Customer{
            protected int customerID; // private -> protected
            protected String customerName;
            protected String customerGrade;
            int bonusPoint;
            double bonusRatio;

            ...

            // protected 예약어로 선언한 변수를 외부에서 사용할 수 있도록 get(), set() 메서드 추가
            public int getCustomerID(){ // CustomerID
                return customerID;
            }
            public void setCustomerID(int custoemrID){
                this.customerID = customerID;
            }
            
            public String getCustomerName(){ // CustomerName
                return customerName;
            }
            public void setCustomerName(String customerName){
                this.customerName = customerName;
            }
            
            public String getCustomerGrade(){ // CustomerGrade
                return customerGrade;
            }
            public void setCustomerGrade(String customerGrade){
                this.customerGrade = customerGrade;
            }
        }
        ```
    + 테스트 프로그램 실행하기  
        ![image](https://user-images.githubusercontent.com/104348646/195586119-c89dce29-7bf5-44bb-b75d-963b4ecd9ece.png)  
        ```
        package inheritancee;

        public class CustomerTest1{
            public static void main(String[] args){
                Customer customerLee = new Customer();
                customerLee.setCustomerID(10010); // customerID와 customerName은 protected 변수 -> set(0 메서드 호출
                customerLee.setCustomerName("이순신");
                customerLee.bonusPoint = 1000;
                System.out.println(customerLee.showCustomerInfo());

                VIPCustomer customerKim = new VIPCustomer();
                customerKim.setCustomerID(10020);
                customerKim.setCustomerName("김유신");
                customerKim.bonusPoint = 1000;
                System.out.println(customerKim.showCustomerInfo());
            }
        }
        ```  
        ![image](https://user-images.githubusercontent.com/104348646/195586160-77b7a15b-4bbb-43b1-9aff-42a513fa0aec.png)  

## 08-2 상속에서 클래스 생성과 형 변환
* 하위 클래스가 생성되는 과정
    ```
    # Customer 클래스 생성자: 출력문 추가
    package inheritance;

    public class Customer{
        protected int customerID;
        protected String customerName;
        protected String customerGrade;
        int bonusPoint;
        double bonusRatio;


        public Customer(){ // 디폴트 생성자
            customerGrade = "SILVER";
            bonusRatio = 0.01;
            System.out.println("Customer() 생성자 호출"); // 상위 클래스 생성할 때의 콘솔 출력문
        }

        public int calcPrice(int price) {
            bonusPoint += price * bonusRatio;
            return price;
        }
        ...
    }

    # VIPCustomer 클래스 생성자: 출력문 추가
    package inheritance;

    public class VIPCustomer extends Customer{
        private int agentID;
        double saleRatio;

        public VIPCustomer(){
            customerGrade = "VIP"; // error
            bonusRatio = 0.05;
            saleRatio = 0.1;
            System.out.println("VIPCustomer() 생성자 호출"); // 하위 클래스 생성할 때의 콘솔 출력문
        }

        public int getAgentID(){
            return agentID;
        }
    }

    # 출력 결과
    package inheritancee;

    public class CustomerTest2{
        public static void main(String[] args){
            VIPCustomer customerKim = new VIPCustomer();
            customerKim.setCustomerID(10020);
            customerKim.setCustomerName("김유신");
            customerKim.bonusPoint = 1000;
            System.out.println(customerKim.showCustomerInfo());
            // @@ Customer() 생성자 호출
            // @@ VIPCustomer() 생성자 호출
            // @@ 김유신 님의 등급은 VIP이며, 보너스 포인트는 1000입니다.
        }
    }
    ```
    - 생성 순서: 상위 클래스 -> 하위 클래스
    - 생성자 호출 순서: 상위 클래스 -> 하위 클래스
    - 하위 클래스의 생성자에서는 무조건 상위 클래스의 생성자가 호출되어야 함  
        ![image](https://user-images.githubusercontent.com/104348646/195586214-d8213d0e-029b-46cc-bc9d-cff7e5ddf856.png)  
        + private변수: 생성은 되지만 하위 클래스에서 접근할 수 없음

* 부모를 부르는 예약어, super
    - super 예약어  
        : 하위 클래스에서 상위 클래스로 접근해 상위 클래스의 기본 생성자를 호출
        + 하위 클래스가 상위 클래스에 대한 주소를 가짐  
        ![image](https://user-images.githubusercontent.com/104348646/195586249-a1855f76-3329-4710-8926-95e7fe0ecda1.png)
        ```
        public VIPCustomer(){
            super(); // (아무것도 없는 경우) 컴파일러가 자동으로 추가하는 코드 / 상위 클래스의 Customer()가 호출됨
            customerGrade = "VIP"; // error
            bonusRatio = 0.05;
            saleRatio = 0.1;
            System.out.println("VIPCustomer() 생성자 호출");
        }
        ```
    - super 예약어로 매개변수가 있는 생성자 호출하기
        + 상위 클래스의 기본 생성자가 없는 경우(매개변수가 있는 생성자만 존재), 하위 클래스는 명시적으로 상위 클래스를 호출해야 함
        ```
        # Customer 클래스의 디폴트 생성자 삭제 + 새로운 생성자 작성
        ...
        public Customer(int customerID, String customerName){
            this.customerID = customerID;
            this.customerName = customerName;
            customerGrade = "SILVER";
            bonusRatio = 0.01;
            System.out.println("Customer(int, String) 생성자 호출");
        }
        ...
        // Customer 클래스를 상속받은 VIPCustomer 클래스에서 error
        ```
        ```
        # 명시적으로 상위 클래스 생성자 호출하기

        ...
        public VIPCustomer(int customerID, String customerName, int agentID){
            super(customerID, customerName); // 상위 클래스 생성자 호출
            customerGrade = "VIP"; // error
            bonusRatio = 0.05;
            saleRatio = 0.1;
            this.agentID = agentID; // 상담원 ID도 함께 지정
            System.out.println("VIPCustomer(int, String, int) 생성자 호출"); // 매개변수: customerID, customerName, agentID
        }
        ...
        ```
        ```
        # super(customerID, customerName); 의 실행 형태

        public Customer(int customerID, String customerName){
            this.customerID = customerID;
            this.customerName = customerName;
            customerGrade = "SILVER";
            bonusRatio = 0.01;
            System.out.println("Customer(int, String) 생성자 호출");
        } // super()를 통해, Customer(int CutomerID, String customerName) 상위 클래스 생성자를 호출 + 코드 순섣대로 멤버 변수 초기화 / 이후, VIPCustomer 하위 클래스 생성자의 내부 코드 수행
        ```
    
    - 상위 클래스의 멤버 변수나 메서드를 참조하는 super
        + this를 사용해서 자신의 멤버에 접근했던 것과 유사
        ```
        # VIPCustomer 클래스의 showVIPInfo() 메서드에서 상위 클래스의 showCustomerInfo() 메서드를 참조해 담당 상담원 아이디를 추가로 출력
        public String showVIPInfo(){
            return super.showCustomerInfo() + "담당 상담원 아이디는 " + agentID + "입니다";
        }
        ```
* 상위 클래스로 묵시적 클래스 형 변환
    - 업캐스팅(upcasting)  
        : 상위 클래스 형으로 변수를 선언하고 하위 클래스 인스턴스를 생성
        + 하위 클래스는 상위 클래스의 타입을 내포하고 있으므로 상위 클래스로 묵시적 형변환이 가능
        + 역은 성립하지 않음  
        ![image](https://user-images.githubusercontent.com/104348646/195794915-64746a5c-91d8-469e-9eb7-323f97044412.png)  
    - 형 변환된 vc가 가리키는 것  
        ![image](https://user-images.githubusercontent.com/104348646/195794965-e6171df8-96ba-4ffc-b116-558ce7076a17.png)  
        + VIPCustomer() 생성자 호출 -> 모든 인스턴트 생성
        + 타입: Customer -> 접급할 수 있는 변수/메서드는 Customer의 변수/메서드
        + 클래스의 상속 계층이 여러 단계인 경우에도 상위 클래스의 형 변환은 묵시적으로 이루어짐

## 08-3 메서드 오버라이딩
* 상위 클래스 메서드 재정의하기
    - 메서드 오버라이딩(method overriding)  
        : 상위 클래스에 정의된 메서드 중 하위 클래스와 기능이 맞지 않거나 추가 기능이 필요한 경우, 같은 이름과 매개변수로 하위 클래스에서 재정의함
        + 반환형, 메서드 이름, 매개변수 개수, 매개변수 자료형이 같아야 함
    - VIP 고객 클래스의 제품 가격 계산 메서드 재정의하기
        ```
        # Customer() 내의 calcPrice() 메서드
        public int calPrice(int price){
            bonusPoint += price * bonusRatio;
            return price;
        }
        ```
        ```
        # calcPrice() 메서드 재정의하기

        package inheritance;

        public class VIPCustomer extends Customer{
            private int agentID;
            double saleRatio;
            ...
            
            // Override: 재정의한 메서드
            @Override // Override 애노테이션: 재정의된 메서드임을 컴파일러에 알림
            public int calcPrice(int price){
                bonusPoint += price * bonusRatio;
                return price - (int)(price * saleRatio); // 할인된 가격을 계산해 반환
            }
        }
        ```
        + 애노테이션(annotation)  
            : 컴파일러에게 특정한 정보를 제공하는 주석
            + 표준 애노테이션  
            ![image](https://user-images.githubusercontent.com/104348646/195795003-ce2ec334-45a5-44db-8a06-bfe48851d621.png)  
        ```
        # calcPrice() 테스트하기
        package inheritance;

        public class OverridingTest1{
            public static void main(String[] args){
                Customer customerLee = new Customer(10010, "Lee");
                customerLee.bonusPoint = 1000;
                
                VIPCustomer customerKim = new VIPCustomer(10020, "Kim", 12345);
                customerKim.bonusPoint = 10000;
                
                int price = 10000;
                System.out.println(customerLee.getCustomerName() + "님이 지불해야 하는 금액은 " + customerLee.calcPrice(price) + "원 입니다");
                System.out.println(customerKim.getCustomerName() + "님이 지불해야 하는 금액은 " + customerKim.calcPrice(price) + "원 입니다");
                // Customer -> 정가 지불 @@ Lee님이 지불해야 하는 금액은 10000원 입니다
                // VIP -> 10% 할인 지불 @@ Kim님이 지불해야 하는 금액은 9000원 입니다
            }
        ```

* 묵시적 클래스 형 변환과 메서드 재정의
    ```
    Customer vc = new VIPCustomer("10030", "Na", 2000);
    vc.calcPrice(10000);
    ```
    - 1 묵시적 형 변환: VIPCustomer가 Customer형으로 변환
    - 2 calcPrice() 메서드 호출
    ```
    # 클래스 형 변환과 재정의 메서드 호출하기
    package inheritance;

    public class OverridingTest2 {
        public static void main(String[] args) {
            Customer vc = new VIPCustomer(10030, "Na", 2000);
            vc.bonusPoint = 1000;

            System.out.println(vc.getCustomerName() + "님이 지불해야 하는 금액은 " + vc.calcPrice(price) + "원 입니다");
            // 재정의된 메서드가 호출됨 @@ Na님이 지불해야 하는 금액은 9000원 입니다
            // vc의 타입은 Customer이지만, 실제 생성된 인스턴스인 VIPCustomer 클래스의 calcPrice() 메서드가 호출됨
        }
    }
    ```

* 가상 메서드(virtual method)
    : 타입과 상관 없이 선언한 클래스형이 아닌 실제 생성된 인스턴스의 메서드를 호출하는 기술
    - 프로그램에서 어떤 객체의 변수/메서드의 참조는 그 타입에 따라 이루어짐
        ```
        # 메서드 호출하기

        package virtualfunction;

        public class TestA{
            int num;
            
            void aaa(){
                System.out.println("aaa() 출력");
            }
            
            public static void main(String[] args){
                TestA a1 = new TestA();
                a1.aaa();
                TestA a2 = new TestA();
                a2.aaa();
            }
        }
        // @@ aaa() 출력
        // @@ aaa() 출력
        ```
        ![image](https://user-images.githubusercontent.com/104348646/195795043-86ec5f1a-5744-499c-8951-8930e9055cb5.png)    
        + 인스턴스가 달라도 동일한 메서드를 호출
        + 1 main() 함수 실행 -> 지역 변수:스택 메모리에 위치
        + 2 (각 참조 변수 a1, a2가 가리키는) 인스턴스: 힙 메모리에 생성
        + 3 메서드의 명령 집합: 메서드 영역(코드 영역)에 위치
        + 4 메서드를 호출하면, 메서드 영역의 주소를 참조해서 명령 실행
    - 가상 메서드의 원리
        + 일반 메서드 호출: 그 메서드의 명령 집합이 있는 메모리 위치를 참조하여 명령 실행
        + 가상 메서드: 가상메서드 테이블에서 주소 값을 찾아서 해당 메서드의 명령 실행
            + 가상메서드 테이블 생성
            + 각 메서드 이름과 실제 메모리 주소가 짝을 이룸  
        ![image](https://user-images.githubusercontent.com/104348646/195795088-701d728e-db6f-4358-8046-4793e0faa984.png)  
        + 재정의 된 메서드: 실제 인스턴스에 해당하는 메서드 호출
        + 재정의되지 않은 메서드: 메서드 주소가 같음 -> 상위 클래스의 메서드 호출    
        ```
        # 클래스형에 기반하여 지불 금액 계산하기

        package inheritance;

        public class OverridingTest3{
            public static void main(String[] args){
                int price = 10000;

                Customer customerLee = new Customer(10010, "Lee");
                System.out.println(customerLee.getCustomerName() + "님이 지불해야 하는 금액은 " + customerLee.calcPrice(price) + "원 입니다");

                VIPCustomer customerKim = new VIPCustomer(10020, "Kim", 12345);
                System.out.println(customerKim.getCustomerName() + "님이 지불해야 하는 금액은 " + customerKim.calcPrice(price) + "원 입니다");

                Customer vc = new VIPCustomer(10030, "Na", 2000);
                System.out.println(vc.getCustomerName() + "님이 지불해야 하는 금액은 " + vc.calcPrice(price) + "원 입니다");
                // 원래 Customer형 메서드가 호출되어야 함. but 가상 메서드 방식에 의해, VIPCustomer 인스턴스의 메서드가 호출됨
                // @@ Na님이 지불해야 하는 금액은 9000원 입니다
            }
        }
        ```

    - 정리
        + 상위 클래스(Customer) -  calcPrice(메서드)
        + 하위 클래스(VIPCustomer) - vc(인스턴스)
        + vc가 상위 클래스로 형 변환됨
        + vc.calcPrice() 호출
            + vc를 선언할 때, 사용한 자료형(Customer)의 메서드가 호출 
            + 생성된 인스턴스(VIPCustomer)의 메서드가 호출됨  
        ![image](https://user-images.githubusercontent.com/104348646/195795141-6a4c0fff-edbd-4b78-8be6-e0d1b9d57b59.png)

## 08-4 다형성(polymorphism)
* 다형성  
    : 하나의 코드가 여러가지 자료형으로 구현되어 실행되는 것
    - 정보은닉, 상속과 함께 객체지향 프로그래밍의 가장 큰 특징
    - 객체지향 프로그래밍의 유연성, 재활용성, 유지보수성에 기본이 됨
    - 예시  
    ![image](https://user-images.githubusercontent.com/104348646/195795181-30a6b73e-5eb9-47ad-ba17-41e7ed3cfb48.png)
    ```
    # 다형성 테스트하기

    package polymorphism;

    class Animal{ // 상위 클래스
        public void move(){ // 메서드
            System.out.println("동물이 움직입니다");
        }
    }

    class Human extends Animal{ // 상속 -> 하위 클래스 1
        public void move(){
            System.out.println("사람이 두 발로 걷습니다");
        }
    }

    class Tiger extends Animal{ // 상속 -> 하위 클래스 2
        public void move(){
            System.out.println("호랑이가 네 발로 뜁니다");
        }
    }
    class Eagle extends Animal{ // 상속 -> 하위 클래스 3
        public void move(){
            System.out.println("독수리가 하늘을 납니다");
        }
    }

    public class AnimalTest1{
        public static void main(String[] args){
            AnimalTest1 aTest = new AnimalTest1();
            aTest.moveAnimal(new Human());
            aTest.moveAnimal(new Tiger());
            aTest.moveAnimal(new Eagle());
        }
        
        public void moveAnimal(Animal animal){ // Animal: 매개변수의 자료형: 상위 클래스 -> animal.move() 메서드 사용 가능
            animal.move(); // 재정의된 메서드가 호출됨: 사람, 호랑이, 독수리 / 매개변수에 따라 출력문이 달라짐
        }
    }
    // @@ 사람이 두 발로 걷습니다
    // @@ 호랑이가 네 발로 뜁니다
    // @@ 독수리가 하늘을 납니다
    ```

* 다형성의 장점
    - 확장성
        : 새로운 인스턴스도 같은 클래스를 상속받아 구현하면, 모든 클래스를 해당 클래스 자료형 하나로 관리 가능
        + 상위 클래스에서 공통 부분의 메서드를 제공하고, 하위 클래스에서 그에 기반한 추가 요소를 덧붙여 구현
        + 코드 길이 감소, 유지보수 편리함
        + 필요에 따라 상속받은 모든 클래스를 하나의 상위 클래스로 처리 가능
        + 다형성으로 각 클래스의 여러 가지 구현 실행이 가능 -> 프로그램 확장 용이
    - 다형성을 활용해 VIP 고객 클래스 완성하기
    ```
    # Customer
            
    package polymorphism;

    public class Customer{
        protected int customerID;
        protected String customerName;
        protected String customerGrade;
        int bonusPoint;
        double bonusRatio;

        public Customer(){
            initCustomer();
        }

        public Customer(int customerID, String customerName){
            this.customerID = customerID;
            this.customerName = customerName;
            initCustomer();
        }

        private void initCustomer(){ // 클래스의 멤버 변수를 초기화하는 메서드: 고객 등급과 보너스 포인트 적립률 지정 함수
            // private: 생성자에서만 호출하는 메서드
            customerGrade = "SILVER";
            bonusRatio = 0.1;
        }

        public int calcPrice(int price){
            bonusPoint += price * bonusRatio;
            return price;
        }

        public String showCustomerInfo(){
            return customerName + "님의 등급은 " + customerGrade + "이며, 보너스 포인트는 " + bonusPoint + "입니다.";
        }
        ...
    }
    ```
    ```
    # VIPCustomer

    package polymorphism;

    public class VIPCustomer extends Customer{
        private int agnetID;
        double saleRatio;

        public VIPCustomer(int customerID, String customerName, int agentID){
            super(customerID, customerName);
            customerGrade = "VIP";
            bonusRatio = 0.05;
            saleRatio = 0.1;
            this.agentID = agentID;
        }

        public int calcPrice(int price){ // 지불 가격 메서드 재정의
            bonusPoint += price * bonusRatio;
            return price - (int)(price * saleRatio); // 할인율을 반영한 지불 가격
        }

        public String showCustomerInfo(){ // 고객 정보 출력 메서드 재정의
            return super.showCustomerInfo() + "담당 상담원 번호는 " + agentID + "입니다"; // 담당 상담원 번호까지 출력
        }

        public int getAgentID(){
            return agnetID;
        }
    }
    ```
    ```
    # test

    package polymorphism;

    public class CustomerTest{
        public static void main(Sting[] args){
            Customer customerLee = new Customer();
            customerLee.setCustomerID(10010);
            customerLee.setCustomerName("Lee");
            customerLee.bonuspoint = 1000;

            System.out.println(customerLee.showCustomerInfo());

            Customer customerKim = new VIPCustomer(10020, "Kim", 12345); // VIPCustomer를 Customer형으로 선언
            customerKim.bonusPoint = 1000;

            System.out.println(customerKim.showCustomerInfo());
            System.out.println("==== 할인율과 보너스 포인트 계산====");

            int price = 10000;
            int leePrice = customerLee.calcPrice(price);
            int kimPrice = customerKim.calcPrice(price);

            System.out.println(customerLee.getCustomerName() + "님이 " + leePrice + "원 지불하셨습니다");
            System.out.println(customerLee.showCustomerInfo());
            System.out.println(customerKim.getCustomerName() + "님이 " + kimPrice + "원 지불하셨습니다");
            System.out.println(customerKim.showCustomerInfo());
            // @@ Lee님의 등급은 SILVER이며, 보너스 포인트는 1000점입니다.
            // @@ Kim님의 등급은 VIP이며, 보너스 포인트는 1000점입니다. 담당 상담원 번호는 12345입니다
            // @@ ==== 할인율과 보너스 포인트 계산====
            // @@ Lee님이 10000원 지불하셨습니다
            // @@ Lee님의 등급은 SILVER이며, 보너스 포인트는 1100점입니다.
            // @@ Kim님이 9000원 지불하셨습니다
            // @@ Kim님의 등급은 VIP이며, 보너스 포인트는 1500점입니다. 담당 상담원 번호는 12345입니다
        }
    }
    ```
    
## 08-5 다형성 활용하기
* 일반 고객과 VIP 고객의 중간 등급 만들기  
    ![image](https://user-images.githubusercontent.com/104348646/195837820-56b52bec-44dd-438f-8230-e2528d3f4663.png)  
    ![image](https://user-images.githubusercontent.com/104348646/195837856-a603c80a-6ee0-4975-98e4-40ce67616d28.png)  
    ```
    # 새로운 고객 등급 GOLD 추가하기

    package witharraylist;

    public class GoldCustomer extends Customer{
        double saleRatio;
        
        public GoldCustomer(int customerID, String customerName){
            super(customerID, customerName);
            customerGrade = "GOLD";
            bonusRatio = 0.02;
            saleRatio = 0.1;
        }
        
        public int calcPrice(int price){ // 재정의한 메서드
            bonusPoint += price * bonusRatio;
            return price - (int)(price * saleRatio);
        }
    }
    ```
    - 배열로 고객 5명 구현하기  
        ![image](https://user-images.githubusercontent.com/104348646/195837947-16024e52-fb97-4bdf-9749-2f8d3ce919c7.png)  
        + 클래스: Customer, GoldCustomer, VIPCustomer
        + 배열의 자료형: Customer
        + => 배열에서 세 클래스를 모두 사용 가능 + Customer 하위 클래스에서 묵시적 형 변환
        ```
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        ```
        ```
        # 배열을 활용한 고객 관리 프로그램 구현하기

        package witharraylist;
        import java.util.ArrayList;

        public class CustomerTest{
            public static void main(String[] args){
                ArrayList<Customer> customerList = new ArrayList<Customer>();

                Customer customerLee = new Customer(10010, "이순신");
                Customer customerShin = new Customer(10020, "신사임당");
                Customer customerHong = new GoldCustomer(10030, "홍길동");
                Customer customerYoul = new GoldCustomer(10040, "이율곡");
                Customer customerKim = new VIPCustomer(10050, "김유신", 12345);

                customerList.add(customerLee); // ArrayList의 add 속성을 사용해 객체 배열에 고객 추가
                customerList.add(customerShin);
                customerList.add(customerHong);
                customerList.add(customerYoul);
                customerList.add(customerKim);

                System.out.println("===== 고객 정보 출력 =====");
                for(Customer customer : customerList){
                    System.out.println(customer.showCustomerInfo());
                }

                System.out.println("===== 할인율과 보너스 포인트 계산 =====");
                int price = 10000;
                for(Customer customer : customerList){ // 다향성 구현: 등급에 따라 할인율과 적립금이 다름 -> 각 클래스에 재정의
                    // customerList 배열의 요소를 하나씩 가져와서 Customer형 변수에 넣음
                    int cost = customer.calcPrice(price); // 현재 변수의 실제 인스턴스에 따라 재정의한 메서드를 각각 호출해서 계산
                    System.out.println(customer.getCustomerName() + " 님이 " + cost + "원 지불하셨습니다");
                    System.out.println(customer.getCustomerName() + "님의 현재 보너스 포인스는 " + customer.bonusPoint + "점입니다");
                }
            }
        }
        ```  
        ![image](https://user-images.githubusercontent.com/104348646/195837984-b8f0a98c-f4c5-4427-8965-05e4bc83ee66.png)  

* 상속은 언제 사용할까
    - IS-A 관계(is a relationship: inheritance)  
        : 일반적인(general) 개념과 구체적인(specific) 개념과의 관계
        + 상위 클래스: 일반적인 개념 클래스 / ex) 포유류
        + 하위 클래스: 구체적인 개념 클래스 / ex) 사람, 호랑이, ...
        + 단순한 코드 재사용 목적으로는 사용하지 않음
    - HAS-A 관계(has a relationship: association)  
        : 한 클래스가 다른 클래스를 소유한 관계
        + 코드 재사용의 한 방법
        ```
        # 학생 클래스 생성 + 모든 학생은 전공 과목을 가지고 있는 경우

        package inheritance2;

        public class Subject{
            private int subjectId;
            private int subjectName;

            public int getSubjectId(){
                return subjectId;
            }

            public void setSubjectId(int subjectId){
                this.subjectId = subjectId;
            }

            public int getSubjectName(){
                return subjectName;
            }

            public void setSubjectName(init subjectName){
                this.subjectName = subjectName;
            }

            public void showSubjectInfo(){
                Systme.out.println(subjected + "," + subjectName);
            }
        }
        // 상속에 적합하지 않음
        // Subject가 Student를 포괄하는 개념의 클래스가 아님
        // Student 클래스를 상속받는 다른 클래스 존재 가능
        ```
        ```
        class Student{
            Subject majorSubject; // Studentr가 Subject를 포함한 관계 -> 멤버 변수로 사용
        }
        ```
    - 상속을 사용하면 클래스 간의 결합도가 높아짐 -> 상위 클래스의 변화가 하위 클래스에 미치는 영향이 큼

## 08-6 다운 캐스팅과 instanceof
* 하위 클래스로 형 변환, 다운 캐스팅
    - 다운 캐스팅(down casting)  
        : 상위 클래스로 형 변환되었던 하위 클래스를 다시 원래 자료형으로 형 변환하는 것

* instnaceof  
    : 원래 인스턴스의 자료형을 체크하는 예약어
    - 예시
        + hAnimal: 원래 Human형으로 생성 -> Animal형으로 형 변환됨
        + 왼쪽에 있는 변수의 원래 인스턴스형이 오른쪽 클래스 자료형인지 확인
        + 다운 캐스팅을 하기 위해선 자료형을 명시적으로 써야 함
    ```
    Animal hAnimal = new Human();
    if(hAnimal instanceof Human){ // hAnimal 인스턴스 자료형이 Human형 이라면
        Human human = (Human)hAnimal; // 인스턴스 hAnimal을 Human형으로 다운 캐스팅
    }
    ```
    ```
    # 원래 자료형이 Human형이 아닌 경우
    Animal ani = new Tiger();
    Human h = human(ani);
    // 컴파일 오류 x: Animal형으로 자동으로 형 변환됨 -> h의 자료형 = human(ani)의 자료형 = human
    // 실행 오류 o
    ```

    ```
    # instanceof로 원래 인스턴스형 확인 후 다운 캐스팅하기

    package polymorphism;
    import java.util.ArrayList;

    class Animal{ // 상위 클래스 Animal
        public void move(){
            System.out.println("동물이 움직입니다.");
        }
    }

    class human extends Animal{ // Animal을 상속받은 Human 클래스
        public void move(){
            System.out.println("사람이 두 발로 걷습니다");
        }
        
        public void readBook(){
            System.out.println("사람이 책을 읽습니다");
        }
    }

    class Tiger extends Animal{ // Animal을 상속받은 Tiger 클래스
        public void move(){
            System.out.println("호랑이가 네 발로 뜁니다");
        }

        public void hunting(){
            System.out.println("호랑이가 사냥을 합니다");
        }
    }

    class Eagle extends Animal{ // Animal을 상속받은 Eagle 클래스
        public void move(){
            System.out.println("독수리가 하늘을 납니다");
        }

        public void flying(){
            System.out.println("독수리가 날개를 쭉 펴고 멀리 날아갑니다");
        }
    }

    public class AnimalTest{
        ArrayList<Animal> aniList = new ArrayList<Animal>(); // Animal: 배열의 자료형을 Animal로 지정
        
        public static void main(String[] args){
            AnimalTest aTest = new AnimalTest();
            aTest.addAnimal();
            System.out.println("원래 형으로 다운 캐스팅");
            aTest.testCasting();
        }
        
        public void addAnimal(){
            aniList.add(new Human()); // ArrayList에 추가되면서, Animal형으로 변환
            aniList.add(new Tiger());
            aniList.add(new Eagle());
            
            for(Animal ani: aniList){ // 배열 요소를 Animal형으로 꺼내서 move()를 호출하면, 재정의된 함수가 호출됨
                // 배열 요소: Animal형 -> 각 클래스의 readBook(), hunting(), flying() 메서드 사용 불가능 -> 다운 캐스팅 필요
                ani.move();
            }
        }
        
        public void testCasting(){
            for(int i = 0; i < aniList.size(); i++){ // 모든 배열 요소를 하나씩 돌면서
                Animal ani = aniList.get(i); // Animal형으로 가져옴
                if(ani instanceof Human){ // Human이면
                    Human h = (Human)ani; // Human형으로 다운 캐스팅
                    h.readBook();
                }
                else if(ani instanceof Tiger){
                    Tiger t = (Tiger)ani;
                    t.hunting();
                }
                else if(ani instanceof Eagle){
                    Eagle e = (Eagle)ani;
                    e.flying();
                }
                else{
                    System.out.println("지원되지 않는 형입니다");
                }
            }
        }
    }
    ```
