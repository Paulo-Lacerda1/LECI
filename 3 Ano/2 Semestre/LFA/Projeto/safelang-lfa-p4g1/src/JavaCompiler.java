import org.antlr.v4.runtime.ParserRuleContext;
import org.stringtemplate.v4.ST;

import rational.FractionType;

import java.util.ArrayList;
import java.util.List;


public class JavaCompiler extends SafelangBaseVisitor<CompilerReturn> {
    private final JavaUtils java_utils;
    public int counter = 0;

    public JavaCompiler(String stgPath, SymbolTable symbolTable, TypeSystem typeSystem) {
        this.java_utils = new JavaUtils(stgPath, symbolTable, typeSystem);
    }

    // ─── Program ──────────────────────────────────────────────────────────────

    @Override
    public CompilerReturn visitProgram(SafelangParser.ProgramContext ctx) {
        ST prog = java_utils.builder.makeST("program", visitStats(ctx.stat())).orElseThrow();
        return new CompilerReturn(prog, null);
    }

    // ─── Stats ────────────────────────────────────────────────────────────────

    @Override public CompilerReturn visitStatAssign(SafelangParser.StatAssignContext ctx) { return visit(ctx.assign()); }
    @Override public CompilerReturn visitStatWrite(SafelangParser.StatWriteContext ctx)   { return visit(ctx.write()); }
    @Override public CompilerReturn visitStatExpr(SafelangParser.StatExprContext ctx)     { return visit(ctx.expr()); }
    @Override public CompilerReturn visitStatIf(SafelangParser.StatIfContext ctx)         { return visit(ctx.if_()); }

    /**
     * Type and unit declarations are purely compile-time metadata already captured
     * by the TypeChecker and stored in TypeSystem/SymbolTable.
     * The only exception is `unit` declarations, which need to register their
     * conversion value into the TypeSystem at runtime (visitDimensionUnit/Suffix).
     */
    @Override
    public CompilerReturn visitStatType(SafelangParser.StatTypeContext ctx) {
        return visit(ctx.type());
    }

    // ─── Write ────────────────────────────────────────────────────────────────

    @Override
    public CompilerReturn visitWriteExpr(SafelangParser.WriteExprContext ctx) {
        CompilerReturn expr = visit(ctx.expr());
        ST exprWithSuffix = java_utils.appendSuffix(expr);
        ST s = java_utils.builder.makeST("writeExpr", exprWithSuffix).orElseThrow();
        return new CompilerReturn(s, null);
    }

    @Override
    public CompilerReturn visitWriteLnExpr(SafelangParser.WriteLnExprContext ctx) {
        if (ctx.expr() == null) {
            ST s = java_utils.builder.makeST("writeLnEmpty").orElseThrow();
            return new CompilerReturn(s, null);
        }
        CompilerReturn expr = visit(ctx.expr());
        ST exprWithSuffix = java_utils.appendSuffix(expr);
        ST s = java_utils.builder.makeST("writeLnExpr", exprWithSuffix).orElseThrow();
        return new CompilerReturn(s, null);
    }

    // ─── Assign ───────────────────────────────────────────────────────────────

    @Override
    public CompilerReturn visitAssignValType(SafelangParser.AssignValTypeContext ctx) {
        String name = ctx.ID().getFirst().getText();

        TypeDescriptor targetType = java_utils.resolveAnnotatedType(ctx);
        CompilerReturn exprRet = visit(ctx.expr());

        ST coercedExpr = java_utils.coerceExpr(exprRet.template, exprRet.type, targetType);
        coercedExpr = java_utils.enforceBitRange(coercedExpr, targetType);
        String javaType = java_utils.typeDescriptorToJava(targetType);

        ST decl   = java_utils.builder.makeST("declareVar", javaType, name).orElseThrow();
        ST assign = java_utils.builder.makeST("assignVal", name, coercedExpr).orElseThrow();
        return new CompilerReturn(java_utils.combine(decl, assign), targetType);
    }

    @Override
    public CompilerReturn visitAssignVal(SafelangParser.AssignValContext ctx) {
        String name = ctx.ID().getText();
        TypeDescriptor targetType = java_utils.symbolTable.lookup(name);

        CompilerReturn exprRet = visit(ctx.expr());
        ST coercedExpr = java_utils.coerceExpr(exprRet.template, exprRet.type, targetType);
        coercedExpr = java_utils.enforceBitRange(coercedExpr, targetType);

        ST assign = java_utils.builder.makeST("assignVal", name, coercedExpr).orElseThrow();

        return new CompilerReturn(assign, targetType);
    }

    @Override
    public CompilerReturn visitAssignTryVal(SafelangParser.AssignTryValContext ctx) {
        String name = ctx.ID().getText();
        TypeDescriptor targetType = java_utils.symbolTable.lookup(name);
        int line = ctx.getStart().getLine();

        CompilerReturn exprRet = visit(ctx.expr());
        ST coercedExpr = java_utils.coerceExpr(exprRet.template, exprRet.type, targetType);
        coercedExpr = java_utils.enforceBitRange(coercedExpr, targetType);

        ST assign = java_utils.builder.makeST("assignTryVal", name, coercedExpr, line).orElseThrow();

        return new CompilerReturn(assign, targetType);
    }

