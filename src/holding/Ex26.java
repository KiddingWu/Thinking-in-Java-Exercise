package holding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.org.kidding.util.TextFile;

/**
 * Take the resulting Map from the previous exercise and re-create the
 * order of the words as they appeared int the original file.
 */
public class Ex26 {

    public static void main(String[] args) {

        Map<String, ArrayList<Integer>> m = new LinkedHashMap<>();

        List<String> words = new ArrayList<>(
                new TextFile("src/holding/SetOperations.java", "\\W+"));

        Iterator<String> itWords = words.iterator();
        int count = 0;
        while (itWords.hasNext()) {
            count++;
            String s = itWords.next();
            if (!m.keySet().contains(s)) {
                ArrayList<Integer> ai = new ArrayList<>();
                ai.add(0, count);
                m.put(s, ai);
            } else {
                m.get(s).add(count);
                m.put(s, m.get(s));
            }
        }
        System.out.println();
        System.out.println("Map of word locations: " + m);

        // New map to hold sorted words, keyed by location:
        Map<Integer, String> replay = new TreeMap();
        Iterator<Map.Entry<String, ArrayList<Integer>>> it = m.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, ArrayList<Integer>> me = it.next();
            for (int i = 0; i < me.getValue().size(); i++)
                replay.put(me.getValue().get(i), me.getKey());
        }
        System.out.println();
        System.out.println("TreeMap of ordered locations, words: " + replay);
        System.out.println();
        // Display words in order as TreeMap values():
        System.out.println("Words in original order: " + replay.values());
    }
}
