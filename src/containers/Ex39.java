package containers;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import cn.org.kidding.util.Countries;
import cn.org.kidding.util.MapData;
import cn.org.kidding.util.RandomGenerator;
import containers.example.MapEntry;

import static cn.org.kidding.util.Print.*;

/**
 * Add a private rehash() method to SimpleHashMap that is invoked
 * when the load foactor exceeds 0.75. During rehashing, double the
 * number of buckets, then search for the first prime number greater
 * than that to determine the new number of buckets.
 */

// Helper class, to finds first prime after an int:
class Prime {
    public static int firstPrimeAfter(int n) {
        for (int i = n + 1; i > n; i++) {
            int factors = 0;
            for (int j = 1; j < (i + 2) / 2; j++) {
                if ((i % j) == 0) factors++;
            }
            if (factors < 2) return i;
        }
        return 0;
    }
}

class SimpleHashMap39<K,V> extends AbstractMap<K,V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;
    // capacity field starts equal to SIZE
    private int capacity = SIZE;
    // You can't have a physical array of generics,
    // but you can upcast to one:
    LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];

    public int capacity() {
        return capacity;
    }

    // method to appropriately increase capacity, and
    // refill the new buckets with the old data:
    public void rehash() {
        capacity = Prime.firstPrimeAfter(capacity * 2);
        LinkedList<MapEntry<K,V>>[] oldBuckets  = buckets;
        buckets = new LinkedList[capacity];
        for (LinkedList<MapEntry<K,V>> bucket : oldBuckets) {
            if (bucket == null) continue;
            for (MapEntry<K,V> mpair : bucket)
                this.put(mpair.getKey(), mpair.getValue());
        }
    }

    @Override
    public V put(K key, V value) {
        // Check and call rehash() if needed:
        if (this.size() > 0.75 * capacity) rehash();
        V oldValue = null;
        MapEntry<K,V> pair = new MapEntry<>(key, value);
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
            buckets[index].add(pair);
        }
        for (MapEntry<K,V> mpair : buckets[index]) {
            if (mpair.getKey().equals(key)) {
                oldValue = mpair.getValue();
                mpair.setValue(pair.getValue());
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
            if (iPair.getKey().equals(key))
                return iPair.getValue();
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
}

public class Ex39 {

    public static void main(String[] args) {
        RandomGenerator.Integer rgi = new RandomGenerator.Integer(10000);
        println("Testing map m of <String,String>: the basics:");
        SimpleHashMap39<String,String> m = new SimpleHashMap39<String,String>();
        m.putAll(Countries.capitals(10));
        println("m: " + m);
        println("m.get(\"CHAD\") " + m.get("CHAD"));
        println("m.size() = " + m.size());
        println("m.capacity() = " + m.capacity());
        println("Testing rehash() for a map m2 of <Integer,Integer>:");
        SimpleHashMap39<Integer,Integer> m2 = new SimpleHashMap39<Integer,Integer>();
        println("m2: " + m2);
        println("m2.size() = " + m2.size());
        println("m2.capacity() = " + m2.capacity());
        m2.putAll(MapData.map(rgi, rgi, 996));
        println("after m2.putAll(MapData.map(rgi, rgi, 996): ");
        println("m2.size() = " + m2.size());
        println("m2.capacity() = " + m2.capacity());
        m2.putAll(MapData.map(rgi, rgi, 2000));
        println("after m2.putAll(MapData.map(rgi, rgi, 2000): ");
        println("m2.size() = " + m2.size());
        println("m2.capacity() = " + m2.capacity());
        m2.putAll(MapData.map(rgi, rgi, 1500));
        println("after m2.putAll(MapData.map(rgi, rgi, 1500): ");
        println("m2.size() = " + m2.size());
        println("m2.capacity() = " + m2.capacity());
    }
}
