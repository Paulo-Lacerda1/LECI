// Generated from CalComplex.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalComplexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalComplexVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CalComplexParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CalComplexParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalComplexParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(CalComplexParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalComplexParser#output}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutput(CalComplexParser.OutputContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalComplexParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(CalComplexParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultDivExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDivExpr(CalComplexParser.MultDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(CalComplexParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComplexExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexExpr(CalComplexParser.ComplexExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpr(CalComplexParser.NumberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InputExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInputExpr(CalComplexParser.InputExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParentExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentExpr(CalComplexParser.ParentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(CalComplexParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(CalComplexParser.AddSubExprContext ctx);
}