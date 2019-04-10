package containers;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import cn.org.kidding.util.Countries;
import containers.example.MapEntry;

import static cn.org.kidding.util.Print.*;

/**
 * Implement the clear() and remove() methods for SimpleHashMap.
 */
class SimpleHashMap22<K,V> extends AbstractMap<K,V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;
    // You can't have a physical array of generics,
    // but you can upcast to one:
    LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null)
            buckets[index] = new LinkedList<>();
        MapEntry<K,V> pair = new MapEntry<>(key, value);
        LinkedList<MapEntry<K,V>> bucket = buckets[index];
        ListIterator<MapEntry<K,V>> it = bucket.listIterator();
        boolean found = false;
        while (it.hasNext()) {
            MapEntry<K,V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair);
                found = true;
                break;
            }
        }
        if (!found)
            buckets[index].add(pair);
        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) return null;
        for (MapEntry<K,V> iPair : buckets[index])
            if (iPair.getKey().equals(key))
                return iPair.getValue();
        return null;
    }

    @Override
    public void clear() {
        for (LinkedList<MapEntry<K,V>> bucket : buckets)
            if (bucket != null) bucket.clear();
    }

    @Override
    public V remove(Object o) {
        V v = null;
        if (this.get(o) != null) {
            int index = Math.abs(o.hashCode()) % SIZE;
            for (MapEntry<K,V> iPair : buckets[index])
                if (iPair.getKey().equals(o)) {
                    v = iPair.getValue();
                    int i = buckets[index].indexOf(iPair);
                    buckets[index].remove(i);
                    break;
                }
        }
        return v;
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
}

public class Ex22 {
    public static void main(String[] args) {
        SimpleHashMap22<String, String> m =
                new SimpleHashMap22<>();
        m.putAll(Countries.capitals(5));
        println(m);
        println(m.get("ALGERIA"));
        println(m.remove("ALGERIA"));
        println(m.get("ALGERIA"));
        println(m.remove("ANGOLA"));
        println(m);
        m.clear();
        println(m);
    }
}
