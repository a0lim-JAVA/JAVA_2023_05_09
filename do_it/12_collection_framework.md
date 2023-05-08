## 12-1 제너릭
* 제네릭(Generic) 프로그래밍  
    : 변수의 선언이나 메서드의 매개변수를 하나의 참조 자료형이 아닌 여러 자료형을 변환 될 수 있도록 프로그래밍 하는 방식
    - 실제 사용되는 참조 자료형으로의 변환은 컴파일러가 검증 -> 안정적인 프로그래밍 방식
    - 컬렉션 프레임워크에서 많이 사용되고 있음

* 제네릭의 필요성
    - 3d 프린터 클래스 예제
    ```
    # 재료: 파우더

    public class ThreeDPriner{
        private Powder material;
        
        public void setMaterial(Powder material){
            this.material = material;
        }
        
        public Powder getMaterial(){
            return material;
        }
    }

    # 재료: Plastic

    public class ThreeDPrinter{
        private Plastic material;

        public void setMaterial(Plastic material){
            this.material = material;
        }

        public Plastic getMaterial(){
            return material;
        }
    }
    ```
    ```
    # material 변수의 자료형을 최상위 클래스인 Object로 사용
        
    public class ThreeDPrinter{
        private Object material;
        
        public void setMaterial(Object material){
            this.material = material;    
        }
        
        public Object getMaterial(){
            return material;
        }
    }
    ```
    ```
    # Object에서 파우더를 재료로 사용하는 경우

    ThreeDPrinter printer = new ThreeDPrinter();

    Powder p1 = new Powder();

    // setMaterial(): 자동으로 형 변환 됨 <- 매개변수 자료형: Object
    printer.setMaterial(p1);

    // getMaterial(): 직접 형 변환 필요 <- Powder 자료형 변수를 반환받음
    Power p2 = (Powder)printer.getMaterial;
    ```

* 제네릭 클래스 정의하기
    - T: 자료형 매개변수(type parameter)
        + 여러 참조 자료형으로 대체될 수 있는 부분을 하나의 문자로 표현
        + 클래스를 사용할 때, T 위치에 실제 사용할 자료형을 지정함
    ```
    public class GenericPrinter<T>{ // GenericPrinter<T>: 제네릭 클래스 / T: 자료형 매개변수
        private T material;

        public void setMaterial(T material){
            this.material = material;
        }

        public T getMaterial(){
            return material;
        }
    }
    ```
    - 다이아몬드 연산자 <>
        + JAVA 7부터 제네릭 자료형의 클래스를 생성할 때, 생성자에 사용하는 자료형 명시 생략 가능
            ```
            ArrayList<String> list = new ArrayList<>(); // ArrayList<>: <> 안을 생략 가능
            ```
    - 자료형 매개변수 T와 static
        + static 변수/메서드는 인스턴스를 생성하지 않아도 클래스 이름으로 호출 가능
        + T의 자료형이 정해지는 순간 = 제네릭 클래스의 인스턴스가 생성되는 순간
        + static이 시점이 더 빠름 -> static 변수의 자료형/static 메서드 내부 변수의 자료형으로 T 사용 불가능
    - 자료형 매개변수의 종류
        + E: element
        + K: key
        + V: value
    - 제네릭에서 자료형 추론하기
        + JAVA 10부터 제공
        + list가 지역 변수로 선언되는 경우만 가능
            ```
            # 기존
            ArrayList<String> list = new ArrayList<String>();
            
            # new
            var list = new ArrayList<String>();
            ```
    
* 제네릭 클래스 사용하기
    - T 클래스에 Powder형 대입
        + Power: 대입된 자료형
    - T형 매개변수가 필요한 메서드에 Powder 클래스 생성 + 대입
        + GenericPrinter<Powder>: 제네릭 자료형(Generic type), 매개변수화된 자료형(parameterized type)
    ```
    # 파우더가 재료인 프린터 생성

    GenericPrinter<Powder> powderPrinter = new GenericPrinter<Powder>();
    powderPrinter.setMaterial(new Powder();
    Powder powder = powderPrinter.getMaterial(); // 명시적 형 변환 필요 X <- GenericPrinter<Powder>에서 자료형 명시함
    ```
    -  제네릭 클래스 사용 예제
        ```
        # Powder 클래스 정의하기

        package generics;

        public class Powder{
            public void doPrinting(){
                System.out.println("Powder 재료로 출력합니다");
            }
            public String toString(){
                return "재료는 Powder입니다";
            }
        }

        # Plasic 클래스 정의하기
                
        public class Plastic{
            public void doPrinting(){
                System.out.println("Plastic 재료로 출력합니다");
            }
            public String toString(){
                return "재료는 Plastic입니다";
            }
        }
        ```
        ```
        # GenericPrinter<T> 클래스 정의하기

        package generics;

        public class GenericPrinter<T>{
            private T material; // T 자료형으로 선언한 변수

            public void setMaterial(T material){
                this.material = material;
            }

            // T 자료형 변수 material을 반환하는 제네릭 메서드
            public T getMaterial(){
                return material;
            }

            public String toString(){
                return material.toString();
            }
        }
        ```
        ```
        # GenericPrinter<T> 클래스 사용하기

        package generics;

        public class GenericPrinterTest{
            public static void main(String[] args){
                // Powder형으로 GenericPrinter 클래스 생성
                GenericPrinter<Powder> powderPrinter = new GenericPrinter<Powder>();
                
                powderPrinter.setMaterial(new Powder());
                Powder powder = powderPrinter.getMaterial();
                System.out.println(powderPrinter); // @@ 재료는 Powder입니다

                // Plastic형으로 GenericPrinter 클래스 생성
                GenericPrinter<Plastic> plasticPrinter = new GenericPrinter<Plastic>();

                plasticPrinter.setMaterial(new Printer());
                Plastic plastic = plasticPrinter.getMaterial();
                System.out.println(plasticPrinter); // @@ 재료는 Plastic입니다
            }
        }
        ```
    - 제네릭에서 대입된 자료형을 명시하지 않는 경우
        + GenericPrinter<Powder>와 같이 사용할 자료형 (Powder) 명시 필요
        + 자료형을 명시하지 않고 사용 가능 -> 경고 표시
            + 이번 버전과의 호환을 위함
        + 반환형에 따라 강제 형 변환 필요
        ```
        GenericPrinter  powderPrinter2 = new GenericPrinter (); // <Powder> 명시하지 않음
        powderPrinter2.setMaterial(new Powder());
        Powder powder = (Powder)powderPrinter.getMaterial(); // 강제 형 변환
        System.out.println(powderPrinter);
        ```
        ```
        # 자료형이 지정된 경우

        GenericPrinter<Object> generalPrinter = new GenericPrinter<Object>();
        ```

