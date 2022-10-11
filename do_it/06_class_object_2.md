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
    (((그림 1-1)))

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

