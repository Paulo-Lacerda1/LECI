// Generated from FracLang.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FracLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FracLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FracLangParser#prgram}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrgram(FracLangParser.PrgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link FracLangParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(FracLangParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link FracLangParser#display}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDisplay(FracLangParser.DisplayContext ctx);
	/**
	 * Visit a parse tree produced by {@link FracLangParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(FracLangParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenthesizedExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(FracLangParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReadExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadExpression(FracLangParser.ReadExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(FracLangParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReduceExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReduceExpression(FracLangParser.ReduceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FractionExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFractionExpression(FracLangParser.FractionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(FracLangParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(FracLangParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdentifierExpression}
	 * labeled alternative in {@link FracLangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpression(FracLangParser.IdentifierExpressionContext ctx);
}