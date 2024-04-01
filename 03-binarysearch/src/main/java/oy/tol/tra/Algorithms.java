package oy.tol.tra;

public class Algorithms {
    public static <T extends Comparable<T>> void sort(T[] array) {
        int i;
        int j;
        for (i = 0; i < array.length - 1; i++) {
            for (j = 0; j < array.length - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    public static <T> void reverse(T[] array) {
        int i = 0;
        while (i < array.length / 2) {
            swap(array, i, array.length - i - 1);
            i++;
        }
    }

    public static <X extends Comparable<X>> int binarySearch(X key, X[] arr, int fromIndex, int toIndex) {
        while (fromIndex <= toIndex) {
            int mid = fromIndex + (toIndex - fromIndex) / 2;

            if (arr[mid].compareTo(key) == 0) {//Found key value, return its index
                return mid;
            } else if (arr[mid].compareTo(key) < 0) {
                fromIndex = mid + 1;//If the intermediate element is smaller than the key value, continue searching in the right half of the array
            } else {//If the intermediate element is greater than the key value, continue searching in the left half of the array
                toIndex = mid - 1;
            }
        }//Key value does not exist in the array, return -1
        return -1;
    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        if (begin < end) {
            int p = partition(array, begin, end);
            quickSort(array, begin, p);
            quickSort(array, p + 1, end);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
        E pivot = array[begin];
        //Initialize two pointers i and j. I moves backwards from the position before the start of the array, and j moves forward from the position after the end of the array.
        int i = begin - 1;
        int j = end + 1;
        //Infinite loop until all pairs of elements that need to be swapped are found
        while (true) {
            //Move i until an element greater than or equal to pivot is found.
            do {
                i++;
            } while (array[i].compareTo(pivot) < 0);
            //Move j until an element less than or equal to pivot is found.
            do {
                j--;
            } while (array[j].compareTo(pivot) > 0);
            //If i>=j, it indicates that all pairs of elements that need to be exchanged have been processed, ending the loop.
            if (i >= j) {
                return j;
            }

            // Swap elements at indices i and j
            swap(array, i, j );
        }
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}