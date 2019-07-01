package gen;

import Symbol.*;
import gen.jythonParser.ParametersContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.LinkedList;

import static gen.jythonParser.*;
import static java.lang.Character.isUpperCase;

/**
 * This class provides an empty implementation of {@link jythonListener},
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */

public class jythonBaseListener implements jythonListener {

	private LinkedList<ClassDec> allClasses;
	private LinkedList<ClassDec> importedClasses;
	private LinkedList<ClassDec> usedClasses;
	private LinkedList<VarDec> usedVariables;
	private LinkedList<MethodDec> usedMethods;
	private LinkedList<MethodDec> usedMethodsInArrayDec;
	private ClassDec thisClass;
	private LinkedList<ReturnType> usedReturnTypes = new LinkedList<>();

	public jythonBaseListener(LinkedList<ClassDec> allClassNames, LinkedList<ClassDec> importedClasses, LinkedList<ClassDec> usedClasses, LinkedList<VarDec> usedVariables, LinkedList<MethodDec> usedMethods, LinkedList<MethodDec> usedMethodsInArrayDec) {
		this.allClasses = allClassNames;
		this.importedClasses = importedClasses;
		this.usedClasses = usedClasses;
		this.usedVariables = usedVariables;
		this.usedMethods = usedMethods;
		this.usedMethodsInArrayDec = usedMethodsInArrayDec;
	}

	// symbol table for global scope
	//SymbolTable current = new SymbolTable("global");
	private SymbolTable current;

	@Override
	public void enterProgram(ProgramContext ctx) {
	}


	@Override
	public void exitProgram(ProgramContext ctx) {
		thisClass.setSymbolTable(current);
		for (ReturnType rt: usedReturnTypes) {
			if (rt.getSymbolTable().lookup(rt.getName(), Kind.VARIABLE)) {
				VariableSymbol vs = (VariableSymbol) rt.getSymbolTable().findSymbol(rt.getName(), Kind.VARIABLE);
				if (!vs.getType().equals(rt.getReturnType()))
					System.out.println("Error 230 : in line " + ctx.start.getLine() + ", ReturnType of this method must be " + rt.getReturnType());
			}
		}
	}

	@Override
	public void enterImportclass(ImportclassContext ctx) {
		importedClasses.add(new ClassDec(ctx.USER_TYPE().getText(), ctx.start.getLine(), null));
	}


	@Override
	public void exitImportclass(ImportclassContext ctx) {
	}


	@Override
	public void enterClassDec(ClassDecContext ctx) {

		String className = ctx.USER_TYPE(0).getText();
		int classLine = ctx.start.getLine();

		String parent;
		if (ctx.USER_TYPE(1) == null)
			parent = "Object";
		else
			parent = ctx.USER_TYPE(1).getText();

		usedClasses.add(new ClassDec(parent, classLine, null));
		//Checks duplicate class declarations
		boolean isDuplicate = false;
		for (ClassDec cd : allClasses) {
			if (cd.getClassName().equals(className)) {
				System.out.println("Error101 : in line " + classLine + ", " + className + " has been defined already");
				isDuplicate = true;
				break;
			}
		}
		if (!isDuplicate) {
			thisClass = new ClassDec(className, classLine, parent);
			allClasses.add(thisClass);
		}

		current = new SymbolTable(ctx.USER_TYPE(0).toString(), null, Type.CLASS);
	}


	@Override
	public void exitClassDec(ClassDecContext ctx) {
	}


	@Override
	public void enterClass_body(Class_bodyContext ctx) {
	}


	@Override
	public void exitClass_body(Class_bodyContext ctx) {
	}


