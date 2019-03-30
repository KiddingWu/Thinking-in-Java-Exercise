package holding;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static cn.org.kidding.util.Print.println;

public class SetOperations {

    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        Collections.addAll(set1, "A B C D E F G H I J K L".split(" "));
        set1.add("M");
        println("H: " + set1.contains("H"));
        println("N: " + set1.contains("N"));
        Set<String> set2 = new HashSet<>();
        Collections.addAll(set2, "H I J K L".split(" "));
        println("set2 in set1: " + set1.containsAll(set2));
        set1.remove("H");
        println("set1: " + set1);
        println("set2 in set1 " + set1.containsAll(set2));
        set1.removeAll(set2);
        println("set2 removed from set1: " + set1);
        Collections.addAll(set1, "X Y Z".split(" "));
        println("'X Y Z' added to set1: " + set1);
    }
}
