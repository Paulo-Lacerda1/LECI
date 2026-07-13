import java.math.BigInteger;
import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class Execute extends BigIntCalcBaseVisitor<BigInteger> {
   HashMap<String, BigInteger> vars = new HashMap<>();

   @Override public BigInteger visitShow(BigIntCalcParser.ShowContext ctx) {
      BigInteger res = visit(ctx.expression());
      if (res != null) {
         System.out.println(res);
      }
      
      return res;
   }

   @Override public BigInteger visitAssignment(BigIntCalcParser.AssignmentContext ctx) {
      BigInteger res = visit(ctx.expression());
      String id = ctx.ID().getText();
      if (id != null && res != null) {
         vars.put(id, res);
      }

      return res;
   }

   @Override public BigInteger visitMultDivExpr(BigIntCalcParser.MultDivExprContext ctx) {
      BigInteger res = null;
      BigInteger n1 = visit(ctx.expression(0));
      BigInteger n2 = visit(ctx.expression(1));
      String op = ctx.op.getText();
      if (n1 != null && n2 != null && op != null) {
         switch(op) {
            case "*":
               res = n1.multiply(n2);
               break;
            case "div":
               res = n1.divide(n2);
               break;
         }
      }

      return res;
   }

   @Override public BigInteger visitIdExpr(BigIntCalcParser.IdExprContext ctx) {
      BigInteger res = null;
      String id = ctx.ID().getText();
      if (id != null) {
         if (!vars.containsKey(id)) {
            System.err.println("ERRO: Variável não definida");
            return null;
         }
      }
      return vars.get(id);
   }

   @Override public BigInteger visitNumberExpr(BigIntCalcParser.NumberExprContext ctx) {
      return new BigInteger(ctx.NUMBER().getText());
   }

   @Override public BigInteger visitParentExpr(BigIntCalcParser.ParentExprContext ctx) {
      return visit(ctx.expression());
   }

   @Override public BigInteger visitModExpr(BigIntCalcParser.ModExprContext ctx) {
      BigInteger res = null;
      BigInteger n1 = visit(ctx.expression(0));
      BigInteger n2 = visit(ctx.expression(1));

      if (n1 != null && n2 != null) {
         res = n1.mod(n2);
      }
      return res;
   }

   @Override public BigInteger visitUnaryExpr(BigIntCalcParser.UnaryExprContext ctx) {
      String op = ctx.op.getText();
      BigInteger res = visit(ctx.expression());
      if (op.equals("-")) {
         res = res.negate();
      }

      return res;
   }

   @Override public BigInteger visitAddSubExpr(BigIntCalcParser.AddSubExprContext ctx) {
      BigInteger res = null;
      BigInteger n1 = visit(ctx.expression(0));
      BigInteger n2 = visit(ctx.expression(1));
      String op = ctx.op.getText();
      if (n1 != null && n2 != null) {
         if (op.equals("+")) {
            res = n1.add(n2);
         } else if (op.equals("-")) {
            res = n1.subtract(n2);
         }
      }

      return res;
   }
}
