## 07-1 배열이란
* 자료를 순차적으로 관리하는 구조, 배열
    - 배열(array)  
        : 자료가 연속으로 나열된 자료 구조

* 배열 선언과 초기화
    - 배열 선언  
        ![image](https://user-images.githubusercontent.com/104348646/195532755-297b78d3-a021-4652-93b1-0e7e12c10dec.png)  
        + 배열 요소  
            : 배열을 이루는 각각의 자료
            + 배열 요소의 자료형은 모두 같음
        + 선언한 자료형과 배열 길이에 따라 메모리가 할당됨
        ```
        int[] studentIDs = new int[10]; // int형 요소가 10개인 배열 선언
        ```  
        ![image](https://user-images.githubusercontent.com/104348646/195533488-859d439d-e58f-4324-8e9d-2d7abb1d6b0a.png)    
    - 배열 초기화하기
        + 배열 선언과 동시에 각 요소의 값이 초기화 됨
            + 정수: 0
            + 실수: 0.0
            + 객체: null
        + 배열 선언과 동시에 특정 값으로 초기화
            ```
            int[] studentIDs = new int[]{101, 102, 103} // 개수는 생략

            int[] studentIDs = new int[3]{101, 102, 103} // error: 개수 입력

            int[] studentIDs = {101, 102, 103} // new int[] 생략 가능

            int[] studentIDs; // 배열 자료형을 선언한 경우
            studentIDs = new int[]{101, 102, 103}; // new int[] 생략 불가능
            ```

* 배열 사용하기
    ```
    studentIDs[0] = 10; // 배열의 첫번째 요소에 값 10을 저장
    ```
    - 인덱스 연산자 []  
        : 배열을 처음 선언할 때 사용한 연산자
        + 인덱스 연산: 배열 이름에 []를 사용하는 것
        + 배열 요소가 저장된 메모리 위치를 찾아 주는 역할
        ```
        num[3] = 25; // 배열의 요소에 값 저장
        age = num[3]; // 변수에 네번째 요소 값을 저장
        ```
        + 배열의 위치
            + 물리적(physical) 위치: 배열이 메모리에서 실제 저장되는 곳
            + 논리적 위치(logical): 이론상 배열 위치
            + 물리적 위치 = 논리적 위치
    - 배열 순서  
        + 배열 길이가 n일 때, 배열 순서는 0 ~ (n-1)
        + 첫번째 요소: 0번 요소
        ```
        # 배열 초기화하고 출력하기
        package array;

        public class ArrayTest {
            public static void main(String[] args){
                int[] num = new int[]{1,2,3,4,5,6,7,8,9,10};

                for(int i = 0; i < num.length; i++){ // 배열의 num[0] ~ num[9]까지 10개 요소 값 출력
                    System.out.println(num[i]); // @@ 1 /n 2 /n ... 9 /n 10
                }
            }
        }
        ```
        + length: 배열의 길이의 속성
            + 처음에 선언한 배열의 전체 요소 개수
    - 전체 배열의 길이와 유효한 요소 값
        ```
        # 배열 길이만큼 출력하기
        package array;

        public class ArrayTest2{
            public static void main(String[] args){
                double[] data = new double[5]; // 배열 선언: double형 + 길이 5
                
                // 세번째 요소까지 값 지정 -> 네번째, 다섯번째 요소 값은 0.0으로 초기화 됨
                data[0] = 10.0;
                data[1] = 20.0;
                data[2] = 30.0;
                
                for(int i = 0; i < data.length; i++){ // i < data.length: 전체 배열 길이만큼 반복
                    System.out.println(data[i]); // @@ 10.0 /n 20.0 /n 30.0 /n 0.0 /n 0.0
                }
            }
        }
        ```
        ```
        # 배열의 유효한 요소 값 출력하기
        package array;

        public class ArrayTest3{
            public static void main(String[] args){
                double[] data = new double[5]; // 배열 선언: double형 + 길이 5
                int size = 0; // 유효한 값이 저장된 배열 요소의 개수를 의미하는 변수 선언

                data[0] = 10.0; size++; // size++: 값을 저장한 후 size 변수 값 증가
                data[1] = 20.0; size++;
                data[2] = 30.0; size++;

                for(int i = 0; i < size; i++){ // i < size: 유효한 값이 저장된 배열 요소 개수만큼 반복
                    System.out.println(data[i]); // @@ 10.0 /n 20.0 /n 30.0
                }
            }
        }
        ```

* 문자 저장 배열 만들기
```
package array;

public class CharArray{
    public static void main(String[] args){
        char[] alphabets = new char[26];
        char ch = 'A';

        for(int i = 0; i < alphabets.length; i++, ch++){
            alphabets[i] = ch; // i: 아스키 값 / ch: 아스키 값에 해당하는 알파벳
        }

        for(int i = 0; i < alphabets.length; i++){
            System.out.println(alphabets[i] + ", " + (int)alphabets[i]); // (int): char형 문자를 int형 정수로 변환
            // @@ "A, 65" /n "B, 66" /n ... "Y, 89" /n "Z, 90"
        }
    }
}
```

* 객체 배열 사용하기
```
package array;

public class BookArray{
    public static void main(String[] args){
        Book[] library = new Book[5]; // Book 클래스형으로 객체 배열 생성(인스턴스 주소 값을 담을 공간)

        for(int i = 0; i < library.length; i++){
            System.out.println(library[i]); // 각 공간은 비어있음 -> 초기화 @@ null /n null ... (5번)
        }
    }
}
```
![image](https://user-images.githubusercontent.com/104348646/195532864-5db5dc53-874c-410b-a58d-98c25f5c45bd.png)
```
# 각 배열 요소에 인스턴스를 생성 및 저장
package array;

public class BookArray2{
    public static void main(String[] args){
        Book[] library = new Book[5];

        library[0] = new Book("태백산맥", "조정래");
        library[1] = new Book("데미안", "헤르만 헤세");
        library[2] = new Book("7년의 밤", "정유정");
        library[3] = new Book("토지", "박경리");
        library[4] = new Book("어린왕자", "생텍쥐페리");

        for(int i = 0; i < library.length; i++){
            library[i].showBookInfo(); // Book 인스턴스 멤버들 @@ "태백산맥, 조정래" /n ... /n "어린왕자, 생텍쥐페리"
        }
        for(int i = 0; i < library.length; i++){
            System.out.println(library[i]); // @@ array.Book@707f7052 /n ... (5개의 다른 메로리 공간 주소)
        }
    }
}
```

* 배열 복사하기
    - 기존 배열과 같은 배열을 만들거나, 배열이 꽉 찬 경우 더 큰 배열을 만들고 기존 배열 자료를 복사
    - 1) 기존 배열과 배열 길이가 같거나 더 긴 배열을 만들고 for문을 사용해 각 요소 값을 반복해서 복사
    - 2) System.arraycopy() 메서드 사용  
        ![image](https://user-images.githubusercontent.com/104348646/195532902-be6530cf-af80-49f7-808b-8a0e0a8c6a98.png)  
        + src의 [scrPos, length] 길이 < dest의 [destPos: ] 길이이면, error
        ```
        package array;

        public class ArrayCopy{
            public static void main(String[] args){
                int[] array1 = {10, 20, 30, 40, 50};
                int[] array2 = {1, 2, 3, 4, 5};
                
                System.arraycopy(array1, 0, array2, 1, 4); // (복사할 배열, 복사할 첫 위치, 대상 배열, 붙여 넣을 첫 위치, 복사할 요소 개수)
                for(int i = 0; i < array2.length; i++){
                    System.out.println(array2[i]); // array1을 0번째부터 복사 -> array2에 1번째에 붙여 넣음, 1번 반복 @@ 1 /n 10 /n 20 /n 30 /n 40 
                }
            }
        }
        ```
    - 객체 배열 복사하기
        ```
        package array;

        public class ObjectCopy1{
            public static void main(String[] args) {
                Book[] bookArray1 = new Book[3];
                Book[] bookArray2 = new Book[3];

                bookArray1[0] = new Book("태백산맥", "조정래");
                bookArray1[1] = new Book("데미안", "헤르만 헤세");
                bookArray1[2] = new Book("7년의 밤", "정유정");
                System.arraycopy(bookArray1, 0, bookArray2, 0, 3);

                for (int i = 0; i < bookArray2.length; i++) {
                    bookArray2[i].showBookInfo(); // @@ "태백산맥, 조정래" /n "데미안, 헤르만 헤세" /n "7년의 밤, 정유정"
                }
            }
        }
        ```
    - 얕은 복사(shallow copy)
        + 객체 배열을 복사할 때, 인스턴스 자체가 아니라 기존 인스턴스의 주소 값만 복사
        + 복사되는 배열의 인스턴스 값이 변경되면, 두 배열 모두 영향 받음
        ```
        package array;

        public class ObjectCopy2{
            public static void main(String[] args){
                Book[] bookArray1 = new Book[3];
                Book[] bookArray2 = new Book[3];

                bookArray1[0] = new Book("태백산맥", "조정래");
                bookArray1[1] = new Book("데미안", "헤르만 헤세");
                bookArray1[2] = new Book("7년의 밤", "정유정");
                System.arraycopy(bookArray1, 0, bookArray2, 0, 3);
                // @@ "태백산맥, 조정래"
                // @@"데미안, 헤르만 헤세"
                // @@ "7년의 밤, 정유정"

                for(int i = 0; i < bookArray2.length; i++){
                    bookArray2[i].showBookInfo();
                }

                bookArray1[0].setBookName("나목"); // bookArray1 배열의 첫 번째 요소 값 변경
                bookArray1[0].setAuthor("박완서");

                System.out.println("=== bookArray1 ===");
                for(i = 0; i < bookArray1.length; i++){
                    bookArray1[i].showBookInfo();
                }
                // @@ === bookArray1 ===
                // @@ "나목, 박완서"
                // @@"데미안, 헤르만 헤세"
                // @@ "7년의 밤, 정유정"

                System.out.println("=== bookArray2 ===");
                for(i = 0; i < bookArray2.length; i++){
                    bookArray2[i].showBookInfo();
                }
                // @@ === bookArray2 ===
                // @@ "나목, 박완서" // bookArray2 배열 요소 값도 변경되어 출력
                // @@"데미안, 헤르만 헤세"
                // @@ "7년의 밤, 정유정"
            }
        }
        ```
        ![image](https://user-images.githubusercontent.com/104348646/195533016-6e9f1f67-61c2-40e7-bcca-0b4fc909f4d5.png)  

    - 깊은 복사(deep copy)
        + 직접 인스턴스를 만들고 그 값을 복사
        ```
        # 복사할 배열에 인스턴스를 따로 생성한 후 요소 값 복사

        package array;

        public class ObjectCopy3{
            public static void main(String[] args){
                Book[] bookArray1 = new Book[3];
                Book[] bookArray2 = new Book[3];

                bookArray1[0] = new Book("태백산맥", "조정래");
                bookArray1[1] = new Book("데미안", "헤르만 헤세");
                bookArray1[2] = new Book("7년의 밤", "정유정");
                
                bookArray2[0] = new Book(); // 디폴트 생성자로 bookArray2 배열 인스턴스 생성
                bookArray2[1] = new Book();
                bookArray2[2] = new Book();
                
                for(int i = 0; i < bookArray1.length; i++){ // bookArray1 배열 요소를 새로 생성한 bookArray2 배열 인스턴스에 복사
                    bookArray2[i].setBookName(bookArray1[i].getBookName());
                    bookArray2[i].setAuthor(bookArray1[i].getAuthor());
                }
                
                for(int i = 0; i < bookArray2.length; i++){
                    bookArray2[i].showBookInfo();
                }

                bookArray1[0].setBookName("나목");
                bookArray1[0].setAuthor("박완서");

                System.out.println("=== bookArray1 ===");
                for(i = 0; i < bookArray1.length; i++){
                    bookArray1[i].showBookInfo();
                }
                // @@ === bookArray1 ===
                // @@ "나목, 박완서"
                // @@"데미안, 헤르만 헤세"
                // @@ "7년의 밤, 정유정"

                System.out.println("=== bookArray2 ===");
                for(i = 0; i < bookArray2.length; i++){
                    bookArray2[i].showBookInfo();
                }
                // @@ === bookArray2 ===
                // @@ "태백산맥, 조정래" // bookArray1 배열 요소 값과 다른 값을 출력
                // @@"데미안, 헤르만 헤세"
                // @@ "7년의 밤, 정유정"
            }
        }
        ```
        ![image](https://user-images.githubusercontent.com/104348646/195533057-71265c6f-7851-4c39-8408-75483dc75e59.png)  

* 향상된 for문과 배열
    - 향상된 for문(enhanced for loop)  
        : 배열 요소 값을 순서대로 처음부터 끝까지 하나씩 가져와서 변수에 대입  
        ![image](https://user-images.githubusercontent.com/104348646/195533095-6ffddb68-94b3-4633-beb9-d2e5ef05bc4e.png)  
        ```
        package array;

        public class EnhancedForloop{
            public static void main(String[] args){
                String[] strArray = {"Java", "Android", "C", "JavaScript", "Python"};
                
                for(String lang : strArray){ // lang: 배열의 각 요소가 대입 / strArray: {"Java", ... , "Python}
                    System.out.println(lang); // @@ "Java" /n ... /n "Python"
                }
            }
        }
        ```

## 07-2 다차원 배열
* 다차원 배열  
    : 이차원 이상으로 구현한 배열

* 이차원 배열  
    ![image](https://user-images.githubusercontent.com/104348646/195533143-61528612-5021-44c4-87ff-d045a699328f.png)  
    - 초기화: 행과 열 개수에 맞추어 {} 안에 ,로 구분해 값을 입력  
    ![image](https://user-images.githubusercontent.com/104348646/195533170-b3bff0fa-0cf6-4183-bd68-c38f82b4a490.png)  
    ```
    # 이차원 배열 초기화하기

    package array;

    public class TwoDimension{
        public static void main(String[] args){
            int[][] arr = {{1,2,3},{4,5,6}}; // 이차원 배열 선언과 동시에 초기화
            
            for(int i = 0; i < arr.length; i++){
                for(int j = 0; j < arr[i].length; j++){
                    System.out.println(arr[i][j]);
                }
                System.out.println(); // 행 출력 끝난 후에 한 줄 띄움
            }
            // @@ 1 /n 2 /n 3
            // @@
            // @@ 4 /n 5 /n 6
        }
    }
    ```
    ![image](https://user-images.githubusercontent.com/104348646/195533229-3d2cca97-47f3-4995-8f82-2d2b0f8925c8.png)  

    ```
    # 이차원 배열의 길이 출력하기

    package array;

    public class TwoDimension2{
        public static void main(String[] args){
            int[][] arr = new int[2][3]; // 선언. 초기화x
            
            for(int i = 0; i < arr.length; i++){
                for(int j = 0; j < arr[i].length; j++){
                    System.out.println(arr[i][j]);
                }
                System.out.println();
            }
            System.out.println(arr.length);
            System.out.println(arr[0].length);
            // 1행 1 ~ 3열 @@ 0 /n 0 /n 0
            // @@
            // 2행 1 ~ 3열 @@ 0 /n 0 /n 0
            // @@
            // 행 길이 @@ 2
            // 열 길이 @@ 3
        }
    }
    ```

## 07-3 ArrayList 클래스 사용하기
* 기존 배열의 단점과 ArrayList
    - ArrayList 클래스  
        : 객체 배열을 관리할 수 있는 멤버 변수와 메서드를 제공
        + 가장 많이 사용하는 객체 배열 클래스
    - 기존 배열의 단점
        + 길이가 정해짐 -> 부족한 경우 다른 배열로 복사 필요
        + 중간에 있는 요소를 비워둘 수 없음 -> 배열 요소 위치 변경 필요

* ArrayList 클래스의 주요 메서드  
    ![image](https://user-images.githubusercontent.com/104348646/195533276-6848a97e-ccdb-4ffb-8f46-dbb9b8b14378.png)
    - add() 메서드를 사용해서, 배열 길이와 상관 없이 객체 추가 가능
    - 중간의 요소 값이 제거되면, 그 다음 요소 값을 하나씩 앞으로 이동하는 코드 구현됨

* ArrayList 클래스 활용하기
    - E: 사용할 객체의 자료형
    - 임포트(import)  
        : 코드에 없는 클래스를 가져와 사용할 때, 이 클래스에 어디에 구현되어 있는지 알리기 위해 선언하는 것
        + import java.util.ArrayList;
    ```
    # 기본 형식
    ArrayList<E> 배열 이름 = new ArrayList<E>();

    # 예시
    ArrayList<Book> library = new ArrayList<Book>();
    ```
    - get(): 요소를 하나 가져오는 메서드
    ```
    # ArrayList 클래스 사용하기

    package array;
    import java.util.ArrayList; // ArrayList 클래스 import

    public class ArrayListTest{
        public static void main(String[] args){
            ArrayList<Book> library = new Arraylist<Book>(); // ArrayList 선언

            library.add(new Book("태백산맥", "조정래")); // add() 메서드로 요소 값 추가 <- 배열 전체 길이를 미지 지정할 필요 없음
            library.add(new Book("데미안", "헤르만 헤세"));
            library.add(new Book("7년의 밤", "정유정"));
            library.add(new Book("토지", "박경리"));
            library.add(new Book("어린왕자", "생텍쥐페리"));

            for(int i = 0; i < library.size(); i++){ // size() 메서드: 배열에 추가된 요소 개수만큼 출력
                Book book = library.get(i);
                book.showBookInfo();
            }
            System.out.println();

            System.out.println("=== 향상된 for문 사용 ===");
            for(Book book : library){
                book.showBookInfo();
            }
            // @@ "태백산맥, 조정래" /n ... /n "어린왕자, 생텍쥐페리"
            // @@ "=== 향상된 for문 사용 ==="
            // @@ "태백산맥, 조정래" /n ... /n "어린왕자, 생텍쥐페리"
        }
    }
    ```

## 07-4 배열 응용 프로그램
![image](https://user-images.githubusercontent.com/104348646/195534067-0372bb30-5aa1-43f7-b16e-f5e7f85a7bf6.png)  
![image](https://user-images.githubusercontent.com/104348646/195534387-7531f0af-8f36-416d-85fc-5570e6e98ab7.png)  
* Student 클래스 구현하기
    - 한 학생이 여러 과목을 수강 -> Subject 클래스형으로 ArrayList를 생성
    - subjectList: 학생이 수강하는 과목을 저장할 배열
    - addSubject() 메서드: 수강 과목을 하나씩 추가
    ```
    package arrayList;
    import java.util.ArrayList;

    public class Student{
        // Student 클래스의 멤버 변수
        int studentID;
        String studentName;
        ArrayList<Subject> subjectList; // ArrayList 선언
        
        public Student(int studentID, String studentName){ // 생성자
            this.studentID = studentID;
            this.studentName = studentName;
            subjectList = new ArrayList<Subject>(); // ArrayList 생성하기
        }
        
        public void addSubject(String name, int score){ // 학생이 수강하는 과목을 subjectList 배열에 하나씩 추가하는 메서드
            Subject subject = new Subject(); // Subject 생성
            subject.setName(name);
            subject.setScorePoint(score);
            subjectList.add(subject);
        }
        
        public void showStudentInfo(){
            int total = 0;
            for(Subject s: subjectList){ // 배열 요소 값 출력
                total += s.getScorePoint(); // 총점 더하기
                System.out.println("학생 " + studentName + "의 " + s.getName() + " 과목 성적은 " + s.getScorePoint() + "입니다.");
            }
            System.out.println("학생 " + studentName + "의 총점은 " + total + "입니다.");
        }
    }
    ```
* Subject 클래스 구현하기
    ```
    package arrayList;

    public class subject{
        // Subject 클래스의 멤버 변수
        private String name;
        private int scorePoint;

        public String getName(){
            return.name;
        }
        public void setName(String name){
            this.name = name;
        }
        public int getScorePoint(){
            return scorePoint;
        }
        public void setScorePoint(int scorePoint){
            this.scorePoint = scorePoint;
        }
    }
```
* 테스트 클래스 구현 후 결과 확인하기
    ```
    package arrayList;

    public class StudentTest{
        public static void main(String[] args){
            Student studentLee = new Student(1001, "Lee");
            studentLee.addSubject("국어", 100);
            studentLee.addSubject("수학", 50);

            Student studentKim = new Student(1002, "Kim");
            studentKim.addSubject("국어", 70);
            studentKim.addSubject("수학", 85);
            studentKim.addSubject("영어", 100);

            studentLee.showStudentInfo();
            System.out.println("==================");
            studentKim.showStudentInfo();
        }
    }
```
