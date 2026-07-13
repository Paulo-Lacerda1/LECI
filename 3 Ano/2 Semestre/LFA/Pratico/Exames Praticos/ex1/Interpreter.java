import java.util.HashMap;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class Interpreter extends StrLangBaseVisitor<String> {
   Scanner sc = new Scanner(System.in);
   HashMap <String, String> vars = new HashMap<String, String>();
   
   @Override public String visitProgram(StrLangParser.ProgramContext ctx) {
      String res = null;

      return visitChildren(ctx);
   }

   @Override public String visitStatement(StrLangParser.StatementContext ctx) {
      String res = null;

      return visitChildren(ctx);
   }

   @Override public String visitAssignment(StrLangParser.AssignmentContext ctx) {
      String var = ctx.ID().getText();
      String expr = visit(ctx.expression());

      vars.put(var, expr);
      return expr;
   }

   @Override public String visitPrint(StrLangParser.PrintContext ctx) {
      System.out.println(visit(ctx.expression()));

      return visitChildren(ctx);
   }

   @Override public String visitExprString(StrLangParser.ExprStringContext ctx) {
      String res = ctx.STRING().getText();

      return res.substring(1, res.length()-1);
   }

   @Override public String visitExprID(StrLangParser.ExprIDContext ctx) {
      String var = ctx.ID().getText();

      if(vars.containsKey(var)){
         return vars.get(var);
      } else {
         System.out.println("Error: Variable " + var + " not defined.");
         return null;
      }
   }

   @Override public String visitExprInput(StrLangParser.ExprInputContext ctx) {
      System.out.print(visit(ctx.expression()));
      String res = sc.nextLine();
      
      return res;
   }

   @Override public String visitExprParent(StrLangParser.ExprParentContext ctx) {
      return visit(ctx.expression());
   }

   @Override public String visitExprAdd(StrLangParser.ExprAddContext ctx) {
      String expr1 = visit(ctx.expression(0));
      String expr2 = visit(ctx.expression(1));
      String res = expr1 + expr2;

      return res;
   }

   @Override public String visitExprSub(StrLangParser.ExprSubContext ctx) {
      String expr1 = visit(ctx.expression(0));
      String expr2 = visit(ctx.expression(1));
      String res = expr1.replaceAll(expr2, "");

      return res;
   }

   @Override public String visitExprTrim(StrLangParser.ExprTrimContext ctx) {
      return visit(ctx.expression()).trim();
   }

   @Override public String visitExprReplace(StrLangParser.ExprReplaceContext ctx) {
      String expr1 = visit(ctx.expression(0));
      String expr2 = visit(ctx.expression(1));
      String expr3 = visit(ctx.expression(2));
      String res = expr1.replace(expr2, expr3);
      
      return res;
   }
}
