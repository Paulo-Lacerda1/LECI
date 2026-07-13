// Generated from LangComplex.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LangComplexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LangComplexVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LangComplexParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(LangComplexParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatDisplay}
	 * labeled alternative in {@link LangComplexParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatDisplay(LangComplexParser.StatDisplayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link LangComplexParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatAssign(LangComplexParser.StatAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link LangComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAddSub(LangComplexParser.ExprAddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprAtom}
	 * labeled alternative in {@link LangComplexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAtom(LangComplexParser.ExprAtomContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomImagNumber}
	 * labeled alternative in {@link LangComplexParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomImagNumber(LangComplexParser.AtomImagNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomImagUnit}
	 * labeled alternative in {@link LangComplexParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomImagUnit(LangComplexParser.AtomImagUnitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomReal}
	 * labeled alternative in {@link LangComplexParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomReal(LangComplexParser.AtomRealContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AtomID}
	 * labeled alternative in {@link LangComplexParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomID(LangComplexParser.AtomIDContext ctx);
}