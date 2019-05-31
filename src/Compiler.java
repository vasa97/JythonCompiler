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
    public static void main(String[] args) throws IOException {
        CharStream stream = null;
        int fileCount = new File("samples\\").list().length;
        for (int i = 1; i <= fileCount; i++) {
            stream = CharStreams.fromFileName("samples\\" + i + ".txt");
            System.out.println("--- "+i+" ---");
            jythonLexer lexer = new jythonLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            jythonParser parser = new jythonParser(tokens);
            ParseTree tree = parser.program();
            ParseTreeWalker walker = new ParseTreeWalker();
            jythonListener listener = new jythonBaseListener(allClasses,importedClassesToBeChecked);
            walker.walk(listener, tree);
        }
        System.out.println("!! finish !!");
        findInheritanceLoops();
        checkImportedClasses();
    }


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