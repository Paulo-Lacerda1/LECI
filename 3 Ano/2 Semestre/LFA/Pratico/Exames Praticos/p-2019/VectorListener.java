// Generated from Vector.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link VectorParser}.
 */
public interface VectorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link VectorParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(VectorParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link VectorParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(VectorParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatShow}
	 * labeled alternative in {@link VectorParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatShow(VectorParser.StatShowContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatShow}
	 * labeled alternative in {@link VectorParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatShow(VectorParser.StatShowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link VectorParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatAssign(VectorParser.StatAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link VectorParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatAssign(VectorParser.StatAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprVector}
	 * labeled alternative in {@link VectorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprVector(VectorParser.ExprVectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprVector}
	 * labeled alternative in {@link VectorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprVector(VectorParser.ExprVectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link VectorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNumber(VectorParser.ExprNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link VectorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNumber(VectorParser.ExprNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link VectorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprID(VectorParser.ExprIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link VectorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprID(VectorParser.ExprIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link VectorParser#vector}.
	 * @param ctx the parse tree
	 */
	void enterVector(VectorParser.VectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link VectorParser#vector}.
	 * @param ctx the parse tree
	 */
	void exitVector(VectorParser.VectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link VectorParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(VectorParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link VectorParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(VectorParser.NumberContext ctx);
}