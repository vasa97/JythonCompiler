package gen;

import java.util.ArrayList;

public class Symbol {
    private String id;
    private String type;
    private Kind kind;
    
    //for methods
    private ArrayList<Symbol> parameters;

    public Symbol(String id, Kind kind, String type){
        this.id = id;
        this.type = type;
        this.kind = kind;
    }

    public static Symbol createMethodSymbol(String id, String returnType, ArrayList<Symbol> params){
        Symbol s = new Symbol(id, Kind.METHOD, returnType);
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

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public Kind getKind() {
        return kind;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
