package holding;

import java.util.ArrayList;
import java.util.List;

interface Selector {
    boolean end();  //最后
    Object current(); //当前
    void next();    //下一个
}

class Sequence {

    /**
     * ArrayList 和 LinkedList 都是按照被插入的顺序保存元素。
     */
    private List<Object> items = new ArrayList<>();

    public void add(Object x) {
        items.add(x);
    }

    private class SequenceSelector implements Selector {

        private int i = 0;
        @Override
        public boolean end() {
            return i == items.size();
        }

        @Override
        public Object current() {
            return items.get(i);
        }

        @Override
        public void next() {
            i++;
        }
    }

    public Selector selector() {
        return new SequenceSelector();
    }
}

/**
 * Modify innerclasses/Sequence.java so that you can add any number
 */
public class Ex3 {
    public static void main(String[] args) {
        Sequence s3 = new Sequence();
        for (int i = 0; i < 10; i++)
            s3.add(i);
        Selector selector = s3.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
        s3.add(10);
        s3.add(11);
        s3.add(12);
        s3.add(13);
        s3.add("good bye");
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}


