package utils;

import java.util.Arrays;
import java.util.List;

public class util {
    public static void main(String[] args) {
        // Loop for each
        List<String> items = Arrays.asList("item1", "item2", "item3");
        for (String item : items) {
            System.out.println(item);
        }

        // Sorting
        int[] array = {5, 2, 8, 1, 9};

        System.out.println("Before sorting:");
        printArray(array);

        bubbleSort(array);

        System.out.println("After sorting:");
        printArray(array);
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}