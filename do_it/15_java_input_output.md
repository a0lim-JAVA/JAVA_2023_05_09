## 15-1 자바 입출력과 스트림
* 스트림(stream)  
  : 입출력 장치와 무관하고 일관성 있게 프로그램을 구현할 수 있는 일종의 가상 통로  
     - 자바의 모든 입출력이 일어남  
     - 파일 디스크, 키보드, 모니터, 메모리 입출력, 네트워크 등에서 입출력 기능을 사용함  

* 스트림의 분류
  - 입력 스트림과 출력 스트림
    + 입력 스트림  
      : 자료를 읽어 들일 때 사용하는 스트림
      + ex) InputStream, Reader로 끝남
    + 출력 스트림  
      : 편집 화면에 사용자가 쓴 글을 파일에 저장할 때 사용하는 스트림
      + ex) OutputStream, Writer로 끝남
    + 스트림은 단방향으로 자료가 이동함 -> 입출력이 동시에 작동할 수 없음

  - 바이트 단위 스트림과 문자 단위 스트림
    + 기본: 바이트 단위로 입출력이 이뤄짐
      + ex) Reader, Writer로 끝남
    + char형(문자)
      + 2바이트 -> 1바이트 만으로는 문자가 깨짐 -> 문자 스트림을 별도로 제공
      + ex) Stream으로 끝남

  - 기반 스트림과 보조 스트림
    + 기반 스트림
      : 자료를 직접 읽거나 쓰는 기능을 제공
      + 읽어 들일 곳(소스)나 써야할 곳(대상)에서 직접 읽고 쓸 수 있음
      + 입출력 대상에 직접 연결되어 생성됨
      + ex) FileInputStream. FileOutputStream, FileReader, FileWriter 등
    + 보조 스트림
      : 다른 스트림에 부가 기능을 제공
      + 직접 읽고 쓰는 기능이 없음
      + ex) InputStreamReader, OutputStreamWriter, BufferedInputStream, BufferedOutputStream 등

## 15-2 표준 입출력

|자료형|변수 이름|설명|
|------|---------|----|
|static PrintStream|out|표준 출력 스트림|
|static InputStream|in|표준 입력 스트림|
|static OutputSteam|err|표준 오류 출력 스트림|

