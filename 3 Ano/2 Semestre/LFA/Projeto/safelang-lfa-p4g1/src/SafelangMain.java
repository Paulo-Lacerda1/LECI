import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class SafelangMain {
   public static void main(String[] args) throws Exception {
      CharStream input;
      Path initialBasePath = null;
      String className = "Program"; // Default fallback if reading from System.in

      if (args.length > 0) {
         Path filePath = Paths.get(args[0]);
         input = CharStreams.fromFileName(filePath.toString());
         initialBasePath = filePath.toAbsolutePath().getParent();
         
         // Extract file name without the extension (e.g., "min-01.sl" -> "Min01" or "min_01")
         String rawFileName = filePath.getFileName().toString();
         int lastDot = rawFileName.lastIndexOf('.');
         String baseName = (lastDot > 0) ? rawFileName.substring(0, lastDot) : rawFileName;
         
         // Clean up characters that are illegal in Java class names (like hyphens)
         className = baseName.replace("-", "_"); 
      } else {
         input = CharStreams.fromStream(System.in);
         initialBasePath = Paths.get("").toAbsolutePath();
      }

      input = resolveText(input, initialBasePath);
      
      SafelangLexer lexer = new SafelangLexer(input);
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      
      SafelangParser parser = new SafelangParser(tokens);
      ParseTree tree = parser.program();
      
      if (parser.getNumberOfSyntaxErrors() == 0) {
         SymbolTable symbolTable = new SymbolTable();
         TypeSystem typeSystem = new TypeSystem();
         TypeChecker visitor0 = new TypeChecker(symbolTable, typeSystem);
         System.out.println("\nPre Compiler, Post type checker:\n");
         visitor0.visit(tree);

         JavaCompiler visitor1 = new JavaCompiler("etc/program.stg", symbolTable, typeSystem);
         
         // 1. Get the compiled template
         org.stringtemplate.v4.ST renderedProgram = visitor1.visit(tree).template;
         
         System.out.println("\nPost Compiler\n\n" + typeSystem);
         System.out.println("\n" + symbolTable);

         // 2. Pass the dynamic class name parameter into the StringTemplate
         renderedProgram.add("className", className);

         // 3. Save to a file named after the class (e.g., "min_01.java")
         FileWriter myWriter = new FileWriter(className + ".java");
         myWriter.write(renderedProgram.render());
         myWriter.close();
         
         System.out.println("Compilation successful! Generated " + className + ".java");
         // runProcess(new String[]{"javac", className + ".java"});
      }
   }

   public static void runProcess(String[] args){
      try{
         Process process = Runtime.getRuntime().exec(args);
         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
         String line;
         while ((line = reader.readLine()) != null) {
            System.out.println(line);
         }
         process.waitFor();
      } catch (Exception e) {
         System.err.println(e.getMessage());
      }
   }

   public static CharStream resolveText(CharStream input, Path initialBasePath) throws IOException{
      String text = input.toString();      
      Set<String> visitedFiles = new HashSet<>();

      String resolvedText = resolveImports(text, visitedFiles, initialBasePath);
      //resolvedText = removeComments(resolvedText);

      //System.out.println(resolvedText);
      
      return CharStreams.fromString(resolvedText);
   }

   public static String removeComments(String content) {
      // remove comentários multiline primeiro
      content = content.replaceAll("##[\\s\\S]*?##", "");
      // remove comentários de linha
      content = content.replaceAll("#[^\n]*", "");
      return content;
   }

   public static String resolveImports(String content, Set<String> visitedFiles, Path basePath) throws IOException {
      Pattern pattern = Pattern.compile("use\\s+\"([^\"]+\\.sl)\"\\s*;");
      Matcher matcher = pattern.matcher(content);

      StringBuilder sb = new StringBuilder();

      while (matcher.find()) {
         String filePathStr = matcher.group(1);

         Path resolvedPath = basePath.resolve(filePathStr).normalize();
         String absolutePath = resolvedPath.toAbsolutePath().toString();

         if (visitedFiles.contains(absolutePath)) {
            throw new RuntimeException("Circular dependency detected! File already imported: " + filePathStr);
         }

         if (!Files.exists(resolvedPath)) {
            throw new RuntimeException("Import error: File not found -> " + resolvedPath.toAbsolutePath() + " (requested as \"" + filePathStr + "\")");
         }

         String fileContent = Files.readString(resolvedPath);

         Set<String> localVisited = new HashSet<>(visitedFiles);
         localVisited.add(absolutePath);

         Path nextBasePath = resolvedPath.getParent();
         if (nextBasePath == null) {
            nextBasePath = Paths.get("").toAbsolutePath();
         }

         String resolvedImportContent = resolveImports(fileContent, localVisited, nextBasePath);

         matcher.appendReplacement(sb, Matcher.quoteReplacement(resolvedImportContent));
      }
      matcher.appendTail(sb);

      return sb.toString();
   }
}
