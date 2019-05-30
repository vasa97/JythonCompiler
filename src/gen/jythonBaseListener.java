package gen;
import Symbol.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.LinkedList;

import static java.lang.Character.isUpperCase;

/**
 * This class provides an empty implementation of {@link jythonListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */

public class jythonBaseListener implements jythonListener {

	LinkedList<ClassDec> allClasses;
	public String className;
	public int classLine;
	public String parent;
	LinkedList<VarDec> allObjects;
	LinkedList<String> importedClasses;

	public jythonBaseListener(LinkedList<ClassDec> allClassNames,LinkedList<VarDec> allObjects) {
		this.allClasses = allClassNames;
		this.allObjects = allObjects;
		importedClasses = new LinkedList<>();
	}

	// symbol table for global scope
	//SymbolTable current = new SymbolTable("global");
	SymbolTable current ;

	@Override public void enterProgram(jythonParser.ProgramContext ctx) { }


	@Override public void exitProgram(jythonParser.ProgramContext ctx) { }


	@Override public void enterImportclass(jythonParser.ImportclassContext ctx) {

		allObjects.add(new VarDec(ctx.USER_TYPE().getText(),ctx.start.getLine()));
		importedClasses.add(ctx.USER_TYPE().getText());
	}


	@Override public void exitImportclass(jythonParser.ImportclassContext ctx) { }


	@Override public void enterClassDec(jythonParser.ClassDecContext ctx) {

		className = ctx.USER_TYPE(0).getText();
		classLine = ctx.start.getLine();

		if(ctx.USER_TYPE(1).getText().equals("<missing USER_TYPE>"))
			parent = "Object";
		else
			parent = ctx.USER_TYPE(1).getText();

		//Checks duplicate class declarations
		for (ClassDec cd:allClasses) {
			if (cd.getClassName().equals(className)) {
				System.out.println("Error101 : in line " + classLine + ", " + className + " has been defined already");
				break;
			}
		}
		allClasses.add(new ClassDec(className,classLine,parent));

		//Checks if the class is imported
		boolean isImported = false;
		for (String string : importedClasses) {
			if (ctx.USER_TYPE(1).getText().equals(string)) {
				isImported = true;
				break;
			}
		}
//		if(!isImported)
//			System.out.println("Error106 : in line " + ctx.start.getLine() + ", cannot find class " + ctx.USER_TYPE(1).getText());

		SymbolTable classDec = new SymbolTable(ctx.USER_TYPE(0).toString(), null, Type.CLASS);
		current = classDec;
	}


	@Override public void exitClassDec(jythonParser.ClassDecContext ctx) {

		if( current.get("main", Kind.METHOD) == null ) {
			System.out.println("Error104 : in class " + current.getId() + " Can not find main method");
		}
	}


	@Override public void enterClass_body(jythonParser.Class_bodyContext ctx) { }


	@Override public void exitClass_body(jythonParser.Class_bodyContext ctx) { }


	@Override public void enterVarDec(jythonParser.VarDecContext ctx) {

		boolean isImported=false;
		Symbol s = new VariableSymbol(ctx.type().getText(), ctx.ID().getText());
		if(isUpperCase(ctx.type().getText().charAt(0))) {
			for (String string : importedClasses) {
				if (ctx.type().getText().equals(string)) {
					isImported = true;
					break;
				}
			}
			if(!isImported){}
				//System.out.println("Error106 : in line " + ctx.start.getLine() + ", cannot find class " + ctx.type().getText());
			else {
				if (current.lookCurrentScope(s, Kind.VARIABLE))
					if(current.getType() == Type.CLASS)
						System.out.println("Error102 : in line "+ctx.start.getLine()+", "+ctx.ID().getText()+" has been defined already in "+current.getId());
					else
						System.out.println("Error103 : in line "+ctx.start.getLine()+", "+ctx.ID().getText()+" has been defined already in current scope");
				else {
					current.insertVariable(ctx.type().getText(), ctx.ID().getText(), Kind.VARIABLE);
					System.out.println(s.getId() + ": added to table->"+current.getId());
				}
			}
		} else {
			if (current.lookCurrentScope(s, Kind.VARIABLE))
				if(current.getType() == Type.CLASS)
					System.out.println("Error102 : in line "+ctx.start.getLine()+", "+ctx.ID().getText()+" has been defined already in "+current.getId());
				else
				System.out.println("Error103 : in line "+ctx.start.getLine()+", "+ctx.ID().getText()+" has been defined already in current scope");
			else {
				current.insertVariable(ctx.type().getText(), ctx.ID().getText(), Kind.VARIABLE);
				System.out.println(s.getId() + ": added to table ->"+current.getId());
			}
		}
	}


	@Override public void exitVarDec(jythonParser.VarDecContext ctx) { }


	@Override public void enterArrayDec(jythonParser.ArrayDecContext ctx) { }


	@Override public void exitArrayDec(jythonParser.ArrayDecContext ctx) { }


