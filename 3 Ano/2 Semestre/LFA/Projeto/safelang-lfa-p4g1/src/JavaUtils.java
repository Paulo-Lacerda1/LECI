import org.antlr.v4.runtime.ParserRuleContext;
import org.stringtemplate.v4.ST;

public class JavaUtils {
    public final STGBuilder builder;
    public final SymbolTable symbolTable;
    public final TypeSystem typeSystem;

    public JavaUtils(String stgPath, SymbolTable symbolTable, TypeSystem typeSystem) {
        this.builder = new STGBuilder(stgPath);
        this.symbolTable = symbolTable;
        this.typeSystem = typeSystem;
    }

    // ─── Utilities ────────────────────────────────────────────────────────────

    /** Wrap raw Java text in a plain ST. */
    public ST makeRawST(String text) {
        ST st = new ST("<v>");
        st.add("v", text);
        return st;
    }

    /** Concatenate two ST statements separated by a newline + indent. */
    public ST combine(ST first, ST second) {
        ST st = new ST("<a>\n        <b>");
        st.add("a", first);
        st.add("b", second);
        return st;
    }

    public ST combineNoSpaces(ST first, ST second) {
        ST st = new ST("<a><b>");
        st.add("a", first);
        st.add("b", second);
        return st;
    }
    /**
     * Cast a Rational expression ST to the Java type matching `resType`.
     * e.g. `((rational.FractionType)(<expr>))`
     */
    public ST castToJavaType(ST expr, TypeDescriptor resType) {
        String javaType = typeDescriptorToJava(resType);
        return builder.makeST("castRational", javaType, expr).orElseThrow();
    }

    /**
     * Coerce an expression from one numeric base type to another.
     * integer → real  :  `.toFraction()`
     * real    → integer: `.toInteger()`
     * same or non-numeric: no-op
     */
    public ST coerceExpr(ST expr, TypeDescriptor fromType, TypeDescriptor toType) {
        if (fromType == null || toType == null) return expr;
        if (fromType.isInteger() && toType.isReal())
            return builder.makeST("toFraction", expr).orElseThrow();
        if (fromType.isReal() && toType.isInteger())
            return builder.makeST("toInteger", expr).orElseThrow();
        return expr;
    }

    /**
     * If the expression carries a dimensional type with a display suffix,
     * appends `+ "<suffix>"` to the string expression.
     * If the expression is not a string yet, wraps it in convertToString first.
     */
    public ST appendSuffix(CompilerReturn exprRet) {
        if (exprRet.type == null || exprRet.type.dimension == null)
            return exprRet.template;
        String suffix = resolveDisplaySuffix(exprRet.type.dimension);
        if (suffix.isEmpty())
            return exprRet.template;
        return builder.makeST("appendSuffix", exprRet.template, suffix).orElseThrow();
    }

    /**
     * Map a TypeDescriptor to its Java representation class.
     */
    public String typeDescriptorToJava(TypeDescriptor type) {
        if (type == null || type.isError()) return "rational.FractionType";
        if (type.isInteger())               return "rational.IntegerType";
        if (type.isReal())                  return "rational.FractionType";
        if (type.isString())                return "String";
        if (type.isBool())                  return "Boolean";
        return "rational.FractionType";
    }

    /**
     * Resolve the annotated type from an assign/declare context.
     * Works for AssignValType, AssignTryValType, and AssignType contexts
     * which all share the same shape: `ID ':=' expr ':' (TYPEINT|TYPEREAL|TYPESTR|ID)`.
     */
    public TypeDescriptor resolveAnnotatedType(ParserRuleContext ctx) {
        String typeName;

        if (ctx instanceof SafelangParser.AssignValTypeContext) {
            // ID ':=' expr ':' type
            typeName = ctx.getChild(4).getText();

        } else if (ctx instanceof SafelangParser.AssignTryValTypeContext) {
            // ID ':=?' expr ':' type
            typeName = ctx.getChild(4).getText();

        } else if (ctx instanceof SafelangParser.AssignTypeContext) {
            // ID ':' type
            typeName = ctx.getChild(2).getText();

        } else if (ctx instanceof SafelangParser.AssignListTypeContext) {
            // ID ':' 'list' '[' type ']'
            typeName = ctx.getChild(4).getText();

        } else if (ctx instanceof SafelangParser.AssignListValTypeContext) {
            // Two shapes:
            // nums := new list[integer] : list[integer]
            // nums := otherNums : list[integer]

            if (ctx.getChild(2).getText().equals("new")) {
                typeName = ctx.getChild(10).getText();
            } else {
                typeName = ctx.getChild(6).getText();
            }

        } else {
            throw new RuntimeException("Unsupported context for type annotation: "
                + ctx.getClass().getSimpleName());
        }

        return resolveAnnotatedType(typeName);
    }

    public TypeDescriptor resolveAnnotatedType(String typeName) {
        switch (typeName) {
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
                TypeDescriptor customType = typeSystem.getBaseType(typeName);

                if (customType == null || customType.isError()) {
                    throw new RuntimeException("Unknown type annotation: " + typeName);
                }

                return customType;
        }
    }

