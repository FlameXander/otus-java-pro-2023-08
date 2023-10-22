package adapter;

import adapter.analog.AnalogSignal;
import adapter.digital.DigitSignal;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * Определим Адаптер, который позволит использовать объекты AnalogDigital
 * там, где требуются объекты DigitalSignal.
 */
@RequiredArgsConstructor
public class AnalogSignalToDigitSignalAdapter implements DigitSignal {

    private static final double THRESHOLD = 0.1;
    private final AnalogSignal analogSignal;

    @Override
    public int[] getData() {
        return Arrays.stream(analogSignal.getData())
                     .mapToInt(a -> (int)(a / THRESHOLD))
                     .toArray();
    }

}
