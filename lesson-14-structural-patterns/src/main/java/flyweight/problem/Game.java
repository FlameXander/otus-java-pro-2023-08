package flyweight.problem;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Game {

    private static final int FPS = 120;

    private final List<Particle> particles = new LinkedList<>();
    private final Canvas canvas = new Canvas();

    public static void main(String... args) {
        /*
         Запустите. Скорее всего из-за того что создали 1_000_000 объектов,
         программа упадёт с java.lang.OutOfMemoryError: Java heap space
         */
        new Game().startShooting();
    }

    public void startShooting() {
        final int totalBullets = 1_000_000;
        final int totalScenes = 30 * FPS;
        Unit tankUnit = new Unit(this, 66);

        for (int i = 0; i < totalBullets; i++)
            tankUnit.fireAt(tankUnit);

        for (int i = 0; i < totalScenes; i++) {
            for (Particle particle : particles)
                particle.move();

            draw(canvas);
        }
    }

    public void draw(Canvas canvas) {
        for (Particle particle : particles)
            particle.draw(canvas);
    }

    public void addParticle(Particle particle) {
        particles.add(particle);
    }
}
