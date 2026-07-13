import java.util.*;

public class Vector {
    private ArrayList<Double> values = new ArrayList<Double>();
    private int dimension = 1;
    private boolean scalar = false;
    private boolean error = false;

    public Vector(ArrayList<Double> val) {
        this.values = val;
        this.dimension = val.size();
    }

    public Vector(ArrayList<Double> val, boolean scalar) {
        this.values = val;
        this.scalar = scalar;
    }

    public Vector(String vec) {
        String vector = vec.substring(1, vec.length()-1);
        String[] numbers = vector.split(",");

        try {
            for (int i = 0; i < numbers.length; i++) {
                values.add(Double.parseDouble(numbers[i]));
            }

        } catch(NumberFormatException e) {
            this.error = true;
        }

        this.dimension = this.values.size();
    }

    public ArrayList<Double> values() {
        return this.values;
    }

    public int dimension() {
        return this.dimension;
    }

    public boolean scalar() {
        return this.scalar;
    }

    public boolean error() {
        return this.error;
    }

    public Vector symmetric() {
        ArrayList<Double> symmetricValues = new ArrayList<>();
        for (Double value : this.values) {
            symmetricValues.add(-value);
        }

        Vector newVec = null;
        if (this.scalar == true) {
            newVec = new Vector(symmetricValues, true);
        } else {
            newVec = new Vector(symmetricValues);
        }

        return newVec;
    }

    public Vector add(Vector v) {
        ArrayList<Double> newValues = new ArrayList<>();
        for (int i = 0; i < this.dimension; i++) {
            newValues.add(this.values.get(i) + v.values().get(i));
        }

        Vector newVec = null;
        if (this.scalar == true) {
            newVec = new Vector(newValues, true);
        } else {
            newVec = new Vector(newValues);
        }

        return newVec;
    }

    public Vector subtract(Vector v) {
        ArrayList<Double> newValues = new ArrayList<>();
        for (int i = 0; i < this.dimension; i++) {
            newValues.add(this.values.get(i) - v.values().get(i));
        }

        Vector newVec = null;
        if (this.scalar == true) {
            newVec = new Vector(newValues, true);
        } else {
            newVec = new Vector(newValues);
        }

        return newVec;
    }

    public Vector multplication(Vector v) {
        ArrayList<Double> newValues = new ArrayList<>();
        Vector newVec = null;
        
        if (this.scalar() && !v.scalar()) {
            for (int i = 0; i < v.dimension; i++) {
                newValues.add(this.values.get(0) * v.values().get(i));
            }
            newVec = new Vector(newValues);
        } else if (!this.scalar() && v.scalar()) {
            for (int i = 0; i < this.dimension; i++) {
                newValues.add(v.values.get(0) * this.values().get(i));
            }
            newVec = new Vector(newValues);
        } else if (this.scalar() && v.scalar()) {
            newValues.add(v.values.get(0) * this.values().get(0));
            newVec = new Vector(newValues, true);
        } else {
            double result = 0.0;
            for (int i = 0; i < this.dimension; i++) {
                result += this.values.get(i) * v.values().get(i);
            }
            newValues.add(result);
            newVec = new Vector(newValues, true);
        }

        return newVec;
    }

    @Override
    public String toString() {
        String res = "";

        if (this.scalar == true) {
            res += values.get(0);
        } else {
            if (values.size() == 0) {
                res += "[]";
            } else if (values.size() == 1) {
                res += "[" + values.get(0) + "]";
            } else {
                res += "[" + values.get(0);
                for (int i = 1; i < values.size(); i++) {
                    res += "," + values.get(i);
                }
                res += "]";
            }
        }

        return res;
    }
}
