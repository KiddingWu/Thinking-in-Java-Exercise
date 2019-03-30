package holding;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.org.kidding.util.TextFile;


/**
 * Create a Map<String, ArrayList<Integer>>. User TextFile
 * to open a text file and read it in a word at a time (use "\\W+\" as
 * the second argument to the TextFile constructor). Count the words as
 * you read them in, and for each word in the file, record int the
 * ArrayList<Integer> the word count associated with that word - that is,
 * in effect, the location in the file where that word was found.
 */
public class Ex25 {

    public static void main(String[] args) {

        Map<String, ArrayList<Integer>> m = new LinkedHashMap();

        List<String> words = new ArrayList<>(
                new TextFile("src/holding/SetOperations.java", "\\W+"));

        Iterator<String> itWords = words.iterator();
        int count = 0;
        while (itWords.hasNext()) {
            String s = itWords.next();
            count++;

            // the location in the file where that word was found.
            if (!m.keySet().contains(s)) {
                ArrayList<Integer> ai = new ArrayList<>();
                ai.add(0, count);
                m.put(s, ai);
            } else {
                m.get(s).add(count);
                m.put(s, m.get(s));
            }
        }
        System.out.println("Map of word locations: " + m);
    }
}
