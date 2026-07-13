import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class StrLangMain {
   public static void main(String[] args) throws Exception {
      if(args.length != 1){
         System.out.println("Usage: java StrLangMain <path2file>");
         System.exit(0);
      }
      File f = new File(args[0]);
      if(!f.exists()){
         System.err.println("ERROR: file does not exist");
         System.exit(0);
      }

      StrLangParser parser = new StrLangParser(null);
      // replace error listener:
      //parser.removeErrorListeners(); // remove ConsoleErrorListener
      //parser.addErrorListener(new ErrorHandlingListener());
      Interpreter visitor0 = new Interpreter();
      // create a CharStream that reads from standard input:
      CharStream input = CharStreams.fromStream(new FileInputStream(args[0]));
      // create a lexer that feeds off of input CharStream:
      StrLangLexer lexer = new StrLangLexer(input);
      // create a buffer of tokens pulled from the lexer:
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      // create a parser that feeds off the tokens buffer:
      parser.setInputStream(tokens);
      // begin parsing at program rule:
      ParseTree tree = parser.program();
      if (parser.getNumberOfSyntaxErrors() == 0) {
         // print LISP-style tree:
         // System.out.println(tree.toStringTree(parser));
         visitor0.visit(tree);
      }
   }
}
