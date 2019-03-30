package holding;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Modify innerclasses/Sequence.java so that Sequence works with an Iterator
 * instead of a Selector.
 */
public class Ex9 {

    private List<Object> items = new ArrayList<>();
    public void add(Object x) {
        items.add(x);
    }

    public Iterator iterator() {
        return items.iterator();
    }

    public static void main(String[] args) {
        Ex9 s = new Ex9();
        for (int i = 0; i < 10; i++)
            s.add(Integer.toString(i));

        Iterator it = s.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }
    }
}
