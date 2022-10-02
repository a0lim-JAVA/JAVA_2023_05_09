## 02-1 컴퓨터는 데이터를 어떻게 표현할까
### 컴퓨터에서 수를 표현하는 방법
* 2진수
	- 0 또는 1로 표현
	- 비트(bit)  
		: 최소 단위
	- 바이트(byte): 8비트
* 아스키(ASCII: American Standard Code for Information Interchange)  
	: 미국 표준 협회(ANSI)가 영문자, 숫자, 특수 문자를 8비트 값의 수로 정의한 규칙
	- ex) 10(10진수) = 0B1010(2진수) = O12(8진수) = 0XA(16진수)

	![image](https://user-images.githubusercontent.com/104348646/193027108-e3a96960-12f9-4a8f-b354-bf4257a40be0.png)  

### 부호 있는 수를 표현하는 방법
* 부호 비트(MSB: Most Significant Bit)
	- 맨 앞에 붙임
	- 0이면 양수, 1이면 음수  
	![image](https://user-images.githubusercontent.com/104348646/193026240-6bced4e3-acbf-4076-ab3e-660cdda49f5d.png)  
  
* 보수  
	: 보충해 주는 수 (음수 표현)
	- 어떤 특정한 10진수 N이 있을 때, 3에 대한 보수 = 3과 어떤 수를 합하여 N이 되는 수
	- ex) N: 10, 3에 대한 보수 = 7 (3 + 7 = 10)
	- ex) 2진수에서 3의 보수  
	![image](https://user-images.githubusercontent.com/104348646/193026293-6bc2cc9e-e2ba-4088-b231-e3ddf219594b.png)  
	- 2의 보수  
		: 부호를 바꿔주는 연산
	- ex) 10진수 5를 대상으로, 2의 보수 연산  
	![image](https://user-images.githubusercontent.com/104348646/193026338-22287127-c955-4e5b-b205-cb8989c44b00.png)  


## 02-2 변수란 무엇일까?
* 변수: 프로그래밍에서 값을 사용하기 위해 선언하는 것
	- 프로그램에서 사용되는 자료를 저장하기 위한 공간
```
### 변수 선언과 값을 대입하고 출력
public class Variable1 {
    public static void main(String[] args) {
        int level; //정수형 변수 level을 선언
        level = 10; //level 변수에 값 10을 대입 //왼쪽 값(level): lvalue, 오른쪽 값(10): rvalue
        System.out.println(level); //level 값 출력
    }
}
```

### 변수 초기화하기
* 초기화  
	: 변수에 처음 값을 대입하는 것
	- 변수 선언 + 초기화
	- 변수 선언 -> 초기화
```
# 변수 초기화하기
public class Variable2 {
    public static void main(String[] args){
        int level = 10; // level 변수 선언 + 값을 대입(초기화)
        System.out.println(level);
    }
}
```

### 변수 이름 정하기
* 영문자(대문자, 소문자), 숫자, $, _ 만 사용 가능
* 변수 이름은 숫자로 시작 불가
* 자바에서 이미 사용 중인 예약어는 사용 불가
	- ex) while, int, break, ...
	- cf) 예약어(reserved word)  
		: 프로그래밍 언어에서 특별한 의미로 미리 약속되어 있는 단어
* 카멜 표기법(camel notation)  
	: 중간에 다른 뜻의 단어가 등장할 때, 첫 글자를 대문자로 사용


## 02-3 변수가 저장되는 공간의 특성, 자료형
* 메모리  
	: 프로그램이 실행되는 작업 공간
	- 변수를 컴퓨터 내부의 메모리 공간에 저장