    @Override
    public CompilerReturn visitAssignTryValType(SafelangParser.AssignTryValTypeContext ctx) {
        String name = ctx.ID().getFirst().getText();
        TypeDescriptor targetType = java_utils.resolveAnnotatedType(ctx);
        int line = ctx.getStart().getLine();

        CompilerReturn exprRet = visit(ctx.expr());
        ST coercedExpr = java_utils.coerceExpr(exprRet.template, exprRet.type, targetType);
        coercedExpr = java_utils.enforceBitRange(coercedExpr, targetType);
        String javaType = java_utils.typeDescriptorToJava(targetType);

        ST decl   = java_utils.builder.makeST("declareVar", javaType, name).orElseThrow();
        ST assign = java_utils.builder.makeST("assignTryVal", name, coercedExpr, line).orElseThrow();
        return new CompilerReturn(java_utils.combine(decl, assign), targetType);
    }

    @Override
    public CompilerReturn visitAssignType(SafelangParser.AssignTypeContext ctx) {
        String name = ctx.ID().getFirst().getText();
        TypeDescriptor targetType = java_utils.resolveAnnotatedType(ctx);
        String javaType = java_utils.typeDescriptorToJava(targetType);
        ST decl = java_utils.builder.makeST("declareVar", javaType, name).orElseThrow();

        return new CompilerReturn(decl, targetType);
    }

    // ─── Type / dimension declarations ───────────────────────────────────────
    // These are compile-time only — no Java code is emitted.
    // The TypeSystem was already populated by the TypeChecker pass.

    @Override public CompilerReturn visitTypeUnit(SafelangParser.TypeUnitContext ctx)                         { return null; }
    @Override public CompilerReturn visitTypeUnitSuffix(SafelangParser.TypeUnitSuffixContext ctx)             { return null; }
    @Override public CompilerReturn visitTypeDependent(SafelangParser.TypeDependentContext ctx)               { return null; }
    @Override public CompilerReturn visitTypeDependentUnit(SafelangParser.TypeDependentUnitContext ctx)       { return null; }
    @Override public CompilerReturn visitTypeDependentUnitSuffix(SafelangParser.TypeDependentUnitSuffixContext ctx) { return null; }

    /**
     * `unit Length [inch, in] := 0.0254 * meter`
     * This DOES generate code: it registers the conversion value at runtime
     * (so the interpreter / generated program knows how to convert inches ↔ meters).
     * The conversion expression is compiled as a Rational arithmetic expression.
     */
    @Override
    public CompilerReturn visitDimensionUnit(SafelangParser.DimensionUnitContext ctx) {
        String dimension = ctx.ID(0).getText();
        String unit = ctx.ID(1).getText();

        String factorStr = "1";
        if (ctx.number() != null) {
            factorStr = java_utils.evaluateConstantFactor(ctx.number());
            java_utils.typeSystem.changeUnitValue(unit, new FractionType(factorStr));
        }

        ST res = java_utils.builder.makeST("dimensionUnit", dimension, unit, factorStr).orElseThrow();
        return new CompilerReturn(res, null);
    }

    /**
     * unit Length [inch, in] := 0.0254 * meter
     */
    @Override
    public CompilerReturn visitDimensionUnitSuffix(SafelangParser.DimensionUnitSuffixContext ctx) {
        String dimension = ctx.ID(0).getText();
        String unit = ctx.ID(1).getText();
        String suffix = ctx.ID(2).getText();

        String factorStr = "1";
        if (ctx.number() != null) {
            factorStr = java_utils.evaluateConstantFactor(ctx.number());
            java_utils.typeSystem.changeUnitValue(unit, new FractionType(factorStr));
        }

        ST res = java_utils.builder.makeST("dimensionUnitSuffix", dimension, unit, suffix, factorStr).orElseThrow();
        return new CompilerReturn(res, null);
    }

    // ─── Expressions ──────────────────────────────────────────────────────────

    @Override
    public CompilerReturn visitStringConcat(SafelangParser.StringConcatContext ctx) {
        CompilerReturn left  = visit(ctx.expr(0));
        CompilerReturn right = visit(ctx.expr(1));
        ST st = java_utils.builder.makeST("stringConcat", left.template, right.template).orElseThrow();
        return new CompilerReturn(st, TypeDescriptor.STRING);
    }

    @Override
    public CompilerReturn visitExprFormatCommand(SafelangParser.ExprFormatCommandContext ctx) {
        CompilerReturn expr  = visit(ctx.expr());
        CompilerReturn width = visit(ctx.number());
        ST st = java_utils.builder.makeST("formatCmd", expr.template, width.template).orElseThrow();
        return new CompilerReturn(st, TypeDescriptor.STRING);
    }

