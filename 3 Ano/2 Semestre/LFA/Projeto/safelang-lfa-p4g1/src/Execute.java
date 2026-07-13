import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import rational.FractionType;
import rational.IntegerType;
import rational.Rational;
import java.math.BigInteger;

public class Execute extends SafelangBaseVisitor<Object> {

    private final SymbolTable symbolTable;
    private final TypeSystem typeSystem;
    private final Map<String, Val> variableValues = new HashMap<>();
    //private final Map<String, FractionType> prefixValues = new HashMap<>();

    private static final Scanner input = new Scanner(System.in);

    // ─── Excepção de controlo de fluxo ───────────────────────────────────────

    /** Sinaliza um fail/rescue */
    static class FailException extends RuntimeException {
        FailException(String msg) { super(msg); }
        FailException() { super("fail"); }
    }

    /** Sinaliza um retry dentro de rescue */
    static class RetryException extends RuntimeException {
        RetryException() { super("retry"); }
    }

    // ─── Valor calculado + tipo ───────────────────────────────────────────────

    static final class Val {
        final Object value;
        final TypeDescriptor type;
        Val(Object value, TypeDescriptor type) {
            this.value = value;
            this.type = type;
        }
    }

    public Execute(SymbolTable symbolTable, TypeSystem typeSystem) {
        this.symbolTable = symbolTable;
        this.typeSystem = typeSystem;
    }

    // ─── Program ─────────────────────────────────────────────────────────────

    @Override
    public Object visitProgram(SafelangParser.ProgramContext ctx) {
        for (SafelangParser.StatContext stat : ctx.stat()) {
            visit(stat);
        }
        return null;
    }

    // ─── Stats ───────────────────────────────────────────────────────────────

    @Override public Object visitStatAssign(SafelangParser.StatAssignContext ctx)  { return visit(ctx.assign()); }
    @Override public Object visitStatWrite(SafelangParser.StatWriteContext ctx)    { return visit(ctx.write()); }
    @Override public Object visitStatExpr(SafelangParser.StatExprContext ctx)      { return visit(ctx.expr()); }
    @Override public Object visitStatType(SafelangParser.StatTypeContext ctx)      { return visit(ctx.type()); }
    @Override public Object visitStatIf(SafelangParser.StatIfContext ctx)          { return visit(ctx.if_()); }
    @Override public Object visitStatTry(SafelangParser.StatTryContext ctx)        { return visit(ctx.try_()); }
    @Override public Object visitStatFor(SafelangParser.StatForContext ctx)        { return visit(ctx.for_()); }
    @Override public Object visitStatWhile(SafelangParser.StatWhileContext ctx)    { return visit(ctx.while_()); }
    @Override public Object visitStatAssert(SafelangParser.StatAssertContext ctx)  { return visit(ctx.assert_()); }
    @Override public Object visitStatFail(SafelangParser.StatFailContext ctx)      { return visit(ctx.fail()); }

    // ─── CommonStats ─────────────────────────────────────────────────────────

    @Override public Object visitCommonStatAssign(SafelangParser.CommonStatAssignContext ctx)   { return visit(ctx.assign()); }
    @Override public Object visitCommonStatWrite(SafelangParser.CommonStatWriteContext ctx)     { return visit(ctx.write()); }
    @Override public Object visitCommonStatExpr(SafelangParser.CommonStatExprContext ctx)      { return visit(ctx.expr()); }
    @Override public Object visitCommonStatIf(SafelangParser.CommonStatIfContext ctx)          { return visit(ctx.if_()); }
    @Override public Object visitCommonStatTry(SafelangParser.CommonStatTryContext ctx)        { return visit(ctx.try_()); }
    @Override public Object visitCommonStatFor(SafelangParser.CommonStatForContext ctx)        { return visit(ctx.for_()); }
    @Override public Object visitCommonStatWhile(SafelangParser.CommonStatWhileContext ctx)    { return visit(ctx.while_()); }
    @Override public Object visitCommonStatAssert(SafelangParser.CommonStatAssertContext ctx)  { return visit(ctx.assert_()); }
    @Override public Object visitCommonStatFail(SafelangParser.CommonStatFailContext ctx)      { return visit(ctx.fail()); }

    // ─── Try / Rescue / Retry ────────────────────────────────────────────────

