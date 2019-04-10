package containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import cn.org.kidding.util.Countries;

import static cn.org.kidding.util.Print.*;

/**
 * Create both an ArrayList and a LinkedList, and fill each using the
 * Countries.names() generator. Print each list using an ordinary
 * iterator, then insert one list into the other by using a ListIterator,
 * inserting at every other location. Now perform the inserting staring
 * at the end of the first list and moving backwards.
 */
public class Ex7 {

    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<>(Countries.names(25));
        LinkedList<String> ll = new LinkedList(Countries.names(25));

        Iterator<String> alit = al.iterator();
        Iterator<String> llit = ll.iterator();
        while (alit.hasNext()) {
            printnb(alit.next() + (alit.hasNext() ? ", " : ""));
        }
        println();
        while (llit.hasNext()) {
            printnb(llit.next() + (llit.hasNext() ? ", " : ""));
        }
        println();
        println();
        ListIterator<String> allit = al.listIterator();
        ListIterator<String> lllit = ll.listIterator();
        while (lllit.hasNext()) {
            allit.add(lllit.next());
            allit.next();
        }
        print(al);
        println();
        List<String> al2 = new ArrayList<>(Countries.names(25));
        ListIterator allit2 = al2.listIterator();
        while (lllit.hasPrevious()) lllit.previous();
        while (allit2.hasNext()) allit2.next();
        while (lllit.hasNext()) {
            allit2.add(lllit.next());
            allit2.previous();
            allit2.previous();
        }
        print(al2);
    }
}