* System.in으로 화면에서 문자 입력받기
  ```
  package stream.inputstream;

  import java.io.IOException;

  public class SystemInTest1 {
      public static void main(String[] args) throws IOException{
          System.out.println("Write Alphabet");

          int i;
          try {
              i = System.in.read();
              System.out.println(i); // 65: 1바이트만 읽음
              System.out.println((char)i); // A: 문자형으로 변환해서 출력해야 함
          } catch (IOException e){
              e.printStackTrace();
          }
      }
  }
  ```
  ```
  ## 문자 여러 개를 입력받기
  
  package stream.inputstream;

  import java.io.IOException;

  public class SystemInTest2 {
      public static void maiin(String[] args){
          System.out.println("Write Alphabets"); // @@ hello

          int i;
          try {
              while((i = System.in.read()) != '\n'){ // read(): 한 바이트를 읽음
                  System.out.println((char)i); // @@ hello
              }
          } catch (IOException e) {
              e.printStackTrace(); // 예외가 발생했는지 따라감
          }
      }
  }
  ```
  
  * 그 외 입력 클래스
    - Scanner 클래스  
      : 문자, 정수, 실수 등의 다른 자료형도 읽기 가능
      + 파일, 문자열을 생성자의 매개변수로 받아 읽기 가능
      |생성자|설명|
      |------|----|
      |Scanner(File source)|파일을 매개변수로 받아 Scanner를 생성함|
      |Scanner(InputStream source)|바이트 스트림을 매개변수로 받아 Scanner를 생성함|
      |Scanner(String source)|String을 매개변수로 받아 Scanner를 생성함|
      ```
      Scanner scanner = new Scanner(System.in) // 표준 입력으로부터 자료를 읽는 기능 사용 가능
      ```
      + 메서드
        + boolean nextBoolean(): boolean 자료를 읽음
        + byte nextByte()
        + short nextShort()
        + int nextInt()
        + long nextLong()
        + float nextFloat()
        + double nextDouble()
        + String nextLine()
      ```
      ## Scanner 테스트
      
      package stream.others;

      import java.util.Scanner;

      public class ScannerTest {
          public static void main(String[] args){
              Scanner scanner = new Scanner(System.in);

              System.out.println("이름: ");
              String name = scanner.nextLine();

              System.out.println("직업: ");
              String job = scanner.nextLine();

              System.out.println("사번: ");
              int num = scanner.nextInt();

              System.out.println(name);
              System.out.println(job);
              System.out.println(num);
          }
      }
      ```

    - Console 클래스  
      : 직접 콘솔 창에서 입력받을 때 사용
      + System.in을 사용하지 않고 간단히 콘솔 내용을 읽을 수 있음
      + 이클립스에서는 연동되지 않음 -> Scanner이 더 보편적임
      + 메서드
        |메서드|설명|
        |------|----|
        |String readLine()|문자열을 읽음|
        |char[] readPassword()|사용자에게 문자열을 보여주지 않고 읽음|
        |Reader reader()|Reader 클래스를 반환함|
        |PrintWriter writer()|PrintWriter 클래스를 반환|
      ```
      ## Console 테스트
      
      package stream.others;

      import java.io.Console;


      public class ConsoleTest {
          public static void main(String[] args){
              Console console = System.console(); // 콘솔 클래스 반환

              System.out.println("이름: ");
              String name = console.readLine();

              System.out.println("직업: ");
              String job = console.readLine();

              System.out.println("비밀번호: ");
              char[] pass = console.readPassword(); // 문자열을 보여주지 않음
              String strPass = new String(pass);

              System.out.println(name);
              System.out.println(job);
              System.out.println(strPass);
          }
      }
      ```

## 15-3 바이트 단위 스트림
* InputStream  
  : 추상 메서드를 포함한 추상클래스
  - 하위 스트림 클래스가 상속받아 각 클래스 역할에 맞게 추상 메서드 기능을 구현함
  - 바이트 단위로 읽는 스트림 중 최상위 스트림
  - 하위 클래스
    + FileInputStream  
      : 파일에서 바이트 단위로 자료를 읽음
    + ByteArrayInputStream  
      : byte 배열 메모리에서 바이트 단위로 자료를 읽음
    + FilterInputStream  
      : 기반 스트림에서 자료를 읽을 때, 추가 기능을 제공하는 보조 스트림의 상위 클래스
  - 메서드
    + int read()  
      : 입력 스트림으로부터 한 바이트의 자료를 읽음. 읽은 자료의 바이트 수를 반환(int)
    + int read(byte[] b)  
      : 입력 스트림으로부터 b[] 크기의 자료를 b[]에 읽음. 읽은 자료의 바이트 수를 반환(int)
    + int read(byte[] b, int off, int len)  
      : 입력 스트림으로부터 b[] 크기의 자료를 b[]의 off 변수 위치부터 저장하고, len만큼 읽음. 읽은 자료의 바이트 수를 반환(int)
    + void close()  
      : 입력 스트림과 연결된 대상 리소스를 닫음
      + ex) FileInputStream인 경우, 파일 닫음

