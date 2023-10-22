package bridge.problem;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Rectangle implements Shape {

    private final String color;

    public Rectangle() {
        this(null);
    }

    @Override
    public void draw() {
        if (color == null)
            System.out.println("Drawing rectangle");
        else
            System.out.format("Drawing %s rectangle\n", color);
    }

}
