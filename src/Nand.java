//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.Map;

/**
 * Nand class.
 */
public class Nand extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param left  left expression
     * @param right right expression
     */
    public Nand(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(getLeft().evaluate(assignment) && getRight().evaluate(assignment));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(getLeft().nandify(), getRight().nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(new Nor(getLeft().norify(), getLeft().norify()),
                new Nor(getRight().norify(), getRight().norify())),
                new Nor(new Nor(getLeft().norify(), getLeft().norify()),
                        new Nor(getRight().norify(), getRight().norify())));
    }

    @Override
    public String getSymbol() {
        return "A";
    }

    @Override
    public Expression simplify() {
        Expression leftSimplified = getLeft().simplify();
        Expression rightSimplified = getRight().simplify();
        // if the left and right are equal return the opposite of one of them
        if (leftSimplified.equals(rightSimplified)) {
            return new Not(leftSimplified);
        }
        // if one of them is false return true
        if (leftSimplified.toString().equals("F") || rightSimplified.toString().equals("F")) {
            return new Val(true);
        }
        // if the left is true return the opposite of the right
        if (leftSimplified.toString().equals("T")) {
            return new Not(rightSimplified);
        }
        // if the right is true return the opposite of the left
        if (rightSimplified.toString().equals("T")) {
            return new Not(leftSimplified);
        }
        // if the expression can't be simplified return the original expression
        return new Nand(leftSimplified, rightSimplified);
    }
}
