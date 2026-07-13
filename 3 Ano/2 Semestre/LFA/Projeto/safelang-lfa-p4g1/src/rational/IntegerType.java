package rational;

import java.math.BigInteger;

public class IntegerType extends Rational {
    public IntegerType(String value)         { super(value, 1); }
    public IntegerType(BigInteger numerator) { super(numerator); }
    public IntegerType(BigInteger numerator, BigInteger denominator) { super(numerator, denominator); }
    public IntegerType(BigInteger numerator, BigInteger denominator, int size) { super(numerator, denominator, size); }

    public IntegerType(String value, int size) {
        super(value, 1, size);
    }

    @Override
    public void normalize() {
        if (this.denominator == null || this.numerator == null) return;
        if (this.denominator.equals(BigInteger.ONE)) return;
        this.numerator   = this.numerator.divide(this.denominator);
        this.denominator = BigInteger.ONE;
    }

    @Override
    public String toString() {
        return numerator.toString();
    }
}