    @Override
    public CompilerReturn visitExprID(SafelangParser.ExprIDContext ctx) {
        String name = ctx.ID().getText();
        if (java_utils.typeSystem.unitExists(name)) {
            TypeDescriptor unitType = java_utils.typeSystem.getBaseType(name);

            // Instead of a hardcoded 1, fetch the actual conversion value literal string
            FractionType scale = java_utils.typeSystem.getUnitValue(name);
            String val = (scale != null) ? scale.toString() : "1";

            ST st = unitType.isInteger()
                    ? java_utils.builder.makeST("integerLiteral", val).orElseThrow()
                    : java_utils.builder.makeST("fractionLiteral", val).orElseThrow();

            return new CompilerReturn(st, unitType);
        }
        TypeDescriptor type = java_utils.symbolTable.lookup(name);
        return new CompilerReturn(java_utils.makeRawST(name), type);
    }

    @Override public CompilerReturn visitExprString(SafelangParser.ExprStringContext ctx) { return visit(ctx.string()); }
    @Override public CompilerReturn visitExprNumber(SafelangParser.ExprNumberContext ctx) { return visit(ctx.number()); }

    // ─── Strings ──────────────────────────────────────────────────────────────

    @Override
    public CompilerReturn visitStringLiteral(SafelangParser.StringLiteralContext ctx) {
        return new CompilerReturn(java_utils.makeRawST(ctx.StringLiteral().getText()), TypeDescriptor.STRING);
    }

    @Override
    public CompilerReturn visitConvertToString(SafelangParser.ConvertToStringContext ctx) {
        CompilerReturn expr = visit(ctx.expr());
        ST baseStr = java_utils.builder.makeST("convertToString", expr.template).orElseThrow();
        ST withSuffix = java_utils.appendSuffix(new CompilerReturn(baseStr, expr.type));
        return new CompilerReturn(withSuffix, TypeDescriptor.STRING);
    }

    @Override
    public CompilerReturn visitReadCmd(SafelangParser.ReadCmdContext ctx) {
        CompilerReturn prompt = visit(ctx.string());
        ST st = java_utils.builder.makeST("readExpr", prompt.template).orElseThrow();
        return new CompilerReturn(st, TypeDescriptor.STRING);
    }

    @Override
    public CompilerReturn visitStringID(SafelangParser.StringIDContext ctx) {
        String name = ctx.ID().getText();
        TypeDescriptor type = java_utils.symbolTable.lookup(name);
        return new CompilerReturn(java_utils.makeRawST(name), type);
    }

    // ─── Numbers ──────────────────────────────────────────────────────────────

    @Override
    public CompilerReturn visitNumberParent(SafelangParser.NumberParentContext ctx) {
        CompilerReturn inner = visit(ctx.expr());
        ST st = java_utils.builder.makeST("rationalParents", inner.template).orElseThrow();
        return new CompilerReturn(st, inner.type);
    }

    /**
     * `number ID` — suffix literal, e.g. `5m`, `10km`.
     * The number is already compiled; the ID is a unit suffix or unit name.
     * The dimensional type is resolved from the suffix/unit.
     */
    @Override
    public CompilerReturn visitNumberSuffix(SafelangParser.NumberSuffixContext ctx) {
        CompilerReturn num = visit(ctx.number());
        String id = ctx.ID().getText();

        // 1. Find the full unit name if 'id' is a suffix (like 'in' -> 'inch')
        String unitName = id;
        if (!java_utils.typeSystem.unitExists(id)) {
            unitName = java_utils.typeSystem.unitSuffixesMap.entrySet().stream()
                    .filter(e -> id.equals(e.getValue()))
                    .map(java.util.Map.Entry::getKey)
                    .findFirst()
                    .orElse(id);
        }

        TypeDescriptor dimType = java_utils.typeSystem.unitExists(unitName) ? java_utils.typeSystem.getBaseType(unitName) : num.type;

        // 2. Fetch the conversion factor from compile-time metadata
        FractionType scale = java_utils.typeSystem.getUnitValue(unitName);
        ST resultTemplate = num.template;

        // If a non-null conversion factor exists (e.g. 127/5000), scale the literal value
        if (scale != null) {
            ST scaleAST = java_utils.builder.makeST("fractionLiteral", scale.toString()).orElseThrow();
            resultTemplate = java_utils.builder.makeST("rationalMul", resultTemplate, scaleAST).orElseThrow();
        }

        // 3. Coerce and return
        ST coerced = java_utils.coerceExpr(resultTemplate, num.type, dimType);
        coerced = java_utils.enforceBitRange(coerced, dimType);
        return new CompilerReturn(coerced, dimType);
    }

    @Override
    public CompilerReturn visitNumberMult(SafelangParser.NumberMultContext ctx) {
        CompilerReturn left  = visit(ctx.number(0));
        CompilerReturn right = visit(ctx.number(1));
        TypeDescriptor resType = java_utils.computeMulDivType(left.type, right.type, "*");

        ST baseMath = java_utils.builder.makeST("rationalMul", left.template, right.template).orElseThrow();
        ST casted   = java_utils.castToJavaType(baseMath, resType);
        return new CompilerReturn(casted, resType);
    }

