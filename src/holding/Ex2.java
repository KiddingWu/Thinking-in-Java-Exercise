package holding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// Modify SimpleConllection.java to use a Set for c.

public class Ex2 {

    public static void main(String[] args) {
        //use Set：只有元素不存在的情况下才会添加
        Set<Integer> c = new HashSet<>();
        for (int j = 0; j < 10 ; j++)
            c.add(j);
        for (Integer j : c)
            System.out.print(j + ", ");
    }
}

class SimpleCollection {
    public static void main(String[] args) {
        Collection<Integer> c = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            c.add(i);
        for (Integer i : c)
            System.out.print(i + ", ");

    }

}
