package expression.exceptions;

import expression.TripleExpression;
import expression.parser.ExpressionParser;

/**
 * Created by гыук on 24.03.2017.
 */
public class Main {
    public static void main(String[] args) throws InputException, OverflowException, CalcException {
        for (String s : new String[]{"p", "po", "pow", "pow2"}) {
            try {
                ExpressionParser parser = new ExpressionParser();

                TripleExpression expr = parser.parse(s);
                System.out.println(expr.evaluate(10, 10, 11));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}