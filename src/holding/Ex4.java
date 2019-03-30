package holding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Create a generator class that produces character names (as String Object)
 * from your favorite movie (you can use Snow White and Star Wars as a fallback)
 * each time you call next(), and loops around to the beginning of the character list
 * when it runs out of names. Use this generator to fill an array, an ArrayList,
 * a LinkedList, a HashSet, a LinkedHashSet, and a TreeSet, then print each container.
 */
class Generator {

    int key = 0;
    public String next() {
        switch (key) {
            default:
            case 0: key++; return "Snow White";
            case 1: key++; return "Star Wars";
            case 2: key++; return "Doc";
            case 3: key++; return "Dopey";
            case 4: key++; return "Grumpy";
            case 5: key++; return "Sleepy";
            case 6: key++; return "Bashful";
            case 7: key = 0; return "Sneezy";
        }
    }

    /**
     * Use this generator to fill an array
     */
    public void fillA(String[] a) {
        for (int i = 0; i < a.length; i++)
            a[i] = next();

    }

    /**
     * Use this generator to fill an Collection
     */
    public Collection fill(Collection<String> c, int n) {
        for (int i = 0; i < n; i++)
            c.add(next());
        return c;

    }
}

public class Ex4 {

    public static void main(String[] args) {
        Generator generator = new Generator();
        String[] a = new String[10];
        generator.fillA(a);
        System.out.println("Array：");
        for (String s : a)
        System.out.print(s + ", ");

        /**
         * Use this generator to fill
         *  an ArrayList,
         *  a LinkedList,
         *  a HashSet,
         *  a LinkedHashSet,
         *  and a TreeSet,
         *  then print each container.
         */
        System.out.println("\n\n");
        System.out.println("Collection：");
        System.out.println(generator.fill(new ArrayList<>(), 10));
        System.out.println(generator.fill(new LinkedList<>(), 10));
        System.out.println(generator.fill(new HashSet<>(), 10));
        System.out.println(generator.fill(new LinkedHashSet<>(), 10));
        System.out.println(generator.fill(new TreeSet<>(), 10));

    }
}


