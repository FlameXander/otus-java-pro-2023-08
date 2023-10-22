package flyweight.solve;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Game {

    private static final int FPS = 120;

    private final List<ParticleContext> particleContexts = new LinkedList<>();
    private final Canvas canvas = new Canvas();
    @Getter
    private final ParticleFlyweight particleFlyweight = new ParticleFlyweight();

    public static void main(String... args) {
        /*
         Запустите. Программа падать не будет, а будет работать
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
            for (ParticleContext particleContext : particleContexts)
                particleContext.getParticle().move(particleContext);

            draw(canvas);
            System.out.println(i);
        }
    }

    public void draw(Canvas canvas) {
        for (ParticleContext particleContext : particleContexts)
            particleContext.getParticle().draw(particleContext.getCoords(),
                                               canvas);
    }

    public void addParticle(int coord, int vector, int speed, Particle.Color color) {
        Particle particle = particleFlyweight.createParticle(color);
        ParticleContext particleContext = new ParticleContext(particle, coord, vector, speed);
        particleContexts.add(particleContext);
    }

}
