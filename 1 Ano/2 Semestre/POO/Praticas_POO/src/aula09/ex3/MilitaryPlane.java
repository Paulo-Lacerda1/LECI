package aula09.ex3;

import java.util.Objects;

public class MilitaryPlane extends Plane {
    private int numMissiles;

    public MilitaryPlane(String id, String manufacturer, String model, int year, int maxPassengers, double maxSpeed, int numMissiles) {
        super(id, manufacturer, model, year, maxPassengers, maxSpeed);
        this.numMissiles = numMissiles;
    }

    public int getNumMissiles() {
        return numMissiles;
    }

    public void setNumMissiles(int numMissiles) {
        this.numMissiles = numMissiles;
    }

    @Override
    public String toString() {
        return "MilitaryPlane {" +
                "id='" + super.getId() + '\'' +
                ", manufacturer='" + super.getManufacturer() + '\'' +
                ", model='" + super.getModel() + '\'' +
                ", productionYear=" + super.getProductionYear() +
                ", maxPassengers=" + super.getMaxPassengers() +
                ", maxSpeed=" + super.getMaxSpeed() +
                ", numMissiles=" + numMissiles +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MilitaryPlane)) {
            return false;
        }
        MilitaryPlane plane = (MilitaryPlane) obj;
        return super.equals(plane) && numMissiles == plane.numMissiles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numMissiles);
    }
}
