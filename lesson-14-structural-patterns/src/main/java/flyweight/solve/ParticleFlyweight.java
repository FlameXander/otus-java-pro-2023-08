package flyweight.solve;

import java.util.EnumMap;
import java.util.Map;

public final class ParticleFlyweight {

    private final Map<Particle.Color, Particle> particles = new EnumMap<>(Particle.Color.class);

    // Не создаём каждый раз новый объект, а используем неизменяемые существующие
    public Particle createParticle(Particle.Color color) {
        if (!particles.containsKey(color))
            particles.put(color, new Particle(color, loadSprite()));

        return particles.get(color);
    }

    private static byte[] loadSprite() {
        return new byte[20_000];

    }
}
