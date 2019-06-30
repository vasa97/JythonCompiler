package Symbol;



import java.util.ArrayList;

public class MethodSymbol extends Symbol {

    private String returnType;
    private ArrayList<String> parameters;
    private int numOfParameters;

    public MethodSymbol(String returnType, String id, ArrayList<String> params) {
        super(id);
        setReturnType(returnType);
        setParameters(params);
        if (params == null) setNumOfParameters(0);
        else setNumOfParameters(params.size());
    }

    public MethodSymbol(String returnType, String id, int numOfParameters) {
        super(id);
        setReturnType(returnType);
        setNumOfParameters(numOfParameters);
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