    /**
     * Compute result type for *, /, //, \\ operations.
     * Division always produces REAL; others promote INTEGER→REAL if either operand is REAL.
     */
    public TypeDescriptor computeMulDivType(TypeDescriptor left, TypeDescriptor right, String op) {
        if (left == null || right == null) return TypeDescriptor.ERROR;

        TypeDescriptor.BaseType base;
        if (op.equals("/"))
            base = TypeDescriptor.BaseType.REAL;
        else
            base = (left.isReal() || right.isReal())
                    ? TypeDescriptor.BaseType.REAL
                    : TypeDescriptor.BaseType.INTEGER;

        String resDim = null;
        if (left.dimension != null && right.dimension != null) {
            resDim = op.equals("*")
                    ? typeSystem.multiplyDimentsionally(left.dimension, right.dimension)
                    : typeSystem.divide(left.dimension, right.dimension);
            // If no named dimension matches, use structural fallback
            if (resDim == null)
                resDim = op.equals("*")
                        ? left.dimension + "*" + right.dimension
                        : left.dimension + "/" + right.dimension;
        } else if (left.dimension != null) {
            resDim = left.dimension;   // scalar on right: dimension unchanged
        } else if (right.dimension != null && op.equals("*")) {
            resDim = right.dimension;  // scalar * dimensional: dimension from right
        }

        if (resDim != null)
            return new TypeDescriptor(base, resDim, null);
        return base == TypeDescriptor.BaseType.REAL ? TypeDescriptor.REAL : TypeDescriptor.INTEGER;
    }

    /**
     * Compute result type for +/- operations.
     * Dimension is preserved from either operand (TypeChecker guarantees they match).
     */
    public TypeDescriptor computeAddSubType(TypeDescriptor left, TypeDescriptor right) {
        if (left == null || right == null) return TypeDescriptor.ERROR;
        TypeDescriptor.BaseType base = (left.isReal() || right.isReal())
                ? TypeDescriptor.BaseType.REAL
                : TypeDescriptor.BaseType.INTEGER;
        String resDim = left.dimension != null ? left.dimension : right.dimension;
        if (resDim != null)
            return new TypeDescriptor(base, resDim, null);
        return base == TypeDescriptor.BaseType.REAL ? TypeDescriptor.REAL : TypeDescriptor.INTEGER;
    }

    /**
     * Resolve the display suffix for a dimension (e.g. "Length" → "m", "Velocity" → "m/s").
     * First checks if the base unit has an explicit short suffix.
     * If not (compound unit like "meter/second"), substitutes each known unit word with its suffix.
     */
    public String resolveDisplaySuffix(String dimensionName) {
        if (dimensionName == null) return "";

        String unitName = typeSystem.getUnit(dimensionName);
        if (unitName == null) return "";

        String suffix = typeSystem.getSuffix(unitName);
        if (suffix != null) return suffix;

        // Compound unit: replace full unit names with their short suffixes
        String working = unitName;
        for (String knownUnit : typeSystem.unitSuffixesMap.keySet()) {
            String knownShort = typeSystem.getSuffix(knownUnit);
            if (knownShort != null && !knownShort.isEmpty())
                working = working.replaceAll("\\b" + knownUnit + "\\b", knownShort);
        }
        return working;
    }

    public String evaluateConstantFactor(SafelangParser.NumberContext numCtx) {
        if (numCtx == null) {
            return "1";
        }

        // If it's a binary operation expression wrapped in the number rule (e.g. NumberMult, NumberDivReal)
        if (numCtx instanceof SafelangParser.NumberMultContext multi) {
            String left = evaluateConstantFactor(multi.number(0));
            String right = evaluateConstantFactor(multi.number(1));
            if (!left.equals("1") && !left.isEmpty()) return left;
            if (!right.equals("1") && !right.isEmpty()) return right;
        }
        else if (numCtx instanceof SafelangParser.NumberDivRealContext div) {
            String left = evaluateConstantFactor(div.number(0));
            String right = evaluateConstantFactor(div.number(1));
            if (!left.equals("1") && !left.isEmpty()) return left;
            if (!right.equals("1") && !right.isEmpty()) return right;
        }
        else if (numCtx instanceof SafelangParser.NumberSuffixContext) {
            // e.g., 0.0254*meter -> 'meter' is matched as a suffix here or number ID
            return evaluateConstantFactor(((SafelangParser.NumberSuffixContext) numCtx).number());
        }
        else if (numCtx instanceof SafelangParser.NumberParentContext) {
            // Parentheses rule context maps via expr()
            return "1";
        }
        // Base terminal tokens
        else if (numCtx instanceof SafelangParser.NumberDecimalContext ||
                numCtx instanceof SafelangParser.NumberIntLiteralContext ||
                numCtx instanceof SafelangParser.NumberScientificContext) {
            return numCtx.getText(); // returns "0.0254" safely
        }

        return "1";
    }

    public ST enforceBitRange(ST expr, TypeDescriptor targetType) {
        if (targetType == null || targetType.isError() || !targetType.hasBitRange()) {
            return expr;
        }

        if (targetType.isInteger()) {
            return builder.makeST("toIntegerSized", expr, targetType.bitRange).orElseThrow();
        }

        if (targetType.isReal()) {
            return builder.makeST("toFractionSized", expr, targetType.bitRange).orElseThrow();
        }

        return expr;
    }
}
