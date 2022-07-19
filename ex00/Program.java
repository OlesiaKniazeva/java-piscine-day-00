package ex00;

public class Program {

    public static void main(String[] args) {
        int number = 678677;

        System.out.println("Сумма цифр числа " + number + " = " + sumOfDigits(number));
    }

    private static int sumOfDigits(int num) {
        if (num == 0) {
            return 0;
        }
        return num % 10 + sumOfDigits(num / 10);
    }
}
