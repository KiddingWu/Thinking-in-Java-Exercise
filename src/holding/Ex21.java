package holding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import cn.org.kidding.util.TextFile;

/**
 * Using a Map<String,Integer>, follow the form of UniqueWords.java to create a
 * program that counts the occurrence of words in a file. Sort the results using
 * Collefctions.sort() with a second argument of String.CASE_INSENSITIVE_ORDER (to
 * produce an alphabetic sort), and display the result.
 */
public class Ex21 {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>(
                new TextFile("src/holding/SetOperations.java", "\\W+"));
        System.out.println("Words to count: " + words);
        // String.CASE_INSENSITIVE_ORDER: 按照字母序排序
        Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
        Map<String, Integer> wordCount = new LinkedHashMap<>();
        Iterator<String> it = words.iterator();
        int totalWords = 0;
        while (it.hasNext()) {
            String s = it.next();
            if (words.contains(s)) {
                Integer count = wordCount.get(s);
                wordCount.put(s, count == null ? 1 : count + 1);
                totalWords++;
            }
        }
        System.out.println();
        System.out.println("Word count: " + wordCount);
        System.out.println();
        System.out.println("Total words: " + totalWords);
    }
}
