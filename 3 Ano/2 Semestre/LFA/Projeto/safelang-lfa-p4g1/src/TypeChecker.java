import java.lang.reflect.Type;
import java.util.HashMap;

public class TypeChecker extends SafelangBaseVisitor<TypeDescriptor> {

    private SymbolTable symbolTable = new SymbolTable();
    private TypeSystem typeSystem = new TypeSystem();

    public TypeChecker(SymbolTable symbolTable, TypeSystem typeSystem) {
        this.typeSystem = typeSystem;
        this.symbolTable = symbolTable;
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────

    /**
     * Promote base type: if either operand is REAL, return REAL; otherwise INTEGER.
     */
    private TypeDescriptor.BaseType promoteBase(TypeDescriptor left, TypeDescriptor right) {
        return (left.isReal() || right.isReal())
            ? TypeDescriptor.BaseType.REAL
            : TypeDescriptor.BaseType.INTEGER;
    }

    /**
     * Check assignment compatibility:
     *   - dimensional  → adimensional : error
     *   - adimensional → dimensional  : error
     *   - dimensional  → dimensional  with different dimension : error
     *
     * The declared type annotation (typeTargetStr) may be either a primitive
     * ("integer", "real", "string") or a dimension name.
     *
     * Returns the correct TypeDescriptor to store for the variable, or ERROR.
     */
    private TypeDescriptor checkAssignCompatibility(
            TypeDescriptor exprType,
            String typeTargetStr,
            org.antlr.v4.runtime.ParserRuleContext ctx
    ) {
        TypeDescriptor targetType = resolveAnnotatedType(typeTargetStr);

        if (targetType == null || targetType.isError()) {
            reportSemanticError(ctx, "Unknown type annotation '" + typeTargetStr + "'");
            return TypeDescriptor.ERROR;
        }

        if (exprType == null || exprType.isError()) {
            reportSemanticError(ctx, "Invalid expression type");
            return TypeDescriptor.ERROR;
        }

        if (exprType.isList || targetType.isList) {
            if (exprType.isList != targetType.isList) {
                reportSemanticError(ctx, "Cannot assign list and non-list types");
                return TypeDescriptor.ERROR;
            }
        }

        boolean exprDim = exprType.hasDimension();
        boolean targetDim = targetType.hasDimension();

        boolean exprRanged = exprType.hasBitRange();
        boolean targetRanged = targetType.hasBitRange();

        // Ranged types are not dimensional types.
        if ((exprRanged || targetRanged) && (exprDim || targetDim)) {
            reportSemanticError(ctx, "Bit-ranged types are incompatible with dimensional types");
            return TypeDescriptor.ERROR;
        }

        // Ranged target: expression must be same primitive base.
        if (targetRanged) {
            if (!sameBaseOrIntToReal(exprType, targetType)) {
                reportSemanticError(ctx,
                    "Cannot assign [" + exprType + "] to bit-ranged type [" + targetType + "]");
                return TypeDescriptor.ERROR;
            }

            if (exprRanged && !sameBitRange(exprType, targetType)) {
                reportSemanticError(ctx,
                    "Bit range mismatch: [" + exprType.bitRange + "] and [" + targetType.bitRange + "]");
                return TypeDescriptor.ERROR;
            }

            return cloneType(targetType);
        }

        // Ranged expression into normal primitive target is okay if base is compatible.
        if (exprRanged) {
            if (!sameBaseOrIntToReal(exprType, targetType)) {
                reportSemanticError(ctx,
                    "Cannot assign bit-ranged value [" + exprType + "] to [" + targetType + "]");
                return TypeDescriptor.ERROR;
            }

            return cloneType(targetType);
        }

        if (exprDim && !targetDim) {
            reportSemanticError(ctx,
                "Cannot assign dimensional value [" + exprType + "] to adimensional type [" + typeTargetStr + "]");
            return TypeDescriptor.ERROR;
        }

        if (!exprDim && targetDim) {
            reportSemanticError(ctx,
                "Cannot assign adimensional value [" + exprType + "] to dimensional type [" + typeTargetStr + "]");
            return TypeDescriptor.ERROR;
        }

        if (exprDim && !exprType.sameDimension(targetType, typeSystem)) {
            reportSemanticError(ctx,
                "Dimensional mismatch: expression is [" + exprType.dimension + "], expected [" + targetType.dimension + "]");
            return TypeDescriptor.ERROR;
        }

        if (exprType.isInteger() && targetType.isReal()) {
            return new TypeDescriptor(TypeDescriptor.BaseType.REAL,
                targetType.dimension,
                targetType.unit);
        }

        if (targetDim) {
            return new TypeDescriptor(promoteBase(exprType, targetType),
                targetType.dimension,
                null);
        }

        if (!targetType.base.equals(exprType.base)) {
            reportSemanticError(ctx, "Cannot assign a ['" + exprType + "'] to a ['" + typeTargetStr + "'].");
            return TypeDescriptor.ERROR;
        }

        return cloneType(targetType);
    }
    
    // ─── Stats ───────────────────────────────────────────────────────────────

    @Override
    public TypeDescriptor visitProgram(SafelangParser.ProgramContext ctx) {
        TypeDescriptor ret = visitChildren(ctx);

        System.out.println(symbolTable);
        System.out.println(typeSystem);

        return ret;
    }

    @Override
    public TypeDescriptor visitStatAssign(SafelangParser.StatAssignContext ctx) {
        TypeDescriptor assignType = visit(ctx.assign());

        if (assignType.isError()) {
            System.err.println("Error during type checker in an assignment");
            System.exit(1);
        }

        return assignType;
    }

    @Override
    public TypeDescriptor visitStatExpr(SafelangParser.StatExprContext ctx) {
        TypeDescriptor exprType = visit(ctx.expr());

        if (exprType == null || exprType.isError()) {
            reportSemanticError(ctx, "Invalid expression statement: '" + ctx.getText() + "'");
            return TypeDescriptor.ERROR;
        }

        return exprType;
    }

    @Override
    public TypeDescriptor visitStatWrite(SafelangParser.StatWriteContext ctx) {
        TypeDescriptor writeType = visit(ctx.write());

        if (writeType.isError()) {
            System.err.println("Error during type checker in a write");
            System.exit(1);
        }

        return writeType;
    }

    @Override
    public TypeDescriptor visitStatIf(SafelangParser.StatIfContext ctx) {
        TypeDescriptor ifType = visit(ctx.if_());
        return ifType;
    }

    @Override
    public TypeDescriptor visitStatType(SafelangParser.StatTypeContext ctx) {
        TypeDescriptor type = visit(ctx.type());
        if (type.isError()) {
            System.err.println("Error during type checker in a type declaration");
            System.exit(1);
        }
        return type;
    }

    @Override
    public TypeDescriptor visitStatFor(SafelangParser.StatForContext ctx) {
        return visit(ctx.for_());
    }

    @Override
    public TypeDescriptor visitStatWhile(SafelangParser.StatWhileContext ctx) {
        return visit(ctx.while_());
    }

    @Override
    public TypeDescriptor visitStatAssert(SafelangParser.StatAssertContext ctx) {
        return visit(ctx.assert_());
    }

    @Override
    public TypeDescriptor visitStatListAdd(SafelangParser.StatListAddContext ctx) {
        return visit(ctx.listadd());
    }

    @Override
    public TypeDescriptor visitStatTry(SafelangParser.StatTryContext ctx) {
        TypeDescriptor tryType = visit(ctx.try_());

        if (tryType.isError()) {
            System.err.println("Error during type checker in try");
            System.exit(1);
        }

        return tryType;
    }

    @Override
    public TypeDescriptor visitStatFail(SafelangParser.StatFailContext ctx) {
        return visit(ctx.fail());
    }

    @Override
    public TypeDescriptor visitCommonStatFail(SafelangParser.CommonStatFailContext ctx) {
        return visit(ctx.fail());
    }

    @Override
    public TypeDescriptor visitCommonStatTry(SafelangParser.CommonStatTryContext ctx) {
        TypeDescriptor tryType = visit(ctx.try_());

        if (tryType.isError()) {
            System.err.println("Error during type checker in try");
            System.exit(1);
        }

        return tryType;
    }

    @Override
    public TypeDescriptor visitCommonStatAssign(SafelangParser.CommonStatAssignContext ctx) {
        TypeDescriptor assignType = visit(ctx.assign());

        if (assignType.isError()) {
            System.err.println("Error during type checker in an assignment");
            System.exit(1);
        }

        return assignType;
    }

    @Override
    public TypeDescriptor visitCommonStatExpr(SafelangParser.CommonStatExprContext ctx) {
        TypeDescriptor exprType = visit(ctx.expr());

        if (exprType == null || exprType.isError()) {
            reportSemanticError(ctx, "Invalid expression statement: '" + ctx.getText() + "'");
            return TypeDescriptor.ERROR;
        }

        return exprType;
    }

    @Override
    public TypeDescriptor visitCommonStatWrite(SafelangParser.CommonStatWriteContext ctx) {
        TypeDescriptor writeType = visit(ctx.write());

        if (writeType.isError()) {
            System.err.println("Error during type checker in a write");
            System.exit(1);
        }

        return writeType;
    } 

    @Override
    public TypeDescriptor visitCommonStatIf(SafelangParser.CommonStatIfContext ctx) {
        TypeDescriptor ifType = visit(ctx.if_());
        return ifType;
    }

    @Override
    public TypeDescriptor visitCommonStatFor(SafelangParser.CommonStatForContext ctx) {
        return visit(ctx.for_());
    }

    @Override
    public TypeDescriptor visitCommonStatWhile(SafelangParser.CommonStatWhileContext ctx) {
        return visit(ctx.while_());
    }

    @Override
    public TypeDescriptor visitCommonStatAssert(SafelangParser.CommonStatAssertContext ctx) {
        return visit(ctx.assert_());
    }

    @Override
    public TypeDescriptor visitCommonStatListAdd(SafelangParser.CommonStatListAddContext ctx) {
        return visit(ctx.listadd());
    }

    // ─── Write ───────────────────────────────────────────────────────────────

    @Override
    public TypeDescriptor visitWriteExpr(SafelangParser.WriteExprContext ctx) {
        TypeDescriptor exprType = visit(ctx.expr());

        if (exprType == null || exprType.isError()) {
            reportSemanticError(ctx, "Invalid expression in write");
            return TypeDescriptor.ERROR;
        }

        if (exprType.isString()) {
            return TypeDescriptor.STRING;
        }

        reportSemanticError(ctx, "write expects a string");
        return TypeDescriptor.ERROR;
    }

    @Override
    public TypeDescriptor visitWriteLnExpr(SafelangParser.WriteLnExprContext ctx) {
        if (ctx.expr() == null) {
            return TypeDescriptor.STRING;
        }

        TypeDescriptor exprType = visit(ctx.expr());

        if (exprType == null || exprType.isError()) {
            reportSemanticError(ctx, "Invalid expression in writeln");
            return TypeDescriptor.ERROR;
        }

        if (exprType.isString()) {
            return TypeDescriptor.STRING;
        }

        reportSemanticError(ctx, "writeln expects a string");
        return TypeDescriptor.ERROR;
    }

    // ─── Assign ──────────────────────────────────────────────────────────────

    /**
     * ID ':=' expr ':' type   — declaration + assignment with explicit type annotation.
     */
    @Override
    public TypeDescriptor visitAssignValType(SafelangParser.AssignValTypeContext ctx) {
        String idName      = ctx.ID().get(0).getText();
        TypeDescriptor exprType = visit(ctx.expr());
        if (exprType.isError()) {
            reportSemanticError(ctx, "Expression error in assignment of '" + idName + "'");
            return TypeDescriptor.ERROR;
        }

        String typeTargetStr = ctx.getChild(4).getText();
        TypeDescriptor stored = checkAssignCompatibility(exprType, typeTargetStr, ctx);
        if (stored.isError()) return TypeDescriptor.ERROR;

        symbolTable.declare(idName, stored);
        return stored;
    }

    /**
     * ID ':=' expr   — re-assignment to an already-declared variable.
     */
    @Override
    public TypeDescriptor visitAssignVal(SafelangParser.AssignValContext ctx) {
        String idName = ctx.ID().getText();
        TypeDescriptor targetType = symbolTable.lookup(idName);

        if (targetType.isError()) {
            reportSemanticError(ctx, "Variable '" + idName + "' has not been declared");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor exprType = visit(ctx.expr());

        if (exprType.isError()) {
            reportSemanticError(ctx, "Expression error in assignment of '" + idName + "'");
            return TypeDescriptor.ERROR;
        }

        if (targetType.isList || exprType.isList) {
            if (!targetType.isList || !exprType.isList) {
                reportSemanticError(ctx, "Cannot assign list and non-list types");
                return TypeDescriptor.ERROR;
            }

            TypeDescriptor targetElementType = makeElementType(targetType);
            TypeDescriptor exprElementType = makeElementType(exprType);

            String targetTypeStr = getTypeNameFromDescriptor(targetElementType);

            TypeDescriptor checkedElementType =
                checkAssignCompatibility(exprElementType, targetTypeStr, ctx);

            if (checkedElementType.isError())
                return TypeDescriptor.ERROR;

            symbolTable.assign(idName, cloneType(targetType));
            return targetType;
        }

        String typeTargetStr = getTypeNameFromDescriptor(targetType);

        TypeDescriptor stored = checkAssignCompatibility(exprType, typeTargetStr, ctx);

        if (stored.isError())
            return TypeDescriptor.ERROR;

        symbolTable.assign(idName, stored);
        return stored;
    }

    /**
     * ID ':=?' expr   — try-assignment to an already-declared variable.
     */
    @Override
    public TypeDescriptor visitAssignTryVal(SafelangParser.AssignTryValContext ctx) {
        String idName = ctx.ID().getText();
        TypeDescriptor targetType = symbolTable.lookup(idName);

        if (targetType.isError()) {
            reportSemanticError(ctx, "Variable '" + idName + "' has not been declared");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor exprType = visit(ctx.expr());
        if (exprType.isError()) return targetType; // try-assign: swallow expression errors

        String typeTargetStr = targetType.hasDimension()
            ? targetType.dimension
            : targetType.base.toString().toLowerCase();

        TypeDescriptor stored = checkAssignCompatibility(exprType, typeTargetStr, ctx);
        if (stored.isError()) return TypeDescriptor.ERROR;

        if ((targetType.isList && !exprType.isList) || (!targetType.isList && exprType.isList)) {
            reportSemanticError(ctx, "Variable '" + idName + "' is not a list");
            return TypeDescriptor.ERROR;
        }

        symbolTable.assign(idName, stored);
        return stored;
    }

    /**
     * ID ':=?' expr ':' type   — try-declaration + assignment with explicit type annotation.
     */
    @Override
    public TypeDescriptor visitAssignTryValType(SafelangParser.AssignTryValTypeContext ctx) {
        String idName = ctx.ID().get(0).getText();
        TypeDescriptor exprType = visit(ctx.expr());

        String typeTargetStr = ctx.getChild(4).getText();
        if (exprType.isError()) {
            // try-assign: declare with the annotated type, swallow expression error
            TypeDescriptor annotated = resolveAnnotatedType(typeTargetStr);
            symbolTable.declare(idName, annotated);
            return annotated;
        }

        TypeDescriptor stored = checkAssignCompatibility(exprType, typeTargetStr, ctx);
        if (stored.isError()) return TypeDescriptor.ERROR;

        symbolTable.declare(idName, stored);
        return stored;
    }

    /**
     * ID ':' type   — declaration without initialisation.
     */
    @Override
    public TypeDescriptor visitAssignType(SafelangParser.AssignTypeContext ctx) {
        String idName = ctx.ID().get(0).getText();

        if (symbolTable.isDeclaredInCurrentScope(idName)) {
            reportSemanticError(ctx, "Variable '" + idName + "' is already declared in this scope");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor retValue;
        if (ctx.TYPEINT() != null)
            retValue = TypeDescriptor.INTEGER;
        else if (ctx.TYPEREAL() != null)
            retValue = TypeDescriptor.REAL;
        else if (ctx.TYPESTR() != null)
            retValue = TypeDescriptor.STRING;
        else if (ctx.TYPEBOOL() != null)
            retValue = TypeDescriptor.BOOL;
        else
            retValue = typeSystem.getBaseType(ctx.ID().get(1).getText());

        if (retValue == null || retValue.isError()) {
            reportSemanticError(ctx, "Unknown type '" + ctx.ID().get(1).getText() + "'");
            return TypeDescriptor.ERROR;
        }

        symbolTable.declare(idName, retValue);
        return retValue;
    }

    /**
     * ID ':' 'list' '[' type ']' 
     */
    @Override
    public TypeDescriptor visitAssignListType(SafelangParser.AssignListTypeContext ctx) {
        String idName = ctx.ID().get(0).getText();

        if (symbolTable.isDeclaredInCurrentScope(idName)) {
            reportSemanticError(ctx, "Variable '" + idName + "' is already declared in this scope");
            return TypeDescriptor.ERROR;
        }

        String elementTypeStr = ctx.getChild(4).getText();

        TypeDescriptor elementType = resolveAnnotatedType(elementTypeStr);

        if (elementType == null || elementType.isError()) {
            reportSemanticError(ctx, "Unkown list element type");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor listType = makeListType(elementType);

        symbolTable.declare(idName, listType);
        return listType;
    }

    /**
     * ID ':=' ('new' 'list' '[' type ']' | ID) ':' 'list' '[' type ']'
     */
    @Override
    public TypeDescriptor visitAssignListValType(SafelangParser.AssignListValTypeContext ctx) {
        String idName = ctx.ID().get(0).getText();

        if (symbolTable.isDeclaredInCurrentScope(idName)) {
            reportSemanticError(ctx, "Variable '" + idName + "' is already declared in this scope");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor sourceListType;
        String targetElementTypeStr = ctx.getChild(4).getText();

        boolean isNewList = ctx.getChild(2).getText().equals("new");

        if (isNewList) {
            // nums := new list[integer] : list[integer]
            String sourceElementTypeStr = ctx.getChild(5).getText();
            targetElementTypeStr = ctx.getChild(10).getText();

            TypeDescriptor sourceElementType = resolveAnnotatedType(sourceElementTypeStr);

            if (sourceElementType == null || sourceElementType.isError()) {
                reportSemanticError(ctx, "Unknown list element type '" + sourceElementTypeStr + "'");
                return TypeDescriptor.ERROR;
            }

            sourceListType = makeListType(sourceElementType);
        } else {
            // nums := otherNums : list[integer]
            String sourceIdName = ctx.getChild(2).getText();
            targetElementTypeStr = ctx.getChild(6).getText();

            sourceListType = symbolTable.lookup(sourceIdName);

            if (sourceListType.isError()) {
                reportSemanticError(ctx, "Variable '" + sourceIdName + "' has not been declared");
                return TypeDescriptor.ERROR;
            }

            if (!sourceListType.isList) {
                reportSemanticError(ctx, "Variable '" + sourceIdName + "' is not a list");
                return TypeDescriptor.ERROR;
            }
        }

        TypeDescriptor targetElementType = resolveAnnotatedType(targetElementTypeStr);

        if (targetElementType == null || targetElementType.isError()) {
            reportSemanticError(ctx, "Unknown list element type '" + targetElementTypeStr + "'");
            return TypeDescriptor.ERROR;
        }

        if (!sourceListType.sameDimension(targetElementType, typeSystem)) {
            reportSemanticError(ctx, 
                "Cannot assign list with dimension [" + sourceListType.dimension +
                "] to list with dimension [" + targetElementType.dimension + "]"
            );
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor finalListType = makeListType(targetElementType);

        symbolTable.declare(idName, finalListType);
        return finalListType;
    }

    /**
     * ID ':=' ('new' 'list' '[' type ']' | ID)
     */
    @Override
    public TypeDescriptor visitAssignListVal(SafelangParser.AssignListValContext ctx) {
        String idName = ctx.ID().get(0).getText();

        TypeDescriptor targetType = symbolTable.lookup(idName);

        if (targetType.isError()) {
            reportSemanticError(ctx, "Variable '" + idName + "' has not been declared");
            return TypeDescriptor.ERROR;
        }

        if (!targetType.isList) {
            reportSemanticError(ctx, "Variable '" + idName + "' is not a list");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor sourceListType;

        boolean isNewList = ctx.getChild(2).getText().equals("new");

        if (isNewList) {
            // nums := new list[integer]
            String sourceElementTypeStr = ctx.getChild(5).getText();

            TypeDescriptor sourceElementType = resolveAnnotatedType(sourceElementTypeStr);

            if (sourceElementType == null || sourceElementType.isError()) {
                reportSemanticError(ctx, "Unkown list element type '" + sourceElementTypeStr + "'");
                return TypeDescriptor.ERROR;
            }

            sourceListType = makeListType(sourceElementType);
        } else {
            // nums := otherNums
            String sourceIdName = ctx.getChild(2).getText();

            sourceListType = symbolTable.lookup(sourceIdName);

            if (sourceListType.isError()) {
                reportSemanticError(ctx, "Variable '" + sourceIdName + "' has not been declared");
                return TypeDescriptor.ERROR;
            }

            if (!sourceListType.isList) {
                reportSemanticError(ctx, "Variable '" + sourceIdName + "' is not a list");
                return TypeDescriptor.ERROR;
            }
        }

        if (!sourceListType.base.equals(targetType.base)) {
            reportSemanticError(ctx,
                "Cannot assign list[" + sourceListType.base + "] to list[" + targetType.base + "]");
            return TypeDescriptor.ERROR;
        }

        if (!sourceListType.sameDimension(targetType, typeSystem)) {
            reportSemanticError(ctx,
                "Cannot assign list with dimension [" + sourceListType.dimension +
                "] to list with dimension [" + targetType.dimension + "]"
            );
            return TypeDescriptor.ERROR;
        }

        symbolTable.assign(idName, sourceListType);
        return sourceListType;
    }

    // ─── Type (type/unit declarations) ───────────────────────────────────────

    @Override
    public TypeDescriptor visitTypeUnit(SafelangParser.TypeUnitContext ctx) {
        String dimensionName = ctx.ID(0).getText();
        if (typeSystem.dimensionExists(dimensionName)) {
            reportSemanticError(ctx, "Dimension '" + dimensionName + "' already declared");
            return TypeDescriptor.ERROR;
        }
        String unitName = ctx.ID(1).getText();
        if (typeSystem.unitExists(unitName)) {
            reportSemanticError(ctx, "Unit '" + unitName + "' already declared");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor.BaseType base = (ctx.TYPEINT() != null)
            ? TypeDescriptor.BaseType.INTEGER : TypeDescriptor.BaseType.REAL;
        TypeDescriptor baseType = new TypeDescriptor(base, dimensionName, null);

        String[] exponents = { dimensionName };
        typeSystem.insertDimensionType(dimensionName, unitName, exponents, baseType);

        return baseType;
    }

    @Override
    public TypeDescriptor visitTypeUnitSuffix(SafelangParser.TypeUnitSuffixContext ctx) {
        String dimensionName = ctx.ID(0).getText();
        if (typeSystem.dimensionExists(dimensionName)) {
            reportSemanticError(ctx, "Dimension '" + dimensionName + "' already declared");
            return TypeDescriptor.ERROR;
        }
        String unitName = ctx.ID(1).getText();
        if (typeSystem.unitExists(unitName)) {
            reportSemanticError(ctx, "Unit '" + unitName + "' already declared");
            return TypeDescriptor.ERROR;
        }
        String suffixName = ctx.ID(2).getText();
        if (typeSystem.suffixExists(suffixName)) {
            reportSemanticError(ctx, "Suffix '" + suffixName + "' already in use");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor.BaseType base = (ctx.TYPEINT() != null)
            ? TypeDescriptor.BaseType.INTEGER : TypeDescriptor.BaseType.REAL;
        TypeDescriptor baseType = new TypeDescriptor(base, dimensionName, null);

        String[] exponents = { dimensionName };
        typeSystem.insertDimensionType(dimensionName, unitName, exponents, suffixName, baseType);

        return baseType;
    }

    @Override
    public TypeDescriptor visitTypeDependent(SafelangParser.TypeDependentContext ctx) {
        String dimensionName = ctx.ID().getText();
        if (typeSystem.dimensionExists(dimensionName)) {
            reportSemanticError(ctx, "Dimension '" + dimensionName + "' already declared");
            return TypeDescriptor.ERROR;
        }

        // Evaluate the RHS dimension expression
        TypeDescriptor rhsType = visit(ctx.type_def_expr());
        if (rhsType == null || rhsType.isError()) {
            reportSemanticError(ctx, "Invalid type expression for '" + dimensionName + "'");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor.BaseType base = (ctx.TYPEINT() != null)
            ? TypeDescriptor.BaseType.INTEGER : TypeDescriptor.BaseType.REAL;

        // Derived dimension always gets REAL when "/" is involved (handled in visitTypeDefExpr)
        if (rhsType.isReal()) base = TypeDescriptor.BaseType.REAL;

        TypeDescriptor baseType = new TypeDescriptor(base, dimensionName, null);

        // Build the unit name from the RHS dimension's base unit(s)
        String unitName = buildUnitNameFromDimExpr(ctx.type_def_expr());
        String[] exponents = buildExponentArray(ctx.type_def_expr());

        typeSystem.insertDimensionType(dimensionName, unitName, exponents, baseType);
        return baseType;
    }

    @Override
    public TypeDescriptor visitTypeDependentUnit(SafelangParser.TypeDependentUnitContext ctx) {
        String dimensionName = ctx.ID(0).getText();
        if (typeSystem.dimensionExists(dimensionName)) {
            reportSemanticError(ctx, "Dimension '" + dimensionName + "' already declared");
            return TypeDescriptor.ERROR;
        }
        String unitName = ctx.ID(1).getText();
        if (typeSystem.unitExists(unitName)) {
            reportSemanticError(ctx, "Unit '" + unitName + "' already declared");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor rhsType = visit(ctx.type_def_expr());
        if (rhsType == null || rhsType.isError()) {
            reportSemanticError(ctx, "Invalid type expression for '" + dimensionName + "'");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor.BaseType base = (ctx.TYPEINT() != null)
            ? TypeDescriptor.BaseType.INTEGER : TypeDescriptor.BaseType.REAL;
        if (rhsType.isReal()) base = TypeDescriptor.BaseType.REAL;

        TypeDescriptor baseType = new TypeDescriptor(base, dimensionName, null);
        String[] exponents = buildExponentArray(ctx.type_def_expr());
        typeSystem.insertDimensionType(dimensionName, unitName, exponents, baseType);

        return baseType;
    }

    @Override
    public TypeDescriptor visitTypeDependentUnitSuffix(SafelangParser.TypeDependentUnitSuffixContext ctx) {
        String dimensionName = ctx.ID(0).getText();
        if (typeSystem.dimensionExists(dimensionName)) {
            reportSemanticError(ctx, "Dimension '" + dimensionName + "' already declared");
            return TypeDescriptor.ERROR;
        }
        String unitName = ctx.ID(1).getText();
        if (typeSystem.unitExists(unitName)) {
            reportSemanticError(ctx, "Unit '" + unitName + "' already declared");
            return TypeDescriptor.ERROR;
        }
        String suffixName = ctx.ID(2).getText();
        if (typeSystem.suffixExists(suffixName)) {
            reportSemanticError(ctx, "Suffix '" + suffixName + "' already in use");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor rhsType = visit(ctx.type_def_expr());
        if (rhsType == null || rhsType.isError()) {
            reportSemanticError(ctx, "Invalid type expression for '" + dimensionName + "'");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor.BaseType base = (ctx.TYPEINT() != null)
            ? TypeDescriptor.BaseType.INTEGER : TypeDescriptor.BaseType.REAL;
        if (rhsType.isReal()) base = TypeDescriptor.BaseType.REAL;

        TypeDescriptor baseType = new TypeDescriptor(base, dimensionName, null);
        String[] exponents = buildExponentArray(ctx.type_def_expr());
        typeSystem.insertDimensionType(dimensionName, unitName, exponents, suffixName, baseType);

        return baseType;
    }

    @Override
    public TypeDescriptor visitTypeByteRange(SafelangParser.TypeByteRangeContext ctx) {
        String typeName = ctx.ID().getText();

        if (typeSystem.typeGenExists(typeName)) {
            reportSemanticError(ctx, "Type '" + typeName + "' already declared");
            return TypeDescriptor.ERROR;
        }

        int bitRange;

        try {
            bitRange = Integer.parseInt(ctx.IntegerLiteral().getText());
        } catch (NumberFormatException e) {
            reportSemanticError(ctx, "Invalid bit range for type '" + typeName + "'");
            return TypeDescriptor.ERROR;
        }

        if (bitRange <= 0) {
            reportSemanticError(ctx, "Bit range must be greater than 0");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor.BaseType base =
            (ctx.TYPEINT() != null)
                ? TypeDescriptor.BaseType.INTEGER
                : TypeDescriptor.BaseType.REAL;

        TypeDescriptor rangedType = new TypeDescriptor(base, bitRange);

        typeSystem.insertTypeRange(typeName, bitRange, rangedType);

        return rangedType;
    }

    // ─── if and else ──────────────────────────────────────────────────────────

    @Override
    public TypeDescriptor visitIfElse(SafelangParser.IfElseContext ctx) {
        TypeDescriptor boolType = visit(ctx.booleans());

        if (!boolType.isBool()) {
            reportSemanticError(ctx, "The type of the expression in if must be boolean.");
            return TypeDescriptor.ERROR;
        }

        symbolTable.pushScope();

        visitChildren(ctx);

        symbolTable.popScope();

        return TypeDescriptor.BOOL;
    }

    @Override
    public TypeDescriptor visitIfEnd(SafelangParser.IfEndContext ctx) {
        TypeDescriptor boolType = visit(ctx.booleans());

        if (!boolType.isBool()) {
            reportSemanticError(ctx, "The type of the expression in if must be boolean.");
            return TypeDescriptor.ERROR;
        }

        symbolTable.pushScope();

        visitChildren(ctx);

        symbolTable.popScope();

        return TypeDescriptor.BOOL;
    }

    @Override
    public TypeDescriptor visitElseNorm(SafelangParser.ElseNormContext ctx) {
        symbolTable.pushScope();
        TypeDescriptor resType = visitChildren(ctx);
        symbolTable.popScope();

        return resType;
    }

    @Override
    public TypeDescriptor visitElseIf(SafelangParser.ElseIfContext ctx) {
        TypeDescriptor boolType = visit(ctx.booleans());

        if (!boolType.isBool()) {
            reportSemanticError(ctx, "The type of the expression in elseif must be boolean.");
            return TypeDescriptor.ERROR;
        }

        symbolTable.pushScope();

        visitChildren(ctx);

        symbolTable.popScope();

        return TypeDescriptor.BOOL;
    }

    // ─── Assert ───────────────────────────────────────────────────────────────

    @Override
    public TypeDescriptor visitAssert(SafelangParser.AssertContext ctx) {
        TypeDescriptor boolType = visit(ctx.booleans());

        if (!boolType.isBool()) {
            reportSemanticError(ctx, "Assert expression must be boolean");
            return TypeDescriptor.ERROR;
        }

        return TypeDescriptor.BOOL;
    }

    // ─── For / While / Until ──────────────────────────────────────────────────

    private void checkLoopBound(TypeDescriptor type,
                                org.antlr.v4.runtime.ParserRuleContext ctx) {
        if (type == null || type.isError()) {
            reportSemanticError(ctx, "Invalid loop bound");
            return;
        }

        if (!type.isInteger() || type.hasDimension() || type.isList || type.hasBitRange()) {
            reportSemanticError(ctx, "Loop bounds must be plain integers");
        }
    }

    @Override
    public TypeDescriptor visitForAssign(SafelangParser.ForAssignContext ctx) {
        TypeDescriptor startType = visit(ctx.number(0));
        TypeDescriptor endType = visit(ctx.number(1));

        checkLoopBound(startType, ctx);
        checkLoopBound(endType, ctx);

        String loopVarName = ctx.ID().getText();

        symbolTable.pushScope();

        if (symbolTable.isDeclaredInCurrentScope(loopVarName)) {
            reportSemanticError(ctx, "Variable '" + loopVarName + "' is already declared in this scope");
            return TypeDescriptor.ERROR;
        }

        symbolTable.declare(loopVarName, TypeDescriptor.INTEGER);

        for (SafelangParser.CommonStatContext statCtx : ctx.commonStat()) {
            TypeDescriptor statType = visit(statCtx);
            if (statType != null && statType.isError()) {
                symbolTable.popScope();
                return TypeDescriptor.ERROR;
            }
        }

        symbolTable.popScope();
        return TypeDescriptor.INTEGER;
    }

    @Override
    public TypeDescriptor visitForNorm(SafelangParser.ForNormContext ctx) {
        TypeDescriptor startType = visit(ctx.number(0));
        TypeDescriptor endType = visit(ctx.number(1));

        checkLoopBound(startType, ctx);
        checkLoopBound(endType, ctx);

        symbolTable.pushScope();

        for (SafelangParser.CommonStatContext statCtx : ctx.commonStat()) {
            TypeDescriptor statType = visit(statCtx);
            if (statType != null && statType.isError()) {
                symbolTable.popScope();
                return TypeDescriptor.ERROR;
            }
        }

        symbolTable.popScope();
        return TypeDescriptor.INTEGER;
    }

    @Override
    public TypeDescriptor visitWhileNorm(SafelangParser.WhileNormContext ctx) {
        TypeDescriptor boolType = visit(ctx.booleans());

        if (!boolType.isBool()) {
            reportSemanticError(ctx, "The type of the expression in while must be boolean");
            return TypeDescriptor.ERROR;
        }

        symbolTable.pushScope();

        for (SafelangParser.CommonStatContext statCtx : ctx.commonStat()) {
            TypeDescriptor statType = visit(statCtx);
            if (statType != null && statType.isError()) {
                symbolTable.popScope();
                return TypeDescriptor.ERROR;
            }
        }

        symbolTable.popScope();
        return TypeDescriptor.BOOL;
    }

    @Override
    public TypeDescriptor visitWhileUntil(SafelangParser.WhileUntilContext ctx) {
        TypeDescriptor boolType = visit(ctx.booleans());

        if (!boolType.isBool()) {
            reportSemanticError(ctx, "The type of the expression in until must be boolean");
            return TypeDescriptor.ERROR;
        }

        symbolTable.pushScope();

        for (SafelangParser.CommonStatContext statCtx : ctx.commonStat()) {
            TypeDescriptor statType = visit(statCtx);
            if (statType != null && statType.isError()) {
                symbolTable.popScope();
                return TypeDescriptor.ERROR;
            }
        }

        symbolTable.popScope();
        return TypeDescriptor.BOOL;
    }

    // ─── type_def_expr visitors ───────────────────────────────────────────────

    /**
     * type_def_expr op=( '*' | '/' ) type_def_expr
     * Returns a synthetic TypeDescriptor carrying the resolved (or structural) dimension.
     */
    @Override
    public TypeDescriptor visitTypeDefExpr(SafelangParser.TypeDefExprContext ctx) {
        TypeDescriptor leftType  = visit(ctx.type_def_expr(0));
        TypeDescriptor rightType = visit(ctx.type_def_expr(1));

        if (leftType == null || leftType.isError())  return TypeDescriptor.ERROR;
        if (rightType == null || rightType.isError()) return TypeDescriptor.ERROR;

        String leftDim  = leftType.dimension;
        String rightDim = rightType.dimension;
        String op       = ctx.op.getText();

        TypeDescriptor.BaseType base = op.equals("/")
            ? TypeDescriptor.BaseType.REAL   // division always gives real
            : promoteBase(leftType, rightType);

        String[] exponents = { leftDim, op, rightDim };
        HashMap<String, Integer> expMap = typeSystem.generateExponents(exponents);
        String resolvedDim = typeSystem.resolveSignature(expMap);

        // If no registered dimension matches, use structural name (professor's answer 3)
        if (resolvedDim == null) resolvedDim = leftDim + op + rightDim;

        return new TypeDescriptor(base, resolvedDim, null);
    }

    /**
     * ID   — references an already-declared dimension.
     */
    @Override
    public TypeDescriptor visitTypeDefID(SafelangParser.TypeDefIDContext ctx) {
        String dimName = ctx.ID().getText();
        if (!typeSystem.dimensionExists(dimName)) {
            reportSemanticError(ctx, "Unknown dimension '" + dimName + "' in type expression");
            return TypeDescriptor.ERROR;
        }
        return typeSystem.getBaseType(dimName);
    }

    // ─── Unit declarations ────────────────────────────────────────────────────

    @Override
    public TypeDescriptor visitDimensionUnit(SafelangParser.DimensionUnitContext ctx) {
        String dimensionName = ctx.ID(0).getText();
        if (!typeSystem.dimensionExists(dimensionName)) {
            reportSemanticError(ctx, "Dimension '" + dimensionName + "' not declared");
            return TypeDescriptor.ERROR;
        }
        String unitName = ctx.ID(1).getText();
        if (typeSystem.unitExists(unitName)) {
            reportSemanticError(ctx, "Unit '" + unitName + "' already declared");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor numberType = visit(ctx.number());
        if (!dimensionName.equals(numberType.dimension)) {
            reportSemanticError(ctx,
                "Unit conversion value must be in dimension '" + dimensionName + "'");
            return TypeDescriptor.ERROR;
        }

        // The conversion value is the numeric literal parsed from `number`.
        // We store 1 as a placeholder; the code-generator will use the actual value.
        typeSystem.insertUnitType(dimensionName, unitName, null);

        return new TypeDescriptor(TypeDescriptor.BaseType.UNIT, dimensionName, unitName);
    }

    @Override
    public TypeDescriptor visitDimensionUnitSuffix(SafelangParser.DimensionUnitSuffixContext ctx) {
        String dimensionName = ctx.ID(0).getText();
        if (!typeSystem.dimensionExists(dimensionName)) {
            reportSemanticError(ctx, "Dimension '" + dimensionName + "' not declared");
            return TypeDescriptor.ERROR;
        }
        String unitName = ctx.ID(1).getText();
        if (typeSystem.unitExists(unitName)) {
            reportSemanticError(ctx, "Unit '" + unitName + "' already declared");
            return TypeDescriptor.ERROR;
        }
        String suffixName = ctx.ID(2).getText();
        if (typeSystem.suffixExists(suffixName)) {
            reportSemanticError(ctx, "Suffix '" + suffixName + "' already in use");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor numberType = visit(ctx.number());
        if (!dimensionName.equals(numberType.dimension)) {
            reportSemanticError(ctx,
                "Unit conversion value must be in dimension '" + dimensionName + "'");
            return TypeDescriptor.ERROR;
        }

        typeSystem.insertUnitType(dimensionName, unitName, null, suffixName);

        return new TypeDescriptor(TypeDescriptor.BaseType.UNIT, dimensionName, unitName);
    }

    // ─── Expr ────────────────────────────────────────────────────────────────

    @Override
    public TypeDescriptor visitStringConcat(SafelangParser.StringConcatContext ctx) {
        TypeDescriptor leftType  = visit(ctx.expr(0));
        TypeDescriptor rightType = visit(ctx.expr(1));

        if (leftType == null || rightType == null) {
            reportSemanticError(ctx, "Invalid expression in string concatenation");
            return TypeDescriptor.ERROR;
        }

        if (leftType.isError() || rightType.isError()) {
            return TypeDescriptor.ERROR;
        }

        if (leftType.isString() && rightType.isString()) {
            return TypeDescriptor.STRING;
        }

        reportSemanticError(ctx,
            "String concatenation requires strings, got [" + leftType + "] and [" + rightType + "]");

        return TypeDescriptor.ERROR;
    }

    @Override
    public TypeDescriptor visitExprFormatCommand(SafelangParser.ExprFormatCommandContext ctx) {
        TypeDescriptor exprType = visit(ctx.expr());

        if (exprType == null || exprType.isError()) {
            reportSemanticError(ctx, "Invalid expression inside format()");
            return TypeDescriptor.ERROR;
        }

        if (!exprType.isString()) {
            reportSemanticError(ctx, "First argument of format() must be a string");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor numType = visit(ctx.number());

        if (numType == null || numType.isError()) {
            reportSemanticError(ctx, "Invalid width argument inside format()");
            return TypeDescriptor.ERROR;
        }

        if (!numType.isInteger() || numType.hasDimension() || numType.isList || numType.hasBitRange()) {
            reportSemanticError(ctx, "Second argument of format() must be a plain integer");
            return TypeDescriptor.ERROR;
        }

        return TypeDescriptor.STRING;
    }

    @Override
    public TypeDescriptor visitExprFormatCommandPlacement(SafelangParser.ExprFormatCommandPlacementContext ctx) {
        TypeDescriptor exprType = visit(ctx.expr());

        if (exprType == null || exprType.isError()) {
            reportSemanticError(ctx, "Invalid expression inside format()");
            return TypeDescriptor.ERROR;
        }

        if (!exprType.isString()) {
            reportSemanticError(ctx, "First argument of format() must be a string");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor numType = visit(ctx.number());

        if (numType == null || numType.isError()) {
            reportSemanticError(ctx, "Invalid width argument inside format()");
            return TypeDescriptor.ERROR;
        }

        if (!numType.isInteger() || numType.hasDimension() || numType.isList || numType.hasBitRange()) {
            reportSemanticError(ctx, "Second argument of format() must be a plain integer");
            return TypeDescriptor.ERROR;
        }

        // Placement is already restricted by the grammar to "left", "center", or "right".
        return TypeDescriptor.STRING;
    }

    @Override
    public TypeDescriptor visitExprID(SafelangParser.ExprIDContext ctx) {
        String name = ctx.ID().getText();

        TypeDescriptor type = symbolTable.lookup(name);

        if (type.isError()) {
            reportSemanticError(ctx, "Variable '" + name + "' has not been declared");
            return TypeDescriptor.ERROR;
        }

        return type;
    }

    @Override
    public TypeDescriptor visitExprNumber(SafelangParser.ExprNumberContext ctx) {
        return visit(ctx.number());
    }

    @Override
    public TypeDescriptor visitExprString(SafelangParser.ExprStringContext ctx) {
        return visit(ctx.string());
    }

    @Override
    public TypeDescriptor visitExprBoolean(SafelangParser.ExprBooleanContext ctx) {
        return visit(ctx.booleans());
    }

    // ─── String ──────────────────────────────────────────────────────────────

    @Override
    public TypeDescriptor visitStringLiteral(SafelangParser.StringLiteralContext ctx) {
        return TypeDescriptor.STRING;
    }

    @Override
    public TypeDescriptor visitConvertToString(SafelangParser.ConvertToStringContext ctx) {
        TypeDescriptor exprType = visit(ctx.expr());

        if (exprType == null || exprType.isError()) {
            reportSemanticError(ctx, "Cannot convert invalid expression to string");
            return TypeDescriptor.ERROR;
        }

        return TypeDescriptor.STRING;
    }

    @Override
    public TypeDescriptor visitReadCmd(SafelangParser.ReadCmdContext ctx) {
        return visit(ctx.string());
    }

    @Override
    public TypeDescriptor visitStringID(SafelangParser.StringIDContext ctx) {
        TypeDescriptor type = symbolTable.lookup(ctx.ID().getText());
        if (type.isString())
            return type;
        else
            return TypeDescriptor.ERROR;
    }

    // ─── Number ──────────────────────────────────────────────────────────────

    @Override
    public TypeDescriptor visitNumberParent(SafelangParser.NumberParentContext ctx) {
        TypeDescriptor exprType = visit(ctx.expr());
        if (exprType.isNumeric())
            return exprType;
        return TypeDescriptor.ERROR;
    }

    /**
     * number ID  — suffix literal, e.g. "5m" where "m" is a registered suffix.
     *
     * Looks up the unit by suffix and returns the matching dimensional type.
     */
    @Override
    public TypeDescriptor visitNumberSuffix(SafelangParser.NumberSuffixContext ctx) {
        TypeDescriptor numType = visit(ctx.number());
        if (numType.isError()) return TypeDescriptor.ERROR;

        String suffix = ctx.ID().getText();

        // First check if ID is a direct unit name
        if (typeSystem.unitExists(suffix)) {
            TypeDescriptor unitType = typeSystem.getBaseType(suffix);
            // Apply base-type promotion (integer is-a real)
            TypeDescriptor.BaseType base = promoteBase(numType, unitType);
            return new TypeDescriptor(base, unitType.dimension, suffix);
        }

        // Then check if ID is a suffix of a known unit
        String dimForSuffix = typeSystem.getDimensionForSuffix(suffix);
        if (dimForSuffix != null) {
            TypeDescriptor dimType = typeSystem.getBaseType(dimForSuffix);
            TypeDescriptor.BaseType base = promoteBase(numType, dimType);
            return new TypeDescriptor(base, dimForSuffix, suffix);
        }

        reportSemanticError(ctx, "Unknown unit or suffix '" + suffix + "'");
        return TypeDescriptor.ERROR;
    }

    @Override
    public TypeDescriptor visitNumberMult(SafelangParser.NumberMultContext ctx) {
        TypeDescriptor leftType  = visit(ctx.number(0));
        if (leftType.isError()) return leftType;
        TypeDescriptor rightType = visit(ctx.number(1));
        if (rightType.isError()) return rightType;

        String leftDim  = leftType.dimension;
        String rightDim = rightType.dimension;
        TypeDescriptor.BaseType base = promoteBase(leftType, rightType);

        if (leftDim == null && rightDim == null)
            return new TypeDescriptor(base);
        else if (leftDim == null)
            return new TypeDescriptor(base, rightDim, null);
        else if (rightDim == null)
            return new TypeDescriptor(base, leftDim, null);
        else {
            // Both dimensional — compute product signature
            String[] expStr = { leftDim, "*", rightDim };
            HashMap<String, Integer> exponents = typeSystem.generateExponents(expStr);
            String resolvedDim = typeSystem.resolveSignature(exponents);

            // Per professor: accept even if no named type exists; use structural name
            if (resolvedDim == null) resolvedDim = leftDim + "*" + rightDim;

            return new TypeDescriptor(base, resolvedDim, null);
        }
    }

    @Override
    public TypeDescriptor visitNumberDivReal(SafelangParser.NumberDivRealContext ctx) {
        TypeDescriptor leftType  = visit(ctx.number(0));
        if (leftType.isError()) return leftType;
        TypeDescriptor rightType = visit(ctx.number(1));
        if (rightType.isError()) return rightType;

        String leftDim  = leftType.dimension;
        String rightDim = rightType.dimension;
        TypeDescriptor.BaseType base = TypeDescriptor.BaseType.REAL; // division always real

        if (leftDim == null && rightDim == null)
            return TypeDescriptor.REAL;
        else if (leftDim == null) {
            // adimensional / dimensional  →  dimension stays (inverted structural)
            return new TypeDescriptor(base, "1/" + rightDim, null);
        } else if (rightDim == null) {
            return new TypeDescriptor(base, leftDim, null);
        } else if (leftDim.equals(rightDim)) {
            return TypeDescriptor.REAL; // same dimension cancels out → adimensional
        } else {
            String[] expStr = { leftDim, "/", rightDim };
            HashMap<String, Integer> exponents = typeSystem.generateExponents(expStr);
            String resolvedDim = typeSystem.resolveSignature(exponents);

            if (resolvedDim == null) resolvedDim = leftDim + "/" + rightDim;

            return new TypeDescriptor(base, resolvedDim, null);
        }
    }

    @Override
    public TypeDescriptor visitNumberQuotModInt(SafelangParser.NumberQuotModIntContext ctx) {
        TypeDescriptor leftType  = visit(ctx.number(0));
        if (leftType.isError()) return leftType;
        TypeDescriptor rightType = visit(ctx.number(1));
        if (rightType.isError()) return rightType;

        if (!leftType.isInteger() || !rightType.isInteger()) {
            reportSemanticError(ctx, "Operators '//' and '\\\\' require integer operands");
            return TypeDescriptor.ERROR;
        }

        String leftDim  = leftType.dimension;
        String rightDim = rightType.dimension;
        String op = ctx.op.getText();

        if (op.equals("//")) {
            // Integer quotient — dimension behaves like real division
            if (leftDim == null && rightDim == null) return TypeDescriptor.INTEGER;
            if (leftDim == null) return new TypeDescriptor(TypeDescriptor.BaseType.INTEGER, "1/" + rightDim, null);
            if (rightDim == null) return new TypeDescriptor(TypeDescriptor.BaseType.INTEGER, leftDim, null);
            if (leftType.sameDimension(rightType, typeSystem)) return TypeDescriptor.INTEGER;

            String[] expStr = { leftDim, "/", rightDim };
            String resolvedDim = typeSystem.resolveSignature(typeSystem.generateExponents(expStr));
            if (resolvedDim == null) resolvedDim = leftDim + "/" + rightDim;
            return new TypeDescriptor(TypeDescriptor.BaseType.INTEGER, resolvedDim, null);
        } else {
            // Modulo — operands must share the same dimension
            if (!leftType.sameDimension(rightType, typeSystem)) {
                reportSemanticError(ctx, "Modulo ('\\\\') requires operands with matching dimensions");
                return TypeDescriptor.ERROR;
            }
            return new TypeDescriptor(TypeDescriptor.BaseType.INTEGER, leftDim, null);
        }
    }

    @Override
    public TypeDescriptor visitNumberAddSub(SafelangParser.NumberAddSubContext ctx) {
        TypeDescriptor leftType  = visit(ctx.number(0));
        if (leftType.isError()) return leftType;
        TypeDescriptor rightType = visit(ctx.number(1));
        if (rightType.isError()) return rightType;

        if (!leftType.sameDimension(rightType, typeSystem)) {
            reportSemanticError(ctx,
                "Cannot add/subtract mismatched dimensions: ["
                + leftType.dimension + "] and [" + rightType.dimension + "]");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor.BaseType base = promoteBase(leftType, rightType);
        return new TypeDescriptor(base, leftType.dimension, null);
    }

    @Override
    public TypeDescriptor visitNumberUnary(SafelangParser.NumberUnaryContext ctx) {
        return visit(ctx.number());
    }

    @Override
    public TypeDescriptor visitConvertToInt(SafelangParser.ConvertToIntContext ctx) {
        if (visit(ctx.expr()).isError()) return TypeDescriptor.ERROR;
        return TypeDescriptor.INTEGER;
    }

    @Override
    public TypeDescriptor visitConvertToReal(SafelangParser.ConvertToRealContext ctx) {
        if (visit(ctx.expr()).isError()) return TypeDescriptor.ERROR;
        return TypeDescriptor.REAL;
    }

    @Override
    public TypeDescriptor visitNumberIntLiteral(SafelangParser.NumberIntLiteralContext ctx) {
        return TypeDescriptor.INTEGER;
    }

    @Override
    public TypeDescriptor visitNumberDecimal(SafelangParser.NumberDecimalContext ctx) {
        return TypeDescriptor.REAL;
    }

    @Override
    public TypeDescriptor visitNumberScientific(SafelangParser.NumberScientificContext ctx) {
        return TypeDescriptor.REAL;
    }

    // ─── Boolean ─────────────────────────────────────────────────────────────

    @Override
    public TypeDescriptor visitBooleanParent(SafelangParser.BooleanParentContext ctx) {
        if (visit(ctx.booleans()).isBool())
            return TypeDescriptor.BOOL;

        reportSemanticError(ctx, "Expression in parenthesis must be of boolean type.");
        return TypeDescriptor.ERROR;
    }

    @Override
    public TypeDescriptor visitBooleanAnd(SafelangParser.BooleanAndContext ctx) {
        if (visit(ctx.booleans().get(0)).isBool() && visit(ctx.booleans().get(1)).isBool())
            return TypeDescriptor.BOOL;

        reportSemanticError(ctx, "Expressions in '&&' must be of boolean type.");
        return TypeDescriptor.ERROR;
    }

    @Override
    public TypeDescriptor visitBooleanOr(SafelangParser.BooleanOrContext ctx) {
        if (visit(ctx.booleans().get(0)).isBool() && visit(ctx.booleans().get(1)).isBool())
            return TypeDescriptor.BOOL;

        reportSemanticError(ctx, "Expressions in '||' must be of boolean type.");
        return TypeDescriptor.ERROR;
    }

    @Override
    public TypeDescriptor visitBooleanEqual(SafelangParser.BooleanEqualContext ctx) {
        TypeDescriptor leftType = visit(ctx.booleans(0));
        TypeDescriptor rightType = visit(ctx.booleans(1));
        if (leftType.sameDimension(rightType, typeSystem) && leftType.base.equals(rightType.base))
            return TypeDescriptor.BOOL;

        reportSemanticError(ctx, "Expressions in '=' comparison must be of the same type and dimension.");
        return TypeDescriptor.ERROR;
    }

    @Override
    public TypeDescriptor visitBooleanLesser(SafelangParser.BooleanLesserContext ctx) {
        TypeDescriptor leftType = visit(ctx.number(0));
        TypeDescriptor rightType = visit(ctx.number(1));
        if (leftType.sameDimension(rightType, typeSystem) && leftType.isNumeric() && rightType.isNumeric() && leftType.base.equals(rightType.base))
            return TypeDescriptor.BOOL;

        reportSemanticError(ctx, "Expressions in '<' comparison must be of type number and have the same dimension and type.");
        return TypeDescriptor.ERROR;
    }

    @Override
    public TypeDescriptor visitBooleanGreater(SafelangParser.BooleanGreaterContext ctx) {
        TypeDescriptor leftType = visit(ctx.number(0));
        TypeDescriptor rightType = visit(ctx.number(1));
        if (leftType.sameDimension(rightType, typeSystem) && leftType.isNumeric() && rightType.isNumeric() && leftType.base.equals(rightType.base))
            return TypeDescriptor.BOOL;

        reportSemanticError(ctx, "Expressions in '>' comparison must be of type number and have the same dimension and type.");
        return TypeDescriptor.ERROR;
    }

    @Override
    public TypeDescriptor visitBooleanLesserEqual(SafelangParser.BooleanLesserEqualContext ctx) {
        TypeDescriptor leftType = visit(ctx.number(0));
        TypeDescriptor rightType = visit(ctx.number(1));
        if (leftType.sameDimension(rightType, typeSystem) && leftType.isNumeric() && rightType.isNumeric() && leftType.base.equals(rightType.base))
            return TypeDescriptor.BOOL;

        reportSemanticError(ctx, "Expressions in '<=' comparison must be of type number and have the same dimension and type.");
        return TypeDescriptor.ERROR;
    }

    @Override
    public TypeDescriptor visitBooleanGreaterEqual(SafelangParser.BooleanGreaterEqualContext ctx) {
        TypeDescriptor leftType = visit(ctx.number(0));
        TypeDescriptor rightType = visit(ctx.number(1));
        if (leftType.sameDimension(rightType, typeSystem) && leftType.isNumeric() && rightType.isNumeric() && leftType.base.equals(rightType.base))
            return TypeDescriptor.BOOL;

        reportSemanticError(ctx, "Expressions in '>=' comparison must be of type number and have the same dimension and type.");
        return TypeDescriptor.ERROR;
    }

    @Override
    public TypeDescriptor visitBooleanNotEqual(SafelangParser.BooleanNotEqualContext ctx) {
        TypeDescriptor leftType = visit(ctx.booleans(0));
        TypeDescriptor rightType = visit(ctx.booleans(1));
        if (leftType.sameDimension(rightType, typeSystem) && leftType.base.equals(rightType.base))
            return TypeDescriptor.BOOL;

        reportSemanticError(ctx, "Expressions in '=' comparison must be of the same type and dimension.");
        return TypeDescriptor.ERROR;
    }

    @Override
    public TypeDescriptor visitBooleanLiteral(SafelangParser.BooleanLiteralContext ctx) {
        return TypeDescriptor.BOOL;
    }

    @Override
    public TypeDescriptor visitBooleanID(SafelangParser.BooleanIDContext ctx) {
        TypeDescriptor idType = symbolTable.lookup(ctx.ID().getText());
        if (idType.isError())
            reportSemanticError(ctx, "variable '" + ctx.ID().getText() + "' does not exist");

        return idType;
    }

    @Override
    public TypeDescriptor visitBooleanNumber(SafelangParser.BooleanNumberContext ctx) {
        TypeDescriptor idType = visit(ctx.number());
        if (!idType.isNumeric())
            return TypeDescriptor.ERROR;

        return idType;
    }

    @Override
    public TypeDescriptor visitBooleanString(SafelangParser.BooleanStringContext ctx) {
        TypeDescriptor idType = visit(ctx.string());
        if (!idType.isString())
            return TypeDescriptor.ERROR;

        return idType;
    }

    @Override
    public TypeDescriptor visitBooleanNot(SafelangParser.BooleanNotContext ctx) {
        TypeDescriptor type = visit(ctx.booleans());

        if (type == null || type.isError()) {
            return TypeDescriptor.ERROR;
        }

        if (!type.isBool()) {
            reportSemanticError(ctx, "'not' requires a boolean expression");
            return TypeDescriptor.ERROR;
        }

        return TypeDescriptor.BOOL;
    }

    /**
     * An ID in a numeric context may be:
     *   1. A unit name  (e.g. `meter`)  → returns the unit's dimensional type
     *   2. A variable   (e.g. `x`)      → returns the variable's type (must be numeric)
     */
    @Override
    public TypeDescriptor visitNumberID(SafelangParser.NumberIDContext ctx) {
        String name = ctx.ID().getText();

        if (typeSystem.unitExists(name))
            return typeSystem.getBaseType(name);

        TypeDescriptor type = symbolTable.lookup(name);
        if (type.isNumeric())
            return type;

        reportSemanticError(ctx, "'" + name + "' is not a numeric value or known unit");
        return TypeDescriptor.ERROR;
    }

    // ─── List retrieval / length ──────────────────────────────────────────────

    private TypeDescriptor getListElementByName(String listName,
                                                TypeDescriptor indexType,
                                                org.antlr.v4.runtime.ParserRuleContext ctx) {
        checkIndex(indexType, ctx);

        TypeDescriptor listType = symbolTable.lookup(listName);

        if (listType.isError()) {
            reportSemanticError(ctx, "Variable '" + listName + "' has not been declared");
            return TypeDescriptor.ERROR;
        }

        if (!listType.isList) {
            reportSemanticError(ctx, "Variable '" + listName + "' is not a list");
            return TypeDescriptor.ERROR;
        }

        return makeElementType(listType);
    }

    @Override
    public TypeDescriptor visitExprListRetrieveElement(SafelangParser.ExprListRetrieveElementContext ctx) {
        String listName = ctx.ID().getText();
        TypeDescriptor indexType = visit(ctx.number());

        return getListElementByName(listName, indexType, ctx);
    }

    @Override
    public TypeDescriptor visitStringListRetrieveElement(SafelangParser.StringListRetrieveElementContext ctx) {
        String listName = ctx.ID().getText();
        TypeDescriptor indexType = visit(ctx.number());

        TypeDescriptor elementType = getListElementByName(listName, indexType, ctx);

        if (!elementType.isString()) {
            reportSemanticError(ctx, "List element is not a string");
            return TypeDescriptor.ERROR;
        }

        return elementType;
    }

    @Override
    public TypeDescriptor visitNumberListRetrieveElement(SafelangParser.NumberListRetrieveElementContext ctx) {
        String listName = ctx.ID().getText();
        TypeDescriptor indexType = visit(ctx.number());

        TypeDescriptor elementType = getListElementByName(listName, indexType, ctx);

        if (!elementType.isNumeric()) {
            reportSemanticError(ctx, "List element is not numeric");
            return TypeDescriptor.ERROR;
        }

        return elementType;
    }

    @Override
    public TypeDescriptor visitBooleanListRetrieveElement(SafelangParser.BooleanListRetrieveElementContext ctx) {
        String listName = ctx.ID(0).getText();

        TypeDescriptor indexType;

        if (ctx.IntegerLiteral() != null) {
            indexType = TypeDescriptor.INTEGER;
        } else {
            TypeDescriptor idType = symbolTable.lookup(ctx.ID(1).getText());

            if (idType.isError()) {
                reportSemanticError(ctx, "Variable '" + ctx.ID(1).getText() + "' has not been declared");
                return TypeDescriptor.ERROR;
            }

            indexType = idType;
        }

        TypeDescriptor elementType = getListElementByName(listName, indexType, ctx);

        if (!elementType.isBool()) {
            reportSemanticError(ctx, "List element is not boolean");
            return TypeDescriptor.ERROR;
        }

        return elementType;
    }

    @Override
    public TypeDescriptor visitNumberListLength(SafelangParser.NumberListLengthContext ctx) {
        String listName = ctx.ID().getText();

        TypeDescriptor listType = symbolTable.lookup(listName);

        if (listType.isError()) {
            reportSemanticError(ctx, "Variable '" + listName + "' has not been declared");
            return TypeDescriptor.ERROR;
        }

        if (!listType.isList) {
            reportSemanticError(ctx, "length() expects a list");
            return TypeDescriptor.ERROR;
        }

        return TypeDescriptor.INTEGER;
    }

    // ─── List add ─────────────────────────────────────────────────────────────

    private TypeDescriptor checkListAdd(TypeDescriptor exprType,
                                        String listName,
                                        org.antlr.v4.runtime.ParserRuleContext ctx) {
        if (exprType == null || exprType.isError())
            return TypeDescriptor.ERROR;

        if (exprType.isList) {
            reportSemanticError(ctx, "Cannot add a list as an element");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor listType = symbolTable.lookup(listName);

        if (listType.isError()) {
            reportSemanticError(ctx, "Variable '" + listName + "' has not been declared");
            return TypeDescriptor.ERROR;
        }

        if (!listType.isList) {
            reportSemanticError(ctx, "Variable '" + listName + "' is not a list");
            return TypeDescriptor.ERROR;
        }

        TypeDescriptor elementTargetType = makeElementType(listType);
        String targetTypeName = getTypeNameFromDescriptor(elementTargetType);

        TypeDescriptor storedElementType = checkAssignCompatibility(exprType, targetTypeName, ctx);

        if (storedElementType.isError())
            return TypeDescriptor.ERROR;

        return listType;
    }

    @Override
    public TypeDescriptor visitListadd(SafelangParser.ListaddContext ctx) {
        TypeDescriptor exprType = visit(ctx.expr());
        String listName = ctx.ID().getText();

        return checkListAdd(exprType, listName, ctx);
    }

    // ─── Try / Rescue ─────────────────────────────────────────────────────────

    @Override
    public TypeDescriptor visitTryNorm(SafelangParser.TryNormContext ctx) {
        return visitCommonStatsWithNewScope(ctx.commonStat());
    }

    @Override
    public TypeDescriptor visitTryRescue(SafelangParser.TryRescueContext ctx) {
        TypeDescriptor tryBlockType = visitCommonStatsWithNewScope(ctx.commonStat());

        if (tryBlockType.isError())
            return TypeDescriptor.ERROR;

        TypeDescriptor rescueType = visit(ctx.rescue());

        if (rescueType.isError())
            return TypeDescriptor.ERROR;

        return TypeDescriptor.BOOL;
    }

    @Override
    public TypeDescriptor visitRescueNorm(SafelangParser.RescueNormContext ctx) {
        return visitCommonStatsWithNewScope(ctx.commonStat());
    }

    @Override
    public TypeDescriptor visitRescueRetry(SafelangParser.RescueRetryContext ctx) {
        /*
        * Type checker does not need to validate retry runtime behaviour.
        * It only checks the statements before retry.
        */
        return visitCommonStatsWithNewScope(ctx.commonStat());
    }

    @Override
    public TypeDescriptor visitFail(SafelangParser.FailContext ctx) {
        /*
        * Runtime control-flow instruction.
        * Type checker only needs to accept it as a valid statement.
        */
        return TypeDescriptor.BOOL;
    }

    // ─── Internal helpers ─────────────────────────────────────────────────────

    /**
     * Build the base unit name for a dependent dimension from the RHS type_def_expr.
     * E.g. Length/Time → "meter/second"
     */
    private String buildUnitNameFromDimExpr(SafelangParser.Type_def_exprContext ctx) {
        if (ctx instanceof SafelangParser.TypeDefIDContext) {
            String dim = ((SafelangParser.TypeDefIDContext) ctx).ID().getText();
            String unit = typeSystem.getUnit(dim);
            return unit != null ? unit : dim;
        }
        SafelangParser.TypeDefExprContext binCtx = (SafelangParser.TypeDefExprContext) ctx;
        String left  = buildUnitNameFromDimExpr(binCtx.type_def_expr(0));
        String op    = binCtx.op.getText();
        String right = buildUnitNameFromDimExpr(binCtx.type_def_expr(1));
        return left + op + right;
    }

    /**
     * Build the exponent array ["Dim1", "*" or "/", "Dim2"] for a binary type_def_expr,
     * or ["DimName"] for a leaf.
     * Only supports single binary operation (top-level); for deeply nested expressions
     * visitTypeDefExpr handles the recursion through the type system itself.
     */
    private String[] buildExponentArray(SafelangParser.Type_def_exprContext ctx) {
        if (ctx instanceof SafelangParser.TypeDefIDContext) {
            String dim = ((SafelangParser.TypeDefIDContext) ctx).ID().getText();
            return new String[]{ dim };
        }
        SafelangParser.TypeDefExprContext binCtx = (SafelangParser.TypeDefExprContext) ctx;
        // Resolve left and right to their dimension names
        String leftDim  = resolveDimExprName(binCtx.type_def_expr(0));
        String rightDim = resolveDimExprName(binCtx.type_def_expr(1));
        return new String[]{ leftDim, binCtx.op.getText(), rightDim };
    }

    /**
     * Recursively resolve a type_def_expr to a dimension name string (which may be structural).
     */
    private String resolveDimExprName(SafelangParser.Type_def_exprContext ctx) {
        if (ctx instanceof SafelangParser.TypeDefIDContext)
            return ((SafelangParser.TypeDefIDContext) ctx).ID().getText();
        TypeDescriptor t = visit(ctx);
        return t != null ? t.dimension : "unknown";
    }

    /**
     * Resolve a type annotation string to a TypeDescriptor (without dimension context).
     * Used for try-assign declarations where the expression may have failed.
     */
    private TypeDescriptor resolveAnnotatedType(String typeStr) {
        switch (typeStr) {
            case "integer":
                return TypeDescriptor.INTEGER;
            case "real":
                return TypeDescriptor.REAL;
            case "string":
                return TypeDescriptor.STRING;
            case "boolean":
            case "bool":
                return TypeDescriptor.BOOL;
            default:
                TypeDescriptor customType = typeSystem.getBaseType(typeStr);

                if (customType == null || customType.isError())
                    return TypeDescriptor.ERROR;

                return customType;
        }
    }

    private TypeDescriptor cloneType(TypeDescriptor type) {
        if (type == null || type.isError())
            return TypeDescriptor.ERROR;

        TypeDescriptor copy;

        if (type.hasDimension()) {
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
        TypeDescriptor listType = cloneType(elementType);
        if (listType.isError())
            return TypeDescriptor.ERROR;

        listType.isList = true;
        return listType;
    }

    private TypeDescriptor makeElementType(TypeDescriptor listType) {
        TypeDescriptor elementType = cloneType(listType);
        if (elementType.isError())
            return TypeDescriptor.ERROR;

        elementType.isList = false;
        return elementType;
    }

    private boolean sameBitRange(TypeDescriptor a, TypeDescriptor b) {
        if (!a.hasBitRange() && !b.hasBitRange())
            return true;

        if (a.hasBitRange() && b.hasBitRange())
            return a.bitRange.equals(b.bitRange);

        return false;
    }

    private boolean sameBaseOrIntToReal(TypeDescriptor exprType, TypeDescriptor targetType) {
        if (exprType.base.equals(targetType.base))
            return true;

        return exprType.isInteger() && targetType.isReal();
    }

    private String getTypeNameFromDescriptor(TypeDescriptor type) {
        if (type.hasDimension())
            return type.dimension;

        return type.base.toString().toLowerCase();
    }

    private TypeDescriptor checkIndex(TypeDescriptor indexType,
                                    org.antlr.v4.runtime.ParserRuleContext ctx) {
        if (indexType == null || indexType.isError())
            return TypeDescriptor.ERROR;

        if (!indexType.isInteger() || indexType.hasDimension() || indexType.isList || indexType.hasBitRange()) {
            reportSemanticError(ctx, "List index must be a plain integer");
            return TypeDescriptor.ERROR;
        }

        return TypeDescriptor.INTEGER;
    }

    private TypeDescriptor visitCommonStatsWithNewScope(
            java.util.List<SafelangParser.CommonStatContext> stats
    ) {
        symbolTable.pushScope();

        for (SafelangParser.CommonStatContext statCtx : stats) {
            TypeDescriptor statType = visit(statCtx);

            if (statType != null && statType.isError()) {
                symbolTable.popScope();
                return TypeDescriptor.ERROR;
            }
        }

        symbolTable.popScope();
        return TypeDescriptor.BOOL;
    }

    // ─── Error Reporting ─────────────────────────────────────────────────────

    private void reportSemanticError(org.antlr.v4.runtime.ParserRuleContext ctx, String message) {
        int line = ctx.getStart().getLine();
        System.err.println("[line " + line + "]   [ERROR]  " + message);
        System.exit(1);
    }
}