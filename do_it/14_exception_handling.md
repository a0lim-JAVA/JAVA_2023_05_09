## 14-1 예외 클래스
* 오류
  - 종류
    + 컴파일 오류(compile error)  
      : 프로그램 코드 작성 중 실수로 발생하는 오류
    + 실행 오류(runtime error)  
      : 실행 중인 프로그램이 의도하지 않은 동작을 하거나 프로그램이 중지되는 오류
      + 버그(bug)  
        : 실행 오류 중 프로그램을 잘못 구현하여 의도한 바와 다르게 실행되어 생기는 오류
  - 예외 처리의 목적
    + 프로그램이 비정상 종료되는 것을 방지
  - 로그를 남기면, 예외 상황을 파악하고 버그를 수정하기 쉬움

* 오류와 예외
  - 실행 오류
    + 시스템 오류(error)  
      - 자바 가상 머신에서 발생
      - 프로그램에서 제어 불가능
      - ex) 사용 가능한 동적 메모리가 없는 경우, 스택 메모리의 오버플로가 발생한 경우
    + 예외(exception)  
      - 프로그램에서 제어 가능
      - ex) 파일이 없는 경우, 네트워크 연결이 안 된 경우, 배열 요소가 없는 경우 등
  - 오류에 대한 전체 클래스  
    : 오류 클래스는 모두 Throwable 클래스에서 상속받음
    (p 488 그림)
    
* 예외 클래스의 종류
  - Exception 클래스가 최상위 클래스
    (p488 그림 2)
  - IOException 클래스  
    : 입출력에 대한 예외 처리
    + 이클립스같은 개발 환경에서는 try-catch문을 사용하여 컴파일 오류를 예외 처리 가능
  - RuntimeException  
    : 프로그램 실행 중 발생할 수 있는 오류에 대한 예외 처리
    + 이클립스같은 개발 환경에서는 컴파일 오류가 발생하지 않음
    + ex) ArithmeticException: 0으로 숫자를 나누는 경우
    
## 14-2 예외 처리하기
* try-catch문
  ```
  try{
      예외가 발생할 수 있는 코드 부분
  } catch(처리할 예외 타입 e){
      try 블록 안에서 예외가 발생했을 때 예외를 처리하는 부분
  }
  ```
  ```
  ## try-catch문 사용하기
  
  package exception;

  public class ArrayExceptionHandling {
      public static void main(String[] args){
          int[] arr = new int[5];

      try{ // 예외 발생할 수 있는 코드: arr의 길이는 5, i의 개수는 6개
          for(int i = 0; i <=5; i++){
              arr[i] = i;
              System.out.println(arr[i]);
          }
      } catch(ArrayIndexOutOfBoundsException e){ // ArrayIndexOutOfBoundsException: 배열에 저장하려는 값의 개수가 배열 범위를 벗어난 경우에 발생하는 예외
          System.out.println(e); // 예외가 발생하면 수행함
      }
      System.out.println("프로그램 종료"); // try-catch문 없이 예외가 발생한 경우, 수행되지 않음
      }
  }
  
  // @@ 0
  // @@ 1
  // @@ 2
  // @@ 3
  // @@ 4
  // @@ java.lang.ArrayIndexOutOfBoundsException
  // @@ 프로그램 종료
  ```

* 컴파일러에 의해 예외가 체크되는 경우
  - 파일 입출력에서 발생하는 예외 처리하기
    + 파일을 읽고 쓰기 위해 스트림(stream) 객체를 사용
      ```
      ## exception.ExceptionHandling1.java
      
      FileInputStream fis = new FileInputStream("a.txt"); // FileInputStream: 데이터를 바이트 단위로 읽음
      // 'FileNotFoundExcption이 처리되지 않음' -> Surround with try/catch(try-catch문 사용)
      // 읽으려는 파일이 없는 경우, JVM에서 FileNotFoundException 예외 클래스가 생성됨
      
      ## Surround with try/catch 사용: 예외가 발생할 위험이 있는 코드가 try 안에 들어감
      
      try {
            fis = new FileInputStream("a.txt");
        } catch (FileNotFoundException e) { // 발생 가능한 예외: FileNotFoundException
            throw new RuntimeException(e);
        }
        
      // 예외 처리 이후에도 프로그램은 계속 수행됨 
      ```

* try-catch-finally문
  - 사용한 시스템 리소스를 사용 후 반드시 close() 메서드로 닫아야 함
  - 끝나지 않고 계속 수행되는 서비스가 있음 -> 자원의 한계
  - finally 블록은 어떤 경우에도 반드시 수행됨(return이 있어도)
    ```
    try{
        예외가 발생할 수 있는 부분
            } catch(처리할 예외 타입 e){
        예외를 처리하는 부분
            } finally {
        항상 수행되는 부분
            }
    ```
    ```
    package exception;

    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.IOException;

    public class ArrayExceptionHandling3 {
        public static void main(String[] args){
            FileInputStream fis = null;

            try{
                fis = new FileInputStream("a.txt");
            } catch (FileNotFoundException e){ // 입력받은 파일이 없는 경우의 예외처리
                System.out.println(e);
                return;
            } finally { // 파일 리소스를 닫음
                if(fis != null){
                    try {
                        fis.close(); // 파일 입력 스트림 닫기
                    } catch (IOException e){ // 예외처리
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                System.out.println("always"); // return과 상관 없이 항상 수행됨
            }
            System.out.println("also");
        }
    }
    ```

* try-with-resources문
  - Closeable과 AutoCloseable 인터페이스를 구현함
  - close()를 명시적으로 호출하지 않아도 정상/예외 경우 모두 close() 메서드가 호출됨
  - AutoCloseable 인터페이스
    + close() 메서드가 있어서, close()를 명시적으로 호출하지 않아도 close() 메서드 부분이 호출됨
    ```
    package exception;

    public class AutoCloseObj implements AutoCloseable{
        @Override
        // close() 메서드 구현
        public void close() throws Exception{
            System.out.println("close");
        }
    }
    ```
    ```
    ## try-with-resources문 test: 정상적으로 종료되는 경우
    
    package exception;

    public class AutoCloseTest {
        public static void main(String[] args) {
            try (AutoCloseObj obj = new AutoCloseObj()) { // 사용할 리소스 선언
            } catch (Exception e) {
                System.out.println("exception");
            }
        }
    }
    ```
    ```
    ## 리소스를 여러 개 생성하는 경우, ;으로 구분함
    try(A a = new A(); B b = new B()){
    ...
      } catch(Exception e{
      ...
      }
    ```
    ```
    ## try-with-resources문 test: 예외가 발생해서 종료되는 경우
    
    package exception;

    public class AutoCloseTest {
        public static void main(String[] args) {
            try (AutoCloseObj obj = new AutoCloseObj()) {
                throw new Exception(); // 강제 예외 발생
            } catch (Exception e) {
                System.out.println("exception");
            }
        }
    }

    // @@ close
    // @@ exception
    ```
  - 향상된 try-with-resources문
    + 자바 9부터 가능
    + try문의 괄호 안에 외부에서 선언한 변수 사용 가능 -> 가독성 향상, 반복 선언 감소
    
    
    
    
    
    
    
    
    
    
    
