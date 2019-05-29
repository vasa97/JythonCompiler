package gen;

public class ClassDec {
    private String className;
    private int classLine;
    private String parent;


    public ClassDec(String className, int classLine, String parent) {
        this.className = className;
        this.classLine = classLine;
        this.parent = parent;
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

}
