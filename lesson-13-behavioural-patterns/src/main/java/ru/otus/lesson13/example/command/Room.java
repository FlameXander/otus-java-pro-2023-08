package ru.otus.lesson13.example.command;

import ru.otus.lesson13.example.command.data.Guest;

public class Room implements Option {

    @Override
    public void charge(Guest guest) {
        guest.setRentCost(100);
    }
}