* FileInputStream  
  : 파일에서 바이트 단위로 자료를 읽어 들일 때 사용
  - 생성자
    + FileInputStream(String name)  
      : 파일 이름 name(경로 포함)을 매개변수로 받아 입력 스트림을 생성
    + FileInputStream(File f)  
      : File 클래스 정보를 매개변수로 받아 입력 스트림을 생성
    ```
    ## FileInputStream 사용하기
    
    package stream.inputstream;

    import java.io.FileInputStream;
    import java.io.IOException;

    public class FileInputStreamTest1 {
        public static void main(String[] args){
            FileInputStream fis = null;

            try{
                fis = new FileInputStream("input.txt"); // input.txt 파일 입력 스트림 생성
                System.out.println(fis.read());
                System.out.println(fis.read());
                System.out.println(fis.read());
            } catch (IOException e){ // IOException: FileNotFoundException의 상위 예외 클래스
                System.out.println(e);
            } finally {
                try {
                    fis.close(); // 열린 스트림은 finally 블록에서 닫음 -> 위의 예외로 인해 스트림이 생성되지 않음 -> NullPointerException
                } catch (IOException e) {
                    System.out.println(e);
                } catch (NullPointerException e){ // 스트림이 null인 경우
                    System.out.println(e);
                }
            }
            System.out.println("end"); // 예외처리가 되어도 프로그램이 중단되지 않고, 출력됨
        }
    }
    ```
  - 파일에서 자료 읽기
    ```
    System.out.println((char)fis.read());
    ```
  - 파일 끝까지 읽기
    + 파일 내용의 크기를 모르는 경우
    ```
    // try-with-resources문 사용
    
    package stream.inputstream;

    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.IOException;

    public class FileInputStreamTest2 {
        public static void main(String[] args){
            try(FileInputStream fis = new FileInputStream("input.txt")) {
                // i 값이 -1이 아닌 동안(읽을 자료가 있는 동안) read() 메서드로 한 바이트를 반복해 읽음
                int i;
                while((i = fis.read()) != -1){
                    System.out.println((char)i);
                }
                System.out.println("end");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // @@ a
    // @@ b
    // @@ c
    // @@ end
    ```
  - int read(byte[] b) 메서드로 읽기
    : 선언한 바이트 배열의 크기만큼 한꺼번에 자료를 읽음
    ```
    ## byte 배열로 읽기 - A to Z
    
    package stream.inputstream;

    import java.io.FileInputStream;
    import java.io.IOException;

    public class FileInputStreamTest3 {
        public static void main(String[] args){
            try(FileInputStream fis = new FileInputStream("input2.txt")) {
                byte[] bs = new byte[10];
                int i;
                while((i = fis.read(bs)) != -1){
                    for(byte b : bs){
                        System.out.println((char)i);
                    }
                    System.out.println(": " + i + "바이트 읽음");
                }
            } catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("end");
        }
    }

    // @@ ABCDEFGHIJ: 10바이트 읽음
    // @@ KLMNOPQRST: 10바이트 읽음
    // @@ UVWXYZQRST: 6바이트 읽음
    // @@ end
    ```
    + 남은 공간에는 이전에 남아 있던 자료가 입력됨
      (p572 그림)
      
      ```
      ## 기존: 전체 배열을 출력
      
      for(byte b : bs){
        System.out.println((char)b);
      }
      
      // @@ UVWXYZQRST: 6바이트 읽음
      
      ## 수정: 바이트 수만큼(i) 출력
      
      for(int k = 0; k < i; k++){
        System.out.println(bs[k]);
      }
      
      // @@ UVWXYZ: 6바이트 읽음
      ```

* OutputStream
  - 바이트 단위로 쓰는 스트림 중 최상위 스트림
  - 자료의 출력 대상에 따라 다른 스트림을 제공함
  - 클래스
    + FileOutputSream  
      : 바이트 단위로 파일에 자료를 씀
    + ByteArrayOutputStream  
      : Byte 배열에 바이트 단위로 자료를 씀
    + FilterOutputStream  
      : 기반 스트림에서 자료를 쓸 때, 추가 기능을 제공하는 보조 스트림의 상위 클래스
  - 메서드
    + void write(int b)  
      : 한 바이트를 출력
    + void write(byte[] b)  
      : b[] 배열에 있는 자료를 출력
    + void write(byte[] b, int off, int len)  
      : b[] 배열에 있는 자료의 off 위치부터 len 개수만큼 자료를 출력
    + void flush()  
      : 출력을 위해 잠시 자료가 머무르는 출력 버퍼를 강제로 비워 자료를 출력
    + void close()  
      : 출력 스트림과 연결된 대상 리소스를 닫음. 출력 버퍼가 비워짐
      + ex) FileOutputStream: 파일을 닫음

