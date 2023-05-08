## 04-1 조건문  
* 조건문  
	: 주어진 조건에 따라 다른 문장을 선택할 수 있도록 프로그래밍 하는 것

* if문과 else문
    ```
    if(조건문){
      수행문; // 조건식이 참인 경우에만 수행
    }
    ```
    ```
    if(조건식){
      수행문1; // 조건식이 참인 경우에 수행
    }
    else{
      수행문2; // 조건식이 거짓인 경우에 수행
    }
    ```
    - 순서도(flow chart)  
        ![image](https://user-images.githubusercontent.com/104348646/193555388-3d27beb3-b70c-4924-a5dd-54ab91ad98a8.png)    
        + if문의 순서도  
            ![image](https://user-images.githubusercontent.com/104348646/193555466-7add4212-3b89-4b6f-9f50-59a540d0854d.png)  
        + ex)  
            ![image](https://user-images.githubusercontent.com/104348646/193555543-46956ff5-8fc6-42a4-96a7-abf052876510.png)   
            ```
            public class IfEx1 {
                public static void main(String[] args){
                    int age = 7;

                    if(age >= 8){
                        System.out.println("학교에 다닙니다");
                    }
                    else{
                        System.out.println("학교에 다니지 않습니다");
                    }
                }
            }
            ```

* if-else문  
    : 하나의 상황에 조건이 여러 개인 경우
    ```
    if(조건식1){
        수행문1; // 조건식1이 참인 경우에 수행
    }
    else if(조건식2){
        수행문2; // 조건식2이 참인 경우에 수행
    }
    else if(조건식3){
        수행문3; // 조건식3이 참인 경우에 수행
    }
    else{
        수행문4; // 위의 조건식이 모두 거짓인 경우에 수행
    }
    수행문5; // if-else문이 끝난 후 수행
    ```
    - ex)  
        ![image](https://user-images.githubusercontent.com/104348646/193555698-27f2de57-9860-48fe-84f1-93be56df9f27.png)  
        ```
        public class IfEx2 {
            public static void main(String[] args){
                int age = 9;
                int charge;

                if(age < 8){
                    charge = 1000;
                    System.out.println("미취학 아동입니다");
                }
                else if(age < 14){
                    charge = 2000;
                    System.out.println("초등학생입니다");
                }
                else if(age < 20){
                    charge = 2500;
                    System.out.println("중, 고등학생입니다");
                }
                else{
                    charge = 3000;
                    System.out.println("일반인입니다");
                }
                System.out.println("입장료는 " + charge + "원 입니다");
                // +: 여러 단어를 연결하여 출력 @@ "초등학생입니다" /n "입장료는 2000원 입니다"

            }
        }

        ```
* if-else문과 if-if문의 차이
```
public class IfEx3 {
    public static void main(String[] args){
        int age = 9;
        int charge;
        
        if(age < 8){
            charge = 1000;
            System.out.println("미취학 아동입니다");
        }
        else(age < 14){
            charge = 2000;
            System.out.println("초등학생입니다");
        }
        else(age < 20){
            charge = 2500;
            System.out.println("중, 고등학생입니다");
        }
        else{
            charge = 3000;
            System.out.println("일반인입니다");
        }
        System.out.println("입장료는 " + charge + "원 입니다");
        // if문의 조건마다 각각 비교함 @@ "초등학생입니다" /n "중, 고등학생입니다" /n "입장료는 2000원 입니다"
    }
}
```  

* 조건문과 조건 연산자
    - if-else문은 조건 연산자로도 구현 가능
        ```
        # 조건문
        if (a > b)
            max = a;
        else
            max = b;

        # 조건 연산자
        max = (a > b) ? a : b;
        ```

* switch-case문
    - 주로 조건이 하나의 변수 값이나 상수 값으로 구분되는 경우에 사용
    ```
    switch(조건){
        case 값1 : 수행문1;
            break;
        case 값1 : 수행문1;
            break;
        ...
        default : 수헹문 n;
    }
    ```
    ```
    ## if-else문
    if(rank == 1){
        color = 'G';
    }
    else if(rank == 2){
        color = 'S';
    }
    else if(rank == 3){
        color = 'B';
    }
    else{
        color = 'A';
    }

    ## switch-case문

    switch(rank){
        case 1: color = 'G'; // 조건: case 1: ... ~ break; 까지 / rank가 1인 case에 color에 G를 대입 + break
            break;
        case 2: color = 'S';
            break;
        case 3: color = 'B';
            break;
        default : color = 'A';
    }
    ```
    - case문을 동시에 사용하기
        + ex)
            ```
            # 월 별 일수 구하기 
            case 1 : case 3 : case 5 : case 5 : case 7 : case 8 : case 10 : case 12 : day = 31;
                break;
            case 4 : case 6 : case 9 : case 11 : day = 30;
                break;
            case 2: day = 28;
                break
            ```
    - break문의 역할
        : switch-case문의 수행을 멈추고 빠져나감
        + ex)
            ```
            public class IfEx4 {
                public static void main(String[] args){
                    int rank = 1;
                    char color;

                    switch(rank){
                        case 1: color = 'G'; // break 사용 시, color = 'G'
                        case 2: color = 'S';
                        case 3: color = 'B';
                        default : color = 'A';
                    }
                    System.out.println(rank + "등의 메달 색깔은 " + color + "입니다");
                    // @@ "1등의 메달 색깔은 A입니다" / 이어서 나오는 문장까지 모두 수행 -> 마지막 default문의 값이 대입됨
                }
            }
            ```

