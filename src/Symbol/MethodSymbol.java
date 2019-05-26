package Symbol;


import java.util.ArrayList;

public class MethodSymbol extends Symbol {

    private boolean fref;
    private Type type;
    private ArrayList<VariableSymbol> parameters;

    public MethodSymbol(String id, Type type, boolean fref, ArrayList<VariableSymbol> params) {
        super(id);
        this.type = type;
        this.fref = fref;
        this.parameters = params;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isFref() {
        return fref;
    }

    public void setFref(boolean fref) {
        this.fref = fref;
    }

    public void setParameters(ArrayList<VariableSymbol> params) {
        this.parameters = params;
    }

    public ArrayList<VariableSymbol> getParameters() {
        return parameters;
    }
}
