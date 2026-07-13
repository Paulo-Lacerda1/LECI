package rational;

import java.math.BigInteger;

public abstract class Rational extends RationalCore {
    public Rational(String value, int max_index, int size) {
        this.strConverter(value.replace("\\s", ""), max_index);
        this.size = size;
        this.normalize();
        this.checkBitRange();
    }

    public Rational(String value, int max_index) {
        this.strConverter(value.replace("\\s", ""), max_index);
        this.normalize();
        this.checkBitRange();
    }

    public Rational(BigInteger numerator) {
        this.numerator = numerator;
        this.denominator = BigInteger.ONE;
        this.normalize();
        this.checkBitRange();
    }

    public Rational(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO))
            throw new IllegalArgumentException("DenominatorZero");

        this.numerator = numerator;
        this.denominator = denominator;
        this.normalize();
        this.checkBitRange();
    }

    public Rational(BigInteger numerator, BigInteger denominator, int size) {
        if (denominator.equals(BigInteger.ZERO))
            throw new IllegalArgumentException("DenominatorZero");

        this.numerator = numerator;
        this.denominator = denominator;
        this.size = size;
        this.normalize();
        this.checkBitRange();
    }

    public Rational add(Rational other) {
        BigInteger num = this.numerator.multiply(other.denominator)
                .add(other.numerator.multiply(this.denominator));
        BigInteger den = this.denominator.multiply(other.denominator);
        return createFractionIfNeeded(other, num, den);
    }

    public Rational subtract(Rational other) {
        BigInteger num = this.numerator.multiply(other.denominator)
                .subtract(other.numerator.multiply(this.denominator));
        BigInteger den = this.denominator.multiply(other.denominator);
        return createFractionIfNeeded(other, num, den);
    }

    public Rational multiply(Rational other) {
        BigInteger num = this.numerator.multiply(other.numerator);
        BigInteger den = this.denominator.multiply(other.denominator);
        return createFractionIfNeeded(other, num, den);
    }

    public Rational divide(Rational other) {
        if (other.numerator.equals(BigInteger.ZERO))
            throw new ArithmeticException("DivisionByZero");

        BigInteger num = this.numerator.multiply(other.denominator);
        BigInteger den = this.denominator.multiply(other.numerator);

        int resultSize = Math.max(this.size, other.size);

        return new FractionType(num, den, resultSize);
    }

    public Rational quotient(Rational other) {
        ifNotIntegersThrow(other);

        Rational divided = this.divide(other);
        BigInteger intPart = divided.numerator.divide(divided.denominator);

        int resultSize = Math.max(this.size, other.size);

        return new IntegerType(intPart, BigInteger.ONE, resultSize);
    }

    public Rational remainder(Rational other) {
        ifNotIntegersThrow(other);

        Rational divided = this.divide(other);
        BigInteger intPart = divided.numerator.divide(divided.denominator);
        Rational multiple = other.multiply(new IntegerType(intPart));
        Rational result = this.subtract(multiple);

        int resultSize = Math.max(this.size, other.size);

        return new IntegerType(result.numerator, BigInteger.ONE, resultSize);
    }

    public Rational createFractionIfNeeded(Rational other, BigInteger num, BigInteger den) {
        int resultSize = Math.max(this.size, other.size);

        return checkIfNotIntegers(other)
            ? new FractionType(num, den, resultSize)
            : new IntegerType(num, den, resultSize);
    }

    public FractionType toFraction() {
        return new FractionType(this.numerator, this.denominator, this.size);
    }

    public IntegerType toInteger() {
        return new IntegerType(this.numerator.divide(this.denominator), BigInteger.ONE, this.size);
    }

    public FractionType toFraction(int size) {
        return new FractionType(this.numerator, this.denominator, size);
    }

    public IntegerType toInteger(int size) {
        return new IntegerType(this.numerator.divide(this.denominator), BigInteger.ONE, size);
    }

    public void negate() {
        this.numerator = this.numerator.negate();
    }

    public int compareTo(Rational other) {
        if (this.getClass() == other.getClass()) {
            BigInteger lhs = this.numerator.multiply(other.denominator);
            BigInteger rhs = other.numerator.multiply(this.denominator);
            return lhs.compareTo(rhs);
        }
        throw new RuntimeException(String.format("TypeMismatch: cannot compare %s with %s",
                this.getClass().getSimpleName(), other.getClass().getSimpleName()));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Rational other = (Rational) obj;
        return this.numerator.multiply(other.denominator)
                .equals(other.numerator.multiply(this.denominator));
    }

    @Override
    public abstract String toString();


}