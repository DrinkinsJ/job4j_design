package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class CarParking implements Parking {
    private final List<Car> passengerParking;
    private final List<Car> truckParking;
    private int passengerSeats;
    private int truckSeats;

    public CarParking(int passengerSeats, int truckSeats) {
        this.passengerSeats = passengerSeats;
        this.truckSeats = truckSeats;
        this.passengerParking = new ArrayList<>();
        this.truckParking = new ArrayList<>();
    }

    @Override
    public boolean add(Car car) {
        boolean isAdded = false;
        if (truckSeats >= car.getSize() && car.getSize() > 1) {
            isAdded = truckParking.add(car);
            truckSeats -= car.getSize();
        } else if (passengerSeats >= car.getSize()) {
            isAdded = passengerParking.add(car);
            passengerSeats -= car.getSize();
        }
        return isAdded;
    }

    int getPassengerSeats() {
        return passengerSeats;
    }

    int getTruckSeats() {
        return truckSeats;
    }
}
