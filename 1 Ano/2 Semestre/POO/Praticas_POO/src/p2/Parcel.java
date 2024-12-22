package p2;

public class Parcel implements Comparable<Parcel> {
    private static int parcelsCount;
    private int id;
    private double weight;
    private String destination;
    private String sender;

    public Parcel(double weight, String destination, String sender) {
        this.weight = weight;
        this.destination = destination;
        this.sender = sender;

        this.id = ++parcelsCount; 
    }

    public Parcel(int id, double weight, String destination, String sender) {
        this.weight = weight;
        this.destination = destination;
        this.sender = sender;

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public int compareTo(Parcel p) {
        return this.id - p.getId();
    }

    @Override
    public String toString() {
        return String.format("%d | W: %.2f D: %s S: %s", id, weight, destination, sender);
    }  
}