	@Override
	public void enterVarDec(VarDecContext ctx) {

		boolean isImported = false;
		Symbol s = new VariableSymbol(ctx.type().getText(), ctx.ID().getText());
		if (isUpperCase(ctx.type().getText().charAt(0))) {
			for (ClassDec cd : importedClasses) {
				if (ctx.type().getText().equals(cd.getClassName())) {
					isImported = true;
					ClassDec c = new ClassDec(ctx.type().USER_TYPE().getText(), ctx.start.getLine(), null);
					usedClasses.add(c);
					break;
				}
			}
			if (!isImported)
				System.out.println("Error106 : in line " + ctx.start.getLine() + ", cannot find class " + ctx.type().getText());
			else {
				if (current.lookCurrentScope(s.getId(), Kind.VARIABLE))
					if (current.getType() == Type.CLASS)
						System.out.println("Error102 : in line " + ctx.start.getLine() + ", " + ctx.ID().getText() + " has been defined already in " + current.getId());
					else
						System.out.println("Error103 : in line " + ctx.start.getLine() + ", " + ctx.ID().getText() + " has been defined already in current scope");
				else {
					current.insertVariable(ctx.type().getText(), ctx.ID().getText(), Kind.VARIABLE);
					System.out.println(s.getId() + ": added to table->" + current.getId());
				}
			}
		} else {
			if (current.lookCurrentScope(s.getId(), Kind.VARIABLE))
				if (current.getType() == Type.CLASS)
					System.out.println("Error102 : in line " + ctx.start.getLine() + ", " + ctx.ID().getText() + " has been defined already in " + current.getId());
				else
					System.out.println("Error103 : in line " + ctx.start.getLine() + ", " + ctx.ID().getText() + " has been defined already in current scope");
			else {
				current.insertVariable(ctx.type().getText(), ctx.ID().getText(), Kind.VARIABLE);
				System.out.println(s.getId() + ": added to table ->" + current.getId());
			}
		}
	}


	@Override
	public void exitVarDec(VarDecContext ctx) {
	}


	@Override
	public void enterArrayDec(ArrayDecContext ctx) {
		if (ctx.expression().rightExp() != null)
			if (ctx.expression().rightExp().INTEGER() == null) {
				if (ctx.expression().rightExp().leftExp() == null)
					System.out.println("Error 210 : in line " + ctx.start.getLine() + ", Size of an array must be of type integer");
				else if (ctx.expression().rightExp().leftExp().ID() != null) {
					String id = ctx.expression().rightExp().leftExp().ID().getText();
					if (current.lookup(id, Kind.VARIABLE)) {
						if (!((VariableSymbol) current.get(id, Kind.VARIABLE)).getType().equals("int"))
							System.out.println("Error 210 : in line " + ctx.start.getLine() + ", Size of an array must be of type integer");
					} else usedMethodsInArrayDec.add(new MethodDec(ctx.start.getLine(), id, thisClass.getClassName()));
				}
			}
	}


	@Override
	public void exitArrayDec(ArrayDecContext ctx) {
	}


	@Override
	public void enterMethodDec(MethodDecContext ctx) {

		int num;
		ArrayList<String> params = new ArrayList<>();

		if (ctx.parameters().size() != 0) {

			num = ctx.parameters(0).parameter().size();

			for (int i = 0; i < num; i++) {
				params.add(ctx.parameters(0).parameter(i).varDec().type().getText());
			}

		}

		String type;
		if (ctx.type() == null) type = "void";
		else type = ctx.type().getText();

		MethodSymbol s = new MethodSymbol(type, ctx.ID().getText(), params);

		if (current.isDefined(s) == 1)
			System.out.println("Error102 : in line " + ctx.start.getLine() + ", method " + ctx.ID() + " has been defined already in " + current.getId());
		else if (current.isDefined(s) == 0) current.insertMethod(type, ctx.ID().getText(), params);
		//remaining objectives: handling isDefined() == 2
		current = new SymbolTable(ctx.ID().getText(), current, Type.METHOD);
		for (int i = 0; i < ctx.statment().size(); i++) {
			if (ctx.statment(i).return_statment() != null)
				if (ctx.statment(i).return_statment().expression().rightExp() != null) {
					if (ctx.statment(i).return_statment().expression().rightExp().leftExp() == null) {
						if (ctx.statment(i).return_statment().expression().rightExp().BOOL() != null && !ctx.type().getText().equals("boolean"))
							System.out.println("Error 230 : in line " + ctx.start.getLine() + ", ReturnType of this method must be " + ctx.type().getText());
						else if (ctx.statment(i).return_statment().expression().rightExp().INTEGER() != null && !s.getReturnType().equals("int"))
							System.out.println("Error 230 : in line " + ctx.start.getLine() + ", ReturnType of this method must be " + ctx.type().getText());
						else if (ctx.statment(i).return_statment().expression().rightExp().FLOAT() != null && !ctx.type().getText().equals("float"))
							System.out.println("Error 230 : in line " + ctx.start.getLine() + ", ReturnType of this method must be " + ctx.type().getText());
						else if (ctx.statment(i).return_statment().expression().rightExp().STRING() != null && !ctx.type().getText().equals("string"))
							System.out.println("Error 230 : in line " + ctx.start.getLine() + ", ReturnType of this method must be " + ctx.type().getText());
					}
					else if (ctx.statment(i).return_statment().expression().rightExp().leftExp().ID()!=null) {
						usedReturnTypes.add(new ReturnType(ctx.start.getLine(),ctx.statment(i).return_statment().expression().rightExp().leftExp().ID().getText(),current,ctx.type().getText()));
					}
				}
		}
	}


