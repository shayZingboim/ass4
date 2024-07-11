//Shay Zingboim 208497255, Yair Kupershtock 322889015

import java.util.Map;

/**
 * And class that represents the logical AND operation.
 */
public class And extends BinaryExpression {
    /**
     * Constructor that sets the left and right expressions.
     *
     * @param left  the left expression
     * @param right the right expression
     */
    public And(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return getLeft().evaluate(assignment) && getRight().evaluate(assignment);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new And(getLeft().assign(var, expression), getRight().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(new Nand(getLeft().nandify(), getRight().nandify()),
                new Nand(getLeft().nandify(), getRight().nandify()));
    }

    @Override
    public Expression norify() {
        return new Nor(new Nor(getLeft().norify(), getLeft().norify()),
                new Nor(getRight().norify(), getRight().norify()));
    }

    @Override
    public String getSymbol() {
        return "&";
    }

    @Override
    public Expression simplify() {
        Expression leftSimplified = getLeft().simplify();
        Expression rightSimplified = getRight().simplify();
        // If the left expression is T, return the other expression.
        if (leftSimplified.toString().equals("T")) {
            return rightSimplified;
        }
        // If the right expression is T, return the other expression.
        if (rightSimplified.toString().equals("T")) {
            return leftSimplified;
        }
        // If the left expressions is F or the right expressions is F, return F.
        if (leftSimplified.toString().equals("F") || rightSimplified.toString().equals("F")) {
            return new Val(false);
        }
        // If the left and right expressions are equal, return one of them.
        if (leftSimplified.equals(rightSimplified)) {
            return leftSimplified;
        }
        // If the expression cant be simplified, return the original expression.
        return new And(leftSimplified, rightSimplified);
    }
}
