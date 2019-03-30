package holding;

import java.net.CookieManager;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Write a class called Command that contains a String and has a method operation()
 * that displays the String. Write a second class with a method that fills a Queue
 * with Command objects and returns it. Pass the filled Queue to a method int a this
 * class that consumes the objects in the Queue and calls their operation() methods.
 */
class Command {
    String s;
    Command(String s) {
        this.s = s;
    }
    void operation() {
        System.out.print(s);
    }
}

class Build {
    Queue<Command> makeQ() {
        Queue<Command> q = new LinkedList<>();
        for (int i = 0; i < 10; i++)
            // offer(): 将元素插入到队尾，或者返回 false.
            q.offer(new Command(i + " "));
        return q;
    }
}

public class Ex27 {
    public static void commandEater(Queue<Command> qc) {
        // peek(): 不移除的情况下返回队头，方法在队列为空时返回 null.
        while (qc.peek() != null)
            // poll(): 移除并返回队头，队列为空时返回 null.
            qc.poll().operation();
    }
    public static void main(String[] args) {
        Build b = new Build();
        commandEater(b.makeQ());
    }
}
