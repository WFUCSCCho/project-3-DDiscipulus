import java.io.IOException;
import java.util.ArrayList;

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort
    public static <T extends Comparable> void mergeSort(ArrayList<T> a,  ArrayList<T> temp, int left, int right) {
        if (left == right) {
            return;
        }       // List has one record
        int mid = (left + right) / 2;          // Select midpoint
        mergeSort(a, temp, left, mid);     // Mergesort first half
        mergeSort(a, temp, mid + 1, right);  // Mergesort second half
        for (int i = left; i <= right; i++) {    // Copy subarray to temp
            temp.set(i, a.get(i));
        }
        // Do the merge operation back to A
        int i1 = left;
        int i2 = mid + 1;
        for (int curr = left; curr <= right; curr++) {
            if (i1 == mid + 1) {                 // Left sublist exhausted
                a.set(curr, temp.get(i2++));
            } else if (i2 > right) {             // Right sublist exhausted
                a.set(curr, temp.get(i1++));
            } else if (temp.get(i1).compareTo(temp.get(i2)) <= 0) {  // Get smaller value
                a.set(curr, temp.get(i1++));
            } else {
                a.set(curr, temp.get(i1++));
            }
        }
    }

    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // Finish Me
    }

    // Quick Sort
    static<T extends Comparable<? super T>> int findPivot( ArrayList<T> a, int i, int j)
    { return (i+j)/2; }
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
            int pivotIndex = findPivot(a, left, right);  // Pick a pivot
            swap(a, pivotIndex, right);               // Stick pivot at end
            // k will be the first position in the right subarray
            int k = partition(a, left, right-1, a.get(right));
            swap(a, k, right);                        // Put pivot in place
            if ((k-left) > 1) { quickSort(a, left, k-1); }  // Sort left partition
            if ((right-k) > 1) { quickSort(a, k+1, right); }  // Sort right partition
    }

    static <T extends Comparable<? super T>> int partition(ArrayList<T> array, int left, int right, T pivot) {
        // finished
        while (left <= right) { // Move bounds inward until they meet
            while (array.get(left).compareTo(pivot) < 0) { left++; }
            while ((right >= left) && (array.get(right).compareTo(pivot) >= 0)) { right--; }
            if (right > left) { swap(array, left, right); } // Swap out-of-place values
        }
        return left;            // Return first position in right partition
    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // The heap constructor invokes the buildheap method
        MaxHeap H = new MaxHeap(A, A.length, A.length);
        for (int i = 0; i < A.length; i++) {  // Now sort
            H.removemax(); // Removemax places max at end of heap
        }
    }

    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    // Bubble Sort
    public static <T extends Comparable> int bubbleSort(ArrayList<T> array, int size) {
        int swaps = 0;
        for (int i = 0; i < array.size() - 1; i++) { // Insert i'th record
            for (int j = 1; j < array.size() - i; j++) {
                if (array.get(j - 1).compareTo(array.get(j)) > 0) {
                    swap(array, j - 1, j);
                    swaps++;
                }
            }
        }
        return swaps;
    }

    // Odd-Even Transposition Sort
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        // Finish Me
    }

    public static void main(String [] args)  throws IOException {
        //...
        // Finish Me
        //...
    }
}
