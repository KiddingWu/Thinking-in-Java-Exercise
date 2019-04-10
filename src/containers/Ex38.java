package containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.org.kidding.util.CountingGenerator;
import cn.org.kidding.util.CountingMapData;
import containers.example.Test;
import containers.example.TestParam;
import containers.example.Tester;

import static cn.org.kidding.util.Print.*;

/**
 * Look up the HashMap class in the JDK documentation. Create a
 * HashMap, fill it with elements. and determine the load factor.
 * Test the lookup speed with this map, then attempt to increase
 * the speed by making a new HashMap with a larger initial capacity
 * and copying the old map into the new one, then run your lookup
 * speed test again on the new map.
 */
class HashMap38 {
   static List<Test<Map<Integer, String>>> tests =
           new ArrayList<>();
   static CountingGenerator.String cgs = new CountingGenerator.String(5);
   static {
        tests.add(new Test<Map<Integer, String>>("put") {
            @Override
            public int test(Map<Integer, String> map, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    map.clear();
                    for (int j = 0; j < size; j++)
                        map.put(j, cgs.next());
                }
                return loops * size;
            }
        });

        tests.add(new Test<Map<Integer, String>>("get") {
            @Override
            public int test(Map<Integer, String> map, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        map.get(j);

                return loops * span;
            }
        });

        tests.add(new Test<Map<Integer, String>>("iterate") {
            @Override
            public int test(Map<Integer, String> map, TestParam tp) {
                int loops = tp.size * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext()) it.next();
                }
                return loops * map.size();
            }
        });
   }

}

public class Ex38 {

    public static void main(String[] args) {
        HashMap<Integer,String> map1 = new HashMap<>();
        println("map1: " + map1);
        map1.putAll(new CountingMapData(16));
        println("map1: " + map1);
        HashMap<Integer, String> map2 = new HashMap<>(64);
        println("map2:" + map2);
        map2.putAll(map1);
        println("map2:" + map2);
        HashMap<Integer, String> map3 = new HashMap<>(128);
        println("map3:" + map3);
        map3.putAll(map1);
        println("map3:" + map3);
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        else
            Tester.defaultParams = TestParam.array(10, 1000, 100, 1000, 1000, 1000);
        Tester.run(map1, HashMap38.tests);
        Tester.run(map2, HashMap38.tests);
        Tester.run(map3, HashMap38.tests);
    }
}
