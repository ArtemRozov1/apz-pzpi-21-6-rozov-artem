package vehiclestrackingsystemmobile.models;

public class Vehicle {
    private int vehicleId;
    private String vehicleNumber;
    private String vehicleType;
    private String manufacturer;
    private String model;
    private int yearOfManufacture;
    private int ownerId;
    private double factoryWeight;
    private double currentWeight;
    private String location;
    private double tankCapacity;
    private double currentFuelLevel;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        vehicleType = vehicleType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        model = model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        yearOfManufacture = yearOfManufacture;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        ownerId = ownerId;
    }

    public double getFactoryWeight() {
        return factoryWeight;
    }

    public void setFactoryWeight(double factoryWeight) {
        factoryWeight = factoryWeight;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        currentWeight = currentWeight;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        location = location;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        tankCapacity = tankCapacity;
    }

    public double getCurrentFuelLevel() {
        return currentFuelLevel;
    }

    public void setCurrentFuelLevel(double currentFuelLevel) {
        currentFuelLevel = currentFuelLevel;
    }
}
