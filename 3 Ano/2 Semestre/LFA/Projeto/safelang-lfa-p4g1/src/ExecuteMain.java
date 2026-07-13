import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecuteMain {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("Uso: java ExecuteMain <ficheiro.sl>");
            System.exit(1);
        }

        // Ler o ficheiro
        Path filePath = Paths.get(args[0]);
        String fileContent = Files.readString(filePath);
        Path basePath = filePath.toAbsolutePath().getParent();

        // Resolver imports
        Set<String> visitedFiles = new HashSet<>();
        String resolvedContent = resolveImports(fileContent, visitedFiles, basePath);

        // Parser
        CharStream input = CharStreams.fromString(resolvedContent);
        SafelangLexer lexer = new SafelangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SafelangParser parser = new SafelangParser(tokens);
        ParseTree tree = parser.program();

        if (parser.getNumberOfSyntaxErrors() > 0) {
            System.err.println("Erros de syntax encontrados!");
            System.exit(1);
        }

        // Interpretação com TypeChecker para registar tipos
        SymbolTable symbolTable = new SymbolTable();
        TypeSystem typeSystem = new TypeSystem();
        TypeChecker typeChecker = new TypeChecker(symbolTable, typeSystem);
        typeChecker.visit(tree);

        // Agora executar
        Execute interpreter = new Execute(symbolTable, typeSystem);
        interpreter.visit(tree);
    }

    /**
     * Recursively resolve imports in SafeLang source code.
     * Processes "use" statements and replaces them with the content of imported files.
     */
    public static String resolveImports(String content, Set<String> visitedFiles, Path basePath) throws Exception {
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
