package holding;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import cn.org.kidding.util.TextFile;

/**
 * Modify Exercise 16 so that you keep a count of the occurence of each vowel.
 */
public class Ex20 {

    public static void vowelCounter(Set<String> st) {
        Set<Character> vowels = new TreeSet<>(
                Arrays.asList('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'));
        int allVowels = 0;
        Map<Character, Integer> vowelMaps = new TreeMap<>();
        for (String s : st) {
            for (Character v : s.toCharArray()) {
                if (vowels.contains(v)) {
                    Integer count = vowelMaps.get(v);
                    vowelMaps.put(v, count == null ? 1 : count + 1);
                    allVowels++;
                }
            }
        }
        System.out.println("Vowels: " + vowelMaps);
        System.out.println("Total vowels: " + allVowels );
    }

    public static void main(String[] args) {
        Set<String> word = new TreeSet<>(
                new TextFile("src/holding/SetOperations.java", "\\W+"));
        System.out.println(word);
        System.out.println();
        vowelCounter(word);
    }
}
