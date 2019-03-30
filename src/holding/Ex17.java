package holding;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Take the Gerbil class in Exercise 1 and put it into a Map instead,
 * associating each Gerbil's name (e.g. "Fuzzy" or "Spot") as as String (the
 * key) for each Gerbil (the value) you put int the table. Get an Iterator for
 * the keySet() and use it to move through the Map, looking up the Gerbil for
 * each key and printing out the key and telling the Gerbil to hop().
 */
public class Ex17 {

    private static Map<String, Gerbil> gerbils = new HashMap<>();

    static {
        gerbils.put("Fuzzy", new Gerbil(0));
        gerbils.put("Spot", new Gerbil(1));
        gerbils.put("Speedy", new Gerbil(2));
        gerbils.put("Dopey", new Gerbil(3));
        gerbils.put("Sleepy", new Gerbil(4));
        gerbils.put("Happy", new Gerbil(5));
    }

    public static void main(String[] args) {
        Iterator<String> it = gerbils.keySet().iterator();
        while (it.hasNext()) {
            String s = it.next();
            System.out.println(s + ": ");
            gerbils.get(s).hop();
        }
    }
}
