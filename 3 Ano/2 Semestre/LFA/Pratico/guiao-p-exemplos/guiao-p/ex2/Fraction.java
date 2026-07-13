public class Fraction {
    private int num;
    private int den;

    public Fraction(int num, int den) {
        this.num = num;
        this.den = den;
    }

    @Override
    public String toString() {
        if (den == 1) {
            return Integer.toString(num);
        }

        return num + "/" + den;
    }
}