// Generated from FracLang.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FracLangParser}.
 */
public interface FracLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FracLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(FracLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link FracLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(FracLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatDisplay}
	 * labeled alternative in {@link FracLangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatDisplay(FracLangParser.StatDisplayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatDisplay}
	 * labeled alternative in {@link FracLangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatDisplay(FracLangParser.StatDisplayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link FracLangParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatAssign(FracLangParser.StatAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link FracLangParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatAssign(FracLangParser.StatAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprFrac}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprFrac(FracLangParser.ExprFracContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprFrac}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprFrac(FracLangParser.ExprFracContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprInt(FracLangParser.ExprIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprInt(FracLangParser.ExprIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprID(FracLangParser.ExprIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link FracLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprID(FracLangParser.ExprIDContext ctx);
}