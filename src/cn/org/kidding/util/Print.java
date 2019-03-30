package cn.org.kidding.util;

import java.io.PrintStream;

public class Print {

    // Print with a newline:
    public static void print(Object obj) {
        System.out.print(obj);
    }

    // Print a newline by itself:
    public static void println() {
        System.out.println();
    }

    public static void println(Object obj) {
        System.out.println(obj);
    }

    // Print with no line break:
    public static void printnb(Object obj) {
        System.out.println(obj);
    }

    // The new Java SE5 printf() (from c):
    public static PrintStream printf(String format, Object... args) {
        return System.out.printf(format, args);
    }
}
