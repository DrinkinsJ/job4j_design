package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.parking.PassengerCar.CAR_SIZE;

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
        if (truckSeats > CAR_SIZE && car.getSize() > CAR_SIZE) {
            isAdded = truckParking.add(car);
            truckSeats -= CAR_SIZE;
        } else if (passengerSeats >= car.getSize()) {
            isAdded = passengerParking.add(car);
            passengerSeats -= car.getSize();
        }
        return isAdded;
    }

    public int getPassengerSeats() {
        return passengerSeats;
    }

    public int getTruckSeats() {
        return truckSeats;
    }
}
