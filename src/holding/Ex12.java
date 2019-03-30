package holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static cn.org.kidding.util.Print.*;
import static cn.org.kidding.util.Print.println;

/**
 * Create and popluate a List<Integer>. Create a secong List<Integer> of the
 * same size as the frist, and use ListIterator to read elements of the first
 * List and insert them into the second in reverse order. (You may want to
 * explore a number of different ways to solve this problem.)
 */
public class Ex12 {

    public static void A() {
        List<Integer> li1 = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> li2 = new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9));

        // ListIterator 是一个更加强大的 Iterator 的子类型，只能用于各种 List 类的访问。
        // ListIterator 可以双向移动。
        ListIterator<Integer> it1 = li1.listIterator();
        ListIterator<Integer> it2 = li2.listIterator();

        println("li1: " + li1);
        println("li2: " + li2);

        // move it1 to end:
        while (it1.hasNext())
            it1.next();

        // now use it2 to re-set li2:
        while (it2.hasNext()) {
            it2.next();
            it2.set(it1.previous());
        }

        println("li1: " + li1);
        println("li2: " + li2);
    }

    public static void B() {
        List<Integer> li1 = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        List<Integer> li2 = new ArrayList<>(Arrays.asList(5, 6, 7, 8, 9));
        ListIterator<Integer> it1 = li1.listIterator(5);
        ListIterator<Integer> it2 = li2.listIterator();

        println("li1: " + li1);
        println("li2: " + li2);

        // now use it2 to re-set li2:
        while (it2.hasNext()) {
            it2.next();
            it2.set(it1.previous());
        }

        println("li1: " + li1);
        println("li2: " + li2);
    }

    public static void main(String[] args) {
        A();
        B();
    }
}