* FileOutputStream  
  : 파일에 바이트 단위 자료를 출력하기 위해 사용
  - 생성자
    + FileOutputStream(String name)  
      : 파일 이름 name(경로 포함)을 매개변수로 받아 출력 스트림을 생성
    + FileOutputStream(String name, boolean append)  
      + 파일 이름 name(경로 포함)을 매개변수로 받아 입력 스트림을 생성
      + append가 true면 파일 스트림을 닫고 다시 생성할 때 파일의 끝에 이어서 사용함(default: false)
    + FileOutputStream(File f, )  
      : File 클래스 정보를 매개변수로 받아 출력 스트림을 생성
    + FileOutputStream(File f, boolean append)  
      + File 클래스 정보를 매개변수로 받아 출력 스트림을 생성
      + append가 true면 파일 스트림을 닫고 다시 생성할 때 파일의 끝에 이어서 사용함(default: false)

  - write() 메서드 사용하기
    ```
    package stream.outputstream;

    import java.io.FileOutputStream;
    import java.io.IOException;

    public class FileOutputStreamTest1 {
        public static void main(String[] args){
            try(FileOutputStream fos = new FileOutputStream("output.txt")){
                // FileOutputStream: 파일에 숫자를 쓰면 해당하는 아스키 코드 값으로 변환됨
                fos.write(65);
                fos.write(66);
                fos.write(67);
            } catch(IOException e){
                e.printStackTrace();
            }
            System.out.println("end"); // @@ end
        }
    }

    // cf) fos = new FileOutputStream("output.txt", true) // 파일에 이어서 씀
    ```

  - write(byte[] b)메서드 사용하기
    ```
    package stream.outputstream;

    import java.io.FileOutputStream;
    import java.io.IOException;

    public class FileOutputStreamTest2 {
        public static void main(String[] args) throws IOException {
            FileOutputStream fos = new FileOutputStream("output.txt", true); // 향상된 try-with-resources문(자바 9부터 제공)
            try(fos){
                byte[] bs = new byte[26];
                byte data = 65; // 'A'의 아스키 값
                // A부터 Z까지 배열에 넣기
                for(int i = 0; i < bs.length; i++){
                    bs[i] = data;
                    data++;
                }
                fos.write(bs); // 배열을 한꺼번에 출력
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("end");
        }
    }
    
    // @@ ABCDEFGHIJKLMNOPQRSTUVWXYZ
    ```
  - write(byte[] b, int off, int len) 메서드 사용하기
    + 배열 자료 중 일부를 출력할 때 사용
    ```
    package stream.outputstream;

    import java.io.FileOutputStream;
    import java.io.IOException;

    public class FileOutputStreamTest3 {
        public static void main(String[] args){
            try(FileOutputStream fos = new FileOutputStream("output3.txt")) {
                byte[] bs = new byte[26];
                byte data = 65;
                for(int i = 0; i < bs.length; i++){
                    bs[i] = data;
                    data++;
                }
                fos.write(bs, 2, 10);
            } catch(IOException e) {
                e.printStackTrace();
            }
            System.out.println("end");
        }
    }

    // @@ CDEFGHIJKL
    ```
  - flush() 메서드  
    : 강제로 자료를 출력함
    + 자료의 양이 출력할 만큼 많지 않은 경우 -> 파일에 쓰이지 않거나 전송되지 않는 경우가 생김 -> flush() 메서드로 강제 출력함
  - close() 메서드
    + close() 메서드 안에서 flush() 메서드를 호출하는 경우 -> 출력 버퍼가 지워지면서 남아 있는 자료가 모두 출력됨









