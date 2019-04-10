package containers;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import cn.org.kidding.util.TextFile;
import containers.example.SimpleHashMap;

import static cn.org.kidding.util.Print.*;

// Repeat Exercise 13 using a SimpleHashMap.
public class Ex19 {

    public static void main(String[] args) {
        // File whose are to be counted:
        String fileName = "src/containers/Ex19.java";
        // Set of unique words in file:
        Set<String> words = new TreeSet<>(new TextFile(fileName, "\\W+"));
        // Create initialize array of correct length:
        SimpleHashMap<String, Integer> wordCount = new SimpleHashMap<>();
        // Word list of all words in file:
        ArrayList<String> fileList = new TextFile(fileName, "\\W+");
        // Count appearances of each unique word and add to array:
        for (String s : words) {
            int count = 0;
            for (String t : fileList) {
                if (s.contains(t)) count++;
            }
            wordCount.put(s, count);
        }
        println(wordCount);
    }
}
