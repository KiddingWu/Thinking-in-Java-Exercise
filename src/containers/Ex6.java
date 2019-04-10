package containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static cn.org.kidding.util.Print.*;

/**
 * Note that List has additional "optional" operations that are not included
 * in Collection. Write a version of Unsupported.java that tests these additional
 * optional operations.
 */
class Unsupported {
    static void test(String msg, List<String> list) {
        println("--- " + msg + " ---");
        Collection<String> c = list;
        Collection<String> subList = list.subList(1, 8);
        // Copy of the sublist:
        Collection<String> c2 = new ArrayList<>(subList);
        try {
            c.retainAll(c2);
        } catch (Exception e) {
            println("retainAll(): " + e);
        }
        try {
            c.removeAll(c2);
        } catch (Exception e) {
            println("removeAll(): " + e);
        }
        try {
            c.clear();
        } catch (Exception e) {
            println("clear(): " + e);
        }
        try {
            c.add("X");
        } catch (Exception e) {
            println("addAll(): " + e);
        }
        try {
            c.addAll(c2);
        } catch (Exception e) {
            println("remove(): " + e);
        }
        try {
            c.remove("C");
        } catch (Exception e) {
            println("remove(): " + e);
        }

        // The List.set() method modifies the value but
        // doesn't change the size of the data structure:
        try {
            list.set(0, "X");
        } catch (Exception e) {
            println("List.set(): " + e);
        }
        // Additional optional List methods:
        try {
            list.add(0, "X");
        } catch (Exception e) {
            println("List.add(): " + e);
        }
        try {
            list.addAll(0, c2);
        } catch (Exception e) {
            println("List.addAll(): " + e);
        }
        try {
            list.remove(0);
        } catch (Exception e) {
            println("List.remove(): " + e);
        }
        try {
            list.removeAll(c2);
        } catch (Exception e) {
            println("List.removeAll(): " + e);
        }
        try {
            list.retainAll(c2);
        } catch (Exception e) {
            println("List retainAll(): " + e);
        }
    }
}

public class Ex6 {
    public static void main(String[] args) {
        Unsupported un = new Unsupported();
        List<String> list =
                Arrays.asList("A B C D E F G H I J K L".split(" "));
        un.test("Modifiable Copy", new ArrayList<>(list));
        un.test("Arrays.asList()", list);
        un.test("unmodifiableList()", Collections.unmodifiableList(new ArrayList<>(list)));
    }
}
