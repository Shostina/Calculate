package expression.exceptions;

import expression.TripleExpression;

/**
 * Created by гыук on 01.04.2017.
 */
public class CheckedAdd extends BinaryOperation {
    public CheckedAdd(TripleExpression first, TripleExpression second) {
        super(first, second);
    }

    protected int operation(int a, int b) throws OverflowException {

        if (((a > 0) && (b > 0) && (a > Integer.MAX_VALUE - b)) ||
                ((a < 0) && (b < 0) && (a < Integer.MIN_VALUE - b))) {
            throw new OverflowException();
        }

        return a + b;
    }
}
