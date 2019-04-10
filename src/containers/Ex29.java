package containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

import cn.org.kidding.util.CountingGenerator;
import cn.org.kidding.util.Generated;
import cn.org.kidding.util.RandomGenerator;
import containers.example.Test;
import containers.example.TestParam;
import containers.example.Tester;

/**
 * Modify ListPerformance.java so that the Lists hold String objects instead
 * of Integers. Use a Generator from the Arrays chapter to create test values.
 */
class ListPerformance29 {
    static RandomGenerator.String randString = new RandomGenerator.String();
    static Random rand = new Random();

    static int reps = 1000;
    static List<Test<List<String>>> tests =
            new ArrayList<>();
    static List<Test<LinkedList<String>>> qTests =
            new ArrayList<>();

    static {
        tests.add(new Test<List<String>>("add") {
            @Override
            public int test(List<String> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < listSize; j++) {
                        list.add(randString.next());
                    }
                }
                return loops * listSize;
            }
        });

        tests.add(new Test<List<String>>("get") {
            @Override
            public int test(List<String> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for (int i = 0; i < loops; i++)
                    list.get(rand.nextInt(listSize));
                return loops;
            }
        });

        tests.add(new Test<List<String>>("set") {
            @Override
            public int test(List<String> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for (int i = 0; i < loops; i++)
                    list.set(rand.nextInt(listSize), "hi");
                return loops;
            }
        });

        tests.add(new Test<List<String>>("iteradd") {
            @Override
            public int test(List<String> list, TestParam tp) {
                final int LOOPS = 1000000;
                int half = list.size() / 2;
                ListIterator<String> it = list.listIterator(half);
                for (int i = 0; i < LOOPS; i++)
                    it.add("hi");
                return LOOPS;
            }
        });

        tests.add(new Test<List<String>>("insert") {
            @Override
            public int test(List<String> list, TestParam tp) {
                int loops = tp.loops;
                for (int i = 0; i < loops; i++)
                    list.add(5, "hi"); // Minimize random-access cost
                return loops;
            }
        });

        tests.add(new Test<List<String>>("remove") {
            @Override
            public int test(List<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(Arrays.asList(Generated.array(String.class,
                            new CountingGenerator.String(), size)));
                }
                return loops * size;
            }
        });

        // Tests for queue behavior:
        qTests.add(new Test<LinkedList<String>>("addFirst") {
            @Override
            public int test(LinkedList<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addFirst("hi");
                }
                return loops * size;
            }
        });

        qTests.add(new Test<LinkedList<String>>("addLast") {
            @Override
            public int test(LinkedList<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addLast("hi");
                }
                return loops * size;
            }
        });

        qTests.add(new Test<LinkedList<String>>("rmFirst") {
            @Override
            public int test(LinkedList<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(Arrays.asList(Generated.array(String.class,
                            new CountingGenerator.String(), size)));
                    while (list.size() > 0)
                        list.removeFirst();
                }
                return loops * size;
            }
        });

        qTests.add(new Test<LinkedList<String>>("rmLast") {
            @Override
            public int test(LinkedList<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(Arrays.asList(Generated.array(String.class,
                            new CountingGenerator.String(), size)));
                    while (list.size() > 0)
                        list.removeLast();
                }
                return loops * size;
            }
        });
    }

    static class ListTester extends Tester<List<String>> {
        public ListTester(List<String> container, List<Test<List<String>>> tests) {
            super(container, tests);
        }

        // Fill to the appropriate size before each test:
        @Override
        protected List<String> initialize(int size) {
            container.clear();
            container.addAll(Arrays.asList(Generated.array(String.class,
                    new CountingGenerator.String(), size)));
            return container;
        }

        // Convenience method:
        public static void run(List<String> list, List<Test<List<String>>> tests) {
            new ListTester(list,tests).timedTest();
        }
    }
}

public class Ex29 {
    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        // Can only do these two tests on an array:
        Tester<List<String>> arrayTest =
                new Tester<List<String>>(null, ListPerformance29.tests.subList(1,3)) {
                    // This will be called before each test. It produces
                    // a non-resizeable array-backed list:
                    @Override
                    protected List<String> initialize(int size) {
                        String[] ia = Generated.array(String.class,
                                new CountingGenerator.String(), size);
                        return Arrays.asList(ia);
                    }
                };
        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        Tester.defaultParams = TestParam.array(
                10, 5000, 100, 5000, 1000, 5000, 10000, 200);
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        ListPerformance29.ListTester.run(new ArrayList<>(), ListPerformance29.tests);
        ListPerformance29.ListTester.run(new LinkedList<>(), ListPerformance29.tests);
        ListPerformance29.ListTester.run(new Vector<>(), ListPerformance29.tests);
        Tester.fieldWidth = 12;
        Tester<LinkedList<String>> qTest = new Tester<>(
                new LinkedList<>(), ListPerformance29.qTests);
        qTest.setHeadline("Queue tests");
        qTest.timedTest();

    }
}
