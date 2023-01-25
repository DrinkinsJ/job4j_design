package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CarParkingTest {

    @Test
    void whenAddCars() {
        CarParking carParking = new CarParking(10, 10);
        Car passengerCar = new PassengerCar();
        Car truck = new Truck(5);
        carParking.add(passengerCar);
        carParking.add(truck);
        assertThat(carParking.getPassengerSeats()).isEqualTo(9);
        assertThat(carParking.getTruckSeats()).isEqualTo(9);
    }

    @Test
    void whenAddTruckAndNoFreeSeats() {
        CarParking carParking = new CarParking(3, 1);
        Car truck = new Truck(5);
        Car truck2 = new Truck(5);
        carParking.add(truck);
        assertThat(carParking.add(truck2)).isFalse();
    }
    @Test
    void whenAddCarsAndNoFreeSeats() {
        CarParking carParking = new CarParking(0, 3);
        Car passengerCar = new PassengerCar();
        assertThat(carParking.add(passengerCar)).isFalse();
    }

    @Test
    void  whenAddTruckOnPassengerParking() {
        CarParking carParking = new CarParking(3, 0);
        Car truck = new Truck(3);
        assertThat(carParking.add(truck)).isTrue();
        assertThat(carParking.getPassengerSeats()).isZero();
    }

    @Test
    void whenTruckLessThenOneSize() {
        assertThatThrownBy(() -> new Truck(1)).isInstanceOf(IllegalArgumentException.class);
    }
}