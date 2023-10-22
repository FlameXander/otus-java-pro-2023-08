package bridge.problem;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Triangle implements Shape {

    private final String color;

    public Triangle() {
        this(null);
    }

    @Override
    public void draw() {
        if (color == null)
            System.out.println("Drawing triangle");
        else
            System.out.format("Drawing %s triangle\n", color);
    }

}
