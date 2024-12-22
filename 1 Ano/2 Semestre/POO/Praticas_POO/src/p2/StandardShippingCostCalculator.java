package p2;

public class StandardShippingCostCalculator implements IShippingCostCalculator{

    public double calculateShippingCost(Parcel p) {
        double weight = p.getWeight();
        double tax;

        if (weight <= 5) {
            tax = 3;
        } else if (weight > 5 && weight <= 10) {
            tax = 2;
        } else {
            tax = 1;
        }

        double finalCost = tax * weight;

        return finalCost;
    }
}

