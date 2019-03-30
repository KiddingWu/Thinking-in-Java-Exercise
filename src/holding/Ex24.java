package holding;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static cn.org.kidding.util.Print.*;

public class Ex24 {

    private static Map<String, Integer> m = new LinkedHashMap<>();

    static {
        m.put("ten", 10);
        m.put("nine", 9);
        m.put("eight", 8);
        m.put("seven", 7);
        m.put("six", 6);
        m.put("five", 5);
        m.put("four", 4);
        m.put("three", 3);
        m.put("two", 2);
        m.put("one", 1);
        m.put("zero", 0);
    }

    public static void A() {

        // temporary map to hold entrys:
        Map<String, Integer> mTemp = new LinkedHashMap<>();
        // use TreeSet to sort the keySet():
        Set<String> ss = new TreeSet<>(m.keySet());
        Iterator<String> itss = ss.iterator();
        while (itss.hasNext()) {
            String s = itss.next();
            Integer i = m.get(s);
            m.remove(s);
            mTemp.put(s, i);
        }

        // get sorted list of temp keys:
        Set<String> ssTemp = new TreeSet<>(mTemp.keySet());
        // move sorted entrys back to map:
        Iterator<String> itssTemp = ssTemp.iterator();
        while (itssTemp.hasNext()) {
            String s = itssTemp.next();
            Integer i = mTemp.get(s);
            mTemp.remove(s);
            m.put(s, i);
        }

        // done with temp:
        mTemp.clear();
        println("Sorted map: " + m);
    }

    public static void B() {

        // temp map to hold entrys:
        Map<String, Integer> mTemp = new LinkedHashMap<>();
        // to sort the keySet():
        // convert Set to List:
        List<String> ssList = new LinkedList<>(m.keySet());
        // sort List:
        Collections.sort(ssList);
        // move entrys in sorted order from m to mTemp:
        Iterator<String> itssList = ssList.iterator();
        while (itssList.hasNext()) {
            String s = itssList.next();
            Integer i = m.get(s);
            m.remove(s);
            mTemp.put(s, i);
        }

        // get lsit of temp keys:
        List<String> ssTemp = new LinkedList<>(mTemp.keySet());
        // move sorted entrys back from mTemp to m:
        Iterator<String> itssTemp = ssTemp.iterator();
        while (itssTemp.hasNext()) {
            String s = itssTemp.next();
            Integer i = mTemp.get(s);
            mTemp.remove(s);
            m.put(s, i);
        }
        // done with temp:
        mTemp.clear();
        println("Sorted map: " + m);
    }

    public static void main(String[] args) {
        println("Map to sort: " + m);
        A();
        B();
    }
}