* case문에 문자열 사용하기
    - JAVA 7부터 switch-case문의 case 값에 정수 값뿐만 아니라 문자열도 사용 가능
        ```
        public class IfEx5 {
            public static void main(String[] args){
                String medal = "gold";

                switch(medal){
                    case "gold":
                        System.out.println(("금메달입니다"));
                        break;
                    case "silver":
                        System.out.println(("은메달입니다"));
                        break;
                    case "bronze":
                        System.out.println(("동메달입니다"));
                        break;
                    default:
                        System.out.println(("메달이 없습니다"));
                        break;
                }
            }
        }
        ```

## 04-2 반복문(loop)
* 반복문  
    : 반복되는 일을 처리하기 위해 사용

* while문
    : 조건식을 만족하는 동안 {} 안의 수행문을 반복해서 처리
    ```
    while(조건식){
        수행문1;
        ...
    }
        수행문2;
    ```
    ![image](https://user-images.githubusercontent.com/104348646/193553030-787cff6b-9c41-440d-9131-733a0c556ff1.png)  
    - ex)  
        ```
        public class WhileEx1 {
            public static void main(String[] args){
                int num = 1;
                int sum = 0;

                while(num <= 10){ // num <= 10인 동안
                    sum = sum + num; // sum에 num을 더함
                    num++; // num에 1씩 더함
                }
                System.out.println(sum); // 10번 반복 @@ 55
            }
        }
        ```
    - while문이 무한히 반복되는 경우
        ```
        while(true){
            ...
        }
        ```

* do-while문  
    : {} 안의 문장을 무조건 한 번 수행한 후에 조건식을 검사
    - {} 안의 문장을 반드시 한 번 이상 수행해야 할 때 사용
    ```
    do {
        수행문1;
        ...
    } while(조건식);
        수행문2;
        ...
    ```
    ![image](https://user-images.githubusercontent.com/104348646/193552899-9b38bb53-e8a5-471d-adf8-bbb40294ac05.png)  
    - ex)  
        ```
        public class DoWhileEx {
            public static void main(String[] args){
                int num = 1;
                int sum = 0;

                do {
                    sum += num; // 조건식이 참이 아니더라도 무조건 한 번 수행
                    num++;
                } while(num <= 10);
                System.out.println(sum); // 10번 반복 @@ 55
            }
        }
        ```

