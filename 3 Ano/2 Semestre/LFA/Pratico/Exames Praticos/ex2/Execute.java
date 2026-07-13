import static java.lang.System.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class Execute extends FracLangBaseVisitor<Fraction> {

   @Override public Fraction visitDisplay(FracLangParser.DisplayContext ctx) {
      Fraction res = visit(ctx.expression());
      if (res != null) {
         out.println(res);
      }
      return res;
   }

   @Override public Fraction visitAssignment(FracLangParser.AssignmentContext ctx) {
      Fraction res = visit(ctx.expression());
      String id = ctx.ID().getText();
      if (res != null) {
         symbolTable.put(id, res);
      }
      return res;
   }

   @Override public Fraction visitFractionExpression(FracLangParser.FractionExpressionContext ctx) {
      String frac = ctx.FRACTION().getText();
      Fraction res = new Fraction(frac);
      if (res.error()) {
         err.println("ERROR: invalid fraction");
         return null;
      }
      return res;
   }

   @Override public Fraction visitIdentifierExpression(FracLangParser.IdentifierExpressionContext ctx) {
      Fraction res = null;
      String id = ctx.ID().getText();
      if (!symbolTable.containsKey(id)) {
         err.println("ERROR: undefined identifier");
      } else {
         res = symbolTable.get(id);
      }
      return res;
   }

   @Override public Fraction visitUnaryExpression(FracLangParser.UnaryExpressionContext ctx) {
      Fraction res = visit(ctx.expression());
      if (res != null) {
         if ("-".equals(ctx.op.getText())) {
            res = new Fraction(-res.num(), res.den());
         }
      }
      return res;
   }

   @Override public Fraction visitAdditiveExpression(FracLangParser.AdditiveExpressionContext ctx) {
      Fraction res = null;
      Fraction e1 = visit(ctx.expression(0));
      Fraction e2 = visit(ctx.expression(1));
      String op = ctx.op.getText();
      if (e1 != null && e2 != null) {
         switch(op) {
            case "+":
               res = new Fraction(e1.num() * e2.den() + e2.num() * e1.den(), e1.den() * e2.den());
               break;
            case "-":
               res = new Fraction(e1.num() * e2.den() - e2.num() * e1.den(), e1.den() * e2.den());
               break;
         }
      }
      return res;
   }

   @Override public Fraction visitMultiplicativeExpression(FracLangParser.MultiplicativeExpressionContext ctx) {
      Fraction res = null;
      Fraction e1 = visit(ctx.expression(0));
      Fraction e2 = visit(ctx.expression(1));
      if (e1 != null && e2 != null) {
         switch(ctx.op.getText()) {
            case "*":
               res = new Fraction(e1.num() * e2.num(), e1.den() * e2.den());
               break;
            case ":":
               res = new Fraction(e1.num() * e2.den(), e1.den() * e2.num());
               break;
         }
      }
      return res;
   }

   @Override public Fraction visitParenthesizedExpression(FracLangParser.ParenthesizedExpressionContext ctx) {
      return visit(ctx.expression());
   }

   @Override public Fraction visitReadExpression(FracLangParser.ReadExpressionContext ctx) {
      Fraction res = null;
      String prompt = ctx.STRING().getText();
      prompt = prompt.substring(1, prompt.length() - 1);
      out.print(prompt+": ");
      String frac = in.nextLine();
      res = new Fraction(frac);
      if (res.error()) {
         err.println("ERROR: invalid fraction");
         res = null;
      }
      return res;
   }

   @Override public Fraction visitReduceExpression(FracLangParser.ReduceExpressionContext ctx) {
      Fraction res = visit(ctx.expression());
      if (res != null) {
         res = res.reduce();
      }
      return res;
   }

   private Map<String, Fraction> symbolTable = new HashMap<>();
   private static Scanner in = new Scanner(System.in);
}
