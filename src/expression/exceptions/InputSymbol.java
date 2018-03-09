package expression.exceptions;

/**
 * Created by гыук on 24.03.2017.
 */

public class InputSymbol {
    private SymbolType operator;
    private String value;

    public InputSymbol(SymbolType operator, String value) {
        this.operator = operator;
        this.value = value;
    }

    public SymbolType getType() {
        return operator;
    }

    public String getValue() {
        return value;
    }
}