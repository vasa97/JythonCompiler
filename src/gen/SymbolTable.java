package gen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SymbolTable {
    /**
     * Method Map id->entry
     **/
    private String id;
    private Map<String, Symbol> methodEntries;
    private Map<String, Symbol> localVariableEntries;
    private Map<String, Symbol> classEntries;
    private Map<String, Symbol> constructorEntries;
    private SymbolTable parentSymbolTable;
//    private ArrayList<SymbolTable> childSymbolTables;
//
//
//    public SymbolTable getChild(String id){
//        for(int i = 0;i<childSymbolTables.size();i++){
//            if (childSymbolTables.get(i).getId().equals(id))return childSymbolTables.get(i);
//        }
//        return null;
//    }
    public SymbolTable(String id,SymbolTable parent) {
        this.id = id;
        methodEntries = new HashMap<String, Symbol>();
        localVariableEntries = new HashMap<String, Symbol>();
        classEntries = new HashMap<String, Symbol>();
        constructorEntries = new HashMap<String, Symbol>();
        parentSymbolTable = parent;
//        childSymbolTables = new ArrayList<>();
    }

//    public void addChild(SymbolTable st){
//        childSymbolTables.add(st);
//    }

    public SymbolTable lookup(String id){
        LinkedList<SymbolTable> sts = new LinkedList<>();
        sts.add(this);
        while (!sts.isEmpty()){
//            sts.addAll(sts.getFirst().childSymbolTables);
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

//    public Symbol getMethod(String id) {
//        return methodEntries.get(id);
//    }
//
//    public Map<String, Symbol> getMethodEntries() {
//        return methodEntries;
//    }

    public void insertMethod(String id, String type) {
        methodEntries.put(id, new Symbol(id,Kind.METHOD, type));
    }
    public void insertMethod(Symbol s) {
        methodEntries.put(s.getId(), s);
    }
//
//    public Map<String> getClassEntries() {
//        return methodEntries.get(id);
//    }

    public void insertClass(String id, String type) {
        classEntries.put(id, new Symbol(id,Kind.CLASS, type));
    }
    public void insertClass(Symbol s) {
        classEntries.put(s.getId(), s);
    }
//    public Symbol getVariable(String id) {
//        Symbol G = localVariableEntries.get(id);
//        return G;
//    }

//    public Map<String, Symbol> getLocalVariableEntries() {
//        return localVariableEntries;
//    }

    public void insertLocalVariable(String id, String type) {
        localVariableEntries.put(id, new Symbol(id,Kind.LOCALVARIABLE, type));
    }

    public void insertLocalVariable(Symbol s){
        localVariableEntries.put(s.getId(), s);
    }

    public void setParent(SymbolTable parent) {
        this.parentSymbolTable = parent;
    }

    public SymbolTable getParent() {
        return parentSymbolTable;
    }
}
