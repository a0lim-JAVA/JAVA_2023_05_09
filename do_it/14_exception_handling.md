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
