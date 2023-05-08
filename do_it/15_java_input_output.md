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
    ![image](https://user-images.githubusercontent.com/104348646/224688563-e178dfd8-e5f9-4d10-aa2b-596b20ad346c.png)  
  
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
   ![image](https://user-images.githubusercontent.com/104348646/224688355-2832a1c1-58e6-4f80-b073-f7a56191b325.png)  

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
      ![image](https://user-images.githubusercontent.com/104348646/224688073-351128a2-5f67-4213-a129-69f603ec9b64.png)  
      
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


## 15-4 문자 단위 스트림
* Reader  
  : 문자 단위로 읽는 스트림 중 최상위 스트림
  - 클래스
    + FileReader  
      : 파일에서 문자 단위로 읽는 스트림 클래스
    + InputStreamReader  
      : 바이트 단위로 읽은 자료를 문자로 변환해주는 보조 스트림 클래스
    + BufferedReader  
      : 문자로 읽을 때 배열을 제공해서 한꺼번에 읽을 수 있는 기능을 제공하는 보조 스트림
  - 메서드
    + int read()  
      : 파일로부터 한 문자를 읽음. 읽은 값을 반환
    + int read(char[] buf)  
      : 파일로부터 buf 배열에 문자를 읽음
    + int read(char[] buf, int off, int len)  
      : 파일로부터 buf 배열의 off 위치에서부터 len 개수만큼 문자를 읽음
    + void close()  
      : 스트림과 연결된 파일 리소스를 닫음

* FileReader
  - 생성자
    + FileReader(String name)  
      : 파일 이름 name(경로 포함)을 매개변수로 받아 입력 스트림을 생성
    + FileReader(File f)  
      : File 클래스 정보를 매개변수로 받아 입력 스트림을 생성
    ```
    package stream.reader;

    import java.io.FileReader;
    import java.io.IOException;

    public class FileReaderTest {
        public static void main(String[] args) {
            try(FileReader fr = new FileReader("reader.txt")) {
                int i;
                while((i = fr.read()) != -1) {
                    System.out.println((char)i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 문자를 입출력함 @@ 안녕하세요 
    // cf) FileInputStream: 바이트 단위로 읽음 -> 문자가 깨져서 출력됨
    ```

* Writer  
  : 문자 단위로 출력하는 스트림 중 최상위 스트림
  - 클래스
    + FileWriter  
      : 파일에 문자 단위로 출력하는 스트림 클래스
    + OutputStreamWriter  
      : 파일에 바ㅣ트 단위로 출력한 자료를 문자로 변환해주는 보조 스트림
    + BufferedWriter  
      : 문자로 쓸 때 배열을 제공하여 한꺼번에 쓸 수 있는 기능을 제공해주는 보조 스트림
  - 메서드
    + void write(int c)  
      : 한 문자를 파일에 출력
    + void write(char[] buf)  
      : 문자 배열 buf의 내용을 파일에 출력
    + void write(char[] buf, int off, int len)  
      : 문자 배열 buf의 off 위치에서부터 len 개수의 문자를 파일에 출력
    + void write(String str)  
      : 문자열 str을 파일에 출력
    + void write(String str, int off, int len)  
      : 문자열 str의 off번째 문자부터 len 개수만큼 파일에 출력
    + void flush()  
      : 파일에 출력하기 전에, 자료가 있는 공간(출력 버퍼)를 비워 출력
    + void close()  
      : 파일과 연결된 스트림을 닫음. 출력 버퍼도 비워짐

* FileWriter  
  : 생성자를 사용해서 스트림을 생성함
  - 파일이 존재하지 않으면 파일을 생성 (FileOutputStream과 유사)
  - 생성자
    + FileWriter(String name)  
      : 파일 이름 name(경로 포함)을 매개변수로 받아 출력 스트림을 생성함
    + FileWriter(String name, boolean append)  
      + 파일 이름 name(경로 포함)을 매개변수로 받아 출력 스트림을 생성함
      + append 값이 true면, 파일 스트림을 닫고 다시 생성할 때 파일 끝에 이어서 씀(default: false)
    + FileWriter(File f, )  
      : File 클래스 정보를 매개변수로 받아 출력 스트림을 생성
    + FileWriter(File f, boolean append)
      + File 클래스 정보를 매개변수로 받아 출력 스트림을 생성
      + append 값이 true면, 파일 스트림을 닫고 다시 생성할 때 파일 끝에 이어서 씀(default: false)
    ```
    package stream.writer;

    import java.io.FileWriter;
    import java.io.IOException;

    public class FileWriterTest {
        public static void main(String[] args){
            try(FileWriter fw = new FileWriter("writer.txt")) {
                fw.write('A'); // 문자 하나 출력 @@ A
                char buf[] = {'B', 'C', 'D', 'E', 'F', 'G'};

                fw.write(buf); // 문자 배열 출력 @@ B, C, D, E, F, G
                fw.write("안녕하세요"); // 문자열 출력 @@ 안녕하세요
                fw.write(buf, 1, 2); // 문자 배열의 일부 출력 @@ CD 
                fw.write("65"); // 숫자를 그대로 출력 // 65
            } catch(IOException e) {
                e.printStackTrace();
            }
            System.out.println("end"); // 출력화면
        }
    }
    ```

## 15-5 보조 스트림
* 보조 스트림(Wrapper 스트림)  
  : 보조 기능을 추가하는 스트림
  - 어떤 보조 스트림이 더해지느냐에 따라 스트림 기능이 추가됨
  - 자신이 감싸고 있는 스트림이 읽거나 쓰는 기능을 수행할 때, 보조 기능을 추가함

* FilterInputStream과 FilteroutputStream
  - 보조 스트림의 상위 클래스
  - 모든 보조 스트림이 상속받음
  - 생성자
    + protected FilterInputStream(InputStream in)
    + public FilterOutputStream(OutputStream out)
  - 다른 생성자를 제공하지 않음  -> 상속받은 보조 클래스도 다른 스트림을 매개변수로 받아 상위 클래스를 호출해야 함
  - 보통 상속받은 하위 클래스를 사용함
  - 보조 스트림의 생성자에 항상 기반 스트림만 매개변수로 전달되는 것은 아님(매개변수로 다른 보조 스트림도 가능)
  (p540 그림)
  
* InputStreamReader와 OutputStreamWriter
  - 바이트 자료만 입력되는 스트림(표준 입출력 System.in 스트림, 네트워크에서 소켓이나 인터넷이 연결되었을 때 읽거나 쓰는 스트림)이 있음
  - 바이트 스트림을 문자로 변환해주는 보조 스트림
  - InputStreamReader의 생성자
    + InputStreamReader(InputStream in)  
      : InputStream 클래스를 생성자의 매개변수로 받아 Reader를 생성함
    + InputStreamReader(InputStream in, Charset cs)  
      : InputStream 클래스를 매개변수로 받아 Reader를 생성함
    + InputStreamReader(InputStream in, CharsetDecoder dec)  
      : InputStream과 CharsetDecoder를 매개변수로 받아 Reader를 생성함
    + inputStreamReader(InputStream in, String charsetName)  
      : InputStream과 String으로 문자 세트 이름을 받아 Reader를 생성함
    + 모든 생성자는 InputStream(바이트 단위로 읽는 스트림)을 매개변수로 받음
    ```
    package stream.decorator;

    import java.io.FileInputStream;
    import java.io.IOException;
    import java.io.InputStreamReader;

    public class InputStreamReaderTest {
        public static void main(String[] args){
            try(InputStreamReader isr = new InputStreamReader(new FileInputStream(("reader.txt")))) { // InputStreamReader(보조 스크림)의 매개변수: FileInputStream(기반 스트림)
                int i;
                while((i = isr.read()) != -1){ // 파일 끝까지 반환해 보조 스트림으로 자료를 읽음
                    System.out.println((char)i); // @@ 안녕하세요
                }
                } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    ```

* Buffered 스트림  
  : 이미 생성된 스트림에 배열 기능을 추가해, 더 빠르게 입출력을 실행할 수 있는 버퍼링 기능을 제공함
  - 한 바이트/문자 단위로 입출력하는 경우, 프로그램 수행 속도가 느려짐
  - 클래스
    + BufferedInputStream  
      : 바이트 단위로 읽는 스트림에 버퍼링 기능을 제공
    + BufferedOutputStream  
      : 바이트 단위로 출력하는 스트림에 버퍼링 기능을 제공
    + BufferedReader  
      : 문자 단위로 읽는 스트림에 버퍼링 기능을 제공
    + BufferedWriter  
      : 문자 단위로 출력하는 스트림에 버퍼링 기능을 제공
  - 생성자
    + BufferedInputStream(InputStream in)  
      : InputStream 클래스를 생성자의 매개변수로 받아 BufferedInputStream을 생성
    + BufferedInputStream(InputStream in, int size)  
      : InputStream 클래스와 버퍼 크기를 생성자의 매개변수로 받아 BufferedInputStream을 생성
    ```
    package stream.decorator;

    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.io.IOException;

    public class FileCopyTest {
        public static void main(String[] args){
            long millisecond = 0;
            try(FileInputStream fis = new FileInputStream("a.zip");
            FileOutputStream fos = new FileOutputStream("copy.zip")) { // a.zip을 copy.zip으로 복사함
                millisecond = System.currentTimeMillis(); // 파일 복사를 시작하기 전 시간
                int i;
                while((i = fis.read()) != -1){
                    fos.write(i);
                }
                millisecond = System.currentTimeMillis() - millisecond; // 파일을 복사하는 데 걸리는 시간을 계산
            } catch (IOException e){
                e.printStackTrace();
            }
            System.out.println(millisecond); // @@ 23274
        }
    }
    ```
    ```
    package stream.decorator;

    import java.io.*;

    public class BufferedStreamTest {
        public static void main(String[] args){
            long millisecond = 0;
            try(FileInputStream fis = new FileInputStream("a.zip");
                FileOutputStream fos = new FileOutputStream("copy.zip");
                BufferedInputStream bis = new BufferedInputStream(fis);
                BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                millisecond = System.currentTimeMillis();
                int i;
                while((i = bis.read()) != -1){
                    bos.write(i);
                }
                millisecond = System.currentTimeMillis() - millisecond;
            } catch(IOException e){
                e.printStackTrace();
            }
            System.out.println(millisecond); // @@ 79
        }
    }
    ```
  - 소켓 통신에서 스트림 사용하기
    + 소켓(socket)  
      : 통신에 사용하는 네트워크 연결 리소스
    ```
    ## Socket 클래스: 소켓 통신을 위해 제공
    Socket socket = new Socekt();
    InputStream is = socket.getInputStream();
    
    ##
    Socket socket = new Socket();
    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputSteam())); // getInputStream(): InputStream이 반환됨(바이트 단위 스트림) -> 문자 깨짐 -> 문자로 변환 -> BufferedReader: 속도 증가
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    ```

* DataInputStream과 DataOutputStream
  - 메모리에 저장된 0, 1 상태를 그대로 읽거나 씀
  - 자료형의 크기가 그대로 보존됨
  - 생성자
    + DataInputStream(InputStream in)  
      : InputStream을 생성자의 매개변수로 받아 DataInputStream을 생성함
    + DataOutputStream(OutputStream out)  
      : OutputStream을 생성자의 매개변수로 받아 DataOutputStream을 생성함
  - DataInputStream의 자료형별 메서드(DataOutputStream은 read()에 대응하는 write() 메서드를 제공)
    + byte readByte()  
      : 1바이트를 읽어 반환
    + boolean readBoolean()  
      : 읽은 자료가 0이 아니면 true, 0이면 false를 반환
    + char readChar()
      : 한 문자를 읽어 반환
    + short readShort()  
      : 2바이트를 읽어 정수 값을 반환
    + int readInt()  
      : 4바이트를 읽어 정수 값을 반환
    + long readLong()  
      : 8바이트를 읽어 정수 값을 반환
    + float readFloat()  
      : 4바이트를 읽어 실수 값을 반환
    + double readDouble()  
      : 8바이트를 읽어 실수 값을 반환
    + String readUTF()  
      : 수정된 UTF-8 인코딩 기반으로 문자열을 읽어 반환
  ```
  package stream.decorator;

  import java.io.*;

  public class DataStreamTest {
      public static void main(String[] args){
          try(FileOutputStream fos = new FileOutputStream("data.txt");
              DataOutputStream dos = new DataOutputStream(fos)) {
              dos.writeByte(100);
              dos.writeChar('A');
              dos.writeInt(10);
              dos.writeFloat(3.14f);
              dos.writeUTF("Test");
          } catch(IOException e){
              e.printStackTrace();
          }
          try(FileInputStream fis = new FileInputStream("data.txt");
              DataInputStream dis = new DataInputStream(fis)) { // 파일에 쓴 것과 동일한 순서, 동일한 메서드로 읽어야 함
              System.out.println(dis.readByte()); // @@ 100
              System.out.println(dis.readChar()); // @@ A
              System.out.println(dis.readInt()); // @@ 10
              System.out.println(dis.readFloat()); // @@ 3.14
              System.out.println(dis.readUTF()); // @@ Test
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }
  ```
  - 데코레이터 패턴  
    : 객체의 결합 을 통해 기능을 동적으로 유연하게 확장 할 수 있게 해주는 패턴
    + 자바의 입출력 스트림 클래스가 데코레이터 패턴임
    + 데코레이터(장식자)  
      : 기능이 동적으로 추가되는 클래스
      + 여러 클래스에 다양하게 적용 가능
      ```
      WhippedCreamCoffee whippedCreamCoffee = new WhippedCreamCoffee(new MochaCoffe(new LatteCoffee(new KenyaCoffee())); // 휘핑 크림이 올라간 모카 커피
      // 실제 커피 클래스: KenyaCoffee
      // 데코레이터 클래스: LatteCoffee, MochaCoffe, WhippedCreamCoffee
      ```

## 15-6 직렬화
* 직렬화와 역직렬화
  - 직렬화(serialization)  
    : 인스턴스의 어느 순간 상태를 그대로 저장하거나 네트워크를 통해 전송하는 것
    + 인스턴스 내용을 연속 스트림으로 만드는 것
  - 역직렬화(deserialization)  
    : 저장된 내용이나 전송받은 내용을 다시 복원하는 것
  - 생성자
    + ObjectInputStream(InputStream in)  
      : InputStream을 생성자의 매개변수로 받아 ObjectInputStream을 생성
    + ObjectOutputStream(OutputStream out)  
      : OutputStream을 생성자의 매개변수로 받아 ObjectOutputStream을 생성
    ```
    ## 직렬화에 사용할 Person 클래스를 만들어서 인스턴스로 생성한 후, 파일에 썼다가 복원
    
    package stream.serialization;

    import com.sun.xml.internal.ws.developer.Serialization;

    import java.io.*;
    import java.lang.annotation.Annotation;

    class Person implements Serialization { // implements Serialization: 직렬화하겠다는 의미
        private static final long serialVersionUID = -1503252402544036183L; // 버전 관리를 위한 정보
        String name;
        String job;

        public Person() {}

        public Person(String name, String job){
            this.name = name;
            this.job = job;
        }

        public String toString(){
            return name + ", " + job;
        }
    }

    public class SerializationTest{
        public static void main(String[] args) throws ClassNotFoundException{ // ClassNotFoundException: 역직렬화 시, 클래스 정보가 존재하지 않을 수 있음
            Person personAhn = new Person("Ahn", "CEO");
            Person personKim = new Person("Kim", "CFO");

            try(FileOutputStream fos = new FileOutputStream("serial.out");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                // 직렬화: personAhn, personKim의 값을 파일에 씀
                oos.writeObject(personAhn);
                oos.writeObject(personKim);
            } catch(IOException e){
                e.printStackTrace();
            }
            try(FileInputStream fis = new FileInputStream("serial.out");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
                // 역직렬화: personAhn, personKim의 값을 파일에서 읽어들임
                Person p1 = (Person)ois.readObject();
                Person p2 = (Person)ois.readObject();

                System.out.println(p1); // @@ Ahn, CEO
                System.out.println(p2); // @@ Kim, CFO
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    ```
  - transient 예약어
    + 직렬화될 수 없는 클래스(Socket 클래스 등)이 인스턴스 변수로 있거나, 직렬화하지 않을 변수가 있는 경우에 사용
    + 해당 변수를 직렬화되고 복원되는 과정에서 제외함
    ```
    String name;
    transient String job;
    
    // @@ Ahn, null
    // @@ Kim, null
    ```
  - serialVersionUID를 사용한 버전 관리
    + 직렬화할 시, 자동으로 serialVersionUID를 생성해서 정보를 저장함
    + 역직렬화 시, serialVersionUID를 비교(클래스가 수정/변경되었는지 확인)
    + 클래스 내용이 변경된 경우, 오류 발생
    + 클래스의 버전 관리 필요
      + Add generated serial version ID: 버전 번호 생성
  - Externalizable
    + 객체의 직렬화와 역직렬화를 프로그래머가 직접 세밀하게 제어하기 위해 사용
    + 프로그래머가 직접 메서드에 그 내용을 구현함
    ```
    ## Externalizable Test
    
    package stream.serialization;

    import java.io.*;

    public class Dog implements Externalizable {
        String name;

        public Dog () {}

        // 구현 필요
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeUTF(name);
        }

        // 구현 필요
        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException{
            name = in.readUTF();
        }

        public String toString(){
            return name;
        }
    }

    public class ExternalizableTest{
        public static void main(String[] args) throws IOException, ClassNotFoundException{
            Dog myDog = new Dog();
            myDog.name = "puppy";

            FileOutputStream fos = new FileOutputStream("external.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            try(fos; oos) {
                oos.writeObject(myDog);
            } catch(IOException e){
                e.printStackTrace();
            }

            FileInputStream fis = new FileInputStream("external.out");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Dog dog = (Dog)ois.readObject();
            System.out.println(dog); // @@ puppy
        }
    }
    ```

## 15-7 그 외 입출력 클래스
* File 클래스
  : 파일 자체의 경로나 정보 확인, 파일 생성 가능
  + 입출력 기능은 없음
  ```
  package stream.others;

  import java.io.File;
  import java.io.IOException;
  import java.sql.SQLOutput;

  public class FileTest {
      public static void main(String[] args) throws IOException{
          File file = new File("D:\\newFile.txt"); // 해당 경로에 File 클래스 생성(아직 실제 파일이 생성되지는 않음)
          file.createNewFile(); // 실제 파일 생성

          // File 클래스가 제공하는 메서드: 파일의 속성을 반환함
          System.out.println(file.isFile()); // @@ true
          System.out.println(file.isDirectory()); // @@ false
          System.out.println(file.getName()); // @@ newFile.txt
          System.out.println(file.getAbsolutePath()); // @@ D:\newFile.txt
          System.out.println(file.getPath()); // @@ D:\newFile.txt
          System.out.println(file.canRead()); // @@ true
          System.out.println(file.canWrite()); // @@ true

          file.delete(); // 파일 삭제
      }
  }
  ```

* RandomAccessFile 클래스  
  : 임의의 위치로 이동해 자료를 읽음
  - 파일 입출력을 동시에 할 수 있음
  - 생성자
    + RandomAccessFile(File file, String mode)  
      : 입출력을 할 File과 입출력 mode를 매개변수로 받음
      + mode에는 r(읽기 전용), rw(읽고 쓰기 기능)을 사용할 수 있음
    + RandomAccessFile(String file, String mode)  
      : 입출력을 할 파일 이름을 문자열로 받고 입출력 mode를 매개변수로 받음
      + mode에는 r(읽기 전용), rw(읽고 쓰기 기능)을 사용할 수 있음
  - DataInput, DataOutput 인터페이스가 구현됨 -> DataInputStream, DataOutputStream과 같이 다양한 자료형을 다룰 수 있음
  ```
  package stream.others;

  import java.io.IOException;
  import java.io.RandomAccessFile;

  public class RandomAccessFileTest {
      public static void main(String[] args) throws IOException{
          RandomAccessFile rf = new RandomAccessFile("random.txt", "rw");
          rf.writeInt(100);
          System.out.println(rf.getFilePointer()); // int 크기: 4 @@ 4
          rf.writeDouble(3.14);
          System.out.println(rf.getFilePointer()); // double 크기: 8 @@ 12
          rf.writeUTF("안녕하세요");
          System.out.println(rf.getFilePointer()); // 수정된 UTF-8 사용 한글(3바이트) * 5 + null문자(2바이트): 17 @@ 29

          rf.seek(0); // 파일 포인터 위치를 맨 처음으로 옮기고 위치를 출력함(옮기지 않을 시, EOFException 오류 발생)
          System.out.println(rf.getFilePointer()); // @@ 0

          int i = rf.readInt();
          double d = rf.readDouble();
          String str = rf.readUTF();

          System.out.println(rf.getFilePointer()); // @@ 19
          System.out.println(i);
          System.out.println(d);
          System.out.println(str);
      }
  }
  ```
