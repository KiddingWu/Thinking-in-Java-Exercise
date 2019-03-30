package holding;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Fill a HashMap with key-value pairs. Print the results to show ordering
 * by hash code. Extract the pairs, sort by key, and place the result into a
 * LinkedHashMap. Show that the insertion order is maintained.
 */
public class Ex18 {

    private static Map<String, Gerbil> gerbils = new HashMap<>();

    static {
        gerbils.put("Fuzzy", new Gerbil(0));
        gerbils.put("Spot", new Gerbil(1));
        gerbils.put("Speedy", new Gerbil(2));
        gerbils.put("Dopey", new Gerbil(3));
        gerbils.put("Sleepy", new Gerbil(4));
        gerbils.put("Happy", new Gerbil(5));
        gerbils.put("Funny", new Gerbil(6));
        gerbils.put("Silly", new Gerbil(7));
        gerbils.put("Goofy", new Gerbil(8));
        gerbils.put("Wowee", new Gerbil(9));
    }

    public static void main(String[] args) {
        System.out.println(gerbils);
        System.out.println();
        Set<String> sortedKeys = new TreeSet<>(gerbils.keySet());
        System.out.println(sortedKeys);
        System.out.println();

        Map<String, Object> sortedGerbils = new LinkedHashMap<>();

        for (String s : sortedKeys) {
            System.out.print("Adding " + s + ", ");
            sortedGerbils.put(s, gerbils.get(s));
        }
        System.out.println();
        System.out.println();
        System.out.println(sortedGerbils);
        System.out.println();

        // or, just:
        Map<String, Gerbil> sortedGerbils2 = new TreeMap<>(gerbils);
        System.out.println(sortedGerbils2);
    }
}
