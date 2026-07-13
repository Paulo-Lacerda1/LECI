import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;
import java.util.Optional;


public class STGBuilder {

    private final STGroupFile group;


    //ST result = builder.makeST("myTemplate", arg1)
    //    .orElseThrow(() -> new RuntimeException("Template inválido"));
    //builder.makeST("myTemplate", arg1, arg2)
    //    .ifPresent(tmpl -> System.out.println(tmpl.render()));

    public STGBuilder(String stg_path) {
        this.group = new STGroupFile(stg_path);
    }

    public Optional<ST> makeST(String instanceName, Object... args) {
        try {
            ST tmpl = group.getInstanceOf(instanceName);
            if (tmpl == null) {
                System.out.printf("Template '%s' not found\n", instanceName);
                return Optional.empty();
            }
            for (int i = 0; i < args.length; i++) {
                tmpl.add("arg" + (i + 1), args[i]);
            }
            return Optional.of(tmpl);
        } catch (Exception e) {
            System.out.printf("Error creating template '%s': %s\n", instanceName, e);
            return Optional.empty();
        }
    }

    public ST getExpression(ST num1, ST num2, String op) {
        String st_instance = switch (op){
            case "+" -> "opAdd";
            case "-" -> "opSub";
            case "*" -> "opMul";
            case "/" -> {
                if(num2.toString().equals("0"))
                    throw new ArithmeticException("DivisionByZero");

                yield "opDiv";
            }
            case "//" -> "opQuot";
            case "\\" -> "opMod";
            default -> throw new IllegalArgumentException("invalidOperator");
        };

        return this.makeST(st_instance, num1, num2).orElseThrow();
    }
}

