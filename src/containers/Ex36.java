package containers;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;

import cn.org.kidding.util.Countries;
import containers.example.MapEntry;
import containers.example.SlowMap;
import containers.example.Test;
import containers.example.TestParam;
import containers.example.Tester;

import static cn.org.kidding.util.Print.*;

/**
 * Modify SlowMap so that instead of two ArrayLists, it holds a single
 * ArrayList of MapEntry objects. Verfiy that modified version works
 * correctly. Using MapPerformance.java, test the speed of your new Map.
 * Now change the put() method so that if performs a sort() after each
 * pair is entered, and modify get() to use Collections.binarySearch()
 * to look up the key. Compares the performance of the new version with
 * the old ones.
 */

// SlowMap with one ArrayList<MapEntry<K,V>> and
// original put() and get() methods:
class SlowMap36a<K,V> extends AbstractMap<K,V> {

    protected List<MapEntry<K,V>> entryList = new ArrayList<>();
    private EntrySet entries = new EntrySet();

    private class EntrySet extends AbstractSet<Map.Entry<K,V>> {

        @Override
        public int size() {
            return entryList.size();
        }

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new Iterator<Entry<K, V>>() {
                private int index = -1;
                @Override
                public boolean hasNext() {
                    return index < entryList.size() - 1;
                }

                @Override
                public Entry<K, V> next() {
                    int i = ++index;
                    return entryList.get(i);
                }

                @Override
                public void remove() {
                    entryList.remove(index--);
                }
            };
        }
    }

    @Override
    public V put(K key, V value) {
        V oldValue = get(key); // The old value or null
        Iterator<MapEntry<K,V>> it = entryList.iterator();
        while (it.hasNext()) {
            MapEntry<K,V> me = it.next();
            if (me.getKey().equals(key))
                me.setValue(value);
        }
        entryList.add(new MapEntry<>(key, value));
        return oldValue;
    }

    @Override
    public V get(Object key) { // key is type Object, not K
        Iterator<MapEntry<K,V>> it = entryList.iterator();
        while (it.hasNext()) {
            MapEntry<K,V> me = it.next();
            if (me.getKey().equals(key))
                return me.getValue();
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return entries;
    }
}

// SlowMap with modified put() and get() method
class SlowMap36b<K,V> extends SlowMap36a<K,V> {

    // To allow search for key via Collections.binarySearch(List<T>, T, Comparator)
    private List<MapEntry<Integer,V>> hashEntryList = new ArrayList<>();
    public List<MapEntry<Integer,V>> hashEntrylist() {
        return hashEntryList;
    }

    // To allow sorting:
    private final MapEntryKeyComp<K,V> comp1 = new MapEntryKeyComp<>();
    private final MapEntryKeyComp<Integer,V> comp2 = new MapEntryKeyComp<>();


    @Override
    public V put(K key, V value) {
        V oldValue = null;
        Iterator<MapEntry<K,V>> it = entryList.iterator();
        while (it.hasNext()) {
            MapEntry<K,V> me = it.next();
            oldValue = me.getValue();
            if (me.getKey().equals(key))
                me.setValue(value);
        }
        entryList.add(new MapEntry<>(key, value));
        hashEntrylist().add(new MapEntry(key.hashCode(), value.hashCode()));
        // Sort (uses hashCode()) after adding:
        Collections.sort(entryList, comp1);
        Collections.sort(hashEntryList, comp2);
        return oldValue;
    }


    // Faster get() with sorting done in put:
    @Override
    public V get(Object key) { // key is type Object, not K
        // Same key, same hashCode via MapEntryKeyComp<K,V>:
        MapEntry<Integer,V> sub = new MapEntry<>(key.hashCode(), null);
        int i = Collections.binarySearch(hashEntryList, sub, comp2);
        if (i >= 0) return hashEntryList.get(i).getValue();
        return null;
    }
}


// Sort by key hashCode():
class MapEntryKeyComp<K,V> implements Comparator<MapEntry<K,V>> {

    @Override
    public int compare(MapEntry<K, V> me1, MapEntry<K, V> me2) {
        return me1.getKey().hashCode() - me2.getKey().hashCode();
    }
}

class MapPerformance36 {
    static List<Test<Map<Integer, Integer>>> tests =
            new ArrayList<>();

    static {
        tests.add(new Test<Map<Integer, Integer>>("put") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops =  tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    map.clear();
                    for (int j = 0; j < size; j++)
                        map.put(j, j);
                }
                return loops * size;
            }
        });

        tests.add(new Test<Map<Integer, Integer>>("get") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++) {
                    for (int j = 0; j < span; j++)
                        map.get(j);
                }
                return loops * span;
            }
        });

        tests.add(new Test<Map<Integer, Integer>>("iterate") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * map.size();
            }
        });
    }
}

public class Ex36 {

    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.defaultParams = TestParam.array(10, 100, 50, 50, 100, 20);
        println("Testing SlowMap36");
        SlowMap36a<String, String> m = new SlowMap36a<>();
        println("m: " + m);
        println("m.get(\"BURUNDI\"): " + m.get("BURUNDI"));
        println("m.entrySet(): " + m.entrySet());
        println("m.keySet(): " + m.keySet());
        println("m.values() = " + m.values());
        println("Two different maps: ");
        SlowMap36a<String,String> m2 = new SlowMap36a<>();
        m2.putAll(Countries.capitals(15));
        println("m.get(\"BURUNDI\"): " + m2.get("BURUNDI"));
        println("m.equals(m2): " + m.equals(m2));
        println("Maps with same entries: ");
        println("m.equals(m2): " + m.equals(m2));
        m.clear();
        println("After m.clear(), m.isEmpty(): " +
                m.isEmpty() + ", m = " + m);
        m2.keySet().clear();
        println("After m2.keySet().clear(), m2.isEmpty(): "
                + m2.isEmpty() + ", m2 = " + m2);
        println();
        println("Testing SlowMap36b:");
        SlowMap36b<String,String> m3 = new SlowMap36b<String,String>();
        m3.putAll(Countries.capitals(15));
        println("m3: " + m3);
        println("m3.get(\"BURUNDI\"): " + m3.get("BURUNDI"));
        println("m3.entrySet(): " + m3.entrySet());
        println("m3.hashEntryList(): " + m3.hashEntrylist());
        m3.clear();
        println("After m3.clear(), m3.isEmpty(): " +
                m3.isEmpty() + ", m3 = " + m3);
        m3.keySet().clear();
        println("After m3.keySet().clear(), m3.isEmpty(): "
                + m3.isEmpty() + ", m3 = " + m3);
        println();
        println("Comparative time tests:");

        Tester.run(new SlowMap<>(), MapPerformance36.tests);
        Tester.run(new SlowMap36a<>(), MapPerformance36.tests);
        Tester.run(new SlowMap36b<>(), MapPerformance36.tests);
        Tester.run(new HashMap<>(), MapPerformance36.tests);
        Tester.run(new TreeMap<>(), MapPerformance36.tests);
        Tester.run(new LinkedHashMap<>(), MapPerformance36.tests);
        Tester.run(new WeakHashMap<>(), MapPerformance36.tests);
        Tester.run(new IdentityHashMap<>(), MapPerformance36.tests);
        Tester.run(new Hashtable<>(), MapPerformance36.tests);
    }
}
