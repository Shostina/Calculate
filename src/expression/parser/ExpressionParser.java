package expression.parser;

import expression.TripleExpression;
import expression.Variable;
import expression.exceptions.*;

/**
 * Created by гыук on 24.03.2017.
 */
public class ExpressionParser implements Parser {
    private ModifiedInput input;

    public TripleExpression parse(String expr) throws CalcException, OverflowException, InputException {
        input = new ModifiedInput(expr);
        return expression();
    }

    private TripleExpression expression() throws CalcException, OverflowException, InputException {
        TripleExpression acc = term();
        while (input.isNext()) {
            InputSymbol operator = input.next();

            switch (operator.getType()) {
                case PLUS:
                    acc = new CheckedAdd(acc, term());
                    break;

                case MINUS:
                    acc = new CheckedSubtract(acc, term());
                    break;

                default:
                    input.prev();
                    return acc;
            }
        }

        return acc;
    }

    private TripleExpression term() throws CalcException, OverflowException, InputException {
        TripleExpression acc = prim();
        while (input.isNext()) {
            InputSymbol operator = input.next();

            switch (operator.getType()) {
                case MUL:
                    acc = new CheckedMultiply(acc, prim());
                    break;

                case DIV:
                    acc = new CheckedDiv(acc, prim());
                    break;

                default:
                    input.prev();
                    return acc;
            }
        }

        return acc;
    }

    private TripleExpression prim() throws CalcException, OverflowException, InputException {
        InputSymbol operator = input.next();
        TripleExpression prim = null;
        switch (operator.getType()) {
            case CONST:
                prim = new Const(Integer.parseInt(operator.getValue()));
                break;

            case VARIABLE:
                prim = new Variable(operator.getValue());
                break;

            case MINUS:
                if (input.next().getType() == SymbolType.CONST) {
                    InputSymbol number = input.current();
                    prim = new Const(Integer.parseInt("-" + number.getValue()));
                } else {
                    input.prev();
                    prim = new CheckedNegate(prim());
                }
                break;
            case POW:
                prim = new CheckedPow(prim());
                break;
            case LOG:
                prim = new CheckedLog(prim());
                break;
            case LP:
                prim = expression();
                input.next();
                break;
        }

        return prim;
    }
}