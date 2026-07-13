public class Fraction {

    public Fraction(int num, int den) {
        this.num = num;
        this.den = den;
        if (den < 0) {
            this.num = -num;
            this.den = -den;
        }
    }

    public Fraction(int num) {
        this.num = num;
        this.den = 1;
    }

    public Fraction(String frac) {
        String[] part = frac.split("/");
        try {
            switch(part.length) {
                case 1:
                    num = Integer.parseInt(part[0]);
                    den = 1;
                    break;
                case 2:
                    num = Integer.parseInt(part[0]);
                    den = Integer.parseInt(part[1]);
                    break;
                default:
                    error = true;
            }
        }
        catch (NumberFormatException e) {
            error = true;
        }    
    }

    public int num() {
        return num;
    }

    public int den() {
        return den;
    }

    public boolean error() {
        return error;
    }

    @Override public String toString() {
        return ""+num+(den == 1 ? "" : "/"+den);
    }

    public Fraction reduce() {
        int mdc = mdc(num, den);
        return new Fraction(num / mdc, den / mdc);
    }

    private int mdc(int a , int b) {
        int res = a;
        if (b != 0) {
            res = mdc(b, a % b);
        } else if (a == 0) {
            res = 1;
        }
        return res;
    }

    private int num, den;
    private boolean error = false;
}
