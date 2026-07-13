package rational;

import java.math.BigInteger;

public class FractionType extends Rational {
    public FractionType(String value) { super(value, 4); }
    public FractionType(BigInteger numerator) { super(numerator); }
    public FractionType(BigInteger numerator, BigInteger denominator) { super(numerator, denominator); }
    public FractionType(BigInteger numerator, BigInteger denominator, int size) { super(numerator, denominator, size); }

    public FractionType(String value, int size) {
        super(value, 4, size);
    }

    @Override
    public void normalize() {
        if (this.denominator == null || this.numerator == null) return;
        BigInteger gcd   = this.numerator.gcd(this.denominator);
        this.numerator   = this.numerator.divide(gcd);
        this.denominator = this.denominator.divide(gcd);

        if (this.denominator.signum() < 0) {
            this.numerator   = this.numerator.negate();
            this.denominator = this.denominator.negate();
        }
    }

    @Override
    public String toString() {
        return denominator.equals(BigInteger.ONE) ?
                String.valueOf(numerator) :
                String.format("%s/%s", numerator, denominator);
    }
}