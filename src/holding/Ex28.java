package holding;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Fill a PriorityQueue (using offer()) with Double values created using
 * java.util.Random, then remove the elements using poll() and display them.
 */
public class Ex28 {

    public static void main(String[] args) {
        Random rand = new Random();
        PriorityQueue<Double> d = new PriorityQueue<>();
        for (int i = 0; i < 10; i++)
            d.offer(rand.nextDouble() * i);
        while (d.peek() != null)
            System.out.println(d.poll() + " ");
    }
}
