package holding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Modify Exercise 1 so it users an Iterator to move through the List while
 * calling hop().
 */
public class Ex8 {
    public static void main(String[] args) {

        List<Gerbil> gerbils = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            gerbils.add(new Gerbil(i));

        Iterator<Gerbil> it = gerbils.iterator();
        while (it.hasNext())
            it.next().hop();

    }
}
