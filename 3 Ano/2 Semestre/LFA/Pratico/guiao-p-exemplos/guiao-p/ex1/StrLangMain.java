import java.io.IOException;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class StrLangMain {
   public static void main(String[] args) {
      try {
         if (args.length != 1) {
            System.err.println("Uso: java StrLangMain ficheiro");
            System.exit(1);
         }

         // create a CharStream that reads from file:
         CharStream input = CharStreams.fromFileName(args[0]);

         // create a lexer that feeds off of input CharStream:
         StrLangLexer lexer = new StrLangLexer(input);

         // create a buffer of tokens pulled from the lexer:
         CommonTokenStream tokens = new CommonTokenStream(lexer);

         // create a parser that feeds off the tokens buffer:
         StrLangParser parser = new StrLangParser(tokens);

         // begin parsing at program rule:
         ParseTree tree = parser.program();

         if (parser.getNumberOfSyntaxErrors() == 0) {
            Visitor visitor0 = new Visitor();
            visitor0.visit(tree);
         }
      }
      catch(IOException e) {
         e.printStackTrace();
         System.exit(1);
      }
      catch(RecognitionException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
}