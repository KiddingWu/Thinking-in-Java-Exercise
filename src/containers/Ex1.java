package containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cn.org.kidding.util.Countries;
import static cn.org.kidding.util.Print.*;

/**
 * Create a List (try both ArrayList and LinkedList) and fill it using
 * Countries. Sort the list and print it, then apply Collection.shuffle()
 * to the list repeatedly. printing it each time so that you can see how
 * the shuffle() method reandomizes the list differently each time.
 */
public class Ex1 {

    public static void main(String[] args) {

        List<String> al = new ArrayList();
        List<String> ll = new LinkedList<>();

        for (int i = 0; i < Countries.DATA.length; i++) {
            al.add(Countries.DATA[i][0]);
            ll.add(Countries.DATA[i][1]);
        }

        Collections.sort(al);
        Collections.sort(ll);
        println(al);
        println(ll);

        for (int i = 0; i < 2; i++) {
            // shuffle() 方法是每次将列表随机打乱
            Collections.shuffle(al);
            Collections.shuffle(ll);
            println("Countries " + i + ": " + al);
            println("Countries " + i + ": " + ll);
        }

        List<String> al2 = new ArrayList<>();
        List<String> ll2 = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            al2.add(Countries.DATA[i][0]);
            ll2.add(Countries.DATA[i][1]);
        }

        for (int i = 0; i < 5; i++) {
            Collections.shuffle(al2);
            Collections.shuffle(ll2);
            println("Countries " + i + ": " + al2);
            println("Countries " + i + ": " + ll2);
            Collections.sort(al2);
            Collections.sort(ll2);
        }
    }
}
