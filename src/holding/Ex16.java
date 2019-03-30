package holding;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import cn.org.kidding.util.TextFile;

/**
 * Create a Set of the vowels. Working from UniqueWords.java, count and
 * display the number of vowels in each input word, and also dispaly the total
 * number of vowels in the input file.
 */
public class Ex16 {

    private static void vowelCounter(Set<String> st) {
        Set<Character> vowels = new TreeSet<>(
                Arrays.asList('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'));
        int allVowels = 0;
        for (String s : st) {
            int count = 0;
            for (Character v : s.toCharArray()) {
                if (vowels.contains(v)){
                    count++;
                    allVowels++;
                }
            }
            System.out.print(s + ": " + count + "\n");
        }
        System.out.println();
        System.out.print("Total vowels: " + allVowels);
    }

    public static void main(String[] args) {
        Set<String> words = new TreeSet<>(
                new TextFile("src/holding/SetOperations.java", "\\W+"));
        System.out.println(words);
        System.out.println();
        vowelCounter(words);
    }
}
