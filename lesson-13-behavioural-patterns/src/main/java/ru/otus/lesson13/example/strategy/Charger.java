package ru.otus.lesson13.example.strategy;


import ru.otus.lesson13.example.command.*;
import ru.otus.lesson13.example.command.data.Guest;


public class Charger {
    public enum Charges {
        BREAKFAST(new Breakfast()),
        EXTRABED(new ExtraBed()),
        MINIBAR(new MiniBar()),
        ROOM(new Room());

        Charges(final Option option) {
            this.option = option;
        }

        final Option option;
    }

    public static void charge(Guest user, Charges type) {
        type.option.charge(user);
    }

}
