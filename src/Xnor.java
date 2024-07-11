//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.Map;

/**
 * The Or class is a class representing an or operation.
 */
public class Xnor extends BinaryExpression {
    /**
     * A constructor for the Xnor class.
     *
     * @param left  The left expression.
     * @param right The right expression.
     */
    public Xnor(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return getLeft().evaluate(assignment) == getRight().evaluate(assignment);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xnor(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(new Nand(getLeft().nandify(), getLeft().nandify()),
                new Nand(getRight().nandify(), getRight().nandify())),
                new Nand(getLeft().nandify(), getRight().nandify()));
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(getLeft().norify(), new Nor(getLeft().norify(), getRight().norify())),
                new Nor(getRight().norify(), new Nor(getLeft().norify(), getRight().norify())));
    }

    @Override
    public String getSymbol() {
        return "#";
    }

    @Override
    public Expression simplify() {
        Expression leftSimplified = getLeft().simplify();
        Expression rightSimplified = getRight().simplify();
        // if the left and right expressions are equal return true
        if (leftSimplified.equals(rightSimplified)) {
            return new Val(true);
        }
        // if the expression can't be simplified return the original expression
        return new Xnor(leftSimplified, rightSimplified);
    }
}