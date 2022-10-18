## 11-1 Object 클래스
* java.lang 패키지
    - 프로그래밍 시 import 하지 않아도 자동으로 import 됨
    - 컴파일 할 때, impoer java.lang.*; 문장이 자동으로 추가됨
    - 많이 사용하는 기본 클래스들이 속한 패키지
    - Strinig, Integer, System 등

* Object 클래스
    - 모든 클래스의 최상위 클래스
    - java.lang.Object 클래스
    - 모든 클래스는 Object 클래스에서 상속 받음
    - 컴파일러가 extends Object를 추가함
        ```
        # 코드를 작성할 때
        class Studnet{
            int studentID;
            String studentName;
        }

        # 컴파일러가 변환
        class Student extends Object{
            int studentID;
            String studentName;
        }
        ```
    - 모든 클래스는 Object 클래스의 메서드를 사용할 수 있음
    - 모든 클래스는 Object 클래스의 메서드 중 일부를 재정의 가능
        + final로 선언된 메서드는 재정의 불가능
    - 주로 사용하는 Object 메서드  
        ![image](https://user-images.githubusercontent.com/104348646/196429780-4ba039bb-724c-4f81-a89e-afaf2e09c6c3.png)

* toString() 메서드
    - Object 클래스의 메서드

    - 객체의 정보를 문자열(String)으로 바꿈
    - String이나 Interger 클래스에는 이미 재정의되어 있음
    ```
    # Object 클래스의 toString() 메서드

    package object;

    class Book {
        int bookNumber;
        String bookTitle;

        Book(int bookNumber, String bookTitle){ // 책 번호와 제목을 매개변수로 입력받는 생성자
            this.bookNumber = bookNumber;
            this.bookTitle = bookTitle;
        }
    }

    public class ToStringEx{
        public static void main(String[] args){
            Bookbook1 = new Book(200, "개미");

            System.out.println(book1); // 인스턴스 정보(클래스 이름.주소 값) @@ object.Book@16f65612
            System.out.println(book1.toString); // toString() 메서드로 인스턴스 정보를 보여줌 @@ object.Book@16f65612
        }
    }
    ```
    ```
    # toString 메서드의 원형
    getClass().getName() +'@' + Integer.toHexString(hashCode())
    // ex) object.Book@16f65612 // 클래스 이름@해시코드 값
    ```
    - String과 Interger 클래스의 toString() 메서드
        + 출력 결과가 '클래스 이름@해시코드 값'이 아닌 경우
            ```
            String str = new String("test);
            System.out.println(str); // @@ "test"
            Integer i1 = new Integer(100);
            System.out.println(i1); // @@ 100
            ```
            + String과 Integer 클래스는 toString() 메서드를 미리 재정의해 둠
    - Book 클래스에서 toString() 메서드 직접 재정의하기
        ```
        package object;

        class Book {
            int bookNumber;
            String bookTitle;

            Book(int bookNumber, String bookTitle){
                this.bookNumber = bookNumber;
                this.bookTitle = bookTitle;
            }
            
            @Override
            public String toString(){
                return bookTitle + "," + bookNumber;
            }
        }

        # Test
        public class ToStringEx{
            public static void main(String[] args){
                Bookbook1 = new Book(200, "개미");

                System.out.println(book1); // @@ 개미,200
                System.out.println(book1.toString); // @@ 개미,200
            }
        }
        ```

* equals() 메서드
    - 두 인스턴스의 주소 값을 비교하여 true/false를 반환
    - 재정의하여 두 인스턴스가 물리적(인스턴스 메모리 주소), 논리적(메모리 주소가 달라도 같은 인스턴스인지)로 동일함의 여부를 반환
    - ex) Student 클래스
        + 같은 학번의 학생인 경우 여러 인스턴스의 주소 값은 다르지만, 같은 학생으로 처리해야 학점/정보 산출에 문제가 생기지 않으므로 이런 경우 equals()메서드를 재정의함
            ```
            # 생성된 인스턴스를 가리키는 참조변수(studentLee)를 다른 변수(studentLee2)에 복사
            Student studentLee = new Student(100, "이상원");
            Strudent studentLee2 = studentLee; // 주소 복사
            ```  
            ![image](https://user-images.githubusercontent.com/104348646/196429934-ee14d859-b169-40bc-8be3-ce29ad5d72c9.png)  
            ```
            # 이름, 학번이 동일한 인스턴스 생성 -> 다른 변수(studentSang)이 가리킴

            Student studentLee = new Student(100, "이상원");
            Strudent studentLee2 = studentLee;
            Student studentSang = new Student(100, "이상원");
            ```  
            ![image](https://user-images.githubusercontent.com/104348646/196429964-9f750f9d-1071-4db1-a160-bd238a4a1c90.png)  
            => 논리적으로는 studentSang도 같은 학생으로 처리해야 함
            ```
            public class EqualsTest{
                public static void main(String[] args){
                    Student studentLee = new Student(100, "이상원");
                    Strudent studentLee2 = studentLee;
                    Student studentSang = new Student(100, "이상원");

                    // studentLee와 studentLee2: 동일한 주소의 두 인스턴스 비교
                    if(studentLee == studentLee2) // == 기호로 비교
                        System.out.println("studentLee와 studentLee2의 주소는 같습니다"); // @@
                    else
                        System.out.println("studentLee와 studentLee2의 주소는 다릅니다");

                    if(studentLee.equals(studentLee2)) // equals() 메서드로 비교
                        System.out.println("studentLee와 studentLee2는 동일합니다"); // @@
                    else
                        System.out.println("studentLee와 studentLee2는 동일하지 않습니다");

                    // studentLee와 studentSang: 동일인이지만 인스턴스의 주소가 다른 경우
                    if(studentLee == studentSang) // == 기호로 비교
                        System.out.println("studentLee와 studentSang의 주소는 같습니다");
                    else
                        System.out.println("studentLee와 studentSang2의 주소는 다릅니다"); // @@

                    if(studentLee.equals(studentSang)) // equals() 메서드로 비교
                        System.out.println("studentLee와 studentSang는 동일합니다");
                    else
                        System.out.println("studentLee와 studentSang는 동일하지 않습니다"); // @@
                }
            }
            ```
            + equals() 메서드의 원래 기능: 두 인스턴스의 주소 비교
                + studentLee2: true / studentSang: false
                + == 기호: 물리적으로 메모리 주소 일치 여부를 확인
                + equals() 메서드: 논리적으로 같은 인스턴스인지 확인하도록 구현 가능
    - String과 Integer 클래스의 equals() 메서드
        ```
        package object;

        public class StringEquals{
            public static void main(String[] args){
                String str1 = new String("abc");
                String str2 = new String("abc");

                System.out.println(str == str2); // 물리적 @@ false
                System.out.println(str.equals(str2)); // 논리적으로 같은 문자열 @@ true

                Integer i1 = new Integer(100);
                Integer i2 = new Integer(100);

                System.out.println(i1 == i2); // 물리적 @@ false
                System.out.println(i1.equals(i2)); // 논리적으로 같은 문자열 @@ true
            }
        }
        ```
    - Student 클래스에서 equals() 메서드 직접 재정의하기
        ```
        package object;

        class Student{
            ...

            // equals() 메서드 재정의
            @Override
            public boolean equals(Object obj){
                if(obj instanceof Student){
                    Student.std = (Student)obj;
                    if(this.studentId == std.studentID) // 학생의 학번이 같으면 true 반환(논리적 일치)
                        return true;
                    else return false;
                }
                return false;
            }
        }

        public class EqualsTest{
            public static void main(String[] args){
                ...
            }
        }
        // @@ studentLee와 studentLee2의 주소는 같습니다
        // @@ studentLee와 studentLee2는 동일합니다
        // @@ studentLee와 studentSang2의 주소는 다릅니다
        // @@ studentLee와 studentSang는 동일합니다 // 물리적으로 메모리 주소가 일치하지 않지만, 논리적으로 일치함을 나타냄
        ```

* hashCode() 메서드
    - hash  
        : 정보를 저장, 검색하기 위해 사용하는 자료구조
        + 힙 메모리에 인스턴스가 저장되는 방식을 의미
    - 해시함수: 자료의 특정 값(키 값)에 대해 저장 위치(저장되어야 할 위치/저장된 해시 테이블 주소)를 반환  
    ![image](https://user-images.githubusercontent.com/104348646/196430016-b7ed4d8e-3f8e-4541-806c-e1a107eef9db.png)  
    - 해시 함수는 어떤 정보인가에 따라 다르게 구현됨
    - hashCode() 메서드: 인스턴스의 저장 주소를 반환
    - 인스턴스를 힙 메모리에 생성하여 관리할 때, 알고리즘을 사용함
        ```
        hachCode = hash(key); // 객체의 해시 코드 값(메모리 위치 값)이 반환됨
        ```
        ```
        # toString() 메서드의 원형
        
        getClass().getName() + '@' + Integer.toHexCode());
        // Integer.toHexCode()) = 해시 코드 값 = JVM이 힙 메모리에 저장한 인스턴스의 주소 값
        ```
        + 논리적으로 동일함을 위해 equals() 메서드를 재정의했다면, hashCode() 메서드도 재정의해 동일한 값이 반환되도록 해야 함
    - String과 Integer 클래스의 hashCode() 메서드
        + String 클래스: 동일한 문자열 인스턴스에 대해 동일한 정수가 반환됨
        + Integer 클래스: 동일한 문자열 인스턴스에 대해 동일한 정수값이 반환됨
        ```
        package object;

        public class HashCodeTest{
            public static void main(String[] args){
                String str1 = new String("abc");
                String str2 = new String("abc");
                
                // abc 문자열의 해시 코드 값 출력
                System.out.println(str1.hashCode()); // @@ 96354
                System.out.println(str2.hashCode()); // @@ 96354
                
                Integer i1 = new Integer(100);
                Integer i2 = new Integer(100);
                
                // Integer(100)의 해시 코드 값 출력
                System.out.println(i1.hashCode()); // @@ 100
                System.out.println(i2.hashCode()); // @@ 100
            }
        }
        ```
    - Student 클래스에서 hashCode() 메서드 재정의하기
        ```
        package object;

        class Student{
            ...
            @Override
            public int hashCode(){
                return studentId;
            }
        }

        public class EqualTest{
            public static void main(String[] args){
                ...
                System.out.println("studentLee의 hashCode: " + studentLee.hashCode());
                System.out.println("studentSang의 hashCode: " + studentSang.hashCode());

                System.out.println("studentLee의 실제 주소값: " + System.identityHashCode(studentLee));
                System.out.println("studentSang의 실제 주소값: " + System.identityHashCode(studentSang));
            }
        }
        ```  
        ![image](https://user-images.githubusercontent.com/104348646/196430231-48b5054b-ba3a-45c5-ad40-6ee0c403fa98.png)  

* clone() 메서드  
    : 객체를 복제해 또 다른 객체를 반환함
    + 객체의 원본을 복제하는 데 사용
    + 기본 틀(prototype)의 복사본을 사용해 복잡한 생성과정을 반복하지 않고 복제
    ```
    # clone() 메서드
    protected Object clone;
    ```
    ```
    # clone() 메서드로 인스턴스 복제하기

    package object;

    // 원점을 의미하는 Point 클래스
    class Point{
        int x:
        int y:

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public String toString(){
            return "x = " + x + "," + "y = " + y;
        }
    }

    class Circle implements Cloneable{ // 객체를 복제해도 된다는 의미: Cloneable 인터페이스를 함께 선언
        Point point;
        int radius;

        Circle(int x, int y, int radius){
            this.radius = radius;
            point = new Point(x, y);
        }

        public String toString(){
            return "원점은 " + point + "이고, 반지름은 " + radius + "입니다";
        }
        
        @Override
        public Object clone() throws CloneNotSupportedException{ // clone() 메서드를 사용할 때 발생할 수 있는 오류를 예외 처리함
            return super.clone();
        }
    }

    # Test

    public class ObjectCloneTest{
        public static void main(String[] args){
            Circle circle = new Circle(10, 20, 30);
            Circle copyCircle = (Circle)circle.clone(); // clone() 메서드를 사용해 circle 인스턴스를 copyCircle에 복제함
            
            System.out.println(circle); // @@ 원점은 x = 10, y = 20이고, 반지름은 30입니다
            System.out.println(copyCircle); // @@ 원점은 x = 10, y = 20이고, 반지름은 30입니다
            System.out.println(System.identityHashCode(circle)); // @@ 2085857771
            System.out.println(System.identityHashCode(copyCircle)); // @@ 248609774
            // copyCircle: 멤버 변수 값은 동일, 주소 값은 상이한 인스턴스
        }
    }
    ```

## 11-2 String 클래스
* String을 선언하는 두 가지 방법
    ```
    String str1 = new String("abc"); // 생성자의 매개변수로 문자열 생성
    String str2 = "test"; // 문자열 상수를 가리키는 방식
    ```
    - 힙 메모리에 인스턴스로 생성되는 경우
        + 객체 생성
        + "abc" 문자열을 위한 메모리가 할당됨
    - 상수 풀(constant pool)에 있는 주소를 참조하는 방법
        + 기존에 만들어져 있던 "test"라는 문자열 상수의 메모리 주소를 가리킴
        + 상수 풀의 문자열을 참조하면, 모든 문자열이 같은 주소를 가리킴  
        ![image](https://user-images.githubusercontent.com/104348646/196430268-bcdf30be-f953-46c9-a95d-e86c582547b1.png)  
        ```
        # 주소 값 비교하기

        package string;

        public class StringTest1{
            public static void main(String[] args){
                String str1 = new String("abc");
                String str2 = new String("abc");

                
                System.out.println(str1 == str2); // 인스턴스가 매번 새로 생성 -> 주소 값이 다름 @@ false 
                System.out.println(str1.equals(str2)); // 문자열은 같음 @@ true

                String str3 = "abc";
                String str4 = "abc";

                System.out.println(str3 == str4); // 문자열 abc는 상수 풀에 저장됨 -> 가리키는 주소 값이 같음 @@ true
                System.out.println(str3.equals(str4)); // 문자열도 같음 @@ true
            }
        }
        ```

* String 클래스의 final char[] 변수
    - char[] 배열을 직접 구현하지 않고 String 클래스 사용
    - 한 번 생성된 String 값(문자열)은 불변(immutable)
    - 두 개의 문자열을 연결하면 하나의 문자열이 변경되는 것이 아니라 새로운 인스턴스가 생성됨
    - 문자열 연결을 계속하면 메모리에 garbage가 많이 생길 수 있음
    ```
    # 두 문자열 연결하기

    package string;

    public class StringTest2{
        public static void main(String[] args){
            String javaStr = new String("java");
            String androidStr = new String("android");

            System.out.println(javaStr); // @@ java
            System.out.println(System.identityHashCode(javaStr)); // @@ 385242642

            javaStr = javaStr.concat(androidStr); // 문자열 javaStr과 ansdroidStr을 연결해서 javaStr에 대입

            System.out.println(javaStr); // @@ javaandroid
            System.out.println(System.identityHashCode(javaStr)); // 새로운 인스턴스 생성 @@ 824009085
        }
    }
    ```  
    ![image](https://user-images.githubusercontent.com/104348646/196431049-e352666d-50e6-4206-896c-dc9f6c6e2aee.png)  

* StringBuffer과 StringBuild 클래스 활용하기
    - 내부적으로 가변적인 char[] 배열을 가지고 있음
    - 문자열을 여러 번 연결하거나 변경할 때 사용하면 유용함
    - 매번 새로 생성하지 않고 기존 배열을 변경 -> garbage가 생기지 않음
    - StringBuffer
        : 멀티 쓰레드 프로그래밍에서 동기화(sybchronization)을 보장
    - StringBuilder
        : 문자열이 안전하게 변경(동기화) 되도록 보장하지 않음
        + 단일 쓰레드 프로그램에서는 권장
        + 속도가 더 빠름
        ```
        # String Builder 클래스 예제

        package string;

        public class StringBuilderTest{
            public static void main(String[] args){
                String javaStr = new String("Java");
                System.out.println("javaStr 문자열 주소: " + System.identityHashCode(javaStr)); // 인스턴스가 처음 생성될 때의 메모리 주소
                // @@ javaStr 문자열 주소: 385242642

                // String으로부터 StringBuilder 생성
                StringBuilder buffer = new StringBuilder(javaStr);
                System.out.println("연산 전 buffer 메모리 주소: " + System.identityHashCode(buffer));
                // @@ 연산 전 buffer 메모리 주소: 824009085

                // 문자열 추가
                buffer.append(" and");
                buffer.append(" android");
                buffer.append(" programming is fun!!!");
                System.out.println("연산 후 buffer 메모리 주소: " + System.identityHashCode(buffer));
                // @@ 연산 후 buffer 메모리 주소: 824009085

                javaStr = buffer.toString(); // toString(): String 클래스로 반환
                System.out.println(javaStr); // @@ Java and android programming is fun!!!
                System.out.println("새로 만들어진 javaStr 문자열 주소: " + System.identityHashCode(javaStr));
                // @@ 새로 만들어진 javaStr 문자열 주소: 2085857771
            }
        }
        ```  
        ![image](https://user-images.githubusercontent.com/104348646/196430379-20e0aca1-53c8-4e2e-b0af-08b45584ff70.png)  

## 11-3 Wapper 클래스
* Wapper 클래스  
    : 객체형을 기본 자료형처럼 사용할 수 있는 클래스
    ```
    public void setValue(Integer i){...} // 객체를 매개변수로 받는 경우
    public Integer returnValue(){...} // 반환 값이 객체형인 경우
    ```
    - 기본 자료형(primitive data type)  
        ![image](https://user-images.githubusercontent.com/104348646/196430417-ac42be21-488e-4242-b48a-109a7bda1bad.png)  

* Integer 클래스 사용하기
    ```
    Integer(int value){...} // 특정 정수를 매개변수로 받는 경우
    Integer(String s){...} // 특정 문자열을 매개변수로 받는 경우
    ```
    - Integer 클래스의 메서드
        ```
        # intValue() 메서드: int 값 가져오기

        Integer iValue = new Integer(100);
        int myValue = iValue.intValue(); // myValue 값을 출력하면 100이 출력됨

        # valueOf() 정적 메서드: 생성자를 사용하지 않고 정수/문자열을 바로 Integer 클래스로 반환

        Integer number1 = Integer.valueOf("100");
        Integer number2 = Integer.valueOf(100);

        # parseInt() 메서드: 문자열이 어떤 숫자를 나타낼 때, 문자열에서 int 값으로 바로 반환
        ex) 학번, 개수 등

        int num = Integer.parseInt("100");
        ```
 
* 오토박싱과 언박싱  
    - 오토박싱(autoboxing)
        : 기본형을 객체형으로 바꿈
    - 언박싱(unboxing)  
        : 객체형을 기본형으로 바꿈
    - Integer는 객체, int는 4바이트 기본 자료형
    - 두 개의 자료를 같이 연산할 때, 자동으로 변환이 일어남
    ```
    Integer num1 = new Integer(100);
    int num2 = 200;
    int sum = num1 + num2; // num1: 언박싱(num.intValue()로 변환)
    Integer num3 = num2; // num2: 오토박싱(Integer.valueOf(num2)로 변환)
    ```

## 11-4 Class 클래스
* Class class란
    - 자바의 모든 클래스와 인터페이스는 컴파일 후 class 파일로 생성됨
    - class 파이에는 객체의 정보(멤버변수, 메서드, 생성자 등)이 포함되어 있음
    - Class 클래스는 컴파일된 class 파일에서 객체의 정보를 가져올 수 있음
    - Class 클래스를 선언하고 클래스 정보를 가져오는 방법
        + 1 Object 클래스의 getClass() 메서드 사용하기
            ```
            // 모든 클래스가 사용 가능
            // 이미 생성된 인스턴스가 필요
            String s = new String();
            Class c = s.getClass(); // getClass() 메서드의 반환형은 Class
            ```
        + 2 클래스 파일 이름을 Class 변수에 직접 대입하기
            ```
            // 컴파일 된 클래스 파일이 있다면, 클래스 이름만으로 가능
            Class c = String.Class;
            ```
        + 3 Class.forName("클래스 이름") 메서드 사용하기
            ```
            // 컴파일 된 클래스 파일이 있다면, 클래스 이름만으로 가능
            Class c = Class.forName("java.lang.String");
            ```
        ```
        # Person 클래스 생성하기

        package classex;

        public class Person{
            private String name;
            private int age;
            
            public Person(){} // 디폴트 생성자
            
            // 이름만 입력받는 생성자
            public Person(String name){
                this.name = name;
            }
            
            // 이름과 나이를 입력받는 생성자
            public Person(String name, int age){
                this.name = name;
                this.age = age;
            }
            
            public String getName(){
                return name;
            }
            public void setName(String name){
                this.name = name;
            }
            
            pulic int getAge(){
                return age;
            }
            public void setAge(int age){
                this.age = age;
            }
        }
        ```
        ```
        # String 클래스 정보 가져오기

        package classex;

        public class ClassTest{
            public static void main(String[] arges) throws ClassNotFoundException{
                Person person = new Person();
                Class pClass1 = person.getClass();
                System.out.println(pClass1.getName()); // 방법 1 @@ classex.Person
                
                Class pClass2 = Person.class;
                System.out.println(pClass1.getName()); // 방법 2 @@ classex.Person
                
                Class pClass3 = Class.forName("classex.Person");
                System.out.println(pClass1.getName()); // 방법 3 @@ classex.Person
            }
        }
        ```

* Class 클래스를 활용해 클래스 정보 알아보기
    - reflection 프로그래밍  
        : Class 클래스를 이용해서 클래스의 정보(생성자, 멤버변수, 메서드)를 가져오고, 이를 활용하며 인스턴스를 생성하고 메서드를 호출하는 등의 프로그래밍 방식
    - 로컬 메머리에 객체가 없어서 객체의 데이터 타입을 직접 알 수 없는 경우(원격에 개체가 있는 경우 등), 객체 정보만을 이용해서 프로그래밍 가능
    - Constructor, Method, Filed 등 java.lang.reflect 패키지에 있는 클래스들을 활용해서 프로그래밍
    - 일반적으로 자료형을 알 수 있는 경우에는 사용하지 않음
        ```
        # String 클래스 정보 가져오기

        package classex;

        import java.lang.reflect.Constructor;
        import java.lang.rreflect.Field;
        import java.lang.reflect.Method;

        public class StringClassTest{
            public static void main(String[] args) throws ClassNotFoundException{
                // forName: 클래스 이름(java.lang.String)으로 Class 클래스 가져오기
                Class strClass = Class.forName("java.lang.String");

                // 모든 생성자 가져오기
                Constructor[] cons = strClass.getConstructors();
                for(Constructor c: cons){
                    System.out.println(c);
                }
                
                // 모든 멤버변수(필드) 가져오기
                System.out.println();
                Field[] fields = strClass.getFields();
                for(Field f: fields){
                    System.out.println(f);
                }
                
                // 모든 메서드 가져오기
                System.out.println();
                Method[] methods = strClass.getMethods();
                for(Method m: methods){
                    System.out.println(m);
                }
            }
        }
        ```  
        + 모든 생성자를 가져온 출력 화면  
        ![image](https://user-images.githubusercontent.com/104348646/196430881-b7bb7391-f92a-468b-9975-1c4a50c4eea4.png)  

* newInstance()를 사용해 클래스 생성하기
    - newInstance() 메서드
        + 항상 Object를 반환 -> 생성된 객체형으로 형변환 필요
    ```
    # Person 클래스의 인스턴스 생성하기: newInstance() 메서드
        
    package classex;

    public class NewInstanceTest{
        public static void main(String[] args) throw ClassNotFoundException, InstantiationException, IllegalAccessException{
            // 생성자로 인스턴스 생성하기
            Person person1 = new Person();
            System.out.println(person1); // @@ classex.Person@16f65612

            // Class 클래스의 newInstance()메서드로 인스턴스 생성하기
            Class pClass = Class.forName("classex.Person");
            Person person2 = (Person)pClass.newInstance();
            System.out.println(person2); // @@ classex.Person@311d617d
        }
    }
    ```
* Class.forName()을 사용해 동적 로딩하기
    - 동적 로딩(dynamic loading)  
        : 컴파일 시에 데이터 타입이 모두 binding 되어 자료형이 로딩되는 것(static loading)이 아니라 실행 중에 데이터 타입을 알고 biniding되는 방식
    - 프로그래밍 할 때는 어떤 클래스를 사용할지 모를 때 변수로 처리하고, 실행될 때 해당 변수에 대입된 값의 클래스가 실행될 수 있도록 Class 클래스에서 제공하는 static 메서드
    - 실행 시에 로딩 -> 경우에 따라 다른 클래스가 사용될 수 있음(유용함)
    - 컴파일 타임에 체크할 수 없음 -> 해당 문자열에 대한 클래스가 없는 경우 예외(ClassNotFoundException)이 발생 가능
    - 로딩되는 클래스를 동적으로 변경하기
    ```
    String className = "classex.Person";
    Class pClass = Class.forName(className);
    ```
