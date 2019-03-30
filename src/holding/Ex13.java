package holding;


import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import cn.org.kidding.controller.Event;

/**
 * In the innerclasses/GreenhouseController.java example, the class
 * Controller uses an ArrayList. Change the code to use a LinkedList
 * instead. and use an Iterator to cycle through the set of events.
 */
public class Ex13 {

    // A class from java.util to hold event objects:
    private List<Event> eventList = new LinkedList<>();
    public void add(Event c) {
        eventList.add(c);
    }
    public void run() {
        LinkedList<Event> eventListCopy =
                new LinkedList<>(eventList);
        ListIterator<Event> it = eventListCopy.listIterator();
        while (it.hasNext()) {
            it.next().action();
            it.previous();
            System.out.println(it.next());
        }
    }
}
