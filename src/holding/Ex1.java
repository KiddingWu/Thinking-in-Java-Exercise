package holding;

import java.util.ArrayList;
import java.util.List;

/**
 * Create a new class called Gerbil with an int gerbilNumber that's
 * initialized int the constructor. Give it a method called hop() that displays
 * which gerbil number that is, and that it's hopping. Create an ArrayList and
 * add Gerbil objects to the List. Now use the get() method to move through
 * the List and call hop() for each Gerbil.
 */
class Gerbil {

    private int gerbilNumber;

    public Gerbil(int i) {
       this.gerbilNumber = i;
    }

    void hop() {
        System.out.println("Gerbil " + gerbilNumber + " hops");
    }
}

public class Ex1 {
    public static void main(String[] args) {
        List<Gerbil> gerbils = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            gerbils.add(new Gerbil(i));
        for (int i = 0; i < 10; i++)
            gerbils.get(i).hop();

        // or, alternatively, using foreach syntax;
        for (Gerbil g : gerbils)
            g.hop();
    }
}
