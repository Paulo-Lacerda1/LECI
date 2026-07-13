// Generated from BigIntCalc.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BigIntCalcParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BigIntCalcVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BigIntCalcParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(BigIntCalcParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigIntCalcParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(BigIntCalcParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigIntCalcParser#show}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow(BigIntCalcParser.ShowContext ctx);
	/**
	 * Visit a parse tree produced by {@link BigIntCalcParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(BigIntCalcParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultDivExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultDivExpr(BigIntCalcParser.MultDivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(BigIntCalcParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpr(BigIntCalcParser.NumberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParentExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentExpr(BigIntCalcParser.ParentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ModExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModExpr(BigIntCalcParser.ModExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(BigIntCalcParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(BigIntCalcParser.AddSubExprContext ctx);
}