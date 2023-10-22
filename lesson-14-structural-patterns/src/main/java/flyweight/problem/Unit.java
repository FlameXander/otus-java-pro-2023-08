package flyweight.problem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Unit {

    private static final int RED_COLOR = 55;
    private static final int GREEN_COLOR = 66;
    private static final int BLUE_COLOR = 77;

    private final Game game;
    private final int coord;

    private Particle.Color nextColor = Particle.Color.RED;

    public void fireAt(Unit target) {
        Particle particle = new Particle(0,
                                         target.getCoord(),
                                         2,
                                         nextColor,
                                         loadSprite());
        game.addParticle(particle);

        if (nextColor == Particle.Color.RED)
            nextColor = Particle.Color.GREEN;
        else if (nextColor == Particle.Color.GREEN)
            nextColor = Particle.Color.BLUE;
        else
            nextColor = Particle.Color.RED;
    }

    private static byte[] loadSprite() {
        return new byte[20_000];
    }

}
