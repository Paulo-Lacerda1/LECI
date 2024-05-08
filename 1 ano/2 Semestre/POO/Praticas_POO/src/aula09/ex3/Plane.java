package aula09.ex3;

import java.util.Objects;

public class Plane {
    private final String id;
    private String manufacturer;
    private String model;
    private int productionYear;
    private int maxPassengers;
    private double maxSpeed;

    public Plane(String id, String manufacturer, String model, int productionYear, int maxPassengers, double maxSpeed) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.productionYear = productionYear;
        this.maxPassengers = maxPassengers;
        this.maxSpeed = maxSpeed;
    }

    public String getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id='" + id + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", productionYear=" + productionYear +
                ", maxPassengers=" + maxPassengers +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Plane)) return false;
        Plane plane = (Plane) obj;
        return productionYear == plane.productionYear &&
               maxPassengers == plane.maxPassengers &&
               Double.compare(plane.maxSpeed, maxSpeed) == 0 &&
               Objects.equals(id, plane.id) &&
               Objects.equals(manufacturer, plane.manufacturer) &&
               Objects.equals(model, plane.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer, model, productionYear, maxPassengers, maxSpeed);
    }
}
