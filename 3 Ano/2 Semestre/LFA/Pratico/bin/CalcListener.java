// Generated from Calc.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalcParser}.
 */
public interface CalcListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalcParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CalcParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CalcParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(CalcParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(CalcParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(CalcParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(CalcParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(CalcParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(CalcParser.ExprContext ctx);
}