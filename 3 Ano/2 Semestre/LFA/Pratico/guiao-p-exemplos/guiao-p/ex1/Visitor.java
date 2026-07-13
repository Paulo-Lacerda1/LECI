import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class Visitor extends StrLangBaseVisitor<String> {

   private Map<String, String> vars = new HashMap<>();
   private Scanner sc = new Scanner(System.in);
   
   @Override public String visitProgram(StrLangParser.ProgramContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitStatPrint(StrLangParser.StatPrintContext ctx) {
      String value = visit(ctx.expr());
      System.out.println(value);
      return visitChildren(ctx);

   }

   @Override public String visitStatAssign(StrLangParser.StatAssignContext ctx) {
      String res = null;
      String name = ctx.ID().getText();
      String value = visit(ctx.expr());
   
      vars.put(name,value);

      return res;
   }

   @Override public String visitExprString(StrLangParser.ExprStringContext ctx) {
      String value = ctx.STRING().getText();
      return value.substring(1,value.length()-1);

      
   }

   @Override public String visitExprID(StrLangParser.ExprIDContext ctx) {
      String name = ctx.ID().getText();
      if(!vars.containsKey(name)){
         System.err.println("erro");
      }
      return vars.get(name);
   }

   @Override public String visitExprInput(StrLangParser.ExprInputContext ctx) {
      
      String prompt = ctx.STRING().getText();

      prompt = prompt.substring(1,prompt.length()-1); //remove as aspas
      
      System.out.print(prompt);

      return sc.nextLine();

      
   }

}
