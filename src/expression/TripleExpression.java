package expression;

import expression.exceptions.CalcException;
import expression.exceptions.InputException;
import expression.exceptions.OverflowException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface TripleExpression {
    int evaluate(int x, int y, int z) throws OverflowException, CalcException, InputException;
}
