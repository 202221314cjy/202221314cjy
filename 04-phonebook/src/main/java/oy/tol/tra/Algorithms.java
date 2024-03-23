package oy.tol.tra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Algorithms {
    private Integer [] array = null;
    /**
     * The method to reverse the internal Java int array.
     */
    public static <T extends Comparable<T>> void reverse(T[] array) {
        int length = array.length;
        for(int i = 0; i < length/2; i++){
            T temp = array[i];
            array[i] = array[length-1-i];
            array[length-1-i] = temp;
        }
    }

    /**
     * Sorts the array to ascending order.
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
        int length = array.length;
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length-1-i; j++){
                T x1 = array[j];
                T x2 = array[j+1];
                if(x1.compareTo(x2) > 0){
                    array[j+1] = x1;
                    array[j] = x2;
                }
            }
        }
    }


    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
        // TODO: you will implement this in step 2 below.
        if (aValue.compareTo(fromArray[fromIndex]) < 0 || aValue.compareTo(fromArray[toIndex]) > 0) {
            return -1;
        }
        // 找中间值
        int mid = (fromIndex + toIndex) / 2;

        if (fromArray[mid].equals(aValue)) {
            return mid;
        } else if (fromArray[mid].compareTo(aValue) > 0) {
            //如果中间值大于要找的值则从左边一半继续递归
            return binarySearch(aValue, fromArray, fromIndex, mid-1);
        } else {
            //如果中间值小于要找的值则从右边一半继续递归
            return binarySearch(aValue, fromArray, mid + 1, toIndex);
        }
    }

    //QuickSort
    public static <E extends Comparable<E>> void fastSort(E [] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E [] array, int begin, int end) {
        if(begin>=end){
            return;
        }
        int pivot=partition(array, begin, end);
        quickSort(array, begin, pivot-1);
        quickSort(array, pivot+1, end);
    }
    private static <E extends Comparable<E>> int partition(E [] array, int begin, int end) {
        E p=array[begin];
        int left=begin;
        int right=end;
        while(left!=right){
            while ((left<right)&&array[right].compareTo(p)>0) {
                right--;
            }
            while ((left<right)&&array[left].compareTo(p)<=0) {
                left++;
            }
            if(left<right){
                E tmp = array[left];
                array[left]=array[right];
                array[right] = tmp;
            }
        }
        array[begin]=array[left];
        array[left]=p;
        return left;
    }


    public static <T> int partitionByRule(T [] pairs,int count,Predicate<T> judgeNullPredicate){
        int left = 0;
        int right = count - 1;

        while (left <= right) {
            while (left <= right && !judgeNullPredicate.test(pairs[left])) {
                left++;
            }

            while (left <= right && judgeNullPredicate.test(pairs[right])) {
                right--;
            }

            if (left < right) {
                T tmp = pairs[left];
                pairs[left]=pairs[right];
                pairs[right] = tmp;
                left++;
                right--;
            }
        }
        return left;

    }
    //TODO: this is for a test,need to be written by youself
    public static <T> void sortWithComparator( T[] array, Comparator<? super T> comparator) {
        Arrays.sort(array, comparator);
    }
}