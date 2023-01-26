package ru.job4j.ood.lsp.parking;

public abstract class Vehicle implements Car {

    private final int size;

    protected Vehicle(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
