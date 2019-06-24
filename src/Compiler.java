import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

import gen.*;

public class Compiler {
    public static LinkedList<ClassDec> allClasses = new LinkedList<>();
    public static LinkedList<ClassDec> importedClassesToBeChecked = new LinkedList<>();
    public static LinkedList<ClassDec> usedClassesToBeChecked = new LinkedList<>();
    public static LinkedList<VarDec> usedVariablesToBeChecked = new LinkedList<>();
    public static LinkedList<MethodDec> usedMethodsToBeChecked = new LinkedList<>();
    public static void main(String[] args) throws IOException {

        CharStream stream;
        File folder = new File("samples\\");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            stream = CharStreams.fromFileName("samples\\" + listOfFiles[i].getName());
            System.out.println("--- "+listOfFiles[i].getName()+" ---");
            jythonLexer lexer = new jythonLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            jythonParser parser = new jythonParser(tokens);
            ParseTree tree = parser.program();
            ParseTreeWalker walker = new ParseTreeWalker();
            jythonListener listener = new jythonBaseListener(allClasses,importedClassesToBeChecked,usedClassesToBeChecked, usedVariablesToBeChecked,usedMethodsToBeChecked);
            walker.walk(listener, tree);
        }

        System.out.println("!! finish !!");
        //findInheritanceLoops();
        //checkImportedClasses();
        //checkUsedClasses();
        //checkVariables();
        //findMain();
        methodCallCheck();
    }

    //Checks if the imported classes exist
    public static void checkImportedClasses(){
        for (ClassDec checkCD: importedClassesToBeChecked) {
            boolean existed = false;
            for (ClassDec cd:allClasses) {
                if(cd.getClassName().equals(checkCD.getClassName()))
                    existed = true;
            }
            if(!existed)
                System.out.println("Error106 : in line " + checkCD.getClassLine() + ", cannot find class " + checkCD.getClassName());
        }
    }

    private static void methodCallCheck(){
        for (MethodDec md:usedMethodsToBeChecked) {
            ClassDec cd = findClassDec(md.getRelatedClass());
            while (!cd.getSymbolTable().lookup(md.getMethodName(), Kind.METHOD)) {
                System.out.println("Error 105 : in line " + md.getMethodLine() + ", cannot find method " + md.getMethodName());
                cd = findClassDec(cd.getParent());
            }
        }
    }

    //Checks if main method exists in any class
    private static void findMain(){
        boolean found = false;
        for (ClassDec cd:allClasses) {
            if(cd.getSymbolTable().lookup("main",Kind.METHOD))
                found = true;
        }
        if(!found)
            System.out.println("Error104 : Can not find main method");
    }
    //Finds loops happened in inheritance hierarchy of classes
    private static void findInheritanceLoops() {

        LinkedList<ClassDec> tempAllClasses = new LinkedList<>(allClasses);

        mainLoop:for (int i = 0; i < tempAllClasses.size(); i++) {
            ClassDec cd = tempAllClasses.get(i);
            if (!cd.getParent().equals("Object")) {
                Stack<ClassDec> ih = new Stack<>(); //ih:inheritance hierarchy
                ih.push(cd);
                if(findClassDec(cd.getParent()) == null)
                    continue;
                ih.push(findClassDec(cd.getParent()));
                while (!ih.peek().getParent().equals("Object") && !ih.peek().getParent().equals(cd.getClassName())) {
                    ClassDec temp = findClassDec(ih.peek().getParent());
                    if (temp == null)
                        continue mainLoop;
                    ih.push(temp);
                }
                if (ih.peek().getParent().equals(cd.getClassName())) {
                    //print inheritance hierarchy
                    System.out.print("Error107 : Invalid inheritance ");
                    ClassDec fe = ih.peek();
                    while (!ih.empty()) {
                        System.out.print(ih.peek().getClassName() + " -> ");
                        tempAllClasses.remove(ih.pop());
                    }
                    System.out.print(fe.getClassName() + "\n");
                    //delete from list
                }
            }
        }
    }

    //Finds a ClassDec object by its class name
    private static ClassDec findClassDec(String className){
        for (ClassDec cd:allClasses) {
            if(cd.getClassName().equals(className))
                return cd;
        }
        return null;
    }

    private static void checkUsedClasses(){
        for (ClassDec checkCD: usedClassesToBeChecked) {
            boolean existed = false;
            for (ClassDec cd:allClasses) {
                if(cd.getClassName().equals(checkCD.getClassName()))
                    existed = true;
            }
            if(!existed && !checkCD.getClassName().equals("Object"))
                System.out.println("Error106 : in line " + checkCD.getClassLine() + ", cannot find class " + checkCD.getClassName());
        }
    }

    //checks if used variables exist in great grandparents of their related classes
    public static void checkVariables(){
        for (VarDec vd: usedVariablesToBeChecked) {
            ClassDec cur = findClassDec(vd.getRelatedClass().getParent());
            boolean found = false;
            while (cur != null) {
                if(cur.getSymbolTable().lookup(vd.getVarName(),Kind.VARIABLE)) {
                    found = true;
                    break;
                }
                cur = findClassDec(cur.getParent());
            }
            if(!found)
                System.out.println("Error108 : in line " + vd.getVarLine() + ", Can not find Variable " + vd.getVarName());
        }
    }
}