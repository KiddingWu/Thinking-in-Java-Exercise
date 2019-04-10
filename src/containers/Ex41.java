package containers;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import cn.org.kidding.util.RandomGenerator;

import static cn.org.kidding.util.Print.*;

/**
 * Modify the class in the previous exercise so that it will work with
 * HashSets and as a key in HashMaps.
 */
class TwoStrings41 implements Comparable<TwoStrings41> {

    private String first = "";
    private String second = "";

    public TwoStrings41(String s1, String s2) {
        this.first = s1;
        this.second = s2;
    }

    // Using only first String to compare:
    @Override
    public int compareTo(TwoStrings41 o) {
        return first.compareTo(o.first);
    }

    // Optional inner class to use second String to compare:
    public static class Comp2 implements Comparator<TwoStrings41> {

        @Override
        public int compare(TwoStrings41 s1, TwoStrings41 s2) {
            return s1.second.compareTo(s2.second);
        }
    }

    public String toString() {
        return first + "&" + second;
    }

    static void printArray(TwoStrings41[] sa) {
        println();
        for (int i = 0; i < sa.length - 1; i++)
            println(sa[i] + ", ");
        println(sa[sa.length - 1] + ")");
    }


}

public class Ex41 {

    public static void main(String[] args) {
        RandomGenerator.String rgs = new RandomGenerator.String();
        Set<TwoStrings41> set = new HashSet<>();
        Map<TwoStrings41, Integer> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            String s1 = rgs.next();
            String s2 = rgs.next();
            set.add(new TwoStrings41(s1, s2));
            map.put(new TwoStrings41(s1, s2), i);
        }
        println("Set: " + set);
        println("Map: " + map);
        println("map.keySet(): " + map.keySet());
        println("Sorted by first String:");
        Set<TwoStrings41> treeSet = new TreeSet<>(set);
        Map<TwoStrings41,Integer> treeMap =
                new TreeMap<>(map);
        println("TreeSet: " + treeSet);
        println("TreeMap: " + treeMap);
        println("Sorted by second String:");
        TwoStrings41.Comp2 comp = new TwoStrings41.Comp2();
        Set<TwoStrings41> treeSet2 =
                new TreeSet<>(comp);
        Map<TwoStrings41,Integer> treeMap2 =
                new TreeMap<>(comp);
        treeSet2.addAll(treeSet);
        treeMap2.putAll(treeMap);
        println("TreeSet2: " + treeSet2);
        println("TreeMap2: " + treeMap2);
    }
}
