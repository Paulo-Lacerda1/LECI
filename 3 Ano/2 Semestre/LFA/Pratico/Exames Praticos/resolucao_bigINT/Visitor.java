import java.util.HashMap;
import java.math.BigInteger;

@SuppressWarnings("CheckReturnValue")
public class Visitor extends BigIntCalcBaseVisitor<BigInteger> {

   private HashMap<String, BigInteger> vars = new HashMap<>();

   @Override
   public BigInteger visitProgram(BigIntCalcParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public BigInteger visitStatShow(BigIntCalcParser.StatShowContext ctx) {
      BigInteger num = visit(ctx.expr());

      if (num != null) {
         System.out.println(num);
      }

      return null;
   }

   @Override
   public BigInteger visitStatAssign(BigIntCalcParser.StatAssignContext ctx) {
      BigInteger num = visit(ctx.expr());
      String id = ctx.ID().getText();

      if (num != null) {
         vars.put(id, num);
      }

      return null;
   }

   @Override
   public BigInteger visitExprInt(BigIntCalcParser.ExprIntContext ctx) {
      return new BigInteger(ctx.INT().getText());
   }

   @Override
   public BigInteger visitExprId(BigIntCalcParser.ExprIdContext ctx) {
      String id = ctx.ID().getText();

      if (!vars.containsKey(id)) {
         System.err.println("ERROR: variável " + id + " não definida");
         return null;
      }

      return vars.get(id);
   }
   
   @Override
   public BigInteger visitExprAddSub(BigIntCalcParser.ExprAddSubContext ctx) {
      BigInteger left = visit(ctx.expr(0));
      BigInteger right = visit(ctx.expr(1));

      if (left == null || right == null) {
         return null;
      }

      switch (ctx.op.getText()) {
         case "+":
            return left.add(right);
         case "-":
            return left.subtract(right);
         default:
            return null;
      }
   }

   @Override
   public BigInteger visitExprMulDivMod(BigIntCalcParser.ExprMulDivModContext ctx) {
      BigInteger left = visit(ctx.expr(0));
      BigInteger right = visit(ctx.expr(1));

      if (left == null || right == null) {
         return null;
      }

      switch (ctx.op.getText()) {
         case "*":
            return left.multiply(right);
         case "div":
            if (right.equals(BigInteger.ZERO)) {
               System.err.println("ERROR: divisão por zero");
               return null;
            }
            return left.divide(right);
         case "mod":
            if (right.equals(BigInteger.ZERO)) {
               System.err.println("ERROR: resto da divisão por zero");
               return null;
            }
            return left.remainder(right);
         default:
            return null;
      }
   }

   @Override
   public BigInteger visitExprUnary(BigIntCalcParser.ExprUnaryContext ctx) {
      BigInteger value = visit(ctx.expr());

      if (value == null) {
         return null;
      }

      switch (ctx.op.getText()) {
         case "+":
            return value;
         case "-":
            return value.negate();
         default:
            return null;
      }
   }

   @Override
   public BigInteger visitExprParent(BigIntCalcParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }
   }