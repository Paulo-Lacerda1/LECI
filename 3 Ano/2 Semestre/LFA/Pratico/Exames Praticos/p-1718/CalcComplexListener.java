// Generated from CalcComplex.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalcComplexParser}.
 */
public interface CalcComplexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalcComplexParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CalcComplexParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcComplexParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CalcComplexParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatOutput}
	 * labeled alternative in {@link CalcComplexParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatOutput(CalcComplexParser.StatOutputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatOutput}
	 * labeled alternative in {@link CalcComplexParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatOutput(CalcComplexParser.StatOutputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatAtribuicao}
	 * labeled alternative in {@link CalcComplexParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatAtribuicao(CalcComplexParser.StatAtribuicaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatAtribuicao}
	 * labeled alternative in {@link CalcComplexParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatAtribuicao(CalcComplexParser.StatAtribuicaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(CalcComplexParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(CalcComplexParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprRead}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprRead(CalcComplexParser.ExprReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprRead}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprRead(CalcComplexParser.ExprReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParent(CalcComplexParser.ExprParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParent(CalcComplexParser.ExprParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMultDiv(CalcComplexParser.ExprMultDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMultDiv(CalcComplexParser.ExprMultDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprComplex}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprComplex(CalcComplexParser.ExprComplexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprComplex}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprComplex(CalcComplexParser.ExprComplexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprID(CalcComplexParser.ExprIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprID(CalcComplexParser.ExprIDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComplexRealImag}
	 * labeled alternative in {@link CalcComplexParser#complex}.
	 * @param ctx the parse tree
	 */
	void enterComplexRealImag(CalcComplexParser.ComplexRealImagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComplexRealImag}
	 * labeled alternative in {@link CalcComplexParser#complex}.
	 * @param ctx the parse tree
	 */
	void exitComplexRealImag(CalcComplexParser.ComplexRealImagContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComplexReal}
	 * labeled alternative in {@link CalcComplexParser#complex}.
	 * @param ctx the parse tree
	 */
	void enterComplexReal(CalcComplexParser.ComplexRealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComplexReal}
	 * labeled alternative in {@link CalcComplexParser#complex}.
	 * @param ctx the parse tree
	 */
	void exitComplexReal(CalcComplexParser.ComplexRealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComplexImag}
	 * labeled alternative in {@link CalcComplexParser#complex}.
	 * @param ctx the parse tree
	 */
	void enterComplexImag(CalcComplexParser.ComplexImagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComplexImag}
	 * labeled alternative in {@link CalcComplexParser#complex}.
	 * @param ctx the parse tree
	 */
	void exitComplexImag(CalcComplexParser.ComplexImagContext ctx);
}