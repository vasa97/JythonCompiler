package gen;

public class ClassDec {

    private String className;
    private int classLine;
    private String parent;
    private SymbolTable symbolTable;

    public ClassDec(String className, int classLine, String parent) {
        setClassName(className);
        setClassLine(classLine);
        setParent(parent);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassLine() {
        return classLine;
    }

    public void setClassLine(int classLine) {
        this.classLine = classLine;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }
}
