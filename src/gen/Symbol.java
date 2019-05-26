package gen;

import java.util.ArrayList;

public class Symbol {
    private String id;
    private Type type;
    private boolean fref;
    //for methods
    private ArrayList<Symbol> parameters;

    public Symbol(String id, Type type){
        this.id = id;
        this.type = type;
        setFref(false);
    }

    public boolean isFref() {
        return fref;
    }

    public void setFref(boolean fref) {
        this.fref = fref;
    }


    public static Symbol createMethodSymbol(String id, Type returnType, ArrayList<Symbol> params){
        Symbol s = new Symbol(id, returnType);
        s.setParameters(params);
        return s;
    }

    public void setParameters(ArrayList<Symbol> parameters) {
        this.parameters = parameters;
    }

    public ArrayList<Symbol> getParameters() {
        return parameters;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
