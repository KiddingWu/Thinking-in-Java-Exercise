package polymorphism.shape;

import static cn.org.kidding.util.Print.*;

public class Square extends Shape {
    @Override
    public void draw() {
        print("Square.draw()");
    }

    @Override
    public void erase() {
        print("Square.draw()");
    }
}
