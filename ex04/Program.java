package ex04;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s;
        int[] arr;
        char[] charArr;
        int[] maxIndexes;
        int[] maxSymbols;

        arr = new int[65535];
        maxIndexes = new int[10];
        maxSymbols = new int[10];

        s = sc.nextLine();
        charArr = s.toCharArray();

        if (s.length() == 0) {
            System.out.println("No symbols were entered");
            System.exit(0);
        }

        for (int i = 0; i < s.length(); ++i) {
            arr[charArr[i]]++;
        }

        findMaxIndexes(arr, maxIndexes, maxSymbols);
        sortArrays(maxIndexes, maxSymbols);
        printGraph(maxIndexes, maxSymbols);

    }

    private static void printGraph(int[] maxIndexes, int[] maxSymbols) {
        int maxAmount;
        int[] sizeGraph;
        String[][] arr;

        maxAmount = countAmountOfColumns(maxIndexes);
        arr = new String[maxAmount + 2][maxAmount * 2];
        fillArray(arr, maxAmount);

        sizeGraph = new int[maxAmount];
        fillSizeGraph(sizeGraph, maxAmount, maxIndexes);

        for (int i = 0; i < maxAmount * 2; ++i) {
            if (i % 2 == 0) {
                int maxAmountCopy;

                maxAmountCopy = maxAmount;
                arr[maxAmount + 1][i] += (char)(maxSymbols[i / 2]);
                for (int g = sizeGraph[i / 2]; g > 0; --g) {
                    arr[maxAmountCopy][i] = "#";
                    maxAmountCopy--;
                }
                arr[maxAmountCopy][i] = convertToString(maxIndexes[i / 2]);
            } else {
                for (int n = 0; n < maxAmount + 2; ++n) {
                    if (maxIndexes[i / 2] > 10 && n == maxAmount - sizeGraph[i / 2]) {
                        arr[n][i] = "  ";
                    } else {
                        arr[n][i] = "   ";
                    }
                }
            }
        }

        for (int i = 0; i < maxAmount + 2; ++i) {
            for (int g = 0; g < maxAmount * 2; ++g) {
                System.out.print(arr[i][g]);
            }
            System.out.println();
        }
    }

    private static String convertToString(int num) {
        String result;
        String inverse;
        char[] arr;

        result = "";
        inverse = "";
        for (; num > 0; num /= 10) {
            result += (char)(num % 10 + '0');
        }

        arr = result.toCharArray();
        for (int i = arr.length - 1; i >= 0; --i) {
            inverse += arr[i];
        }

        return inverse;
    }

    private static void fillArray(String[][] arr, int maxAmount) {
        for (int i = 0; i < maxAmount + 2; ++i) {
            for (int g = 0; g < maxAmount * 2; ++g) {
                arr[i][g] = "";
            }
        }
    }

    private static int scaleSize(int maxAmount, int maxNum, int numScale) {
        return maxAmount * numScale / maxNum;
    }

    private static void fillSizeGraph(int[] sizeGraph, int maxAmount, int[] maxIndexes) {
        for (int i = 0; i < maxAmount; ++i) {
            sizeGraph[i] = scaleSize(maxAmount, maxIndexes[0], maxIndexes[i]);
        }
    }

    private static int countAmountOfColumns(int[] maxIndexes) {
        int amount;

        amount = 0;

        for (int maxIndex : maxIndexes) {
            if (maxIndex > 0) {
                amount++;
            }
        }
        return amount;
    }


    private static void sortArrays(int[] maxIndexes, int[] maxSymbols) {
        int maxIdx;
        int temp;
        int tempSymbol;

        for (int i = 0; i < maxIndexes.length; ++i) {
            maxIdx = findMinIdx(maxIndexes, maxSymbols, i);

            temp = maxIndexes[i];
            tempSymbol = maxSymbols[i];

            maxIndexes[i] = maxIndexes[maxIdx];
            maxSymbols[i] = maxSymbols[maxIdx];

            maxIndexes[maxIdx] = temp;
            maxSymbols[maxIdx] = tempSymbol;
        }
    }

    private static int findMinIdx(int[] maxIndexes, int[] maxSymbols, int i) {
        int maxIdx;
        int maxSymbol;
        int idx;

        maxIdx = maxIndexes[i];
        maxSymbol = maxSymbols[i];
        idx = i;

        while (i < maxIndexes.length) {
            if (maxIdx < maxIndexes[i] || (maxIdx == maxIndexes[i] && maxSymbols[i] < maxSymbol)) {
                maxIdx = maxIndexes[i];
                maxSymbol = maxSymbols[i];
                idx = i;
            }
            ++i;
        }

        return idx;
    }

    private static void findMaxIndexes(int[] arr, int[] maxIndexes, int[] maxSymbols) {
        int minNumIdx;

        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] > 0) {
                minNumIdx = findMinNumInArr(maxIndexes, maxSymbols, i, arr);
                if (minNumIdx != -1 && maxIndexes[minNumIdx] <= arr[i]) {
                    maxIndexes[minNumIdx] = arr[i];
                    maxSymbols[minNumIdx] = i;
                }
            }
        }
    }

    private static int findMinNumInArr(int[] indexes, int[] symbols, int arrIdx, int[] arr) {
        int min;
        int minSymbol;
        int idx;

        idx = 0;
        min = indexes[0];
        minSymbol = symbols[0];

        for (int i = 0; i < indexes.length; ++i) {
            if (indexes[i] == 0) {
                return i;
            }
            if ((min == indexes[i] && minSymbol < symbols[i]) || (min > indexes[i])) {
                idx = i;
                min = indexes[i];
                minSymbol = symbols[i];
            }
        }

        if ((arr[arrIdx] == min && minSymbol != arrIdx) || arr[arrIdx] > min) {
            if (arrIdx > minSymbol) {
                return idx;
            }
        }
        return -1;
    }

}
