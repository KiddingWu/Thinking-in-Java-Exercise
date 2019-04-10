package containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.org.kidding.util.RandomGenerator;

import static cn.org.kidding.util.Print.*;

/**
 * Create a class containing two String objects and make it Comparable so
 * that the comparison only cares about the first String. Fill an array
 * and an ArrayList with objects of your class, using the RandomGenerator
 * generator. Demonstrate that sorting works properly. Now make a
 * Comparator that only cares about the second String, and demostrate
 * that sorting works properly. Also perform a binary search using your
 * Comparator.
 */
class TwoStrings40 implements Comparable<TwoStrings40> {

    private String first = "";
    private String second = "";

    public TwoStrings40(String s1, String s2) {
        this.first = s1;
        this.second = s2;
    }

    // Using only first String to compare:
    @Override
    public int compareTo(TwoStrings40 ts) {
        return first.compareTo(ts.first);
    }

    // Optional inner class to use second String to compare:
    public static class Comp2 implements Comparator<TwoStrings40> {

        @Override
        public int compare(TwoStrings40 ts1, TwoStrings40 ts2) {
            return ts1.second.compareTo(ts2.second);
        }
    }

    static void printArray(TwoStrings40[] sa) {
        print("(");
        for (int i = 0; i < sa.length - 1; i++)
            println(sa[i] + ", ");
        println(sa[sa.length - 1] + ")");
    }

    @Override
    public String toString() {
        return first + " & " + second;
    }
}

public class Ex40 {
    public static void main(String[] args) {
        RandomGenerator.String rgs = new RandomGenerator.String();
        TwoStrings40[] array = new TwoStrings40[5];
        List<TwoStrings40> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            String s1 = rgs.next();
            String s2 = rgs.next();
            array[i] = new TwoStrings40(s1, s2);
            list.add(new TwoStrings40(s1, s2));
        }
        println("Array: ");
        TwoStrings40.printArray(array);
        println("List: " + list);
        Arrays.sort(array);
        Collections.sort(list, null);
        println();
        println("Sorted by first word: ");
        println("Array: ");
        TwoStrings40.printArray(array);
        println("List: " + list);
        TwoStrings40.Comp2 comp = new TwoStrings40.Comp2();
        Arrays.sort(array, comp);
        Collections.sort(list, comp);
        println();
        println("Sorted by second word:");
        println("Array");
        TwoStrings40.printArray(array);
        println("List: " + list);
    }
}
