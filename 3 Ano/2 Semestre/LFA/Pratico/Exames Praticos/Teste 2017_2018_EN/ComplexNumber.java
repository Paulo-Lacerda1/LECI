public class ComplexNumber {
    private int real;
    private int imaginary;

    public ComplexNumber(int real, int imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber(int real) {
        this(real, 0);
    }

    public ComplexNumber(String complex) {
        String[] parts;
        if (complex.contains("+")) {
            parts = complex.split("\\+");
            this.real = Integer.parseInt(parts[0]);
            this.imaginary = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
        } else if (complex.contains("-")) {
            parts = complex.split("-");
            this.real = Integer.parseInt(parts[0]);
            if (parts[1].length() == 1) { 
                this.imaginary = -1;
            } else {
                this.imaginary = -Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
            }
        } else {
            if (complex.contains("i")) {
                this.real = 0;
                if (complex.length() > 1) {
                    this.imaginary = Integer.parseInt(complex.substring(0, complex.length() - 1));
                } else {
                    this.imaginary = 1;
                }
            } else {
                this.real = Integer.parseInt(complex);
                this.imaginary = 0;
            }
        }
    }

    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        if (this.imaginary == 0) {
            return new ComplexNumber(this.real - other.real, other.imaginary);
        } else {
            return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
        }
    }

    public ComplexNumber multiply(ComplexNumber other) {
        return new ComplexNumber(this.real * other.real - this.imaginary * other.imaginary,
                this.real * other.imaginary + this.imaginary * other.real);
    }

    public ComplexNumber divide(ComplexNumber other) {
        return new ComplexNumber((this.real * other.real + this.imaginary * other.imaginary) /
                (other.real * other.real + other.imaginary * other.imaginary),
                (this.imaginary * other.real - this.real * other.imaginary) /
                        (other.real * other.real + other.imaginary * other.imaginary));
    }

    public int getReal() {
        return this.real;
    }

    public int getImaginary() {
        return this.imaginary;
    }

    public String toString() {
        if (this.imaginary == 0) {
            return this.real + "";
        } else if (this.real == 0) {
            return this.imaginary == 0 ? "0"
                    : (this.imaginary == 1 ? "i" : (this.imaginary == -1 ? "-i" : this.imaginary + "i"));

        } else {
            return this.real +
                    (this.imaginary == 0 ? ""
                            : (this.imaginary > 0 ? " + " : " - ") +
                                    (Math.abs(this.imaginary) == 1 ? "i" : Math.abs(this.imaginary) + "i"));

        }
    }
}
