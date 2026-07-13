// Generated from FracLang.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FracLangParser}.
 */
public interface FracLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FracLangParser#prgram}.
	 * @param ctx the parse tree
	 */
	void enterPrgram(FracLangParser.PrgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link FracLangParser#prgram}.
	 * @param ctx the parse tree
	 */
	void exitPrgram(FracLangParser.PrgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link FracLangParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(FracLangParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link FracLangParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(FracLangParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link FracLangParser#display}.
	 * @param ctx the parse tree
	 */
	void enterDisplay(FracLangParser.DisplayContext ctx);
	/**
	 * Exit a parse tree produced by {@link FracLangParser#display}.
	 * @param ctx the parse tree
	 */
	void exitDisplay(FracLangParser.DisplayContext ctx);
	/**
	 * Enter a parse tree produced by {@link FracLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(FracLangParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FracLangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(FracLangParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenthesizedExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(FracLangParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenthesizedExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(FracLangParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReadExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterReadExpression(FracLangParser.ReadExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReadExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitReadExpression(FracLangParser.ReadExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(FracLangParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(FracLangParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReduceExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterReduceExpression(FracLangParser.ReduceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReduceExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitReduceExpression(FracLangParser.ReduceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FractionExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFractionExpression(FracLangParser.FractionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FractionExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFractionExpression(FracLangParser.FractionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(FracLangParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(FracLangParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(FracLangParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(FracLangParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdentifierExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpression(FracLangParser.IdentifierExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdentifierExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpression(FracLangParser.IdentifierExpressionContext ctx);
}