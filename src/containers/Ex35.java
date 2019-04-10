package containers;

import java.util.*;

import containers.example.*;

/**
 * Modify MapPerformance.java to include tests of SlowMap.
 */
class MapPerformance35 {
    static List<Test<Map<Integer, Integer>>> tests =
            new ArrayList<>();
    static {
        tests.add(new Test<Map<Integer, Integer>>("put") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    map.clear();
                    for (int j = 0; j < size; j++) {
                        map.put(j, j);
                    }
                }
                return loops * size;
            }
        });

        tests.add(new Test<Map<Integer, Integer>>("get") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        map.get(j);
                return loops * span;
            }
        });

        tests.add(new Test<Map<Integer, Integer>>("iterator") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * map.size();
            }
        });
    }
}

public class Ex35 {
    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        else
            Tester.defaultParams = TestParam.array(10, 500, 100, 500, 500, 100);
        Tester.run(new SlowMap<>(), MapPerformance35.tests);
        Tester.run(new TreeMap<>(), MapPerformance35.tests);
        Tester.run(new HashMap<>(), MapPerformance35.tests);
        Tester.run(new LinkedHashMap<>(), MapPerformance35.tests);
        Tester.run(new IdentityHashMap<>(), MapPerformance35.tests);
        Tester.run(new WeakHashMap<>(), MapPerformance35.tests);
        Tester.run(new Hashtable<>(), MapPerformance35.tests);
    }
}
