package containers;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.WeakHashMap;

import cn.org.kidding.util.Countries;
import containers.example.MapEntry;
import containers.example.Test;
import containers.example.TestParam;
import containers.example.Tester;

/**
 * Modify SimpleHashMap to use ArrayList s instead of LinkedLists.
 * Modify MapPerformance.java to compare the performance of the two
 * implementations.
 */
class SimpleHashMap37a<K,V> extends AbstractMap<K,V> {

    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;
    // You can't have a physical  array of generics,
    // but you can upcast to one:
    LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        MapEntry<K,V> pair = new MapEntry<>(key, value);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
            buckets[index].add(pair);
        }
        for (MapEntry<K,V> iPair : buckets[index]) {
            if (iPair.getKey().equals(key)) {
               oldValue = iPair.getValue();
               iPair.setValue(pair.getValue());
               break;
            }
        }
        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) return null;
        for (MapEntry<K,V> iPair : buckets[index])
            if (iPair.getKey().equals(key)) return iPair.getValue();
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K,V>> set = new HashSet<>();
        for (LinkedList<MapEntry<K,V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K,V> mpair : bucket)
                set.add(mpair);
        }
        return set;
    }

    public static void main(String[] args) {
        SimpleHashMap37a<String, String> m =
                new SimpleHashMap37a<>();
        m.putAll(Countries.capitals(25));
        System.out.println(m);
        System.out.println(m.get("ETHIOPIA"));
        m.put("ETHIOPIA", "abc");
        System.out.println(m.get("ETHIOPIA"));
        System.out.println(m.entrySet());
    }
}

class SimpleHashMap37b<K,V> extends AbstractMap<K,V> {

    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;
    // You can't have a physical array of generics,
    // but you can upcast to one:s
    ArrayList<MapEntry<K,V>>[] buckets = new ArrayList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        MapEntry<K,V> pair = new MapEntry<>(key, value);
        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
            buckets[index].add(pair);
        }
        ArrayList<MapEntry<K,V>> bucket = buckets[index];
        ListIterator<MapEntry<K,V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K,V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair); // Replace old with new
                break;
            }
        }
        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode() % SIZE);
        if (buckets[index] == null) return null;
        for (MapEntry<K,V> iPair : buckets[index])
            if (iPair.getKey().equals(key)) return iPair.getValue();
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K,V>> set = new HashSet<>();
        for (ArrayList<MapEntry<K,V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K,V> iPair : bucket)
                set.add(iPair);

        }
        return set;
    }

    public static void main(String[] args) {
        SimpleHashMap37b<String, String> m =
                new SimpleHashMap37b<>();
        m.putAll(Countries.capitals(25));
        System.out.println(m);
        System.out.println(m.get("ANGOLA"));
        m.put("ANGOLA", "abc");
        System.out.println(m.get("ANGOLA"));
        System.out.println(m.entrySet());
    }
}

class MapPerformance37 {

    static List<Test<Map<Integer, Integer>>> tests = new ArrayList<>();
    static {
        tests.add(new Test<Map<Integer, Integer>>("put") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    map.clear();
                    for (int j = 0; j < size; j++)
                        map.put(j,j);
                }
                return loops * size;
            }
        });

        tests.add(new Test<Map<Integer, Integer>>("get") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        map.get(j);
                return loops * span;
            }
        });

        tests.add(new Test<Map<Integer, Integer>>("iterate") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext()) it.next();
                }
                return loops * map.size();
            }
        });
    }
}
public class Ex37 {
    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.defaultParams  = TestParam.array(10, 1000, 100, 1000, 1000, 100);
        Tester.run(new SimpleHashMap37a<>(), MapPerformance37.tests);
        Tester.run(new SimpleHashMap37b<>(), MapPerformance37.tests);
        Tester.run(new HashMap<>(), MapPerformance37.tests);
        Tester.run(new TreeMap<>(), MapPerformance37.tests);
        Tester.run(new LinkedHashMap<>(), MapPerformance37.tests);
        Tester.run(new WeakHashMap<>(), MapPerformance37.tests);
        Tester.run(new IdentityHashMap<>(), MapPerformance37.tests);
        Tester.run(new TreeMap<>(), MapPerformance37.tests);

    }
}
