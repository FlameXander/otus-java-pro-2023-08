package adapter;

import adapter.analog.AnalogSignal;
import adapter.analog.SimpleAnalogSignal;
import adapter.digital.DigitSignal;
import adapter.digital.SimpleDigitSignal;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AdapterDemo {

    public static void main(String... args) {
        // Для DigitalSignal используем printDigitalSignal() метод
        printDigitalSignal(new SimpleDigitSignal(1, 2, 3, 4));

        // Для AnalogSignal используем printAnalogSignal() метод
        AnalogSignal analogSignal = new SimpleAnalogSignal(0.2, 1.4, 3.12, 0.9);
        printAnalogSignal(analogSignal);

        /*
         Но, если необходимо использовать pringDigitalSignal для AnalogSignal,
         то необходимо написать адаптер AnalogSignal -> DigitalSignal
         */
        AnalogSignalToDigitSignalAdapter adapter = new AnalogSignalToDigitSignalAdapter(analogSignal);
        printDigitalSignal(adapter);
    }

    public static void printDigitalSignal(DigitSignal digitSignal) {
        System.out.println(Arrays.toString(digitSignal.getData()));
    }

    public static void printAnalogSignal(AnalogSignal analogSignal) {
        System.out.println(Arrays.toString(analogSignal.getData()));
    }

}
