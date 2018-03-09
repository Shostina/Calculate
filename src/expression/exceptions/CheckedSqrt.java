package expression.exceptions;

import expression.TripleExpression;

/**
 * Created by гыук on 03.04.2017.
 */


public class CheckedSqrt implements TripleExpression {
    private TripleExpression operand;
    private final int MAX = 46341;

    public CheckedSqrt(TripleExpression operand) {
        this.operand = operand;
    }

    public int evaluate(int x, int y, int z) throws InputException, OverflowException, CalcException {
        int value = operand.evaluate(x, y, z);

        if (value < 0) {
            throw new CalcException("Taking root of value under zero");
        }

        if (value == 1) {
            return 1;
        }

        int l = 0, r = value, m;
        if(value > MAX) {
            r = MAX;
        }
        while (r > l + 1) {
            m = (r + l) / 2;
            if (m * m > value) {
                r = m;
            } else {
                l = m;
            }
        }

        return l;
    }
}