* 기본 자료형의 종류  
	![image](https://user-images.githubusercontent.com/104348646/193280028-c7cad6b4-8975-42c4-98a3-8c63ee027f14.png)  


* 정수 자료형
	- 0: 모든 비트가 0 -> 양수의 범위에서 제외  
	![image](https://user-images.githubusercontent.com/104348646/193280088-e11ea272-0775-4c33-a838-b5aeaeb21476.png)  

	- byte형
		+ 1 byte = 8 bit
		+ byte 단위의 정보를 저장하거나 통신할 때 주로 사용
		+ 수의 범위(-128 ~ 127)를 초과하는 경우, 에러 발생
	- short형
		+ 2 byte
	- int형
		+ 4 byte
		+ 정수 표현의 기본형
		+ 정수 연산을 할 때, 4바이트 단위로 처리하는 것이 가장 효율적
		+ 자료형이 다른 정수와 연산하는 경우, 두 정수를 더하기 전에 모두 int형으로 변환 -> 출력: int형
	- long형
		- 정수를 표현하는 가장 큰 단위의 자료형
		```
		public class IntegerVariable {
		    public static void main(String[] args) {
			int num1 = 12345678900; // error: int형의 범위를 초과함
			long num2 = 12345678900; // error: 모든 정수 값을 기본형으로 처리
			long num = 12345678900L; // 식별자 L: long형임을 나타냄
			long num = 1000; // 정수 값 1000dms long형 변수로 자동 변환되어 저장됨
		    }
		}
		```
* 문자 자료형
	- 인코딩(encoding)  
		: 각 문자에 따른 특정한 숫자 값(코드 값)을 부여
	- 디코딩(decoding)  
		: 숫자 값을 원래의 문자로 변환
	- 문자 세트  
		: 문자를 표현할 코드 값(숫자 값)들을 정한 집합
		+ 아스키(ASCII)
			+ 기본 문자 인코딩
			+ 영문자, 숫자, 특수 문자: 1바이트만 사용
		+ 유니코드(Unicode)
			+ ex) UTF-8, UTF-16
			+ 유니코드의 1 byte는 아스키 코드 값과 호환됨
			+ 다른 언어 문자(한글 등) 및 그 밖의 문자: 2바이트 (이상) 사용
			```
			public class CharEx1 {
			    public static void main(String[] args){
				char ch1 = 'A';
				System.out.println(ch1); // 문자 출력 @@ A
				System.out.println((int)ch1); // 문제에 해당하는 정수 값(=아스키 코드 값) 출력 @@ 65

				char ch2 = 66;
				System.out.println(ch2); // 정수 값에 해당하는 문자 출력 @@ B

				int ch3 = 67;
				System.out.println(ch3); // 문자 정수 값 출력 @@ '67'
				System.out.println((char)ch3); // 정수 값에 해당하는 문자 출력 @@ C
			    }
			}
			```

- 문자형 기본 문법
		+ 문자: ''를 사용
		+ 문자열(문자를 여러 개 이은 것): ""를 사용
		+ 널문자('\0'): 문자열의 끝을 표현. 모든 문자열 끝에 붙음
		```
		public class CharQuotes {
			public static void main(String[] args){
				char ex_char = 'A';
				System.out.println(ex_char); // 정수 값 65로 정해진 문자 @@ A
				System.out.println((int)ex_char); // @@ 65

				String ex_string = "A";
				System.out.println(ex_string); // 내부를 살펴보면 "A\0"으로 쓰임 
				System.out.println((int)ex_string); // error: 'A'와 "A"는 다름
			}
		}
		```
	- 유니코드 값을 사용
		+ ex)'한' : 16진수로 나타냄 -> 2 바이트 사용
		```
		public class CharEx2 {
			public static void main(String[] args){
				char ch1 = '한';
				char ch2 = '\uD55C'; // '한'의 유니코드 값

				System.out.println(ch1); // @@ 한
				System.out.println(ch2); // @@ 한
			}
		}
		```
	- 문자형 변수에 숫자를 저장  
		: 컴퓨터 내부에서는 정수 값으로 표현되기 때문에 정수 자료형으로 분류 가능  
		+ char형은 음수 값 표현이 불가능
		```
		public class CharEx3 {
		    public static void main(String[] args){
		        int a = 65;
		        int b = -65;

		        char a2 = 65;
		        //char b2 = -66;

		        System.out.println((char)a); // @@ A
		        System.out.println((char)b); // 음수 값 표현이 불가능 @@ ?8
		        System.out.println(a2); // @@ A
		    }
		}
		```		
	- 유니코드를 표현하는 인코딩 방법
		+ UTF-8  
			: 각 문자마다 1~4 바이트를 사용 -> 메모리 낭비 방지 + 빠른 속도 / 인터넷 등에서 주로 사용
		+ UTF-16  
			: 모든 문자를 2 바이트로 표현함 -> 1 바이트로 표현할 수 있는 자료를 저장 시, 메모리 낭비 / 자바의 기본 인코딩 방식

