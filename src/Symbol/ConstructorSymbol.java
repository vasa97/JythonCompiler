package Symbol;


import java.util.ArrayList;

public class ConstructorSymbol extends Symbol {

    private ArrayList<String> parameters;

    public ConstructorSymbol(String id, ArrayList<String> params) {
        super(id);
        setParameters(params);
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String> params) {
        this.parameters = params;
    }
}