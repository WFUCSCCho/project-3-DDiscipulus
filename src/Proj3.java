import java.io.IOException;
import java.io.PrintWriter;
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
        merge(a,temp,left,mid,right);
    }

    public static <T extends Comparable> void merge(ArrayList<T> a,ArrayList<T> temp, int left, int mid, int right) {
        int i1 = left;
        int i2 = mid + 1;
        for (int curr = left; curr <= right; curr++) {
            if (i1 > mid) {                 // Left sublist exhausted
                a.set(curr, temp.get(i2++));
            } else if (i2 > right) {             // Right sublist exhausted
                a.set(curr, temp.get(i1++));
            } else if (temp.get(i1).compareTo(temp.get(i2)) <= 0) {  // Get smaller value
                a.set(curr, temp.get(i1++));
            } else {
                a.set(curr, temp.get(i2++));
            }
        }
    }


    // Quick Sort
    static <T extends Comparable<? super T>>int findPivot( ArrayList<T> a, int i, int j)
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
            // Swap out-of-place values
            if (left < right) { swap(array, left, right);
                left++; right--;
            }
        }
        return left;            // Return first position in right partition
    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable> int leftChild(int pos, ArrayList<T> a)
    { return 2 * pos + 1; }
    private static <T extends Comparable> void buildHeap(ArrayList<T> a, int left, int right) {
        int n = a.size();
        // Start heapifying from the last non-leaf node
        for (int i = (right - 1) / 2; i >= left; i--) {
            heapify(a, i, right);  // Build the heap by heapifying non-leaf nodes
        }
    }
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        buildHeap(a, left, right);

        for (int i = right; i > left; i--) {

            swap(a, left, i);

            heapify(a, left, i - 1);
        }
    }

    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
       int leftC = leftChild(left,a);
       int rightC = leftC +1;
        int n = a.size();
        int maxIndex = left;

        if (leftC <= right && a.get(leftC).compareTo(a.get(maxIndex)) > 0) {
            maxIndex = leftC;
        }
        if (rightC <= right && a.get(rightC).compareTo(a.get(maxIndex)) > 0) {
            maxIndex = rightC;
        }
        if(right < n && a.get(right).compareTo(a.get(maxIndex))>0){
            maxIndex = right;
        }
        if(maxIndex != left){
            swap(a,left,maxIndex);
            heapify(a,maxIndex,right);
         }
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
    public static <T extends Comparable> int transpositionSort(ArrayList<T> array, int size) {
       boolean isSorted = false;
       int swaps = 0;
        while(!isSorted){
            isSorted = true;
            for(int i = 0; i < array.size(); i++) {
                int adjIndex = i + 1;
                if (adjIndex == array.size()) {
                    break;
                }
                if (i % 2 == 0) {
                    if (array.get(adjIndex).compareTo(array.get(i)) > 0) {
                        swap(array, adjIndex, i);
                        swaps++;
                    }
                }

            }
            for(int i = 0; i < array.size(); i++){
              int adjIndex = i+1;
              if(adjIndex == array.size()){
                  break;
              }
                if(i % 2 != 0){
                    if (array.get(adjIndex).compareTo(array.get(i)) > 0) {
                        swap(array, adjIndex, i);
                    }

                    }

                }


            }
        return swaps;
    }

    public static void main(String [] args)  throws IOException {
        FastFoodNutritionInfo.readFastFoodData("C:\\Users\\desti\\Documents\\project-1-part-2-DDiscipulus\\src\\Edited(4)FFNData.csv");
        if(args.length != 3){
            System.err.println("Argument count is invalid: " + args.length);
            System.out.println("Ensure algorithms are being typed like the following: bubble, merge, transposition, quick");
            System.exit(0);
        }
        String filePath = args[0];
        String sorter = args[1].toLowerCase(); // Normalize case for comparison
        int lines = Integer.valueOf(args[2]);

        System.out.println(sorter + lines);

    }
}
