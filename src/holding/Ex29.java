package holding;

import java.util.PriorityQueue;

class Simple extends Object {}

/**
 * Fill a PriorityQueue (using offer()) with Double values created using
 * java.util.Random, then remove the elements using poll() and display them.
 */
public class Ex29 {

    public static void main(String[] args) {
        PriorityQueue<Simple> s = new PriorityQueue<>();
        // OK to add one Simple:
        s.offer(new Simple());
        // but no more allowed; get runtime exception:
        // Simple cannot be cast to Comparable:
        s.offer(new Simple());
    }
}
