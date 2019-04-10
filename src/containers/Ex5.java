package containers;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static cn.org.kidding.util.Print.*;

/**
 * Modify CountingMapData.java to fully implement the flyweight
 * by adding a custom EntrySet class like the one in Countries.java.
 * See adding a custom solution CountingMapData5Alt.java.
 */

// Use AbstractMap by implementing entrySet()
class CountingMapData5 extends AbstractMap<Integer, String> {

    private static String[] chars =
            "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");

    static class Entry implements Map.Entry<Integer, String> {
        int index;

        Entry(int index) {
            this.index = index;
        }

        @Override
        public Integer getKey() {
            return index;
        }

        @Override
        public String getValue() {
            return chars[index % chars.length] +
                    Integer.toString(index / chars.length) ;
        }

        @Override
        public String setValue(String value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean equals(Object o) {
            return Integer.valueOf(index).equals(o);
        }

        @Override
        public int hashCode() {
            return Integer.valueOf(index).hashCode();
        }
    }

    // Use AbstractSet by implementing size() & iterator()
    // 定制 EntrySet 实现享元
    static class EntrySet extends AbstractSet<Map.Entry<Integer, String>> {
        private int size;
        EntrySet(int size) {
            if (size < 0)
                this.size = 0;
            // Can't be any bigger than the array:
            else if (size > chars.length)
                this.size = chars.length;
            else
                this.size = size;
        }

        @Override
        public int size() {
            return size;
        }

        private class Iter implements Iterator<Map.Entry<Integer, String>> {
            // Only one Entry object per Iterator:
            private Entry entry = new Entry(-1);

            @Override
            public boolean hasNext() {
                return entry.index < size - 1;
            }

            @Override
            public Map.Entry<Integer, String> next() {
                entry.index++;
                return entry;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        @Override
        public Iterator<Map.Entry<Integer, String>> iterator() {
            return new Iter();
        }
    }

    private Set<Map.Entry<Integer, String>> entries = new EntrySet(chars.length);

    @Override
    public Set<Map.Entry<Integer, String>> entrySet() {
        return entries;
    }

    static Map<Integer, String> select(final int size) {
        return new CountingMapData5() {
            public Set<Map.Entry<Integer, String>> entrySet() {
                return new EntrySet(size);
            }
        };
    }
}

public class Ex5 {
    public static void main(String[] args) {
        println(new CountingMapData5());
        println(CountingMapData5.select(10));
    }
}