* 실수 자료형
	- 부동 소수점 방식  
		: 가수 부분과 지수 부분을 나누어서 실수를 나타냄  
		+ 가수 < 밑수  
		![image](https://user-images.githubusercontent.com/104348646/193409753-9bc7a3cf-ea32-42f5-8b16-00749f0d33ab.png)  
		+ 정규화  
			: 가수를 밑수보다 작은 한 자리짜리 가수로 표현하는 것
			+ 컴퓨터는 숫자를 내부적으로 표현할 때 2^n으로 표현 -> 밑수 = 2
			+ 1.m x 2^n

	- float형
		+ 4 바이트(32비트): 부호 1비트, 지수부 8비트, 가수부 23비트

	- double형
		+ 8 바이트(64비트): 부호 1비트, 지수부 11비트, 가수부 52비트
		+ 자바에서 실수의 기본형
		+ float형보다 더 정밀한 실수 표현이 가능
		```
		# float형과 double형
		public class DoubleEx1 {
		    public static void main(String[] args){
		        double dnum = 3.14;
		        float fnum = 3.14F; //F: 식별자 (double형이 기본형)

		        System.out.println(dnum);
		        System.out.println(fnum);
		    }
		}
		```
		+ 부동 소수점 방식의 오류
		```
		public class DoubleEx2 {
		    public static void main(String[] args){
		        double dnum = 1;

		        for(int i = 0; i < 10000; i++){
		            dnum = dnum + 0.1; // 0.1을 10000번 더함
		        }
		        System.out.println(dnum); // 예상값: 1001 @@ 1001.000000000159 -> 약간의 오차가 발생 -> 감수
		    }
		}
		```

* 논리 자료형  
	: 어떤 변수의 참, 거짓의 값을 나타냄
	+ boolean형
		+ 1 바이트 <- 값: true, false
	```
	boolean isMarried; // boolean형 선언
	```
	```
	public class BooleanEx {
	    public static void main(String[] args){
	        boolean isMarried = true; // boolean 변수를 선언 + 초기화
	        System.out.println(isMarried); //@@ true
	    }
	}
	```

* 자료형 없이 변수 선언하기
	- JAVA 10부터는 지역 변수 자료형 추론(local variable type inference)이 가능
	- 자료형 대신 var을 사용
	- 변수에 대입되는 자료를 보고 컴파일러가 추측함
	```
	var num = 10; // int로 컴파일
	var dNum = 10.0; // double로 컴파일
	var str = "hello"; // string으로 컴파일
	```

	- 한 번 선언한 자료형 변수를 다른 자료형으로 사용할 수 없음
		+ ex) str = "test"; str = 3; //error
	- 지역변수만 가능
		+ 지역변수: 프로그램의 {} 내에서 사용할 수 있는 변수

## 02-4 상수와 리터럴
* 상수 선언하기
    - 상수  
        : 항상 변하지 않는 값
    - final 예약어를 사용

    ```
    public class Constant {
        public static void main(String[] args){
            final int MAX_NUM = 100; // 선언 + 초기화
            final int MIN_NUM; // 선언

            MIN_NUM = 0; // 사용하기 전에 초기화 / 초기화 하지 않으면 error

            System.out.println(MAX_NUM);
            System.out.println(MIN_NUM);
            // MAX_NUM = 1000; // error: 상수는 값 변경 불가
        }
    }
    ```
