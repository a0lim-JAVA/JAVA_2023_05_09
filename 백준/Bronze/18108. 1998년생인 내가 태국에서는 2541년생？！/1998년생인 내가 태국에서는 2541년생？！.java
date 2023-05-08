import java.util.Scanner;

public class Main {

    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int A = in.nextInt(); // input에서 공백 전까지만 받음

        System.out.println(A - 543);

        
        in.close();
    }
}