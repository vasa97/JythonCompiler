package gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link jythonParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface jythonVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link jythonParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(jythonParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#importclass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportclass(jythonParser.ImportclassContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#classDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDec(jythonParser.ClassDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#class_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass_body(jythonParser.Class_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#varDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(jythonParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#arrayDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDec(jythonParser.ArrayDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#methodDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDec(jythonParser.MethodDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#constructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructor(jythonParser.ConstructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(jythonParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(jythonParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatment(jythonParser.StatmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#return_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_statment(jythonParser.Return_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#condition_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition_list(jythonParser.Condition_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#while_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_statment(jythonParser.While_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#if_else_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_else_statment(jythonParser.If_else_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#print_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint_statment(jythonParser.Print_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#for_statment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFor_statment(jythonParser.For_statmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#method_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod_call(jythonParser.Method_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(jythonParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(jythonParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#rightExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightExp(jythonParser.RightExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#leftExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftExp(jythonParser.LeftExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(jythonParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#explist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplist(jythonParser.ExplistContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#assignment_operators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment_operators(jythonParser.Assignment_operatorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#eq_neq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq_neq(jythonParser.Eq_neqContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#relation_operators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation_operators(jythonParser.Relation_operatorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#add_sub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd_sub(jythonParser.Add_subContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#mult_mod_div}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult_mod_div(jythonParser.Mult_mod_divContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(jythonParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link jythonParser#jythonType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJythonType(jythonParser.JythonTypeContext ctx);
}