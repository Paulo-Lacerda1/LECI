import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Tabela de símbolos que gerencia variáveis declaradas e seus tipos com suporte a escopos aninhados.
 */
public class SymbolTable {

    private final Stack<Map<String, TypeDescriptor>> scopes = new Stack<>();

    public SymbolTable() {
        scopes.push(new HashMap<>());
    }

    /**
     * Declara uma nova variável com seu tipo.
     * Lança exceção se a variável já foi declarada no escopo atual.
     */
    public void declare(String name, TypeDescriptor type) {
        Map<String, TypeDescriptor> currentScope = scopes.peek();
        if (currentScope.containsKey(name)) {
            throw new RuntimeException("Variable '" + name + "' is already declared");
        }
        currentScope.put(name, type);
    }

    /**
     * Procura o tipo de uma variável em qualquer escopo (começando pelo mais recente).
     * Retorna TypeDescriptor.ERROR se a variável não foi declarada.
     */
    public TypeDescriptor lookup(String name) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsKey(name)) {
                return scopes.get(i).get(name);
            }
        }
        return TypeDescriptor.ERROR;
    }

    /**
     * Verifica se uma variável foi declarada em qualquer escopo.
     */
    public boolean isDeclared(String name) {
        for (Map<String, TypeDescriptor> scope : scopes) {
            if (scope.containsKey(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se uma variável foi declarada no escopo atual.
     */
    public boolean isDeclaredInCurrentScope(String name) {
        return scopes.peek().containsKey(name);
    }

    /**
     * Abre um novo escopo (para if/else, blocos, etc).
     */
    public void pushScope() {
        scopes.push(new HashMap<>());
    }

    /**
     * Fecha o escopo atual.
     */
    public void popScope() {
        if (scopes.size() > 1) {
            scopes.pop();
        }
    }

    /**
     * Remove uma variável da tabela de símbolos.
     */
    public void remove(String name) {
        scopes.peek().remove(name);
    }

    /**
     * Assign/atualiza uma variável existente.
     */
    public void assign(String name, TypeDescriptor type) {
        // Procura a variável em qualquer escopo e a atualiza
        for (int i = scopes.size() - 1; i >= 0; i--) {
            if (scopes.get(i).containsKey(name)) {
                scopes.get(i).put(name, type);
                return;
            }
        }
        // Se não encontrar, declara no escopo atual
        scopes.peek().put(name, type);
    }

    /**
     * Limpa toda a tabela de símbolos.
     */
    public void clear() {
        scopes.clear();
        scopes.push(new HashMap<>());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = scopes.size() - 1; i >= 0; i--) {
            sb.append("Scope ").append(i).append(": ").append(scopes.get(i)).append("\n");
        }
        return sb.toString();
    }
}
