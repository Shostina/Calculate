package expression.exceptions;
import expression.TripleExpression;

/**
 * Created by гыук on 24.03.2017.
 */

public abstract class BinaryOperation implements TripleExpression {
    protected TripleExpression l;
    protected TripleExpression r;

    public BinaryOperation(TripleExpression l, TripleExpression r) {
        this.l = l;
        this.r = r;
    }

    abstract int operation(int a, int b) throws InputException, OverflowException, CalcException;

    public int evaluate(int x, int y, int z) throws InputException, OverflowException, CalcException {
        return operation(l.evaluate(x, y, z), r.evaluate(x, y, z));
    }

}

