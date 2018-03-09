package expression.exceptions;

import expression.TripleExpression;

/**
 * Created by гыук on 01.04.2017.
 */
public class CheckedNegate implements TripleExpression {

    private TripleExpression operand;

    public CheckedNegate (TripleExpression operand) {
        this.operand = operand;
    }

    public int evaluate(int x, int y, int z) throws InputException, OverflowException, CalcException {
        if (operand.evaluate(x, y, z) == Integer.MIN_VALUE) {
            throw new OverflowException();
        }

        return -operand.evaluate(x, y, z);
    }
}