import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class Visitor extends FracLangBaseVisitor<Fraction> {

   private HashMap<String, Fraction> vars = new HashMap<>();

   @Override
   public Fraction visitProg(FracLangParser.ProgContext ctx) {
      return visitChildren(ctx);
   }

   @Override
   public Fraction visitStatDisplay(FracLangParser.StatDisplayContext ctx) {
      Fraction value = visit(ctx.expr());

      if (value != null) {
         System.out.println(value);
      }

      return null;
   }

   @Override
   public Fraction visitStatAssign(FracLangParser.StatAssignContext ctx) {
      String id = ctx.var.getText();
      Fraction value = visit(ctx.expr());

      if (value != null) {
         vars.put(id, value);
      }

      return null;
   }

   @Override
   public Fraction visitExprFrac(FracLangParser.ExprFracContext ctx) {
      int num = Integer.parseInt(ctx.INT(0).getText());
      int den = Integer.parseInt(ctx.INT(1).getText());

      if (den == 0) {
         System.err.println("Erro: denominador não pode ser zero.");
         System.exit(1);
      }

      return new Fraction(num, den);
   }

   @Override
   public Fraction visitExprInt(FracLangParser.ExprIntContext ctx) {
      int inteiro = Integer.parseInt(ctx.INT().getText());

      return new Fraction(inteiro, 1);
   }

   @Override
   public Fraction visitExprID(FracLangParser.ExprIDContext ctx) {
      String id = ctx.ID().getText();

      if (!vars.containsKey(id)) {
         System.err.println("Erro: variável " + id + " não definida.");
         System.exit(1);
      }

      return vars.get(id);
   }
}