* 상수를 사용하면 편리한 이유
    - 프로그램 내부에서 반복적으로 사용하고, 변하지 않아야 하는 값에 사용
    ```
    ## 값을 코드에 직접 사용
    if(count == 30) {...}
    while(i < 30) {...}

    ## 값을 상수로 선언하여 사용
    final int MAX_STUDENT_NUM = 35;
    if(count == MAX_STUDENT_NUM) {...}
    while(i < MAX_STUDENT_NUM) {...}
    ```

* 리터럴(literal)  
    : 프로그램에서 사용하는 모든 숫자, 문자, 논리값
    + 리터럴 or 리터럴 상수: 문자, 숫자를 일컫는 말
    + 리터럴 메모리 과정
        + 프로그램이 시작할 때, 리터럴이 시스템에 같이 로딩됨
        + 특정 메모리 공간인 상수 풀(constant pool)에 있음
        + 필요한 경우, 상수 풀에서 가져와서 사용
        + 상수 풀에 저장할 때 정수는 int, 실수는 double로 저장됨 -> long, float 값으로 저장해야하는 경우엔 식별자(L, l, F, f)를 명시해야 함  
    ![image](https://user-images.githubusercontent.com/104348646/193409777-1c3ef7cc-6988-4d56-86ff-46d1d2ebb784.png)  

## 02-5 형 변환
* 형 변환(type conversion)  
	: 각 변수의 자료형이 다를 때 자료형을 같게 바꾸는 것
	- 규칙
		+ 바이트 크기가 작은 자료형에서 큰 자료형으로 형 변환은 자동으로 이루어진다
		+ 덜 정밀한 자료형에서 더 정밀한 자료형으로 형 변환은 자동으로 이루어진다  
		![image](https://user-images.githubusercontent.com/104348646/193444872-33c13c13-92d3-47c5-9ed9-e5e441fab76e.png)  

* 묵시적 형 변환
	- 바이트 크기가 작은 자료형에서 큰 자료형으로 대입하는 경우
	```
	byte bNum = 10;
	int iNum = bNum; // byte형 변수를 int형 변수에 대입함 -> 자료 손실 없음: 4바이트에서 1바이트를 저장 + 남은 3바이트는 0으로 채워짐
	```
	- 덜 정밀한 자료형에서 더 정밀한 자료형으로 대입하는 경우
	```
	int iNum2 = 20;
	float fNum = iNum2; // 바이트는 같지만 float형이 더 정밀하게 데이터 표현 가능
	```
	- 연산 중에 자동 변환되는 경우
	```
	int iNum = 20;
	float fNum = iNum;
	double dNum;
	dNum = fNum + iNum; // int형이 float형으로 변환 -> 더한 값이 double형(dNum의 자료형)으로 변환됨
	```

* 명시적 형 변환  
	<-> 묵시적 형 변환
	- 바이트 크기가 큰 자료형에서 작은 자료형으로 대입하는 경우
	```
	int iNum = 10;
	byte bNum = (byte)iNum; // 변환할 자료형을 명시
	
	int iNum = 1000;
	byte bNum = (byte)iNum; // byte형의 범위를 초과함 -> 자료 손실 발생 @@-24
	```
	- 더 정밀한 자료형에서 덜 정밀한 자료형으로 대입하는 경우
	```
	double dNum = 3.14;
	int iNum2 = (int)dNum; // 정수 부분만 출력됨 @@ 3
	```
	- 연산 중 형 변환
	```
	public class Conversion {
	    public static void main(String[] args){
	        double dNum1 = 1.2;
	        float fNum2 = 0.9F;
	
	        int iNum3 = (int)dNum1 + (int)fNum2; // 두 실수가 각각 형 변환되어 더해짐
	        int iNum4 = (int)(dNum1 + fNum2); // 두 실수의 합이 먼저 계산되고 형 변환됨
	
	        System.out.println(iNum3); // 1(int) + 0(int) = 1 @@ 1
	        System.out.println(iNum4); // (int)2.1 @@ 2
	    }
	}
	```
