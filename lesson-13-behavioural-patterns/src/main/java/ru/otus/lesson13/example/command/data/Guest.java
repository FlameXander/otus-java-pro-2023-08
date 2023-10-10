package ru.otus.lesson13.example.command.data;

import java.util.ArrayList;
import java.util.List;


public class Guest {

    private final List<Charge> charges = new ArrayList<>();

    private int rentCost = 0;

    private final String name;

    public Guest(final String name) {
        this.name = name;
    }

    public void addCharge(Charge charge) {
        charges.add(charge);
    }

    public List<Charge> getCharges() {
        return charges;
    }

    public int getRentCost() {
        return rentCost;
    }

    public void setRentCost(final int rentCost) {
        this.rentCost = rentCost;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "charges=" + charges +
                ", rentCost=" + rentCost +
                ", name='" + name + '\'' +
                '}';
    }
}
