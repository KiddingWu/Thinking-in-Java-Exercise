package holding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static cn.org.kidding.util.Print.*;
import static cn.org.kidding.util.Print.print;
import static cn.org.kidding.util.Print.println;

/**
 * Change 8 Exercise 9 in the Polymorphism chapter to use an ArrayList to
 * hold the Rodents and an Iterator to move through the sequence of
 * Rodents.
 */

class RandomRodentGenerator {
    private Random rand = new Random();
    public Rodent next() {
        switch (rand.nextInt(3)) {
            default:
            case 0: return new Mouse();
            case 1: return new Mole();
            case 2: return new Hamster();
        }
    }
}

/**
 * 啮齿动物
 */
class Rodent {
    private String name = "Rodent";

    protected void eat() {
        println("Rodent.eat()");
    }
    protected void run() {
        println("Rodent.run()");
    }
    protected void sleep() {
        println("Rodent.sleep()");
    }
    public String toString() {
        return name;
    }
}

/**
 * 老鼠
 */
class Mouse extends Rodent {
    private String name = "Mouse";
    @Override
    protected void eat() {
        println("Mouse.eat()");
    }

    @Override
    protected void run() {
        println("Mouse.run()");
    }

    @Override
    protected void sleep() {
        println("Mouse.sleep()");
    }

    @Override
    public String toString() {
        return name;
    }
}

/**
 * 鼹鼠
 */
class Mole extends Rodent {
    private String name = "Mole";
    @Override
    protected void eat() {
        println("Mole.eat()");
    }

    @Override
    protected void run() {
        println("Mole.run()");
    }

    @Override
    protected void sleep() {
        println("Mole.sleep()");
    }

    @Override
    public String toString() {
        return name;
    }
}

/**
 * 大颊鼠
 */
class Hamster extends Rodent {
    private String name = "Hamster";
    @Override
    protected void eat() {
        println("Hamster.eat()");
    }

    @Override
    protected void run() {
        println("Hamster.run()");
    }

    @Override
    protected void sleep() {
        println("Hamster.sleep()");
    }

    @Override
    public String toString() {
        return name;
    }
}

public class Ex10 {

    private static RandomRodentGenerator gen =
            new RandomRodentGenerator();

    public static void main(String[] args) {
        List<Rodent> rodentList = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            rodentList.add(gen.next());

        Iterator<Rodent> it = rodentList.iterator();
        while (it.hasNext()) {
            Rodent r = it.next();
            print(r + ": ");
            r.eat();
            r.run();
            r.sleep();
        }

    }
}
