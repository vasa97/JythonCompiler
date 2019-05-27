package Symbol;


import java.util.ArrayList;

public class MethodSymbol extends Symbol {

    private boolean fref;
    private Type returnType;
    private ArrayList<Type> parameters;

    public MethodSymbol(Type returnType, String id, ArrayList<Type> params, boolean fref) {
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

    public void setParameters(ArrayList<Type> params) {
        this.parameters = params;
    }

    public ArrayList<Type> getParameters() {
        return parameters;
    }
}