package adapter.digital;

import lombok.Getter;

@Getter
public class SimpleDigitSignal implements DigitSignal {

    private final int[] data;

    public SimpleDigitSignal(int... data) {
        this.data = data;
    }

}
