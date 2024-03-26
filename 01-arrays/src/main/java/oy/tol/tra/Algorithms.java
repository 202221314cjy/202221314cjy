package oy.tol.tra;


public class Algorithms {
/*Invert array*/
    public static <E> void reverse(E[] arr) {
        int start = 0;//Reverse starting position
        int end = arr.length - 1;//Reverse end Position
        while (start < end) {
            method1(arr, start, end);//Using method2 for element exchange
            start++;//Update starting position
            end--;//Update end position
        }
    }

    private static <E> void method1(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static <E extends Comparable<E>> void sort(E[] items) {
        for (int x = 0; x < items.length - 1; x++) {
            for (int y = 0; y < items.length - 1 - x; y++) {
                if (items[y].compareTo(items[y + 1]) > 0) {//If the previous element is greater than the next element, swap
                   method1(items, y, y + 1);//Using method1 for element exchange
                }
            }
        }
    }


}