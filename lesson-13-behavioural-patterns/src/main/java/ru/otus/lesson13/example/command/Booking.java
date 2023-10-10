package ru.otus.lesson13.example.command;

import ru.otus.lesson13.example.command.data.Guest;

import java.util.ArrayList;
import java.util.List;

public class Booking {

    private final List<Option> options = new ArrayList<>();

    private final Guest guest;

    public Booking(final Guest guest) {
        this.guest = guest;
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public Guest checkOut() {
        options.forEach(option -> option.charge(guest));
        return guest;
    }
}
