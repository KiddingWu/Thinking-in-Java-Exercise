package containers;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.org.kidding.util.CountingMapData;
import containers.example.MapEntry;
import containers.example.SlowMap;

import static cn.org.kidding.util.Print.*;

/**
 * Apply the tests in Maps.java to SlowMap to verify that it works.
 * Fix anything in SlowMap that doesn't work properly.
 */
class SlowMap16<K,V> extends AbstractMap<K,V> {

    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();
    private EntrySet entries = new EntrySet();

    @Override
    public Set<Entry<K, V>> entrySet() {
        return entries;
    }

    public V put(K key, V value) {
        V oldValue = get(key); // The old value or null
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else {
            values.set(keys.indexOf(key), value);
        }
        return oldValue;
    }

    public V get(Object key) { // key is type Object, not K
        if (!keys.contains(key))
            return null;
        return values.get(keys.indexOf(key));
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public int size() {
            return keys.size();
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new Iterator<Entry<K, V>>() {
                private int index = -1;
                @Override
                public boolean hasNext() {
                    return index < keys.size() - 1;
                }

                @SuppressWarnings("unchecked")
                @Override
                public Map.Entry<K, V> next() {
                    int i = ++index;
                    return new MapEntry<>(keys.get(i), values.get(i));
                }

                @Override
                public void remove() {
                    keys.remove(index);
                    values.remove(index--);
                }
            };
        }
    }
}

public class Ex16 {

    public static void printKeys(Map<Integer, String> map) {
        println("Size = " + map.size() + ", ");
        print("Keys: " );
        println(map.keySet()); // Produce a Set of the keys
    }

    public static void test(Map<Integer, String> map) {
        println(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        // Map has 'Set' behavior for keys:
        map.putAll(new CountingMapData(25));
        printKeys(map);
        // Producing a Collection of the values:
        print("Values: ");
        println(map.values());
        println(map);
        println("map.containsKey(11): " + map.containsKey(11));
        println("map.get(11): " + map.get(11));
        println("map.containsValue(\"F0\"): " +
                map.containsKey("F0"));
        Integer key = map.keySet().iterator().next();
        println("First key in map: " + key);
        map.remove(key);
        printKeys(map);
        map.clear();
        println("map.isEmpty(): " + map.isEmpty());
        map.putAll(new CountingMapData(25));
        // Operations on the Set change the Map:
        map.keySet().removeAll(map.keySet());
        println("map.isEmpty(): " + map.isEmpty());
    }

    public static void main(String[] args) {
        test(new HashMap<Integer, String>());
        println();
        test(new SlowMap<Integer, String>());
        println();
        test(new SlowMap16<Integer, String>());

    }
}
