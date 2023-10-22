package flyweight.problem;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public final class Particle {

    private int coord;
    private long vector;
    private int speed;
    private Color color;
    private byte[] sprite;

    public void move() {
        coord++;
        vector++;
    }

    public void draw(Canvas canvas) {
    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Color {
        RED,
        GREEN,
        BLUE
    }

}
