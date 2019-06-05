package Symbol;



import java.util.ArrayList;

public class MethodSymbol extends Symbol {

    private boolean fref;
    private String returnType;
    private ArrayList<String> parameters;
    private int numOfParameters;

    public MethodSymbol(String returnType, String id, ArrayList<String> params, boolean fref) {
        super(id);
        setReturnType(returnType);
        setFref(fref);
        setParameters(params);
        if (params == null) setNumOfParameters(0);
        else setNumOfParameters(params.size());
    }

    public MethodSymbol(String returnType, String id, int numOfParameters, boolean fref) {
        super(id);
        setReturnType(returnType);
        setFref(fref);
        setNumOfParameters(numOfParameters);
    }

    public boolean isFref() {
        return fref;
    }

    public void setFref(boolean fref) {
        this.fref = fref;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String> params) {
        this.parameters = params;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String type) {
        this.returnType = type;
    }

    public int getNumOfParameters() {
        return numOfParameters;
    }

    public void setNumOfParameters(int numOfParameters) {
        this.numOfParameters = numOfParameters;
    }
}