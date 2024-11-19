/**
 * @ Proj3.Java
 * @ This program tests the time used by various sorting algorithms on the Fast Food Nutrition dataset.
 * @ author: Destiny
 * @ date: Nov 4, 2024
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Proj3 {
    // Sorting Method declarations

       // Implementation of Merge Sort
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
// Handles the merging element of Merge sort
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
        // method finds a pivot by selecting the middle element
    static <T extends Comparable<? super T>>int findPivot( ArrayList<T> a, int i, int j)
    { return (i+j)/2; }

    // Implementation of quick sort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
            int pivotIndex = findPivot(a, left, right);  // Pick a pivot
            swap(a, pivotIndex, right);               // Stick pivot at end
            // k will be the first position in the right subarray
            int k = partition(a, left, right-1, a.get(right));
            swap(a, k, right);                        // Put pivot in place
            if ((k-left) > 1) { quickSort(a, left, k-1); }  // Sort left partition
            if ((right-k) > 1) { quickSort(a, k+1, right); }  // Sort right partition
    }

    // splits the array in middle
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

    // swaps 2 elements in an array
    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
        // finds left child in heap
    public static <T extends Comparable> int leftChild(int pos, ArrayList<T> a)
    { return 2 * pos + 1; }
        // build a heap of an array
    private static <T extends Comparable> void buildHeap(ArrayList<T> a, int left, int right) {
        int n = a.size();
        // Start heapifying from the last non-leaf node
        for (int i = (right - 1) / 2; i >= left; i--) {
            heapify(a, i, right);  // Build the heap by heapifying non-leaf nodes
        }
    }
        // Implementation of heapsort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        buildHeap(a, left, right);

        for (int i = right; i > left; i--) {

            swap(a, left, i);

            heapify(a, left, i - 1);
        }
    }
// percolates down to ensure that array maintains properties of a heap
    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
       int leftC = leftChild(left,a);
       int rightC = leftC +1;
        int n = a.size();
        int maxIndex = left;

        // iterate through left and right subtrees to ensure parent isn't smaller than child
        if (leftC <= right && a.get(leftC).compareTo(a.get(maxIndex)) > 0) {
            maxIndex = leftC;
        }
        if (rightC <= right && a.get(rightC).compareTo(a.get(maxIndex)) > 0) {
            maxIndex = rightC;
        }

        if(right < n && a.get(right).compareTo(a.get(maxIndex))>0){
            maxIndex = right;
        }
        // swap if appropiate
        if(maxIndex != left){
            swap(a,left,maxIndex);
            heapify(a,maxIndex,right);
         }
        }


    // Bubble Sort implementation
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

    // Odd-Even Transposition Sort implementation
    public static <T extends Comparable> int transpositionSort(ArrayList<T> array, int size) {
       boolean isSorted = false; // check if sorted
       boolean countedSwapAlready = false; // since it's parallel* make sure to count swaps only once
       int swaps = 0;
        while (!isSorted) {
            isSorted = true;
            countedSwapAlready = false;

            // Do bubble sort on even indices
            for (int i = 0; i < array.size() - 1; i += 2) {
                int adjIndex = i + 1;
                if (array.get(i).compareTo(array.get(adjIndex)) > 0) {
                    swap(array, i, adjIndex);
                    swaps++;
                    isSorted = false;
                    countedSwapAlready = true;
                }
            }

            // Do bubble sort at odd indices
            for (int i = 1; i < array.size() - 1; i += 2) {
                int adjIndex = i + 1;
                if (array.get(i).compareTo(array.get(adjIndex)) > 0) {
                    swap(array, i, i + 1);
                    isSorted = false;
                    if(!countedSwapAlready){
                        swaps++;
                    }
                }
            }
        }

        return swaps;
    }
    // fill an array with default Fast food nutrition
    public static void refillTempArray(ArrayList<FastFoodNutritionInfo> tempArray,ArrayList<FastFoodNutritionInfo> listToBeSorted){
        for(int i = 0; i < listToBeSorted.size(); i++){
            tempArray.add(new FastFoodNutritionInfo());
        }

    }

// Parse FFN data into an object
    public static FastFoodNutritionInfo miniParser(String data){
        String[] properData = data.split(",");

        // Extract the relevant data from the array
        String company = properData[0];
        String item = properData[1];
        Double calories = Double.parseDouble(properData[2]);
        Double totalFat = Double.parseDouble(properData[3]);
        Double carbs = Double.parseDouble(properData[4]);
        Double protein = Double.parseDouble(properData[5]);

        // Create and return a new FastFoodNutritionInfo object
        return new FastFoodNutritionInfo(company, item, totalFat, calories, carbs, protein);
    }
    // create a deep copy
    public static void copyFFNList(ArrayList<FastFoodNutritionInfo> newList, ArrayList<FastFoodNutritionInfo> ogList){
        for (FastFoodNutritionInfo item : ogList) {

            // copy each element
            newList.add(new FastFoodNutritionInfo(item));
        }
    }
    // fill a given list, with a specified amount of lines
    public static void listFiller(int numLines, Scanner inputFileNameScanner, ArrayList<FastFoodNutritionInfo> list){
        int linesRead = 0;
        while (inputFileNameScanner.hasNextLine() && linesRead < numLines) {
            String line = inputFileNameScanner.nextLine();
            FastFoodNutritionInfo info = miniParser(line); // Assuming this method parses a CSV line
            list.add(info); // Add to unsorted list
            linesRead++;
        }
    }
    // Take's a string and write is to a file and prints to a console
    public static void writeToFileAndPrint(String content) throws IOException {
        // create file variable
        File myFile = new File(analysisFilePath);

        // ensure it exists or create one
        if (!myFile.exists()) {
            try {
                myFile.createNewFile();
            } catch (IOException e) {

            }
        }
        System.out.println(content);

        FileWriter dataTyper = new FileWriter(myFile, true); // file writer
        dataTyper.write(content +"\n"); // write given string and new line

        dataTyper.close(); // close file to preserve data
    }
    // Overloaded method that writes a list to the sorted file without printing
    public static void writeToFileAndPrint(ArrayList<FastFoodNutritionInfo> list) throws IOException {
        // create file variable
        File myFile = new File(sortedFilePath);

        // ensure it exists or create one
        if (!myFile.exists()) {
            try {
                myFile.createNewFile();
            } catch (IOException e) {

            }
        }
        String content = String.valueOf(list);


        FileWriter dataTyper = new FileWriter(myFile, true); // file writer
        dataTyper.write(content +"\n"); // write given string and new line

        dataTyper.close(); // close file to preserve data
    }

    static String analysisFilePath = "src/analysis.txt"; // holds analysis file path
   static String sortedFilePath = "src/sorted.txt"; // holds sorted file path


    public static void main(String [] args)  throws IOException {
System.out.println("Recognized Sorting algorithms: bubble, transposition, heap, merge, quick");

        new PrintWriter(sortedFilePath).close();

        FastFoodNutritionInfo.readFastFoodData("C:\\Users\\desti\\Documents\\project-1-part-2-DDiscipulus\\src\\Edited(4)FFNData.csv");
        if (args.length != 3) {
            System.err.println("Argument count is invalid: " + args.length);
            System.out.println("Ensure algorithms are being typed like the following: bubble, merge, transposition, quick");
            System.exit(0);
        }

        // get command line info
        String filePath = args[0];

        FileInputStream inputFileNameStream = new FileInputStream(filePath);

        Scanner scanner = new Scanner(inputFileNameStream);


        // ignore first line
        scanner.nextLine();
        String sorter = args[1].toLowerCase();
        int lines = Integer.valueOf(args[2]);


        // make our lists
        ArrayList<FastFoodNutritionInfo> sortedList = new ArrayList<>();
        listFiller(lines, scanner, sortedList);
            Collections.sort(sortedList);
        ArrayList<FastFoodNutritionInfo> reversedList = new ArrayList<>();
        copyFFNList(reversedList,sortedList);
            Collections.reverse(reversedList);
        ArrayList<FastFoodNutritionInfo> shuffledList = new ArrayList<>();
        copyFFNList(shuffledList,sortedList);
            Collections.shuffle(shuffledList);

        boolean countSwaps = false; // tracks if we are concerned with swaps
        boolean isBubble = false; // tracks if its bubble sort

        // determines which sort it is
        // & sets bubble & transposition specific variables to true
        switch (sorter) {
            case "bubble":
                isBubble = true;
                countSwaps = true;
                break;
            case "transposition":
                countSwaps = true;
        }
        if (!countSwaps) {
            switch(sorter) {
                case "merge":
                    String messagePreTest = sorter + " Trial for " + lines + " lines";
                    writeToFileAndPrint(messagePreTest);

                    // sorted
                    ArrayList<FastFoodNutritionInfo> tempArray = new ArrayList<>(sortedList.size()); // temp array for merge
                    refillTempArray(tempArray,sortedList); // fill it with default FFN objects
                    long start1 = System.nanoTime(); // start time
                    mergeSort(sortedList,tempArray,0, sortedList.size()-1);
                    long end1 = System.nanoTime(); // end time

                    tempArray.clear();
                    refillTempArray(tempArray,sortedList);

                    // reversed
                    long start2 = System.nanoTime(); // start time
                    mergeSort(reversedList,tempArray,0, reversedList.size()-1);
                    long end2 = System.nanoTime(); // end time

                    tempArray.clear();
                    refillTempArray(tempArray,sortedList);

                    // shuffled
                    long start3 = System.nanoTime(); // start time
                    mergeSort(shuffledList,tempArray,0, shuffledList.size()-1);
                    long end3 = System.nanoTime(); // end time


                    writeToFileAndPrint("\t Sorted Time: " + (end1 - start1) / 1e9 + " sec");
                    writeToFileAndPrint( sortedList);
                    writeToFileAndPrint("\t Reverse Time: " + (end2 - start2) / 1e9 + " sec");
                    writeToFileAndPrint(reversedList);
                    writeToFileAndPrint("\t Shuffled Time: " + (end3 - start3) / 1e9 + " sec");
                    writeToFileAndPrint(shuffledList);
                    break;
                case "heap" :
                    messagePreTest = sorter + " Trial for " + lines + " lines";
                    writeToFileAndPrint(messagePreTest);
                    int rightIndex = sortedList.size()-1;

                    // sorted
                    start1 = System.nanoTime(); // start time
                    heapSort(sortedList,0,rightIndex);
                    end1 = System.nanoTime(); // end time


                    // reversed
                    start2 = System.nanoTime(); // start time
                    heapSort(reversedList,0,rightIndex);;
                    end2 = System.nanoTime(); // end time

                    // shuffled
                    start3 = System.nanoTime(); // start time
                    heapSort(shuffledList,0,rightIndex);
                    end3 = System.nanoTime(); // end time


                    writeToFileAndPrint("\t Sorted Time: " + (end1 - start1) / 1e9 + " sec");
                    writeToFileAndPrint( sortedList);
                    writeToFileAndPrint("\t Reverse Time: " + (end2 - start2) / 1e9 + " sec");
                    writeToFileAndPrint(reversedList);
                    writeToFileAndPrint("\t Shuffled Time: " + (end3 - start3) / 1e9 + " sec");
                    writeToFileAndPrint(shuffledList);

                    break;
                case "quick":
                    messagePreTest = sorter + " Trial for " + lines + " lines";
                    writeToFileAndPrint(messagePreTest);
                    rightIndex = sortedList.size()-1;

                    // sorted
                    start1 = System.nanoTime(); // start time
                    quickSort(sortedList,0,rightIndex);
                    end1 = System.nanoTime(); // end time


                    // reversed
                    start2 = System.nanoTime(); // start time
                    quickSort(reversedList,0,rightIndex);;
                    end2 = System.nanoTime(); // end time

                    // shuffled
                    start3 = System.nanoTime(); // start time
                    quickSort(shuffledList,0,rightIndex);
                    end3 = System.nanoTime(); // end time


                    writeToFileAndPrint("\t Sorted Time: " + (end1 - start1) / 1e9 + " sec");
                    writeToFileAndPrint( sortedList);
                    writeToFileAndPrint("\t Reverse Time: " + (end2 - start2) / 1e9 + " sec");
                    writeToFileAndPrint(reversedList);
                    writeToFileAndPrint("\t Shuffled Time: " + (end3 - start3) / 1e9 + " sec");
                    writeToFileAndPrint(shuffledList);

                default:
                    System.out.println("Unrecognized sort method, failed");
                    System.exit(0);
            }

        } else if (countSwaps && isBubble) {
            int bubbleSwaps1;
            int bubbleSwaps2;
            int bubbleSwaps3;
            // bubble sort times
            String messagePreTest = sorter + " Trial for " + lines + " lines";
            writeToFileAndPrint(messagePreTest);

            // sorted
            long start1 = System.nanoTime(); // start time
           bubbleSwaps1 = bubbleSort(sortedList, sortedList.size());
            long end1 = System.nanoTime(); // end time

            // reversed
            long start2 = System.nanoTime(); // start time
            bubbleSwaps2 = bubbleSort(reversedList, reversedList.size());
            long end2 = System.nanoTime(); // end time
            // shuffled
            long start3 = System.nanoTime(); // start time
            bubbleSwaps3 = bubbleSort(shuffledList, shuffledList.size());
            long end3 = System.nanoTime(); // end time

            writeToFileAndPrint("\t"+ bubbleSwaps1+  " swaps "+ "Sorted Time: " + (end1 - start1) / 1e9 + " sec");
                 writeToFileAndPrint( sortedList);
            writeToFileAndPrint("\t"+ bubbleSwaps2+  " swaps "+ "Reverse Time: " + (end2 - start2) / 1e9 + " sec");
                writeToFileAndPrint(reversedList);
            writeToFileAndPrint("\t"+ bubbleSwaps3+  " swaps Shuffled Time: " + (end3 - start3) / 1e9 + " sec");
                writeToFileAndPrint(shuffledList);


        } else{
            // transposition
            int bubbleSwaps1;
            int bubbleSwaps2;
            int bubbleSwaps3;
            // bubble sort times
            String messagePreTest = sorter + " Trial for " + lines + " lines";
            writeToFileAndPrint(messagePreTest);

            // sorted
            bubbleSwaps1 = transpositionSort(sortedList, sortedList.size());

            // reversed
            bubbleSwaps2 = transpositionSort(reversedList, reversedList.size());
            // shuffled
            bubbleSwaps3 = transpositionSort(shuffledList, shuffledList.size());

            writeToFileAndPrint("\t Sorted list "+ bubbleSwaps1+  " swaps ");
            writeToFileAndPrint( sortedList);
            writeToFileAndPrint("\t Reversed list "+ bubbleSwaps2+  " swaps ");
            writeToFileAndPrint(reversedList);
            writeToFileAndPrint("\t Shuffled List "+ bubbleSwaps3 + " swaps");
            writeToFileAndPrint(shuffledList);
        }
    }
}
