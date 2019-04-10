package containers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cn.org.kidding.util.CountingGenerator;
import containers.example.Test;
import containers.example.TestParam;
import containers.example.Tester;

/**
 * Modify SetPerformance.java so that the Sets hold String objects instead
 * of Integers. Use a Generator from the Arrays chapter to create test values.
 */
class SetPerformance34 {
    static CountingGenerator.String cgs = new CountingGenerator.String(5);
    static CountingGenerator.String cgs2 = new CountingGenerator.String(5);

    static List<Test<Set<String>>> tests =
            new ArrayList<>();

    static {
        tests.add(new Test<Set<String>>("add") {
            @Override
            public int test(Set<String> set, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    set.clear();
                    for (int j = 0; j < size; j++) {
                        set.add(cgs.next());
                    }
                }
                return loops * size;
            }
        });

        tests.add(new Test<Set<String>>("contains") {
            @Override
            public int test(Set<String> set, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        set.contains(cgs2.next());
                return loops * span;
            }
        });

        tests.add(new Test<Set<String>>("iterate") {
            @Override
            public int test(Set<String> set, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator<String> it = set.iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * set.size();
            }
        });

    }
}

public class Ex34 {
    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.fieldWidth = 10;
        Tester.run(new TreeSet<>(), SetPerformance34.tests);
        Tester.run(new HashSet<>(), SetPerformance34.tests);
        Tester.run(new LinkedHashSet<>(), SetPerformance34.tests);
    }
}