    @Override
    public CompilerReturn visitNumberDivReal(SafelangParser.NumberDivRealContext ctx) {
        CompilerReturn left  = visit(ctx.number(0));
        CompilerReturn right = visit(ctx.number(1));
        TypeDescriptor resType = java_utils.computeMulDivType(left.type, right.type, "/");

        ST baseMath = java_utils.builder.makeST("rationalDiv", left.template, right.template).orElseThrow();
        ST casted   = java_utils.castToJavaType(baseMath, resType);
        return new CompilerReturn(casted, resType);
    }

    @Override
    public CompilerReturn visitNumberQuotModInt(SafelangParser.NumberQuotModIntContext ctx) {
        CompilerReturn left  = visit(ctx.number(0));
        CompilerReturn right = visit(ctx.number(1));
        String op = ctx.op.getText();

        // Dimension follows same rules as integer division/modulo
        TypeDescriptor resType = java_utils.computeMulDivType(left.type, right.type, op);

        String tmpl = op.equals("//") ? "rationalQuot" : "rationalMod";
        ST baseMath = java_utils.builder.makeST(tmpl, left.template, right.template).orElseThrow();
        ST casted   = java_utils.castToJavaType(baseMath, resType);
        return new CompilerReturn(casted, resType);
    }

    @Override
    public CompilerReturn visitNumberAddSub(SafelangParser.NumberAddSubContext ctx) {
        CompilerReturn left  = visit(ctx.number(0));
        CompilerReturn right = visit(ctx.number(1));
        String op = ctx.op.getText();

        TypeDescriptor resType = java_utils.computeAddSubType(left.type, right.type);
        String tmpl = op.equals("+") ? "rationalAdd" : "rationalSub";

        ST baseMath = java_utils.builder.makeST(tmpl, left.template, right.template).orElseThrow();
        ST casted   = java_utils.castToJavaType(baseMath, resType);
        return new CompilerReturn(casted, resType);
    }

    @Override
    public CompilerReturn visitNumberUnary(SafelangParser.NumberUnaryContext ctx) {
        CompilerReturn operand = visit(ctx.number());
        if (ctx.op.getText().equals("-")) {
            ST st = java_utils.builder.makeST("rationalNegate", operand.template).orElseThrow();
            return new CompilerReturn(st, operand.type);
        }
        return operand;
    }

    @Override
    public CompilerReturn visitConvertToInt(SafelangParser.ConvertToIntContext ctx) {
        CompilerReturn expr = visit(ctx.expr());
        ST st = java_utils.builder.makeST("convertToInteger", expr.template).orElseThrow();
        return new CompilerReturn(st, TypeDescriptor.INTEGER);
    }

    @Override
    public CompilerReturn visitConvertToReal(SafelangParser.ConvertToRealContext ctx) {
        CompilerReturn expr = visit(ctx.expr());
        ST st = java_utils.builder.makeST("convertToFraction", expr.template).orElseThrow();
        return new CompilerReturn(st, TypeDescriptor.REAL);
    }

    @Override
    public CompilerReturn visitNumberIntLiteral(SafelangParser.NumberIntLiteralContext ctx) {
        String val = ctx.IntegerLiteral().getText();
        ST st = java_utils.builder.makeST("integerLiteral", val).orElseThrow();
        return new CompilerReturn(st, TypeDescriptor.INTEGER);
    }

    @Override
    public CompilerReturn visitNumberDecimal(SafelangParser.NumberDecimalContext ctx) {
        String val = ctx.NumberDecimal().getText();
        ST st = java_utils.builder.makeST("fractionLiteral", val).orElseThrow();
        return new CompilerReturn(st, TypeDescriptor.REAL);
    }

    @Override
    public CompilerReturn visitNumberScientific(SafelangParser.NumberScientificContext ctx) {
        String val = ctx.NumberScientific().getText();
        ST st = java_utils.builder.makeST("fractionLiteral", val).orElseThrow();
        return new CompilerReturn(st, TypeDescriptor.REAL);
    }

    /**
     * A bare ID in a numeric context is either:
     *   - a unit name  (e.g. `meter`)  → scalar 1 of that dimension's type
     *   - a variable   (e.g. `x`)      → its stored value
     */
    @Override
    public CompilerReturn visitNumberID(SafelangParser.NumberIDContext ctx) {
        String name = ctx.ID().getText();
        if (java_utils.typeSystem.unitExists(name)) {
            TypeDescriptor unitType = java_utils.typeSystem.getBaseType(name);

            // Instead of a hardcoded 1, fetch the actual conversion value literal string
            FractionType scale = java_utils.typeSystem.getUnitValue(name);
            String val = (scale != null) ? scale.toString() : "1";

            ST st = unitType.isInteger()
                    ? java_utils.builder.makeST("integerLiteral", val).orElseThrow()
                    : java_utils.builder.makeST("fractionLiteral", val).orElseThrow();

            return new CompilerReturn(st, unitType);
        }
        TypeDescriptor type = java_utils.symbolTable.lookup(name);
        return new CompilerReturn(java_utils.makeRawST(name), type);
    }

    //          BOOLEAN LOGIC

    @Override public CompilerReturn visitBooleanParent(SafelangParser.BooleanParentContext ctx) {
        CompilerReturn inner = visit(ctx.booleans());
        ST st = java_utils.builder.makeST("booleanParent", inner.template).orElseThrow();
        return new CompilerReturn(st, inner.type);
    }

