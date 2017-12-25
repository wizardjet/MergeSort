import java.util.Scanner;

public class MergeSort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter problem: ");
        String input = sc.next();
        int[] problem = stringToIntArray(input);
        System.out.print("problem: ");
        print(problem);
        int[] result = mergeSort(problem);
        System.out.println("-------------------------------------------------");
        System.out.print("problem: ");
        print(problem);
        System.out.print("final result: ");
        print(result);
        System.out.println("-------------------------------------------------");
    }

    static int[] mergeSort(int[] array) {
        if (array.length == 1) {
            return array;
        }
        int[][] partition = betterPartition(array);
        return merge(mergeSort(partition[0]), mergeSort(partition[1]));
    }

    static int[] merge(int[] array1, int[] array2) {
        int[] sortedArray = new int[array1.length + array2.length];
        int index1 = 0;
        int index2 = 0;
        int indexSorted = -1;
        System.out.print("merge: ");
        print(array1, array2);
        while (indexSorted < sortedArray.length - 1) {
            if (index1 < array1.length) { //array 1 iteration incomplete
                if (index2 < array2.length) { //array 2 iteration incomplete
                    if (array1[index1] < array2[index2]) {
                        sortedArray[++indexSorted] = array1[index1++];
                    } else {
                        sortedArray[++indexSorted] = array2[index2++];
                    }
                } else { //array 2 iteration complete, array 1 incomplete
                    sortedArray[++indexSorted] = array1[index1++];
                }
            } else { //array 1 iteration complete, array 2 incomplete
                sortedArray[++indexSorted] = array2[index2++];
            }
        }
        System.out.print("result: ");
        print(sortedArray);
        return sortedArray;
    }

    static int[][] betterPartition(int[] array) {
        int[] arrayLeft = new int[array.length / 2];
        for (int i = 0; i < array.length / 2; i++) {
            arrayLeft[i] = array[i];
        }
        int step = array.length / 2;
        int limit = array.length / 2;
        if (!isEven(array.length)) {
            limit = array.length / 2 + 1;
        }
        int[] arrayRight = new int[limit];
        for (int i = 0; i < limit; i++) {
            arrayRight[i] = array[i + step];
        }
        System.out.print("split: ");
        print(arrayLeft, arrayRight);
        return new int[][]{arrayLeft, arrayRight};
    }

    static int[][] partition(int[] array) {
        return new int[][]{copyArray(array, "L"), copyArray(array, "R")};
    }

    static int[] copyArray(int[] array, String side) {
        int step = 0;
        int limit = array.length / 2;
        switch (side) {
            case "L":
                step = 0;
                System.out.print("split L: ");
                break;
            case "R":
                step = array.length / 2;
                if (!isEven(array.length)) {
                    limit = array.length / 2 + 1;
                }
                System.out.print("split R: ");
                break;
        }
        int[] arrayCopy = new int[limit];
        for (int i = 0; i < limit; i++) {
            arrayCopy[i] = array[i + step];
        }
        print(arrayCopy);
        return arrayCopy;
    }

    static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }

    static void print(int[] array1, int[] array2) {
        for (int i = 0; i < array1.length; i++) {
            System.out.print(array1[i]);
        }
        System.out.print(",");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i]);
        }
        System.out.println();
    }

    static int[] stringToIntArray(String s) {
        int[] array = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            array[i] = Character.getNumericValue(s.charAt(i));
        }
        return array;
    }

    static boolean isEven(int n) {
        return n % 2 == 0;
    }

}