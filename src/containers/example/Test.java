package containers.example;

public abstract class Test<C> {
    String name;
    public Test(String name) { this.name = name; }
    // Override this method of repetitions of test.
    // Returns actual number or repetitions of test.
    public abstract int test(C container, TestParam tp);
}
