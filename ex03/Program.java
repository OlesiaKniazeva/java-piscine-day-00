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
        int num;
        String word;

        String weekAndNum = sc.nextLine();
        Scanner weekNum = new Scanner(weekAndNum);

        grade = 10;
        word = weekNum.next();
        if (word.equals("42")) {
            return 0;
        }
        if (!word.equals("Week")) {
            exitError();
        }
        if (!weekNum.hasNextInt()) {
            exitError();
        }
        num = weekNum.nextInt();
        weekNum.close();
        if (num < 0 || num > 18 || num != week) {
            exitError();
        }

        String grades = sc.nextLine();
        Scanner s = new Scanner(grades);
        for (int i = 0; i < 5; ++i) {
            int scannedNum;

            if (!s.hasNextInt()) {
                s.close();
                exitError();
            }
            scannedNum = s.nextInt();
            if (scannedNum < 1 || scannedNum > 9) {
                s.close();
                exitError();
            }
            if (scannedNum < grade) {
                grade = scannedNum;
            }
        }
        s.close();
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
