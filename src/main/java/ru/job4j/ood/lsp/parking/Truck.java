package ru.job4j.ood.lsp.parking;

public class Truck extends Vehicle {

    public Truck(int size) {
        super(size);
        if (size <= CAR_SIZE) {
            throw new IllegalArgumentException("Truck size can't be <= 1");
        }
    }
}
