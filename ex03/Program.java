package ex03;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        int grade;
        int week;
        long results;
        long num;

        Scanner sc = new Scanner(System.in);
        week = 1;
        num = 1;
        results = 0;

        while (true) {
            grade = scanData(week, sc);
            if (grade == 0) {
                break;
            }
            results += grade * num;
            num *= 10;
            ++week;
        }
        writeGradeResults(results);
    }

    private static int scanData(int week, Scanner sc) {
        int grade;
        int weekNum;
        String word;

        grade = 10;
        word = sc.next();
        if (word.equals("42")) {
            return 0;
        }
        if (!word.equals("Week")) {
            exitError();
        }
        if (!sc.hasNextInt()) {
            exitError();
        }
        weekNum = sc.nextInt();
        if (weekNum < 0 || weekNum > 18 || weekNum != week) {
            exitError();
        }

        for (int i = 0; i < 5; ++i) {
            int scannedNum;

            if (!sc.hasNextInt()) {
                exitError();
            }
            scannedNum = sc.nextInt();
            if (scannedNum < 1 || scannedNum > 9) {
                exitError();
            }
            if (scannedNum < grade) {
                grade = scannedNum;
            }
        }
        return grade;
    }

    private static void exitError() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    private static void writeGradeResults(long results) {
        int weeks;

        weeks = 0;

        for (long i = results; i > 0; i /= 10) {
            weeks++;
            System.out.print("Week " + weeks + " ");
            for (int n = 0; n < i % 10; ++n) {
                System.out.print('=');
            }
            System.out.println('>');
        }
    }

}
