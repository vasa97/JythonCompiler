package gen;

import java.util.ArrayList;

public class Symbol {
    private String id;
    private String type;
    private Kind kind;
    boolean isArray;
    //for methods
    private ArrayList<Symbol> parameters;
    private int paramCounts;
    private String[] defaultValues;
    public Symbol(){}

    public Symbol(String id, Kind kind, String type){
        this.id = id;
        this.type = type;
        this.kind = kind;

        this.defaultValues = new String[1];

        if(type == "boolean"){
            defaultValues[0] = "false";
        }
        else if(type == "int"){
            defaultValues[0] = "0";
        }
    }

    public static Symbol createMethodSymbol(String id, String returnType){
        Symbol s = new Symbol(id, Kind.METHOD, returnType);
        return s;
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

    public void setArray(boolean array) {
        isArray = array;
    }
    public boolean isArray() {
        return isArray;
    }

    public void setDefaultValues(String[] defaultValues) {
        this.defaultValues = defaultValues;
    }

    public String[] getDefaultValues() {
        return defaultValues;
    }

    public Symbol(Kind kind, String type){
//        this.id = id;
        this.type = type;
        this.kind = kind;
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

    public void setParamCounts(int paramCounts) {
        this.paramCounts = paramCounts;
    }

    public int getParamCounts() {
        return paramCounts;
    }
}
