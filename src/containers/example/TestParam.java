package containers.example;

// A "data transfer object"

public class TestParam {

    public final int size; // 表示容器的元素数量。
    public final int loops; // 控制该测试迭代的次数。

    public TestParam(int size, int loops) {
        this.size = size;
        this.loops = loops;
    }

    // Create an array of TestParam from a varargs sequence:
    // 接受可变参数列表，其中包括可互换的 size 和 loops 的值。
    public static TestParam[] array(int... values) {
        int size = values.length / 2;
        TestParam[] result = new TestParam[size];
        int n = 0;
        for (int i = 0; i < size; i++)
            result[i] = new TestParam(values[n++], values[n++]);
        return result;
    }

    // Convert a String array to TestParam array:
    public static TestParam[] array(String[] values) {
        int[] vals = new int[values.length];
        for (int i = 0; i < vals.length; i++)
            vals[i] = Integer.decode(values[i]);
        return array(vals);
    }
}
