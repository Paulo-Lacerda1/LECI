// Generated from StrLang.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link StrLangParser}.
 */
public interface StrLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link StrLangParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(StrLangParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link StrLangParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(StrLangParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link StrLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(StrLangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StrLangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(StrLangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StrLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(StrLangParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link StrLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(StrLangParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link StrLangParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(StrLangParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link StrLangParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(StrLangParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprReplace}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprReplace(StrLangParser.ExprReplaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprReplace}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprReplace(StrLangParser.ExprReplaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprString(StrLangParser.ExprStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprString}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprString(StrLangParser.ExprStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprParent(StrLangParser.ExprParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprParent(StrLangParser.ExprParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprSub}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprSub(StrLangParser.ExprSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprSub}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprSub(StrLangParser.ExprSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInput}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprInput(StrLangParser.ExprInputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInput}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprInput(StrLangParser.ExprInputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprTrim}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprTrim(StrLangParser.ExprTrimContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprTrim}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprTrim(StrLangParser.ExprTrimContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprID(StrLangParser.ExprIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprID(StrLangParser.ExprIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAdd}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprAdd(StrLangParser.ExprAddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAdd}
	 * labeled alternative in {@link StrLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprAdd(StrLangParser.ExprAddContext ctx);
}