// Generated from CalComplex.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalComplexParser}.
 */
public interface CalComplexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalComplexParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CalComplexParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalComplexParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CalComplexParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalComplexParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(CalComplexParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalComplexParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(CalComplexParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalComplexParser#output}.
	 * @param ctx the parse tree
	 */
	void enterOutput(CalComplexParser.OutputContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalComplexParser#output}.
	 * @param ctx the parse tree
	 */
	void exitOutput(CalComplexParser.OutputContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalComplexParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(CalComplexParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalComplexParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(CalComplexParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultDivExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultDivExpr(CalComplexParser.MultDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultDivExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultDivExpr(CalComplexParser.MultDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(CalComplexParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(CalComplexParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComplexExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComplexExpr(CalComplexParser.ComplexExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComplexExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComplexExpr(CalComplexParser.ComplexExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpr(CalComplexParser.NumberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpr(CalComplexParser.NumberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InputExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInputExpr(CalComplexParser.InputExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InputExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInputExpr(CalComplexParser.InputExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParentExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParentExpr(CalComplexParser.ParentExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParentExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParentExpr(CalComplexParser.ParentExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(CalComplexParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(CalComplexParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(CalComplexParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link CalComplexParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(CalComplexParser.AddSubExprContext ctx);
}