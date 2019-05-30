package Symbol;


import gen.jythonParser;

import java.util.ArrayList;
import java.util.List;

public class MethodSymbol extends Symbol {

    private boolean fref;
    private String returnType;
    private List<jythonParser.ParametersContext> parameters;

    public MethodSymbol(String returnType, String id, List<jythonParser.ParametersContext> params, boolean fref) {
        super(id);
        setReturnType(returnType);
        setFref(fref);
        setParameters(params);
    }

    public String getType() {
        return returnType;
    }

    public void setReturnType(String type) {
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