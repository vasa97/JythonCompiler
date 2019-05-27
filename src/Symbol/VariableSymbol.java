package Symbol;

public class VariableSymbol extends Symbol {

    private String type;

    public VariableSymbol(String type, String id) {
        super(id);
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}