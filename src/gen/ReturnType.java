package gen;

public class ReturnType {
    private int line;
    private String name;
    private SymbolTable symbolTable;
    private String returnType;

    public ReturnType(int line, String name, SymbolTable symbolTable, String returnType) {
        this.line = line;
        this.name = name;
        this.symbolTable = symbolTable;
        this.returnType = returnType;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }
}
