package Symbol;



import java.util.ArrayList;

public class MethodSymbol extends Symbol {

    private boolean fref;
    private String returnType;
    private ArrayList<String> parameters;

    public MethodSymbol(String returnType, String id, ArrayList<String> params, boolean fref) {
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

    public void setParameters(ArrayList<String> params) {
        this.parameters = params;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }
}