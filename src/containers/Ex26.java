package containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.org.kidding.util.Print.*;

class CountedString26 {
    private static List<String> created =
            new ArrayList<String>();

    private String s;
    private int id = 0;
    private char c;

    public CountedString26(String str, char ch) {
        s = str;
        c = ch;
        created.add(s);

        // id is the total number of instances
        // of this string in use by CountedString:
        for (String s2 : created)
            if (s2.equals(s)) id++;
    }

    @Override
    public int hashCode() {
        // The very simple approach:
        // return s.hashCode() * id:
        // Using Joshua Bloch's recipe:
        int result = 17;
        result = 37 * result + s.hashCode();
        result = 37 * result + id;
        result = 37 * result + c;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CountedString26 &&
                s.equals(((CountedString26)obj).s) &&
                id == (((CountedString26)obj)).id &&
                c == (((CountedString26)obj).c);
    }

    @Override
    public String toString() {
        return "String: " + s + " id: " + id +
                " char: " + c + " hashCode(): " + hashCode();
    }
}

public class Ex26 {
    public static void main(String[] args) {
        Map<CountedString26, Integer> map =
                new HashMap();
        CountedString26[] cs = new CountedString26[5];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = new CountedString26("hi", 'x');
            map.put(cs[i], i); // Autobox int -> Integer
        }
        println(map);
        for (CountedString26 cstring : cs) {
            println("Looking up " + cstring);
            println(map.get(cstring));
        }
    }
}
