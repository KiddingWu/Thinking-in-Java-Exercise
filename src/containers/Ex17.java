package containers;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.org.kidding.util.Countries;
import containers.example.MapEntry;

import static cn.org.kidding.util.Print.*;

/**
 * Implement the rest of the Map interface for SlowMap.
 */
class SlowMap17<K,V> implements Map<K,V> {

    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();
    private EntrySet entries = new EntrySet();
    private Set<K> keySet = new KeySet();

    public class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public int size() {
            return keys.size();
        }

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new Iterator<Entry<K, V>>() {
                private int index = -1;
                @Override
                public boolean hasNext() {
                    return index < keys.size() - 1;
                }

                @Override
                public Entry<K, V> next() {
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

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return this.entrySet().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.contains(value);
    }

    @Override
    public V get(Object key) { // key is type Object, not K
        if (!keys.contains(key))
            return null;
        return values.get(keys.indexOf(key));
    }

    @Override
    public V put(K key, V value) {
        V oldValue = value;
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else {
            values.set(keys.indexOf(key), value);
        }
        return oldValue;
    }

    @Override
    public V remove(Object key) {
        V v = this.get(key);
        int i = keys.indexOf(key);
        keys.remove(i);
        values.remove(i);
        return v;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> me : m.entrySet())
            this.put(me.getKey(), me.getValue());
    }

    @Override
    public void clear() {
        this.entrySet().clear();
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    private class KeySet extends AbstractSet<K> {

        @Override
        public int size() {
            return keys.size();
        }

        @Override
        public Iterator<K> iterator() {
            return new Iterator<K>() {
                private int index = -1;
                @Override
                public boolean hasNext() {
                    return index < keys.size() - 1;
                }

                @Override
                public K next() {
                    ++index;
                    return keys.get(index);
                }

                @Override
                public void remove() {
                    keys.remove(index--);
                }
            };
        }
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return entries;
    }

    @Override
    public boolean equals(Object o) {
        if ((o instanceof SlowMap17)) {
            if (this.entrySet().equals(((SlowMap17)o).entrySet()))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.entrySet().hashCode();
    }

    @Override
    public String toString() {
        return this.entrySet().toString();
    }
}

public class Ex17 {
    public static void main(String[] args) {
        SlowMap17<String,String> m =  new SlowMap17<>();
        m.putAll(Countries.capitals(15));
        println("m: " + m);
        println("m.get(\"BURUNDI\"): " + m.get("BURUNDI"));
        println("m.entrySet(): " + m.entrySet());
        println("m.keySet(): " + m.keySet());
        println("m.values(): " + m.values());
        println("Two different maps: ");
        SlowMap17<String,String> m2 = new SlowMap17<>();
        println("m.equals(m2): " + m.equals(m2));
        m2.putAll(Countries.capitals(15));
        println("Maps with same entries: ");
        println("m.equals(m2): " + m.equals(m2));
        m.clear();
        println("After m.clear(), m.isEmpty(): " +
                m.isEmpty() + ", m = " + m);
        m2.keySet().clear();
        print("After m2.keySet().clear(), m2.isEmpty(): " +
                m2.isEmpty() + ", m2 = " + m2);
    }
}
