package adapter.analog;

import lombok.Getter;

@Getter
public class SimpleAnalogSignal implements AnalogSignal {

    private final double[] data;

    public SimpleAnalogSignal(double... data) {
        this.data = data;
    }

}
