package expression.exceptions;

import expression.TripleExpression;

/**
 * Created by гыук on 24.03.2017.
 */
public class Const implements TripleExpression {
    int valI;

    public Const(int val) {
        this.valI =  val;
    }

    public int evaluate(int x, int y, int z) {
        return valI;
    }

}