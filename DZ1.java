// Написать программу вычисления n-ого треугольного числа.

import java.util.Scanner;


public class DZ1 {
    public static void main(String[] args) {
           
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число n: ");
        int n = in.nextInt();
                
        System.out.printf("Треугольное число для " + n + " равно: " + (n*(n+1))/2);
        in.close();
    }
}
