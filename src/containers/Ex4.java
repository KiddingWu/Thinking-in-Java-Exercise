package containers;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;

import cn.org.kidding.util.TextFile;

import static cn.org.kidding.util.Print.println;

/**
 * Create a Collection initializer that opens a file and breaks
 * it into words using TextFile, and then uses the words as the
 * source of data for the resulting Collection. Demostrate that
 * it works.
 */
public class Ex4 {

    static Collection<String> CollectFromText(String fileName) {
        String[] sa = TextFile.read(fileName).split("\\W+");
        return Arrays.asList(sa);
    }

    static Collection<String> CollectFromText2(String fileName) {
        return new TreeSet<String>(new TextFile(fileName, "\\W+"));
    }

    public static void main(String[] args) {
        println(CollectFromText("src/containers/Ex4.java"));
        println(CollectFromText2("src/containers/Ex4.java"));
    }
}
