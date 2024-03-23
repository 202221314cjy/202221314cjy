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

    public static <T> void reverse(T [] array) {
        int i = 0;
        while (i < array.length/2) {
            swap(array,i,array.length-i-1);
            i++;
        }
    }

    public static <X extends Comparable<X>> int binarySearch(X key, X[] arr, int fromIndex, int toIndex) {
        while (fromIndex <= toIndex) {
            int mid = fromIndex + (toIndex - fromIndex) / 2;

            if (arr[mid].compareTo(key) == 0) {
                return mid;//If the intermediate element happens to be the element to be searched for, return the index directly
            } else if (arr[mid].compareTo(key) < 0) {
                fromIndex = mid + 1;//If the intermediate element is smaller than the element to be searched for, update the starting index
            } else {
                toIndex = mid - 1;//If the intermediate element is larger than the element to be searched for, update the end index
            }
        }
        return -1;//If no element is found throughout the loop, return -1
    }
    public static <E extends Comparable<E>> void fastSort(E [] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E [] array, int begin, int end) {
        if (begin < end){
            int x = partition(array,begin,end);
            quickSort(array, begin, x-1);
            quickSort(array, x+1, end);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int low, int high) {
        int pivotIndex = low + (high - low) / 2;//Select the element in the middle position as the hub element
        E pivotValue = arr[pivotIndex];

        int i = low - 1;
        int j = high + 1;

        while (true) {//Find the first value greater than or equal to the hub element from left to right
            do {
                i++;
            } while (arr[i].compareTo(pivotValue) < 0);//Find the first value less than or equal to the hub element from right to left

            do {
                j--;
            } while (arr[j].compareTo(pivotValue) > 0);//If i and j meet or intersect, the partition ends

            if (i >= j) {
                return j;
            }

            swap(arr, i, j);
        }
    }
    //Generic methods
    public static <T> void swap(T[] array,int i,int j){
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}