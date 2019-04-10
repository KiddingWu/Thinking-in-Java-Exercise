package containers;

import java.util.Iterator;
import java.util.LinkedList;

import static cn.org.kidding.util.Print.*;

/**
 * Using a LinkedList as your underlying implementation,
 * define your own SortedSet.
 */
class SortSeted10<E> extends LinkedList<E> {

    int compare(E e1, E e2) {
        int c = e1.hashCode() - e2.hashCode();
        return (c < 0) ? -1 : (c == 0) ? 0 : 1;
    }

    public boolean add(E e) {
        if (!this.contains(e)) {
            Iterator<E> it = this.iterator();
            int index = 0;
            while (it.hasNext()) {
                if (compare(it.next(), e) < 1)
                    index++;
            }
            add(index, e);
            return true;
        }
        return false;
    }

    public E remove() {
        return super.removeLast();
    }
}

class T {}

public class Ex10 {
    public static void main(String[] args) {
        SortSeted10<T> ss = new SortSeted10<>();
        ss.add(new T());
        ss.add(new T());
        ss.add(new T());
        ss.add(new T());
        print(ss);
        SortSeted10<Integer> ss2 = new SortSeted10<>();
        ss2.add(6);
        ss2.add(8);
        ss2.add(2);
        ss2.add(4);
        ss2.add(8);
        print(ss2);
        SortSeted10<String> ss3 = new SortSeted10<>();
        ss3.add("three");
        ss3.add("three");
        ss3.add("seven");
        ss3.add("hi");
        ss3.add("two");
        ss3.add("one");
        print(ss3);
        ss3.remove();
        print(ss3);
    }
}
