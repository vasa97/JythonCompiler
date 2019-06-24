package gen;

public class VarDec {

    private int varLine;
    private String varName;
    private ClassDec relatedClass;

    public VarDec(String varName, int varLine,ClassDec relatedClass) {
        this.varLine = varLine;
        this.varName = varName;
        this.relatedClass = relatedClass;
    }

    public int getVarLine() {
        return varLine;
    }

    public void setVarLine(int varLine) {
        this.varLine = varLine;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public ClassDec getRelatedClass() {
        return relatedClass;
    }

    public void setRelatedClass(ClassDec relatedClass) {
        this.relatedClass = relatedClass;
    }
}