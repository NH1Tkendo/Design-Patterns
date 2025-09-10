package Cau2;

public class Vehicle {
    private String ownerName;
    private String model;
    private int engineCapacity; // in cc
    private double price; // in VND

    // Constructor
    public Vehicle(String ownerName, String model, int engineCapacity, double price) {
        this.ownerName = ownerName;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.price = price;
    }

    // Getters
    public String getOwnerName() {
        return ownerName;
    }

    public String getModel() {
        return model;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setOwnerName(String name) {
        this.ownerName = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setEngineCapacity(int ec) {
        this.engineCapacity = ec;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Method to calculate tax based on engine capacity
    public double calculateTax() {
        if (engineCapacity < 100) {
            return price * 0.01;
        } else if (engineCapacity <= 200) {
            return price * 0.03;
        } else {
            return price * 0.05;
        }
    }
}
