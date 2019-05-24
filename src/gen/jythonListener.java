package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link jythonParser}.
 */
public interface jythonListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link jythonParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(jythonParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(jythonParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#importclass}.
	 * @param ctx the parse tree
	 */
	void enterImportclass(jythonParser.ImportclassContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#importclass}.
	 * @param ctx the parse tree
	 */
	void exitImportclass(jythonParser.ImportclassContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#classDec}.
	 * @param ctx the parse tree
	 */
	void enterClassDec(jythonParser.ClassDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#classDec}.
	 * @param ctx the parse tree
	 */
	void exitClassDec(jythonParser.ClassDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#class_body}.
	 * @param ctx the parse tree
	 */
	void enterClass_body(jythonParser.Class_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#class_body}.
	 * @param ctx the parse tree
	 */
	void exitClass_body(jythonParser.Class_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#varDec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(jythonParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#varDec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(jythonParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#arrayDec}.
	 * @param ctx the parse tree
	 */
	void enterArrayDec(jythonParser.ArrayDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#arrayDec}.
	 * @param ctx the parse tree
	 */
	void exitArrayDec(jythonParser.ArrayDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#methodDec}.
	 * @param ctx the parse tree
	 */
	void enterMethodDec(jythonParser.MethodDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#methodDec}.
	 * @param ctx the parse tree
	 */
	void exitMethodDec(jythonParser.MethodDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#constructor}.
	 * @param ctx the parse tree
	 */
	void enterConstructor(jythonParser.ConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#constructor}.
	 * @param ctx the parse tree
	 */
	void exitConstructor(jythonParser.ConstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(jythonParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(jythonParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(jythonParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(jythonParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#statment}.
	 * @param ctx the parse tree
	 */
	void enterStatment(jythonParser.StatmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#statment}.
	 * @param ctx the parse tree
	 */
	void exitStatment(jythonParser.StatmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#return_statment}.
	 * @param ctx the parse tree
	 */
	void enterReturn_statment(jythonParser.Return_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#return_statment}.
	 * @param ctx the parse tree
	 */
	void exitReturn_statment(jythonParser.Return_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#condition_list}.
	 * @param ctx the parse tree
	 */
	void enterCondition_list(jythonParser.Condition_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#condition_list}.
	 * @param ctx the parse tree
	 */
	void exitCondition_list(jythonParser.Condition_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#while_statment}.
	 * @param ctx the parse tree
	 */
	void enterWhile_statment(jythonParser.While_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#while_statment}.
	 * @param ctx the parse tree
	 */
	void exitWhile_statment(jythonParser.While_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#if_else_statment}.
	 * @param ctx the parse tree
	 */
	void enterIf_else_statment(jythonParser.If_else_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#if_else_statment}.
	 * @param ctx the parse tree
	 */
	void exitIf_else_statment(jythonParser.If_else_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#print_statment}.
	 * @param ctx the parse tree
	 */
	void enterPrint_statment(jythonParser.Print_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#print_statment}.
	 * @param ctx the parse tree
	 */
	void exitPrint_statment(jythonParser.Print_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#for_statment}.
	 * @param ctx the parse tree
	 */
	void enterFor_statment(jythonParser.For_statmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#for_statment}.
	 * @param ctx the parse tree
	 */
	void exitFor_statment(jythonParser.For_statmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#method_call}.
	 * @param ctx the parse tree
	 */
	void enterMethod_call(jythonParser.Method_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#method_call}.
	 * @param ctx the parse tree
	 */
	void exitMethod_call(jythonParser.Method_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(jythonParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(jythonParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(jythonParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(jythonParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#rightExp}.
	 * @param ctx the parse tree
	 */
	void enterRightExp(jythonParser.RightExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#rightExp}.
	 * @param ctx the parse tree
	 */
	void exitRightExp(jythonParser.RightExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#leftExp}.
	 * @param ctx the parse tree
	 */
	void enterLeftExp(jythonParser.LeftExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#leftExp}.
	 * @param ctx the parse tree
	 */
	void exitLeftExp(jythonParser.LeftExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(jythonParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(jythonParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#explist}.
	 * @param ctx the parse tree
	 */
	void enterExplist(jythonParser.ExplistContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#explist}.
	 * @param ctx the parse tree
	 */
	void exitExplist(jythonParser.ExplistContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#assignment_operators}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_operators(jythonParser.Assignment_operatorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#assignment_operators}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_operators(jythonParser.Assignment_operatorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#eq_neq}.
	 * @param ctx the parse tree
	 */
	void enterEq_neq(jythonParser.Eq_neqContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#eq_neq}.
	 * @param ctx the parse tree
	 */
	void exitEq_neq(jythonParser.Eq_neqContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#relation_operators}.
	 * @param ctx the parse tree
	 */
	void enterRelation_operators(jythonParser.Relation_operatorsContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#relation_operators}.
	 * @param ctx the parse tree
	 */
	void exitRelation_operators(jythonParser.Relation_operatorsContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#add_sub}.
	 * @param ctx the parse tree
	 */
	void enterAdd_sub(jythonParser.Add_subContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#add_sub}.
	 * @param ctx the parse tree
	 */
	void exitAdd_sub(jythonParser.Add_subContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#mult_mod_div}.
	 * @param ctx the parse tree
	 */
	void enterMult_mod_div(jythonParser.Mult_mod_divContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#mult_mod_div}.
	 * @param ctx the parse tree
	 */
	void exitMult_mod_div(jythonParser.Mult_mod_divContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(jythonParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(jythonParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link jythonParser#jythonType}.
	 * @param ctx the parse tree
	 */
	void enterJythonType(jythonParser.JythonTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link jythonParser#jythonType}.
	 * @param ctx the parse tree
	 */
	void exitJythonType(jythonParser.JythonTypeContext ctx);
}