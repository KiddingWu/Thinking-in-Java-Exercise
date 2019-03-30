package holding;


import java.util.Iterator;
import java.util.Random;

import polymorphism.shape.Circle;
import polymorphism.shape.Shape;
import polymorphism.shape.Square;
import polymorphism.shape.Triangle;

/**
 * Modify polymorphism/shape/RandomShapeGenerator.java to make it
 * Iterable. You'll need to add a constructor that takes the number of
 * elements that you want the iterator to produce before stopping. Verify
 * that is works.
 */
class RandomShapeGenerator31 implements Iterable<Shape> {

    private Random rand = new Random();

    public Shape make() {
        switch (rand.nextInt(3)) {
            default:
            case 0: return new Circle();
            case 1: return new Square();
            case 2: return new Triangle();
        }
    }

    private Shape[] shapes;

    RandomShapeGenerator31(int n) {
        shapes = new Shape[n];
        for (int i = 0; i < n; i++)
            shapes[i] = make();
    }

    @Override
    public Iterator<Shape> iterator() {
        return new Iterator<Shape>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < shapes.length;
            }

            @Override
            public Shape next() {
                return shapes[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

public class Ex31 {

    public static void main(String[] args) {
        RandomShapeGenerator31 rsg = new RandomShapeGenerator31(20);

        // 只要类实现了 Iterable 接口，并覆盖 iterator() 方法，就可以被 foreach 遍历
        for (Shape s : rsg)
            System.out.println(s);
    }
}
