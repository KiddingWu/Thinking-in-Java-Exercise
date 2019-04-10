package containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.org.kidding.util.RandomGenerator;

import static cn.org.kidding.util.Print.*;

/**
 * Modify Exercise 40 so that an alphabetic sort is used.
 */
class TwoStrings42 implements Comparable<TwoStrings42> {

    private String first = "";
    private String second = "";

    public TwoStrings42(String s1, String s2) {
        this.first = s1;
        this.second = s2;
    }

    // Using only first String to compare:
    @Override
    public int compareTo(TwoStrings42 o) {
        return first.compareToIgnoreCase(o.first);
    }

    // Optional inner class to user second to compare:
    public static class Comp2 implements Comparator<TwoStrings42> {
        @Override
        public int compare(TwoStrings42 o1, TwoStrings42 o2) {
            return o1.second.compareToIgnoreCase(o2.second);
        }
    }

    static void printArray(TwoStrings42[] sa) {
        println("(");
        for (int i = 0; i < sa.length - 1; i++)
            println(sa[i] + ", ");
        println(sa[sa.length - 1]);
    }

    @Override
    public String toString() {
        return first + " & " + second;
    }
}


public class Ex42 {

    public static void main(String[] args) {
        RandomGenerator.String rgs = new RandomGenerator.String();
        TwoStrings42[] array = new TwoStrings42[5];
        List<TwoStrings42> list = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            String s1 = rgs.next();
            String s2 = rgs.next();
            array[i] = new TwoStrings42(s1, s2);
            list.add(new TwoStrings42(s1, s2));
        }
        printnb("Array: ");
        TwoStrings42.printArray(array);
        println("List: " + list);
        Arrays.sort(array);
        Collections.sort(list, null);
        println();
        println("Sorted by first word:");
        printnb("Array: ");
        TwoStrings42.printArray(array);
        println("List: " + list);
        TwoStrings42.Comp2 comp = new TwoStrings42.Comp2();
        Arrays.sort(array, comp);
        Collections.sort(list, comp);
        println();
        println("Sorted by second word:");
        printnb("Array: ");
        TwoStrings42.printArray(array);
        println("List: " + list);
    }
}
