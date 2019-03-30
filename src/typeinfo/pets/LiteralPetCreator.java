package typeinfo.pets;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Using class literals
public class LiteralPetCreator extends PetCreator {
    // No try block needed
    public static final List<Class<? extends Pet>> allTypes =
            Collections.unmodifiableList(Arrays.asList(
                    Pet.class, Mutt.class, Dog.class));

    private static final List<Class<? extends Pet>> types =
            allTypes.subList(allTypes.indexOf(Mutt.class),
                    allTypes.size());

    // Types for random creation:
    public List<Class<? extends Pet>> types() {
        return types;
    }

    public static void main(String[] args) {
        System.out.println(types);
    }
}
