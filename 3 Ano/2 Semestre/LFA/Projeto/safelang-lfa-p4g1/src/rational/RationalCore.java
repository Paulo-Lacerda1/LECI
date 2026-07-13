package rational;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RationalCore {
    public BigInteger numerator;
    public BigInteger denominator;
    public int size = 0;

    private final String[] regex_arr = {
            "^([+-]?\\d+)$",
            // 2312312312, -42, +7

            "^([+-]?\\d+)/([+-]?\\d+)$",
            //  1/-2,   -1/-2,   -1/2
            // -0.5/-0.5
            // -1/-2 -> -1, -2

            "^([+-]?\\d+)\\.(\\d+)$",
            //  0.0254, -0.5,   12.34
            //  0.0254  ->  0 , 0254

            "^([+-]?\\d+(?:\\.\\d+)?)e([+-]?\\d+)$",
            //  0.3e10,  3e10,   -1.2e-5
            //  0.3e10  ->  0.3 , 10
    };

    public void normalize() {
        // implemented by subclasses
    }

    public void strConverter(String value, int max_index) {
        boolean status = false;
        int index = 0;
        Matcher match = null;

        while (index < max_index) {
            Pattern expr = Pattern.compile(regex_arr[index]);
            match = expr.matcher(value);
            status = match.matches();
            if (status) break;
            index++;
        }

        if (!status)
            throw new IllegalArgumentException("InvalidNumberFormat");

        switch (index) {
            case 0 -> strIntCvt(match.group(1));
            case 1 -> strFracCvt(match.group(1), match.group(2));
            case 2 -> strRealCvt(match.group(1), match.group(2));
            case 3 -> strExpCvt(match.group(1), match.group(2));
            default -> throw new RuntimeException("InvalidRegexIndex");
        }
    }

    private void strFracCvt(String num1, String num2) {
        boolean num1_is_neg = num1.contains("-");
        boolean num2_is_neg = num2.contains("-");

        if (num2.equals("0")) throw new IllegalArgumentException("DenominatorZero");

        if (num1_is_neg || num2_is_neg) {
            num1 = num1.replace("-", "");
            num2 = num2.replace("-", "");
        }

        this.numerator = ((num1_is_neg && !num2_is_neg) || (!num1_is_neg && num2_is_neg))
                ? new BigInteger(num1).negate() : new BigInteger(num1);
        this.denominator = new BigInteger(num2);
    }

    private void strRealCvt(String num1, String num2) {
        boolean num1_is_neg = num1.contains("-");
        num1 = num1.replace("-", "");

        BigInteger den = BigInteger.TEN.pow(num2.length());
        this.denominator = new BigInteger(String.valueOf(den));
        this.numerator = new BigInteger(num1 + num2);
        if (num1_is_neg) this.numerator = this.numerator.negate();
    }

    private void strExpCvt(String num1, String num2) {
        BigDecimal mantissa = new BigDecimal(num1);
        int exponent = Integer.parseInt(num2);

        BigDecimal value = mantissa.scaleByPowerOfTen(exponent);
        value = value.stripTrailingZeros();

        int scale = value.scale();

        if (scale < 0) {
            this.numerator = value.unscaledValue().multiply(BigInteger.TEN.pow(-scale));
            this.denominator = BigInteger.ONE;
        } else {
            this.numerator = value.unscaledValue();
            this.denominator = BigInteger.TEN.pow(scale);
        }
    }

    private void strIntCvt(String num1) {
        this.numerator = new BigInteger(num1);
        this.denominator = BigInteger.ONE;
    }

    // IntegerType CHECKER
    protected void ifNotIntegersThrow(Rational other){
        if(checkIfNotIntegers(other))
            throw new RuntimeException("NumbersNotIntegerType");
    }

    protected boolean checkIfNotIntegers(Rational other) {
        return !(this instanceof IntegerType) || !(other instanceof IntegerType);
    }

    protected void checkBitRange() {
        if (size <= 0) return;

        if (this instanceof IntegerType) {
            BigInteger min = BigInteger.ONE.shiftLeft(size - 1).negate();
            BigInteger max = BigInteger.ONE.shiftLeft(size - 1).subtract(BigInteger.ONE);

            if (numerator.compareTo(min) < 0 || numerator.compareTo(max) > 0) {
                throw new RuntimeException(
                    "IntegerOutOfRange: value " + numerator + " does not fit in integer[" + size + "]"
                );
            }
        }
    }
}