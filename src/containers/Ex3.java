package containers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import cn.org.kidding.util.Countries;

import static cn.org.kidding.util.Print.*;

/**
 * Using Countries, fill a Set multiple times with the same data
 * and verify that the Set ends up with only one of each instance.
 * Try this with HashSet, LinkedHashSet, and TreeSet.
 */
public class Ex3 {

    public static void main(String[] args) {

        Set<String> hs = new HashSet<>();
        Set<String> lhs = new LinkedHashSet<>();
        Set<String> ts = new TreeSet<>();
        println("HashSet hs = " + hs);
        println("LinkedHashSet lhs = " + lhs);
        println("TreeSet ts = " + ts);
        Map<String, String> hm = new HashMap<>(0);
        Pattern p = Pattern.compile("A[a-zA-Z]*");
        for (int i = 0; i < Countries.DATA.length; i++) {
            if (p.matcher(Countries.DATA[i][0]).matches())
                hm.put(Countries.DATA[i][0], Countries.DATA[i][1]);
        }

        println("hm.keySet() = " + hm.keySet());
        println();
        println("Adding hm.keySet()");
        hs.addAll(hm.keySet());
        lhs.addAll(hm.keySet());
        ts.addAll(hm.keySet());
        println("hs: " + hs);
        println("lhs: " + lhs);
        println("ts: " + ts);
        println();
        println("Adding 10 more times");
        for (int i = 0; i < 10; i++) {
            hs.addAll(hm.keySet());
            lhs.addAll(hm.keySet());
            ts.addAll(hm.keySet());
        }
        println("hs: " + hs);
        println("lhs: " + lhs);
        println("ts: " + ts);
    }
}
