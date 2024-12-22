package aula09.ex3;

import java.util.Objects;

public class CommercialPlane extends Plane {
    private int numOfCrewMembers;

    public CommercialPlane(String id, String manufacturer, String model, int year, int maxNumOfPassengers, double maxSpeed, int numOfCrewMembers) {
        super(id, manufacturer, model, year, maxNumOfPassengers, maxSpeed);
        this.numOfCrewMembers = numOfCrewMembers;
    }

    public int getNumOfCrewMembers() {
        return numOfCrewMembers;
    }

    public void setNumOfCrewMembers(int numOfCrewMembers) {
        this.numOfCrewMembers = numOfCrewMembers;
    }

    @Override
    public String toString() {
        return "CommercialPlane {" +
            "id='" + super.getId() + '\'' +
            ", manufacturer='" + super.getManufacturer() + '\'' +
            ", model='" + super.getModel() + '\'' +
            ", productionYear=" + super.getProductionYear() +
            ", maxPassengers=" + super.getMaxPassengers() +
            ", maxSpeed=" + super.getMaxSpeed() +
            ", numOfCrewMembers=" + numOfCrewMembers +
            '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommercialPlane)) {
            return false;
        }
        CommercialPlane plane = (CommercialPlane) obj;
        return super.equals(plane) && numOfCrewMembers == plane.numOfCrewMembers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numOfCrewMembers);
    }
}
