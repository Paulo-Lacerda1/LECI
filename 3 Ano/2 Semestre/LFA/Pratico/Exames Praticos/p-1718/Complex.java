public class Complex {
   private double real;
   private double imag;

   public Complex(double real, double imag) {
      this.real = real;
      this.imag = imag;
   }

   public double real() {
      return real;
   }

   public double imag() {
      return imag;
   }

   public Complex add(Complex other) {
      return new Complex(this.real + other.real, this.imag + other.imag);
   }

   public Complex sub(Complex other) {
      return new Complex(this.real - other.real, this.imag - other.imag);
   }

   public Complex mult(Complex other) {
      double r = this.real * other.real - this.imag * other.imag;
      double i = this.real * other.imag + this.imag * other.real;
      return new Complex(r, i);
   }

   public Complex div(Complex other) {
      double den = other.real * other.real + other.imag * other.imag;

      double r = (this.real * other.real + this.imag * other.imag) / den;
      double i = (this.imag * other.real - this.real * other.imag) / den;

      return new Complex(r, i);
   }

   @Override
   public String toString() {
      if (imag == 0) {
         return "" + real;
      }

      if (real == 0) {
         if (imag == 1) return "i";
         if (imag == -1) return "-i";
         return imag + "i";
      }

      if (imag > 0) {
         if (imag == 1) return real + "+i";
         return real + "+" + imag + "i";
      }

      if (imag == -1) return real + "-i";
      return real + "" + imag + "i";
   }
}