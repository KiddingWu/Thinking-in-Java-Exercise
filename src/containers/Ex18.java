package containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static cn.org.kidding.util.Print.*;

/**
 * Using SlowMap.java for inspiration, Create a SlowSet.
 */
class SlowSet18<E> implements Set<E> {

    private List<E> elements = new ArrayList();

    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < elements.size(); i++)
            result += elements.get(i).hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SlowSet18) {
            if ((elements.size() == ((SlowSet18) obj).size())) {
                int count = 0;
                for (int i = 0; i < elements.size(); i++)
                    if (elements.get(i).equals(((SlowSet18) obj).elements.get(i))) count++;
                if (count == elements.size()) return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return (elements.size() == 0);
    }

    @Override
    public boolean contains(Object o) {
        return elements.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = - 1;
            @Override
            public boolean hasNext() {
                return index < elements.size() - 1;
            }

            @Override
            public E next() {
                ++index;
                return elements.get(index);
            }

            @Override
            public void remove() {
                elements.remove(index--);
            }
        };
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public boolean add(E e) {
        if (elements.contains(e)) return false;
        return elements.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return elements.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int count = 0;
        for (Object o : c)
            if (elements.contains(o)) count++;
        if (count == c.size()) return true;
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return elements.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int n = elements.size();
        for (int i = 0; i < elements.size(); i++) {
            E e = elements.get(i);
            if (!c.contains(e)) elements.remove(e);
        }
        if (n != elements.size()) return true;
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int n = elements.size();
        for (Object o : c) elements.remove(o);
        if (n != elements.size()) return true;
        return false;
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return elements.toArray(a);
    }

    @Override
    public String toString() {
        if (elements.size() == 0) return "[]";
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < elements.size() - 1; i++)
            s.append(elements.get(i) + ", ");
        s.append(elements.get(elements.size() - 1));
        s.append("]");
        return s.toString();

    }
}

public class Ex18 {

    public static void main(String[] args) {
        SlowSet18<String> ss = new SlowSet18<String>();
        ss.add("hi");
        println(ss);
        ss.add("there");
        println(ss);
        List<String> list = Arrays.asList("you", "cutie", "pie");
        ss.addAll(list);
        println(ss);
        println("ss.size() = " + ss.size());
        println("ss.contains(\"you\"): " + ss.contains("you"));
        println("ss.contains(\"me\"): " + ss.contains("me"));
        println("ss.containsAll(list): " + ss.containsAll(list));
        SlowSet18<String> ss2 = new SlowSet18<String>();
        println("ss2 = " + ss2);
        println("ss.containsAll(ss2): " + ss.containsAll(ss2));
        println("ss2.containAll(ss): " + ss2.containsAll(ss));
        ss2.add("you");
        ss2.add("cutie");
        ss.removeAll(ss2);
        println("ss = " + ss);
        println("ss.hashCode() = " + ss.hashCode());
        List<String> list2 = Arrays.asList("hi", "there", "pie");
        ss2.remove("you");
        println(ss2);
        println("ss2.isEmpty(): " + ss2.isEmpty());
        ss2.clear();
        println("ss2.isEmpty(): " + ss2.isEmpty());
        ss2.addAll(list2);
        println("ss2 = " + ss2);
        println("ss.equals(ss2): " + ss.equals(ss2));
        String[] sa = new String[3];
        println("ss.toArray(sa): " + ss.toArray(sa));
        for(int i = 0; i < sa.length; i++) printnb(sa[i] + " " );
    }
}
