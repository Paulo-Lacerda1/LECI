// Generated from /home/paulo/Desktop/LFA_projeto/safelang-lfa-p4g1/src/Safelang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SafelangParser}.
 */
public interface SafelangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SafelangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SafelangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SafelangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SafelangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatAssign(SafelangParser.StatAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatAssign(SafelangParser.StatAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatWrite}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatWrite(SafelangParser.StatWriteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatWrite}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatWrite(SafelangParser.StatWriteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatExpr}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatExpr(SafelangParser.StatExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatExpr}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatExpr(SafelangParser.StatExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatType}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatType(SafelangParser.StatTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatType}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatType(SafelangParser.StatTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatIf}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatIf(SafelangParser.StatIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatIf}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatIf(SafelangParser.StatIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatTry}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatTry(SafelangParser.StatTryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatTry}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatTry(SafelangParser.StatTryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatFor}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatFor(SafelangParser.StatForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatFor}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatFor(SafelangParser.StatForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatWhile}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatWhile(SafelangParser.StatWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatWhile}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatWhile(SafelangParser.StatWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatAssert}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatAssert(SafelangParser.StatAssertContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatAssert}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatAssert(SafelangParser.StatAssertContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatListAdd}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatListAdd(SafelangParser.StatListAddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatListAdd}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatListAdd(SafelangParser.StatListAddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatFail}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatFail(SafelangParser.StatFailContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatFail}
	 * labeled alternative in {@link SafelangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatFail(SafelangParser.StatFailContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CommonStatAssign}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void enterCommonStatAssign(SafelangParser.CommonStatAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CommonStatAssign}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void exitCommonStatAssign(SafelangParser.CommonStatAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CommonStatWrite}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void enterCommonStatWrite(SafelangParser.CommonStatWriteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CommonStatWrite}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void exitCommonStatWrite(SafelangParser.CommonStatWriteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CommonStatExpr}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void enterCommonStatExpr(SafelangParser.CommonStatExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CommonStatExpr}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void exitCommonStatExpr(SafelangParser.CommonStatExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CommonStatIf}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void enterCommonStatIf(SafelangParser.CommonStatIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CommonStatIf}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void exitCommonStatIf(SafelangParser.CommonStatIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CommonStatTry}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void enterCommonStatTry(SafelangParser.CommonStatTryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CommonStatTry}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void exitCommonStatTry(SafelangParser.CommonStatTryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CommonStatFor}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void enterCommonStatFor(SafelangParser.CommonStatForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CommonStatFor}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void exitCommonStatFor(SafelangParser.CommonStatForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CommonStatWhile}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void enterCommonStatWhile(SafelangParser.CommonStatWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CommonStatWhile}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void exitCommonStatWhile(SafelangParser.CommonStatWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CommonStatAssert}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void enterCommonStatAssert(SafelangParser.CommonStatAssertContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CommonStatAssert}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void exitCommonStatAssert(SafelangParser.CommonStatAssertContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CommonStatListAdd}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void enterCommonStatListAdd(SafelangParser.CommonStatListAddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CommonStatListAdd}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void exitCommonStatListAdd(SafelangParser.CommonStatListAddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CommonStatFail}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void enterCommonStatFail(SafelangParser.CommonStatFailContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CommonStatFail}
	 * labeled alternative in {@link SafelangParser#commonStat}.
	 * @param ctx the parse tree
	 */
	void exitCommonStatFail(SafelangParser.CommonStatFailContext ctx);
	/**
	 * Enter a parse tree produced by {@link SafelangParser#fail}.
	 * @param ctx the parse tree
	 */
	void enterFail(SafelangParser.FailContext ctx);
	/**
	 * Exit a parse tree produced by {@link SafelangParser#fail}.
	 * @param ctx the parse tree
	 */
	void exitFail(SafelangParser.FailContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WriteExpr}
	 * labeled alternative in {@link SafelangParser#write}.
	 * @param ctx the parse tree
	 */
	void enterWriteExpr(SafelangParser.WriteExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WriteExpr}
	 * labeled alternative in {@link SafelangParser#write}.
	 * @param ctx the parse tree
	 */
	void exitWriteExpr(SafelangParser.WriteExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WriteLnExpr}
	 * labeled alternative in {@link SafelangParser#write}.
	 * @param ctx the parse tree
	 */
	void enterWriteLnExpr(SafelangParser.WriteLnExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WriteLnExpr}
	 * labeled alternative in {@link SafelangParser#write}.
	 * @param ctx the parse tree
	 */
	void exitWriteLnExpr(SafelangParser.WriteLnExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignValType}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignValType(SafelangParser.AssignValTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignValType}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignValType(SafelangParser.AssignValTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignVal}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignVal(SafelangParser.AssignValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignVal}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignVal(SafelangParser.AssignValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignTryVal}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignTryVal(SafelangParser.AssignTryValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignTryVal}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignTryVal(SafelangParser.AssignTryValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignTryValType}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignTryValType(SafelangParser.AssignTryValTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignTryValType}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignTryValType(SafelangParser.AssignTryValTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignType}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignType(SafelangParser.AssignTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignType}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignType(SafelangParser.AssignTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignListValType}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignListValType(SafelangParser.AssignListValTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignListValType}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignListValType(SafelangParser.AssignListValTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignListType}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignListType(SafelangParser.AssignListTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignListType}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignListType(SafelangParser.AssignListTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignListVal}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssignListVal(SafelangParser.AssignListValContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignListVal}
	 * labeled alternative in {@link SafelangParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssignListVal(SafelangParser.AssignListValContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeUnit}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeUnit(SafelangParser.TypeUnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeUnit}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeUnit(SafelangParser.TypeUnitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeUnitSuffix}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeUnitSuffix(SafelangParser.TypeUnitSuffixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeUnitSuffix}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeUnitSuffix(SafelangParser.TypeUnitSuffixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDependent}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeDependent(SafelangParser.TypeDependentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDependent}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeDependent(SafelangParser.TypeDependentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDependentUnit}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeDependentUnit(SafelangParser.TypeDependentUnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDependentUnit}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeDependentUnit(SafelangParser.TypeDependentUnitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDependentUnitSuffix}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeDependentUnitSuffix(SafelangParser.TypeDependentUnitSuffixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDependentUnitSuffix}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeDependentUnitSuffix(SafelangParser.TypeDependentUnitSuffixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeByteRange}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterTypeByteRange(SafelangParser.TypeByteRangeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeByteRange}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitTypeByteRange(SafelangParser.TypeByteRangeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DimensionUnit}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterDimensionUnit(SafelangParser.DimensionUnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DimensionUnit}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitDimensionUnit(SafelangParser.DimensionUnitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DimensionUnitSuffix}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterDimensionUnitSuffix(SafelangParser.DimensionUnitSuffixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DimensionUnitSuffix}
	 * labeled alternative in {@link SafelangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitDimensionUnitSuffix(SafelangParser.DimensionUnitSuffixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfElse}
	 * labeled alternative in {@link SafelangParser#if}.
	 * @param ctx the parse tree
	 */
	void enterIfElse(SafelangParser.IfElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfElse}
	 * labeled alternative in {@link SafelangParser#if}.
	 * @param ctx the parse tree
	 */
	void exitIfElse(SafelangParser.IfElseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfEnd}
	 * labeled alternative in {@link SafelangParser#if}.
	 * @param ctx the parse tree
	 */
	void enterIfEnd(SafelangParser.IfEndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfEnd}
	 * labeled alternative in {@link SafelangParser#if}.
	 * @param ctx the parse tree
	 */
	void exitIfEnd(SafelangParser.IfEndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ElseIf}
	 * labeled alternative in {@link SafelangParser#else}.
	 * @param ctx the parse tree
	 */
	void enterElseIf(SafelangParser.ElseIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ElseIf}
	 * labeled alternative in {@link SafelangParser#else}.
	 * @param ctx the parse tree
	 */
	void exitElseIf(SafelangParser.ElseIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ElseNorm}
	 * labeled alternative in {@link SafelangParser#else}.
	 * @param ctx the parse tree
	 */
	void enterElseNorm(SafelangParser.ElseNormContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ElseNorm}
	 * labeled alternative in {@link SafelangParser#else}.
	 * @param ctx the parse tree
	 */
	void exitElseNorm(SafelangParser.ElseNormContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TryNorm}
	 * labeled alternative in {@link SafelangParser#try}.
	 * @param ctx the parse tree
	 */
	void enterTryNorm(SafelangParser.TryNormContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TryNorm}
	 * labeled alternative in {@link SafelangParser#try}.
	 * @param ctx the parse tree
	 */
	void exitTryNorm(SafelangParser.TryNormContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TryRescue}
	 * labeled alternative in {@link SafelangParser#try}.
	 * @param ctx the parse tree
	 */
	void enterTryRescue(SafelangParser.TryRescueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TryRescue}
	 * labeled alternative in {@link SafelangParser#try}.
	 * @param ctx the parse tree
	 */
	void exitTryRescue(SafelangParser.TryRescueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RescueNorm}
	 * labeled alternative in {@link SafelangParser#rescue}.
	 * @param ctx the parse tree
	 */
	void enterRescueNorm(SafelangParser.RescueNormContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RescueNorm}
	 * labeled alternative in {@link SafelangParser#rescue}.
	 * @param ctx the parse tree
	 */
	void exitRescueNorm(SafelangParser.RescueNormContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RescueRetry}
	 * labeled alternative in {@link SafelangParser#rescue}.
	 * @param ctx the parse tree
	 */
	void enterRescueRetry(SafelangParser.RescueRetryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RescueRetry}
	 * labeled alternative in {@link SafelangParser#rescue}.
	 * @param ctx the parse tree
	 */
	void exitRescueRetry(SafelangParser.RescueRetryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForAssign}
	 * labeled alternative in {@link SafelangParser#for}.
	 * @param ctx the parse tree
	 */
	void enterForAssign(SafelangParser.ForAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForAssign}
	 * labeled alternative in {@link SafelangParser#for}.
	 * @param ctx the parse tree
	 */
	void exitForAssign(SafelangParser.ForAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForNorm}
	 * labeled alternative in {@link SafelangParser#for}.
	 * @param ctx the parse tree
	 */
	void enterForNorm(SafelangParser.ForNormContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForNorm}
	 * labeled alternative in {@link SafelangParser#for}.
	 * @param ctx the parse tree
	 */
	void exitForNorm(SafelangParser.ForNormContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileNorm}
	 * labeled alternative in {@link SafelangParser#while}.
	 * @param ctx the parse tree
	 */
	void enterWhileNorm(SafelangParser.WhileNormContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileNorm}
	 * labeled alternative in {@link SafelangParser#while}.
	 * @param ctx the parse tree
	 */
	void exitWhileNorm(SafelangParser.WhileNormContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileUntil}
	 * labeled alternative in {@link SafelangParser#while}.
	 * @param ctx the parse tree
	 */
	void enterWhileUntil(SafelangParser.WhileUntilContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileUntil}
	 * labeled alternative in {@link SafelangParser#while}.
	 * @param ctx the parse tree
	 */
	void exitWhileUntil(SafelangParser.WhileUntilContext ctx);
	/**
	 * Enter a parse tree produced by {@link SafelangParser#assert}.
	 * @param ctx the parse tree
	 */
	void enterAssert(SafelangParser.AssertContext ctx);
	/**
	 * Exit a parse tree produced by {@link SafelangParser#assert}.
	 * @param ctx the parse tree
	 */
	void exitAssert(SafelangParser.AssertContext ctx);
	/**
	 * Enter a parse tree produced by {@link SafelangParser#listadd}.
	 * @param ctx the parse tree
	 */
	void enterListadd(SafelangParser.ListaddContext ctx);
	/**
	 * Exit a parse tree produced by {@link SafelangParser#listadd}.
	 * @param ctx the parse tree
	 */
	void exitListadd(SafelangParser.ListaddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDefID}
	 * labeled alternative in {@link SafelangParser#type_def_expr}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefID(SafelangParser.TypeDefIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDefID}
	 * labeled alternative in {@link SafelangParser#type_def_expr}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefID(SafelangParser.TypeDefIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeDefExpr}
	 * labeled alternative in {@link SafelangParser#type_def_expr}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefExpr(SafelangParser.TypeDefExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeDefExpr}
	 * labeled alternative in {@link SafelangParser#type_def_expr}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefExpr(SafelangParser.TypeDefExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringConcat}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringConcat(SafelangParser.StringConcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringConcat}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringConcat(SafelangParser.StringConcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprString(SafelangParser.ExprStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprString(SafelangParser.ExprStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprFormatCommand}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprFormatCommand(SafelangParser.ExprFormatCommandContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprFormatCommand}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprFormatCommand(SafelangParser.ExprFormatCommandContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprBoolean}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprBoolean(SafelangParser.ExprBooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprBoolean}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprBoolean(SafelangParser.ExprBooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprListRetrieveElement}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprListRetrieveElement(SafelangParser.ExprListRetrieveElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprListRetrieveElement}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprListRetrieveElement(SafelangParser.ExprListRetrieveElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNumber(SafelangParser.ExprNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNumber(SafelangParser.ExprNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprID(SafelangParser.ExprIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprID(SafelangParser.ExprIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprFormatCommandPlacement}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprFormatCommandPlacement(SafelangParser.ExprFormatCommandPlacementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprFormatCommandPlacement}
	 * labeled alternative in {@link SafelangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprFormatCommandPlacement(SafelangParser.ExprFormatCommandPlacementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link SafelangParser#string}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(SafelangParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link SafelangParser#string}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(SafelangParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConvertToString}
	 * labeled alternative in {@link SafelangParser#string}.
	 * @param ctx the parse tree
	 */
	void enterConvertToString(SafelangParser.ConvertToStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConvertToString}
	 * labeled alternative in {@link SafelangParser#string}.
	 * @param ctx the parse tree
	 */
	void exitConvertToString(SafelangParser.ConvertToStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReadCmd}
	 * labeled alternative in {@link SafelangParser#string}.
	 * @param ctx the parse tree
	 */
	void enterReadCmd(SafelangParser.ReadCmdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReadCmd}
	 * labeled alternative in {@link SafelangParser#string}.
	 * @param ctx the parse tree
	 */
	void exitReadCmd(SafelangParser.ReadCmdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringListRetrieveElement}
	 * labeled alternative in {@link SafelangParser#string}.
	 * @param ctx the parse tree
	 */
	void enterStringListRetrieveElement(SafelangParser.StringListRetrieveElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringListRetrieveElement}
	 * labeled alternative in {@link SafelangParser#string}.
	 * @param ctx the parse tree
	 */
	void exitStringListRetrieveElement(SafelangParser.StringListRetrieveElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringID}
	 * labeled alternative in {@link SafelangParser#string}.
	 * @param ctx the parse tree
	 */
	void enterStringID(SafelangParser.StringIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringID}
	 * labeled alternative in {@link SafelangParser#string}.
	 * @param ctx the parse tree
	 */
	void exitStringID(SafelangParser.StringIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConvertToInt}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterConvertToInt(SafelangParser.ConvertToIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConvertToInt}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitConvertToInt(SafelangParser.ConvertToIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberListLength}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberListLength(SafelangParser.NumberListLengthContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberListLength}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberListLength(SafelangParser.NumberListLengthContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberSuffix}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberSuffix(SafelangParser.NumberSuffixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberSuffix}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberSuffix(SafelangParser.NumberSuffixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConvertToType}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterConvertToType(SafelangParser.ConvertToTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConvertToType}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitConvertToType(SafelangParser.ConvertToTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberScientific}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberScientific(SafelangParser.NumberScientificContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberScientific}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberScientific(SafelangParser.NumberScientificContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberDecimal}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberDecimal(SafelangParser.NumberDecimalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberDecimal}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberDecimal(SafelangParser.NumberDecimalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberID}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberID(SafelangParser.NumberIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberID}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberID(SafelangParser.NumberIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConvertToReal}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterConvertToReal(SafelangParser.ConvertToRealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConvertToReal}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitConvertToReal(SafelangParser.ConvertToRealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberIntLiteral}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberIntLiteral(SafelangParser.NumberIntLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberIntLiteral}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberIntLiteral(SafelangParser.NumberIntLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberUnary}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberUnary(SafelangParser.NumberUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberUnary}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberUnary(SafelangParser.NumberUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberMult}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberMult(SafelangParser.NumberMultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberMult}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberMult(SafelangParser.NumberMultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberListRetrieveElement}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberListRetrieveElement(SafelangParser.NumberListRetrieveElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberListRetrieveElement}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberListRetrieveElement(SafelangParser.NumberListRetrieveElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberQuotModInt}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberQuotModInt(SafelangParser.NumberQuotModIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberQuotModInt}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberQuotModInt(SafelangParser.NumberQuotModIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberParent}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberParent(SafelangParser.NumberParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberParent}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberParent(SafelangParser.NumberParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberAddSub}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberAddSub(SafelangParser.NumberAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberAddSub}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberAddSub(SafelangParser.NumberAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberDivReal}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumberDivReal(SafelangParser.NumberDivRealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberDivReal}
	 * labeled alternative in {@link SafelangParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumberDivReal(SafelangParser.NumberDivRealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanID}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanID(SafelangParser.BooleanIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanID}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanID(SafelangParser.BooleanIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanNumber}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanNumber(SafelangParser.BooleanNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanNumber}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanNumber(SafelangParser.BooleanNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanLesser}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLesser(SafelangParser.BooleanLesserContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanLesser}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLesser(SafelangParser.BooleanLesserContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanLiteral}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(SafelangParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanLiteral}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(SafelangParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanOr}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanOr(SafelangParser.BooleanOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanOr}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanOr(SafelangParser.BooleanOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanNot}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanNot(SafelangParser.BooleanNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanNot}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanNot(SafelangParser.BooleanNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanAnd}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanAnd(SafelangParser.BooleanAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanAnd}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanAnd(SafelangParser.BooleanAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanParent}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanParent(SafelangParser.BooleanParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanParent}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanParent(SafelangParser.BooleanParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanGreaterEqual}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanGreaterEqual(SafelangParser.BooleanGreaterEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanGreaterEqual}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanGreaterEqual(SafelangParser.BooleanGreaterEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanString}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanString(SafelangParser.BooleanStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanString}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanString(SafelangParser.BooleanStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanLesserEqual}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLesserEqual(SafelangParser.BooleanLesserEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanLesserEqual}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLesserEqual(SafelangParser.BooleanLesserEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanListRetrieveElement}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanListRetrieveElement(SafelangParser.BooleanListRetrieveElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanListRetrieveElement}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanListRetrieveElement(SafelangParser.BooleanListRetrieveElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanGreater}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanGreater(SafelangParser.BooleanGreaterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanGreater}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanGreater(SafelangParser.BooleanGreaterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanEqual}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanEqual(SafelangParser.BooleanEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanEqual}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanEqual(SafelangParser.BooleanEqualContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanNotEqual}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void enterBooleanNotEqual(SafelangParser.BooleanNotEqualContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanNotEqual}
	 * labeled alternative in {@link SafelangParser#booleans}.
	 * @param ctx the parse tree
	 */
	void exitBooleanNotEqual(SafelangParser.BooleanNotEqualContext ctx);
}