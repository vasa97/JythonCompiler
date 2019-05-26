package gen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SymbolTable {
    /**
     * Method Map id->entry
     **/
    private Map<String, Symbol> methodEntries;
    private Map<String, Symbol> variableEntries;
    private Map<String, Symbol> objectEntries;
    private String id;
    private SymbolTable parentSymbolTable;
    private ArrayList<SymbolTable> childSymbolTables;
    private int depth=0;
    public SymbolTable getChild(String id){
        for(int i = 0;i<childSymbolTables.size();i++){
            if (childSymbolTables.get(i).getId().equals(id))return childSymbolTables.get(i);
        }
        return null;
    }
    public SymbolTable(String id) {
        this.id = id;
        methodEntries = new HashMap<String, Symbol>();
        variableEntries = new HashMap<String, Symbol>();
        childSymbolTables = new ArrayList<>();
    }

    public void addChild(SymbolTable st){
        childSymbolTables.add(st);
    }

    public SymbolTable lookup(String id){
        LinkedList<SymbolTable> sts = new LinkedList<>();
        sts.add(this);
        while (!sts.isEmpty()){
            sts.addAll(sts.getFirst().childSymbolTables);
            if(id.equals(sts.getFirst().getId())){
                return sts.getFirst();
            }
            sts.pop();
        }
        return null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Symbol getMethod(String id) {
        return methodEntries.get(id);
    }

    public Map<String, Symbol> getMethodEntries() {
        return methodEntries;
    }

    public void insertMethod(String id, String type) {
        methodEntries.put(id, new Symbol(Kind.METHOD, type));
    }

    public void insertMethod(Symbol s) {
        methodEntries.put(s.getId(), s);
    }

//    public Symbol getClass(String id, Type t) {
//        return methodEntries.get(id);
//    }
//
//    public void insertClass(String id, String type) {
//        methodEntries.put(id, new Symbol(Kind.CLASS, type));
//    }

    public Symbol getVariable(String id) {
        Symbol G = variableEntries.get(id);
        return G;
    }

    public Map<String, Symbol> getVariableEntries() {
        return variableEntries;
    }

    public void insertVariable(String id, String type) {
        variableEntries.put(id, new Symbol(Kind.VARIABLE, type));
    }

    public void insertVariable(Symbol s){
        variableEntries.put(s.getId(), s);
    }

    public void setParent(SymbolTable parent) {
        this.parentSymbolTable = parent;
        this.depth = parent.getDepth() + 1;
    }

    public SymbolTable getParent() {
        return parentSymbolTable;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDepth() {
        return depth;
    }
}