	@Override
	public void exitMethodDec(MethodDecContext ctx) {
		current = current.getParent();
	}


	@Override
	public void enterConstructor(ConstructorContext ctx) {
		int num;
		ArrayList<String> params = new ArrayList<>();

		if (ctx.parameters().size() != 0) {

			//System.out.println(ctx.parameters(0).parameter().size());
			num = ctx.parameters(0).parameter().size();

			for (int i = 0; i < num; i++) {
				params.add(ctx.parameters(0).parameter(i).varDec().type().getText());
			}
		}

		ConstructorSymbol s = new ConstructorSymbol(ctx.USER_TYPE().getText(), params);

		if (current.isDefined(s) == 1)
			System.out.println("Error102 : in line " + ctx.start.getLine() + ", constructor " + ctx.USER_TYPE().getText() + " has been defined already in " + current.getId());
		else {
			current.insertCostructor(ctx.USER_TYPE().getText(), params);
			System.out.println(s.getId() + ": added to table->" + current.getId());
		}

		current = new SymbolTable(ctx.USER_TYPE().getText(), current, Type.Constructor);
	}


	@Override
	public void exitConstructor(ConstructorContext ctx) {
		current = current.getParent();
	}


	@Override
	public void enterParameter(ParameterContext ctx) {
	}


	@Override
	public void exitParameter(ParameterContext ctx) {
	}


	@Override
	public void enterParameters(ParametersContext ctx) {
	}


	@Override
	public void exitParameters(ParametersContext ctx) {
	}


	@Override
	public void enterStatment(StatmentContext ctx) {
	}


	@Override
	public void exitStatment(StatmentContext ctx) {
	}


	@Override
	public void enterReturn_statment(Return_statmentContext ctx) {
	}


	@Override
	public void exitReturn_statment(Return_statmentContext ctx) {
	}


	@Override
	public void enterCondition_list(Condition_listContext ctx) {
	}


	@Override
	public void exitCondition_list(Condition_listContext ctx) {
	}


	@Override
	public void enterWhile_statment(While_statmentContext ctx) {


		if (ctx.condition_list().expression().size() == 1) {

			if (ctx.condition_list().expression(0).add_sub() != null) {
				System.out.println("Error 220 : in line " + ctx.start.getLine() + " , Condition type must be Boolean.");
			} else if (ctx.condition_list().expression(0).rightExp().INTEGER() != null) {
				System.out.println("Error 220 : in line " + ctx.start.getLine() + " , Condition type must be Boolean.");
			} else if (current.lookup(ctx.condition_list().expression(0).rightExp().leftExp().ID().getText(), Kind.METHOD)) {
				if (!((MethodSymbol) current.findSymbol(ctx.condition_list().expression(0).rightExp().leftExp().ID().getText(), Kind.METHOD)).getReturnType().equals("boolean")) {
					System.out.println("Error 220 : in line " + ctx.start.getLine() + " , Condition type must be Boolean.");
				}
			} else if (!(ctx.condition_list().expression(0).rightExp().leftExp().ID().equals("false") || ctx.condition_list().expression(0).rightExp().leftExp().ID().equals("false"))) {
				System.out.println("Error 220 : in line " + ctx.start.getLine() + " , Condition type must be Boolean.");
			}

		} else {
			int m = ctx.condition_list().expression().size();

			for (int i = 0; i < m; i++) {

				if (ctx.condition_list().expression(i).add_sub() != null) {
					System.out.println("Error 220 : in line " + ctx.start.getLine() + " , Condition type must be Boolean.");
				} else if (ctx.condition_list().expression(i).rightExp().leftExp() == null) {
					System.out.println("Error 220 : in line " + ctx.start.getLine() + " , Condition type must be Boolean.");
				}
			}
		}

		String blockID = "block" + current.getBlockCount();
		current.insertBlock(blockID);
		current = new SymbolTable(blockID, current, Type.BLOCK);
	}


