package expression.exceptions;

import expression.TripleExpression;

/**
 * Created by гыук on 01.04.2017.
 */
public class CheckedDiv extends BinaryOperation {
    public CheckedDiv(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    protected int operation(int a, int b) throws OverflowException, CalcException {

        if (b == 0) {
            throw new CalcException("division by zero");
        } else if ((a == Integer.MIN_VALUE) && (b == -1)) {
            throw new OverflowException();
        }
        return a / b;
    }
}
