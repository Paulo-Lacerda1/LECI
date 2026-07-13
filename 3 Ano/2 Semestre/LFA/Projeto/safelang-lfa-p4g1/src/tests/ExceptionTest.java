package utils;

import rational.IntegerType;

public class ExceptionTest {
    private static String[] str_tests = {
            "2312312312", "-42", "+7",
            "1/-2", "-1/-2", "-1/2",
            "0.0254", "-0.5", "12.34",
            "0.3e10", "3e10", "-1.2e-5"};

    public static void main(String[] args) throws Exception {

        for(String str_test: str_tests) {
            try {
                IntegerType test = new IntegerType(str_test);
                System.out.println("    Value: " + test.toString());
            }catch (Exception e) {
                Utils.showError(e);
            }
        }

        try {
            level1();
        } catch (Exception e) {
            Utils.showError(e);
        }
    }

    private static void level1() {
        try {
            level2();
        } catch (Exception e) {
            throw new RuntimeException("Level 1 exception", e);
        }
    }

    private static void level2() {
        throw new IllegalArgumentException("Level 2 exception");
    }

}