	@Override
	public void exitWhile_statment(While_statmentContext ctx) {
		current = current.getParent();
	}


	@Override
	public void enterIf_else_statment(If_else_statmentContext ctx) {

		for (int i = 0; i < ctx.condition_list().size(); i++)
			if (ctx.condition_list(i).expression().size() == 1) {

				if (ctx.condition_list(i).expression(0).add_sub() != null) {
					System.out.println("Error 220 : in line " + ctx.start.getLine() + " , Condition type must be Boolean.");
				} else if (ctx.condition_list(i).expression(0).rightExp().INTEGER() != null) {
					System.out.println("Error 220 : in line " + ctx.start.getLine() + " , Condition type must be Boolean.");
				} else if (current.lookup(ctx.condition_list(i).expression(0).rightExp().leftExp().ID().getText(), Kind.METHOD)) {
					if (!((MethodSymbol) current.findSymbol(ctx.condition_list(i).expression(0).rightExp().leftExp().ID().getText(), Kind.METHOD)).getReturnType().equals("boolean")) {
						System.out.println("Error 220 : in line " + ctx.start.getLine() + " , Condition type must be Boolean.");
					}
				} else if (!(ctx.condition_list(i).expression(0).rightExp().leftExp().ID().equals("false") || ctx.condition_list(i).expression(0).rightExp().leftExp().ID().equals("false"))) {
					System.out.println("Error 220 : in line " + ctx.start.getLine() + " , Condition type must be Boolean.");
				}

			} else {
				int m = ctx.condition_list(i).expression().size();

				for (int j = 0; j < m; j++) {

					if (ctx.condition_list(i).expression(j).add_sub() != null) {
						System.out.println("Error 220 : in line " + ctx.start.getLine() + " , Condition type must be Boolean.");
					} else if (ctx.condition_list(i).expression(j).rightExp().leftExp() == null) {
						System.out.println("Error 220 : in line " + ctx.start.getLine() + " , Condition type must be Boolean.");
					}
				}
			}


		String blockID = "block" + current.getBlockCount();
		current.insertBlock(blockID);
		current = new SymbolTable(blockID, current, Type.BLOCK);
	}


	@Override
	public void exitIf_else_statment(If_else_statmentContext ctx) {

		current = current.getParent();
	}


	@Override
	public void enterPrint_statment(Print_statmentContext ctx) {
	}


	@Override
	public void exitPrint_statment(Print_statmentContext ctx) {
	}


	@Override
	public void enterFor_statment(For_statmentContext ctx) {
		String blockID = "block" + current.getBlockCount();
		current.insertBlock(blockID);
		current = new SymbolTable(blockID, current, Type.BLOCK);
	}


	@Override
	public void exitFor_statment(For_statmentContext ctx) {
		current = current.getParent();
	}


	@Override
	public void enterMethod_call(Method_callContext ctx) {

		if (ctx.ID() != null) {
			if (ctx.leftExp() != null) {
				current.findSymbol(ctx.leftExp().ID().getText(), Kind.VARIABLE);
				VariableSymbol s = (VariableSymbol) current.findSymbol(ctx.leftExp().ID().getText(), Kind.VARIABLE);
				if (s != null)
					usedMethods.add(new MethodDec(ctx.start.getLine(), ctx.ID().getText(), s.getType()));
			} else {
				SymbolTable st = current;
				while (st.getParent() != null)
					st = st.getParent();
				usedMethods.add(new MethodDec(ctx.start.getLine(), ctx.ID().getText(), st.getId()));
			}
		}
		if(ctx.args().explist() != null)
			if (ctx.args().explist().expression().size() == 1){}
		else {
			LinkedList<String> parameters =  new LinkedList<>();
				for (int i = 0; i < ctx.args().explist().expression().size(); i++) {
					if (ctx.args().explist().expression(i).rightExp().leftExp() != null) {
						String e = current.findSymbol(ctx.args().explist().expression(i).rightExp().leftExp().getText(),Kind.VARIABLE).getId();
						parameters.add(e);
					}
				}
				for (MethodDec md : usedMethods) {
					if (md.getMethodName().equals(ctx.ID().getText()))
						md.setParameters(parameters);
				}
			}

	}


