package expression.exceptions;

import expression.TripleExpression;

/**
 * Created by гыук on 03.04.2017.
 */
public class CheckedPow implements TripleExpression {

    private TripleExpression operand;

    public CheckedPow (TripleExpression operand) {
        this.operand = operand;
    }

    public int evaluate(int x, int y, int z) throws InputException, OverflowException, CalcException {
        int value = operand.evaluate(x, y, z);

        if (value < 0) {
            throw new CalcException("Value in power < 0");
        }
        int res = 1;
        int i = 1;
        if (value > 31) {
            throw new OverflowException();
        }
        for(; i <= value; i++){
            res *= 2;
        }
        return res;
    }
}
