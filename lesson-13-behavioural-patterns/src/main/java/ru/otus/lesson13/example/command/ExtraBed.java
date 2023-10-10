package ru.otus.lesson13.example.command;

import ru.otus.lesson13.example.command.data.Charge;
import ru.otus.lesson13.example.command.data.Guest;

public class ExtraBed implements Option{
    @Override
    public void charge(Guest guest) {
        guest.addCharge(new Charge("extra bed", 10));
    }
}
