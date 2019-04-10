package containers;

import java.util.TreeSet;
import cn.org.kidding.util.RandomGenerator;
import static cn.org.kidding.util.Print.*;

/**
 * Use RandomGenerator.String to fill a TreeSet, but use a alphabetic
 * ordering. Print the TreeSet to verify the sort order.
 */
public class Ex9 {

    public static void main(String[] args) {
        // use a alphabetic ordering.
        TreeSet<String> ts = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        RandomGenerator.String rgs = new RandomGenerator.String(20);
        for (int i = 0; i < 20; i++) {
            ts.add(rgs.next());
        }
        print(ts);
    }
}
