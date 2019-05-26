package Symbol;


import java.util.ArrayList;

public class ConstructorSymbol extends Symbol {

    private ArrayList<VariableSymbol> parameters;

    public ConstructorSymbol(String id, ArrayList<VariableSymbol> params) {
        super(id);
        this.parameters = params;
    }

    public ArrayList<VariableSymbol> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<VariableSymbol> params) {
        this.parameters = params;
    }
}