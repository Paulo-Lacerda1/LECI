import java.util.Map;
import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class Interpreter extends LangComplexBaseVisitor<Complex> {

   private Map<String, Complex> variables = new HashMap<>();

   @Override
   public Complex visitProgram(LangComplexParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public Complex visitStatDisplay(LangComplexParser.StatDisplayContext ctx) {
      Complex value = visit(ctx.expr());

      if (value != null) {
         System.out.println(value);
      }

      return value;
   }

   @Override
   public Complex visitStatAssign(LangComplexParser.StatAssignContext ctx) {
      String varName = ctx.ID().getText();
      Complex value = visit(ctx.expr());

      if (value == null) {
         System.err.println("Erro: valor inválido na atribuição a " + varName);
         return null;
      }

      variables.put(varName, value);
      return value;
   }

   @Override
   public Complex visitExprUnary(LangComplexParser.ExprUnaryContext ctx) {
      Complex value = visit(ctx.expr());

      if (value == null) {
         return null;
      }

      String op = ctx.op.getText();

      if (op.equals("-")) {
         return value.opposite();
      }

      return value;
   }

   @Override
   public Complex visitExprExtract(LangComplexParser.ExprExtractContext ctx) {
      Complex value = visit(ctx.expr());

      if (value == null) {
         return null;
      }

      String op = ctx.op.getText();

      if (op.equals("re")) {
         return value.realPart();
      }

      return value.imagPart();
   }

   @Override
   public Complex visitExprConj(LangComplexParser.ExprConjContext ctx) {
      Complex value = visit(ctx.expr());

      if (value == null) {
         return null;
      }

      return value.conjugate();
   }

   @Override
   public Complex visitExprMod(LangComplexParser.ExprModContext ctx) {
      Complex value = visit(ctx.expr());

      if (value == null) {
         return null;
      }

      return value.modulus();
   }

   @Override
   public Complex visitExprMultDiv(LangComplexParser.ExprMultDivContext ctx) {
      Complex left = visit(ctx.expr(0));
      Complex right = visit(ctx.expr(1));

      if (left == null || right == null) {
         return null;
      }

      String op = ctx.op.getText();

      if (op.equals("*")) {
         return left.mult(right);
      }

      return left.div(right);
   }

   @Override
   public Complex visitExprAddSub(LangComplexParser.ExprAddSubContext ctx) {
      Complex left = visit(ctx.expr(0));
      Complex right = visit(ctx.expr(1));

      if (left == null || right == null) {
         return null;
      }

      String op = ctx.op.getText();

      if (op.equals("+")) {
         return left.add(right);
      }

      return left.sub(right);
   }

   @Override
   public Complex visitExprImagNumber(LangComplexParser.ExprImagNumberContext ctx) {
      double value = Double.parseDouble(ctx.NUMBER().getText());
      return new Complex(0, value);
   }

   @Override
   public Complex visitExprRealNumber(LangComplexParser.ExprRealNumberContext ctx) {
      double value = Double.parseDouble(ctx.NUMBER().getText());
      return new Complex(value, 0);
   }

   @Override
   public Complex visitExprI(LangComplexParser.ExprIContext ctx) {
      return new Complex(0, 1);
   }

   @Override
   public Complex visitExprID(LangComplexParser.ExprIDContext ctx) {
      String varName = ctx.ID().getText();

      if (!variables.containsKey(varName)) {
         System.err.println("Erro: variável " + varName + " não definida");
         return null;
      }

      return variables.get(varName);
   }

   @Override
   public Complex visitExprParent(LangComplexParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }
}