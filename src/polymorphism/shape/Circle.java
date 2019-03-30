package polymorphism.shape;

import static cn.org.kidding.util.Print.*;

public class Circle extends Shape {
    @Override
    public void draw() {
        print("Circle.draw()");

    }

    @Override
    public void erase() {
        print("Circle.erase()");
    }
}
