// Generated from BigIntCalc.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BigIntCalcParser}.
 */
public interface BigIntCalcListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BigIntCalcParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(BigIntCalcParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigIntCalcParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(BigIntCalcParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatShow}
	 * labeled alternative in {@link BigIntCalcParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatShow(BigIntCalcParser.StatShowContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatShow}
	 * labeled alternative in {@link BigIntCalcParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatShow(BigIntCalcParser.StatShowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link BigIntCalcParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatAssign(BigIntCalcParser.StatAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link BigIntCalcParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatAssign(BigIntCalcParser.StatAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMulDivMod}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMulDivMod(BigIntCalcParser.ExprMulDivModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMulDivMod}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMulDivMod(BigIntCalcParser.ExprMulDivModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(BigIntCalcParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(BigIntCalcParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParent(BigIntCalcParser.ExprParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParent(BigIntCalcParser.ExprParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprUnary(BigIntCalcParser.ExprUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprUnary}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprUnary(BigIntCalcParser.ExprUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprInt(BigIntCalcParser.ExprIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprInt}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprInt(BigIntCalcParser.ExprIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprId(BigIntCalcParser.ExprIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprId}
	 * labeled alternative in {@link BigIntCalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprId(BigIntCalcParser.ExprIdContext ctx);
}