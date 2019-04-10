package containers;


import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import cn.org.kidding.util.TextFile;
import containers.example.AssociativeArray;

import static cn.org.kidding.util.Print.*;

/**
 * Use AssociativeArray.java to create a word-occurrence counter,
 * mapping String to Integer. Using the cn.org.kidding.util.TextFile
 * utility in this book, open a text file and break up the words
 * in that file using whitespace and punctuation, and count the
 * occurrence of the words in that file.
 */
public class Ex13 {

    public static void main(String[] args) {

        // File whose words are to be counted:
        String fileName = "src/containers/Ex13.java";
        // Set of unique words in file:
        Set<String> words = new TreeSet<>(new TextFile(fileName, "\\W+"));
        // Create initialize array of correct length:
        AssociativeArray<String, Integer> wordCount =
                new AssociativeArray<>(words.size());
        // Word list of all words in file:
        ArrayList<String> fileList = new TextFile(fileName, "\\W+");
        // Count appearances of each unique word and add to array:
        for (String s : words) {
            int count = 0;
            for (String t : fileList) {
                if (t.equals(s)) count++;
            }
            wordCount.put(s, count);
        }
        print(wordCount);
    }
}