	@Override
	public void exitMethod_call(Method_callContext ctx) {
	}


	@Override
	public void enterAssignment(AssignmentContext ctx) {

		if (ctx.varDec() != null) {
			String s;
			s = ctx.varDec().type().getText();


			switch (s) {
				case "int":
					if (!ctx.expression().rightExp().getText().matches("[0-9]+"))
						if (current.lookup(ctx.expression().rightExp().leftExp().ID().getText(), Kind.VARIABLE)) {
							if (!((VariableSymbol) current.findSymbol(ctx.expression().rightExp().leftExp().ID().getText(), Kind.VARIABLE)).getType().equals(s))
								System.out.println("Error 250 : in line " + ctx.start.getLine() + " , Incompatible types : " + ((VariableSymbol) current.findSymbol(ctx.expression().rightExp().leftExp().ID().getText(), Kind.VARIABLE)).getType() + " can not be converted to " + s + ".");
						}else   System.out.println("Error 250 : in line " + ctx.start.getLine() + " , Incompatible types : string can not be converted to " + s + ".");
							break;


			}

		}


	}




	@Override public void exitAssignment(AssignmentContext ctx) { }


	@Override public void enterExpression(ExpressionContext ctx) { }


	@Override public void exitExpression(ExpressionContext ctx) { }


	@Override public void enterRightExp(RightExpContext ctx) {
		if(isUpperCase(ctx.getText().charAt(0))) {
			boolean isImported = false;
			for (ClassDec cd : importedClasses) {
				if (ctx.getText().equals(cd.getClassName())) {
					isImported = true;
					ClassDec c = new ClassDec(ctx.getText(),ctx.start.getLine(),null);
					usedClasses.add(c);
					break;
				}
			}
			if(!isImported)
				System.out.println("Error106 : in line " + ctx.start.getLine() + ", cannot find class " + ctx.USER_TYPE().getText());
			//else usedClasses
		}
	}


	@Override public void exitRightExp(RightExpContext ctx) { }


	@Override public void enterLeftExp(LeftExpContext ctx) {
		Symbol s = new VariableSymbol(null,ctx.ID().getText());
		if(!current.lookup(s.getId(), Kind.VARIABLE))
			usedVariables.add(new VarDec(ctx.ID().getText(),ctx.start.getLine(),thisClass));
	}

	@Override public void exitLeftExp(LeftExpContext ctx) { }


	@Override public void enterArgs(ArgsContext ctx) { }


	@Override public void exitArgs(ArgsContext ctx) { }


	@Override public void enterExplist(ExplistContext ctx) { }


	@Override public void exitExplist(ExplistContext ctx) { }


	@Override public void enterAssignment_operators(Assignment_operatorsContext ctx) { }


	@Override public void exitAssignment_operators(Assignment_operatorsContext ctx) { }


	@Override public void enterEq_neq(Eq_neqContext ctx) { }


	@Override public void exitEq_neq(Eq_neqContext ctx) { }


	@Override public void enterRelation_operators(Relation_operatorsContext ctx) { }


	@Override public void exitRelation_operators(Relation_operatorsContext ctx) { }


	@Override public void enterAdd_sub(Add_subContext ctx) { }


	@Override public void exitAdd_sub(Add_subContext ctx) { }


	@Override public void enterMult_mod_div(Mult_mod_divContext ctx) { }


	@Override public void exitMult_mod_div(Mult_mod_divContext ctx) { }


	@Override public void enterType(TypeContext ctx) { }


	@Override public void exitType(TypeContext ctx) { }


	@Override public void enterJythonType(JythonTypeContext ctx) { }


	@Override public void exitJythonType(JythonTypeContext ctx) { }


	@Override public void enterEveryRule(ParserRuleContext ctx) { }


	@Override public void exitEveryRule(ParserRuleContext ctx) { }


	@Override public void visitTerminal(TerminalNode node) { }


	@Override public void visitErrorNode(ErrorNode node) { }
}