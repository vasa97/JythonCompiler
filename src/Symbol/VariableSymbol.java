package Symbol;

public class VariableSymbol extends Symbol {

    private Type type;

    public VariableSymbol(String id, Type type) {
        super(id);
        this.type = type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}