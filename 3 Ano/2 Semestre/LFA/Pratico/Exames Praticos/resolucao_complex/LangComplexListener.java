// Generated from LangComplex.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LangComplexParser}.
 */
public interface LangComplexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LangComplexParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(LangComplexParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangComplexParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(LangComplexParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatDisplay}
	 * labeled alternative in {@link LangComplexParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatDisplay(LangComplexParser.StatDisplayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatDisplay}
	 * labeled alternative in {@link LangComplexParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatDisplay(LangComplexParser.StatDisplayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link LangComplexParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatAssign(LangComplexParser.StatAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link LangComplexParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatAssign(LangComplexParser.StatAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link LangComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(LangComplexParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAddSub}
	 * labeled alternative in {@link LangComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(LangComplexParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprAtom}
	 * labeled alternative in {@link LangComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAtom(LangComplexParser.ExprAtomContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprAtom}
	 * labeled alternative in {@link LangComplexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAtom(LangComplexParser.ExprAtomContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomImagNumber}
	 * labeled alternative in {@link LangComplexParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomImagNumber(LangComplexParser.AtomImagNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomImagNumber}
	 * labeled alternative in {@link LangComplexParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomImagNumber(LangComplexParser.AtomImagNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomImagUnit}
	 * labeled alternative in {@link LangComplexParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomImagUnit(LangComplexParser.AtomImagUnitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomImagUnit}
	 * labeled alternative in {@link LangComplexParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomImagUnit(LangComplexParser.AtomImagUnitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomReal}
	 * labeled alternative in {@link LangComplexParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomReal(LangComplexParser.AtomRealContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomReal}
	 * labeled alternative in {@link LangComplexParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomReal(LangComplexParser.AtomRealContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AtomID}
	 * labeled alternative in {@link LangComplexParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtomID(LangComplexParser.AtomIDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AtomID}
	 * labeled alternative in {@link LangComplexParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtomID(LangComplexParser.AtomIDContext ctx);
}