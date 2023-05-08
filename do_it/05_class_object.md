## 요약 정리
![image](https://user-images.githubusercontent.com/104348646/195062188-e95ebbb0-aa50-4f47-9852-78d4754cb721.png)

## 05-1 객체 지향 프로그래밍과 클래스
* 객체와 객체 지향 프로그래밍
    - 객체(object)  
        : 구체적, 추상적 데이터로, 자신과 다른 것을 식별 가능한 것
    - 객체 지향 프로그래밍(OOP: Object-Oriented Programming)  
        : 객체를 기반으로 하는 프로그래밍

* 생활 속에서 객체 찾아보기
    - 절차 지향 프로그래밍(Procedural Programming)
        + 순서대로 일어나는 일을 시간 순으로 프로그래밍
        + ex) C 언어  
        ![image](https://user-images.githubusercontent.com/104348646/194532627-50da5b34-ee34-4b08-a4a9-95073e8bb78d.png)  
    - 객체 지향 프로그래밍
        + 객체를 정의하고 객체 간 협력을 프로그래밍  
        ![image](https://user-images.githubusercontent.com/104348646/194532669-fc21d72d-049c-4f24-a50c-7b5705bc40e9.png)  

* 클래스란  
    : 객체에 대한 속성과 기능을 코드로 구현한 것
    - 클래스를 정의하다 <- 객체를 클래스로 구현한다
    - 객체의 속성  
        : 멤버 변수(member variable) (= 객체의 특성= property) (= 속성= attribute)
    - 객체의 기능  
        + 객체가 하는 기능들을 메서드로 구현(method= member function)
        + 객체의 동작  
    ![image](https://user-images.githubusercontent.com/104348646/194532745-d7adaf1c-ebb3-473c-a949-86a87e3e95e5.png)  

    - 클래스 정의
        + 기본: 하나의 java 파일에 하나의 클래스
        + 여러 개의 클래스가 같이 있는 경우 -> public 클래스는 하나 + (public 클래스의 이름 = java 파일의 이름)
        + 자바의 모든 요소가 클래스 내부에 있어야 함
        ```
        (접근 제어자) class [클래스명]{
            [멤버변수];
            [메서드];
        }
        ```
        ```
        # 학생 클래스 만들기
        public class Student{ // class: 클래스를 만드는 예약어 / Student: 클래스명
            // 멤버 변수들
            int studentID;
            String studentName;
            int grade;
            String address;
        }
        // ex)
        // 속성: 학번, 이름, 학년, 주소
        // 기능: 수강신청, 수업 듣기, 시험 보기
        ```

    - 클래스명 짓는 규칙  
        + 코딩 컨벤션(coding convention)  
          : 코딩을 할 때, 읽기 쉽고 이해하기 쉽도록 정한 규칙
        + 대문자로 시작

## 05-2 클래스 살펴보기
* 클래스 속성을 구현하는 멤버 변수
    - ex) Person 클래스
        ```
        public class Person{
            String name;
            int height;
            double weight;
            char gender;
            boolean married
        }
        ```
    - 변수의 자료형
        + 기본 자료형  
          : int, long, float, double 등
        + 참조 자료형  
          : String, Date, 다른 클래스에서 사용하는 멤버 변수의 자료형

* 클래스 기능을 구현하는 메서드
```
public class Student {
    // 멤버 변수들
    int studentID;
    String studentName;
    int grade;
    String address;

    public void showStudentInfo(){ // 메서드 추가: showStudentInfo()
        System.out.println((studentName + "," + address));
    }
}
```

* 패키지
    : 클래스 파일의 묶음
    - 패키지 생성 -> 프로젝트 하위에 물리적으로 디렉토리가 생성됨
    - 프로젝트 전체 소스 코드를 구성하는 계층구조
        ![image](https://user-images.githubusercontent.com/104348646/194532807-bb606952-7e23-4080-838a-2401c7715d54.png)
    - 패키지 선언하기
        + 패키지 이름이 다르면, 클래스 이름이 같아도 다른 클래스임
        ```
        package domain.student.view; // 클래스의 전체 이름(class full name)

        public class StudentView{

        }
        ```

## 05-3 메서드
* 메서드    
    : 함수(function)의 한 종류
* 함수  
    : 하나의 기능을 수행하는 일련의 코드
    - 어떤 기능을 수행하도록 미리 구현해 놓고 필요할 때마다 호출하여 사용 가능

* 함수의 입력과 반환
    - 매개변수  
        : 함수의 입력으로 받는 변수
    - 반환 값  
        : 함수를 수행한 후 결과로 되돌려 주는 값

* 함수 정의하기  
    ```
    int add (int num1, int num2){ // int: 함수 반환형 / add: 함수명 / int num1. int num2: 매개변수
        int reult;
        result = num1 + num2;
        return result; // return: return 예약어
    }
    ```
    - 함수 이름: add
    - 매개변수: num1, num2
        + 함수를 호출할 때, () 안의 자료형에 맞게 함수에 전달됨
        + 매개변수가 필요 없는 함수
            ```
            int getTenTotal(){
                int i;
                int total = 0;
                for(i = 1; i <= 10; i++){
                    total += i;
                }
                return total // 1 ~ 10의 합을 반환
            }
            ```
    - return 예약어와 반환형
        + 결과 값(= 반환 값)이 변수 result에 저장됨
        + return: '이 함수의 결과 값을 반환합니다'를 뜻하는 예약어
        + 반환형: 반환 값의 자료형 ex) int
        + 반환 값이 없는 함수
            ```
            void printGreetiing(String name){
                System.out.println(name);
                return; // 반환 값 없음 -> void: '반환할 값이 없다'는 뜻의 예약어
            }
            ```
        + 함수 수행을 끝내고 프로그램 흐름 중에서 호출한 곳으로 다시 되돌아가는 경우에 사용
            ```
            void divide(int num1, num2){
                if(num2 == 0){
                    System.out.println("impossible");
                    return; // 함수 수행 종료 예약어
                }
                else{
                    int result = num1 / num2;
                    System.out.println(result);
                }
            }
            ```

* 함수 호출하고 값 반환하기
    ```
    package classpart;

    public class FunctionTest{
        public static void main(String[] args){
            int num1 = 10; // main() 함수 / add 함수의 매개변수와 같을 필요 없음
            int num2 = 20;

            int sum = add(num1, num2); // add 함수 값 호출
            System.out.println(sum);
        }
        
        public static int add(int n1, int n2){ // add 함수 정의 / 매개변수: n1, n2
            int result = n1 + n2;
            return result; // 결과 값 반환
        }
    }
    ```

* 함수 호출과 스택 메모리
    - 스택(stack)
        : 함수가 호출될 때 사용하는 메모리  
        ![image](https://user-images.githubusercontent.com/104348646/195063728-ffe836a3-59a3-4dbb-b34d-b15e48a8ff4a.png)  
        + 메모리 생성 과정
            1. main() 함수의 스택 생성  
            2. main()에서 add() 호출  
            3. add() 함수의 스택 생성  
        + 메모리 해제 화정
            1. add() 함수 수행 종료에 따라 add() 함수의 스택 제거
            2. main() 함수
        + 여러 함수를 사용하는 경우, 함수를 호출한 순서대로 메모리 공간 생성 + 맨 마지막에 호출한 함수부터 반환
            + ex) 호출: A() -> B() -> C() -> 소멸: C() -> B() -> A()
    - 지역 변수: 함수 내부에서만 사용하는 변수
        + 스택 메모리에 생성됨

* 함수의 장점  
    1. 기능을 나누어 코드를 효율적으로 구현 -> 가독성
    2. 기능별로 함수를 구현해 놓으면, 같은 기능을 매번 코드로 만들지 않고 호출 -> 편리함
    3. 디버깅 작업이 편리: 오류가 난 기능만 찾아서 수정
    + 하나의 함수에 하나의 기능 구현하기

* 클래스 기능을 구현하는 메서드
    - 메서드(method)  
        : 클래스 내부에서 사용하는 멤버 함수
        + 멤버 변수를 사용하여 기능 구현
        + 함수 + 객체 지향 개념
    ```
    package classpart;

    public class Student{
        // 멤버 변수
        int studentID;
        String studentName;
        int grade;
        String address;
        
        public String getStudentName(){ // 학생 이름을 반환하는 메서드
            return studentName;
        }
        
        public void SetStudentName(String name){ // 학생 이름을 부여하는 메서드 / String name: 학생 이름을 매개변수로 전달
            studentName = name;
        }
    }
    ```

    - naming convention
        + 클래스: 대문자로 시작
        + public 클래스: 1개(unique) + (= 자바 파일 이름)
        + package: 모두 소문자
        + 변수, 메서드: 소문자로 시작

## 05-4 클래스와 인스턴스
* 클래스 사용과 main() 함수
    - 멤버 변수: 클래스 속성을 나타냄
    - 메서드: 멤버 변수를 이용하여 클래스 기능 구현
    - 프로그램을 시작하는 main() 함수  
        : JVM이 프로그램을 시작하기 위해 호출하는 함수
        + 클래스 내부에 만듦. 클래스의 메서드는 아님

* Student 클래스에 main() 함수 포함하기
    - 클래스 내부에 main() 함수 -> 이 클래스가 프로그램의 시작 클래스
    ```
    package classpart;

    public class Student{
        // 멤버 변수
        int studentID;
        String studentName;
        int grade;
        String address;
        
        public String getStudentName(){ // 학생 이름을 반환하는 메서드
            return studentName;
        }
        
        // main 함수: 직접 실행해 클래스가 제대로 수행되는지 확인
        public static void main(String[] args){
            Student studentAhn = new Student(); // Student 클래스 생성
            studentAhn.studentName = "james"; // 값 대입

            System.out.println(studentAhn.studentName);
            System.out.println(studentAhn.getStudentName());
        }
    }
    ```

* main() 함수를 포함한 실행 클래스 따로 만들기
    - 클래스의 패키지가 다른 경우, import 문을 사용해서 불러옴
```
public class StudentTest{
    // main() 함수
    public static void main(String[] args){
        Student studentAhn = new Student(); // Student 클래스 생성
        studentAhn.studentName = "james";

        System.out.println(studentAhn.studentName);
        System.out.println(studentAhn.getStudentName());
    }
}
```

* new 예약어로 클래스 생성하기
    ```
    클래스형 변수 이름 = new 생성자; // new 예약어: 클래스를 생성
    ```
    - 인스턴스  
        : 실제로 사용할 수 있도록 생성된 클래스
    - 참조 변수  
        : 인스턴스를 가리키는 클래스형 변수

* 인스턴스와 참조 변수
    - 객체, 클래스, 인스턴스
        + 객체
            : 의사나 행위가 미치는 대상
        + 클래스
            : 객체를 코드로 구현한 것
        + 인스턴트
            : 클래스가 메모리 공간에 생성된 상태  
        ![image](https://user-images.githubusercontent.com/104348646/194532864-9753a46c-1182-48b8-bf92-d192e1fc6d54.png)  
    - 인스턴스 여러 개 생성하기
        ```
        public class StudentTest{
            // main() 함수
            public static void main(String[] args){
                Student student1 = new Student(); // Student1: 인스턴스
                student1.studentName = "james";
                System.out.println(student1.getStudentName());

                Student student2 = new Student(); // Student2: 인스턴스
                student2.studentName = "chris";
                System.out.println(student2.getStudentName());
            }
        }
        ```
    - 참조 변수 사용하기
        ```
        참조 변수.멤버 변수
        참조 변수.메서드
        ```
        ```
        student1.studentName = "james"; // studentName: 멤버 변수 사용
        System.out.println(student1.getStudentName()); // getStudentName(): 메서드 사용
        ```

* 인스턴스와 힙 메모리
    - 힙 메모리(heap memory)
        : 변수(인트턴스)를 저장하는 공간
        + 지역 변수 student1에 생성된 인스턴스를 대입함 = student1에 인스턴스가 생성된 힙 메모리의 주소를 대입함
        + 프로그램에서 사용하는 동적 메모리(dynamic memory) 공간
            + 사용이 끝나면 메모리를 해제
            + JAVA에서는 가비지 컬렉터가 자동으로 메모리를 해제함
        ```
        # 인스턴스 생성
        Student student1 = new Student();
        Student student2 = new Student();
        ```
        ![image](https://user-images.githubusercontent.com/104348646/195062960-7f627d8d-0ab9-40c4-8c98-ab749f163c18.png)  
        + 클래스에서 선언한 멤버 변수 = 인스턴스 변수(멤버 변수를 저장하는 공간이 매번 따로 생김)

    - 참조 변수와 참조 값
        + 참조 변수: 힙 메모리에 생성된 인스턴스
            ```
            # 참조 변수 값 출력

            public class StudentTest{
                // main() 함수
                public static void main(String[] args){
                    Student student1 = new Student(); // Student1: 인스턴스
                    student1.studentName = "james";

                    Student student2 = new Student(); // Student2: 인스턴스
                    student2.studentName = "chris";

                    System.out.println(student1); // 참조 변수 값 @@ classpart.Student@16f65612
                    System.out.println(student2); // 참조 변수 값 @@ classpart.Student@311d617d
                }
            }
            ```
        + 참조 값(= 해시 코드= hash code): 힙 메모리에 생성된 인스턴스의 메모리 주소
            + [클래스 이름]@[주소 값]
            + JVM에서 객체가 생성되었을 때, 생성된 객체에 할당하는 가상 주소
        ![image](https://user-images.githubusercontent.com/104348646/195063110-ba54c7df-7ba2-4019-ae64-db3ca4dac476.png)  

## 05-5 생성자
* 생성자(constructor)  
    : 인스턴스를 초기화 할 때의 명령어 집합
    - 생성자의 이름 = 클래스의 이름
    - 메소드 아님 -> 상속되지 않음 + 리턴 값 없음
    - 하나의 클래스에는 반드시 하나 이상의 생성자가 존재
    ```
    <modifiers> <class_name>([<argument_list>])
    {
        [<statements>]
    }
    ```
    ```
    # 생성자 만들기
    package constructor;

    public class Person{
        String name;
        float height;
        float weight;
    }

    # 생성자 테스트하기
    package constructor;

    public class PersonTest{
        public static void main(String[] args){
            Person personLee = new Person(); // Person(): 생성자
        }
    }
    ```

    - 디폴트 생성자(default constructor)  
        : 생성자가 없는 클래스에서 클래스 파일을 컴파일 할 때, 자바 컴파일러에서 자동으로 생성함
        + 매개 변수, 구현 코드 없음
        + 매개 변수를 추가하면, 디폴트 생성자는 제공되지 않음
        ```
        package constructor;

        public class Person{
            String name;
            float height;
            float weight;

            public Person(){} // 디폴트 생성자
        }
        ```
    - 생성자 만들기
        + 주로 멤버 변수에 대한 값들을 매개변수로 받아서 인스턴스가 새로 생성될 때, 변수 값들을 초기화함
        ```
        # 생성자 만들기
        package constructor;

        public class Person{
            String name;
            float height;
            float weight;

            public Person(String pname){ // 사람 이름을 매개변수로 입력받아서 Person 클래스를 생성하는 생성자
                name = pname;
            }
        }

        # 생성자 테스트(error)
        package constructor;

        public class PersonTest{
            public static void main(String[] args){
                Person personLee = new Person(); // error: 디폴트 생성자가 없음
            }
        }

        # 디폴트 생성자 직접 추가하기
        package constructor;

        public class Person{
            String name;
            float height;
            float weight;

            public Person(){} // 디폴트 생성자 직접 추가

            public Person(String pname){
                name = pname;
            }
        }
        ```

* 생성자 오버로드(constructor overload)  
    : 생성자가 두 개 이상 제공되는 경우
    - 원하는 생성자를 선택해 사용 가능
    ```
    public class Student{
        int studentID;

        public.Student(int studentID){
            this.studentID = studentID;
            // 학번을 매개변수로 입력받아 Student 클래스를 생성하는 생성자
            // 학생을 생성할 때, 학번이 반드시 필요 -> 디폴트 생성자를 구현하지 않음
        }
    }
    ```
    ```
    # 생성자 사용하기
    package constructor;

    public class Person{
        String name;
        float height;
        float weight;

        public Person(){} // 디폴트 생성자

        public Person(String pname){ // 이름을 매개변수로 입력받는 생성자
            name = pname;
        }

        public Person(String pname, float pheight, float pweight){ // 이름, 키, 몸무게를 매개변수로 입력받는 생성자
            name = pname;
            height = pheight;
            weight = pweight;
        }
    }

    # 클래스 테스트 구현하기
    package constructor;

    public class PersonTest{
        public static void main(String[] args){
            // 디폴트 생성자로 클래스를 생성한 후, 인스턴스 변수 값을 따로 초기화
            Person personKim = new Person();
            personKim.name = "Kim";
            personKim.height = 180.0F;
            personKim.wight = 85.5F;

            Person personLee = new Person("Lee", 175, 75); // 인스턴스 변수 초기화와 동시에 클래스 생성
        }
    }
    ```

## 05-6 참조 자료형
* 참조 자료형  
    : 클래스 형으로 선언하는 자료형
    - Sting, Date, 클래스 등
    ```
    # 학생 클래스 만들기
    package reference;

    public class Student1{
        int studentID;
        String studentName; // String: 참조 자료형
        int koreaScore;
        int mathScore;
        Sting koreadSubject;
        String mathSubject;
    }

    # 과목 클래스와 학생 클래스로 쪼개기
    ## 과목 클래스 만들기
    package reference;

    public class Subject{
        String SubjectName;
        int scorePoint;
    }
    ## 학생 클래스 만들기
    package reference;

    public class StudentNew{
        int studentID;
        String studentNmea;
        Subject korean; // Subject형을 사용하여 선언
        Subject math;
    }
    ```

## 05-7 정보 은닉(information hiding)
* 접근 제어자 살펴보기
    - 접근 제어자(access modifier)  
        : 클래스 내부의 변수나 메서드, 생성자에 대한 접근 권한을 지정하는 예약어
        + public 접근 제어자  
            : 외부 클래스에서 클래스 내부의 멤버 변수나 메서드에 접근 가능
        + private 접근 제어자  
            : 외부 클래스에서 클래스 내부의 멤버 변수나 메서드에 접근 불가능
        ```
        # private 접근 제어자 사용

        package hiding;

        public class Student{
            int studentID;
            private String studentName; // studentName 변수를 private로 선언
            int grade;
            String address;

            public String getStudentNmae(){
                return studentName;
            }

            public void setStudentName(String studentName){
                this.studentName = studentName;
            }
        }

        # private 변수 테스트(error)
        package hiding;

        public class StudentTest{
            public static void main(String[] args){
                Student studentLee = new Student();
                studentLee.studentName = "Lee"; // error: private -> 외부 클래스인 StudentTest.java 클래스에서 접근 불가

                System.out.println(studentLee.getStudentName());
            }
        }
        ```
    - get(), set() 메서드
        : private  변수를 사용하는 메서드
        ```
        # get(), set() 메서드 사용
        package hiding;

        public class Student{
            int studentID;
            private String studentName;
            int grade;
            String address;

            // private 변수인 studentName에 접근해 값을 가져오는 public get() 메서드
            public String getStudentName(){
                return studentName;
            }

            // private 변수인 studentName에 접근해 값을 지정하는 public set() 메서드
            public void setStudentName(String studentname){
                this.studentName = studentName;
            }
        }
        ```
        ```
        # private 변수 테스트 재시도
        package hiding;

        public class StudentTest{
            public static void main(String[] args){
                Student studentLee = new Student();
                studentLee.setstudentName("Lee"); // setStudentName() 메서드를 활용해 private 변수에 접근

                System.out.println(studentLee.getStudentName());
            }
        }
        ```
* 정보 은닉(information hiding)  
    : 클래스 내부에서 사용할 변수나 메서드를 private로 선언해서 외부에서 접근하지 못하도록 하는 것
    - 자바에서는, 접근 제어자를 사용해서 구현
    - 오류를 막을 수 있음
    ```
    # 멤버 변수를 public으로 선언
    public class MyDate{
        public int day;
        public int month;
        public int year;
    }

    public class MyDateTest{
        public static void main(String[] args){
            MyDate date = new Mydate;
            date.month = 2;
            date.day = 31; // 접근이 제한되지 않음 -> 2/31일은 존재하지 않지만 입력이 가능 -> 정보 오류를 막을 수 없음
            date.year = 2018;
        }
    }

    # 멤버 변수를 private로 선언
    public class MyDate{
        // 클래스 내부에서 사용할 변수나 메서드를 private로 선언 -> 외부에서 입력 불가능
        private int day;
        private int month;
        private int year;

        public void setDay(int day){ // 내부 메서드의 규칙에 따라 내부에서만 생성 가능
            if(month == 2){
                if(day < 1 || day > 28){
                    System.out.println("error");
                } else{
                    this.day = day;
                }
            }
        }
    }
    ```
    ![image](https://user-images.githubusercontent.com/104348646/195063284-cedb5708-eb0e-4b4f-8a09-c4652bfec024.png)  
