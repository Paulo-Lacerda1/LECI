package utils;

import rational.IntegerType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.function.Supplier;


public class Utils {

    public static String formatCmd(String text, IntegerType spaces_rat, String mode) {
        int width = spaces_rat.numerator.intValue();

        if (text == null) {
            text = "";
        }

        if (width <= text.length()) {
            return text;
        }

        int totalPadding = width - text.length();

        return switch (mode) {
            case "left" -> text + " ".repeat(totalPadding);

            case "right" -> " ".repeat(totalPadding) + text;

            case "center" -> {
                int leftPadding = totalPadding / 2;
                int rightPadding = totalPadding - leftPadding;
                yield " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
            }

            default -> throw new RuntimeException("InvalidFormat");
        };
    }

    public static void showError(Exception except) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        except.printStackTrace(pw);
        System.err.printf("""
            
                [ERROR]  Exception occurred!
            
                    Exception name:         %s
                    Exception message:      %s
                    Exception cause:        %s
                    Exception stack trace:

                    %s
            """, except.getClass().getSimpleName(), except.getMessage(), getCause(except), getStackTrace(except));
    }

    private static String getCause(Exception except){
        Throwable cause = except.getCause();
        return cause == null ? "Unknow" : cause.toString();
    }

    private static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));

        StringBuilder sb = new StringBuilder();

        for (String line : sw.toString().split("\n"))
            sb.append("    ").append(line).append("\n");

        return sb.toString();
    }
}


    /*
            Try Rescue

    casos a considerar:

        try, rescue, retry, end
        try, rescue, end
        try, end

     */


    /*
    tryRescue(() -> {
        int x = 10 / 0;
    }, "ERROR: invalid number");

    assim apenas executamos a função, sem return
    */
    /*
    public static void tryRescue(Runnable task, boolean rescue, String err_msg){
        while (true) {
            try {
                task.run();
                return;
            } catch (Exception e) {
                if(err_msg != null && !err_msg.isEmpty()) System.err.println(err_msg);
                if(rescue) continue;
                return;
            }
        }
    }
    */
    /*
    tryRescue(() -> {
        return 10 / 0;
    }, "ERROR: invalid number");

    assim conseguimos obter o valor do return
    */
    /*
    public static <T> T tryRescue(Supplier<T> task, boolean rescue, String err_msg) {
        while (true) {
            try {
                return task.get();
            } catch (Exception e) {
                if(err_msg != null && !err_msg.isEmpty()) System.err.println(err_msg);
                if(rescue) continue;
                throw e;
            }
        }
    }
    */
