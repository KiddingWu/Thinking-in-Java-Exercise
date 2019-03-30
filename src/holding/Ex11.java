package holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;

import static cn.org.kidding.util.Print.*;
import static cn.org.kidding.util.Print.print;
import static cn.org.kidding.util.Print.println;

/**
 * Write a method the uses an Iterator to step through a collection and
 * print the toString() of each object int the container. Fill all the different
 * type of Collections with objects and apply your method to each container.
 */
public class Ex11 {

    public static void printAny(Collection c) {
        Iterator it = c.iterator();
        while (it.hasNext())
            print(it.next() + " ");
        println();
    }

    public static void main(String[] args) {
        ArrayList<Integer> al =
                new ArrayList<>(Arrays.asList(1, 2, 3));
        LinkedList<Character> ll =
                new LinkedList<>(Arrays.asList('a', 'b', 'c'));
        HashSet<Float> hs =
                new HashSet<>(Arrays.asList(1.1f, 2.2f, 3.3f));
        TreeSet<Double> ts =
                new TreeSet<>(Arrays.asList(1.11, 2.22, 3.33));
        LinkedHashSet<Integer> lhs =
                new LinkedHashSet<>(Arrays.asList(11, 22, 33));
        printAny(al);
        printAny(ll);
        printAny(hs);
        printAny(ts);
        printAny(lhs);
    }
}
