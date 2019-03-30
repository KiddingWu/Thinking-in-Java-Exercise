package holding;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Create an empty LinkedList<Integer>. Using a ListIterator, add Integers
 * to the list by always inserting them in the middlle of the list.
 */
public class Ex14 {

    static void addMiddle(LinkedList<Integer> l, Integer[] ia) {
        for (Integer i : ia) {
            // inserting them in the middle of the list.
            ListIterator<Integer> it =
                    l.listIterator((l.size()) / 2);
            it.add(i);
            System.out.println(l);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> li = new LinkedList<>();
        Integer[] x  = {0,   1, 2, 3, 4, 5, 6, 7};
        addMiddle(li, x);
    }
}
