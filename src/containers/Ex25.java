package containers;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import cn.org.kidding.util.Countries;

/**
 * Instead of using a ListIterator for each bucket, modify MapEntry so that
 * it is a self-contained singly linked list (each MapEntry should have a
 * forward link to the next MapEntry). Modify the rest of the code in
 * SimpleHashMap.java so that this new approach works correctly.
 */
class SimpleHashMap25<K,V> extends AbstractMap<K,V> {

    // 成为一种自包含的单向链表（每个 MapEntry 应该都有一个指向下一个 MapEntry 的前链接），
    // 从而不用对每个桶位都使用 ListIterator
    class MapEntry25<K,V> implements Map.Entry<K,V> {
        private K key;
        private V value;

        //so that it is a self-contained singly linked list
        // (each MapEntry should have a forward link to the next MapEntry).
        private MapEntry25<K,V> nextEntry = null;

        public MapEntry25(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V v) {
            V result = this.value;
            value = v;
            return result;
        }

        public MapEntry25<K, V> getNextEntry() {
            return nextEntry;
        }

        public void setNextEntry(MapEntry25<K, V> nextEntry) {
            this.nextEntry = nextEntry;
        }

        @Override
        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof MapEntry25)) return false;
            MapEntry25 me = (MapEntry25) obj;
            return
                    (key == null ? null : key.equals(me.getKey())) &&
                            (value == null ? null : value.equals(me.getValue()));

        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    // Choose a prime number for the hash table
    // size to achieve a uniform distribution:
    static final int SIZE = 997;
    // You can't have a physical array of generics,
    // but you can upcast to one:
    LinkedList<MapEntry25<K,V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) return null;
        for (MapEntry25<K,V> iPair : buckets[index])
            if (iPair.getKey().equals(key))
                return iPair.getValue();
        return null;
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        MapEntry25<K,V> pair = new MapEntry25<>(key, value);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
            buckets[index].add(pair);
        }
        LinkedList<MapEntry25<K,V>> bucket = buckets[index];
        if (buckets[index].size() > 0) {
            for (MapEntry25<K,V> entry = bucket.get(0); entry != null;
            entry = entry.getNextEntry()) {
                if (entry.getKey().equals(key)) {
                    oldValue = entry.getValue();
                    entry.setValue(value);
                    return oldValue;
                }
            }
            bucket.add(pair);
            int i = bucket.indexOf(pair);
            if (i > 0) bucket.get(i - 1).setNextEntry(pair);
            return pair.getValue();
        }
        return oldValue;
    }

    @Override

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K,V>> set = new HashSet<Entry<K, V>>();
        for (LinkedList<MapEntry25<K,V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry25<K,V> mpair : bucket)
                set.add(mpair);
        }
        return set;
    }
}

public class Ex25 {
    public static void main(String[] args) {
        SimpleHashMap25<String, String> m =
                new SimpleHashMap25<>();
        m.putAll(Countries.capitals(5));
        System.out.println(m);
        System.out.println(m.put("BENIN", "New York?"));
        System.out.println(m.put("BENIN", "Porto-Novo"));
        System.out.println(m.get("BENIN"));
        System.out.println(m.entrySet());
    }
}
