// Generated from BigIntCalc.g4 by ANTLR 4.12.0
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
	 * Enter a parse tree produced by {@link BigIntCalcParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(BigIntCalcParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigIntCalcParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(BigIntCalcParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigIntCalcParser#show}.
	 * @param ctx the parse tree
	 */
	void enterShow(BigIntCalcParser.ShowContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigIntCalcParser#show}.
	 * @param ctx the parse tree
	 */
	void exitShow(BigIntCalcParser.ShowContext ctx);
	/**
	 * Enter a parse tree produced by {@link BigIntCalcParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(BigIntCalcParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BigIntCalcParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(BigIntCalcParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultDivExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultDivExpr(BigIntCalcParser.MultDivExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultDivExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultDivExpr(BigIntCalcParser.MultDivExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(BigIntCalcParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(BigIntCalcParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpr(BigIntCalcParser.NumberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpr(BigIntCalcParser.NumberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParentExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParentExpr(BigIntCalcParser.ParentExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParentExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParentExpr(BigIntCalcParser.ParentExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ModExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterModExpr(BigIntCalcParser.ModExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ModExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitModExpr(BigIntCalcParser.ModExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(BigIntCalcParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(BigIntCalcParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(BigIntCalcParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link BigIntCalcParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(BigIntCalcParser.AddSubExprContext ctx);
}