package containers;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import static cn.org.kidding.util.Print.*;

/**
 * Compare the performance of Collections.sort() between
 * an ArrayList a LinkedList.
 */
public class Ex30 {

    static Random rand = new Random();
    public static ArrayList<Integer> createFullArrayList(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) list.add(rand.nextInt(size));
        return list;
    }

    public static LinkedList<Integer> createFullLinkedList(int size) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) list.add(rand.nextInt(size));
        return list;
    }

    public static long meanSortTimeLinkedList(int size, int reps) {
        long time = 0;
        for (int i = 0; i < reps; i++) {
            LinkedList<Integer> lList = createFullLinkedList(size);
            long sortTime = timedLinkedListSort(lList);
            time += sortTime;
        }
        return time/reps;
    }

    public static long meanSortTimeArrayList(int size, int reps) {
        long time = 0;
        for (int i = 0; i < reps; i++) {
            ArrayList<Integer> aList = createFullArrayList(size);
            long sortTime = timedArrayListSort(aList);
            time += sortTime;
        }
        return time/reps;
    }

    public static long timedArrayListSort(ArrayList<Integer> list) {
        long start = System.nanoTime();
        Collections.sort(list);
        long stop = System.nanoTime();
        return stop - start;
    }

    public static long timedLinkedListSort(LinkedList<Integer> list) {
        long start = System.nanoTime();
        Collections.sort(list);
        long stop = System.nanoTime();
        return stop - start;
    }

    public static void main(String[] args) {
        println("COllections.sort() times (nanosecs) : ");
        println();
        println("Means of 10000 sorts: ");
        println();
        println("Lists of 10 elements: ");
        println("LinkedList: " + meanSortTimeLinkedList(10, 10000));
        println("ArrayList: " + meanSortTimeArrayList(10, 10000));
        println();
        println("Lists of 100 elements: ");
        println("LinkedList: " + meanSortTimeLinkedList(100, 10000));
        println("ArrayList: " + meanSortTimeArrayList(100, 10000));
        println();
        println("Means of 1000 elements");
        println("LinkedList: " + meanSortTimeLinkedList(1000, 10000));
        println("ArrayList: " + meanSortTimeArrayList(1000, 10000));
    }
}
