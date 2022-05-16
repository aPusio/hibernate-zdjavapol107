package org.example;

import org.example.model.Car;

public class App
{
    public static void main( String[] args ) {
        Car fiatPanda = new Car("Fiat Panda", 2000, 3, 4);
        Car build = Car.builder().amountOfDoors(2).build();

    }
}
