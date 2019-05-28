package Symbol;


import gen.jythonParser;

import java.util.ArrayList;
import java.util.List;

public class MethodSymbol extends Symbol {

    private boolean fref;
    private Type returnType;
    private List<jythonParser.ParametersContext> parameters;

    public MethodSymbol(Type returnType, String id, List<jythonParser.ParametersContext> params, boolean fref) {
        super(id);
        setReturnType(returnType);
        setFref(fref);
        setParameters(params);
    }

    public Type getType() {
        return returnType;
    }

    public void setReturnType(Type type) {
        this.returnType = type;
    }

    public boolean isFref() {
        return fref;
    }

    public void setFref(boolean fref) {
        this.fref = fref;
    }

    public void setParameters(List<jythonParser.ParametersContext> params) {
        this.parameters = params;
    }

    public List<jythonParser.ParametersContext> getParameters() {
        return parameters;
    }
}