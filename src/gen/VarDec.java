package gen;

public class VarDec {
    private String varName;
    private int varLine;

    public VarDec(String varName, int varLine) {
        this.varName = varName;
        this.varLine = varLine;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public int getVarLine() {
        return varLine;
    }

    public void setVarLine(int varLine) {
        this.varLine = varLine;
    }
}
