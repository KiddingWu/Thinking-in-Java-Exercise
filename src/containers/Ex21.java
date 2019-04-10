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
 * Modify SimpleHashMap.java to that it reports the number of
 * "probes" necessarys when collisions occur. That is, how
 * many calls to next() must be made on the Iterators that
 * walk the LinkedLists looking for matches?
 * @param <K>
 * @param <V>
 */
class SimpleHashMap21<K,V> extends AbstractMap<K,V> {

    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;

    // You can't have a physical array of generics,
    // but you can upcast to one:
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode() % SIZE);
        if (buckets[index] == null)
            buckets[index] = new LinkedList<>();
        LinkedList<MapEntry<K,V>> bucket = buckets[index];
        MapEntry<K,V> pair = new MapEntry<>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K,V>> it = bucket.listIterator();
        int probes = 0;
        while (it.hasNext()) {
            MapEntry<K,V> iPair = it.next();
            probes++;
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair);
                found = true;
                println("Collision at " +
                        iPair.getKey() + ": " + probes + " probe"
                        + ((probes == 1) ? "" : "s") + " needed");
                break;
            }
        }
        if (!found)
            buckets[index].add(pair);
        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode() % SIZE);
        if (!buckets[index].contains(key)) return null;
        for (MapEntry<K,V> iPair : buckets[index]) {
            if (iPair.getKey().equals(key))
                return iPair.getValue();
        }
        return null;
    }

    @Override
    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> set = new HashSet<>();
        for (LinkedList<MapEntry<K,V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K,V> mpair : bucket)
            set.add(mpair);
        }
        return set;
    }
}

public class Ex21 {
    public static void main(String[] args) {
        SimpleHashMap21<String,String> m = new SimpleHashMap21();
        m.putAll(Countries.capitals(5));
        println(m);
        m.put("EGYPT", "Berlin?");
        m.put("EGYPT", "New York?");
        m.put("EGYPT", "Gairo");
        println();
        m.putAll(Countries.capitals(5));
        println(m);
    }
}
