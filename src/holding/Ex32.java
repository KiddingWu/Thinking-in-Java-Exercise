package holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import static cn.org.kidding.util.Print.*;

/**
 * Following the example of MultiIterableClass, add reversed() and randomized()
 * methods to NonCollectionSequence.java, as well as making NonCollectionSequence.java
 * implement Iterable and show that all the approaches * work in foreach statements.
 */
class PetSequence {
    protected Pet[] pets = Pets.createArray(8);
}

class NonCollectionSequence32 extends PetSequence implements Iterable<Pet> {

    @Override
    public Iterator<Pet> iterator() {
        return new Iterator<Pet>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < pets.length;
            }

            @Override
            public Pet next() {
                return pets[index++];
            }
        };
    }

    public Iterable<Pet> reversed() {
        return new Iterable<Pet>() {
            @Override
            public Iterator<Pet> iterator() {
                return new Iterator<Pet>() {
                    private int index = pets.length - 1;
                    @Override
                    public boolean hasNext() {
                        return index > -1;
                    }

                    @Override
                    public Pet next() {
                        return pets[index--];
                    }

                    @Override
                    public void remove() { // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public Iterable<Pet> randomized() {
        return new Iterable<Pet>() {
            @Override
            public Iterator<Pet> iterator() {
                List<Pet> shuffled =
                        new ArrayList<>(Arrays.asList(pets));
                Collections.shuffle(shuffled, new Random());
                return shuffled.iterator();
            }
        };
    }
}

public class Ex32 {

    public static void main(String[] args) {
        NonCollectionSequence32 ncs = new NonCollectionSequence32();
        for (Pet p : ncs.reversed())
            print(p + " ");
        println();
        for (Pet p : ncs)
            print(p + " ");
        println();
        for (Pet p : ncs.randomized())
            print(p + " ");
    }
}
