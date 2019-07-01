package gen;


import Symbol.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {


    private Type type;
    private String id;
    private Map<Kind, Map<String, Symbol>> entries;
    private SymbolTable parent;


    public SymbolTable(String id, SymbolTable parent, Type type) {
        this.id = id;
        entries = new HashMap<>();
        setParent(parent);
        setType(type);
    }

    public boolean lookup(String id,Kind kind) {
        SymbolTable cur = this;
        while (cur != null) {
            if (cur.get(id, kind) != null)
                return true;
            cur = cur.getParent();
        }
        return false;
    }

    public Symbol findSymbol(String id,Kind kind) {
        SymbolTable cur = this;
        while (cur != null) {
            if (cur.get(id, kind) != null)
                return cur.get(id, kind);
            cur = cur.getParent();
        }
        return null;
    }

    int isDefined(MethodSymbol s1) {
        Symbol s2  = get(s1.getId(), Kind.METHOD);

        //if there is no method with this name
        if (s2 == null) return 0;

        MethodSymbol ms = (MethodSymbol) s2;

        //if there is a method with exact name and parameters with s1
        if (ms.getParameters().equals(s1.getParameters())) return 1;

        //if there is a method with s1.name but different parameters
        return 2;
    }

    int isDefined(ConstructorSymbol cs) {
        Symbol s  = get(cs.getId(), Kind.CONSTRUCTOR);

        //if there is no method with this name
        if (s == null) return 0;

        ConstructorSymbol cons = (ConstructorSymbol) s;

        //if there is a method with exact name and parameters with s1
        if (cons.getParameters().equals(cs.getParameters())) return 1;

        //if there is a method with s1.name but different parameters
        return 2;
    }

    boolean lookCurrentScope(String id, Kind kind){
        return get(id, kind) != null;
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

            case CONSTRUCTOR:
                Map<String, Symbol> constructorEntries = entries.get(Kind.CONSTRUCTOR);
                if (constructorEntries != null)
                    return constructorEntries.get(id);
                else return null;

            default:
                Map<String, Symbol> localvariableEntries = entries.get(Kind.VARIABLE);
                if (localvariableEntries != null)
                    return localvariableEntries.get(id);
                else return null;
        }
    }

    void insertBlock(String id){
        Map<String, Symbol> blockEntries = entries.get(Kind.BLOCK);
        if (blockEntries == null) {
            blockEntries = new HashMap<>();
            entries.put(Kind.CONSTRUCTOR, blockEntries);
        }
        blockEntries.put(id, new Symbol(id));
    }

    int getBlockCount(){
        Map<String, Symbol> blockEntries = entries.get(Kind.BLOCK);
        if(blockEntries == null)
            return 0;
        return blockEntries.size();
    }

    void insertVariable(String type, String id, Kind kind) {

        Map<String,Symbol> localVariableEntries = entries.get(Kind.VARIABLE);
        if (localVariableEntries == null) {
            localVariableEntries = new HashMap<String,Symbol>();
            entries.put(kind, localVariableEntries);
        }
        localVariableEntries.put(id,new VariableSymbol(type, id));
    }

    void insertMethod(String returnType, String id, int numberOfParameters){

        Map<String, Symbol> methodEntries = entries.get(Kind.METHOD);
        if (methodEntries == null) {
            methodEntries = new HashMap<>();
            entries.put(Kind.METHOD, methodEntries);
        }
        methodEntries.put(id, new MethodSymbol(returnType, id, numberOfParameters));
    }

    void insertMethod(String returnType, String id, ArrayList<String> params){

        Map<String, Symbol> methodEntries = entries.get(Kind.METHOD);
        if (methodEntries == null) {
            methodEntries = new HashMap<>();
            entries.put(Kind.METHOD, methodEntries);
        }
        methodEntries.put(id, new MethodSymbol(returnType, id, params));
    }

    void insertCostructor(String id, ArrayList<String> params) {

        Map<String, Symbol> constructorEntries = entries.get(Kind.CONSTRUCTOR);
        if (constructorEntries == null) {
            constructorEntries = new HashMap<>();
            entries.put(Kind.CONSTRUCTOR, constructorEntries);
        }
        constructorEntries.put(id, new ConstructorSymbol(id, params));
    }

    private void setParent(SymbolTable parent) {
        this.parent = parent;
    }

    SymbolTable getParent() {
        return this.parent;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}