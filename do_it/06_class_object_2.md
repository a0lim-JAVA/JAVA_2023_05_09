## 06-1 this 예약어
* this(자신의 메모리를 가리킴)
    : 생성된 인스턴스 스스로를 가리키는 예약어
    ```
    # this 출력
    package thisex;

    public class BirthDay {
        int day;
        int month;
        int year;
        
        public void setYear(int year){ // 태어난 연도를 지정하는 메서드
            this.year = year; // = bDay.year = year;
        }
        
        public void printThis(){ // this 출력 메서드
            System.out.println(this); // = System.out.println(bDay);
        }
    }

    public class ThisExample{
        public static void main(String[] args){
            BirthDay bDay = new BirthDay();
            bDay.setYear(2000); // 태어난 연도를 2000으로 지정
            System.out.println(bDay); // 참조변수 출력 @@ thisex.Birthday@311d617d
            bDay.printThis(); // this 출력 메서드 호출 @@ thisex.Birthday@311d617d -> this = bDay
        }
    }
    // 한 파일에 두 개의 클래스가 존재함
    ```
    ![image](https://user-images.githubusercontent.com/104348646/195262367-61f7cbee-8ad3-4de5-92c1-f574e15b17a7.png)  

* this(생성자에서 다른 생성자를 호출)
    - 생성자를 호출하는 코드 이전에 다른 코드 작성 불가
        + 생성자는 클래스 생성 시에 호출됨 -> this를 사용한 생성자가 호출되기 전에 다른 코드가 있으면, error
    ```
    package thisex;

    class Person{
        String name;
        int age;

        Person(){
            this("None", 1); // this를 사용해서 Person(String, int) 생성자 호출
        }

        Person(String name, int age){ // 호출된 생성자
            this.name = name;
            this.age = age;
        }
    }

    public class CallAnotherConst {
        public static void main(String[] args){
            Person noName = new Person(); // Person: 디폴트 생성자
            System.out.println(noName.name); // @@ "None"
            System.out.println(noName.age); // @@ 1
        }
    }
    ```

* this(자신의 주소를 반환)
    - 클래스 자료형과 상관 없이 클래스 내에서 this를 사용하면 자신의 주소 값을 반환할 수 있음
    ```
    package thisex;

    class Person{
        String name;
        int age;

        Person(){
            this("None", 1); // this를 사용해서 Person(String, int) 생성자 호출
        }

        Person(String name, int age){ // 호출된 생성자
            this.name = name;
            this.age = age;
        }

        Person returnItSelf(){ // 반환형: 클래스형
            return this; // this 반환
        }
    }

    public class CallAnotherConst {
        public static void main(String[] args){
            Person noName = new Person(); // Person: 디폴트 생성자
            System.out.println(noName.name); // @@ "None"
            System.out.println(noName.age); // @@ 1

            Person p = noName.returnItSelf(); // this 값을 클래스 변수 p에 대입
            System.out.println(p); // @@ thisex.Person@16f65612
            System.out.println(noName); // @@ thisex.Person@16f65612
        }
    }
    ```

## 06-2 객체 간 협력
![image](https://user-images.githubusercontent.com/104348646/195099639-0c3c7948-5e20-4f11-a2e9-f2f79cdd1e5a.png)  

* 학생 클래스 구현하기
    ```
    package coorperation;

    public class Student { // 멤버 변수
        public String studentName;
        public int grade;
        public int money;
        
        public Student(String studentName, int money){ // 학생 이름과 가진 돈을 매개변수로 받는 생성자 -> 학생 클래스를 하나 생성하면, 학생 이름과 가진 돈을 초기화
            // 디폴트 생성자 제공하지 않음 -> 학생 클래스를 생성하기 위해서는 해당 생성자를 호출해야 함
            this.studentName = studentName;
            this.money = money;
        }
        
        public void takeBus(Bus bus){ // 학생이 버스를 타면 1000원을 지불하는 기능을 구현한 메서드
            bus.take(1000);
            this.money -= 1000;
        }
        
        public void takeSubway(Subway subway){ // 학생이 지하철을 타면 1500원을 지불하는 기능을 구현한 메서드
            subway.take(1500);
            this.money -= 1500;
        }
        
        public void showInfo(){ // 학생의 현재 정보를 출력하는 메서드
            System.out.println(studentName + "님의 남은 돈은 " + money + "원 입니다.");
        }
    }
    ```

* 버스 클래스 구현하기
    ```
    package coorperation;

    public class Bus{ // 멤버 변수
        int busNumber;
        int passengerCount;
        int money;
        
        public Bus(int busNumber){ // 버스 번호를 매개변수로 받는 생성자
            this.busNumber = busNumber;
        }
        
        public void take(int money){ // 승객이 버스에 탄 경우를 구현한 메서드
            this.money += money; // 버스 수입 증가
            passengerCount++; // 승객 수 증가
        }
        
        public void showInfo(){ // 버스 정보를 출력하는 메서드
            System.out.println("버스 " + busNumber + "번의 승객은 " + passengerCount + "명이고, 수입은 " + money + "원 입니다");
        }
    }
    ```

* 지하철 클래스 구현하기
    ```
    package coorperation;

    public class Subway{ // 멤버 변수
        String lineNumber;
        int passengerCount;
        int money;

        public Subway(String lineNumber){ // 지하철 노선 번호를 매개변수로 받는 생성자
            this.lineNumber = lineNumber;
        }

        public void take(int money){ // 승객이 지하철에 탄 경우를 구현한 메서드
            this.money += money; // 수입 증가
            passengerCount++; // 승객 수 증가
        }

        public void showInfo(){ // 지하철 정보를 출력하는 메서드
            System.out.println(lineNumber + "의 승객은 " + passengerCount + "명이고, 수입은 " + money + "원 입니다");
        }
    }
    ```

* 학생, 버스, 지하철 객체 협력하기
    - "학생이 지하철을 탄다", "지하철에 학생이 탄다" -> 두 객체 각각의 클래스에 메서드로 구현해야 함
```
# 버스와 지하철 타기
public class TakeTrans{
    public static void main(String[] args){
        Student student1 = new Student("James", 5000); // 학생 인스턴스 1 생성
        Student student2 = new Student("Tomas", 10000); // 학생 인스턴스 2 생성

        Bus bus100 = new Bus(100); // 노선 번호가 100번인 버스 생성
        student1.takeBus(bus100); // James가 100번 버스를 탐
        student1.showInfo(); // @@ "James님의 남은 돈은 4000원 입니다."
        bus100.showInfo(); // @@ "버스 100번의 승객은 1명이고, 수입은 1000원 입니다"
        
        Subway subwayGreen = new Subway("line2"); // 노선 번호가 2호선인 지하철 생성
        student1.takeSubway(subwayGreen); // Tomas가 2호선을 탐
        student2.showInfo();// @@ "Tomas님의 남은 돈은 8500원 입니다."
        subwayGreen.showInfo(); // @@ line2의 승객은 1명이고, 수입은 1500원 입니다.
    }
}
```

## 06-3 static 변수
* static 변수(= 정적 변수) (= 클래스 변수= class variable)  
    : 클래스 전반에서 공통으로 사용할 수 있는 기준 변수
    - 변수를 선언할 때, 자료형 앞에 static 예약어 사용
    - 클래스 내부에 선언하지만, 다른 멤버 변수처럼 인스턴스가 생성될 때마다 새로 생성되지 않음
    - 프로그램이 실행되어 메모리에 올라갔을 때, 딱 한 번 메모리 공간이 할당 -> 모든 인스턴스가 값을 공유
    ![image](https://user-images.githubusercontent.com/104348646/195099707-41487d26-1fea-4801-be43-9ef70fe74922.png)  
    ![image](https://user-images.githubusercontent.com/104348646/195099732-b9e8e3fc-0351-4ef9-a005-ff5e8b4836b9.png)  

    ```
    # static 변수 사용하기
    package staticex;

    public class Student {
        public static int serialNum = 1000; // static 변수는 인스턴스 생성과 상관 없이 먼저 생성됨
        // 기준값: 1000 / 학생이 생성될 때마다 순서대로 증가
        public int studentID;
        public String studentName;
        public int grade;
        public String address;
        
        public String getStudentName(){
            return studentName;
        }
        
        public void setStudentName(String name){
            studentName = name;
        }
    }

    # static 변수 테스트하기
    package staticex;

    public class StudentTest1{
        public static void main(String[] args){
            Student student1 = new Student();
            student1.setStudentName("Lee");
            System.out.println(student1.serialNum); // serialNum 변수의 초깃값 출력 @@ 1000
            student1.serialNum++; // static 변수 값 증가

            Student student2 = new Student();
            student2.setStudentName("Son");
            System.out.println(student1.serialNum); // 증가된 값 출력 @@1001
            System.out.println(student2.serialNum); // 증가된 값 출력 @@1001
            // static으로 선언한 serialNum 변수는 모든 인스턴스가 공유 -> 두 참조변수가 동일한 변수의 메모리를 가리킴
        }
    }
    ```
    ![image](https://user-images.githubusercontent.com/104348646/195099758-e888231a-0971-4113-bcbe-ba05b5b933a9.png)  
  
    - 학생이 한 번 생성될 때마다 학번을 자동으로 부여하는 프로그램 만들기
    ```
    # 학번 자동으로 부여하기
    package staticex;

    public class Student1 {
        public static int serialNum = 1000; // static 변수는 인스턴스 생성과 상관 없이 먼저 생성됨
        // 기준값: 1000 / 학생이 생성될 때마다 순서대로 증가
        public int studentID;
        public String studentName;
        public int grade;
        public String address;

        public Student1(){ // 생성자
            serialNum++; // 학생이 생성될 때마다 증가
            studentID = serialNum; // 증가된 값을 학번 인스턴스 변수에 부여
            // static 변수는 모든 인스턴스가 공유 -> 바로 학번에 적용하면 모든 학생의 학번이 동일해짐
        }

        public String getStudentName(){
            return studentName;
        }

        public void setStudentName(String name){
            studentName = name;
        }
    }

    # 학번 확인하기
    package staticex;

    public class StudentTest2{
        public static void main(String[] args){
            Student1 student1 = new Student1();
            student1.setStudentName("Lee");
            System.out.println(student1.serialNum); // @@ 1001
            System.out.println(student1.studentName + " 학번: " + student1.studentID); // @@ "Lee 학번: 1001"

            Student1 student2 = new Student();
            student2.setStudentName("Son");
            System.out.println(student2.serialNum); // @@1002
            System.out.println(student2.studentName + " 학번: " + student2.studentID); // @@ "Son 학번: 1002"
        }
    }
    ```
* 클래스 변수
    - static 변수는 인스턴스보다 먼저 생성 -> 클래스 이름으로도 참조해 사용 가능
    - 클래스에 속해 한 번만 생성되고 이를 여러 인스턴스가 공유
    ```
    package staticex;

    public class StudentTest3{
        public static void main(String[] args){
            Student1 student1 = new Student1();
            student1.setStudentName("Lee");
            System.out.println(Student1.serialNum); // serialNum 변수를 직접 클래스 이름(Student1)로 참조 @@ 1001
            System.out.println(student1.studentName + " 학번: " + student1.studentID); // @@ "Lee 학번: 1001"

            Student1 student2 = new Student();
            student2.setStudentName("Son");
            System.out.println(Student1.serialNum); // serialNum 변수를 직접 클래스 이름(Student1)로 참조 @@1002
            System.out.println(student2.studentName + " 학번: " + student2.studentID); // @@ "Son 학번: 1002"
        }
    }
    ```

* static 메서드 (= 클래스 메서드= class method)  
    : static 변수를 위한 메서드
    ```
    # serialNum의 get(), set() 메서드 사용
    package staticex;

    public class Student2 {
        private static int serialNum = 1000; // private 변수로 변경 -> 외부에서 serialNum 변수 직접 참조 불가
        public int studentID;
        public String studentName;
        public int grade;
        public String address;

        public Student2(){ // 생성자
            serialNum++; // 학생이 생성될 때마다 증가
            studentID = serialNum; // 증가된 값을 학번 인스턴스 변수에 부여
        }

        public String getStudentName(){
            return studentName;
        }

        public void setStudentName(String name){
            studentName = name;
        }

        public static int getSerialNum(){ // serialNum의 get() 메서드
            int i = 10;
            return serialNum;
        }

        public static void setSerialNum(int serialNum){ // serialNum의 set() 메서드
            Student2.serialNum = serialNum;
        }
    }

    # 학번 출력하기
    public class StudentTest4{
        public static void main(String[] args){
            Student2 student1 = new Student2();
            student1.setStudentName("Lee");
            System.out.println(Student2.getSerialNum()); // serialNum 값을 가져오기 위해 get() 메서드를 클래스 이름으로 직접 호출 @@ 1001
            System.out.println(student1.studentName + " 학번: " + student1.studentID); // @@ "Lee 학번: 1001"

            Student2 student2 = new Student2();
            student2.setStudentName("Son");
            System.out.println(Student2.getSerialNum()); // serialNum 값을 가져오기 위해 get() 메서드를 클래스 이름으로 직접 호출 @@1002
            System.out.println(student2.studentName + " 학번: " + student2.studentID); // @@ "Son 학번: 1002"
        }
    }
    ```

* 클래스 메서드와 인스턴스 변수
    - 클래스 메서드
        + 인스턴스가 생성되지 않아도 호출 가능
    - 인스턴스 변수
        + 인스턴스가 생성되어야 메모리가 할당됨
    - 클래스 메서드 내부에서는 지역 변수, 클래스 변수 사용 가능 + 인스턴스 변수 사용 불가
    - 일반 메서드에서 클래스 변수 사용 가능
    ```
    package staticex;

    public class Student2 {
        private static int serialNum = 1000; // private 변수로 변경 / static 변수 / 기준값: 1000
        public int studentID;
        public String studentName;
        public int grade;
        public String address;
        
        ...

        public static int getSerialNum(){ // serialNum의 get() 메서드 -> 클래스 메서드
            int i = 10; // i: 지역변수 <- 메서드 내부에서 선언함
            studentName = "Lee"; // error: 인스턴스 변수 
            return serialNum;
        }
    }

    # studentName 변수 살펴보기
    package staticex;

    public class StudentTest5{
        public static void main(Sting[] args){
            System.out.println(Student2.getSerialNum()) // 인스턴스 생성 없이 호출 가능 @@ 1000
        }
    }
    ```

## 06-4 변수의 유효 범위
* 변수 유효 범위(scope)
    - 지역 변수의 유효 범위
        + 하나의 함수에 선언한 지역 변수는 다른 함수에서 사용 불가능
        + 지역 변수가 생성되는 메모리: 스택(stack)
        + 함수가 반환되면, 할당되었던 메모리 공간이 해제 및 삭제
    - 멤버 변수(= 인스턴스 변수)의 유효 범위
        + 클래스의 모든 메서드에서 사용 가능
        + 클래스가 생성될 때 저장되는 메모리: 힙(heap)
        + 인스턴스가 가비지 컬렉터에 의해 수거되면, 메모리에서 사라짐
        + 클래스 내부의 여러 메서드에서 사용할 변수로 적합
    - static 변수의 유효 범위
        + 사용자가 프로그램을 실행하면, 메모리에 프로그램이 상주 -> 이 중 데이터 영역에서 상수, 문자열, static 변수가 생성됨
        + 클래스 생성과 관련 없이 처음부터 데이터 영역 메모리에 생성
        + cf) 멤버 변수: 객체가 생성되는 문장 new가 되어야만 생성됨
        + private이 아니라면, 클래스 외부에서도 객체 생성과 무관하게 사용 가능
        + 프로그램이 끝나고 메모리를 해제하면, 소멸
        + 크기가 작은 변수에 적합  
    ![image](https://user-images.githubusercontent.com/104348646/195266642-7adeaa19-397a-4946-a1c7-4be37c43943d.png)  

## 06-5 static 응용 - 싱글톤 패턴
* 싱클톤 패턴(singleton pattern)  
    : 객체 지향 프로그램에서 인스턴스를 단 하나만 생성하는 디자인 패턴
    - 실습) static을 응용하여 프로그램 전반에서 사용하는 인스턴스를 하나만 구현

* 싱글톤 패턴으로 회사 클래스 구현하기
    - 단계 1: 생성자를 private로 만들기
        + 디폴트 생성자는 public -> 외부 클래스에서 인스턴스 생성 가능 -> private로 설정
        + Company 클래스 내부에서만 이 클래스의 생성 제어 가능
        ```
        package singleton;

        public class Company {
            private Company(){} // 접근 제어자를 private로 설정
        }
        ```
    - 단계 2: 클래스 내부에 static으로 유일한 인스턴스 생성하기
        + Company 클래스 내부에서 하나의 인스턴스 생성
        + 프로그램 전체에서 사용할 유일한 인스턴스
        ```
        package singleton;

        public class Company{
            private static Company instance = new Company();
            private Company(){}
        }
        ```
    - 단계 3: 외부에서 참조할 수 있는 public 메서드 만들기
        + private로 선언한 유일한 인스턴스를 외부에서도 사용할 수 있도록 설정
        + public 메서드 생성 -> 인스턴스 반환
        + static 메서드로 선언 -> 인스턴스 생성과 상관 없이 호출할 수 있어야 함
        ```
        package singleton;

        public class Company{
            ...

            public static Company getInstance(){ // public get() 메서드 -> 인스턴스를 외부에서 참조 가능
                if(instance == null){
                    instance = new Company();
                }
                return instance; // 유일하게 생성한 인스턴스 반환
            }
        }
        ```
    - 단계 4: 실제로 사용하는 코드 만들기
        + getInstamce() 메서드를 호출 -> 반환 값: 유일한 인스턴스
        ```
        package singleton;

        public class CompanyTest{
            public static void main(Striing[] args){
                Company c1 = Company.getInstance(); // 클래스 이름으로 getInstance() 호출하여, 참조 변수에 대입
                Company c2 = Company.getInstance(); // 클래스 이름으로 getInstance() 호출하여, 참조 변수에 대입
                System.out.println(c1 == c2); // 두 변수가 같은 주소인지 확인 @@ true -> 참조 값은 항상 같음(싱글톤 패턴)
            }
        }
        ```
