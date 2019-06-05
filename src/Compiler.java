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

    private static LinkedList<ClassDec> allClasses = new LinkedList<>();
    private static LinkedList<ClassDec> importedClassesToBeChecked = new LinkedList<>();
    private static LinkedList<ClassDec> usedClassesToBeChecked = new LinkedList<>();
    private static LinkedList<VarDec> usedVaribalesToBeChecked = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        CharStream stream;
        File folder = new File("samples\\");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                stream = CharStreams.fromFileName("samples\\" + listOfFile.getName());
                System.out.println("--- " + listOfFile.getName() + " ---");
                jythonLexer lexer = new jythonLexer(stream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                jythonParser parser = new jythonParser(tokens);
                ParseTree tree = parser.program();
                ParseTreeWalker walker = new ParseTreeWalker();
                jythonListener listener = new jythonBaseListener(allClasses, importedClassesToBeChecked, usedClassesToBeChecked, usedVaribalesToBeChecked);
                walker.walk(listener, tree);
            }
        }

        System.out.println("!! finish !!");
        findInheritanceLoops();
        checkImportedClasses();
        checkUsedClasses();
        checkVariables();
    }

    private static void checkImportedClasses(){
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
    private static void checkVariables(){

        for (VarDec vd:usedVaribalesToBeChecked) {
            ClassDec cur = findClassDec(vd.getRelatedClass().getParent());
            boolean found = false;
            while (cur != null) {
                if(cur.getSymbolTable().lookup(vd.getVarName(), Kind.VARIABLE)) {
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