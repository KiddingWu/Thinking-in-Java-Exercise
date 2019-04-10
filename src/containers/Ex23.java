package containers;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import cn.org.kidding.util.Countries;
import containers.example.MapEntry;

import static cn.org.kidding.util.Print.*;

/**
 * Implement the rest of the Map interface for SimpleHashMap.
 */
class SimpleHashMap23<K,V> implements Map<K,V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;

    // You can't have a physical array of generics,
    // but you can upcast to one:
    LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];

    private KeySet keys = new KeySet();
    private EntrySet entries = new EntrySet();

    @Override
    public int size() {
        int result = 0;
        for (LinkedList bucket : buckets)
            if (bucket != null) result += bucket.size();
        return result;
    }

    // Three methods to help with proper iteration by EntrySet.iterator():
    private int firstNonEmptyBucket() {
        if (buckets.length < 1) return -1;
        for (int j = 0; j < buckets.length; j++)
            if (buckets[j] != null) return j;
        return -1;
    }

    private int start(int i) {
        int first = this.firstNonEmptyBucket();
        if (i < first) return -1;
        if (i == first) return 0;
        int result = 0;
        for (int j = 0; j < i; j++)
            if (buckets[j] != null) result += buckets[j].size();
        return result;
    }

    private int end(int i) {
        int first = this.firstNonEmptyBucket();
        if (i < first) return -1;
        return start(i) + ((buckets[i] == null) ? 0 : buckets[i].size());
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        for (MapEntry<K,V> iPair : buckets[index])
            if (iPair.getKey().equals(key))
                return true;
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (LinkedList<MapEntry<K,V>> bucket : buckets) {
            if (bucket != null)
                for (MapEntry<K,V> iPair : bucket)
                    if (iPair.getValue().equals(value))
                        return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        for(MapEntry<K,V> iPair : buckets[index]) {
            if (iPair.getKey().equals(key))
                return iPair.getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null)
            buckets[index] = new LinkedList<>();
        MapEntry<K,V> pair = new MapEntry<>(key, value);
        LinkedList<MapEntry<K,V>> bucket = buckets[index];
        boolean found = false;
        ListIterator<MapEntry<K,V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K,V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair); // Replace old with new
                found = true;
                break;
            }
        }
        if (!found)
            buckets[index].add(pair);
        return oldValue;
    }

    @Override
    public V remove(Object key) {
        V v = null;
        if (this.get(key) != null) {
            int index = Math.abs(key.hashCode()) % SIZE;
            for (MapEntry<K,V> iPair : buckets[index])
                if (iPair.getKey().equals(key)) {
                    v = iPair.getValue();
                    int i = buckets[index].indexOf(iPair);
                    buckets[index].remove(i);
                    break;
                }
        }
        return v;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> me : m.entrySet())
            this.put(me.getKey(), me.getValue());
    }

    @Override
    public void clear() {
        for (LinkedList<MapEntry<K,V>> bucket : buckets)
            if (bucket != null) bucket.clear();
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

     private class KeySet extends AbstractSet<K> {
         @Override
         public int size() {
             return SimpleHashMap23.this.size();
         }

         @Override
         public Iterator<K> iterator() {
             return new Iterator<K>() {
                 private int index = - 1;
                 @Override
                 public boolean hasNext() {
                     return index < SimpleHashMap23.this.size() - 1;
                 }

                 @Override
                 public K next() {
                     int i = ++index;
                     for (int j = 0; j < SIZE; j++)
                         if ((start(j) <= index) && (index < end(j)))
                             return buckets[j].get(index - start(j)).getKey();
                     return null;
                 }

                 @Override
                 public void remove() {
                     for (int j = 0; j < SIZE; j++)
                         if (start(j) <= index && index < end(j))
                             buckets[j].remove(index - start(j));
                     index--;
                 }
             };
         }
     }

    @Override
    public Collection<V> values() {
        HashSet<V> vals = new HashSet<>();
        for (LinkedList<MapEntry<K,V>> bucket : buckets) {
            if (bucket != null)
                for (MapEntry<K,V> iPair : bucket)
                    vals.add(iPair.getValue());
        }
        return vals;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return entries;
    }

    private class EntrySet extends AbstractSet<Map.Entry<K,V>> {

        @Override
        public int size() {
            return SimpleHashMap23.this.size();
        }

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new Iterator<Entry<K, V>>() {
                private int index = -1;
                @Override
                public boolean hasNext() {
                    return index < SimpleHashMap23.this.size() - 1;
                }

                @Override
                public Entry<K, V> next() {
                    int i = ++index;
                    for (int j = 0; j < SIZE; j++) {
                        if ((start(j) <= index) && (index < end(j)))
                            return new MapEntry<>(
                                    buckets[j].get(index - start(j)).getKey(),
                                    buckets[j].get(index - start(j)).getValue());
                    }
                    return null;
                }

                @Override
                public void remove() {
                    for (int j = 0; j < SIZE; j++)
                        if (start(j) <= index && index < end(j))
                            buckets[j].remove(index - start(j));
                    index--;
                }
            };
        }
    }

    @Override
    public int hashCode() {
        return this.entrySet().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SimpleHashMap23) {
            if (this.entrySet().equals(((SimpleHashMap23)o).entrySet()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.entrySet().toString();
    }
}

public class Ex23 {

    public static void main(String[] args) {
        SimpleHashMap23<String,String> map =
                new SimpleHashMap23<String,String>();
        map.putAll(Countries.capitals(3));
        println("map = " + map);
        println("map.entrySet(): " + map.entrySet());
        println("map.keySet(): " + map.keySet());
        println("map.keySet(): " + map.keySet());
        println("map.values() = " + map.values());
        println("map.isEmpty(): " + map.isEmpty());
        println("map.containsKey(\"ALGERIA\"): " + map.containsKey("ALGERIA"));
        println("map.containsValue(\"Algiers\"): " + map.containsValue("Algiers"));
        println("map.get(\"ALGERIA\"): " + map.get("ALGERIA"));
        println("map.remove(\"ALGERIA\"): " + map.remove("ALGERIA"));
        println("After map.remove(\"ALGERIA\"), map.containsKey(\"ALGERIA\"): " +
                map.containsKey("ALGERIA"));
        println(" and map.get(\"ALGERIA\"): " + map.get("ALGERIA"));
        println(" and map: = " + map);
        map.clear();
        println("After map.clear(), map = " + map);
        println(" and map.isEmpty(): " + map.isEmpty());
        map.putAll(Countries.capitals(3));
        println("After map.putAll(Countries.capitals(3)), map = " + map);
        SimpleHashMap23<String,String> map2 =
                new SimpleHashMap23<String,String>();
        map2.putAll(Countries.capitals(4));
        println("After map2.putAll(Countries.capitals(4)), map2 = " + map2);
        println(" and map.equals(map2): " + map.equals(map2));
        map2.remove("BOTSWANA");
        println("After map2.remove(\"BOTSWANT\"), map.equals(map2): " + map.equals(map2));
        map.entrySet().clear();
        println("After map.entrySet().clear, map = " + map);
        map.putAll(Countries.capitals(3));
        println("After map.putAll(Countries.capitals(3)), map = " + map);
        map.keySet().clear();
        println("After map.keySet().clear(), map = " + map);
    }
}
