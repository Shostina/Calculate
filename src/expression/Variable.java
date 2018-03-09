package expression;

/**
 * Created by гыук on 24.03.2017.
 */
public class Variable implements TripleExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public int evaluate(int x, int y, int z) {
        if (name == "y") {
            return y;
        }
        if (name == "z") {
            return z;
        }
        return x;
    }

}