    @Override public CompilerReturn visitBooleanNotEqual(SafelangParser.BooleanNotEqualContext ctx) {
        ST temp = java_utils.builder.makeST("booleanNotEqual", visit(ctx.booleans(0)).template, visit(ctx.booleans(1)).template).orElseThrow();
        return new CompilerReturn(temp, TypeDescriptor.BOOL);
    }

    @Override
    public CompilerReturn visitBooleanNot(SafelangParser.BooleanNotContext ctx) {
        CompilerReturn inner = visit(ctx.booleans());

        ST st = java_utils.builder
            .makeST("booleanNot", inner.template)
            .orElseThrow();

        return new CompilerReturn(st, TypeDescriptor.BOOL);
    }

    @Override public CompilerReturn visitBooleanEqual(SafelangParser.BooleanEqualContext ctx) {
        ST temp = java_utils.builder.makeST("booleanEqual", visit(ctx.booleans(0)).template, visit(ctx.booleans(1)).template).orElseThrow();
        return new CompilerReturn(temp, TypeDescriptor.BOOL);
    }

    @Override public CompilerReturn visitBooleanGreater(SafelangParser.BooleanGreaterContext ctx) {
        ST temp = java_utils.builder.makeST("booleanGreater", visit(ctx.number(0)).template, visit(ctx.number(1)).template).orElseThrow();
        return new CompilerReturn(temp, TypeDescriptor.BOOL);
    }

    @Override public CompilerReturn visitBooleanGreaterEqual(SafelangParser.BooleanGreaterEqualContext ctx) {
        ST temp = java_utils.builder.makeST("booleanGreaterEqual", visit(ctx.number(0)).template, visit(ctx.number(1)).template).orElseThrow();
        return new CompilerReturn(temp, TypeDescriptor.BOOL);
    }

    @Override public CompilerReturn visitBooleanLesser(SafelangParser.BooleanLesserContext ctx) {
        ST temp = java_utils.builder.makeST("booleanLesser", visit(ctx.number(0)).template, visit(ctx.number(1)).template).orElseThrow();
        return new CompilerReturn(temp, TypeDescriptor.BOOL);
    }

    @Override public CompilerReturn visitBooleanLesserEqual(SafelangParser.BooleanLesserEqualContext ctx) {
        ST temp = java_utils.builder.makeST("booleanLesserEqual", visit(ctx.number(0)).template, visit(ctx.number(1)).template).orElseThrow();
        return new CompilerReturn(temp, TypeDescriptor.BOOL);
    }

    @Override public CompilerReturn visitBooleanAnd(SafelangParser.BooleanAndContext ctx) {
        ST temp = java_utils.builder.makeST("booleanAnd", visit(ctx.booleans(0)).template, visit(ctx.booleans(1)).template).orElseThrow();
        return new CompilerReturn(temp, TypeDescriptor.BOOL);
    }

    @Override public CompilerReturn visitBooleanOr(SafelangParser.BooleanOrContext ctx) {
        ST temp = java_utils.builder.makeST("booleanOr", visit(ctx.booleans(0)).template, visit(ctx.booleans(1)).template).orElseThrow();
        return new CompilerReturn(temp, TypeDescriptor.BOOL);
    }

    @Override public CompilerReturn visitBooleanNumber(SafelangParser.BooleanNumberContext ctx) { return visit(ctx.number()); }

    @Override public CompilerReturn visitBooleanString(SafelangParser.BooleanStringContext ctx) { return visit(ctx.string()); }

    @Override public CompilerReturn visitExprBoolean(SafelangParser.ExprBooleanContext ctx) { return visit(ctx.booleans()); }

    @Override public CompilerReturn visitBooleanID(SafelangParser.BooleanIDContext ctx) {
        String name = ctx.ID().getText();
        TypeDescriptor type = java_utils.symbolTable.lookup(name);
        return new CompilerReturn(java_utils.makeRawST(name), type);
    }


    //          COMMON STAT LOGIC

    @Override public CompilerReturn visitCommonStatAssign(SafelangParser.CommonStatAssignContext ctx) {
        return visit(ctx.assign());
    }

    @Override public CompilerReturn visitCommonStatWrite(SafelangParser.CommonStatWriteContext ctx) {
        return visit(ctx.write());
    }

