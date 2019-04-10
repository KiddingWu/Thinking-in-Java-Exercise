package containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.org.kidding.util.Print.*;

/**
 * Modify the hashCode() in CountedString.java by removing the combination
 * with id, and demonstrate that CountedString still works as a key. What
 * is the problem with this approach?
 */
class CountedString27 {
    private String s;
    private int id = 0;

    private static List<String> created = new ArrayList<>();

    public CountedString27(String str) {
        s = str;
        created.add(s);
        // id is the total number of instances
        // of this string in use by CountedString:
        for (String s2 : created)
            if (s2.equals(s))
                id++;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + s.hashCode();
        // result = 37 * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CountedString27 &&
                s.equals(((CountedString27)obj).s) &&
                id == (((CountedString27)obj).id);
    }

    @Override
    public String toString() {
        return "String: " + s + " id: " + id +
                ", hashCode(): " + hashCode();
    }
}

public class Ex27 {

    public static void main(String[] args) {
        Map<CountedString27, Integer> map =
                new HashMap<>();
        CountedString27[] cs = new CountedString27[5];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = new CountedString27("hi");
            map.put(cs[i], i);  // Autobox int -> Integer
        }
        println(map);
        // Problem: same hash code for different objects:
        for (CountedString27 csstring : cs) {
            println("Looking up " + csstring);
            println(map.get(csstring));
        }
    }
}