    @Override
    public Object visitTryNorm(SafelangParser.TryNormContext ctx) {
        try {
            for (SafelangParser.CommonStatContext stat : ctx.commonStat()) {
                visit(stat);
            }
        } catch (FailException e) {
            // sem rescue → propaga como RuntimeException
            throw new RuntimeException("Unhandled fail: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Object visitTryRescue(SafelangParser.TryRescueContext ctx) {
        while (true) {
            try {
                for (SafelangParser.CommonStatContext stat : ctx.commonStat()) {
                    visit(stat);
                }
                return null; // sucesso — sai do loop
            } catch (FailException e) {
                // entra no rescue
                try {
                    visit(ctx.rescue());
                    return null; // rescue sem retry → termina
                } catch (RetryException re) {
                    // retry → volta ao início do try
                }
            } catch (RetryException re) {
                // retry lançado directamente dentro do try (sem rescue intermédio)
            }
        }
    }

    @Override
    public Object visitRescueNorm(SafelangParser.RescueNormContext ctx) {
        for (SafelangParser.CommonStatContext stat : ctx.commonStat()) {
            visit(stat);
        }
        return null;
    }

    @Override
    public Object visitRescueRetry(SafelangParser.RescueRetryContext ctx) {
        for (SafelangParser.CommonStatContext stat : ctx.commonStat()) {
            visit(stat);
        }
        throw new RetryException();
    }

    // ─── For ─────────────────────────────────────────────────────────────────

    @Override
    public Object visitForAssign(SafelangParser.ForAssignContext ctx) {
        String varName = ctx.ID().getText();
        Val fromVal = asVal(visit(ctx.number(0)));
        Val toVal   = asVal(visit(ctx.number(1)));

        long from = asLong(fromVal);
        long to   = asLong(toVal);

        // declarar/sobrescrever variável de iteração no escopo actual
        if (!symbolTable.isDeclared(varName)) {
            symbolTable.declare(varName, TypeDescriptor.INTEGER);
        }

        for (long i = from; i <= to; i++) {
            variableValues.put(varName, new Val(new IntegerType(BigInteger.valueOf(i)), TypeDescriptor.INTEGER));
            symbolTable.pushScope();
            for (SafelangParser.CommonStatContext stat : ctx.commonStat()) {
                visit(stat);
            }
            symbolTable.popScope();
        }
        return null;
    }

    @Override
    public Object visitForNorm(SafelangParser.ForNormContext ctx) {
        Val fromVal = asVal(visit(ctx.number(0)));
        Val toVal   = asVal(visit(ctx.number(1)));

        long from = asLong(fromVal);
        long to   = asLong(toVal);

        for (long i = from; i <= to; i++) {
            symbolTable.pushScope();
            for (SafelangParser.CommonStatContext stat : ctx.commonStat()) {
                visit(stat);
            }
            symbolTable.popScope();
        }
        return null;
    }

    // ─── While / Until ───────────────────────────────────────────────────────

    @Override
    public Object visitWhileNorm(SafelangParser.WhileNormContext ctx) {
        while (true) {
            Val cond = asVal(visit(ctx.booleans()));
            if (!((Boolean) cond.value)) {
                break;
            }
            symbolTable.pushScope();
            for (SafelangParser.CommonStatContext stat : ctx.commonStat()) {
                visit(stat);
            }
            symbolTable.popScope();
        }
        return null;
    }

    @Override
    public Object visitWhileUntil(SafelangParser.WhileUntilContext ctx) {
        while (true) {
            Val cond = asVal(visit(ctx.booleans()));
            if ((Boolean) cond.value) {
                break;
            }
            symbolTable.pushScope();
            for (SafelangParser.CommonStatContext stat : ctx.commonStat()) {
                visit(stat);
            }
            symbolTable.popScope();
        }
        return null;
    }

    // ─── Assert ──────────────────────────────────────────────────────────────

    @Override
    public Object visitAssert(SafelangParser.AssertContext ctx) {
        Val cond = asVal(visit(ctx.booleans()));
        if (!(Boolean) cond.value) {
            throw new FailException("assert failed");
        }
        return null;
    }

    // ─── Fail ────────────────────────────────────────────────────────────────


    @Override
    public Object visitFail(SafelangParser.FailContext ctx) {
        throw new FailException();
    }

    // ─── Write ───────────────────────────────────────────────────────────────

    @Override
    public Object visitWriteExpr(SafelangParser.WriteExprContext ctx) {
        Val expr = asVal(visit(ctx.expr()));
        System.out.print(convertValueToString(expr));
        System.out.flush();
        return null;
    }

    @Override
    public Object visitWriteLnExpr(SafelangParser.WriteLnExprContext ctx) {
        if (ctx.expr() == null) {
            System.out.println();
            System.out.flush();
            return null;
        }
        Val expr = asVal(visit(ctx.expr()));
        System.out.println(convertValueToString(expr));
        System.out.flush();
        return null;
    }

    // ─── Assign ──────────────────────────────────────────────────────────────

    @Override
    public Object visitAssignValType(SafelangParser.AssignValTypeContext ctx) {
        String name = ctx.ID().get(0).getText();
        TypeDescriptor targetType = resolveDeclaredType(ctx);

        if (!symbolTable.isDeclared(name)) {
            symbolTable.declare(name, targetType);
        }

        Val expr = asVal(visit(ctx.expr()));
        if (expr == null) {
            throw new RuntimeException("ERROR: Expression evaluated to null for: " + name);
        }
        if (!isAssignableVal(expr, targetType)) {
            throw new RuntimeException("ERROR: Incompatible types assigning to " + name);
        }

        variableValues.put(name, convertValueToType(expr, targetType));
        return null;
    }

    @Override
    public Object visitAssignVal(SafelangParser.AssignValContext ctx) {
        String name = ctx.ID().getText();
        TypeDescriptor targetType = symbolTable.lookup(name);
        if (targetType.isError()) {
            throw new RuntimeException("ERROR: Variable not declared: " + name);
        }

        Val expr = asVal(visit(ctx.expr()));
        if (!isAssignableVal(expr, targetType)) {
            throw new RuntimeException("ERROR: Incompatible types assigning to " + name);
        }

        variableValues.put(name, convertValueToType(expr, targetType));
        return null;
    }

    @Override
    public Object visitAssignTryVal(SafelangParser.AssignTryValContext ctx) {
        String name = ctx.ID().getText();
        TypeDescriptor targetType = symbolTable.lookup(name);
        int line = ctx.getStart().getLine();

        if (targetType.isError()) {
            System.err.println("[line " + line + "]   [ERROR]  Variable '" + name + "' has not been declared");
            System.exit(1);
        }

        try {
            Val expr = asVal(visit(ctx.expr()));
            if (expr == null) {
                System.err.println("[line " + line + "]   [ERROR]  Expression evaluated to null for: " + name);
                System.exit(1);
            }

            Val result = null;
            if (isAssignableVal(expr, targetType)) {
                result = convertValueToType(expr, targetType);
            } else if (expr.type.isReal() && targetType.isInteger() && expr.type.sameDimension(targetType, typeSystem)) {
                result = convertValueToType(
                    new Val(((Rational) expr.value).toInteger(), TypeDescriptor.INTEGER),
                    targetType);
            } else {
                System.err.println("[line " + line + "]   [ERROR]  Incompatible types assigning to " + name);
                System.exit(1);
            }

            variableValues.put(name, result);
        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg != null && msg.startsWith("ERROR: ")) {
                msg = msg.substring(7);
            }
            System.err.println("[line " + line + "]   [ERROR]  " + msg);
            System.exit(1);
        }
        return null;
    }

    @Override
    public Object visitAssignTryValType(SafelangParser.AssignTryValTypeContext ctx) {
        String name = ctx.ID().get(0).getText();
        TypeDescriptor targetType = resolveDeclaredType(ctx);
        int line = ctx.getStart().getLine();

        if (!symbolTable.isDeclared(name)) {
            symbolTable.declare(name, targetType);
        }

        try {
            Val expr = asVal(visit(ctx.expr()));
            if (expr == null) {
                System.err.println("[line " + line + "]   [ERROR]  Expression evaluated to null for: " + name);
                System.exit(1);
            }
            if (!isAssignableVal(expr, targetType)) {
                System.err.println("[line " + line + "]   [ERROR]  Incompatible types assigning to " + name);
                System.exit(1);
            }

            variableValues.put(name, convertValueToType(expr, targetType));
        } catch (Exception e) {
            String msg = e.getMessage();
            if (msg != null && msg.startsWith("ERROR: ")) {
                msg = msg.substring(7);
            }
            System.err.println("[line " + line + "]   [ERROR]  " + msg);
            System.exit(1);
        }
        return null;
    }

    @Override
    public Object visitAssignType(SafelangParser.AssignTypeContext ctx) {
        String name = ctx.ID().get(0).getText();
        TypeDescriptor targetType = resolveDeclaredType(ctx);

        if (!symbolTable.isDeclared(name)) {
            symbolTable.declare(name, targetType);
        }
        if (!variableValues.containsKey(name)) {
            //variableValues.put(name, new Val(null, targetType));
            variableValues.put(name, defaultValueForType(targetType));
        }
        return null;
    }

    // ─── Lists ───────────────────────────────────────────────────────────────

    // @SuppressWarnings("unchecked")
    // @Override
    // public Object visitAssignListValType(SafelangParser.AssignListValTypeContext ctx) {
    //     String name = ctx.ID().get(0).getText();
    //     boolean isNewList = ctx.getChild(2).getText().equals("new");
    //
    //     TypeDescriptor elementType = resolveTypeToken(
    //         isNewList ? ctx.getChild(10).getText() : ctx.getChild(6).getText());
    //     TypeDescriptor listType = makeListType(elementType);
    //     TypeDescriptor storedElementType = makeElementType(listType);
    //
    //     List<Val> list;
    //     if (isNewList) {
    //         list = new ArrayList<>();
    //     } else {
    //         String sourceName = ctx.getChild(2).getText();
    //         Val sourceVal = variableValues.get(sourceName);
    //         if (sourceVal == null || !(sourceVal.value instanceof List)) {
    //             throw new RuntimeException("ERROR: List not found: " + sourceName);
    //         }
    //         list = copyList((List<Val>) sourceVal.value);
    //     }
    //
    //     if (!symbolTable.isDeclared(name)) {
    //         symbolTable.declare(name, listType);
    //     }
    //     variableValues.put(name, new Val(list, storedElementType));
    //     return null;
    // }

    @SuppressWarnings("unchecked")
    @Override
    public Object visitAssignListValType(SafelangParser.AssignListValTypeContext ctx) {
        String name = ctx.ID().get(0).getText();
        boolean isNewList = ctx.getChild(2).getText().equals("new");

        TypeDescriptor elementType = resolveTypeToken(
            isNewList ? ctx.getChild(10).getText() : ctx.getChild(6).getText());
        TypeDescriptor listType = makeListType(elementType);
        TypeDescriptor storedElementType = makeElementType(listType);

        List<Val> list;
        if (isNewList) {
            TypeDescriptor sourceElementType = resolveTypeToken(ctx.getChild(5).getText());
            ensureListCopyCompatible(makeListType(sourceElementType), elementType);
            list = new ArrayList<>();
        } else {
            String sourceName = ctx.getChild(2).getText();
            TypeDescriptor sourceListType = symbolTable.lookup(sourceName);
            if (sourceListType.isError()) {
                throw new RuntimeException("ERROR: Variable not declared: " + sourceName);
            }
            if (!sourceListType.isList) {
                throw new RuntimeException("ERROR: Variable '" + sourceName + "' is not a list");
            }
            ensureListCopyCompatible(sourceListType, elementType);

            Val sourceVal = variableValues.get(sourceName);
            if (sourceVal == null || !(sourceVal.value instanceof List)) {
                throw new RuntimeException("ERROR: List not found: " + sourceName);
            }
            list = copyList((List<Val>) sourceVal.value);
        }

        if (!symbolTable.isDeclared(name)) {
            symbolTable.declare(name, listType);
        }
        variableValues.put(name, new Val(list, storedElementType));
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object visitAssignListType(SafelangParser.AssignListTypeContext ctx) {
        String name = ctx.ID().get(0).getText();
        TypeDescriptor elementType = resolveTypeToken(ctx.getChild(4).getText());
        TypeDescriptor listType = makeListType(elementType);
        TypeDescriptor storedElementType = makeElementType(listType);

        List<Val> list = new ArrayList<>();

        if (!symbolTable.isDeclared(name)) {
            symbolTable.declare(name, listType);
        }
        variableValues.put(name, new Val(list, storedElementType));
        return null;
    }

    // @SuppressWarnings("unchecked")
    // @Override
    // public Object visitAssignListVal(SafelangParser.AssignListValContext ctx) {
    //     String name = ctx.ID().get(0).getText();
    //     TypeDescriptor targetListType = symbolTable.lookup(name);
    //
    //     if (targetListType.isError()) {
    //         throw new RuntimeException("ERROR: Variable not declared: " + name);
    //     }
    //     if (!targetListType.isList) {
    //         throw new RuntimeException("ERROR: Variable '" + name + "' is not a list");
    //     }
    //
    //     TypeDescriptor storedElementType = makeElementType(targetListType);
    //     boolean isNewList = ctx.getChild(2).getText().equals("new");
    //
    //     List<Val> list;
    //     if (isNewList) {
    //         list = new ArrayList<>();
    //     } else {
    //         String sourceName = ctx.getChild(2).getText();
    //         Val sourceVal = variableValues.get(sourceName);
    //         if (sourceVal == null || !(sourceVal.value instanceof List)) {
    //             throw new RuntimeException("ERROR: List not found: " + sourceName);
    //         }
    //         list = copyList((List<Val>) sourceVal.value);
    //     }
    //
    //     variableValues.put(name, new Val(list, storedElementType));
    //     return null;
    // }

    @SuppressWarnings("unchecked")
    @Override
    public Object visitAssignListVal(SafelangParser.AssignListValContext ctx) {
        String name = ctx.ID().get(0).getText();
        TypeDescriptor targetListType = symbolTable.lookup(name);

        if (targetListType.isError()) {
            throw new RuntimeException("ERROR: Variable not declared: " + name);
        }
        if (!targetListType.isList) {
            throw new RuntimeException("ERROR: Variable '" + name + "' is not a list");
        }

        TypeDescriptor storedElementType = makeElementType(targetListType);
        boolean isNewList = ctx.getChild(2).getText().equals("new");

        List<Val> list;
        if (isNewList) {
            TypeDescriptor sourceElementType = resolveTypeToken(ctx.getChild(5).getText());
            ensureListCopyCompatible(makeListType(sourceElementType), makeElementType(targetListType));
            list = new ArrayList<>();
        } else {
            String sourceName = ctx.getChild(2).getText();
            TypeDescriptor sourceListType = symbolTable.lookup(sourceName);
            if (sourceListType.isError()) {
                throw new RuntimeException("ERROR: Variable not declared: " + sourceName);
            }
            if (!sourceListType.isList) {
                throw new RuntimeException("ERROR: Variable '" + sourceName + "' is not a list");
            }
            ensureListCopyCompatible(sourceListType, targetListType);

            Val sourceVal = variableValues.get(sourceName);
            if (sourceVal == null || !(sourceVal.value instanceof List)) {
                throw new RuntimeException("ERROR: List not found: " + sourceName);
            }
            list = copyList((List<Val>) sourceVal.value);
        }

        variableValues.put(name, new Val(list, storedElementType));
        return null;
    }


    @Override
    public Object visitStatListAdd(SafelangParser.StatListAddContext ctx){
        return visit(ctx.listadd());
    }


    @Override
    public Object visitCommonStatListAdd(SafelangParser.CommonStatListAddContext ctx){
        return visit(ctx.listadd());
    }


    @SuppressWarnings("unchecked")
    @Override
    public Object visitListadd(SafelangParser.ListaddContext ctx) {
        String name = ctx.ID().getText();
        Val val = asVal(visit(ctx.expr()));

        TypeDescriptor listType = symbolTable.lookup(name);
        if (listType.isError()) {
            throw new RuntimeException("ERROR: Variable not declared: " + name);
        }
        if (!listType.isList) {
            throw new RuntimeException("ERROR: Variable '" + name + "' is not a list");
        }

        Val listVal = variableValues.get(name);
        if (listVal == null || !(listVal.value instanceof List)) {
            throw new RuntimeException("ERROR: List not found: " + name);
        }

        if (val.type.isList) {
            throw new RuntimeException("ERROR: Cannot add a list as an element");
        }

        TypeDescriptor elementType = makeElementType(listType);
        if (!isAssignableVal(val, elementType)) {
            throw new RuntimeException("ERROR: Incompatible element type for list " + name);
        }

        ((List<Val>) listVal.value).add(convertValueToType(val, elementType));
        return null;
    }


    /** ID '[' number ']'  — aceder elemento (1-indexed) */
    @SuppressWarnings("unchecked")
    @Override
    public Object visitExprListRetrieveElement(SafelangParser.ExprListRetrieveElementContext ctx) {
        String name = ctx.ID().getText();
        Val indexVal = asVal(visit(ctx.number()));
        int idx = (int) asLong(indexVal) - 1; // 1-indexed
        Val listVal = asVal(variableValues.get(name));
        if (listVal == null) {
            throw new RuntimeException("ERROR: List not found: " + name);
        }
        List<Val> list = (List<Val>) listVal.value;
        // return list.get(idx);
        return getListElementAt(list, idx, name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object visitNumberListRetrieveElement(SafelangParser.NumberListRetrieveElementContext ctx) {
        String name = ctx.ID().getText();
        Val indexVal = asVal(visit(ctx.number()));
        int idx = (int) asLong(indexVal) - 1;
        Val listVal = asVal(variableValues.get(name));
        if (listVal == null) {
            throw new RuntimeException("ERROR: List not found: " + name);
        }
        List<Val> list = (List<Val>) listVal.value;
        // return list.get(idx);
        return getListElementAt(list, idx, name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object visitStringListRetrieveElement(SafelangParser.StringListRetrieveElementContext ctx) {
        String name = ctx.ID().getText();
        Val indexVal = asVal(visit(ctx.number()));
        int idx = (int) asLong(indexVal) - 1;
        Val listVal = asVal(variableValues.get(name));
        if (listVal == null) {
            throw new RuntimeException("ERROR: List not found: " + name);
        }
        List<Val> list = (List<Val>) listVal.value;
        // return list.get(idx);
        return getListElementAt(list, idx, name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object visitBooleanListRetrieveElement(SafelangParser.BooleanListRetrieveElementContext ctx) {
        String name = ctx.ID().get(0).getText();
        // index pode ser IntegerLiteral ou ID
        int idx;
        String idxText = ctx.getChild(2).getText();
        try {
            idx = Integer.parseInt(idxText) - 1;
        } catch (NumberFormatException e) {
            Val idxVal = asVal(variableValues.get(idxText));
            idx = (int) asLong(idxVal) - 1;
        }
        Val listVal = asVal(variableValues.get(name));
        if (listVal == null) {
            throw new RuntimeException("ERROR: List not found: " + name);
        }
        List<Val> list = (List<Val>) listVal.value;
        // return list.get(idx);
        return getListElementAt(list, idx, name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object visitNumberListLength(SafelangParser.NumberListLengthContext ctx) {
        String name = ctx.ID().getText();
        Val listVal = asVal(variableValues.get(name));
        if (listVal == null) {
            throw new RuntimeException("ERROR: List not found: " + name);
        }
        int len = ((List<Val>) listVal.value).size();
        return new Val(new IntegerType(BigInteger.valueOf(len)), TypeDescriptor.INTEGER);
    }

    // ─── Type / dimension declarations ───────────────────────────────────────

    @Override
    public Object visitTypeUnit(SafelangParser.TypeUnitContext ctx) {
        String dimensionName = ctx.ID(0).getText();
        if (typeSystem.dimensionExists(dimensionName)) {
            return null;
        }

        String unitName = ctx.ID(1).getText();
        TypeDescriptor.BaseType base = (ctx.TYPEINT() != null)
            ? TypeDescriptor.BaseType.INTEGER : TypeDescriptor.BaseType.REAL;
        TypeDescriptor baseType = new TypeDescriptor(base, dimensionName, null);
        typeSystem.insertDimensionType(dimensionName, unitName, new String[]{ dimensionName }, baseType);
        return null;
    }

    @Override
    public Object visitTypeUnitSuffix(SafelangParser.TypeUnitSuffixContext ctx) {
        String dimensionName = ctx.ID(0).getText();
        if (typeSystem.dimensionExists(dimensionName)) {
            return null;
        }

        String unitName   = ctx.ID(1).getText();
        String suffixName = ctx.ID(2).getText();
        TypeDescriptor.BaseType base = (ctx.TYPEINT() != null)
            ? TypeDescriptor.BaseType.INTEGER : TypeDescriptor.BaseType.REAL;
        TypeDescriptor baseType = new TypeDescriptor(base, dimensionName, null);
        typeSystem.insertDimensionType(dimensionName, unitName, new String[]{ dimensionName }, suffixName, baseType);
        return null;
    }

    @Override
    public Object visitTypeDependent(SafelangParser.TypeDependentContext ctx) {
        String dimensionName = ctx.ID().getText();
        if (typeSystem.dimensionExists(dimensionName)) {
            return null;
        }

        TypeDescriptor rhsType = asTypeDescriptor(visit(ctx.type_def_expr()));
        TypeDescriptor.BaseType base = (ctx.TYPEINT() != null)
            ? TypeDescriptor.BaseType.INTEGER : TypeDescriptor.BaseType.REAL;
        if (rhsType != null && rhsType.isReal()) {
            base = TypeDescriptor.BaseType.REAL;
        }

        TypeDescriptor baseType = new TypeDescriptor(base, dimensionName, null);
        String unitName = buildUnitNameFromDimExpr(ctx.type_def_expr());
        String[] exponents = buildExponentArray(ctx.type_def_expr());
        typeSystem.insertDimensionType(dimensionName, unitName, exponents, baseType);
        return null;
    }

    @Override
    public Object visitTypeDependentUnit(SafelangParser.TypeDependentUnitContext ctx) {
        String dimensionName = ctx.ID(0).getText();
        if (typeSystem.dimensionExists(dimensionName)) {
            return null;
        }

        String unitName = ctx.ID(1).getText();
        TypeDescriptor rhsType = asTypeDescriptor(visit(ctx.type_def_expr()));
        TypeDescriptor.BaseType base = (ctx.TYPEINT() != null)
            ? TypeDescriptor.BaseType.INTEGER : TypeDescriptor.BaseType.REAL;
        if (rhsType != null && rhsType.isReal()) {
            base = TypeDescriptor.BaseType.REAL;
        }

        TypeDescriptor baseType = new TypeDescriptor(base, dimensionName, null);
        String[] exponents = buildExponentArray(ctx.type_def_expr());
        typeSystem.insertDimensionType(dimensionName, unitName, exponents, baseType);
        return null;
    }

    @Override
    public Object visitTypeDependentUnitSuffix(SafelangParser.TypeDependentUnitSuffixContext ctx) {
        String dimensionName = ctx.ID(0).getText();
        if (typeSystem.dimensionExists(dimensionName)) {
            return null;
        }

        String unitName   = ctx.ID(1).getText();
        String suffixName = ctx.ID(2).getText();
        TypeDescriptor rhsType = asTypeDescriptor(visit(ctx.type_def_expr()));
        TypeDescriptor.BaseType base = (ctx.TYPEINT() != null)
            ? TypeDescriptor.BaseType.INTEGER : TypeDescriptor.BaseType.REAL;
        if (rhsType != null && rhsType.isReal()) {
            base = TypeDescriptor.BaseType.REAL;
        }

        TypeDescriptor baseType = new TypeDescriptor(base, dimensionName, null);
        String[] exponents = buildExponentArray(ctx.type_def_expr());
        typeSystem.insertDimensionType(dimensionName, unitName, exponents, suffixName, baseType);
        return null;
    }

    @Override
    public Object visitTypeByteRange(SafelangParser.TypeByteRangeContext ctx) {
        String typeName = ctx.ID().getText();
        TypeDescriptor.BaseType base = (ctx.TYPEINT() != null)
            ? TypeDescriptor.BaseType.INTEGER : TypeDescriptor.BaseType.REAL;
        long bitRange = Long.parseLong(ctx.IntegerLiteral().getText());

        TypeDescriptor rangeType = new TypeDescriptor(base, (int) bitRange);
        typeSystem.insertTypeRange(typeName, (int) bitRange, rangeType);

        if (!symbolTable.isDeclared(typeName)) {
            symbolTable.declare(typeName, rangeType);
        }
        return null;
    }

    @Override
    public Object visitDimensionUnit(SafelangParser.DimensionUnitContext ctx) {
        String dimensionName = ctx.ID(0).getText();
        if (!typeSystem.dimensionExists(dimensionName)) {
            throw new RuntimeException("ERROR: Dimension not declared: " + dimensionName);
        }

        String unitName = ctx.ID(1).getText();
        Val conversionVal = asVal(visit(ctx.number()));
        FractionType conversionValue = toFractionType(conversionVal);

        if (typeSystem.unitExists(unitName)) {
            typeSystem.changeUnitValue(unitName, conversionValue);
        } else {
            typeSystem.insertUnitType(dimensionName, unitName, conversionValue);
        }

        return null;
    }

    @Override
    public Object visitDimensionUnitSuffix(SafelangParser.DimensionUnitSuffixContext ctx) {
        String dimensionName = ctx.ID(0).getText();
        if (!typeSystem.dimensionExists(dimensionName)) {
            throw new RuntimeException("ERROR: Dimension not declared: " + dimensionName);
        }

        String unitName   = ctx.ID(1).getText();
        String suffixName = ctx.ID(2).getText();
        Val conversionVal = asVal(visit(ctx.number()));
        FractionType conversionValue = toFractionType(conversionVal);

        if (typeSystem.unitExists(unitName)) {
            typeSystem.changeUnitValue(unitName, conversionValue);
        } else {
            typeSystem.insertUnitType(dimensionName, unitName, conversionValue, suffixName);
        }

        return null;
    }

    // ─── Prefix ──────────────────────────────────────────────────────────────
    //
    // prefix Y := 1e24 : real;
    // A gramática trata isto como um assign especial. O TypeChecker/Execute não
    // tem uma regra separada — os prefixos são apenas variáveis reais globais
    // que são depois usadas em expressões.  Aqui registamos o valor para lookup.
    //
    // Prefixos são processados pelo visitAssignValType (prefix Y := 1e24 : real)
    // Nada extra necessário — são variáveis normais de tipo real.
    //
    // ─── Type helpers ────────────────────────────────────────────────────────

    private String buildUnitNameFromDimExpr(SafelangParser.Type_def_exprContext ctx) {
        if (ctx instanceof SafelangParser.TypeDefIDContext) {
            String dim = ((SafelangParser.TypeDefIDContext) ctx).ID().getText();
            String unit = typeSystem.getUnit(dim);
            return unit != null ? unit : dim;
        }
        SafelangParser.TypeDefExprContext bin = (SafelangParser.TypeDefExprContext) ctx;
        return buildUnitNameFromDimExpr(bin.type_def_expr(0))
             + bin.op.getText()
             + buildUnitNameFromDimExpr(bin.type_def_expr(1));
    }

    private String[] buildExponentArray(SafelangParser.Type_def_exprContext ctx) {
        if (ctx instanceof SafelangParser.TypeDefIDContext) {
            return new String[]{ ((SafelangParser.TypeDefIDContext) ctx).ID().getText() };
        }
        SafelangParser.TypeDefExprContext bin = (SafelangParser.TypeDefExprContext) ctx;
        String leftDim  = resolveDimExprName(bin.type_def_expr(0));
        String rightDim = resolveDimExprName(bin.type_def_expr(1));
        return new String[]{ leftDim, bin.op.getText(), rightDim };
    }

    private String resolveDimExprName(SafelangParser.Type_def_exprContext ctx) {
        if (ctx instanceof SafelangParser.TypeDefIDContext) {
            return ((SafelangParser.TypeDefIDContext) ctx).ID().getText();
        }
        TypeDescriptor t = asTypeDescriptor(visit(ctx));
        return t != null ? t.dimension : "unknown";
    }

    @Override
    public Object visitTypeDefExpr(SafelangParser.TypeDefExprContext ctx) {
        TypeDescriptor leftType  = asTypeDescriptor(visit(ctx.type_def_expr(0)));
        TypeDescriptor rightType = asTypeDescriptor(visit(ctx.type_def_expr(1)));
        if (leftType == null || leftType.isError()) {
            return TypeDescriptor.ERROR;
        }
        if (rightType == null || rightType.isError()) {
            return TypeDescriptor.ERROR;
        }

        String op = ctx.op.getText();
        TypeDescriptor.BaseType base = op.equals("/")
            ? TypeDescriptor.BaseType.REAL : promoteBase(leftType, rightType);

        String[] exponents = { leftType.dimension, op, rightType.dimension };
        HashMap<String, Integer> expMap = typeSystem.generateExponents(exponents);
        String resolvedDim = typeSystem.resolveSignature(expMap);
        if (resolvedDim == null) {
            resolvedDim = leftType.dimension + op + rightType.dimension;
        }

        return new TypeDescriptor(base, resolvedDim, null);
    }

    @Override
    public Object visitTypeDefID(SafelangParser.TypeDefIDContext ctx) {
        String dimName = ctx.ID().getText();
        if (!typeSystem.dimensionExists(dimName)) {
            throw new RuntimeException("ERROR: Unknown dimension: " + dimName);
        }
        return typeSystem.getBaseType(dimName);
    }

    // ─── Expr ────────────────────────────────────────────────────────────────

    @Override
    public Object visitStringConcat(SafelangParser.StringConcatContext ctx) {
        Val left  = asVal(visit(ctx.expr(0)));
        Val right = asVal(visit(ctx.expr(1)));
        String result = convertValueToString(left) + convertValueToString(right);
        return new Val(result, TypeDescriptor.STRING);
    }

    @Override
    public Object visitExprFormatCommand(SafelangParser.ExprFormatCommandContext ctx) {
        Val expr    = asVal(visit(ctx.expr()));
        Val columns = asVal(visit(ctx.number()));
        long numCols = asLong(columns);
        String exprStr = convertValueToString(expr);
        // right-align por defeito
        String formatted = String.format("%" + numCols + "s", exprStr);
        return new Val(formatted, TypeDescriptor.STRING);
    }

    @Override
    public Object visitExprFormatCommandPlacement(SafelangParser.ExprFormatCommandPlacementContext ctx) {
        Val expr    = asVal(visit(ctx.expr()));
        Val columns = asVal(visit(ctx.number()));
        String alignment = ctx.op.getText().replace("\"", "");
        long numCols = asLong(columns);
        String exprStr = convertValueToString(expr);

        String formatted;
        int len = exprStr.length();
        if (numCols <= len) {
            formatted = exprStr;
        } else {
            long padding = numCols - len;
            switch (alignment) {
                case "left":
                    formatted = exprStr + " ".repeat((int) padding);
                    break;
                case "right":
                    formatted = " ".repeat((int) padding) + exprStr;
                    break;
                case "center":
                default:
                    long leftPad  = padding / 2;
                    long rightPad = padding - leftPad;
                    formatted = " ".repeat((int) leftPad) + exprStr + " ".repeat((int) rightPad);
                    break;
            }
        }
        return new Val(formatted, TypeDescriptor.STRING);
    }

    @Override
    public Object visitExprID(SafelangParser.ExprIDContext ctx) {
        String name = ctx.ID().getText();
        if (!variableValues.containsKey(name)) {
            throw new RuntimeException("ERROR: Variable not found: " + name);
        }
        return variableValues.get(name);
    }

    @Override
    public Object visitExprNumber(SafelangParser.ExprNumberContext ctx)   { return visit(ctx.number()); }
    @Override
    public Object visitExprString(SafelangParser.ExprStringContext ctx)   { return visitChildren(ctx); }
    @Override
    public Object visitExprBoolean(SafelangParser.ExprBooleanContext ctx) { return visit(ctx.booleans()); }

    // ─── If / Else ───────────────────────────────────────────────────────────

    @Override
    public Object visitIfEnd(SafelangParser.IfEndContext ctx) {
        Val cond = asVal(visit(ctx.booleans()));
        if ((Boolean) cond.value) {
            symbolTable.pushScope();
            for (SafelangParser.CommonStatContext stat : ctx.commonStat()) {
                visit(stat);
            }
            symbolTable.popScope();
        }
        return null;
    }

    @Override
    public Object visitIfElse(SafelangParser.IfElseContext ctx) {
        Val cond = asVal(visit(ctx.booleans()));
        if ((Boolean) cond.value) {
            symbolTable.pushScope();
            for (SafelangParser.CommonStatContext stat : ctx.commonStat()) {
                visit(stat);
            }
            symbolTable.popScope();
        } else if (ctx.else_() != null) {
            visit(ctx.else_());
        }
        return null;
    }

    @Override
    public Object visitElseIf(SafelangParser.ElseIfContext ctx) {
        Val cond = asVal(visit(ctx.booleans()));
        if ((Boolean) cond.value) {
            symbolTable.pushScope();
            for (SafelangParser.CommonStatContext stat : ctx.commonStat()) {
                visit(stat);
            }
            symbolTable.popScope();
        } else if (ctx.else_() != null) {
            visit(ctx.else_());
        }
        return null;
    }

    @Override
    public Object visitElseNorm(SafelangParser.ElseNormContext ctx) {
        symbolTable.pushScope();
        for (SafelangParser.CommonStatContext stat : ctx.commonStat()) {
            visit(stat);
        }
        symbolTable.popScope();
        return null;
    }

    // ─── Booleans ────────────────────────────────────────────────────────────

    @Override
    public Object visitBooleanParent(SafelangParser.BooleanParentContext ctx) { return visit(ctx.booleans()); }

    @Override
    public Object visitBooleanNot(SafelangParser.BooleanNotContext ctx) {
        Val val = asVal(visit(ctx.booleans()));
        return new Val(!(Boolean) val.value, TypeDescriptor.BOOL);
    }

    @Override
    public Object visitBooleanAnd(SafelangParser.BooleanAndContext ctx) {
        Val left  = asVal(visit(ctx.booleans(0)));
        Val right = asVal(visit(ctx.booleans(1)));
        return new Val((Boolean) left.value && (Boolean) right.value, TypeDescriptor.BOOL);
    }

    @Override
    public Object visitBooleanOr(SafelangParser.BooleanOrContext ctx) {
        Val left  = asVal(visit(ctx.booleans(0)));
        Val right = asVal(visit(ctx.booleans(1)));
        return new Val((Boolean) left.value || (Boolean) right.value, TypeDescriptor.BOOL);
    }

    @Override
    public Object visitBooleanEqual(SafelangParser.BooleanEqualContext ctx) {
        Val left  = resolveBooleanOperand(ctx.booleans(0));
        Val right = resolveBooleanOperand(ctx.booleans(1));
        return new Val(compareValsEqual(left, right), TypeDescriptor.BOOL);
    }

    @Override
    public Object visitBooleanNotEqual(SafelangParser.BooleanNotEqualContext ctx) {
        Val left  = resolveBooleanOperand(ctx.booleans(0));
        Val right = resolveBooleanOperand(ctx.booleans(1));
        return new Val(!compareValsEqual(left, right), TypeDescriptor.BOOL);
    }

    @Override
    public Object visitBooleanLesser(SafelangParser.BooleanLesserContext ctx) {
        Val left  = asVal(visit(ctx.number(0)));
        Val right = asVal(visit(ctx.number(1)));
        return new Val(asDouble(left) < asDouble(right), TypeDescriptor.BOOL);
    }

    @Override
    public Object visitBooleanGreater(SafelangParser.BooleanGreaterContext ctx) {
        Val left  = asVal(visit(ctx.number(0)));
        Val right = asVal(visit(ctx.number(1)));
        return new Val(asDouble(left) > asDouble(right), TypeDescriptor.BOOL);
    }

    @Override
    public Object visitBooleanLesserEqual(SafelangParser.BooleanLesserEqualContext ctx) {
        Val left  = asVal(visit(ctx.number(0)));
        Val right = asVal(visit(ctx.number(1)));
        return new Val(asDouble(left) <= asDouble(right), TypeDescriptor.BOOL);
    }

    @Override
    public Object visitBooleanGreaterEqual(SafelangParser.BooleanGreaterEqualContext ctx) {
        Val left  = asVal(visit(ctx.number(0)));
        Val right = asVal(visit(ctx.number(1)));
        return new Val(asDouble(left) >= asDouble(right), TypeDescriptor.BOOL);
    }

    @Override
    public Object visitBooleanLiteral(SafelangParser.BooleanLiteralContext ctx) {
        boolean val = ctx.op.getText().equals("true");
        return new Val(val, TypeDescriptor.BOOL);
    }

    @Override
    public Object visitBooleanID(SafelangParser.BooleanIDContext ctx) {
        String name = ctx.ID().getText();
        Val var = variableValues.get(name);
        if (var == null) {
            throw new RuntimeException("ERROR: Variable not found: " + name);
        }
        if (var.type.isBool()) {
            return var;
        }
        if (var.type.isInteger() || var.type.isReal()) {
            boolean r = var.type.isInteger()
                ? !((IntegerType) var.value).equals(new IntegerType("0"))
                : Math.abs(asDouble(var)) > 1e-10;
            return new Val(r, TypeDescriptor.BOOL);
        }
        if (var.type.isString()) {
            return new Val(!String.valueOf(var.value).isEmpty(), TypeDescriptor.BOOL);
        }
        throw new RuntimeException("ERROR: Cannot use " + var.type + " as boolean");
    }

    @Override
    public Object visitBooleanNumber(SafelangParser.BooleanNumberContext ctx) {
        Val num = asVal(visit(ctx.number()));
        boolean r = num.type.isInteger()
            ? !((IntegerType) num.value).equals(new IntegerType("0"))
            : Math.abs(asDouble(num)) > 1e-10;
        return new Val(r, TypeDescriptor.BOOL);
    }

    @Override
    public Object visitBooleanString(SafelangParser.BooleanStringContext ctx) {
        Val str = asVal(visit(ctx.string()));
        return new Val(!String.valueOf(str.value).isEmpty(), TypeDescriptor.BOOL);
    }

    // ─── String ──────────────────────────────────────────────────────────────

    @Override
    public Object visitStringLiteral(SafelangParser.StringLiteralContext ctx) {
        String text = ctx.StringLiteral().getText();
        text = text.substring(1, text.length() - 1);
        text = text.replace("\\n", "\n").replace("\\t", "\t").replace("\\\\", "\\");
        return new Val(text, TypeDescriptor.STRING);
    }

    @Override
    public Object visitConvertToString(SafelangParser.ConvertToStringContext ctx) {
        Val exprVal = asVal(visit(ctx.expr()));
        String str  = convertValueToString(exprVal);

        // Adicionar sufixo dimensional se existir
        if (exprVal.type != null && exprVal.type.dimension != null) {
            String suffix = resolveDisplaySuffix(exprVal.type.dimension);
            if (!suffix.isEmpty()) {
                str = str + suffix;
            }
        }
        return new Val(str, TypeDescriptor.STRING);
    }

    @Override
    public Object visitReadCmd(SafelangParser.ReadCmdContext ctx) {
        Val prompt = asVal(visit(ctx.string()));
        System.out.print(String.valueOf(prompt.value));
        System.out.flush();
        String line = input.nextLine();
        return new Val(line, TypeDescriptor.STRING);
    }

    @Override
    public Object visitStringID(SafelangParser.StringIDContext ctx) {
        String name = ctx.ID().getText();
        Val var = variableValues.get(name);
        if (var == null) {
            throw new RuntimeException("ERROR: Variable not found: " + name);
        }
        return var;
    }

    // ─── Number ──────────────────────────────────────────────────────────────

    @Override
    public Object visitNumberParent(SafelangParser.NumberParentContext ctx) { return visit(ctx.expr()); }

    @Override
    public Object visitNumberSuffix(SafelangParser.NumberSuffixContext ctx) {
        Val num = asVal(visit(ctx.number()));
        String id = ctx.ID().getText();
        String unitName = resolveUnitName(id);
        if (unitName == null) {
            throw new RuntimeException("ERROR: Unknown unit/suffix: " + id);
        }

        TypeDescriptor dimType = typeSystem.getBaseType(unitName);
        TypeDescriptor.BaseType base = promoteBase(num.type, dimType);
        TypeDescriptor resultType = new TypeDescriptor(base, dimType.dimension, id);

        FractionType convFactor = typeSystem.getUnitValue(unitName);
        Object value = num.value;
        if (convFactor != null) {
            value = ((Rational) num.value).multiply(convFactor);
        }
        return convertValueToType(new Val(value, num.type), resultType);
    }

    @Override
    public Object visitNumberMult(SafelangParser.NumberMultContext ctx) {
        Val left  = asVal(visit(ctx.number(0)));
        Val right = asVal(visit(ctx.number(1)));

        TypeDescriptor resultType = left.type;
        if (!left.type.hasDimension()) {
            resultType = right.type;
        } else if (right.type.hasDimension()) {
            String resultDim = typeSystem.multiplyDimentsionally(left.type.dimension, right.type.dimension);
            if (resultDim == null) {
                resultDim = left.type.dimension + "*" + right.type.dimension;
            }
            resultType = new TypeDescriptor(resultType.base, resultDim, null);
        }

        Rational product = toRational(left).multiply(toRational(right));
        TypeDescriptor.BaseType base = promoteBase(left.type, right.type);
        resultType = new TypeDescriptor(base, resultType.dimension, resultType.unit);

        Object value = base == TypeDescriptor.BaseType.INTEGER
            ? new IntegerType(product.numerator, product.denominator)
            : new FractionType(product.numerator, product.denominator);
        return new Val(value, resultType);
    }

    @Override
    public Object visitNumberDivReal(SafelangParser.NumberDivRealContext ctx) {
        Val left  = asVal(visit(ctx.number(0)));
        Val right = asVal(visit(ctx.number(1)));

        TypeDescriptor resultType;

        if (!left.type.hasDimension() && !right.type.hasDimension()) {
            resultType = TypeDescriptor.REAL;
        } else if (left.type.hasDimension() && !right.type.hasDimension()) {
            resultType = new TypeDescriptor(
                TypeDescriptor.BaseType.REAL,
                left.type.dimension,
                null
            );
        } else if (!left.type.hasDimension() && right.type.hasDimension()) {
            resultType = new TypeDescriptor(
                TypeDescriptor.BaseType.REAL,
                right.type.dimension,
                null
            );
        }
        else {
            String resultDim = typeSystem.divide(left.type.dimension, right.type.dimension);
            if (resultDim == null) {
                resultDim = left.type.dimension + "/" + right.type.dimension;
            }
            resultType = new TypeDescriptor(
                TypeDescriptor.BaseType.REAL,
                resultDim,
                null
            );
        }
        Rational quotient = toRational(left).divide(toRational(right));
        return new Val(new FractionType(quotient.numerator, quotient.denominator), resultType);
    }

    @Override
    public Object visitNumberQuotModInt(SafelangParser.NumberQuotModIntContext ctx) {
        Val left  = asVal(visit(ctx.number(0)));
        Val right = asVal(visit(ctx.number(1)));
        String op = ctx.op.getText();

        long a = asLong(left);
        long b = asLong(right);
        long result = op.equals("//") ? a / b : a % b;
        return new Val(new IntegerType(BigInteger.valueOf(result)), TypeDescriptor.INTEGER);
    }

    @Override
    public Object visitNumberAddSub(SafelangParser.NumberAddSubContext ctx) {
        Val left  = asVal(visit(ctx.number(0)));
        Val right = asVal(visit(ctx.number(1)));
        String op = ctx.op.getText();

        TypeDescriptor.BaseType base = promoteBase(left.type, right.type);
        TypeDescriptor resultType = new TypeDescriptor(base,
            left.type.dimension != null ? left.type.dimension : right.type.dimension, null);

        if (base == TypeDescriptor.BaseType.INTEGER) {
            IntegerType a = (IntegerType) toRational(left);
            IntegerType b = (IntegerType) toRational(right);
            Rational res = op.equals("+") ? a.add(b) : a.subtract(b);
            return new Val(new IntegerType(res.numerator, res.denominator), resultType);
        } else {
            Rational a = toRational(left).toFraction();
            Rational b = toRational(right).toFraction();
            Rational res = op.equals("+") ? a.add(b) : a.subtract(b);
            return new Val(new FractionType(res.numerator, res.denominator), resultType);
        }
    }

    @Override
    public Object visitNumberUnary(SafelangParser.NumberUnaryContext ctx) {
        Val val = asVal(visit(ctx.number()));
        if (ctx.op.getText().equals("-")) {
            if (val.type.isInteger()) {
                IntegerType iv = (IntegerType) val.value;
                return new Val(new IntegerType(iv.numerator.negate(), iv.denominator), val.type);
            } else {
                FractionType fv = (FractionType) toRational(val).toFraction();
                return new Val(new FractionType(fv.numerator.negate(), fv.denominator), val.type);
            }
        }
        return val;
    }

    @Override
    public Object visitConvertToInt(SafelangParser.ConvertToIntContext ctx) {
        Val expr = asVal(visit(ctx.expr()));
        try {
            if (expr.value instanceof Rational rat) {
                return new Val(rat.toInteger(), TypeDescriptor.INTEGER);
            }
            return new Val(new IntegerType(String.valueOf(expr.value).trim()), TypeDescriptor.INTEGER);
        } catch (IllegalArgumentException e) {
            throw new FailException("unable to convert to integer: " + e.getMessage());
        }
    }

    @Override
    public Object visitConvertToReal(SafelangParser.ConvertToRealContext ctx) {
        Val expr = asVal(visit(ctx.expr()));
        try {
            if (expr.value instanceof Rational rat) {
                return new Val(rat.toFraction(), TypeDescriptor.REAL);
            }
            return new Val(new FractionType(String.valueOf(expr.value).trim()), TypeDescriptor.REAL);
        } catch (IllegalArgumentException e) {
            throw new FailException("unable to convert to real: " + e.getMessage());
        }
    }

    // @Override
    // public Object visitConvertToType(SafelangParser.ConvertToTypeContext ctx) {
    //     // ID '(' expr ')' — conversão para tipo dimensional ou prefixo
    //     String typeName = ctx.ID().getText();
    //     Val expr = asVal(visit(ctx.expr()));
    //
    //     // Verificar se é uma dimensão conhecida
    //     if (typeSystem.dimensionExists(typeName)) {
    //         TypeDescriptor targetType = typeSystem.getBaseType(typeName);
    //         return convertValueToType(expr, targetType);
    //     }
    //
    //     // Verificar se é um prefixo (variável do tipo real)
    //     Val prefixVal = variableValues.get(typeName);
    //     if (prefixVal != null && prefixVal.type.isReal()) {
    //         // multiplica pelo factor do prefixo
    //         Rational scaled = toRational(expr).multiply(toRational(prefixVal));
    //         return new Val(new FractionType(scaled.numerator, scaled.denominator), TypeDescriptor.REAL);
    //     }
    //
    //     // Fall-through: devolve expr tal como está
    //     return expr;
    // }

    @Override
    public Object visitConvertToType(SafelangParser.ConvertToTypeContext ctx) {
        // ID '(' expr ')' — conversão para tipo dimensional, com bits, ou prefixo
        String typeName = ctx.ID().getText();
        Val expr = asVal(visit(ctx.expr()));

        if (typeSystem.dimensionExists(typeName)) {
            TypeDescriptor targetType = typeSystem.getBaseType(typeName);
            return convertValueToType(expr, targetType);
        }

        if (typeSystem.rangedTypeExists(typeName)) {
            TypeDescriptor rangedType = typeSystem.getBaseType(typeName);
            return convertValueToType(expr, rangedType);
        }

        Val prefixVal = variableValues.get(typeName);
        if (prefixVal != null && prefixVal.type.isReal()) {
            Rational scaled = toRational(expr).multiply(toRational(prefixVal));
            return new Val(new FractionType(scaled.numerator, scaled.denominator), TypeDescriptor.REAL);
        }

        TypeDescriptor namedType = typeSystem.getBaseType(typeName);
        if (namedType != null && !namedType.isError()) {
            return convertValueToType(expr, namedType);
        }

        return expr;
    }

    @Override
    public Object visitNumberIntLiteral(SafelangParser.NumberIntLiteralContext ctx) {
        return new Val(new IntegerType(ctx.IntegerLiteral().getText()), TypeDescriptor.INTEGER);
    }

    @Override
    public Object visitNumberDecimal(SafelangParser.NumberDecimalContext ctx) {
        return new Val(new FractionType(ctx.NumberDecimal().getText()), TypeDescriptor.REAL);
    }

    @Override
    public Object visitNumberScientific(SafelangParser.NumberScientificContext ctx) {
        return new Val(new FractionType(ctx.NumberScientific().getText()), TypeDescriptor.REAL);
    }

    @Override
    public Object visitNumberID(SafelangParser.NumberIDContext ctx) {
        String name = ctx.ID().getText();

        // Unidade conhecida (ex: meter, second)
        String unitName = resolveUnitName(name);
        if (unitName != null) {
            TypeDescriptor dimType = typeSystem.getBaseType(unitName);
            FractionType unitVal = typeSystem.getUnitValue(unitName);
            Object value = (unitVal != null) ? unitVal
                : (dimType.isInteger() ? new IntegerType("1") : new FractionType("1"));
            return new Val(value, dimType);
        }

        Val var = variableValues.get(name);
        if (var == null) {
            throw new RuntimeException("ERROR: Variable not found: " + name);
        }
        return var;
    }

    // ─── Helpers privados ─────────────────────────────────────────────────────

    private FractionType toFractionType(Val val) {
        if (val.value instanceof FractionType) {
            return (FractionType) val.value;
        }
        if (val.value instanceof Rational rat) {
            return new FractionType(rat.numerator, rat.denominator);
        }
        throw new RuntimeException("ERROR: Expected numeric value, got: " + val.type);
    }

    private Rational toRational(Val val) {
        if (val.value instanceof Rational rat) {
            return rat;
        }
        throw new RuntimeException("ERROR: Expected numeric value, got: " + val.type);
    }

    private Val resolveBooleanOperand(SafelangParser.BooleansContext ctx) {
        if (ctx instanceof SafelangParser.BooleanIDContext idCtx) {
            return variableValues.getOrDefault(idCtx.ID().getText(),
                new Val(null, TypeDescriptor.ERROR));
        }
        if (ctx instanceof SafelangParser.BooleanNumberContext numCtx) {
            return asVal(visit(numCtx.number()));
        }
        if (ctx instanceof SafelangParser.BooleanStringContext strCtx) {
            return asVal(visit(strCtx.string()));
        }
        if (ctx instanceof SafelangParser.BooleanParentContext parCtx) {
            return resolveBooleanOperand(parCtx.booleans());
        }
        return asVal(visit(ctx));
    }

    private boolean compareValsEqual(Val left, Val right) {
        if (left.type.isNumeric() && right.type.isNumeric()) {
            return Math.abs(asDouble(left) - asDouble(right)) < 1e-9;
        }
        if (left.type.isString() && right.type.isString()) {
            return String.valueOf(left.value).equals(String.valueOf(right.value));
        }
        if (left.type.isBool() && right.type.isBool()) {
            return (Boolean) left.value == (Boolean) right.value;
        }
        throw new RuntimeException("ERROR: Cannot compare " + left.type + " with " + right.type);
    }

    private String resolveUnitName(String id) {
        if (typeSystem.unitExists(id)) {
            return id;
        }
        for (var entry : typeSystem.unitSuffixesMap.entrySet()) {
            if (id.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    private String resolveDisplaySuffix(String dimensionName) {
        if (dimensionName == null) {
            return "";
        }

        // 1. Dimensão nomeada com unidade registada (ex: "Length" → "meter" → "m")
        String unitName = typeSystem.getUnit(dimensionName);
        if (unitName != null) {
            String suffix = typeSystem.getSuffix(unitName);
            if (suffix != null) {
                return suffix;
            }
            // Unidade composta sem sufixo directo (ex: "meter/second") → resolver partes
            return replaceDimensionsOrUnitsInExpr(unitName, false);
        }

        // 2. Dimensão estrutural anónima (ex: "Length*Length", "Velocity*Velocity")
        return replaceDimensionsOrUnitsInExpr(dimensionName, true);

    }

    private String replaceDimensionsOrUnitsInExpr(String expr, boolean isDimension) {
        // Dividir preservando os operadores * e /
        String[] tokens = expr.split("(?<=[*/])|(?=[*/])");
        StringBuilder sb = new StringBuilder();
        for (String token : tokens) {
            token = token.trim();
            if (token.equals("*")) {
                sb.append(".");
            } else if (token.equals("/")) {
                sb.append("/");
            } else if (token.isEmpty()) {
                // ignorar
            } else if (isDimension) {
                // token é um nome de dimensão — resolver recursivamente
                sb.append(resolveDisplaySuffix(token));
            } else {
                // token é um nome de unidade — procurar sufixo curto
                String suf = typeSystem.getSuffix(token);
                sb.append(suf != null && !suf.isEmpty() ? suf : token);
            }
        }
        return sb.toString();
    }


    private TypeDescriptor resolveTypeToken(String token) {
        switch (token) {
            case "integer": return TypeDescriptor.INTEGER;
            case "real":    return TypeDescriptor.REAL;
            case "string":  return TypeDescriptor.STRING;
            case "boolean": return TypeDescriptor.BOOL;
            case "bool":    return TypeDescriptor.BOOL;
            default:        return typeSystem.getBaseType(token);
        }
    }




    // Tipos para valores default (em vez de null):
    private Val defaultValueForType(TypeDescriptor type) {
        if (type.isBool()) {
            return new Val(false, type); // boolean Default: false
        }
        if (type.isString()) {
            return new Val("", type); // string Default: ""
        }
        if (type.isInteger()) {
            return new Val(new IntegerType(BigInteger.ZERO), type); // integer Default: 0
        }
        if (type.isReal()) {
            return new Val(new FractionType(BigInteger.ZERO, BigInteger.ONE), type); // real Default: 0.0
        }

        // Não deveria acontecer!!!
        // MAnda um aviso e mantemos o null como fallback....por agora
        System.err.println("WARNING: No default value for type: " + type);
        return new Val(null, type); // fallback: null
    }



    // PATCH: Cria uma cópia independente de um TypeDescriptor.
    //      -> O isList é mutável: ao converter entre tipo elemento NMEC e tipo
    // lista (ex. list[NMEC]) não podemos alterar o original partilhado com o TypeSystem ou symbolTable.
    // logo, acho melhor termos uma cópia independente. Alterar o TypeDescriptor para IMtavel vai dar bue trabalho...
    private TypeDescriptor cloneTypeDescriptor(TypeDescriptor type) {

        TypeDescriptor copy;
        if (type.dimension != null){
            copy = new TypeDescriptor(type.base, type.dimension, type.unit);
        } else if (type.hasBitRange()) {
            copy = new TypeDescriptor(type.base, type.bitRange);
        } else {
            copy = new TypeDescriptor(type.base);
        }
        copy.isList = type.isList;
        return copy;
    }

    private TypeDescriptor makeListType(TypeDescriptor elementType) {
        TypeDescriptor listType = cloneTypeDescriptor(elementType);
        listType.isList = true;
        return listType;
    }

    private TypeDescriptor makeElementType(TypeDescriptor listType) {
        TypeDescriptor elementType = cloneTypeDescriptor(listType);
        elementType.isList = false;
        return elementType;
    }

    private List<Val> copyList(List<Val> source) {
        return new ArrayList<>(source);
    }

    private void ensureListCopyCompatible(TypeDescriptor sourceListType, TypeDescriptor targetType) {
        if (!sourceListType.base.equals(targetType.base)) {
            throw new RuntimeException(
                "ERROR: Cannot assign list[" + sourceListType.base + "] to list[" + targetType.base + "]");
        }
        if (!sourceListType.sameDimension(targetType, typeSystem)) {
            throw new RuntimeException(
                "ERROR: Cannot assign list with dimension [" + sourceListType.dimension
                + "] to list with dimension [" + targetType.dimension + "]");
        }
    }

    private TypeDescriptor resolveDeclaredType(SafelangParser.AssignValTypeContext ctx) {
        if (ctx.TYPEINT() != null) {
            return TypeDescriptor.INTEGER;
        }
        if (ctx.TYPEREAL() != null) {
            return TypeDescriptor.REAL;
        }
        if (ctx.TYPESTR() != null) {
            return TypeDescriptor.STRING;
        }
        if (ctx.TYPEBOOL() != null) {
            return TypeDescriptor.BOOL;
        }
        String dim = ctx.ID().get(1).getText();
        TypeDescriptor t = typeSystem.getBaseType(dim);
        if (t == null || t.isError()) {
            throw new RuntimeException("ERROR: Unknown type: " + dim);
        }
        return t;
    }

    private TypeDescriptor resolveDeclaredType(SafelangParser.AssignTypeContext ctx) {
        if (ctx.TYPEINT() != null) {
            return TypeDescriptor.INTEGER;
        }
        if (ctx.TYPEREAL() != null) {
            return TypeDescriptor.REAL;
        }
        if (ctx.TYPESTR() != null) {
            return TypeDescriptor.STRING;
        }
        if (ctx.TYPEBOOL() != null) {
            return TypeDescriptor.BOOL;
        }
        String dim = ctx.ID().get(1).getText();
        TypeDescriptor t = typeSystem.getBaseType(dim);
        if (t == null || t.isError()) {
            throw new RuntimeException("ERROR: Unknown type: " + dim);
        }
        return t;
    }

    private TypeDescriptor resolveDeclaredType(SafelangParser.AssignTryValTypeContext ctx) {
        if (ctx.TYPEINT() != null) {
            return TypeDescriptor.INTEGER;
        }
        if (ctx.TYPEREAL() != null) {
            return TypeDescriptor.REAL;
        }
        if (ctx.TYPESTR() != null) {
            return TypeDescriptor.STRING;
        }
        if (ctx.TYPEBOOL() != null) {
            return TypeDescriptor.BOOL;
        }
        String dim = ctx.ID().get(1).getText();
        TypeDescriptor t = typeSystem.getBaseType(dim);
        if (t == null || t.isError()) {
            throw new RuntimeException("ERROR: Unknown type: " + dim);
        }
        return t;
    }

    private boolean isAssignableVal(Val expr, TypeDescriptor targetType) {
        if (!expr.type.sameDimension(targetType, typeSystem)) {
            return false;
        }
        if (expr.type.base.equals(targetType.base)) {
            return true;
        }
        if (expr.type.isInteger() && targetType.isReal()) {
            return true;
        }
        return false;
    }

    // private static Val convertValueToType(Val expr, TypeDescriptor targetType) {
    //     if (expr.type.isInteger() && targetType.isReal()) {
    //         Rational rat = (Rational) expr.value;
    //         return new Val(rat.toFraction(), targetType);
    //     }
    //     return new Val(expr.value, targetType);
    // }

    private Val convertValueToType(Val expr, TypeDescriptor targetType) {
        Val converted = convertValueToTypeRaw(expr, targetType);
        if (!fitsInBitRange(converted, targetType)) {
            throw new FailException("ERROR: Bit range overflow");
        }
        return converted;
    }

    private static Val convertValueToTypeRaw(Val expr, TypeDescriptor targetType) {
        if (expr.type.isInteger() && targetType.isReal()) {
            Rational rat = (Rational) expr.value;
            return new Val(rat.toFraction(), targetType);
        }
        return new Val(expr.value, targetType);
    }

    private boolean fitsInBitRange(Val val, TypeDescriptor targetType) {
        if (!targetType.hasBitRange()) {
            return true;
        }

        int bits = targetType.bitRange;
        if (targetType.isInteger()) {
            BigInteger n = extractBigInteger(val);
            BigInteger min = BigInteger.ONE.shiftLeft(bits - 1).negate();
            BigInteger max = BigInteger.ONE.shiftLeft(bits - 1).subtract(BigInteger.ONE);
            return n.compareTo(min) >= 0 && n.compareTo(max) <= 0;
        }
        if (targetType.isReal()) {
            Rational rat = extractRational(val);
            return bitLength(rat.numerator) <= bits && bitLength(rat.denominator) <= bits;
        }
        return true;
    }

    private static int bitLength(BigInteger n) {
        if (n.signum() == 0) {
            return 0;
        }
        return n.abs().bitLength();
    }

    private static BigInteger extractBigInteger(Val val) {
        if (val.value instanceof IntegerType iv) {
            return iv.numerator;
        }
        if (val.value instanceof FractionType fv) {
            return fv.numerator.divide(fv.denominator);
        }
        if (val.value instanceof Rational rat) {
            return rat.numerator.divide(rat.denominator);
        }
        throw new RuntimeException("ERROR: Expected integer value");
    }

    private static Rational extractRational(Val val) {
        if (val.value instanceof Rational rat) {
            return rat;
        }
        throw new RuntimeException("ERROR: Expected numeric value");
    }

    private TypeDescriptor asTypeDescriptor(Object o) { return (TypeDescriptor) o; }

    private TypeDescriptor.BaseType promoteBase(TypeDescriptor left, TypeDescriptor right) {
        return (left.isReal() || right.isReal())
            ? TypeDescriptor.BaseType.REAL : TypeDescriptor.BaseType.INTEGER;
    }

    private static Val asVal(Object o) { return (Val) o; }

    private static long asLong(Val val) {
        if (val.value instanceof IntegerType iv) {
            return iv.numerator.longValue();
        }
        if (val.value instanceof FractionType fv) {
            return fv.numerator.longValue() / fv.denominator.longValue();
        }
        if (val.value instanceof Rational rat) {
            return rat.numerator.longValue() / rat.denominator.longValue();
        }
        throw new RuntimeException("ERROR: Expected integer value");
    }

    private static double asDouble(Val val) {
        if (val.value instanceof Rational rat) {
            return rat.numerator.doubleValue() / rat.denominator.doubleValue();
        }
        if (val.value instanceof Double d) {
            return d;
        }
        throw new RuntimeException("ERROR: Expected numeric value, got " + val.type);
    }

    private static String convertValueToString(Val val) {
        if (val == null || val.value == null) {
            return "null";
        }
        if (val.type.isString()) {
            return String.valueOf(val.value);
        }
        if (val.type.isBool()) {
            return val.value.toString();
        }
        if (val.value instanceof Rational rat) {
            return formatRational(rat);
        }
        return String.valueOf(val.value);
    }

    private static String formatRational(Rational rat) {
        if (rat.denominator.equals(BigInteger.ONE)) {
            return rat.numerator.toString();
        }
        return rat.numerator + "/" + rat.denominator;
    }

    // Usado para aceder a um elem. de uma lista
    private static Val getListElementAt(List<Val> list, int idx, String listName) {
        if (idx < 0 || idx >= list.size()) {
            throw new RuntimeException("ERROR: List index Out of Bounds for: " + listName + " ---> index: " + (idx + 1));
        }
        return list.get(idx);
    }

}