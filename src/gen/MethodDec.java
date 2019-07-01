package gen;

import java.util.LinkedList;

public class MethodDec {
    private int MethodLine;
    private String MethodName;
    private String relatedClass;
    private LinkedList<String> parameters;
    public MethodDec(int methodLine, String methodName, String relatedClass) {
        MethodLine = methodLine;
        MethodName = methodName;
        this.relatedClass = relatedClass;
    }

    public LinkedList<String> getParameters() {
        return parameters;
    }

    public void setParameters(LinkedList<String> parameters) {
        this.parameters = parameters;
    }

    public int getMethodLine() {
        return MethodLine;
    }

    public void setMethodLine(int methodLine) {
        MethodLine = methodLine;
    }

    public String getMethodName() {
        return MethodName;
    }

    public void setMethodName(String methodName) {
        MethodName = methodName;
    }

    public String getRelatedClass() {
        return relatedClass;
    }

    public void setRelatedClass(String relatedClass) {
        this.relatedClass = relatedClass;
    }
}
