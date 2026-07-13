import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class LangComplexg4 extends LangComplexBaseVisitor<Complex> {

   private HashMap<String, Complex> vars = new HashMap<>();

   @Override
   public Complex visitProgram(LangComplexParser.ProgramContext ctx) {
      Complex res = null;

      for (LangComplexParser.StatContext s : ctx.stat()) {
         res = visit(s);
      }

      return res;
   }

   @Override
   public Complex visitStatDisplay(LangComplexParser.StatDisplayContext ctx) {
      Complex res = visit(ctx.expr());
      System.out.println(res);
      return res;
   }

   @Override
   public Complex visitStatAssign(LangComplexParser.StatAssignContext ctx) {
      String id = ctx.ID().getText();
      Complex value = visit(ctx.expr());

      vars.put(id, value);

      return value;
   }

   @Override
   public Complex visitExprAddSub(LangComplexParser.ExprAddSubContext ctx) {
      Complex left = visit(ctx.expr());
      Complex right = visit(ctx.atom());

      String op = ctx.op.getText();

      if (op.equals("+")) {
         return left.add(right);
      }
      else {
         return left.sub(right);
      }
   }

   @Override
   public Complex visitExprAtom(LangComplexParser.ExprAtomContext ctx) {
      return visit(ctx.atom());
   }

   @Override
   public Complex visitAtomImagNumber(LangComplexParser.AtomImagNumberContext ctx) {
      double imag = Double.parseDouble(ctx.NUMBER().getText());

      return new Complex(0, imag);
   }

   @Override
   public Complex visitAtomImagUnit(LangComplexParser.AtomImagUnitContext ctx) {
      return new Complex(0, 1);
   }

   @Override
   public Complex visitAtomReal(LangComplexParser.AtomRealContext ctx) {
      double real = Double.parseDouble(ctx.NUMBER().getText());

      return new Complex(real, 0);
   }

   @Override
   public Complex visitAtomID(LangComplexParser.AtomIDContext ctx) {
      String id = ctx.ID().getText();

      if (!vars.containsKey(id)) {
         System.err.println("Erro: variável " + id + " não definida");
         return new Complex(0, 0);
      }

      return vars.get(id);
   }
}