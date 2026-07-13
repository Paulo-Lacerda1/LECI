import java.util.*;

@SuppressWarnings("CheckReturnValue")
public class Execute extends CalComplexBaseVisitor<ComplexNumber> {
   HashMap<String,ComplexNumber> vars = new HashMap<String,ComplexNumber>();
   private static Scanner in = new Scanner(System.in);

   @Override public ComplexNumber visitOutput(CalComplexParser.OutputContext ctx) {
      ComplexNumber res = visit(ctx.expression());
      if (res != null) {
         System.out.println(res);
      }

      return res;
   }

   @Override public ComplexNumber visitAssignment(CalComplexParser.AssignmentContext ctx) {
      ComplexNumber res = visit(ctx.expression());
      String id = ctx.ID().getText();
      if (id != null && res != null) {
         vars.put(id,res);
      }

      return res;
   }

   @Override public ComplexNumber visitMultDivExpr(CalComplexParser.MultDivExprContext ctx) {
      ComplexNumber res = null;
      ComplexNumber n1 = visit(ctx.expression(0));
      ComplexNumber n2 = visit(ctx.expression(1));
      if (n1 != null && n2 != null) {
         switch(ctx.op.getText()) {
            case "*":
               res = n1.multiply(n2);
               break;
            case ":":
               res = n1.divide(n2);
               break;
         }
      }

      return res;
   }

   @Override public ComplexNumber visitIdExpr(CalComplexParser.IdExprContext ctx) {
      ComplexNumber res = null;
      String id = ctx.ID().getText();
      if (id != null) {
         if (!vars.containsKey(id)) {
            System.err.println("ERRO: Variável não definida!");
            return null;
         }
      }

      return vars.get(id);
   }

   @Override public ComplexNumber visitComplexExpr(CalComplexParser.ComplexExprContext ctx) {
      ComplexNumber res = new ComplexNumber(ctx.getText());
      return res;
   }

   @Override public ComplexNumber visitNumberExpr(CalComplexParser.NumberExprContext ctx) {
      ComplexNumber res = new ComplexNumber(ctx.getText());
      return res;
   }

   @Override public ComplexNumber visitParentExpr(CalComplexParser.ParentExprContext ctx) {
      return visit(ctx.expression());
   }

   @Override public ComplexNumber visitUnaryExpr(CalComplexParser.UnaryExprContext ctx) {
      ComplexNumber res = null;
      ComplexNumber n = visit(ctx.expression());
      switch(ctx.op.getText()) {
         case "-":
            res = new ComplexNumber(-n.getReal(), -n.getImaginary());
         case "+":
            res = n;
      }

      return res;
   }

   @Override public ComplexNumber visitAddSubExpr(CalComplexParser.AddSubExprContext ctx) {
      ComplexNumber res = null;
      ComplexNumber n1 = visit(ctx.expression(0));
      ComplexNumber n2 = visit(ctx.expression(1));
      if (n1 != null && n2 != null) {
         switch(ctx.op.getText()) {
            case "+":
               res = n1.add(n2);
               break;
            case "-":
               res = n1.subtract(n2);
               break;
         }
      }

      return res;
   }

   @Override public ComplexNumber visitInputExpr(CalComplexParser.InputExprContext ctx) {  
      String n = "";
      System.out.print("Real: ");
      n += in.nextLine();
      System.out.print("Imaginary: ");
      String input = in.nextLine();
      if (input.length() == 1) {
         n += "+" + input + "i";
      } else {
         n += input + "i";
      }

      ComplexNumber res = new ComplexNumber(n);

      return res;
   }
}
