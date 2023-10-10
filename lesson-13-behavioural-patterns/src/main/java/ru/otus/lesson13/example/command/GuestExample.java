package ru.otus.lesson13.example.command;

import ru.otus.lesson13.example.command.data.Guest;


public class GuestExample {
    public static void main(String[] args) {
        Guest guest = new Guest("Ivan Ivanov");

        Booking booking = new Booking(guest);

        booking.addOption(new ExtraBed());
        booking.addOption(new Room());
        booking.addOption(new MiniBar());

        guest = booking.checkOut();

        System.out.println(guest);
    }
}
