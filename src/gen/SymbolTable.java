package gen;


import Symbol.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable {

    private String id;
    private Map<Kind, Map<String, Symbol>> entries;
    private SymbolTable parent;

    public SymbolTable(String id){
        this.id = id;
        entries = new HashMap<>();
        setParent(null);
    }

    public SymbolTable(String id,SymbolTable parent) {
        this.id = id;
        entries = new HashMap<>();
        setParent(parent);
    }

    public boolean lookup(Symbol symbol,Kind kind) {
        SymbolTable cur = this;

        while (cur != null) {
            if (cur.get(symbol.getId(), kind) != null)
                return true;

            //System.out.println(cur.getId());
            cur = cur.getParent();

        }
        return false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Symbol get(String id, Kind kind) {
        switch (kind) {
            case METHOD:
                Map<String, Symbol> methodEntries = entries.get(Kind.METHOD);
                if (methodEntries != null)
                    return methodEntries.get(id);
                else return null;

            case ATTRIBUTE:
                Map<String, Symbol> attributeEntries = entries.get(Kind.ATTRIBUTE);
                if (attributeEntries != null)
                    return attributeEntries.get(id);
                else return null;

            case CONSTRUCTOR:
                Map<String, Symbol> constructorEntries = entries.get(Kind.CONSTRUCTOR);
                if (constructorEntries != null)
                    return constructorEntries.get(id);
                else return null;

            default:
                Map<String, Symbol> localvariableEntries = entries.get(Kind.LOCALVARIABLE);
                if (localvariableEntries != null)
                    return localvariableEntries.get(id);
                else return null;
        }
    }

    public void insertVariable(String type,String id,Kind kind) {

        Map<String,Symbol> localVariableEntries = entries.get(Kind.ATTRIBUTE);
        if (localVariableEntries == null) {
            localVariableEntries = new HashMap<String,Symbol>();
            entries.put(kind, localVariableEntries);
        }
        localVariableEntries.put(id,new VariableSymbol(type, id));
    }

    public void insertMethod(Type returnType, String id, List<jythonParser.ParametersContext> params, boolean fref){

        Map<String, Symbol> methodEntries = entries.get(Kind.METHOD);
        if (methodEntries == null) {
            methodEntries = new HashMap<>();
            entries.put(Kind.METHOD, methodEntries);
        }
        methodEntries.put(id, new MethodSymbol(returnType, id, params, fref));
    }

    public void insertCostructor(String id, ArrayList<VariableSymbol> params) {

        Map<String, Symbol> constructorEntries = entries.get(Kind.CONSTRUCTOR);
        if (constructorEntries == null) {
            constructorEntries = new HashMap<>();
            entries.put(Kind.CONSTRUCTOR, constructorEntries);
        }
        constructorEntries.put(id, new ConstructorSymbol(id, params));
    }

    public void setParent(SymbolTable parent) {
        this.parent = parent;
    }

    public SymbolTable getParent() {
        return this.parent;
    }
}