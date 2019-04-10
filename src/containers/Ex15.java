package containers;

import java.util.ArrayList;
import java.util.Iterator;

import cn.org.kidding.util.TextFile;
import containers.example.SlowMap;

import static cn.org.kidding.util.Print.*;

/**
 * Repeat Exercise 13 using a SlowMap.
 */
public class Ex15 {

    public static void main(String[] args) {
        // File whose words are to be counted:
        String fileName = "src/containers/Ex15.java";
        // List of all words in file:
        ArrayList<String> wordList = new TextFile(fileName, "\\W+");
        SlowMap<String, Integer> wordCount = new SlowMap<>();
        Iterator<String> it = wordList.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (!wordCount.containsKey(s)) wordCount.put(s, 1);
            else {
                int count = wordCount.get(s);
                wordCount.put(s, ++count);
            }
        }
        print(wordCount);
    }
}
