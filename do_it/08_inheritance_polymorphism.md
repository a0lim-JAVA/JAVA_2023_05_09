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
            this.agentID = agentID;
            System.out.println("VIPCustomer() 생성자 호출");
        }
        ...
        ```
