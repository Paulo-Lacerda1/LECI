// Generated from Vector.g4 by ANTLR 4.12.0
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
	 * Enter a parse tree produced by {@link VectorParser#statment}.
	 * @param ctx the parse tree
	 */
	void enterStatment(VectorParser.StatmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link VectorParser#statment}.
	 * @param ctx the parse tree
	 */
	void exitStatment(VectorParser.StatmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link VectorParser#show}.
	 * @param ctx the parse tree
	 */
	void enterShow(VectorParser.ShowContext ctx);
	/**
	 * Exit a parse tree produced by {@link VectorParser#show}.
	 * @param ctx the parse tree
	 */
	void exitShow(VectorParser.ShowContext ctx);
	/**
	 * Enter a parse tree produced by {@link VectorParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(VectorParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link VectorParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(VectorParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(VectorParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(VectorParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpr(VectorParser.NumberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpr(VectorParser.NumberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(VectorParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(VectorParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParentExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParentExpr(VectorParser.ParentExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParentExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParentExpr(VectorParser.ParentExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VectorExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVectorExpr(VectorParser.VectorExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VectorExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVectorExpr(VectorParser.VectorExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(VectorParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(VectorParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpr(VectorParser.AddSubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpr(VectorParser.AddSubExprContext ctx);
}