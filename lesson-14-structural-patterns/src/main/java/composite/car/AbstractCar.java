package composite.car;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractCar implements Car {

    private final String id;
    private final String model;

    @Override
    public final void checkAvailability() {
        System.out.format("%s %s\n", id, model);
    }

}
