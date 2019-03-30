package cn.org.kidding.util;

import java.util.LinkedList;

/**
 * "栈顶" 通常指 "后进先出"（LIFO）的容器
 * @param <T>
 */

// Making a stack from a LinkedList.
public class Stack<T> {
    private LinkedList<T> storage = new LinkedList<>();

    // push(): 接受的是 T 类型的对象
    public void push(T v) {
        storage.addFirst(v);
    }
    // peek(): 提供栈顶元素
    public T peek() {
         return storage.getFirst();
    }
    // pop(): 移除并返回栈顶元素
    public T pop() {
        return storage.remove();
    }
    public boolean empty() {
        return storage.isEmpty();
    }
    public String toString() {
        return storage.toString();
    }

}