    @Override public CompilerReturn visitCommonStatExpr(SafelangParser.CommonStatExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override public CompilerReturn visitCommonStatIf(SafelangParser.CommonStatIfContext ctx) {
        return visit(ctx.if_());
    }

    //          IF ELSE LOGIC

    @Override public CompilerReturn visitIfElse(SafelangParser.IfElseContext ctx) {

        java_utils.symbolTable.pushScope();
        ST temp = java_utils.builder.makeST("conditionIfElse",
                visit(ctx.booleans()).template, visitStats(ctx.commonStat())).orElseThrow();
        if(ctx.else_() != null) {
            ST temp1 = visit(ctx.else_()).template;
            temp = java_utils.combineNoSpaces(temp, temp1);
        }
        java_utils.symbolTable.popScope();

        return new CompilerReturn(temp, null);
    }

    @Override public CompilerReturn visitIfEnd(SafelangParser.IfEndContext ctx) {
        java_utils.symbolTable.pushScope();
        ST prog = java_utils.builder.makeST("conditionIfEnd",
                visit(ctx.booleans()).template, visitStats(ctx.commonStat())).orElseThrow();
        java_utils.symbolTable.popScope();

        return new CompilerReturn(prog, null);
    }

    @Override public CompilerReturn visitElseIf(SafelangParser.ElseIfContext ctx) {
        java_utils.symbolTable.pushScope();
        ST temp = java_utils.builder.makeST("conditionElseIf",
                visit(ctx.booleans()).template, visitStats(ctx.commonStat())).orElseThrow();
        if(ctx.else_() != null) {
            ST temp1 = visit(ctx.else_()).template;
            temp = java_utils.combineNoSpaces(temp, temp1);
        }
        java_utils.symbolTable.popScope();

        return new CompilerReturn(temp, null);
    }

    @Override public CompilerReturn visitElseNorm(SafelangParser.ElseNormContext ctx) {
        java_utils.symbolTable.pushScope();
        ST prog = java_utils.builder.makeST("conditionElseNorm", visitStats(ctx.commonStat())).orElseThrow();
        java_utils.symbolTable.popScope();

        return new CompilerReturn(prog, null);
    }

    //          TYPE LOGIC

    @Override public CompilerReturn visitTypeDefExpr(SafelangParser.TypeDefExprContext ctx) {
        return null;
    }

    @Override public CompilerReturn visitTypeDefID(SafelangParser.TypeDefIDContext ctx) {
        String name = ctx.ID().getText();
        TypeDescriptor type = java_utils.typeSystem.getBaseType(name);
        return new CompilerReturn(java_utils.makeRawST(name), type);
    }


    private List<ST> visitStats(List<? extends ParserRuleContext> stats) {
        List<ST> result = new ArrayList<>();
        for (ParserRuleContext stat : stats) {
            CompilerReturn res = visit(stat);
            if (res != null && res.template != null)
                result.add(res.template);
        }

        return result;
    }


    @Override public CompilerReturn visitStatListAdd(SafelangParser.StatListAddContext ctx)       { return visit(ctx.listadd()); }
    @Override public CompilerReturn visitStatFor(SafelangParser.StatForContext ctx)               { return visit(ctx.for_()); }
    @Override public CompilerReturn visitStatTry(SafelangParser.StatTryContext ctx)               { return visit(ctx.try_()); }
    @Override public CompilerReturn visitStatWhile(SafelangParser.StatWhileContext ctx)           { return visit(ctx.while_()); }
    @Override public CompilerReturn visitStatAssert(SafelangParser.StatAssertContext ctx)         { return visit(ctx.assert_()); }
    @Override public CompilerReturn visitCommonStatListAdd(SafelangParser.CommonStatListAddContext ctx) { return visit(ctx.listadd()); }
    @Override public CompilerReturn visitCommonStatFor(SafelangParser.CommonStatForContext ctx)   { return visit(ctx.for_()); }
    @Override public CompilerReturn visitCommonStatTry(SafelangParser.CommonStatTryContext ctx)   { return visit(ctx.try_()); }
    @Override public CompilerReturn visitCommonStatWhile(SafelangParser.CommonStatWhileContext ctx){ return visit(ctx.while_()); }
    @Override public CompilerReturn visitCommonStatAssert(SafelangParser.CommonStatAssertContext ctx){ return visit(ctx.assert_()); }

    //          LISTS LOGIC

    @Override
    public CompilerReturn visitListadd(SafelangParser.ListaddContext ctx) {
        String listName = ctx.ID().getText();
        TypeDescriptor listType = java_utils.symbolTable.lookup(listName);
        TypeDescriptor elementType = makeListElementType(listType);

        CompilerReturn exprRet = visit(ctx.expr());
        ST element = java_utils.coerceExpr(exprRet.template, exprRet.type, elementType);
        element = java_utils.enforceBitRange(element, elementType);

        ST st = java_utils.builder.makeST("listAdd", listName, element).orElseThrow();
        return new CompilerReturn(st, listType);
    }

    @Override
    public CompilerReturn visitExprListRetrieveElement(SafelangParser.ExprListRetrieveElementContext ctx) {
        return compileListGet(ctx.ID().getText(), visit(ctx.number()));
    }

    @Override
    public CompilerReturn visitStringListRetrieveElement(SafelangParser.StringListRetrieveElementContext ctx) {
        return compileListGet(ctx.ID().getText(), visit(ctx.number()));
    }

    @Override
    public CompilerReturn visitNumberListLength(SafelangParser.NumberListLengthContext ctx) {
        ST st = java_utils.builder.makeST("listLength", ctx.ID().getText()).orElseThrow();
        return new CompilerReturn(st, TypeDescriptor.INTEGER);
    }

    @Override
    public CompilerReturn visitNumberListRetrieveElement(SafelangParser.NumberListRetrieveElementContext ctx) {
        return compileListGet(ctx.ID().getText(), visit(ctx.number()));
    }

    @Override
    public CompilerReturn visitAssignListValType(SafelangParser.AssignListValTypeContext ctx) {
        String name = ctx.ID().getFirst().getText();
        TypeDescriptor listType = java_utils.symbolTable.lookup(name);
        String javaListType = listTypeToJava(listType);

        ST st;
        if (isNewListAssignment(ctx)) {
            st = java_utils.builder.makeST("listDecl", javaListType, name).orElseThrow();
        } else {
            String sourceName = ctx.getChild(2).getText();
            st = java_utils.builder.makeST("listCopy", javaListType, name, sourceName).orElseThrow();
        }

        return new CompilerReturn(st, listType);
    }

    @Override
    public CompilerReturn visitAssignListType(SafelangParser.AssignListTypeContext ctx) {
        String name = ctx.ID().getFirst().getText();
        TypeDescriptor listType = java_utils.symbolTable.lookup(name);
        ST st = java_utils.builder.makeST("listDecl", listTypeToJava(listType), name).orElseThrow();
        return new CompilerReturn(st, listType);
    }

    @Override
    public CompilerReturn visitAssignListVal(SafelangParser.AssignListValContext ctx) {
        String name = ctx.ID().getFirst().getText();
        TypeDescriptor listType = java_utils.symbolTable.lookup(name);

        ST st;
        if (isNewListAssignment(ctx)) {
            st = java_utils.builder.makeST("listValNew", name).orElseThrow();
        } else {
            String sourceName = ctx.getChild(2).getText();
            st = java_utils.builder.makeST("listValCopy", name, sourceName).orElseThrow();
        }

        return new CompilerReturn(st, listType);
    }

    private CompilerReturn compileListGet(String listName, CompilerReturn indexRet) {
        TypeDescriptor listType = java_utils.symbolTable.lookup(listName);
        TypeDescriptor elementType = makeListElementType(listType);

        ST st = java_utils.builder.makeST("listGet", listName, indexRet.template).orElseThrow();
        return new CompilerReturn(st, elementType);
    }

    private String listTypeToJava(TypeDescriptor listType) {
        TypeDescriptor elementType = makeListElementType(listType);
        return "ArrayList<" + java_utils.typeDescriptorToJava(elementType) + ">";
    }

    private TypeDescriptor makeListElementType(TypeDescriptor listType) {
        TypeDescriptor elementType = cloneType(listType);
        if (!elementType.isError())
            elementType.isList = false;
        return elementType;
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

    private boolean isNewListAssignment(ParserRuleContext ctx) {
        return ctx.getChildCount() > 2 && ctx.getChild(2).getText().equals("new");
    }


    //          FOR LOGIC

    @Override
    public CompilerReturn visitForAssign(SafelangParser.ForAssignContext ctx) {
        String loopVarName = ctx.ID().getText();

        CompilerReturn start = visit(ctx.number(0));
        CompilerReturn end = visit(ctx.number(1));

        boolean alreadyDeclared = !java_utils.symbolTable.lookup(loopVarName).isError();

        java_utils.symbolTable.pushScope();

        if (!alreadyDeclared) {
            java_utils.symbolTable.declare(loopVarName, TypeDescriptor.INTEGER);
        }

        List<ST> body = visitStats(ctx.commonStat());

        java_utils.symbolTable.popScope();

        String templateName = alreadyDeclared
            ? "loopForAssignExisting"
            : "loopForAssignNew";

        ST st = java_utils.builder
            .makeST(templateName, loopVarName, start.template, end.template, body)
            .orElseThrow();

        return new CompilerReturn(st, null);
    }

    @Override
    public CompilerReturn visitForNorm(SafelangParser.ForNormContext ctx) {
        CompilerReturn start = visit(ctx.number(0));
        CompilerReturn end = visit(ctx.number(1));

        List<ST> body = visitStats(ctx.commonStat());

        ST st = java_utils.builder
            .makeST("loopForNorm", start.template, end.template, body)
            .orElseThrow();

        return new CompilerReturn(st, null);
    }


    //      ASSERT LOGIC


    @Override
    public CompilerReturn visitAssert(SafelangParser.AssertContext ctx) {
        CompilerReturn predicate = visit(ctx.booleans());

        ST st = java_utils.builder
            .makeST("assertStmt", predicate.template, ctx.getStart().getLine())
            .orElseThrow();

        return new CompilerReturn(st, null);
    }


    //          TRY LOGIC

    @Override public CompilerReturn visitTryNorm(SafelangParser.TryNormContext ctx) {
        List<SafelangParser.CommonStatContext> stat = ctx.commonStat();

        if(stat == null) stat = new ArrayList<>();

        List<ST> tryBody  = visitStats(stat);
        ST st = java_utils.builder
                .makeST("tryNorm", tryBody)
                .orElseThrow();

        return new CompilerReturn(st);
    }

    @Override public CompilerReturn visitTryRescue(SafelangParser.TryRescueContext ctx) {
        SafelangParser.RescueContext rescue = ctx.rescue();
        List<SafelangParser.CommonStatContext> rescueStats;
        List<SafelangParser.CommonStatContext> tryStats = ctx.commonStat();

        if (tryStats == null)
            tryStats = new ArrayList<>();

        boolean need_rescue = false;

        if (rescue instanceof SafelangParser.RescueRetryContext retryCtx) {
            rescueStats = retryCtx.commonStat();
            need_rescue = true;
        }
        else if (rescue instanceof SafelangParser.RescueNormContext normCtx)
            rescueStats = normCtx.commonStat();
        else
            rescueStats = new ArrayList<>();

        ST st;
        List<ST> tryBody     = visitStats(tryStats);
        List<ST> rescueBody  = visitStats(rescueStats);

        if(need_rescue) {
            st = java_utils.builder
                    .makeST("tryRescueRetry", counter++, tryBody, rescueBody)
                    .orElseThrow();
        } else {
            st = java_utils.builder
                    .makeST("tryRescueNorm", tryBody, rescueBody)
                    .orElseThrow();
        }

        return new CompilerReturn(st);
    }

    @Override public CompilerReturn visitRescueNorm(SafelangParser.RescueNormContext ctx) { return null; }

    @Override public CompilerReturn visitRescueRetry(SafelangParser.RescueRetryContext ctx) { return null; }

    @Override
    public CompilerReturn visitStatFail(SafelangParser.StatFailContext ctx) {
        return visit(ctx.fail());
    }

    @Override
    public CompilerReturn visitCommonStatFail(SafelangParser.CommonStatFailContext ctx) {
        return visit(ctx.fail());
    }

    @Override
    public CompilerReturn visitFail(SafelangParser.FailContext ctx) {
        ST st = java_utils.builder
            .makeST("failStmt", ctx.getStart().getLine())
            .orElseThrow();

        return new CompilerReturn(st, null);
    }


    //          WHILE LOGIC

    @Override
    public CompilerReturn visitWhileNorm(SafelangParser.WhileNormContext ctx) {
        CompilerReturn condition = visit(ctx.booleans());
        List<ST> body = visitStats(ctx.commonStat());

        ST st = java_utils.builder
            .makeST("loopWhileNorm", condition.template, body)
            .orElseThrow();

        return new CompilerReturn(st, null);
    }

    @Override
    public CompilerReturn visitWhileUntil(SafelangParser.WhileUntilContext ctx) {
        CompilerReturn condition = visit(ctx.booleans());
        List<ST> body = visitStats(ctx.commonStat());

        ST st = java_utils.builder
            .makeST("loopWhileUntil", condition.template, body)
            .orElseThrow();

        return new CompilerReturn(st, null);
    }



    //      BOOLEAN LOGIC

    @Override
    public CompilerReturn visitBooleanLiteral(SafelangParser.BooleanLiteralContext ctx) {
        ST st = java_utils.makeRawST(ctx.getText());
        return new CompilerReturn(st, TypeDescriptor.BOOL);
    }

    @Override
    public CompilerReturn visitBooleanListRetrieveElement(SafelangParser.BooleanListRetrieveElementContext ctx) {
        String listName = ctx.ID(0).getText();

        CompilerReturn indexRet;

        if (ctx.IntegerLiteral() != null) {
            ST index = java_utils.builder
                .makeST("integerLiteral", ctx.IntegerLiteral().getText())
                .orElseThrow();

            indexRet = new CompilerReturn(index, TypeDescriptor.INTEGER);
        } else {
            String indexName = ctx.ID(1).getText();
            TypeDescriptor indexType = java_utils.symbolTable.lookup(indexName);

            indexRet = new CompilerReturn(java_utils.makeRawST(indexName), indexType);
        }

        return compileListGet(listName, indexRet);
    }

    //      ETC

    @Override
    public CompilerReturn visitExprFormatCommandPlacement(SafelangParser.ExprFormatCommandPlacementContext ctx) {
        CompilerReturn expr = visit(ctx.expr());
        CompilerReturn width = visit(ctx.number());

        ST st = java_utils.builder
            .makeST("formatCmdPlacement", expr.template, width.template, java_utils.makeRawST(ctx.op.getText()))
            .orElseThrow();

        return new CompilerReturn(st, TypeDescriptor.STRING);
    }

    @Override
    public CompilerReturn visitConvertToType(SafelangParser.ConvertToTypeContext ctx) {
        String typeName = ctx.ID().getText();
        TypeDescriptor targetType = java_utils.typeSystem.getBaseType(typeName);

        CompilerReturn exprRet = visit(ctx.expr());
        ST coerced = java_utils.coerceExpr(exprRet.template, exprRet.type, targetType);
        coerced = java_utils.enforceBitRange(coerced, targetType);

        return new CompilerReturn(coerced, targetType);
    }

    @Override
    public CompilerReturn visitTypeByteRange(SafelangParser.TypeByteRangeContext ctx) {
        String typeName  = ctx.ID().getText();
        String baseToken = ctx.getChild(3).getText();
        String size      = ctx.IntegerLiteral().getText();

        ST comment = java_utils.builder
            .makeST("declareType", typeName + " | base: " + baseToken + " | size: " + size)
            .orElseThrow();

        return new CompilerReturn(comment, null);
    }
}
