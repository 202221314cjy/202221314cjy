package oy.tol.tra;

import java.util.Arrays;
import java.util.function.Predicate;

import java.util.Comparator;

public class Algorithms {
    //Generic methods

    public static <T> void reverse(T [] array){
        int l=0;
        int r=array.length-1;
        while(l<r){
            swap(array,l,r);
            l++;
            r--;
        }
    }
    public static <T extends Comparable<T>> void sort(T [] array){

        for (int i=0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                if(array[j].compareTo(array[j+1])>0){
                    swap(array, j, j+1);
                }
            }
        }
    }

    public static <E extends Comparable<E>> int binarySearch(E aValue, E[] fromArray, int fromIndex, int toIndex) {
        while (fromIndex <= toIndex) {
            int mid = (fromIndex + toIndex) / 2;//Calculate intermediate index


            int cmp = aValue.compareTo(fromArray[mid]);//Comparison with median
            if (cmp == 0) {
                return mid;//Find the target value and return the index
            } else if (cmp < 0) {
                toIndex = mid - 1;//Target value on the left, update end index
            } else {
                fromIndex = mid + 1;//Target value on the right, update starting index
            }
        }
        return -1;
    }

    //QuickSort
    public static <E extends Comparable<E>> void fastSort(E [] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> void quickSort(E [] array, int begin, int end) {
        if(begin>=end){
            return;
        }
        int p=partition(array, begin, end);
        quickSort(array, begin, p-1);
        quickSort(array, p+1, end);
    }
    private static <T extends Comparable<T>> int partition(T [] array, int begin, int end) {
        int left=begin;
        int right=end;
        T p=array[begin];

        while(left!=right){
            while ((left<right)&&array[right].compareTo(p)>0) {
                right--;
            }
            while ((left<right)&&array[left].compareTo(p)<=0) {
                left++;
            }
            if(left<right){
                swap(array, left, right);
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
            //Move the left pointer until an element that does not meet the condition is found
            while (left <= right && !judgeNullPredicate.test(pairs[left])) {
                left++;
            }
            //Move the right pointer until an element that meets the condition is found
            while (right >= left && judgeNullPredicate.test(pairs[right])) {
                right--;
            }
            //Exchange elements that satisfy and those that do not
            if (left < right) {
                swap(pairs, left, right);
                left++;
                right--;
            }
        }
        return left;//Return Left Pointer Position

    }
    //TODO: this is for a test,need to be written by youself
    public static <T> void sortWithComparator( T[] array, Comparator<? super T> comparator) {
        Arrays.sort(array, comparator);
    }
    public static <E>void swap(E[] array,int m,int n){
        E tmp=array[m];
        array[m]=array[n];
        array[n]=tmp;
    }
}