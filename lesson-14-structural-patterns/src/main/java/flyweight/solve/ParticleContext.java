package flyweight.solve;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Изменяемая часть Particle (используем для каждого Particle свой объект)
 */
@Getter
@Setter
@AllArgsConstructor
public class ParticleContext {

    /*
     Этот объект шарится между всеми контекстами (а не создаётся индивидуально).
     Для простоты положил его прямо в контекст, но он может быть где угодно
     */
    private final Particle particle;

    private int coords;
    private long vector;
    private int speed;
}
