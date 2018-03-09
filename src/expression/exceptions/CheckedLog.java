package expression.exceptions;

import expression.TripleExpression;

/**
 * Created by гыук on 03.04.2017.
 */
public class CheckedLog implements TripleExpression {

    private TripleExpression operand;

    public CheckedLog (TripleExpression operand) {
        this.operand = operand;
    }

    public int evaluate(int x, int y, int z) throws InputException, OverflowException, CalcException {
        int value = operand.evaluate(x, y, z);

        if (value <= 0) {
            throw new CalcException("value in log <= 0");
        }

        if (value == 1) {
            return 0;
        }
        int res = 1;
        int i = 1;
        for(; i < 31; i++){
            res *= 2;
            if (res >= value) {
                break;
            }
        }
        if (res == value)
            return i;
        return i - 1;
    }
}
