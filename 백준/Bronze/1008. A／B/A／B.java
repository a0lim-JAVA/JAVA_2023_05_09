import java.util.Scanner;

public class Main{
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        double A = s.nextInt();
        double B = s.nextInt();
        
        System.out.println(A / B);
        
        s.close();
    }
}