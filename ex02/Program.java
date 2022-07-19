package ex02;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        int num;
        int sum;
        int countQueries;

        Scanner sc = new Scanner(System.in);

        countQueries = 0;
        while (true) {
            num = sc.nextInt();
            if (num == 42) {
                break;
            }
            sum = sumOfDigits(num);
            if (isPrime(sum)) {
                countQueries++;
            }
        }
        System.out.println("Count of coffee-request – " + countQueries);
    }

    private static int sumOfDigits(int num) {
        int sum;

        sum = 0;

        for (int i = num; i > 0; i /= 10) {
            sum += i % 10;
        }
        return sum;
    }

    private static boolean isPrime(int num) {
        if (num == 2 || num == 3) {
            return true;
        } else if (num % 2 == 0 || num < 2) {
            return false;
        }

        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
