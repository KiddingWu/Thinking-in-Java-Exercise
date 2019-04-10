package containers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import containers.example.AssociativeArray;

import static cn.org.kidding.util.Print.*;

/**
 * Substitutes a HashMap, a TreeMap and a LinkedHashMap
 * in AssociativeArray.java's main()
 */
public class Ex12 {

    public static void main(String[] args) {
        AssociativeArray<String, String> map =
                new AssociativeArray<>(6);

        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("sun", "warm");
        try {
            map.put("extra", "object"); // Past the end
        } catch (ArrayIndexOutOfBoundsException e) {
            print("Too many objects!");
        }
        println(map);
        println(map.get("ocean"));
        println();
        println("--- hashMap ---");
        HashMap<String, String> hashMap = new HashMap<>(6);
        hashMap.put("sky", "blue");
        hashMap.put("grass", "green");
        hashMap.put("ocean", "dancing");
        hashMap.put("tree", "tall");
        hashMap.put("earth", "brown");
        hashMap.put("sun", "warm");
        try {
            hashMap.put("extra", "object");
        } catch (ArrayIndexOutOfBoundsException e) {
            print("Too many objects!");
        }
        println(hashMap);
        println(hashMap.get("ocean"));
        println();
        println("--- treeMap ---");
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("sky", "blue");
        treeMap.put("grass", "green");
        treeMap.put("ocean", "dancing");
        treeMap.put("tree", "tall");
        treeMap.put("earth", "brown");
        treeMap.put("sun", "warm");
        try {
            treeMap.put("extra", "object");
        } catch (ArrayIndexOutOfBoundsException e) {
            print("Too many objects!");
        }
        println(treeMap);
        println(treeMap.get("ocean"));
        println();
        println("--- linkedHashMap ---");
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("sky", "blue");
        linkedHashMap.put("grass", "green");
        linkedHashMap.put("ocean", "dancing");
        linkedHashMap.put("tree", "tall");
        linkedHashMap.put("earth", "brown");
        linkedHashMap.put("sun", "warm");
        try {
            linkedHashMap.put("extra", "object");
        } catch (ArrayIndexOutOfBoundsException e) {
            print("Too many objects!");
        }
        println(linkedHashMap);
        print(linkedHashMap.get("ocean"));
    }
}
