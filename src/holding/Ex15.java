package holding;

import cn.org.kidding.util.Stack;

import static cn.org.kidding.util.Print.*;

/**
 * Stacks are often used to evaluate exporessions in programming
 * language. Using cn.org.kidding.util.Stack, evaluate the following
 * expression, when '+' means "push the following letter onto the stack,
 * and '-' means " pop the top of the stack and print it":
 * "+U+n+c---+e+r+t---+a+i+n+t+y---+ -+r+u--+l+e+s---"
 */
public class Ex15 {

    public static void main(String[] args) {
        Stack<Character> sc = new Stack<>();
        sc.push('U');
        sc.push('n');
        sc.push('c');
        print(sc.pop());
        print(sc.pop());
        print(sc.pop());
        sc.push('e');
        sc.push('r');
        sc.push('t');
        print(sc.pop());
        print(sc.pop());
        print(sc.pop());
        sc.push('a');
        sc.push('i');
        sc.push('n');
        sc.push('t');
        sc.push('y');
        print(sc.pop());
        print(sc.pop());
        print(sc.pop());
        sc.push(' ');
        print(sc.pop());
        sc.push('r');
        sc.push('u');
        print(sc.pop());
        print(sc.pop());
        sc.push('l');
        sc.push('e');
        sc.push('s');
        print(sc.pop());
        print(sc.pop());
        print(sc.pop());
    }
}
