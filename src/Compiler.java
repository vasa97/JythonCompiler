import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;

import gen.*;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.tool.Rule;

public class Compiler {
    public static void main(String[] args) throws IOException {
        LinkedList<ParseTree> parseTrees = new LinkedList<>();
        LinkedList<String> allClassNames = new LinkedList<>();
        CharStream stream = null;
        int fileCount = new File("samples\\").list().length;
        for (int i = 1; i <= fileCount; i++) {
            stream = CharStreams.fromFileName("samples\\" + i + ".txt");
            jythonLexer lexer = new jythonLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            jythonParser parser = new jythonParser(tokens);
            ParseTree tree = parser.program();
            parseTrees.add(tree);
            ParseTreeWalker walker = new ParseTreeWalker();
            ClassFinder classFinder = new ClassFinder();
            walker.walk(classFinder, tree);
            if(allClassNames.contains(classFinder.className))
                System.out.println("Error101 : in line "+ classFinder.classLine + ", " + classFinder.className + " has been defined already");
            else
                allClassNames.add(classFinder.className);
        }
        for (ParseTree pt:parseTrees) {
            ParseTreeWalker walker = new ParseTreeWalker();
            jythonListener listener = new jythonBaseListener(allClassNames);
            walker.walk(listener, pt);
        }
    }
}