	@Override public void enterMethodDec(jythonParser.MethodDecContext ctx) {

		String type ;
		if (ctx.type() == null) type = "void";
		else type = ctx.type().getText();
		MethodSymbol s = new MethodSymbol(type, ctx.ID().getText(), ctx.parameters(), false);

		if (current.isDefined(s))
			System.out.println("error method :" + ctx.ID());
		else current.insertMethod(type, ctx.ID().getText(), ctx.parameters(), false);

		SymbolTable methodDec = new SymbolTable(ctx.ID().getText(), current, Type.METHOD);
		current = methodDec;
	}


	@Override public void exitMethodDec(jythonParser.MethodDecContext ctx) {
		current = current.getParent();
	}


	@Override public void enterConstructor(jythonParser.ConstructorContext ctx) { }


	@Override public void exitConstructor(jythonParser.ConstructorContext ctx) { }


	@Override public void enterParameter(jythonParser.ParameterContext ctx) { }


	@Override public void exitParameter(jythonParser.ParameterContext ctx) { }


	@Override public void enterParameters(jythonParser.ParametersContext ctx) { }


	@Override public void exitParameters(jythonParser.ParametersContext ctx) { }


	@Override public void enterStatment(jythonParser.StatmentContext ctx) { }


	@Override public void exitStatment(jythonParser.StatmentContext ctx) { }


	@Override public void enterReturn_statment(jythonParser.Return_statmentContext ctx) { }


	@Override public void exitReturn_statment(jythonParser.Return_statmentContext ctx) { }


	@Override public void enterCondition_list(jythonParser.Condition_listContext ctx) { }


	@Override public void exitCondition_list(jythonParser.Condition_listContext ctx) { }


	@Override public void enterWhile_statment(jythonParser.While_statmentContext ctx) { }


	@Override public void exitWhile_statment(jythonParser.While_statmentContext ctx) { }


	@Override public void enterIf_else_statment(jythonParser.If_else_statmentContext ctx) { }


	@Override public void exitIf_else_statment(jythonParser.If_else_statmentContext ctx) { }


	@Override public void enterPrint_statment(jythonParser.Print_statmentContext ctx) { }


	@Override public void exitPrint_statment(jythonParser.Print_statmentContext ctx) { }


	@Override public void enterFor_statment(jythonParser.For_statmentContext ctx) { }


	@Override public void exitFor_statment(jythonParser.For_statmentContext ctx) { }


	@Override public void enterMethod_call(jythonParser.Method_callContext ctx) { }


	@Override public void exitMethod_call(jythonParser.Method_callContext ctx) { }


	@Override public void enterAssignment(jythonParser.AssignmentContext ctx) { }


	@Override public void exitAssignment(jythonParser.AssignmentContext ctx) { }


	@Override public void enterExpression(jythonParser.ExpressionContext ctx) { }


	@Override public void exitExpression(jythonParser.ExpressionContext ctx) { }


	@Override public void enterRightExp(jythonParser.RightExpContext ctx) {
		if(isUpperCase(ctx.getText().charAt(0))) {
			boolean isImported = false;
			for (String string : importedClasses) {
				if (ctx.getText().equals(string)) {
					isImported = true;
					break;
				}
			}
			if(!isImported)
				System.out.println("Error106 : in line " + ctx.start.getLine() + ", cannot find class " + ctx.USER_TYPE().getText());
		}
	}


	@Override public void exitRightExp(jythonParser.RightExpContext ctx) { }


	@Override public void enterLeftExp(jythonParser.LeftExpContext ctx) { }


	@Override public void exitLeftExp(jythonParser.LeftExpContext ctx) { }


	@Override public void enterArgs(jythonParser.ArgsContext ctx) { }


	@Override public void exitArgs(jythonParser.ArgsContext ctx) { }


	@Override public void enterExplist(jythonParser.ExplistContext ctx) { }


	@Override public void exitExplist(jythonParser.ExplistContext ctx) { }


	@Override public void enterAssignment_operators(jythonParser.Assignment_operatorsContext ctx) { }


	@Override public void exitAssignment_operators(jythonParser.Assignment_operatorsContext ctx) { }


	@Override public void enterEq_neq(jythonParser.Eq_neqContext ctx) { }


	@Override public void exitEq_neq(jythonParser.Eq_neqContext ctx) { }


	@Override public void enterRelation_operators(jythonParser.Relation_operatorsContext ctx) { }


	@Override public void exitRelation_operators(jythonParser.Relation_operatorsContext ctx) { }


	@Override public void enterAdd_sub(jythonParser.Add_subContext ctx) { }


	@Override public void exitAdd_sub(jythonParser.Add_subContext ctx) { }


	@Override public void enterMult_mod_div(jythonParser.Mult_mod_divContext ctx) { }


	@Override public void exitMult_mod_div(jythonParser.Mult_mod_divContext ctx) { }


	@Override public void enterType(jythonParser.TypeContext ctx) { }


	@Override public void exitType(jythonParser.TypeContext ctx) { }


	@Override public void enterJythonType(jythonParser.JythonTypeContext ctx) { }


	@Override public void exitJythonType(jythonParser.JythonTypeContext ctx) { }


	@Override public void enterEveryRule(ParserRuleContext ctx) { }


	@Override public void exitEveryRule(ParserRuleContext ctx) { }


	@Override public void visitTerminal(TerminalNode node) { }


	@Override public void visitErrorNode(ErrorNode node) { }
}