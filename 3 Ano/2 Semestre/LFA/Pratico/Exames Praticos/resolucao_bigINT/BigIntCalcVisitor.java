// Generated from BigIntCalc.g4 by ANTLR 4.13.2
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
	 * Visit a parse tree produced by the {@code StatShow}
	 * labeled alternative in {@link BigIntCalcParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatShow(BigIntCalcParser.StatShowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link BigIntCalcParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatAssign(BigIntCalcParser.StatAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMulDivMod}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMulDivMod(BigIntCalcParser.ExprMulDivModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddSub(BigIntCalcParser.ExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParent(BigIntCalcParser.ExprParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUnary(BigIntCalcParser.ExprUnaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInt(BigIntCalcParser.ExprIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprId(BigIntCalcParser.ExprIdContext ctx);
}