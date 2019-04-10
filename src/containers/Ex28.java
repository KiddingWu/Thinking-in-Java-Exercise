package containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static cn.org.kidding.util.Print.*;

/**
 * Modify cn.org.kidding.util/Tuple.java to make it a
 * general-purpose class by adding hashCode(), equals().
 * and implementing Comparable for each type of Tuple.
 */
class TwoTuple28<A,B> implements Comparable {
    public final A first;
    public final B second;
    public TwoTuple28(A a, B b) {
        first = a;
        second = b;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + first.hashCode();
        result = 37 * result + second.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TwoTuple28 &&
                first.equals(((TwoTuple28)obj).first) &&
                second.equals(((TwoTuple28)obj).second);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof TwoTuple28)) throw new ClassCastException();
        TwoTuple28 t = (TwoTuple28)o;
        return (this.hashCode() - t.hashCode() < 0) ? -1 :
                ((this.hashCode() - t.hashCode() > 0) ? 1 : 0);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

class ThreeTuple28<A,B,C> extends TwoTuple28<A,B>{
    public final C third;
    public ThreeTuple28(A a, B b, C c) {
        super(a, b);
        third = c;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 37 * result + third.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ThreeTuple28 &&
                ((ThreeTuple28)obj).first.equals(first) &&
                ((ThreeTuple28)obj).second.equals(second) &&
                ((ThreeTuple28)obj).third.equals(third);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof ThreeTuple28)) throw new ClassCastException();
        ThreeTuple28 t = (ThreeTuple28) o;
        return (this.hashCode() - t.hashCode() < 0) ? -1 :
                ((this.hashCode() - t.hashCode() > 0) ? 1 : 0);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second +  ", " + third +")";
    }
}

class FourTuple28<A,B,C,D> extends ThreeTuple28<A,B,C> {
    public final D four;
    public FourTuple28(A a, B b, C c, D d) {
        super(a, b, c);
        four = d;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 37 * result + four.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        return obj instanceof FourTuple28 &&
                ((FourTuple28)obj).first.equals(first) &&
                ((FourTuple28)obj).second.equals(second) &&
                ((FourTuple28)obj).third.equals(third) &&
                ((FourTuple28)obj).four.equals(four);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof  FourTuple28)) throw new ClassCastException();
        FourTuple28 t = (FourTuple28) o;
        return (this.hashCode() - t.hashCode() < 0) ? -1 :
                ((this.hashCode() - t.hashCode() > 0) ? 1 : 0);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second +  ", " + third +  ", " + four +")";
    }
}

class FiveTuple28<A,B,C,D,E> extends FourTuple28<A,B,C,D> {
    private final E five;
    public FiveTuple28(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        five = e;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 37 * result + five.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FiveTuple28 &&
                ((FiveTuple28)obj).first.equals(first) &&
                ((FiveTuple28)obj).second.equals(second) &&
                ((FiveTuple28)obj).third.equals(third) &&
                ((FiveTuple28)obj).four.equals(four) &&
                ((FiveTuple28)obj).five.equals(five);
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof FiveTuple28)) throw new ClassCastException();
        FiveTuple28 t = (FiveTuple28) o;
        return (this.hashCode() - t.hashCode() < 0) ? -1 :
                (this.hashCode() - t.hashCode() > 0) ? 1 : 0;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second +  ", " + third +  ", " + four +  ", " + five +")";
    }

}

class Tuple28 {
    public static <A,B> TwoTuple28<A,B> tuple(A a, B b) {
        return new TwoTuple28<>(a, b);
    }

    public static <A,B,C> ThreeTuple28<A,B,C> tuple(A a, B b, C c) {
        return new ThreeTuple28<>(a, b, c);
    }

    public static <A,B,C,D> FourTuple28<A,B,C,D> tuple(A a, B b, C c, D d) {
        return new FourTuple28<>(a, b, c, d);
    }

    public static <A,B,C,D,E> FiveTuple28<A,B,C,D,E> tuple(A a, B b, C c, D d, E e) {
        return new FiveTuple28<>(a, b, c,d,e);
    }
}

public class Ex28 {
    public static void main(String[] args) {
        Tuple28 t = new Tuple28();
        FiveTuple28 t1b = t.tuple(1,1,1,1,1);
        FiveTuple28 t2b = t.tuple(1,1,1,2,1);
        FiveTuple28 t3b = t.tuple(1,1,1,1,1);
        FiveTuple28 t4b = t.tuple(1,1,1,1,0);
        println(t1b.compareTo(t1b));
        println(t1b.compareTo(t2b));
        println(t1b.compareTo(t3b));
        println(t1b.compareTo(t4b));
        List<FiveTuple28> list =
                new ArrayList<>(Arrays.asList(t1b, t2b, t3b, t4b));
        Set<FiveTuple28> s = new TreeSet<>();
        s.addAll(list);
        println(s);
    }
}
