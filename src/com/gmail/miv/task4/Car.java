package com.gmail.miv.task4;

public class Car {

    private String manufacturer;
    private String model;
    private Double capacity;
    private boolean accident;

    public Car() {
    }

    public Car(String manufacturer, String model, Double capacity, boolean accident) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.capacity = capacity;
        this.accident = accident;
    }

    @Override
    public String toString() {
        return "Car{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", accident=" + accident +
                '}';
    }

}