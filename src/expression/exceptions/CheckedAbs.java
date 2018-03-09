package expression.exceptions;

import expression.TripleExpression;

/**
 * Created by гыук on 03.04.2017.
 */
public class CheckedAbs implements TripleExpression {

    private TripleExpression operand;

    public CheckedAbs (TripleExpression operand) {
        this.operand = operand;
    }

    public int evaluate(int x, int y, int z) throws InputException, OverflowException, CalcException {
        if (operand.evaluate(x, y, z) == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        if (operand.evaluate(x, y, z) < 0) {
            return -operand.evaluate(x, y, z);
        } else {
            return operand.evaluate(x, y, z);
        }
    }
}
