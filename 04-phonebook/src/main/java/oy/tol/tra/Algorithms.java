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


    public static <T> int partitionByRule(T [] pairs,int count,Predicate<T> judge){
        int l = 0;
        int r = count - 1;

        while (l <= r) {
            //Move the left pointer until an element that does not meet the condition is found
            while (l <= r && !judge.test(pairs[l])) {
                l++;
            }
            //Move the right pointer until an element that meets the condition is found
            while (r >= l && judge.test(pairs[r])) {
                r--;
            }
            //Exchange elements that satisfy and those that do not
            if (l < r) {
                swap(pairs, l, r);
                l++;
                r--;
            }
        }
        return l;//Return Left Pointer Position

    }
    private static <T extends Comparable<T>> int partition(T [] array, int begin, int end) {
        int l=begin;
        int r=end;
        T p=array[begin];
        //Loop until the left and right pointers meet
        while(l!=r){
            //Starting from the right side, find the position of the first element smaller than the baseline element
            while ((l<r)&&array[r].compareTo(p)>0) {
                r--;
            }
            //Starting from the left, find the position of the first element that is greater than or equal to the baseline element
            while ((l<r)&&array[l].compareTo(p)<=0) {
                l++;
            }
            //If the left and right pointers have not yet met, swap the positions of the two elements
            if(l<r){
                swap(array, l, r);
            }
        }
        //Place the reference element in its final position
        array[begin]=array[l];
        array[l]=p;
        return l;
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