package composite.car;

import java.util.ArrayList;
import java.util.List;

// Composite
public final class CarStockComposite implements Car {

    private final List<Car> cars = new ArrayList<>();

    @Override
    public void checkAvailability() {
        for (Car car : cars) {
            car.checkAvailability();
        }
    }

    public void add(Car check) {
        cars.add(check);
    }

}
