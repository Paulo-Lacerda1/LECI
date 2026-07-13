import java.util.HashMap;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class Visitor extends CalcComplexBaseVisitor<Complex> {

   private HashMap<String, Complex> vars = new HashMap<>();

   @Override
   public Complex visitProgram(CalcComplexParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public Complex visitStatOutput(CalcComplexParser.StatOutputContext ctx) {
      Complex num = visit(ctx.expr());
      System.out.println(num);
      return null;
   }

   @Override
   public Complex visitStatAtribuicao(CalcComplexParser.StatAtribuicaoContext ctx) {
      Complex value = visit(ctx.expr());
      String id = ctx.ID().getText();

      vars.put(id, value);

      return null;
   }

   @Override
   public Complex visitExprAddSub(CalcComplexParser.ExprAddSubContext ctx) {
      Complex left = visit(ctx.expr(0));
      Complex right = visit(ctx.expr(1));

      switch (ctx.op.getText()) {
         case "+":
            return left.add(right);
         case "-":
            return left.sub(right);
         default:
            return null;
      }
   }

   @Override
   public Complex visitExprMultDiv(CalcComplexParser.ExprMultDivContext ctx) {
      Complex left = visit(ctx.expr(0));
      Complex right = visit(ctx.expr(1));

      switch (ctx.op.getText()) {
         case "*":
            return left.mult(right);
         case ":":
            return left.div(right);
         default:
            return null;
      }
   }

   @Override
   public Complex visitExprParent(CalcComplexParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override
   public Complex visitExprComplex(CalcComplexParser.ExprComplexContext ctx) {
      return visit(ctx.complex());
   }

   @Override
   public Complex visitComplexRealImag(CalcComplexParser.ComplexRealImagContext ctx) {
      double real = Double.parseDouble(ctx.INT().getText());
      double imag = parseImag(ctx.IMG().getText());

      if (ctx.op.getText().equals("-")) {
         imag = -imag;
      }

      return new Complex(real, imag);
   }

   @Override
   public Complex visitComplexReal(CalcComplexParser.ComplexRealContext ctx) {
      double real = Double.parseDouble(ctx.INT().getText());
      return new Complex(real, 0);
   }

   @Override
   public Complex visitComplexImag(CalcComplexParser.ComplexImagContext ctx) {
      double imag = parseImag(ctx.IMG().getText());
      return new Complex(0, imag);
   }

   @Override
   public Complex visitExprID(CalcComplexParser.ExprIDContext ctx) {
      String id = ctx.ID().getText();

      if (!vars.containsKey(id)) {
         System.err.println("Erro: variável " + id + " não definida");
         return new Complex(0, 0);
      }

      return vars.get(id);
   }

   private double parseImag(String text) {
      String value = text.substring(0, text.length() - 1);

      if (value.length() == 0) {
         return 1;
      }

      return Double.parseDouble(value);
   }

   @Override public Complex visitExprRead(CalcComplexParser.ExprReadContext ctx) {
   Scanner sc = new Scanner(System.in);

   System.out.print("Parte real: ");
   double real = sc.nextDouble();

   System.out.print("Parte imaginária: ");
   double imag = sc.nextDouble();

   return new Complex(real, imag);
}
}