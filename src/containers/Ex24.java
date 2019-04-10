package containers;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static cn.org.kidding.util.Print.*;

/**
 * Follwing the example in SimpleHashMap.java,
 * create and test a SimpleHashSet.
 * @param <E>
 */
class SimpleHashSet<E> implements Set<E> {

    static final int SIZE = 997;

    LinkedList<E>[] buckets = new LinkedList[SIZE];

    @Override
    public int size() {
        int result = 0;
        for (LinkedList bucket : buckets)
            if (bucket != null) result += bucket.size();
        return result;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = Math.abs(o.hashCode()) % SIZE;
        if (buckets[index] == null) return false;
        for (E e : buckets[index])
            if (e.equals(o)) return true;
        return false;
    }

    // Three methods to help with proper iteration by SimpleHashSet.iterator():
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
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = -1;
            @Override
            public boolean hasNext() {
                return index < SimpleHashSet.this.size() - 1;
            }

            @Override
            public E next() {
                int i = ++index;
                for (int j = 0; j < SIZE ; j++) {
                    if ((start(j) <= index) && (index < end(j)))
                        return buckets[j].get(index - start(j));
                }
                return null;
            }

            @Override
            public void remove() {
                for (int j = 0; j < SIZE; j++)
                    if ((start(j) <= index) && (index < end(j)))
                        buckets[j].remove(index - start(j));
                index--;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size()];
        Iterator<E> it = this.iterator();
        for (int i = 0; i < this.size(); i++)
            result[i] = it.next();
        return result;
    }

    @Override
    public boolean add(E e) {
        if (this.contains(e)) return false;
        int index = Math.abs(e.hashCode()) % SIZE;
        if (buckets[index] == null)
            buckets[index] = new LinkedList<>();
        buckets[index].add(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = Math.abs(o.hashCode()) % SIZE;
        if (buckets[index] == null) return false;
        for (E e : buckets[index]) {
            if (e.equals(o)) {
                buckets[index].remove(o);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int count = 0;
        for (Object o : c)
            if (this.contains(o)) count++;
        if (count == this.size()) return true;
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int start = this.size();
        for (E e : c) this.add(e);
        return (this.size() > start);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int n = this.size();
        for (LinkedList<E> bucket : buckets) {
            for (E e : bucket)
                if (!c.contains(e)) this.remove(e);
        }
        if (n != this.size()) return true;
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int n = this.size();
        for (Object o : c) this.remove(o);
        if (n != this.size()) return true;
        return false;
    }

    @Override
    public void clear() {
        for (LinkedList<E> bucket : buckets)
            if (bucket != null) bucket.clear();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Iterator<E> it = this.iterator();
        for (int i = 0; i < this.size(); i++) {
            E x = it.next();
            try {
                a[i] = (T) x;
            } catch (ClassCastException e) {
                throw new RuntimeException(e);
            }
        }
        return a;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (LinkedList<E> bucket : buckets)
            if (bucket != null)
                for (E e : bucket)
                    if (e != null) result += e.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SimpleHashSet) {
            if ((this.size() == ((SimpleHashSet)o).size())) {
                int count = 0;
                Iterator it = ((SimpleHashSet)o).iterator();
                while (it.hasNext())
                    if (this.contains(it.next())) count++;
                if (count == this.size()) return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.size() == 0) return "[]";
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (LinkedList<E> bucket : buckets) {
            if (bucket != null)
                for (E e : bucket)
                    s.append(String.valueOf(e) + " ");
        }
        s.replace(s.length() - 1, s.length(), "]");
        return s.toString();
    }
}

public class Ex24 {

    public static void main(String[] args) {
        SimpleHashSet<String> shs = new SimpleHashSet<String>();
        println("New empty SimpleHashSet24, shs = " + shs);
        shs.add("hi");
        shs.add("there");
        println("After shs.add(\"hi\") and shs.add(\"there\"), shs = " + shs);
        List<String> list = Arrays.asList("you", "cutie", "pie");
        shs.addAll(list);
        println("After shs.addAll(list) (you, cutie, pie), shs = " + shs);
        println("shs.size() = " + shs.size());
        println("shs.contains(\"you\"): " + shs.contains("you"));
        println("shs.contains(\"me\"): " + shs.contains("me"));
        println("shs.containsAll(list): " + shs.containsAll(list));
        SimpleHashSet<String> shs2 = new SimpleHashSet<String>();
        println("New empty shs2 = " + shs2);
        println("shs.containsAll(list): " + shs.containsAll(list));
        println("shs2.containAll(list): " + shs2.containsAll(list));
        println("shs.containsAll(shs2): " + shs.containsAll(shs2));
        println("shs2.containAll(shs2): " + shs2.containsAll(shs2));
        shs2.add("you");
        shs2.add("cutie");
        println("After shs2.add(\"you\"), shs2.add(\"cutie\"), shs2 = " + shs2);
        shs.removeAll(shs2);
        println("After shs.removeAll(shs2), shs = " + shs);
        println("shs.hashCode() = " + shs.hashCode());
        println("shs2 = " + shs2);
        println("shs2.isEmpty(): " + shs2.isEmpty());
        shs2.clear();
        println("After shs2.clear(), shs2.isEmpty(): " + shs2.isEmpty());
        List<String> list2 = Arrays.asList("hi", "there", "pie");
        shs2.addAll(list2);
        println("After shs2.addAll(list2) (hi, there, pie), shs2 = " + shs2);
        println("shs.equals(shs2): " + shs.equals(shs2));
        String[] sa = new String[3];
        shs.toArray(sa);
        printnb("After String[] sa = new String[3], shs.toArray(sa), sa holds: ");
        for(int i = 0; i < sa.length; i++) printnb(sa[i] + " " );
    }
}
