package ex01;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        long num;

        num = inputLongNum();

        findIfNumberIsPrime(num);
    }

    private static long inputLongNum() {
        Scanner sc = new Scanner(System.in);

        while (!sc.hasNextLong()) {
            sc.next();
        }

        return sc.nextLong();
    }

    private static void findIfNumberIsPrime(long num) {
        long iter;

        iter = 1;

        if (num < 2) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        } else if (num == 2 || num == 3) {
            System.out.println("true 1");
            System.exit(0);
        } else if (num % 2 == 0) {
            System.out.println("false 1");
            System.exit(0);
        }

        for (long i = 3; i * i <= num; i += 2) {
            ++iter;
            if (num % i == 0) {
                System.out.println("false " + iter);
                System.exit(0);
            }
        }
        System.out.println("true " + iter);
    }

}