* T 자료형에 사용할 자료형을 제한하는 <T extends 클래스>
    - T가 사용될 클래스를 제한하기 위해 사용
    - ex)  
        ![image](https://user-images.githubusercontent.com/104348646/196698009-0ae9da40-068a-4573-9327-b64ccd4a95d7.png)  
        + Material에서 상속받지 않은 Water와 같은 클래스는 프린터 재료로 사용 불가 -> 자료형 제한
        + Material에 정의된 메서드 공유 가능
        ```
        # Material 추상 클래스

        package generics;

        public abstract class Material {
            public abstract void doPrinting(); // 상속받은 클래스는 doPrinting() 추상 메서드 구현 필수
        }
        ```
        ```
        # Powder 클래스

        package generics;

        public class Powder extends Material{
            public void doPrinting(){
                System.out.println("Powder 재료로 출력합니다");
            }
            
            public String toString(){
                return "재료는 Powder입니다";
            }
        }

        # Plastic 클래스

        package generics;

        public class Plastic extends Material{
            public void doPrinting(){
                System.out.println("Plastic 재료로 출력합니다");
            }

            public String toString(){
                return "재료는 Plastic입니다";
            }
        }
        ```
        ```
        # GenericPrinter<T extends Material> 클래스

        package generics;

        public class GenericPrinter<T extends Material>{
            private T material;

            ...
        }
        // 상속받지 않은 Water 클래스를 사용하면, error
        ```
    - <T extends 클래스>로 상위 메서드 사용하기
        ```
        # <T extends 클래스>를 사용하지 않은 경우
        
        public class GenericPrinter<T>{
            private T material;
        }

        # <T extends 클래스>를 사용하는 경우
        
        public class GenericPrinter<T extends Material>{
            private T material;
        } // 메서드에 doPrinting()이 추가됨
        ```
        ```
        # <T extends 클래스> 사용하기

        package generics;

        public class GenericPrinter<T extends Material>{
            private T material;
            
            public void setMaterial(T material){
                this.material = material;
            }
            public T getMaterial(){
                return material;
            }
            
            public String toString(){
                return material.toString();
            }
            
            public void printing(){
                material.doPrinting(); // 상위 클래스 Material의 메서드 호출
            }
        }
        ```
        ```
        # <T extends 클래스> 테스트하기: T형 material변수에서 doPrinting() 메서드 호출 가능

        package generics;

        public class GenericPrinterTest2{
            public static void main(String[] args){
                GenericPrinter<Powder> powderPrinter = new GenericPrinter<Powder>();
                powerPrinter.setMaterial(new Powder());
                powderPrinter.printing(); // @@ Powder 재료로 출력합니다

                GenericPrinter<Plastic> plasticPrinter = new GenericPrinter<Plastic>();
                powerPrinter.setMaterial(new Plastic());
                plasticPrinter.printing(); // @@ Plastic 재료로 출력합니다
            }
        }
        ```

* 제네릭 메서드 활용하기
    - 메서드의 매개변수를 자료형 매개변수로 사용하는 경우
    - 자료형 매개 변수가 하나 이상인 경우
    - 제네릭 메서드의 일반 형식
        ```
        public<자료형 매개변수> 반환형 메서드 이름(자료형 매개변수 ...){}
        ```
    - ex) 자료형 매개변수가 두 개 인 경우: Point 클래스
        + 좌표 x, y: 정수, 실수 모두 가능 -> T, V 자료형 매개변수로 변환
        + getX(), getY(): T, V를 반환 => 제네릭 메서드
        ```
        # 자료형 매개변수를 두 개 사용하는 클래스

        package generics;

        public class Point<T, V>{
            T x;
            V y;

            Point(T x, V y){
                this.x = x;
                this.y = y;
            }

            public T getX(){ // 제네릭 메서드
                return x;
            }

            public V getY(){ // 제네릭 메서드
                return y;
            }
        }
        ```
        ```
        # 두 점을 생성
        // <> 다이아몬드 연산자만 사용(자료형 명시하지 않음)
        // x: Integer / y: Double
        Point<Integer, Double> p1 = new Point<>(0, 0.0);
        Point<Integer, Double> p2 = new Point<>(10, 10.0);
        ```
        ```
        # 제네릭 메서드 구현하기: 사각형의 넓이를 계산하는 makeRectangle() 메서드 생성

        package generics;

        public class GenericMethod{
            public static <T, V> double makeRectangle(Point<T, V> p1, Point<T, V> p2){ // 제너릭 메서드
                double left = ((Number)p1.getX()).doubleValue();
                double right = ((Number)p2.getX()).doubleValue();
                double bottom = ((Number)p1.getY()).doubleValue();
                double top = ((Number)p2.getY()).doubleValue();

                double width = right - left;
                double height = top - bottom;

                return width * height;
            }

            public static void main(String[] args){
                Point<Integer, Double> p1 = new Point<Integer, Double>(0, 0.0);
                Point<Integer, Double> p2 = new Point<>(10, 10.0);
                // Point<Integer, Double> p1 = new Point(0, 0.0); 로 자료형 생략 가능
            
                double rect = GenericMethod.<Integer, Double>makeRectangle(p1, p2);
                // double rect = GenericMethod.makeRecctangle(p1, p2); 로 자료형 생략 가능
                System.out.println("두 점으로 만들어진 사각형의 넓이는 " + rect + "입니다"); // @@ 두 점으로 만들어진 사각형의 넓이는 100.0입니다
            }
        }
        ```  
        ![image](https://user-images.githubusercontent.com/104348646/196964138-b746dcf7-0025-4c10-b81b-9567918752c4.png)  
    - 
* 컬렉션 프레임워크에서 사용하는 제네릭
    - ArrayList.java에서 ArrayList 클래스의 정의
        ```
        public class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable{
            ...
        }
        ```
        ```
        # 배열 생성
        ArrayList<String> list = new ArrayList<String>();
        ```
        ```
        # ArrayList에서 가장 많이 사용하는 get() 메서드
        public E get(int index);
        return elementData(index); // 배열 생성에 따라, E의 자료형: String
        ```

## 12-2 컬렉션 프레임워크
* 컬렉션 프레임워크(collection framework)  
    : 프로그램 구현에 필요한 자료구조(Data Structure)를 구현해 놓은 라이브러리  
        + 자료구조(data structure): 프로그램 실행 중 메모리에 자료를 유지/관리에 사용  
    - java.util 패키지에 구현되어 있음
    - 개발에 소요되는 시간을 절약하면서 최적화된 알고리즘을 사용할 수 있음
    - 여러 인터페이스와 구현 클래스 사용 방법을 이해해야 함  
    ![image](https://user-images.githubusercontent.com/104348646/196698087-318c2459-add7-4495-a75c-0545256b2e86.png)  

* Collection 인터페이스  
    : 하나의 자료를 모아서 관리하기 위한 메서드가 정의된 인터페이스
    - 하위에 List와 Set 인터페이스가 있음
    - 여러 클래스들이 Collection 인터페이스를 구현함  
        ![image](https://user-images.githubusercontent.com/104348646/196698143-4385e83d-1294-4512-b4aa-0d03cf4f5e72.png)  
    - 주요 메서드  
        ![image](https://user-images.githubusercontent.com/104348646/196698583-af5ac42d-b8c4-4122-8a0a-c1e8a3e0e0cd.png)  
        + add(), remove() 메서드: boolean형으로 결과값을 반환함 <- 객체가 추가/제거 되었는지 여부를 반환

* Map 인터페이스
    : 쌍(pair)로 된 자료들을 모아서 관리하기 위한 메서드가 선언된 인터페이스
    - key-value의 쌍으로 이루어짐
    - key는 중복될 수 없음
    - Hashtable, HashMap, TreeMap, Properties 클래스 등이 인터페이스를 구현함
    - 주요 메서드  
          ![image](https://user-images.githubusercontent.com/104348646/196700025-b30072c7-e553-4b70-91c3-a741fa868ec0.png)  

* 실습 패키지 구조
    ```
    # Member 클래스(회원) 구현하기
    package collection;

    public class Member {
        // 속성
        private int memberId;
        private String memberName;

        public Member(int memberId, String memberName){
            this.memberId = memberId;
            this.memberName = memberName;
        }

        public int getMemberId(){
            return memberId;
        }

        public void setMemberId(int memberId){
            this.memberId = memberId;
        }

        public String getMemberName(){
            return memberName;
        }

        public void setMemberName(String memberName){
            this.memberName = memberName;
        }
        
        // toString()메서드 재정의
        @Override
        public String toString(){
            return memberName + " 회원님의 아이디는 " + memberId + "입니다";
        }
    }
    ```

## 12-3 List 인터페이스
* List 인터페이스
    - Collection 하위 인터페이스
    - 객체를 순서에 따라 저장하고 관리하는데 필요한 메서드가 선언된 인터페이스
    - 배열의 기능을 구현하기 위한 인터페이스
    - ArrayList, Vector, LinkedList 등이 사용됨

* ArrayList 클래스
    - ArrayList를 활용해 회원 관리 프로그램 구현하기
        ```
        package collection.arraylist;

        import java.util.ArrayList;
        import collection.Member; // Member 클래스는 collection 패키지에 있음 -> import 필요

        public class MemberArrayList {
            // ArrayList 선언
            private ArrayList<Member> arrayList;

            // ArrayList 생성(Member 형)
            public MemberArrayList(){
                arrayList = new ArrayList<Member>();
            }

            // addMember() 메서드: 회원 추가 - 매개변수로 전달된 회원을 ArrayList 맨 뒤에 추가
            public void addMember(Member member){
                arrayList.add(member);
            }

            // removerMember() 메서드: 회원 삭제 - 매개변수로 전달받은 memberID 회원을 ArrayList에서 찾아 제거
            public boolean removeMember(int memberId){ // boolean: T/F
                for(int i = 0; i < arrayList.size(); i++){
                    Member member = arrayList.get(i); // get(i) 메서드: (i+1)번째 회원을 순차적으로 가져옴
                    int tempId = member.getMemberId();
                    if(tempId == memberId){
                        arrayList.remove(i);
                        return true;
                    }
                }
                // 반복문이 끝날 때까지 해당 아이디를 찾지 못한 경우 출력
                System.out.println(memberId + "가 존재하지 않습니다");
                return false;
            }

            // showAllMember() 메서드: 전체 회원 출력
            public void showAllMember(){
                for(Member member : arrayList){
                    System.out.println(member); // Member 클래스에 재정의한 toString()이 호출됨
                }
                System.out.println();
            }
        }
        ```
    - MemberArrayList 테스트 클래스 구현하기
        ```
        package collection.arraylist;

        import collection.Member;

        public class MemberArrayListTest {
            public static void main(String[] args){
                MemberArrayList memberArrayList = new MemberArrayList();

                // 새로운 회원 인스턴스 생성
                Member memberLee = new Member(1001, "이지원");
                Member memberSon = new Member(1002, "손민국");
                Member memberPark = new Member(1003, "박서훤");
                Member memberHong = new Member(1004, "홍길동");

                // ArrayList에 회원 추가
                memberArrayList.addMember(memberLee);
                memberArrayList.addMember(memberSon);
                memberArrayList.addMember(memberPark);
                memberArrayList.addMember(memberHong);

                memberArrayList.showAllMember(); // 전체 회원 출력

                memberArrayList.removeMember(memberHong.getMemberId()); // 홍길동 회원 삭제
                memberArrayList.showAllMember(); // 전체 회원 재출력
            }
        }
        // @@ 이지원 회원님의 아이디는 1001입니다
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 박서훤 회원님의 아이디는 1003입니다
        // @@ 홍길동 회원님의 아이디는 1004입니다
         
        // @@ 이지원 회원님의 아이디는 1001입니다
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 박서훤 회원님의 아이디는 1003입니다
        ```
    - 배열 용량(capacity)
        + 디폴트 용량: 10
        + ArrayList(int) 생성자를 사용하면, 초기에 생성할 배열의 용량 지정 가능
        + 배열 용량 초과 시, add(), insert() 등의 요소가 추가되는 메서드는 큰 용량의 배열을 새로 만들고 기존 항목 복사함

* ArrayList와 Vector 클래스
    - 객체 배열을 구현한 클래스
    - ArrayList
        + 일반적으로 더 많이 사용
    - Vector
        + 멀티 스레드 상태에서 리소스에 대한 동기화가 필요한 경우
        + JAVA 2부터 제공
        + 호출 시, 배열 객체에 잠금을 하고, 메서드 수행이 끝나면 잠금을 해제 -> 속도가 느림
    - 스레드(thread)  
        : 작업 단위
        + 단일 스레드(single thread): 하나의 스레드만 수행
        + 멀티 스레드(multi-thread): 두 개 이상의 스레드가 동시에 실행되는 경우
    - 동기화(synchronization)  
        : 두 개의 스레드가 동시에 하나의 리소스에 접근할 때, 순서를 맞춰서 데이터 오류 발생 방지
        + 동기화 기능이 추가 되어야 하는 경우
            ```
            Collections.synchronizedList(new ArrayList<String>());
            // Vector로 바꾸지 않고 ArrayList 생성 코드 사용
            ```
* LinkedList 클래스
    - 논리적으로 순차적인 자료구조가 구현된 클래스
    - 링크드 리스트 구조
        + 다음 요소에 대한 참조값을 가지고 있음
        + 마지막 요소의 다음 요소 주소 = null  
        ![image](https://user-images.githubusercontent.com/104348646/196698696-5733c779-f75a-4d17-8557-1bd6b018ec38.png)  
    - 링크드 리스트에 요소 추가하기  
        ![image](https://user-images.githubusercontent.com/104348646/196698724-80d40a52-953f-49c6-b9fc-bfc409c38834.png)  
    - 링크드 리스트의 요소 제거하기  
        ![image](https://user-images.githubusercontent.com/104348646/196698750-a700fd78-407d-4e21-8b7e-f1cc0eb1b135.png)  
    - 요소의 추가/삭제에 드는 비용: 배열 > LinkedList
        + 배열: 생성 시, 용량 지정 + 요소가 초과된 경우에 용량을 늘리며 수행
            + 자료 변동이 거의 없는 경우 효율적
        + 링크드 리스트: 요소 추가마다 동적으로 요소의 메모리 생성
            + 자료의 변동(삽입/삭제)가 많은 경우 효율적
    - LinkedList 클래스 사용하기
        ```
        package collection.linkedlist;

        import java.util.LinkedList;

        public class LinkedListTest {
            public static void main(String[] args){
                LinkedList<String> myList = new LinkedList<String>();

                // 링크드 리스트에 요소 추가
                myList.add("A");
                myList.add("B");
                myList.add("C");

                System.out.println(mylist); // 리스트 전체 출력 @@ [A, B, C]

                // 링크드 리스트의 첫 번째 위치에 D 추가
                myList.add(1, "D");
                System.out.println(myList); // @@ [A, D, B, C]

                // 연결 리스트의 맨 앞에 0 추가
                myList.addFirst("0");
                System.out.println(myList); // @@ [0, A, D, B, C]
                
                // 연결 리스트의 맨 뒤 요소 삭제 후 해당 요소를 출력
                System.out.println(myList.removeLast()); // @@ C
                System.out.println(myList); // @@ [0, A, D, B]
            }
        }
        ```

* ArrayList로 스택과 큐 구현하기
    - Stack, Queue의 기능은 구현된 클래스가 있음
    - ArrayList/LinkedList를 활용해 사용 가능
    - 스택(Stack)  
        + Last in First Out(LIFO)
        + 맨 마지막에 추가된 요소가 먼저 꺼내지는 자료구조
        + 게임의 무르기 기능, 최근 자료 추출 등에서 사용  
            ![image](https://user-images.githubusercontent.com/104348646/196698784-66c2a01c-6812-42aa-940d-2f9b561ce5ae.png)  
            ```
            # 스택 구현하기
            
            package collection.arraylist;

            import java.util.ArrayList;

            public class MyStack {
                private ArrayList<String> arrayStack = new ArrayList<String>();

                // 스택의 맨 뒤에 요소를 추가
                public void push(String data){
                    arrayStack.add(data);
                }

                // 스택의 맨 뒤에서 요소 꺼냄
                public String pop(){
                    int len = arrayStack.size(); // arrayStack.size(): ArrayList에 저장된 유효한 자료의 개수
                    if(len == 0){
                        System.out.println("스택이 비었습니다");
                        return null;
                    }

                    return(arrayStack.remove(len - 1)); // 맨 뒤에 있는 자료 반환하고 배열에서 제거
                }
            }

            # test
            package collection.arraylist;

            public class StackTest {
                public static void main(String[] args) {
                    MyStack stack = new MyStack();
                    stack.push("A");
                    stack.push("B");
                    stack.push("C");

                    System.out.println(stack.pop()); // @@ C
                    System.out.println(stack.pop()); // @@ B
                    System.out.println(stack.pop()); // @@ A
                }
            }
            ```
            + push  
                ![image](https://user-images.githubusercontent.com/104348646/196964364-84dfd81b-de36-4f1e-b81c-141be618601a.png)  
            + pop  
                ![image](https://user-images.githubusercontent.com/104348646/196964413-dd26ba45-85d4-4885-a04c-156101f830c6.png)  
    - 큐(Queue)
        + First In First Out(FIFO)
        + 맨 처음에 추가된 요소가 먼저 꺼내지는 자료구조  
            ![image](https://user-images.githubusercontent.com/104348646/196964501-e124aa0e-c576-494e-a558-dc606ee1a8d2.png)  
        + 선착순 등
            ```
            # 큐 구현하기

            package collection.arraylist;

            import java.util.ArrayList;

            public class MyQueue {
                private ArrayList<String> arrayQueue = new ArrayList<String>();

                // 큐의 맨 뒤에 추갸
                public void enQueue(String data){
                    arrayQueue.add(data);
                }

                // 큐의 맨 앞에서 꺼냄
                public String deQueue(){
                    int len = arrayQueue.size();
                    if(len == 0){
                        System.out.println("큐가 비었습니다");
                        return null;
                    }

                    return(arrayQueue.remove(0)); // 맨 앞의 자료 반환하고 배열에서 제거
                }
            }

            # Test

            public class QueueTest {
                public static void main(String[] args){

                    MyQueue queue = new MyQueue();
                    queue.enQueue("A");
                    queue.enQueue("B");
                    queue.enQueue("C");

                    System.out.println(queue.deQueue()); // @@ A
                    System.out.println(queue.deQueue()); // @@ B
                    System.out.println(queue.deQueue()); // @@ C
                }
            }
            ```
            + enQueue  
                ![image](https://user-images.githubusercontent.com/104348646/196964554-28068aba-6ef6-45d3-a3df-af1d9107bc70.png)  
            + deQueue  
                ![image](https://user-images.githubusercontent.com/104348646/196964597-8ecd5f56-7327-47df-92be-be8f9a3527fd.png)  

* Collection 요소를 순회하는 Iterator
    - Iterator  
        : Collection 인터페이스를 구현한 객체에서 미리 정의되어 있는 iterator() 메서드를 호출하여 참조
        + ex) Collection을 구현한 ArrayList에 iterator() 메서드 호출 -> Iterator 클래스 반환
            ```
            Iterator ir = memberArrayList.iterator(); // iterator 형 변수에 대입해 사용
            ```
    - Iterator를 사용하여 요소를 순회할 때 사용하는 메서드  
        ![image](https://user-images.githubusercontent.com/104348646/196958257-d4e11727-835c-4d33-b69e-c73aee61b0fe.png)  
        + 순서가 없는 클래스도 Iterator로 요소 순회 가능
            ```
            # MemberArrayList 클래스의 removeMember() 메서드에 적용
                
            public boolean removeMember(int memberId){ // boolean: T/F
                Iterator<Member> ir = arrayList.iterator(); // Iterator 반환 / Iterator<Member> 같은 제네릭 자료형으로 지정
                while(ir.hasNext()){ // 순회하면서 요소가 있는 동안
                    Member member = ir.next(); // next(): 다음 회원을 반환받음
                    int tempId = member.getMemberId();
                    if(tempId == memberId){ // 회원 아이디가 매개변수와 일치하면
                        arrayList.remove(member);
                        return true;
                    }
                }
                // 반복문이 끝날 때까지 해당 아이디를 찾지 못한 경우 출력
                System.out.println(memberId + "가 존재하지 않습니다");
                return false;
            }
            ```

## 12-4 Set 인터페이스
* Set 인터페이스
    - Collection 하위의 인터페이스
    - 중복 허용 X
    - 아이디, 주민번호, 사번 등 유일한 값이나 객체를 관리할 때 사용
    - 순서가 없음
        + cf) List: 순서기반의 인터페이스
    - get(i) 메서드 제공 X

* HashSet 클래스
    - 집합 자료 구조를 구현
    - 중복 허용 X
        ```
        # HashSet 테스트하기

        package collection.hashset;

        import java.util.HashSet;

        public class HashSetTest {
            public static void main(String[] args){
                HashSet<String> hashSet = new HashSet<String>();
                hashSet.add(new String("임정순"));
                hashSet.add(new String("박현정"));
                hashSet.add(new String("홍연의"));
                hashSet.add(new String("강감찬"));
                hashSet.add(new String("강감찬")); // 중복
                
                System.out.println(hashSet); // @@ [홍연의, 박현정, 강감찬, 임정순]
            }
        }
        ```
        + Hash에 중복된 값은 추가되지 않음
        + 자료가 추가된 순서와 상관없이 출력됨
    - HashSet을 활용해 회원 관리 프로그램 구현하기
        ```
        # HashSet 활용하기

        package collection.hashset;

        import java.util.HashSet;
        import java.util.Iterator;

        import collection.Member;

        public class MemberHashSet {
            private HashSet<Member> hashSet; // HashSet 선언

            public MemberHashSet(){
                hashSet = new HashSet<Member>(); // HashSet 생성
            }

            public void addMember(Member member){ // HashSet에 회원 추가
                hashSet.add(member);
            }

            // 매개변수로 받은 회원 아이디에 해당하는 회원 삭제
            public boolean removeMember(int memberId){
                Iterator<Member> ir = hashSet.iterator(); // Iterator를 활용해 순회함

                while(ir.hasNext()){
                    Member member = ir.next(); // 회원을 하나씩 가져와서 cf) ArrayList: i번째 항목을 가져옴
                    int tempId = member.getMemberId(); // 아이디 비교
                    if(tempId == memberId){
                        hashSet.remove(member);
                        return true;
                    }
                }
                System.out.println(memberId + "가 존재하지 않습니다");
                return false;
            }
            
            // 모든 회원 출력
            public void showAllMember(){
                for(Member member : hashSet){
                    System.out.println(member);
                }
                System.out.println();
            }
        }
        ```
        + boolean remove(Object o) 메서드
            : 매개변수로 받은 객체를 삭제하고 삭제 여부를 true, false로 반환
        ```
        # HashSet Test

        package collection.hashset;

        import collection.Member;

        public class MemberHashSetTest {
            public static void main(String[] args){
                MemberHashSet memberHashSet = new MemberHashSet();

                Member memberLee = new Member(1001, "이지원");
                Member memberSon = new Member(1002, "손민국");
                Member memberPark = new Member(1003, "박서훤");

                memberHashSet.addMember(memberLee);
                memberHashSet.addMember(memberSon);
                memberHashSet.addMember(memberPark);
                memberHashSet.showAllMember();

                Member memberHong = new Member(1003, "홍길동"); // 아이디 중복 회원
                memberHashSet.addMember(memberHong);
                memberHashSet.showAllMember();
            }
        }
        // @@ 박서훤 회원님의 아이디는 1003입니다
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 이지원 회원님의 아이디는 1001입니다

        // @@ 박서훤 회원님의 아이디는 1003입니다
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 이지원 회원님의 아이디는 1001입니다
        // @@ 홍길동 회원님의 아이디는 1003입니다
        ```
        + 아이디 중복 회원이 출력됨 <- String 클래스에 객체가 동일한 경우에 대한 처리 방법이 이미 구현되어 있음
    - 객체가 동일함을 구현하기
        ```
        # equals(), hashCode() 메서드 재정의: Object 클래스에서 논리적으로 같은 객체 구현(회원 아이디가 같으면 같은 회원임)

        package collection;

        public class Member {
            // 속성
            private int memberId;
            private String memberName;
            ...
            @Override
            public boolean equals(Object obj){
                if(obj instanceof Member){
                    Member member = (Member)obj;
                    if(this.memberId == member.memberId)
                        return true;
                    else
                        return false;
                }
                return false;
            }
        }

        # Test
        // @@ 박서훤 회원님의 아이디는 1003입니다
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 이지원 회원님의 아이디는 1001입니다

        // @@ 박서훤 회원님의 아이디는 1003입니다
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 이지원 회원님의 아이디는 1001입니다 // 아이디 중복 회원은 추가되지 않음
        ```

* TreeSet 클래스  
    : 자료의 중복을 허용하지 않으면서 오름차순/내림차순으로 출력 결과 값을 정렬
    - 객체의 정렬에 사용됨
    - 내부적으로 이진 검색 트리(binary search tree)로 구현되어 있음
    - 이진 검색 트리에 자료가 저장될 때, 비교해서 저장될 위치를 정함
    - 객체 비교를 위해 Comparable/Comparator 인터페이스 구현 필요
        ```
        # Treeset 테스트하기

        package collection.treeset;

        import java.util.TreeSet;

        public class TreeSetTest {
            public static void main(String[] args){
                TreeSet<String> treeSet = new TreeSet<String>();
                treeSet.add("홍길동");
                treeSet.add("강감찬");
                treeSet.add("이순신");
                
                for(String str: treeSet){
                    System.out.println(str);
                    // @@ 강감찬
                    // @@ 이순신
                    // @@ 홍길동 <- 이름순으로 정렬되어 출력됨
                }
            }
        }
        ```
    - 이진 검색 트리(BST: Binary Search Tree)
        + 노드(node)
            : 트리 자료 구조에서 각 자료가 들어가는 공간
        + 부모-자식 노드(parent-child node)
            : 위 아래로 연결된 노드의 관계
        + 자료 중복 허용 X
        + 부모가 가지는 자식 노드의 수: 2개 이하
        + 특정 값을 찾을 때, 노드와 비교해서 작으면 왼쪽, 크면 오른쪽 자식 노드 방향으로 이동
        + 비교 범위가 평균 1/2 줄어듬 -> 효과적  
        ![image](https://user-images.githubusercontent.com/104348646/196964725-75712aea-bce6-4804-948b-a5d876a8c7fd.png)  
        + ex) 23, 10, 48, 15, 7, 22, 56  
            ![image](https://user-images.githubusercontent.com/104348646/196964802-9b8f9722-a918-4044-8fdb-fc9e8190be72.png)  
    - TreeSet을 활용해 회원 관리 프로그램 구현하기
        ```
        package collection.treeset;

        import java.util.Iterator;
        import java.util.TreeSet;

        import collection.Member;

        public class MemberTreeSet {
            private TreeSet<Member> treeSet;
            
            public MemberTreeSet(){
                treeSet = new TreeSet<Member>();
            }
            // TreeSet에 회원을 추가하는 메서드
            public void addMember(Member member){
                treeSet.add(member);
            }
            
            // TreeSet에서 회원을 삭제하는 메서드
            public boolean removeMember(int memberId){
                Iterator<Member> ir = treeSet.iterator();

                while(ir.hasNext()){
                    Member member = ir.next();
                    int tempId = member.getMemberId();
                    if(tempId == memberId){
                        treeSet.remove(member);
                        return true;
                    }
                }
                System.out.println(memberId + "가 존재하지 않습니다");
                return false;
            }
            
            // 전체 회원을 출력하는 메서드
            public void showAllMember(){
                for(Member member : treeSet){
                    System.out.println(member);
                }
                System.out.println();
            }
        }
        ```
        ```
        # Test

        package collection.treeset;

        import collection.Member;

        public class MemberTreeSetTest {
            public static void main(String[] args){
                MemberTreeSet memberTreeSet = new MemberTreeSet();
                
                Member memberPark = new Member(1003, "박서훤");
                Member memberLee = new Member(1001, "이지원");
                Member memberSon = new Member(1002, "손민국");

                memberTreeSet.addMember(memberLee);
                memberTreeSet.addMember(memberSon);
                memberTreeSet.addMember(memberPark);
                memberTreeSet.showAllMember();
                
                Member memberHong = new Member(1003, "홍길동"); // 아이디 중복 회원 추가
                memberTreeSet.addMember(memberHong);
                memberTreeSet.showAllMember();
            }
        }
        // error:collection.Member cannot be cast to java.base/java.lang.Comparable
        // Comparable 인터페이스를 구현하지 않음
        ```

* Comparable 인터페이스와 Comparator 인터페이스
    - 정렬 대상이 되는 클래스가 구현해야 하는 인터페이스
    - 자기 자신(this)와 전달받은 매개변수를 비교하는Comparble 인터페이스
        + 일반적으로 더 많이 사용함
        + compareTo() 메서드 구현
        ```
        # 정렬 기준 값이 있는 Member 클래스에 구현
        public class Member implements Comparable<member>{
            ...
        }
        ```
        ```
        # Comparable 인터페이스 구현하기

        package collection;

        public class Member {
            // 속성
            private int memberId;
            private String memberName;

            public Member(int memberId, String memberName){
                this.memberId = memberId;
                this.memberName = memberName;
            }

            ...
            // compareTo() 메서드 재정의: 추가한 회원 아이디와 매개변수로 받은 회원 아이디를 비교함
            @Override
            public int compareTo(Member member){
                return (this.memberId - member.memberId); // 양수: 새 회원 아이디가 더 큼 -> 오른쪽 노드로 이동 / 음수
            }
        }
        // @@ 이지원 회원님의 아이디는 1001입니다 / 오름차순으로 정렬됨
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 박서훤 회원님의 아이디는 1003입니다

        // @@ 이지원 회원님의 아이디는 1001입니다
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 박서훤 회원님의 아이디는 1003입니다
        ```
        ```
        # 내림차순 정렬로 변경

        @Override
        public int compareTo(Member member){
        return (this.memberId - member.memberId) * (-1); // * (-1): 내림차순으로 정렬하기 위해 반환 값을 음수로 만듦
        }
        // @@ 박서훤 회원님의 아이디는 1003입니다
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 이지원 회원님의 아이디는 1001입니다
         
        // @@ 박서훤 회원님의 아이디는 1003입니다
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 이지원 회원님의 아이디는 1001입니다
        ```
    - 두 매개변수를 비교하는 Comparator 인터페이스
        + compare() 메서드를 구현
        + 두 개의 매개 변수를 비교
        + 이미 Comparble이 구현된 경우, Comparator을 이용해 다른 정렬 방식 정의 가능
            ```
            # Comparator 인터페이스 구현하기

            package collection;

            import java.util.Comparator;

            public class Member2 implements Comparator<Member2>{
                private int memberId;
                private String memberName;

                public Member2(int memberId, String memberName){
                    this.memberId = memberId;
                    this.memberName = memberName;
                }

                public int getMemberId(){
                    return memberId;
                }

                public void setMemberId(int memberId){
                    this.memberId = memberId;
                }

                public String getMemberName(){
                    return memberName;
                }

                public void setMemberName(String memberName){
                    this.memberName = memberName;
                }

                // toString() 메서드 재정의
                @Override
                public String toString(){
                    return memberName + " 회원님의 아이디는 " + memberId + "입니다";
                }


                // compare() 메서드 재정의: 전달받은 두 매개변수를 비교함
                @Override
                public int compare(Member2 mem1, Member2 mem2){
                    return mem1.getMemberId() - mem2.getMemberId();
                }
            }
            ```
        + TreeSet 생성자에 Comparator를 구현한 객체를 매개변수로 전달
            ```
            # 아래 코드 구현 필요
            TreeSet<Member> treeSet = new TreeSet<Member>(new Member());
            ```
            ```
            # Comparator 인터페이스 사용하기

            package collection.treeset;

            import java.util.Comparator;
            import java.util.Set;
            import java.util.TreeSet;

            class MyCompare implements Comparator<String>{
                @Override
                public int compare(String s1, String s2){
                    return (s1.compareTo(s2)) * -1;
                }
            }

            public class ComparatorTest {
                public static void main(String[] args){
                    // TreeSet 생성자의 매개변수로 정렬 방식을 지정: 내림차순
                    Set<String> set = new TreeSet<String>(new MyCompare());
                    set.add("aaa");
                    set.add("ccc");
                    set.add("bbb");

                    System.out.println(set);
                }
            }
            // @@ [ccc, bbb, aaa]
            ```

## 12-5 Map 인터페이스
* Map 인터페이스
    - key - value pair의 객체를 관리하는데 필요한 메서드가 정의됨
    - key는 중복될 수 없음
    - key를 이용해 값을 저장/검색/삭제에 사용
    - 내부적으로 hash 방식으로 구현됨
        ```
        index = hash(key) // index: 저장 위치
        ```
    - key가 되는 객체는 객체의 유일성함의 여부를 알기 위해, equals()와 hashCode() 메서드를 재정의함

* HashMap 클래스
    - Map 인터페이스를 구현한 클래스 중 가장 일반적으로 사용하는 클래스
    - HashTable 클래스
        + JAVA 2부터 제공
        + Vector처럼 동기화를 제공
    - 여러 메서드를 활용하여 Pair 자료를 쉽고 빠르게 관리 가능
    - HashMap을 활용해 회원 관리 프로그램 구현하기
        ```
        # HashMap 활용하기

        package map.hashmap;

        import java.util.HashMap;
        import java.util.Iterator;

        import collection.Member;

        public class MemberHashMap {
            private HashMap<Integer, Member> hashMap;

            public MemberHashMap(){
                hashMap = new HashMap<Integer, Member>();
            }

            // HashMap에 회원을 추가하는 메서드
            public void addMember(Member member){
                hashMap.put(member.getMemberId(), member); // key-value 쌍으로 추가
            }

            // HashMap에서 회원을 삭제하는 메서드
            public boolean removeMember(int memberId){
                if(hashMap.containsKey(memberId)){ // HashMap에 매개변수로 받은 키 값인 회원 아이디가 있다면
                    hashMap.remove(memberId); // 해당 회원 삭제
                    return true;
                }
                System.out.println(memberId + "가 존재하지 않습니다");
                return false;
            }

            // Iterator를 사용해 전체 회원을 출력하는 메서드
            public void showAllMember(){
                Iterator<Integer> ir = hashMap.keySet().iterator();
                while (ir.hasNext()){ // 다음 key가 있으면
                    int key = ir.next(); // key 값을 가져와서
                    Member member = hashMap.get(key); // key로부터 value 가져오기
                    System.out.println(member);
                }
                System.out.println();
            }
        }
        ```
        ```
        # HashMap 활용하기

        package map.hashmap;

        import collection.Member;

        public class MemberHashMapTest {
            public static void main(String[] args){
                MemberHashMap memberHashMap = new MemberHashMap();

                Member memberLee = new Member(1001, "이지원");
                Member memberSon = new Member(1002, "손민국");
                Member memberPark = new Member(1003, "박서훤");
                Member memberHong = new Member(1004, "홍길동");

                memberHashMap.addMember(memberLee);
                memberHashMap.addMember(memberSon);
                memberHashMap.addMember(memberPark);
                memberHashMap.addMember(memberHong);

                memberHashMap.showAllMember();

                memberHashMap.removeMember(1004); // 회원 아이디(key 값)이 1004인 회원 삭제
                memberHashMap.showAllMember();
            }
        }

        // @@ 이지원 회원님의 아이디는 1001입니다
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 박서훤 회원님의 아이디는 1003입니다
        // @@ 홍길동 회원님의 아이디는 1004입니다
         
        // @@ 이지원 회원님의 아이디는 1001입니다
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 박서훤 회원님의 아이디는 1003입니다 / 홍길동 회원 삭제됨
        ```

* TreeMap 클래스
    - key 객체를 정렬해 key-value를 pair로 관리하는 클래스
    - key에 사용되는 클래스에 Comparable, Comparator 인터페이스를 구현
    - JAVA에 많은 클래스들은 이미 Comparable이 구현되어 있음
    - 구현된 클래스를 key로 사용하는 경우, 구현 불필요
        ```
        public final class Integer extends Number implements Comparable<integer>{
            ...
            public int compareTo(Integer anotherInteger){
                return compare(this.vlaue, anotherInteger.value);
            }
        }
        ```
        ```
        # TreeMap 활용하기
                
        package map.treemap;

        import java.util.Iterator;
        import java.util.TreeMap;

        import collection.Member;

        public class MemberTreeMap {
            private TreeMap<Integer, Member> treeMap;

            public MemberTreeMap(){
                treeMap = new TreeMap<Integer, Member>();
            }

            public void addMember(Member member){
                treeMap.put(member.getMemberId(), member); // key-value 쌍으로 추가
            }

            public boolean removeMember(int memberId){
                if(treeMap.containsKey(memberId)){
                    treeMap.remove(memberId); // key 값에 맞는 자료 삭제
                    return true;
                }
                System.out.println(memberId + "가 존재하지 않습니다");
                return false;
            }

            public void showAllMember(){
                Iterator<Integer> ir = treeMap.keySet().iterator();
                while (ir.hasNext()){
                    int key = ir.next();
                    Member member = treeMap.get(key);
                    System.out.println(member);
                }
                System.out.println();
            }
        }
        ```
        ```
        # TreeMap 활용하기

        package map.treemap;

        import collection.Member;

        public class MemberTreeMapTest{
            public static void main(String[] args){
                MemberTreeMap memberHashMap = new MemberTreeMap();

                Member memberPark = new Member(1003, "박서훤");
                Member memberLee = new Member(1001, "이지원");
                Member memberHong = new Member(1004, "홍길동");
                Member memberSon = new Member(1002, "손민국");

                // 회원 아이디 순서와 상관없이 회원 추가
                memberHashMap.addMember(memberPark);
                memberHashMap.addMember(memberLee);
                memberHashMap.addMember(memberHong);
                memberHashMap.addMember(memberSon);

                memberHashMap.showAllMember();

                memberHashMap.removeMember(1004);
                memberHashMap.showAllMember();
            }
        }
        // @@ 이지원 회원님의 아이디는 1001입니다 / 정렬됨
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 박서훤 회원님의 아이디는 1003입니다
        // @@ 홍길동 회원님의 아이디는 1004입니다
        
        // @@ 이지원 회원님의 아이디는 1001입니다
        // @@ 손민국 회원님의 아이디는 1002입니다
        // @@ 박서훤 회원님의 아이디는 1003입니다 / 삭제됨
        ```

