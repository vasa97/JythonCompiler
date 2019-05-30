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
    public static LinkedList<VarDec> allObjects = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        CharStream stream = null;
        int fileCount = new File("samples\\").list().length;
        for (int i = 1; i <= fileCount; i++) {
            stream = CharStreams.fromFileName("samples\\" + i + ".txt");
            jythonLexer lexer = new jythonLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            jythonParser parser = new jythonParser(tokens);
            ParseTree tree = parser.program();
            ParseTreeWalker walker = new ParseTreeWalker();
            jythonListener listener = new jythonBaseListener(allClasses,allObjects);
            walker.walk(listener, tree);
        }
        findInheritanceLoops();
        findUndeclaredClasses();
    }

    //Finds undeclared classes
    public static void findUndeclaredClasses(){
        boolean existed;
        for (VarDec vd:allObjects) {
            existed = false;
            for (ClassDec cd:allClasses) {
                if(vd.getVarName().equals(cd.getClassName())) {
                    existed = true;
                    break;
                }
            }
//            if(!existed)
//                System.out.println("Error106 : in line " + vd.getVarLine() + ", cannot find class " + vd.getVarName());
        }
    }
    //Finds loops happened in inheritance hierarchy of classes
    public static void findInheritanceLoops() {
        LinkedList<ClassDec> tempAllClasses = new LinkedList<>();
        tempAllClasses.addAll(allClasses);
        for (int i = 0; i < tempAllClasses.size(); i++) {
            ClassDec cd = tempAllClasses.get(i);
            if (!cd.getParent().equals("Object")) {
                Stack<ClassDec> ih = new Stack<>(); //ih:inheritance hierarchy
                ih.push(cd);
                ih.push(findClassDec(cd.getParent()));
                while (!ih.peek().getParent().equals("Object") && !ih.peek().getParent().equals(cd.getClassName())) {
                    ih.push(findClassDec(ih.peek().getParent()));
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
    public static ClassDec findClassDec(String className){
        for (ClassDec cd:allClasses) {
            if(cd.getClassName().equals(className))
                return cd;
        }
        return null;
    }
}