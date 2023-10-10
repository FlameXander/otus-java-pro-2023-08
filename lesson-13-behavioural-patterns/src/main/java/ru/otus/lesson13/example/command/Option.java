package ru.otus.lesson13.example.command;

import ru.otus.lesson13.example.command.data.Guest;


public interface Option {
    void charge(Guest guest);
}
