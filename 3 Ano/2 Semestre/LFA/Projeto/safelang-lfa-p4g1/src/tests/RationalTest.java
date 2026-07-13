package rational;

import java.util.function.BiFunction;

public class RationalTest {
    private static String[] str_tests = {
            "1/-2", "-1/-2", "-1/2",
            "0.0254", "-0.5", "12.34",
            "0.3e10", "3e10", "-1.2e-5",
            "2312312312", "-42", "+7"};

    private static String[] str_fails = {"dfsas", "122/1212/12", "-0.5/-0.5"};


    public static void main(String[] args) {
        FractionType test_fraction;
        IntegerType test_integer;

        testClass("Integer", true);
        testClass("Fraction", false);

        test_fraction = new FractionType("12.34");
        test_integer = new IntegerType("12");

        System.out.println("\n\n\t\tInteger x Real:\n");
        testArithmetic(test_fraction, test_integer);
        System.out.println("\n\n\t\tInteger x Integer:\n");
        testArithmetic(test_integer, test_integer);
        System.out.println("\n\n\t\tReal x Real:\n");
        testArithmetic(test_fraction, test_fraction);

    }



    private static void testArithmetic(Rational test_fraction, Rational test_integer){
        testDiv("   ADDICTION  ", test_fraction, "+", test_integer, Rational::add);
        testDiv(" SUBTRACTION  ", test_fraction, "-", test_integer, Rational::subtract);
        testDiv("MULTIPLICATION", test_fraction, "*", test_integer, Rational::multiply);
        testDiv("DIVISION REAL ", test_fraction, "/", test_integer, Rational::divide);
        testDiv(" DIVISION INT ", test_fraction, "//", test_integer, Rational::quotient);
        testDiv("  MODULE INT  ", test_fraction, "\\\\", test_integer, Rational::remainder);
    }

    private static void testDiv(String label, Rational num1, String op, Rational num2, BiFunction<Rational, Rational, Rational> operation){
        try {
            Rational result = operation.apply(num1, num2);
            System.out.printf("\t[%s]%20s  %2s  %-8s -> %-20s <=> %20s\n", label, num1, op, num2, result, result.getClass());
        } catch (Exception e){
            System.out.printf("\t[%s]%20s  %2s  %-8s -> %-20s\n", label, num1, op, num2, e.getMessage());
        }
    }



    private static void testClass(String str_type, boolean is_integer) {
        System.out.printf("\n\n\t\t%s test:\n\n", str_type);

        for(String str_test: str_tests){
            testNumber(str_test, is_integer);
        }

        for(String str_test: str_fails){
            testNumber(str_test, is_integer);
        }
    }

    private static void testNumber(String str_test, boolean is_integer){
        try {
            Rational test;
            if(is_integer)
                test = new IntegerType(str_test);
            else
                test = new FractionType(str_test);
            System.out.printf("\t%20s -> %-20s\n", str_test, test.toString());
        }catch (Exception e){
            System.out.printf("\t%20s -> %-20s\n", str_test, e.getMessage());
        }
    }

}
