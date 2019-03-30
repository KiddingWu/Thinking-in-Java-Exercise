package holding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Repeat the previous exercise with a HashSet and a LinkedHashSet.
 */
public class Ex19 {

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

        Set<String> hashedKeys = new HashSet<>(gerbils.keySet());
        System.out.println("HashSet: " + hashedKeys);
        System.out.println();
        Map<String, Gerbil> hashedGerbils = new HashMap<>();
        for (String s : hashedKeys) {
            System.out.print("Adding " + s + ", ");
            hashedGerbils.put(s, gerbils.get(s));
        }
        System.out.println();
        System.out.println();
        System.out.println("From HashSet: " + hashedGerbils);

        System.out.println();
        Set<String> linkedHashedKeys = new LinkedHashSet<>(gerbils.keySet());
        System.out.println("LinkedHashSet: " + linkedHashedKeys);
        System.out.println();
        Map<String, Gerbil> linkedHashedGerbils =
                new LinkedHashMap<>();
        for (String s : linkedHashedKeys) {
            System.out.print("Adding " + s + ", ");
            linkedHashedGerbils.put(s, gerbils.get(s));
        }
        System.out.println();
        System.out.println();
        System.out.println("From LinkedHashSet: " + linkedHashedGerbils);
    }
}
