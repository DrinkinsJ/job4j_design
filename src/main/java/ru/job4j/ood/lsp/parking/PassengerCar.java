package ru.job4j.ood.lsp.parking;

public class PassengerCar implements Car {
    private static final int PASSENGER_CAR_SIZE = 1;

    public PassengerCar() {
    }

    @Override
    public int getSize() {
        return PASSENGER_CAR_SIZE;
    }
}