* for문  
    ```
    for(초기화식; 조건식; 증감식;){
        수행문;
    }
    ```
    - ex)
        ```
        int num;
        for(num = 1; num <=5; num++)
        // 1. num을 1로 초기화 / 2. num <= 5 조건식을 검사 / 3. 참이면, for문(System.out.println)을 수행 / 4. 증감식 num++을 수행(num의 값이 1 증가)
        {
            System.out.println(num); // num이 6이 되면 for문 종료 -> 6은 출력되지 않음
        }
        ```
    - while vs for
        ```
        ## while
        int num = 1; // 초기화
        int sum = 0;
        while(num <= 10){ // 조건 비교
            sum += num;
            num++; // 증감식
        }

        ## for
        int sum = 0;
        for(int num = 1; num <= 10; num++){ // 초기화 + 조건 비교 + 증감식
            sum += num;
        }
        ```
* for문 요소 생략하기
    : 코드가 중복되거나 사용할 필요가 없을 때, 생략 가능
    - 초기화식 생략
        ```
        int i = 0;
        for( ; i < 5; i++){
            ...
        }
        ```
    - 조건식 생략
        ```
        for(i = 0; i++){
            sum += i;
            if(sum > 200) break; // for문 안에 if문을 사용
        }
        ```
    - 증감식 생략
        ```
        for(i = 0; i < 5; ){
            ...
            i = (++i) % 10;
        }
        ```
    - 요소 모두 생략
        ```
        for( ; ; ){
            ...
        } // 무한 반복
        ```

* 중첩된 반복문  
    : 외부 반복 수행 이후, 내부 반복 수행
    - ex)
        ```
        public class LoopEx {
            public static void main(String[] args) {
                int dan;
                int times;

                for(dan = 2; dan <= 9; dan++){ // 2 ~ 9단까지 반복
                    for(times = 1; times <= 9; times++){
                        System.out.println(dan + "x" + times + "=" + dan * times);
                    }
                    System.out.println( ); // 단 사이에 한 줄을 띄워서 출력
                }
            }
        }
        ```

* 반복문의 쓰임
    - for: 반복 횟수가 정해진 경우
    - do-while문: 반드시 한 번 이상 수행해야 하는 경우
    - while: 조건의 참/거짓에 따라 수행하는 경우

* continue문  
    : 이후의 문장은 수행하지 않고 for문의 처음으로 돌아가 증감식을 수행
    - ex)
        ```
        public class ContinueEx {
            public static void main(String[] args) {
                int total = 0;
                int num;

                for(num = 1; num <= 100; num++){ // num이 1씩 증가해서 100이 될 때까지 반복
                    if(num % 2 == 0) // 사용할 문장이 1개임 -> {} 미사용 가능
                        continue; // num 값이 짝수인 경우, 뒤의 수행을 생략하고 num++만 수행
                    total += num;
                }
                System.out.println(total); // 1 ~ 100까지의 홀수 합 @@ 2500
            }
        }
        ```

* break문  
    : 반복문에서 break문은 그 지점에서 더 이상 수행문을 반복하지 않고 반복문을 빠져나옴
    - ex)
        ```
        # for 안의 조건문을 사용
        public class break1 {
            public static void main(String[] args) {
                int sum = 0;
                int num = 0;

                for(num = 0; sum < 100; num++){ // sum이 100보다 크면 종료
                    sum += num;
                }

                System.out.println("num: " + num); // 마지막으로 더한 수 = 14 <-> @@ num: 15
                System.out.println("sum: " + sum); // 1 ~ 14까지의 합 @@ sum: 105
            }
        }

        # break를 사용
        public class break2 {
            public static void main(String[] args) {
                int sum = 0;
                int num = 0;
                
                for(num = 0; ; num++){ // 조건 생략 -> break로 표현
                    sum += num;
                    if(sum >= 100)
                        break;
                }
                System.out.println("num: " + num); // 마지막으로 더한 수 @@ num: 14
                System.out.println("sum: " + sum); // @@ sum: 105
            }
        }
        ```
    - break문의 위치  
        ![image](https://user-images.githubusercontent.com/104348646/193552686-da447e23-4a09-4981-a422-0437ecec7f68.png)

























