// Generated from Vector.g4 by ANTLR 4.13.2
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
	 * Visit a parse tree produced by the {@code StatShow}
	 * labeled alternative in {@link VectorParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatShow(VectorParser.StatShowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatAssign}
	 * labeled alternative in {@link VectorParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatAssign(VectorParser.StatAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprVector}
	 * labeled alternative in {@link VectorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprVector(VectorParser.ExprVectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprNumber}
	 * labeled alternative in {@link VectorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNumber(VectorParser.ExprNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprID}
	 * labeled alternative in {@link VectorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprID(VectorParser.ExprIDContext ctx);
	/**
	 * Visit a parse tree produced by {@link VectorParser#vector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVector(VectorParser.VectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link VectorParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(VectorParser.NumberContext ctx);
}