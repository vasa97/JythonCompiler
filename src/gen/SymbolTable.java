package gen;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SymbolTable {

    private String id;
    private Map<Kind, Map<String,Symbol>> entries;
    private SymbolTable parent;
//    private ArrayList<SymbolTable> childSymbolTables;
//
//    public SymbolTable getChild(String id){
//        for(int i = 0;i<childSymbolTables.size();i++){
//            if (childSymbolTables.get(i).getId().equals(id))return childSymbolTables.get(i);
//        }
//        return null;
//    }

    public SymbolTable(String id,SymbolTable parent) {
        this.id = id;
        entries = new HashMap<>();
        this.parent = parent;
//        childSymbolTables = new ArrayList<>();
    }

//    public void addChild(SymbolTable st){
//        childSymbolTables.add(st);
//    }

    public boolean lookup(Symbol symbol,Kind kind) {
        SymbolTable cur = this;
        while (cur != null) {
            if (get(symbol.getId(), kind) != null)
                return true;
            else cur = cur.parent;
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
                return methodEntries.get(id);

            case ATTRIBUTE:
                Map<String, Symbol> attributeEntries = entries.get(Kind.ATTRIBUTE);
                return attributeEntries.get(id);

            case CONSTRUCTOR:
                Map<String, Symbol> constructorEntries = entries.get(Kind.CONSTRUCTOR);
                return constructorEntries.get(id);


            default:
                Map<String, Symbol> localvariableEntries = entries.get(Kind.LOCALVARIABLE);
                return localvariableEntries.get(id);
        }

    }
        public void insert (String id, Type type, Kind kind){
            switch (kind) {
                case METHOD:
                    Map<String, Symbol> methodEntries = entries.get(Kind.METHOD);
                    if (methodEntries == null) {
                        methodEntries = new HashMap<>();
                        entries.put(Kind.METHOD, methodEntries);
                    }
                    methodEntries.put(id, new Symbol(id, type));
                    break;

                case ATTRIBUTE:
                    Map<String, Symbol> attributeEntries = entries.get(Kind.ATTRIBUTE);
                    if (attributeEntries == null) {
                        attributeEntries = new HashMap<>();
                        entries.put(Kind.METHOD, attributeEntries);
                    }
                    attributeEntries.put(id, new Symbol(id, type));
                    break;

                case CONSTRUCTOR:
                    Map<String, Symbol> constructorEntries = entries.get(Kind.CONSTRUCTOR);
                    if (constructorEntries == null) {
                        constructorEntries = new HashMap<>();
                        entries.put(Kind.METHOD, constructorEntries);
                    }
                    constructorEntries.put(id, new Symbol(id, type));
                    break;

                default:
                    Map<String, Symbol> localvariableEntries = entries.get(Kind.LOCALVARIABLE);
                    if (localvariableEntries == null) {
                        localvariableEntries = new HashMap<>();
                        entries.put(Kind.METHOD, localvariableEntries);
                    }
                    localvariableEntries.put(id, new Symbol(id, type));
                    break;
            }
        }

    public void setParent(SymbolTable parent) {
        this.parent = parent;
    }

    public SymbolTable getParent() {
        return this.parent;
    }
}
