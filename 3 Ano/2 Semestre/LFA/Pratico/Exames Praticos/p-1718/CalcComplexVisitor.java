// Generated from CalcComplex.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalcComplexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalcComplexVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CalcComplexParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CalcComplexParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatOutput}
	 * labeled alternative in {@link CalcComplexParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatOutput(CalcComplexParser.StatOutputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatAtribuicao}
	 * labeled alternative in {@link CalcComplexParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatAtribuicao(CalcComplexParser.StatAtribuicaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddSub(CalcComplexParser.ExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprRead}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRead(CalcComplexParser.ExprReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprParent}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParent(CalcComplexParser.ExprParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprMultDiv}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultDiv(CalcComplexParser.ExprMultDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprComplex}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprComplex(CalcComplexParser.ExprComplexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link CalcComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprID(CalcComplexParser.ExprIDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComplexRealImag}
	 * labeled alternative in {@link CalcComplexParser#complex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexRealImag(CalcComplexParser.ComplexRealImagContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComplexReal}
	 * labeled alternative in {@link CalcComplexParser#complex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexReal(CalcComplexParser.ComplexRealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComplexImag}
	 * labeled alternative in {@link CalcComplexParser#complex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexImag(CalcComplexParser.ComplexImagContext ctx);
}