package containers;

import java.util.ArrayList;

import static cn.org.kidding.util.Print.*;

/**
 * Create a container that ecapsulates an array of String, and only
 * allows adding Strings and getting Strings, so that there are no
 * casting issues during use. If the internal array isn't big enough
 * for the next add, your container should automatically resize it.
 * In main(), compare the performance of your container with an
 * ArrayList<String>.
 */
class StringsHolder {

    private int size = 0;
    private String[] sArray = new String[size];

    // Can add only Strings;
    // (Resizing and copying will degrade performance)
    public void add(String s) {
        size += 1;
        String[] temp = new String[size];
        for (int i = 0; i < sArray.length; i++) temp[i] = sArray[i];
        temp[size - 1] = s;
        sArray = temp;
    }

    // get() returns only Strings:
    public String get(int i) {
        if (-1 < i && i < size) return sArray[i];
        else throw new ArrayIndexOutOfBoundsException(i);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sArray.length; i++) sb.append(sArray[i] + " ");
        return sb.toString();
    }
}

// Alternate to eliminate resizing time for fixed size StringHolder2:
class StringsHolder2 {
    private int size = 1000;
    private int index = 0;

    private String[] sArray = new String[size];

    // Can add only Strings:
    public void add(String s) {
        if (index < size) sArray[index++] = s;
    }
    // get() returns only Strings:
    public String get(int i) {
        if (-1 < i && i < size) return sArray[i];
        else throw new ArrayIndexOutOfBoundsException(i);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sArray.length; i++) sb.append(sArray[i] + " ");
        return sb.toString();
    }
}

public class Ex31 {
    static long addTimeTest(StringsHolder sh, int reps) {
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) sh.add("hi");
        long stop = System.nanoTime();
        return (stop - start)/reps;
    }

    static long addTimeTest(ArrayList<String> list, int reps) {
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) list.add("hi");
        long stop = System.nanoTime();
        return (stop - start)/reps;
    }

    static long getTimeTest(StringsHolder sh, int reps) {
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) sh.get(i);
        long stop = System.nanoTime();
        return (stop - start)/reps;
    }

    static long getTimeTest(ArrayList list, int reps) {
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) list.get(i);
        long stop = System.nanoTime();
        return (stop - start)/reps;
    }

    static long addTimeTest(StringsHolder2 sh, int reps) {
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) sh.add("hi");
        long stop = System.nanoTime();
        return (stop - start)/reps;
    }

    static long getTimeTest(StringsHolder2 sh, int reps) {
        long start = System.nanoTime();
        for (int i = 0; i < reps; i++) sh.get(i);
        long stop = System.nanoTime();
        return (stop - start)/reps;
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        StringsHolder sh = new StringsHolder();
        println("Add times (nanoseconds):");
        println("Mean of 1000:");
        println("ArrayList add(): " + addTimeTest(list, 1000));
        println("StringsHolder add(): " + addTimeTest(sh, 1000));
        println("Mean of 10000:");
        println("ArrayList add(): " + addTimeTest(list, 10000));
        println("StringsHolder add(): " + addTimeTest(sh, 10000));
        println();
        println("Get times (nanoseconds):");
        println("Mean of 10000:");
        println("ArrayList get(): " + getTimeTest(list, 10000));
        println("StringsHolder get(): " + getTimeTest(sh, 10000));
        println();
        println("Using alternate fixed size (1000) StringsHolder2");
        println();
        println("eliminates resizing and copying:");
        ArrayList<String> list2 = new ArrayList<String>();
        StringsHolder2 sh2 = new StringsHolder2();
        println("Add times (nanoseconds):");
        println("Mean of 1000:");
        println("ArrayList add(): " + addTimeTest(list2, 1000));
        println("StringsHolder2 add(): " + addTimeTest(sh2, 1000));
        println();
        println("Get times (nanoseconds):");
        println("Mean of 1000:");
        println("ArrayList get(): " + getTimeTest(list2, 1000));
        println("StringsHolder2 get(): " + getTimeTest(sh2, 1000));
    }
}
