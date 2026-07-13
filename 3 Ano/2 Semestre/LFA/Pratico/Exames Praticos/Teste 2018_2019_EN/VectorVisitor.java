// Generated from Vector.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link VectorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface VectorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link VectorParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(VectorParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link VectorParser#statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatment(VectorParser.StatmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link VectorParser#show}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShow(VectorParser.ShowContext ctx);
	/**
	 * Visit a parse tree produced by {@link VectorParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(VectorParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(VectorParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpr(VectorParser.NumberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(VectorParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParentExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentExpr(VectorParser.ParentExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VectorExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVectorExpr(VectorParser.VectorExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(VectorParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSubExpr}
	 * labeled alternative in {@link VectorParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpr(VectorParser.AddSubExprContext ctx);
}