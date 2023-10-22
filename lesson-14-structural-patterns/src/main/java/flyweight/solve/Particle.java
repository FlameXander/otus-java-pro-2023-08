package flyweight.solve;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * Неизменяемая часть Particle (используем один объект для всех Particle)
 */
@RequiredArgsConstructor
public final class Particle {

    // эти свойства не меняются от объекта к объекту
    private final Color color;
    private final byte[] sprite;

    public void move(ParticleContext particleContext) {
        particleContext.setCoords(particleContext.getCoords() + 1);
        particleContext.setVector(particleContext.getVector() + 1);
    }

    public void draw(int coords, Canvas canvas) {
    }

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public enum Color {
        RED,
        GREEN,
        BLUE
    }

}
