import java.util.*;

@SuppressWarnings("CheckReturnValue")
public class Execute extends VectorBaseVisitor<Vector> {
   HashMap<String,Vector> vars = new HashMap<String,Vector>();

   @Override public Vector visitShow(VectorParser.ShowContext ctx) {
      Vector value = visit(ctx.expression());
      if (value != null) {
         System.out.println(value);
      }

      return value;
   }

   @Override public Vector visitAssignment(VectorParser.AssignmentContext ctx) {
      Vector res = visit(ctx.expression());
      String id = ctx.ID().getText();
      if (res != null && id != null) {
         vars.put(id, res);
      }

      return res;
   }

   @Override public Vector visitVectorExpr(VectorParser.VectorExprContext ctx) {
      String vector = ctx.VECTOR().getText();
      Vector res = new Vector(vector);
      if (res.error()) {
         System.err.println("ERRO: Vetor inválido!");
         return null;
      }
      return res;
   }

   @Override public Vector visitNumberExpr(VectorParser.NumberExprContext ctx) {
      ArrayList<Double> value = new ArrayList<Double>();
      double number = Double.parseDouble(ctx.NUMBER().getText());
      value.add(number);
      Vector res = new Vector(value,true);
      if (res.error()) {
         System.err.println("ERRO: Vetor inválido!");
         return null;
      }

      return res;
   }

   @Override public Vector visitIdExpr(VectorParser.IdExprContext ctx) {
      Vector res = null;
      String id = ctx.ID().getText();
      if (!vars.containsKey(id)) {
         System.err.println("ERRO: Variável não definida!");
      } else {
         res = vars.get(id);
      }

      return res;
   }

   @Override public Vector visitUnaryExpr(VectorParser.UnaryExprContext ctx) {
      Vector res = null;
      Vector vector = visit(ctx.expression());
      String op = ctx.op.getText();

      if (vector != null && op != null) {
         if (op.equals("-")) {
            res = vector.symmetric();
         } else {
            res = vector;
         }
      }

      return res;
   }

   @Override public Vector visitAddSubExpr(VectorParser.AddSubExprContext ctx) {
      Vector res = null;
      Vector v1 = visit(ctx.expression(0));
      Vector v2 = visit(ctx.expression(1));

      if (v1 != null && v2 != null) {
         if (v1.scalar() != v2.scalar()) {
            System.err.println("ERRO: Não é possível adicionar/subtrair escalares com vetores!");
            return null;
         }
         if (v1.dimension() != v2.dimension()) {
            System.err.println("ERRO: Não é possível adicionar/subtrair elementos com dimensões diferentes!");
            return null;
         }

         switch(ctx.op.getText()) {
            case "+":
               res = v1.add(v2);
               break;
            case "-":
               res = v1.subtract(v2);
               break;
         }
      }

      return res;
   }

   @Override public Vector visitParentExpr(VectorParser.ParentExprContext ctx) {
      return visit(ctx.expression());
   }

   @Override public Vector visitMultExpr(VectorParser.MultExprContext ctx) {
      Vector res = null;
      Vector v1 = visit(ctx.expression(0));
      Vector v2 = visit(ctx.expression(1));

      if (v1 != null && v2 != null) {
         switch(ctx.op.getText()) {
            case "*":
               if (!v1.scalar() && !v2.scalar()) {
                  System.err.println("ERRO: Não existe multiplicação entre dois vetores!");
                  return null;
               } 
               res = v1.multplication(v2);
               break;
            case ".":
               if (v1.scalar() || v2.scalar()) {
                  System.err.println("ERRO: Não existe produto interno sem ser entre vetores!");
                  return null;
               }
               if (v1.dimension() != v2.dimension()) {
                  System.err.println("ERRO: Não existe produto interno entre vetores de dimensões diferentes!");
                  return null;
               }
               res = v1.multplication(v2);
               break;
         }
      }

      return res;
   }
}
